grammar silver:compiler:extension:abella_compilation:encoding;


--Clean up duplicate accesses of attributes and equality hypotheses,
--   making their results equal through unification
function cleanClauses
[(String, AbellaType, String, Term, [[Metaterm]])] ::=
               eqs::[(String, AbellaType, String, Term, [[Metaterm]])]
{
  return
     case eqs of
     | [] -> []
     | (attr, ty, prod, hd, bodies)::rest ->
       (attr, ty, prod, hd, unifyBodies(bodies))::cleanClauses(rest)
     end;
}



--Clean up duplicate accesses of attributes and equality hypotheses,
--   making their results equal through unification
--Since we might change the head to match a particular body, we need
--   to pair the head with each body for returning
function cleanFunction
[(Term, [Metaterm])] ::= hd::Term bodies::[[Metaterm]]
{
  --Put the head with each body to use the same unification function
  --   as for attribute equations
  local joined::[[Metaterm]] =
        map(\ b::[Metaterm] -> termMetaterm(hd)::b, bodies);
  --Head is maintained as the first thing in each list
  local unified::[[Metaterm]] = unifyBodies(joined);
  local headOut::[(Term, [Metaterm])] =
        map(\ l::[Metaterm] ->
              case l of
              | termMetaterm(hd)::rest -> (hd, rest)
              | _ -> error("Anything else is impossible (" ++ head(l).unparse ++ ")")
              end,
            unified);
  return headOut;
}



--Clean up duplicate attribute equations and equality hypotheses,
--   unifying them and replacing them elsewhere
--For each body in bodies, we maintain the order of the clauses in it
function unifyBodies
[[Metaterm]] ::= bodies::[[Metaterm]]
{
  local body::[Metaterm] = head(bodies);
  --[(attr, tree var, result value, index in body list)]
  local attrEqs::[(String, (String, Integer), Term, Integer)] =
        foldl(\ rest::([(String, (String, Integer), Term, Integer)], Integer)
                m::Metaterm ->
                case m of
                | termMetaterm(
                     applicationTerm(nameTerm(accessRel),
                        consTermList(varTerm(tree, i),
                        consTermList(_,
                        singleTermList(aval)))))
                  when nameIsAccess(accessRel) ->
                  ( (accessToAttrName(accessRel), (tree, i),
                     aval, rest.2)::rest.1,
                    rest.2 + 1 )
                | _ -> (rest.1, rest.2 + 1)
                end,
              ([], 0), body).1;
  --Grouped by attribute and tree
  local grouped::[[(String, (String, Integer), Term, Integer)]] =
        let sorted::[(String, (String, Integer), Term, Integer)] =
            sortBy(\ p1::(String, (String, Integer), Term, Integer)
                     p2::(String, (String, Integer), Term, Integer) ->
                     p1.1 < p2.1 ||
                     (p1.1 == p2.1 && p1.2.1 < p2.2.1) ||
                     (p1.1 == p2.1 && p1.2.1 == p2.2.1 && p1.2.2 < p2.2.2),
                   attrEqs)
        in
          groupBy(\ p1::(String, (String, Integer), Term, Integer)
                     p2::(String, (String, Integer), Term, Integer) ->
                     p1.1 == p2.1 && p1.2.1 == p2.2.1 && p1.2.2 == p2.2.2,
                  sorted)
        end;
  --Remove duplicates accesses
  local removeIndices::[Integer] =
        flatMap(\ l::[(String, (String, Integer), Term, Integer)] ->
                  let x::[Integer] =
                      map(\ p::(String, (String, Integer), Term, Integer) ->
                            p.4, l)
                  in
                    tail(x) --leave one occurrence of the access
                  end,
                grouped);
  local removed::[Metaterm] =
        foldl(\ prev::(Integer, [Metaterm]) m::Metaterm ->
                if contains(prev.1, removeIndices)
                then (prev.1 + 1, prev.2)
                else (prev.1 + 1, m::prev.2),
              (0, []), body).2;
  local removedForward::[Metaterm] = reverse(removed); --foldl flips it
  --Find equality hypotheses for unification and remove them
  local eqUnifyInfo::([(Term, Term)], [Metaterm]) =
        foldr(\ m::Metaterm rest::([(Term, Term)], [Metaterm]) ->
                case m of
                | eqMetaterm(t1, t2) -> ( (new(t1), new(t2))::rest.1, rest.2 )
                | _ -> ( rest.1, m::rest.2 )
                end,
              ([], []), removedForward);
  --Unify attribute access results and equal results
  local eqs::[(Term, Term)] =
        flatMap(\ l::[(String, (String, Integer), Term, Integer)] ->
                  let x::[Term] =
                      map(\ p::(String, (String, Integer), Term, Integer) ->
                            p.3, l)
                  in
                    allPairs(x)
                  end,
                grouped) ++ eqUnifyInfo.1;
  local unified::Maybe<[( (String, Integer), Term )]> =
        unifyTermEqs(eqs);
  local substituted::[Metaterm] =
        case unified of
        | nothing() ->
          error("Should not access substituted when not consistent")
        | just( substs ) ->
          --foldl to do earlier substitutions first, with results
          --which might then be further replaced
          foldl(\ prev::[Metaterm] subst::((String, Integer), Term) ->
                  map(replaceVar(subst.1, subst.2, _), prev),
                eqUnifyInfo.2, substs)
        end;
  --
  return
     case bodies of
     | [] -> []
     | _::rest ->
       case unified of
       | nothing() -> unifyBodies(rest)
       | just(_) -> substituted::unifyBodies(rest)
       end
     end;
}



--Unify all the pairs, resulting in the given variable substitutions
--nothing() indicates an inability to unify
function unifyTermEqs
Maybe<[( (String, Integer), Term )]> ::= eqs::[(Term, Term)]
{
  local eq_result::
        (Boolean, Maybe<((String, Integer), Term)>, [(Term, Term)]) =
        unifyTerms(head(eqs).1, head(eqs).2);
  local newEqs::[(Term, Term)] = eq_result.3 ++ tail(eqs);
  local replacedEqs::[(Term, Term)] =
        case eq_result.2 of
        | nothing() -> newEqs
        | just((v, tm)) ->
          map(\ p::(Term, Term) ->
                ( replaceVar_Term(v, tm, p.1),
                  replaceVar_Term(v, tm, p.2) ),
              newEqs)
        end;
  return
     case eqs of
     | [] -> just([])
     | _::_ ->
      if eq_result.1
      then case unifyTermEqs(replacedEqs), eq_result.2 of
           | nothing(), _ -> nothing()
           | just(subst), nothing() -> just(subst)
           | just(subst), just(here) -> just(here::subst)
           end
      else nothing()
    end;
}

--(successful unification, substitution generated, new equations)
--Note we can have (true, nothing(), _) if tm1 == tm2
function unifyTerms
( Boolean, Maybe<((String, Integer), Term)>, [(Term, Term)] ) ::=
      tm1::Term tm2::Term
{
  return
     case tm1, tm2 of
     | varTerm(s1, i1), varTerm(s2, i2) ->
       if s1 == s2 && i1 == i2
       then ( true, nothing(), [] )
       else ( true, just(( (s1, i1), varTerm(s2, i2) )), [] )
     | varTerm(s, i), tm ->
       ( true, just(((s, i), new(tm))), [] )
     | tm, varTerm(s, i) ->
       ( true, just(((s, i), new(tm))), [] )
     | nameTerm(s1), nameTerm(s2) ->
       if s1 == s2
       then ( true, nothing(), [] )
       else ( false, nothing(), [] )
     | consTerm(t11, t12), consTerm(t21, t22) ->
       ( true, nothing(), [(new(t11), new(t21)), (new(t12), new(t22))] )
     | nilTerm(), nilTerm() ->
       ( true, nothing(), [] )
     | applicationTerm(f1, args1), applicationTerm(f2, args2) ->
       ( length(args1.argList) == length(args2.argList),
         nothing(),
         (new(f1), new(f2))::zipWith(pair(_, _), args1.argList, args2.argList) )
     | _, _ -> ( false, nothing(), [] )
     end;
}

