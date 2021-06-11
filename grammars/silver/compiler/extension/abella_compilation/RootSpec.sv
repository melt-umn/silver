grammar silver:compiler:extension:abella_compilation;


imports silver:compiler:driver:util;


--Whether we should try to output anything
synthesized attribute shouldOutput::Boolean;
--The text we should output for the grammar
synthesized attribute output::String;


attribute
   shouldOutput, output,
   prods, nonterminals, attrs, attrOccurrences, localAttrs,
   inheritedAttrs, associatedAttrs, attrEqClauses, attrEqInfo
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
  local attrClauses::[(String, AbellaType, [DefClause])] =
        produceClauses(g.attrEqInfo, g.inheritedAttrs);
  top.output =
      generateContents(g.nonterminals, g.attrs, g.attrOccurrences,
         g.inheritedAttrs, g.localAttrs, g.associatedAttrs, g.prods,
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
  --Cleaning not implemented yet
  local cleanInhs::[(String, AbellaType, String, Term, [[Metaterm]])] =
        inhs;
  local cleanSyns::[(String, AbellaType, String, Term, [[Metaterm]])] =
        syns;
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
            inhs ++ syns);
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
       else --I should try to fix the names here
            error("Did you define an inherited attribute on two " ++
                  "different children in two different aspects?  " ++
                  "Why would you do that?  Perhaps I'll help you " ++
                  "in the future, but for now, put them in the " ++
                  "same production.")
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

