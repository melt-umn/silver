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
top::RootSpec ::= _ _
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
top::RootSpec ::= g::Grammar oldInterface::Maybe<InterfaceItems>
                  grammarName::String grammarSource::String
                  grammarTime::Integer generateLocation::String
{
  local componentName::String = encodeName(grammarName);

  --Everything from the current grammar AND imported by it
  local relevantEnv::Decorated Env =
        appendEnv(g.globalImports, g.env);

  top.output =
      generateContents(g.nonterminals, g.attrs, g.attrOccurrences,
         g.inheritedAttrs, g.localAttrs, g.prods,
         g.synAttrEqInfo, g.inhAttrEqInfo, g.localAttrDefs,
         g.funRelClauses, encodeName(grammarName), relevantEnv,
         g.flowEnv);

  --left for error in producing it, right for file contents
  local interfaceFileBuild::Either<String String> =
        buildInterfaceFile(grammarName, top.compiledGrammars,
                           top.moduleNames, top.generateLocation,
                           top.grammarSource);

  top.interface_output = interfaceFileBuild.fromRight;

  top.shouldOutput =
      grammarName != "silver:core" &&
      interfaceFileBuild.isRight;
  top.error_output =
      if grammarName == "silver:core"
      then ""
      else "Error:  Could not generate for grammar " ++ grammarName ++
           ":  " ++ interfaceFileBuild.fromLeft ++ "\n\n";
}


--Build the full contents of the interface file
--left for an error from gathering imported things
--right for the file contents
function buildInterfaceFile
Either<String String> ::= grammarName::String
                          compiledGrammars::EnvTree<Decorated RootSpec>
                          moduleNames::[String]
                          generateLocation::String
                          grammarSource::String
{
  --Remove core until the issues with it are sorted out
  local no_core_module_names::[String] =
        remove("silver:core", moduleNames);
  --Figure out the import order for the imported grammars
  local dependencyGraph::graph:Graph<String> =
        buildDependencyGraph(graph:empty(), compiledGrammars,
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
        gatherGrammarInfo(no_core_module_names, generateLocation,
                          grammarSource, unsafeIO());
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
  return
     case grammarInformation of
     | left(x) -> left(x)
     | right(_) -> right(grammarName ++ ". " ++ order_string ++
                         definition_string ++ theorem_string)
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
      ioin::IOToken
{
  local thisGrammar::String = head(moduleNames);
  local grammarDir::String = substitute(":", "/", thisGrammar);
  local interfaceFile::String =
        generatedLoc ++ "thm/" ++ grammarDir ++ "/thm_interface.svthmi";
  local interfaceIsFile::IOVal<Boolean> = isFileT(interfaceFile, ioin);
  local theoremFile::String =
        generatedLoc ++ "thm/" ++ grammarDir ++ "/thm_outerface.svthmi";
  local theoremIsFile::IOVal<Boolean> =
        isFileT(theoremFile, interfaceIsFile.io);

  local interfaceContents::IOVal<String> =
        readFileT(interfaceFile, theoremIsFile.io);
  local parsedInterface::ParseResult<Interface_c> =
        interface_parser(interfaceContents.iovalue, interfaceFile);
  local theoremContents::IOVal<String> =
        readFileT(theoremFile, interfaceContents.io);
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

