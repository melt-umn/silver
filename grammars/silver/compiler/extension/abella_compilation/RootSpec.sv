grammar silver:compiler:extension:abella_compilation;


imports silver:compiler:driver:util;


--Whether we should try to output anything
synthesized attribute shouldOutput::Boolean;
--The text we should output for the grammar
synthesized attribute output::String;


attribute
   shouldOutput, output,
   prods, nonterminals, attrs, attrOccurrences, localAttrs,
   inheritedAttrs, attrEqInfo
occurs on RootSpec;


aspect production interfaceRootSpec
top::RootSpec ::= _ _ _
{
  top.shouldOutput = false;
  top.output = "";
}

aspect production errorRootSpec
top::RootSpec ::= _ _ _ _ _
{
  top.shouldOutput = false;
  top.output = "";
}

aspect production grammarRootSpec
top::RootSpec ::= g::Grammar grammarName::String grammarSource::String
                  grammarTime::Integer generateLocation::String
{
  top.shouldOutput = grammarName != "silver:core";
  local componentName::String = shortestName(grammarName);
  --[(nonterminal type name (with $nt_), [(production name, production type)])]
  local prodsByType::[(String, [(String, AbellaType)])] =
        let byType::[(AbellaType, String, AbellaType)] =
            map(\ p::(String, AbellaType) ->
                  ( p.2.resultType, p.1, p.2 ), g.prods)
        in
        let sorted::[(AbellaType, String, AbellaType)] =
            sortBy(\ p1::(AbellaType, String, AbellaType)
                     p2::(AbellaType, String, AbellaType) ->
                     case p1.1, p2.1 of
                     | nameAbellaType(n1), nameAbellaType(n2) -> n1 <= n2
                     | _, _ -> error("Not possible")
                     end,
                   byType)
        in
        let grouped::[[(AbellaType, String, AbellaType)]] =
            groupBy(\ p1::(AbellaType, String, AbellaType)
                      p2::(AbellaType, String, AbellaType) ->
                      case p1.1, p2.1 of
                      | nameAbellaType(n1), nameAbellaType(n2) -> n1 == n2
                      | _, _ -> error("Not possible")
                      end,
                    sorted)
        in
          map(\ l::[(AbellaType, String, AbellaType)] ->
                ( case head(l).1 of
                  | nameAbellaType(n) -> n
                  | _ -> error("Not possible")
                  end,
                  map(\ p::(AbellaType, String, AbellaType) ->
                        ( p.2, p.3 ), l) ),
              grouped)
        end end end;
  --Find the productions which are missing equations for each
  --   attribute and produce empty clauses
  local sortedAttrEquations::[(String, AbellaType, String)] =
        let filtered::[(String, AbellaType, String)] =
            map(\ p::(String, AbellaType, String, Term, [[Metaterm]]) ->
                  (p.1, p.2, p.3), g.attrEqInfo)
        in
          sortBy(\ p1::(String, AbellaType, String)
                   p2::(String, AbellaType, String) ->
                   p1.1 <= p2.1 &&
                   case p1.2, p2.2 of
                   | nameAbellaType(n1), nameAbellaType(n2) -> n1 <= n2
                   | _, _ -> error("Not possible")
                   end,
                 filtered)
        end;
  local groupedAttrsProds::[[(String, AbellaType, String)]] =
        groupBy(\ p1::(String, AbellaType, String)
                  p2::(String, AbellaType, String) ->
                  p1.1 == p2.1 && tysEqual(p1.2, p2.2),
                sortedAttrEquations);
  local foundProds::[(String, AbellaType, [String])] =
        map(\ l::[(String, AbellaType, String)] ->
              (head(l).1, head(l).2,
               map(\ p::(String, AbellaType, String) -> p.3, l)),
            groupedAttrsProds);
  local missingProdsByAttr::[(String, AbellaType, [(String, AbellaType)])] =
        map(\ eqs::(String, AbellaType, [String]) ->
              case eqs.2 of
              | nameAbellaType(nt) ->
                let prods::[(String, AbellaType)] =
                    findAssociated(nt, prodsByType).fromJust
                in
                  ( eqs.1, eqs.2,
                    foldr(\ p::(String, AbellaType)
                            rest::[(String, AbellaType)] ->
                            if contains(p.1, eqs.3)
                            then rest
                            else p::rest,
                          [], prods) )
                end
              | _ -> error("Not possible")
              end,
            foundProds);
  local missingClauses::[(String, AbellaType, [DefClause])] =
        map(\ p::(String, AbellaType, [(String, AbellaType)]) ->
              ( p.1, p.2,
                map(\ prod::(String, AbellaType) ->
                      let treeTm::Term =
                          buildApplication(
                             nameTerm(nameToProd(prod.1)),
                             foldr(\ t::AbellaType
                                     rest::(Integer, [Term]) ->
                                     ( rest.1 + 1,
                                       nameTerm("$T" ++ toString(rest.1))::rest.2 ),
                                   (0, []), prod.2.argumentTypes).2)
                      in
                        factClause(
                           termMetaterm(
                              buildApplication(
                                 nameTerm(equationName(p.1, p.2)),
                                 [treeTm, nameTerm("$NodeTree")])))
                      end,
                    p.3) ),
            missingProdsByAttr);
  local attrClauses::[(String, AbellaType, [DefClause])] =
        produceClauses(g.attrEqInfo, g.inheritedAttrs) ++
        missingClauses;
  --Find the attributes which need to be set by a nonterminal on which
  --   they do not occur so we know we need to make a full relation
  --e.g. Root sets env on children even though it has no env itself
  local associatedAttrs::[(String, [String])] =
        let filtered::[(String, AbellaType)] =
            map(\ p::(String, AbellaType, String, Term, [[Metaterm]]) ->
                  (p.1, p.2), g.attrEqInfo)
        in
        let cleaned::[(String, AbellaType)] =
            nubBy(\ p1::(String, AbellaType) p2::(String, AbellaType) ->
                    p1.1 == p2.1 && tysEqual(p1.2, p2.2), filtered)
        in
        let sorted::[(String, AbellaType)] =
            sortBy(\ p1::(String, AbellaType) p2::(String, AbellaType) ->
                     p1.1 <= p2.1, cleaned)
        in
        let grouped::[[(String, AbellaType)]] =    
            groupBy(\ p1::(String, AbellaType) p2::(String, AbellaType) ->
                      p1.1 == p2.1, sorted)
        in
        let paired::[(String, [String])] =
            map(\ l::[(String, AbellaType)] ->
                  ( head(l).1, map(\ p::(String, AbellaType) ->
                                     nonterminalTypeToName(p.2),
                                   l) ), grouped)
        in
          map(\ p::(String, [String]) ->
                case findAssociated(p.1, g.attrOccurrences) of
                | nothing() -> error("Attr must exist")
                | just(nts_tys) ->
                  let nts::[String] = map(fst(_), nts_tys)
                  in
                    ( p.1,
                      foldr(\ nt::String rest::[String] ->
                              if contains(nt, nts)
                              then rest
                              else nt::rest,
                            [], p.2) )
                  end
                end,
              paired)
        end end end end end;
  --
  top.output =
      generateContents(g.nonterminals, g.attrs, g.attrOccurrences,
         g.inheritedAttrs, g.localAttrs, associatedAttrs, g.prods,
         attrClauses, shortestName(grammarName));
}


--Build the clauses based on information for attribute equations
function produceClauses
[(String, AbellaType, [DefClause])] ::=
              info::[(String, AbellaType, String, Term, [[Metaterm]])]
              inhAttrs::[String]
{
  local splitInhSyn::([(String, AbellaType, String, Term, [[Metaterm]])],
                      [(String, AbellaType, String, Term, [[Metaterm]])]) =
        partition(\ p::(String, AbellaType, String, Term, [[Metaterm]]) ->
                    contains(p.1, inhAttrs),
                  info);
  --Group into things that need to be single clauses (same attr, same prod)
  local groupedInhs::[[(String, AbellaType, String, Term, [[Metaterm]])]] =
        groupBy(\ p1::(String, AbellaType, String, Term, [[Metaterm]])
                  p2::(String, AbellaType, String, Term, [[Metaterm]]) ->
                  p1.1 == p2.1 && p1.3 == p2.3,
                splitInhSyn.1);
  local inhs::[(String, AbellaType, String, Term, [[Metaterm]])] =
        map(combineEquations(_), groupedInhs);
  local syns::[(String, AbellaType, String, Term, [[Metaterm]])] =
        splitInhSyn.2;
  --Clean clauses to get only one access of each attr
  local cleanInhs::[(String, AbellaType, String, Term, [[Metaterm]])] =
        cleanClauses(inhs);
  local cleanSyns::[(String, AbellaType, String, Term, [[Metaterm]])] =
        cleanClauses(syns);
  --Replace all the varTerms with nameTerms and add bindings
  local noVars::[(String, AbellaType, String, Term, [Metaterm])] =
        map(\ p::(String, AbellaType, String, Term, [[Metaterm]]) ->
              let call::(Term, [Metaterm]) =
                  fillVars(p.4, map(\ body::[Metaterm] ->
                                      if null(body)
                                      then trueMetaterm() --possible?
                                      else foldl(andMetaterm(_, _),
                                                 head(body),
                                                 tail(body)), p.5))
              in
                ( p.1, p.2, p.3, call.1, call.2 )
              end,
            cleanInhs ++ cleanSyns);
  --
  return
     map(\ p::(String, AbellaType, String, Term, [Metaterm]) ->
           ( p.1, p.2,
             map(\ b::Metaterm -> ruleClause(termMetaterm(p.4), b),
                 p.5) ),
         noVars);
}

--Take the information for different inh equations and combine them
function combineEquations
(String, AbellaType, String, Term, [[Metaterm]]) ::=
   eqs::[(String, AbellaType, String, Term, [[Metaterm]])]
{
  local rest::(String, AbellaType, String, Term, [[Metaterm]]) =
        combineEquations(tail(eqs));
  local first::(String, AbellaType, String, Term, [[Metaterm]]) =
        head(eqs);
  --We need to make the names in the clause heads consistent with each other
  --This also requires changing names in the bodies, and we will
  --   combine the two bodies into a single body
  local consistentNames::(Term, [[Metaterm]]) =
        makeConsistentNames(rest.4, rest.5, first.4, first.5);
  return
     case eqs of
     | [] -> error("Impossible empty (combineEquations)")
     | [p] -> p
     | _ -> (first.1, first.2, first.3,
             consistentNames.1, consistentNames.2)
     end;
}

function makeConsistentNames
(Term, [[Metaterm]]) ::= hd1::Term body1::[[Metaterm]]
                         hd2::Term body2::[[Metaterm]]
{
  local call::(Term, [[Metaterm]], [[Metaterm]]) =
        makeConsistentNames_help(hd1, body1, hd2, body2);
  local joined::[[Metaterm]] =
        foldr(\ b1::[Metaterm] rest::[[Metaterm]] ->
                map(\ l::[Metaterm] -> b1 ++ l,
                    call.3) ++ rest,
              [], call.2);
  return ( call.1, joined );
}
{-
  Make the names in the two heads be consistent, also replacing them
  in the bodies to keep the same semantic meaning.
-}
function makeConsistentNames_help
(Term, [[Metaterm]], [[Metaterm]]) ::= hd1::Term body1::[[Metaterm]]
                                       hd2::Term body2::[[Metaterm]]
{
  return
     case hd1, hd2 of
     | nilTerm(), nilTerm() ->
       ( nilTerm(), body1, body2 )
     | consTerm(t11, t12), consTerm(t21, t22) ->
       let sub1::(Term, [[Metaterm]], [[Metaterm]]) =
           makeConsistentNames_help(t11, body1, t21, body2)
       in
       let sub2::(Term, [[Metaterm]], [[Metaterm]]) =
           makeConsistentNames_help(t12, sub1.2, t22, sub1.3)
       in
         ( consTerm(sub1.1, sub2.1), sub2.2, sub2.3 )
       end end
     | applicationTerm(f1, args1), applicationTerm(f2, args2) ->
       let fsub::(Term, [[Metaterm]], [[Metaterm]]) =
           makeConsistentNames_help(f1, body1, f2, body2)
       in
       let argsub::(TermList, [[Metaterm]], [[Metaterm]]) =
           makeConsistentNames_help_list(args1, fsub.2, args2, fsub.3)
       in
         ( applicationTerm(fsub.1, argsub.1), argsub.2, argsub.3 )
       end end
     | nameTerm(name1), nameTerm(name2) ->
       if name1 == name2
       then ( nameTerm(name1), body1, body2 )
       else error("Name terms must match because they are constants")
     | varTerm(name1, i1), varTerm(name2, i2) ->
       if name1 == name2 && i1 == i2
       then ( varTerm(name1, i1), body1, body2 )
       else let newVar::Integer = genInt() in
                ( varTerm(name1, newVar),
                  map(\ l::[Metaterm] ->
                        map(replaceVar((name1, i1),
                               varTerm(name1, newVar), _), l),
                      body1),
                  map(\ l::[Metaterm] ->
                        map(replaceVar((name2, i2),
                               varTerm(name1, newVar), _), l),
                      body2) )
            end
     | _, _ ->
       error("Unexpected case in makeConsistentNames_help" ++
             " (" ++ hd1.unparse ++ "  ;  " ++ hd2.unparse ++ ")")
     end;
}
function makeConsistentNames_help_list
(TermList, [[Metaterm]], [[Metaterm]]) ::= hd1::TermList body1::[[Metaterm]]
                                           hd2::TermList body2::[[Metaterm]]
{
  return
     case hd1, hd2 of
     | nilTermList(), nilTermList() ->
       ( nilTermList(), body1, body2 )
     | singleTermList(t1), singleTermList(t2) ->
       let sub::(Term, [[Metaterm]], [[Metaterm]]) =
           makeConsistentNames_help(t1, body1, t2, body2)
       in
         ( singleTermList(sub.1), sub.2, sub.3 )
       end
     | consTermList(t1, rest1), consTermList(t2, rest2) ->
       let tsub::(Term, [[Metaterm]], [[Metaterm]]) =
           makeConsistentNames_help(t1, body1, t2, body2)
       in
       let restsub::(TermList, [[Metaterm]], [[Metaterm]]) =
           makeConsistentNames_help_list(rest1, tsub.2, rest2, tsub.3)
       in
         ( consTermList(tsub.1, restsub.1), restsub.2, restsub.3 )
       end end
     | _, _ -> error("Unexpected case in makeConsistentNames_help_list")
     end;
}

--Remove duplicate accesses of attributes, making their results equal
function cleanClauses
[(String, AbellaType, String, Term, [[Metaterm]])] ::=
               eqs::[(String, AbellaType, String, Term, [[Metaterm]])]
{
  return
     case eqs of
     | [] -> []
     | (attr, ty, prod, hd, bodies)::rest ->
       (attr, ty, prod, hd, cleanBodies(bodies))::cleanClauses(rest)
     end;
}
function cleanBodies
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
                        singleTermList(
                           applicationTerm(attrex, valList))))))
                  when nameIsAccess(accessRel) ->
                  ( (accessToAttrName(accessRel), (tree, i),
                     head(valList.argList), rest.2)::rest.1,
                    rest.2 + 1 )
                | _ -> (rest.1, rest.2 + 1)
                end,
              ([], 0), body).1;
  --Grouped by attribute and tree
  local grouped::[[(String, (String, Integer), Term, Integer)]] =
        let sorted::[(String, (String, Integer), Term, Integer)] =
            sortBy(\ p1::(String, (String, Integer), Term, Integer)
                     p2::(String, (String, Integer), Term, Integer) ->
                     p1.1 <= p2.1 && p1.2.1 <= p2.2.1 && p1.2.2 <= p2.2.2,
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
  --Unify attribute access results
  local eqs::[(Term, Term)] =
        flatMap(\ l::[(String, (String, Integer), Term, Integer)] ->
                  let x::[Term] =
                      map(\ p::(String, (String, Integer), Term, Integer) ->
                            p.3, l)
                  in
                    allPairs(x)
                  end,
                grouped);
  local unified::Maybe<[( (String, Integer), Term )]> =
        unifyTermEqs(eqs);
  local substituted::[Metaterm] =
        case unified of
        | nothing() -> removed
        | just( substs ) ->
          --foldl to do earlier substitutions first, with results
          --which might then be further replaced
          foldl(\ prev::[Metaterm] subst::((String, Integer), Term) ->
                  map(replaceVar(subst.1, subst.2, _), prev),
                removed, substs)
        end;
  --
  return
     case bodies of
     | [] -> []
     | _::rest ->
       case unified of
       | nothing() -> cleanBodies(rest)
       | just(_) -> substituted::cleanBodies(rest)
       end
     end;
}

--Replace all varTerms with nameTerms with unique names
function fillVars
(Term, [Metaterm]) ::= hd::Term bodies::[Metaterm]
{
  local headNames::[String] = generateNamesFromVars(hd.freeVars, []);
  local zippedHeadNames::[((String, Integer), String)] =
        zipWith(pair(_, _), hd.freeVars, headNames);
  local filledHead::Term =
        foldr(\ p::((String, Integer), String) rest::Term ->
                replaceVar_Term(p.1, nameTerm(p.2), rest),
              hd, zippedHeadNames);
  local cleanedBodies::[Metaterm] =
        foldr(\ p::((String, Integer), String) rest::[Metaterm] ->
                map(replaceVar(p.1, nameTerm(p.2), _), rest),
              bodies, zippedHeadNames);
  local filledBodies::[Metaterm] =
        fillVarsBodies(cleanedBodies, headNames);
  return ( filledHead, filledBodies );
}

--Replace all varTerms in each clause body with a nameTerm and add
--   bindings for the names
function fillVarsBodies
[Metaterm] ::= bodies::[Metaterm] usedNames::[String]
{
  local body::Metaterm = head(bodies);
  local freevars::[(String, Integer)] = nub(body.freeVars);
  local newNames::[String] =
        generateNamesFromVars(freevars, usedNames);
  local filledBody::Metaterm =
        foldr(\ p::((String, Integer), String) rest::Metaterm ->
                replaceVar(p.1, nameTerm(p.2), rest),
              body, zipWith(pair(_, _), freevars, newNames));
  local boundBody::Metaterm =
        if null(newNames)
        then filledBody
        else bindingMetaterm(existsBinder(),
                map(\ x::String -> (x, nothing()), newNames),
                filledBody);
  return
     case bodies of
     | [] -> []
     | _::rest -> boundBody::fillVarsBodies(rest, usedNames)
     end;
}

--Returns the new names in order corresponding to the vars
function generateNamesFromVars
[String] ::= vars::[(String, Integer)] usedNames::[String]
{
  return
     case vars of
     | [] -> []
     | (name, _)::rest ->
       let newName::String = makeUniqueNameFromBase(name, usedNames)
       in
         newName::generateNamesFromVars(rest, newName::usedNames)
       end
     end;
}

