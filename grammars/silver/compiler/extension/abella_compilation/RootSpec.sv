grammar silver:compiler:extension:abella_compilation;


import silver:util:treeset as set;
import silver:util:graph as graph;
import silver:util:treemap as tmap;


--Whether we should try to output anything
synthesized attribute shouldOutput::Boolean;
--The text we should output for the grammar encoding
synthesized attribute output::String;
--The text we should output for the interface file
synthesized attribute interface_output::String;
--An error message for if something went wrong
synthesized attribute error_output::String;


attribute
   shouldOutput, output, interface_output, error_output
occurs on RootSpec;


aspect production interfaceRootSpec
top::RootSpec ::= _ _ _
{
  top.shouldOutput = false;
  top.output = "";
  top.interface_output = "";
  top.error_output = "";
}

aspect production errorRootSpec
top::RootSpec ::= _ _ _ _ _
{
  top.shouldOutput = false;
  top.output = "";
  top.interface_output = "";
  top.error_output = "";
}

aspect production grammarRootSpec
top::RootSpec ::= g::Grammar grammarName::String grammarSource::String
                  grammarTime::Integer generateLocation::String
{
  local componentName::String = encodeName(grammarName);

  --Everything from the current grammar AND imported by it
  local relevantEnv::Decorated Env =
        appendEnv(g.globalImports, g.env);

  local prodsByType::[(String, [(String, AbellaType)])] =
        let prodsByNT::[EnvTree<ValueDclInfo>] =
            relevantEnv.prodsForNtTree
        in
        let prodsLst::[(String, ValueDclInfo)] =
            flatMap(tmap:toList(_), prodsByNT)
        in
        let sorted::[(String, ValueDclInfo)] =
            sortBy(\ p1::(String, ValueDclInfo) p2::(String, ValueDclInfo) ->
                     p1.1 <= p2.1,
                   prodsLst)
        in
        let grouped::[[(String, ValueDclInfo)]] =
            groupBy(\ p1::(String, ValueDclInfo) p2::(String, ValueDclInfo) ->
                      p1.1 == p2.1,
                    sorted)
        in
        let prods::[(String, [ValueDclInfo])] =
            map(\ l::[(String, ValueDclInfo)] ->
                  ( nameToNonterminal(colonsToEncoded(head(l).1)),
                    map(snd, l) ),
                grouped)
        in
        let expandProd::[(String, [(String, AbellaType)])] =
            map(\ p::(String, [ValueDclInfo]) ->
                  ( p.1,
                    map(\ d::ValueDclInfo ->
                          let pname::String = d.fullName
                          in
                            ( colonsToEncoded(pname),
                              lookupProdType(pname, relevantEnv) )
                          end,
                        p.2) ),
                prods)
        in
          expandProd
        end end end end end end;
  --[(nonterminal type name (with $nt_), [(production name, production type)])]
  local prodsByType1::[(String, [(String, AbellaType)])] =
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
        let x::[(String, [(String, AbellaType)])] =
            map(\ l::[(AbellaType, String, AbellaType)] ->
                  ( case head(l).1 of
                    | nameAbellaType(n) -> n
                    | _ -> error("Not possible")
                    end,
                    map(\ p::(AbellaType, String, AbellaType) ->
                          ( p.2, p.3 ), l) ),
                grouped)
        in
          x
        end end end end;
  --Find the productions which are missing equations for each
  --   attribute and produce empty clauses
  local sortedAttrEquations::[(String, AbellaType, String)] =
        let filtered::[(String, AbellaType, String)] =
            map(\ p::(String, AbellaType, String, Term, [[Metaterm]]) ->
                  (p.1, p.2, p.3), g.attrEqInfo)
        in
          sortBy(\ p1::(String, AbellaType, String)
                   p2::(String, AbellaType, String) ->
                   case p1.2, p2.2 of
                   | nameAbellaType(n1), nameAbellaType(n2) ->
                     p1.1 < p2.1 || (p1.1 == p2.1 && n1 <= n2)
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
               map(\ p::(String, AbellaType, String) -> colonsToEncoded(p.3), l)),
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
                                       nameTerm("T" ++ toString(rest.1))::rest.2 ),
                                   (0, []), prod.2.argumentTypes).2)
                      in
                        factClause(
                           termMetaterm(
                              buildApplication(
                                 nameTerm(equationName(p.1, p.2) ++
                                          "__" ++ componentName),
                                 [nameTerm("TreeName"), treeTm,
                                  nameTerm("NodeTree")])))
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
        --Cut down the equation information to attr and tree root type
        let filtered::[(String, AbellaType)] =
            map(\ p::(String, AbellaType, String, Term, [[Metaterm]]) ->
                  (p.1, p.2), g.attrEqInfo)
        in
        --Cut down to unique pairs of attr and tree root type
        let cleaned::[(String, AbellaType)] =
            nubBy(\ p1::(String, AbellaType) p2::(String, AbellaType) ->
                    p1.1 == p2.1 && tysEqual(p1.2, p2.2), filtered)
        in
        --Sort by attribute
        let sorted::[(String, AbellaType)] =
            sortBy(\ p1::(String, AbellaType) p2::(String, AbellaType) ->
                     p1.1 <= p2.1, cleaned)
        in
        --Group by attribute
        let grouped::[[(String, AbellaType)]] =    
            groupBy(\ p1::(String, AbellaType) p2::(String, AbellaType) ->
                      p1.1 == p2.1, sorted)
        in
        --Put into groups of (attr, [nonterminals])
        let paired::[(String, [String])] =
            map(\ l::[(String, AbellaType)] ->
                  ( head(l).1, map(\ p::(String, AbellaType) ->
                                     nonterminalTypeToName(p.2),
                                   l) ), grouped)
        in
          --Get just the ones without any occurrence already, either here or in a previous grammar
          flatMap(\ p::(String, [String]) ->
                    let pOccurs::[String] =
                        case findAssociated(p.1, g.attrOccurrences) of
                        | nothing() -> [] --unknown because not declared in this grammar
                        | just(nts_tys) -> map(fst, nts_tys)
                        end
                    in
                      [( p.1,
                         foldr(\ nt::String rest::[String] ->
                                 if contains(nt, pOccurs)
                                 then rest
                                 else if checkAttrNtFullRelationExists(p.1, nt, grammarName, relevantEnv, g.flowEnv)
                                 then rest
                                 else nt::rest,
                               [], p.2) )]
                    end, paired)
        end end end end end;
  --
  top.output =
      generateContents(g.nonterminals, g.attrs, g.attrOccurrences,
         g.inheritedAttrs, g.localAttrs, associatedAttrs, g.prods,
         attrClauses, g.localAttrDefs, g.funRelClauses,
         encodeName(grammarName), relevantEnv);


  local no_core_module_names::[String] =
        remove("silver:core", top.moduleNames);
  --Figure out the import order for the imported grammars
  local dependencyGraph::graph:Graph<String> =
        buildDependencyGraph(graph:empty(), top.compiledGrammars,
                             grammarName, no_core_module_names);
  local full_import_order::[String] =
        graphToOrder(grammarName, dependencyGraph);
  local unique_import_order::[String] =
        --remove core until the issues with it are sorted out
        remove("silver:core", nub(full_import_order));
  local order_string::String =
        if null(unique_import_order)
        then ". "
        else foldl(\ rest::String s::String ->
                     rest ++ ", " ++ s,
                   head(unique_import_order),
                   tail(unique_import_order)) ++ ". ";
  --Figure out the theorem order for imported theorems and set up the imported definitions
  local grammarInformation::Either<String [(String, [DefinitionElement], [ParsedElement])]> =
        gatherGrammarInfo(no_core_module_names, top.generateLocation,
                          top.grammarSource, unsafeIO());
  --Clean it up so each definition only shows up once
  local combined_defs::[DefinitionElement] =
        foldr(\ d::DefinitionElement rest::[DefinitionElement] ->
                if containsBy(definitionElements_eq, d, rest)
                then rest
                else d::rest,
              [],
              foldr(\ p::(String, [DefinitionElement], [ParsedElement])
                      rest::[DefinitionElement] ->
                      p.2 ++ rest,
                    [], grammarInformation.fromRight));
  local definition_string::String =
        implode("", map((.encode), combined_defs));
  local ordered_grammar_information::[[ParsedElement]] =
        map(\ p::(String, [DefinitionElement], [ParsedElement]) -> p.3,
           sortBy(\ p1::(String, [DefinitionElement], [ParsedElement])
                    p2::(String, [DefinitionElement], [ParsedElement]) ->
                    p1.1 <= p2.1,
                  grammarInformation.fromRight));
  local combined_thms::[ParsedElement] =
        combineAllOrders(filter(\ l::[ParsedElement] -> !null(l),
                                ordered_grammar_information));
  local theorem_string::String =
        implode("", map((.encode), combined_thms));

  --Build the full contents of the interface file
  top.interface_output =
      grammarName ++ ". " ++ order_string ++ definition_string ++ theorem_string;


  top.shouldOutput =
      grammarName != "silver:core" &&
      grammarInformation.isRight;
  top.error_output =
      if grammarName == "silver:core"
      then ""
      else "Error:  Could not generate for grammar " ++ grammarName ++
           ":  " ++ grammarInformation.fromLeft ++ "\n\n";
}


--Check whether attr is ever set on a child by a production building
--   nt not introduced by this grammar or if attr occurs on nonterminal
--Determines whether we need to introduce a new full relation
--Note:  abella_nt is something like gr$*$am$*$mar$*$Nt, as is abella_attr
function checkAttrNtFullRelationExists
Boolean ::= abella_attr::String abella_nt::String currentGrammar::String e::Decorated Env f::FlowEnv
{
  --convert to gr:am:mar:Nt
  local nt::String = encodedToColons(abella_nt);
  local attr::String = encodedToColons(abella_attr);

  --Find whether attr is declared to occur on nt anywhere
  local occursOn::Boolean = length(getOccursDcl(attr, nt, e)) > 0;

  --find known productions for nt to search for setting attr on child
  local knownProds::[ValueDclInfo] = getKnownProds(nt, e);
  --only interested in prods from other grammars
  local notThisGrammarProds::[ValueDclInfo] =
        filter(\ v::ValueDclInfo -> v.sourceGrammar != currentGrammar, knownProds);
  --prod names and child names for the prods
  local namesChildren::[(String, [String])] =
        map(\ v::ValueDclInfo -> (v.fullName, v.namedSignature.inputNames),
            notThisGrammarProds);
  --whether the prods has an equation for attr on any child
  local equationForAttr::[Boolean] =
        map(\ p::(String, [String]) ->
              any(map(\ name::String ->
                        !null(lookupInh(p.1, name, attr, f)),
                      p.2)),
            namesChildren);
  --only care if any of them have an equation
  local attrSet::Boolean = any(equationForAttr);

  return occursOn || attrSet;
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


{-
  Starting from a given grammar, build a graph with directed edges
  from the original grammar down through the grammars on which it
  depends.

  For example, if A imports B and C and B imports D, we will get a
  graph with the following edges:
  * A -> B
  * A -> C
  * B -> D
-}
function buildDependencyGraph
graph:Graph<String> ::= initialGraph::graph:Graph<String>
                        compiledGrammars::EnvTree<Decorated RootSpec>
                        currentGrammar::String modules::[String]
{
  return
     foldr(\ module::String g::graph:Graph<String> ->
             let rs::[Decorated RootSpec] =
                 searchEnvTree(module, compiledGrammars)
             in
               --silver:core ends up depending on itself, but only on
               --  itself, so we can't do it but also don't need to do it
               if module == "silver:core"
               then g
               else
                  --I'm not sure if we can actually get different information
                  --from different members of r
                  foldr(\ r::Decorated RootSpec rest::graph:Graph<String> ->
                          buildDependencyGraph(rest, compiledGrammars,
                                               module, r.moduleNames),
                        g, rs)
             end,
           graph:add(map(pair(currentGrammar, _), modules),
                     initialGraph),
           modules);
}


{-
  Create an ordering of vertices in the graph which puts every vertex
  earlier than the vertex pointing to it, not including the initial
  vertex.  Assumes the graph is a tree.

  @param initial  The initial vertex, which logically comes at the end of the list, but is not included
  @param graph  The graph which we are ordering
  @returns  An ordering of the vertices in the graph
-}
function graphToOrder
[vertex] ::= initial::vertex graph::graph:Graph<vertex>
{
  local edges_out::[vertex] =
        set:toList(graph:edgesFrom(initial, graph));
  return
     foldr(\ v::vertex rest::[vertex] ->
             graphToOrder(v, graph) ++ v::rest,
           [], edges_out);
}




--Read the theorem and interface files to get the proof information
--Returns a list of tuples of the grammar name, definitions, and proof
--   information or an error message
function gatherGrammarInfo
Either<String [(String, [DefinitionElement], [ParsedElement])]> ::=
      moduleNames::[String]
      --Silver's generated dir,  the root of the grammars
      generatedLoc::String grammarLoc::String
      ioin::IO
{
  local thisGrammar::String = head(moduleNames);
  local grammarDir::String = substitute(":", "/", thisGrammar);
  local interfaceFile::String =
        generatedLoc ++ "thm/" ++ grammarDir ++ "/thm_interface.svthmi";
  local interfaceIsFile::IOVal<Boolean> = isFile(interfaceFile, ioin);
  local theoremFile::String =
        generatedLoc ++ "thm/" ++ grammarDir ++ "/thm_outerface.svthmi";
  local theoremIsFile::IOVal<Boolean> =
        isFile(theoremFile, interfaceIsFile.io);

  local interfaceContents::IOVal<String> =
        readFile(interfaceFile, theoremIsFile.io);
  local parsedInterface::ParseResult<Interface_c> =
        interface_parser(interfaceContents.iovalue, interfaceFile);
  local theoremContents::IOVal<String> =
        readFile(theoremFile, interfaceContents.io);
  local parsedTheorem::ParseResult<Interface_c> =
        interface_parser(theoremContents.iovalue, theoremFile);

  local thisDefs::[DefinitionElement] =
        parsedInterface.parseTree.ast.3 ++
        map(\ d::DefinitionElement ->
              case d of
              | defineElement(rels, bodies) ->
                defineElement(
                   map(\ p::(String, AbellaType) ->
                         (buildEncodedName(thisGrammar, p.1), p.2),
                       rels),
                   --We need a way to expand all short names, based on where they came from
                   --For now, just replace the defined names
                   let replaces::[(String, Term)] =
                       map(\ p::(String, AbellaType) ->
                             ( p.1, nameTerm(
                                       buildEncodedName(thisGrammar,
                                                          p.1)) ),
                           rels)
                   in
                     map(\ p::(Metaterm, Maybe<Metaterm>) ->
                           ( replaceNames(replaces, p.1),
                             bind(p.2,
                                  \ m::Metaterm ->
                                    just(replaceNames(replaces, m))) ),
                         bodies)
                   end)
              end,
            parsedTheorem.parseTree.ast.3);
  local thisThms::Either<String [ParsedElement]> =
        combineGrammarThms(thisGrammar, theoremFile,
                           parsedInterface.parseTree.ast.4,
                           parsedTheorem.parseTree.ast.4);

  local subcall::Either<String [(String, [DefinitionElement], [ParsedElement])]> =
        gatherGrammarInfo(tail(moduleNames), generatedLoc,
                          grammarLoc, theoremContents.io);

  return
     case moduleNames of
     | [] -> right([])
     | _::_ ->
       if !interfaceIsFile.iovalue
       then left("Could not find interface file for grammar " ++
                 thisGrammar ++ "; compile grammar again(" ++
                 interfaceFile ++ ")")
       else if !theoremIsFile.iovalue
       then left("Could not find theorem file \"" ++ theoremFile ++
                 "\"; assuming no new theorems introduced by grammar " ++
                 thisGrammar)
       else if !parsedInterface.parseSuccess
       then left("Error parsing interface file for grammar " ++
                 thisGrammar ++ "; recompile grammar and DO NOT " ++
                 "edit this file")
       else if !parsedTheorem.parseSuccess
       then left("Error parsing theorem file for grammar " ++
                 thisGrammar ++ ":\n" ++ parsedTheorem.parseErrors)
       else case thisThms of
            | left(msg) -> left(msg)
            | right(thms) ->
              case subcall of
              | left(msg) -> left(msg)
              | right(rest) ->
                right( (thisGrammar, thisDefs, thms)::rest )
              end
            end
     end;
}


{-
  Given the orders from the interface and the theorem file, combine
  them into one order with the correct statements for each theorem
  name.

  Uses an Either because we want to check that all required theorems
  are included and give an error if they are not.
-}
function combineGrammarThms
Either<String [ParsedElement]> ::=
      currentGrammar::String thmFilename::String
      interface::[ParsedElement] thmFile::[ParsedElement]
{
  return
     case interface, thmFile of
     | [], rest -> right(rest)
     {-
       The only overlap between the two is in extensible theorems.  If
       one is not an extensible theorem, put that first, with a
       preference for the interface file.  If both are extensible, we
       need to determine if there is some overlap between them.  If
       there is overlap, join them, using the theorem statements from
       the interface for overlapping names.  If there is no overlap,
       put the theorem file one first, since those interface theorems
       must be included in the file later.
     -}
     | nonextensibleTheorem(name, stmt)::intRest, tf ->
       case combineGrammarThms(currentGrammar, thmFilename,
                               intRest, tf) of
       | left(msg) -> left(msg)
       | right(rest) ->
         right(nonextensibleTheorem(name, stmt)::rest)
       end
     | splitElement(name, names)::intRest, tf ->
       case combineGrammarThms(currentGrammar, thmFilename,
                               intRest, tf) of
       | left(msg) -> left(msg)
       | right(rest) ->
         right(splitElement(name, names)::rest)
       end
     | extensibleMutualTheoremGroup(interfaceThms, [])::intRest,
       nonextensibleTheorem(name, stmt)::tfRest ->
       case combineGrammarThms(currentGrammar, thmFilename,
                               interface, tfRest) of
       | left(msg) -> left(msg)
       | right(rest) ->
         right(nonextensibleTheorem(
                  buildEncodedName(currentGrammar, name),
                  stmt)::rest)
       end
     | extensibleMutualTheoremGroup(interfaceThms, [])::intRest,
       splitElement(name, names)::tfRest ->
       case combineGrammarThms(currentGrammar, thmFilename,
                               interface, tfRest) of
       | left(msg) -> left(msg)
       | right(rest) ->
         right(splitElement(name, names)::rest)
       end
     | extensibleMutualTheoremGroup(interfaceThms, [])::intRest,
       extensibleMutualTheoremGroup(thmThms, [])::tfRest ->
       case combineGrammarThms(currentGrammar, thmFilename,
                               interface, tfRest) of
       | left(msg) -> left(msg)
       | right(rest) ->
         right(extensibleMutualTheoremGroup(
                  map(\ p::(String, Metaterm, String) ->
                        ( buildEncodedName(currentGrammar, p.1),
                          p.2, p.3 ),
                      thmThms), [])::rest)
       end
     | extensibleMutualTheoremGroup(interfaceThms, [])::intRest,
       extensibleMutualTheoremGroup(thmThms, oldThms)::tfRest ->
       let interfaceNames::[String] = map(fst, interfaceThms)
       in
       let extraThms::[String] =
           filter(\ s::String -> !contains(s, interfaceNames),
                  oldThms)
       in
       let missingThms::[String] =
           filter(\ s::String -> !contains(s, oldThms),
                  interfaceNames)
       in
         if !null(extraThms) && !null(missingThms)
         then left("Theorem file " ++ thmFilename ++ " for " ++
                   currentGrammar ++ " contains a " ++
                   "mutual theorem group with extra theorems " ++
                   implode(",", extraThms) ++ " and missing " ++
                   "theorems " ++ implode(",", missingThms))
         else if !null(extraThms)
         then left("Theorem file " ++ thmFilename ++ " for " ++
                   currentGrammar ++ " contains a " ++
                   "mutual theorem group with extra theorems " ++
                   implode(",", extraThms))
         else if !null(missingThms)
         then left("Theorem file " ++ thmFilename ++ " for " ++
                   currentGrammar ++ " contains a " ++
                   "mutual theorem group with missing " ++
                   "theorems " ++ implode(",", missingThms))
         else case combineGrammarThms(currentGrammar, thmFilename,
                                      intRest, tfRest) of
              | left(msg) -> left(msg)
              | right(rest) ->
                right(extensibleMutualTheoremGroup(
                         interfaceThms ++ 
                         map(\ p::(String, Metaterm, String) ->
                               ( buildEncodedName(currentGrammar, p.1),
                                 p.2, p.3 ),
                             thmThms), [])::rest)
              end
       end end end
     | _, _ -> error("Impossible for any other case to arise")
     end;
}






{-
  Combine all the theorem work from all the imported grammars into a
  single, coherent order.

  This assumes that the given list of theorems is sorted by grammar
  name and contains no empty lists.
-}
function combineAllOrders
[ParsedElement] ::= orders::[ [ParsedElement] ]
{
  local fsts::[ParsedElement] = map(head, orders);
  local first::ParsedElement = findFirst(fsts, nothing());
  local newOrders::[ [ParsedElement] ] = cleanOrders(first, orders);
  return
     case orders of
     | [] -> []
     | _::_ -> first::combineAllOrders(newOrders)
     end;
}

--Find the first thing to put in the combined order based on all the
--   first theorems from all the orders
--It might be possible to make this more effecient by just pulling all
--   non-extensible things off the front of all
function findFirst
ParsedElement ::= fsts::[ParsedElement] thusFar::Maybe<ParsedElement>
{
  return
     case fsts, thusFar of
     | [], nothing() -> error("Impossible")
     | [], just(x) -> x
     --If both are extensible, check if they overlap in theorems
     --If not, go with the one coming in
     --If yes, combine them (underlying mutual group to which they may have added)
     | extensibleMutualTheoremGroup(athms, [])::rest,
       just(extensibleMutualTheoremGroup(bthms, [])) ->
       if null(intersectThms(athms, bthms))
       then findFirst(rest, thusFar)
       else findFirst(rest,
               just(extensibleMutualTheoremGroup(
                       unionThms(athms, bthms), [])))
     --Anything coming in other than extensible overrides extensible
     | extensibleMutualTheoremGroup(athms, [])::rest, just(_) ->
       findFirst(rest, thusFar)
     --We set it up so no old theorems can be in the set
     | extensibleMutualTheoremGroup(_, _::_)::rest, _ ->
       error("Impossible")
     --A non-extensible cannot override anything coming in
     | _::rest, just(_) -> findFirst(rest, thusFar)
     --Take whatever the first thing is
     | x::rest, nothing() -> findFirst(rest, just(x))
     end;
}


--Remove the chosen piece from the orders, ensuring no empty orders
--   are placed into the result
function cleanOrders
[ [ParsedElement] ] ::= chosen::ParsedElement orders::[ [ParsedElement] ]
{
  return
     case orders of
     | [] -> []
     | hd::tl ->
       case hd, chosen of
       | splitElement(aname, alst)::rest, splitElement(bname, blst)
         when aname == bname && alst == blst ->
         if null(rest)
         then cleanOrders(chosen, tl)
         else rest::cleanOrders(chosen, tl)
       | nonextensibleTheorem(aname, astmt)::rest,
         nonextensibleTheorem(bname, bstmt) when aname == bname ->
         if null(rest)
         then cleanOrders(chosen, tl)
         else rest::cleanOrders(chosen, tl)
       | extensibleMutualTheoremGroup(athms, _)::rest,
         extensibleMutualTheoremGroup(bthms, _)
         when !null(intersectThms(athms, bthms)) ->
         if null(rest)
         then cleanOrders(chosen, tl)
         else rest::cleanOrders(chosen, tl)
       | _, _ -> hd::cleanOrders(chosen, tl)
       end
     end;
}


function intersectThms
[(String, Metaterm, String)] ::= lst1::[(String, Metaterm, String)]
                                 lst2::[(String, Metaterm, String)]
{
  return
     intersectBy(\ p1::(String, Metaterm, String)
                   p2::(String, Metaterm, String) ->
                   p1.1 == p2.1,
                 lst1, lst2);
}

function unionThms
[(String, Metaterm, String)] ::= lst1::[(String, Metaterm, String)]
                                 lst2::[(String, Metaterm, String)]
{
  return
     unionBy(\ p1::(String, Metaterm, String)
               p2::(String, Metaterm, String) ->
               p1.1 == p2.1,
             lst1, lst2);
}

