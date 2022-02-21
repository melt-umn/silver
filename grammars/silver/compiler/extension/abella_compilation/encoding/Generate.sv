grammar silver:compiler:extension:abella_compilation:encoding;


import silver:util:treemap as tmap;

type AbellaDefs = silver:compiler:extension:abella_compilation:abella:Defs;


function generateNonterminalTypes
String ::= nonterminals::[String]
{
  return
     case nonterminals of
     | [] -> ""
     | nt::rest ->
       "Kind " ++ nameToNonterminal(nt) ++ "   type.\n" ++
       generateNonterminalTypes(rest)
     end;
}


function generateProductions
String ::= prods::[(String, AbellaType)]
{
  return
     case prods of
     | [] -> ""
     | (prod, ty)::rest ->
       "Type " ++ nameToProd(prod) ++ "   " ++ ty.unparse ++ ".\n" ++
       generateProductions(rest)
     end;
}


function generateNodeTypes
String ::= nonterminals::[String]
{
  return
     case nonterminals of
     | [] -> ""
     | nt::rest ->
       "Kind " ++ nameToNodeType(nt) ++ "   type.\n" ++
       generateNodeTypes(rest)
     end;
}


function generateNodeTreeConstructors
String ::= nonterminals::[String]
{
  return
     case nonterminals of
     | [] -> ""
     | nt::rest ->
       "Type " ++ nodeTreeConstructorName(nameToNonterminalType(nt)) ++
       "   " ++ nameToNodeType(nt) ++
       " -> list $node_tree -> $node_tree.\n" ++
       generateNodeTreeConstructors(rest)
     end;
}


function generateAccessRelations
String ::= attrOccurrences::[(String, [(String, AbellaType)])]
{
  return
     case attrOccurrences of
     | [] -> ""
     | (attr, ntstys)::rest ->
       foldr(\ p::(String, AbellaType) rest::String ->
               "Type " ++ accessRelationName(nameToNonterminalType(p.1), attr) ++
               "   " ++ nameToNonterminal(p.1) ++ " -> " ++
               nameToNodeType(p.1) ++ " -> " ++
               functorAbellaType(nameAbellaType(attrValTypeName), p.2).unparse ++
               " -> prop.\n" ++ rest,
             generateAccessRelations(rest), ntstys)
     end;
}


function generateLocalAccessRelations
String ::= localAttrs::[(String, [(String, AbellaType)])]
           env::Decorated Env
{
  return
     case localAttrs of
     | [] -> ""
     | (attr, [])::rest ->
       generateLocalAccessRelations(rest, env)
     | (attr, (prod, attrTy)::tl)::rest ->
       let prodTy::AbellaType = lookupProdType(prod, env)
       in
         "Type " ++ localAccessRelationName(prodTy.resultType, attr, prod) ++
         "   " ++ prodTy.resultType.unparse ++ " -> " ++
         typeToNodeType(prodTy.resultType) ++ " -> " ++
         functorAbellaType(nameAbellaType(attrValTypeName), attrTy).unparse ++
         " -> prop.\n" ++
         generateLocalAccessRelations((attr, tl)::rest, env)
       end
     end;
}


function generateForwardAccessRelations
String ::= new_nonterminals::[String] env::Decorated Env
{
  return
     case new_nonterminals of
     | [] -> ""
     | nt::rest ->
       "Type " ++ accessRelationName(nameToNonterminalType(nt), "forward") ++
       "   " ++ nameToNonterminal(nt) ++ " -> " ++
       typeToNodeType(nameToNonterminalType(nt)) ++ " -> " ++
       functorAbellaType(nameAbellaType(attrValTypeName),
          functorAbellaType(
             functorAbellaType(pairType,
                nameToNonterminalType(nt)),
             nodeTreeType)).unparse ++
       " -> prop.\n" ++
       generateForwardAccessRelations(rest, env)
     end;
}


function generateInheritedInformation
String ::= inheritedAttrs::[String]
{
  return
     case inheritedAttrs of
     | [] -> ""
     | attr::rest ->
       "Type $" ++ attr ++ "$_is_inherited   prop.\n" ++
       generateInheritedInformation(rest)
     end;
}


function generateStructureEqFull
String ::= nonterminals::[String]
{
  return
     case nonterminals of
     | [] -> ""
     | nt::rest ->
       let ntName::String = nameToNonterminal(nt) in
           "Type " ++ typeToStructureEqName(nameAbellaType(ntName)) ++
           "   " ++ ntName ++ " -> " ++ ntName ++ " -> prop.\n" ++
           generateStructureEqFull(rest)
       end
     end;
}


function generateStructureEqComponent
String ::= prods::[(String, AbellaType)] component::String
{
  --Sort before grouping to get all prods of type grouped together
  local sorted::[(String, AbellaType)] =
        sortBy(\ p1::(String, AbellaType) p2::(String, AbellaType) ->
                 p1.2.headTypeName <= p2.2.headTypeName,
               prods);
  local grouped::[[(String, AbellaType)]] =
        groupBy(\ p1::(String, AbellaType) p2::(String, AbellaType) ->
                  tysEqual(p1.2.resultType, p2.2.resultType), sorted);
  return foldr(\ g::[(String, AbellaType)] rest::String ->
                 generateStructureEqComponentGroup(g, component) ++
                 ".\n" ++ rest,
               "", grouped);
}
function generateStructureEqComponentGroup
String ::= group::[(String, AbellaType)] component::String
{
  local nt::AbellaType =
        case group of
        | [] -> nameAbellaType("Impossible if called after grouping (structure eq)")
        | (_, prodTy)::_ -> prodTy.resultType
        end;
  return
     "Define " ++ typeToStructureEqName(nt) ++ name_sep ++ component ++
     " : " ++ nt.unparse ++ " -> " ++ nt.unparse ++ " -> prop by\n" ++
     implode(";\n",
             map(\ p::(String, AbellaType) ->
                   generateStructureEqComponentBodies(p.1, p.2,
                      nt, component), group));
}
function generateStructureEqComponentBodies
String ::= prod::String prodTy::AbellaType nt::AbellaType component::String
{
  local children::[(String, String, AbellaType)] =
        foldr(\ t::AbellaType rest::([(String, String, AbellaType)], [String]) ->
                let n1::String =
                    makeUniqueNameFromTy(t, rest.2) in
                let n2::String =
                    makeUniqueNameFromTy(t, n1::rest.2) in
                  if tyIsNonterminal(t)
                  then ((n1, n2, t)::rest.1, n1::n2::rest.2)
                  else ((n1, n1, t)::rest.1, n1::rest.2)
                end end,
              ([], []), prodTy.argumentTypes).1;
  local clauseHead::String =
        typeToStructureEqName(nt) ++ name_sep ++ component ++ " " ++
        "(" ++ nameToProd(prod) ++ " " ++
            implode(" ", map(\ p::(String, String, AbellaType) ->
                               p.1, children)) ++ ") " ++
        "(" ++ nameToProd(prod) ++ " " ++
            implode(" ", map(\ p::(String, String, AbellaType) ->
                               p.2, children)) ++ ")";
  local clauseBody::String =
        foldr(\ p::(String, String, AbellaType) rest::String ->
                if tyIsNonterminal(p.3)
                then typeToStructureEqName(p.3) ++
                     " " ++ p.1 ++ " " ++ p.2 ++
                     --Only include an and if rest isn't empty
                     if rest == ""
                     then ""
                     else " /\\\n     " ++ rest
                else rest,
              "", children);
  return
     if clauseBody == ""
     then "  " ++ clauseHead
     else "  " ++ clauseHead ++ " :=\n     " ++ clauseBody;
}


function generateEquationsFull
String ::= attrOccurrences::[(String, [(String, AbellaType)])]
{
  return
     case attrOccurrences of
     | [] -> ""
     | (attr, ntstys)::rest ->
       foldr(\ nt::String innerRest::String ->
               "Type " ++ equationName(attr,
                                       nameToNonterminalType(nt)) ++
               "   " ++ nameToNonterminal(nt) ++ " -> " ++
               nameToNonterminal(nt) ++ " -> $node_tree -> prop.\n" ++
               innerRest,
             generateEquationsFull(rest), map(fst, ntstys))
     end;
}


function generateWpdRelationsFull
String ::= nonterminals::[String]
{
  return
     case nonterminals of
     | [] -> ""
     | nt::rest ->
       "Type " ++ wpdNodeTypeName(nameToNonterminalType(nt)) ++ "   " ++
          nameToNonterminal(nt) ++ " -> $node_tree -> prop.\n" ++
       "Type " ++ wpdTypeName(nameToNonterminalType(nt)) ++ "   " ++
          nameToNonterminal(nt) ++ " -> $node_tree -> prop.\n" ++
       generateWpdRelationsFull(rest)
     end;
}


function generateWpdNodeRelationsComponent
String ::= attrOccurrences::[(String, [(String, AbellaType)])]
           localAttrs::[(String, [(String, AbellaType)])]
           associatedAttrs::[(String, [String])]
           env::Decorated Env component::String
{
  --(tag, attr, attr type, nonterminal type on which it occurs, blank)
  local expanded::[(String, String, AbellaType, String, String)] =
        flatMap(\ p::(String, [(String, AbellaType)]) ->
                  map(\ x::(String, AbellaType) ->
                        ("attr", p.1, x.2, x.1, ""), p.2),
                attrOccurrences);
  --(tag, local attr, local type, nonterminal type, production)
  local locals::[(String, String, AbellaType, String, String)] =
        flatMap(\ p::(String, [(String, AbellaType)]) ->
                  map(\ x::(String, AbellaType) ->
                        case lookupProdType(x.1, env).resultType of
                        | nameAbellaType(prodTy) ->
                          ("local", p.1, x.2, nonterminalToName(prodTy), x.1)
                        | ty ->
                          error("Production must build nonterminal; got " ++ ty.unparse ++ " for production " ++ x.1)
                        end, p.2),
                localAttrs);
  local sorted::[(String, String, AbellaType, String, String)] =
        sortBy(\ p1::(String, String, AbellaType, String, String)
                 p2::(String, String, AbellaType, String, String) ->
                 p1.4 <= p2.4, expanded ++ locals);
  local grouped::[[(String, String, AbellaType, String, String)]] =
        groupBy(\ p1::(String, String, AbellaType, String, String)
                  p2::(String, String, AbellaType, String, String) ->
                  p1.4 == p2.4, sorted);
  --(nonterminal, [associated attrs])
  local associatedByGroups::[(String, [String])] =
        let expanded::[(String, String)] =
            flatMap(\ p::(String, [String]) ->
                      map(\ nt::String -> (nt, p.1), p.2),
                    associatedAttrs)
        in
        let sorted::[(String, String)] =
            sortBy(\ p1::(String, String) p2::(String, String) ->
                     p1.1 <= p2.2, expanded)
        in
        let grouped::[[(String, String)]] =
            groupBy(\ p1::(String, String) p2::(String, String) ->
                      p1.1 == p2.1, sorted)
        in
          map(\ l::[(String, String)] ->
                (head(l).1, map(snd, l)), grouped)
        end end end;
  return
     implode("",
        map(generateWpdNodeRelationsComponentGroup(
               _, associatedByGroups, component),
            grouped));
}
function generateWpdNodeRelationsComponentGroup
String ::= group::[(String, String, AbellaType, String, String)]
           associatedByGroups::[(String, [String])]
           component::String
{
  local nt::AbellaType = nameToNonterminalType(head(group).4);
  local bodyCall::(String, [String]) =
        generateWpdNodeRelationsComponentGroupBody(group);
  local theseAssociated::Maybe<[String]> =
        findAssociated(head(group).4, associatedByGroups);
  local associatedStr::String =
        case theseAssociated of
        | nothing() -> ""
        | just(lst) ->
          foldr(\ attr::String rest::String ->
                  equationName(attr, nt) ++ " Tree Tree (" ++
                  nodeTreeConstructorName(nt) ++ " Node CL)" ++
                  if rest == "" then ""
                                else " /\\\n         " ++ rest,
                "", lst)
        end;
  return
     "Define " ++ wpdNodeTypeName(nt) ++ name_sep ++ component ++ " : " ++
     nt.unparse ++ " -> $node_tree -> prop by\n" ++
     "   " ++ wpdNodeTypeName(nt) ++ name_sep ++ component ++ " Tree (" ++
              nodeTreeConstructorName(nt) ++ " Node CL) :=\n" ++
     "      exists " ++ implode(" ", bodyCall.2) ++ ",\n" ++
     bodyCall.1 ++
     ( if associatedStr == ""
       then ""
       else " /\\\n         " ++ associatedStr ) ++
     ".\n";
}
function generateWpdNodeRelationsComponentGroupBody
(String, [String]) ::= group::[(String, String, AbellaType, String, String)]
{
  local subcall::(String, [String]) =
        generateWpdNodeRelationsComponentGroupBody(tail(group));
  return
     case group of
     | [] -> ("", [])
     | (tag, attr, attrTy, nt, prod)::_ ->
       let aName::String = "A" ++ attr in
       let ntTy::AbellaType = nameToNonterminalType(nt) in
       let equation::String =
           case tag of
           | "attr" -> equationName(attr, ntTy)
           | "local" -> localEquationName(attr, prod)
           | _ -> error("Tag must be one of these")
           end in
       let access::String =
           case tag of
           | "attr" -> accessRelationName(ntTy, attr)
           | "local" -> localAccessRelationName(ntTy, attr, prod)
           | _ -> error("Tag must be one of these")
           end in
       let isRel::String = attrTy.isRelation in
         ( "         " ++ equation ++ " Tree Tree (" ++
                nodeTreeConstructorName(ntTy) ++ " Node CL) /\\\n" ++
           "            " ++ access ++ " Tree Node " ++ aName ++ " /\\\n" ++
           "            $is_attrVal (" ++ isRel ++ ") " ++ aName ++
           if subcall.1 == ""
           then ""
           else " /\\\n" ++ subcall.1,
           aName::subcall.2 )
       end end end end end
     end;
}


function generateWpdNtRelationsComponent
String ::= prods::[(String, AbellaType)] component::String
{
  --Sort before grouping to get all prods of type grouped together
  local sorted::[(String, AbellaType)] =
        sortBy(\ p1::(String, AbellaType) p2::(String, AbellaType) ->
                 p1.2.headTypeName <= p2.2.headTypeName,
               prods);
  local grouped::[[(String, AbellaType)]] =
        groupBy(\ p1::(String, AbellaType) p2::(String, AbellaType) ->
                  tysEqual(p1.2.resultType, p2.2.resultType), sorted);
  local ret::String =
     implode(".\n",
        map(\ g::[(String, AbellaType)] ->
              generateWpdNtRelationsComponentGroup(g, component),
            grouped));
  return
     if ret == ""
     then ret
     else ret ++ ".\n";
}
function generateWpdNtRelationsComponentGroup
String ::= group::[(String, AbellaType)] component::String
{
  local nt::AbellaType =
        case group of
        | [] -> error("Impossible if called after grouping (WPD NT components)")
        | (_, prodTy)::_ -> prodTy.resultType
        end;
  return
     "Define " ++ wpdTypeName(nt) ++ name_sep ++ component ++
     " : " ++ nt.unparse ++ " -> $node_tree -> prop by\n" ++
     implode(";\n",
         map(\ p::(String, AbellaType) ->
               generateWpdNtRelationsComponentBodies(p.1, p.2,
                  nt, component), group));
}
function generateWpdNtRelationsComponentBodies
String ::= prod::String prodTy::AbellaType nt::AbellaType component::String
{
  local children::[(String, AbellaType)] =
        foldr(\ t::AbellaType rest::([(String, AbellaType)], [String]) ->
                let n::String =
                    makeUniqueNameFromTy(t, rest.2) in
                  ((n, t)::rest.1, n::rest.2)
                end,
              ([], []), prodTy.argumentTypes).1;
  local clauseHead::String =
        wpdTypeName(nt) ++ name_sep ++ component ++ " " ++
        "(" ++ nameToProd(prod) ++ " " ++
            implode(" ", map(\ p::(String, AbellaType) ->
                               p.1, children)) ++ ") " ++
        "(" ++ nodeTreeConstructorName(nt) ++ " Node (" ++
            foldr(\ p::(String, AbellaType) rest::String ->
                    if tyIsNonterminal(p.2)
                    then p.1 ++ "Ntr::" ++ rest
                    else rest,
                  "nil)", children) ++ ")";
  local clauseBody::String =
        wpdNodeTypeName(nt) ++ " " ++
        "(" ++ nameToProd(prod) ++ " " ++
            implode(" ", map(\ p::(String, AbellaType) ->
                               p.1, children)) ++ ") " ++
        "(" ++ nodeTreeConstructorName(nt) ++ " Node (" ++
            foldr(\ p::(String, AbellaType) rest::String ->
                    if tyIsNonterminal(p.2)
                    then p.1 ++ "Ntr::" ++ rest
                    else rest,
                  "nil)", children) ++ ")" ++
        ( if null(children)
          then ""
          else " /\\\n     " ) ++
        foldr(\ p::(String, AbellaType) rest::String ->
                ( if tyIsNonterminal(p.2)
                  then wpdTypeName(p.2) ++ " " ++ p.1 ++ " " ++
                                                  p.1 ++ "Ntr"
                  else p.2.isRelation ++ " " ++ p.1) ++
                  --Only include an and if rest isn't empty
                  if rest == ""
                  then ""
                  else " /\\\n     " ++ rest,
              "", children);
  return
     if clauseBody == ""
     then "  " ++ clauseHead
     else "  " ++ clauseHead ++ " :=\n     " ++ clauseBody;
}


function generateAccessUniquenessAxioms
String ::= attrOccurrences::[(String, [(String, AbellaType)])]
           localAttrs::[(String, [(String, AbellaType)])]
           env::Decorated Env
{
  local attrs::[String] =
        flatMap(\ p::(String, [(String, AbellaType)]) ->
                  map(\ ntty::(String, AbellaType) ->
                        accessRelationName(nameToNonterminalType(ntty.1), p.1),
                      p.2), attrOccurrences);
  local locals::[String] =
        flatMap(\ p::(String, [(String, AbellaType)]) ->
                  map(\ pt::(String, AbellaType) ->
                        localAccessRelationName(
                           lookupProdType(pt.1, env).resultType,
                           p.1, pt.1),
                      p.2), localAttrs);
  return
     foldr(\ acc::String rest::String ->
             "Theorem " ++ acc ++ name_sep ++ "unique : forall Tree Node V V',\n" ++
             "   " ++ acc ++ " Tree Node V ->\n" ++
             "   " ++ acc ++ " Tree Node V' -> V = V'.\n" ++
             "skip.\n" ++
             rest,
           "", attrs ++ locals);
}


function generateAccessIsAxioms
String ::= attrOccurrences::[(String, [(String, AbellaType)])]
           localAttrs::[(String, [(String, AbellaType)])]
           env::Decorated Env
{
  --[(access relation, attr type, nonterminal)]
  local attrInfos::[(String, AbellaType, AbellaType)] =
        flatMap(\ p::(String, [(String, AbellaType)]) ->
                  map(\ ntty::(String, AbellaType) ->
                        (accessRelationName(
                            nameToNonterminalType(ntty.1), p.1),
                         ntty.2,
                         nameToNonterminalType(ntty.1)),
                      p.2), attrOccurrences);
  local locals::[(String, AbellaType, AbellaType)] =
        flatMap(\ p::(String, [(String, AbellaType)]) ->
                  map(\ pt::(String, AbellaType) ->
                        (localAccessRelationName(
                            lookupProdType(pt.1, env).resultType,
                            p.1, pt.1), pt.2,
                         lookupProdType(pt.1, env).resultType),
                      p.2), localAttrs);
  return
     foldr(\ p::(String, AbellaType, AbellaType) rest::String ->
             let isTree::Boolean =
                 case p.2 of
                 | functorAbellaType(
                      functorAbellaType(nameAbellaType("$pair"), nt),
                      node) when tyIsNonterminal(nt) -> true
                 | _ -> false
                 end in
             let treeTy::AbellaType =
                 case p.2 of
                 | functorAbellaType(
                      functorAbellaType(nameAbellaType("$pair"), nt),
                      node) when tyIsNonterminal(nt) -> nt
                 | _ -> error("Should not access this")
                 end
             in
               "Theorem " ++ p.1 ++ name_sep ++ "is : forall Tree Node CL "  ++
                  ( if isTree
                    then "VTr VNode"
                    else "V" ) ++ ",\n" ++
               "   " ++ wpdTypeName(p.3) ++ " Tree (" ++
                        nodeTreeConstructorName(p.3) ++ " Node CL) ->\n" ++
               "   " ++ p.1 ++ " Tree Node ($attr_ex " ++
                  ( if isTree
                    then "($pair_c VTr VNode)"
                    else "V" ) ++ ") ->\n" ++
               "   " ++
                  ( if isTree
                    then wpdTypeName(treeTy) ++ " VTr VNode"
                    else p.2.isRelation ++ " V" ) ++ ".\n" ++
               "skip.\n" ++
               rest
             end end,
           "", attrInfos ++ locals);
}


function generatePrimaryComponentTheorems
String ::=
     --All the equation relation clauses being generated in this grammar
     --We need a primary component theorem for each prod here
     --[(attr, top NT type, prod, head term (rel tree nodetree), [clause bodies])]
     attrEqInfo::[(String, AbellaType, String, Term, [[Metaterm]])]
     component::String
{
  --Drop the clause bodies, which we don't care about
  local droppedExtraInfo::[(String, AbellaType, String, Term)] =
        map(\ p::(String, AbellaType, String, Term, [[Metaterm]]) ->
              (p.1, p.2, p.3, p.4),
            attrEqInfo);
  --Remove any duplicates so we only get each PC theorem once
  --Since we have fully-qualified names, prod name uniquely determines
  --    NT and we don't need to check it
  local noDuplicatesAttrEqInfo::[(String, AbellaType, String, Term)] =
        nubBy(\ p1::(String, AbellaType, String, Term)
                p2::(String, AbellaType, String, Term) ->
                p1.1 == p2.1 && p1.3 == p2.3,
              droppedExtraInfo);
  return generatePrimaryComponentTheorems_help(noDuplicatesAttrEqInfo, component);
}
function generatePrimaryComponentTheorems_help
String ::=
     --All the equation relation clauses being generated in this grammar
     --We need a primary component theorem for each prod here
     --[(attr, top NT type, prod, head term (rel tree nodetree))]
     attrEqInfo::[(String, AbellaType, String, Term)]
     component::String
{
  local first::(String, AbellaType, String, Term) =
        head(attrEqInfo);
  local attr::String = first.1;
  local nt::AbellaType = first.2;
  local prod::String = nameToProd(first.3);
  local children::[String] =
        case first.4 of
        | applicationTerm(eq_comp_rel,
             consTermList(treename, consTermList(treeTm, _))) ->
          case treeTm of
          | applicationTerm(prod, children) ->
            map(\ t::Term ->
                  case t of
                  | nameTerm(name) -> name
                  | varTerm(base, _) -> base
                  | _ -> error("Children of prod must be names or vars here")
                  end,
                children.argList)
          | nameTerm(_) -> []
          | _ -> error("Tree must be tree-shaped")
          end
        | _ -> error("Must be an application with enough arguments")
        end;
  local here::String =
        "Theorem " ++ equationName(attr, nt) ++ name_sep ++
           prod ++ " : forall " ++ implode(" ", children) ++
           " Node TreeName T,\n   " ++
        typeToStructureEqName(nt) ++ " T (" ++
           prod ++ " " ++ implode(" ", children) ++") ->\n   " ++
        equationName(attr, nt) ++ " TreeName T Node ->\n   " ++
        equationName(attr, nt) ++ name_sep ++ component ++
           " TreeName (" ++ prod ++ " " ++ implode(" ", children) ++
           ") Node.\n" ++
        "skip.\n";
  return
     case attrEqInfo of
     | [] -> ""
     | _::tl -> here ++ generatePrimaryComponentTheorems_help(tl, component)
     end;
}
function generatePrimaryComponentTheoremBodies
String ::= attrGroups::[(String, [String])]
           prodGroups::[(String, [(String, AbellaType)])]
           component::String
{
  --(nonterminal, [attrs])
  local first::(String, [String]) = head(attrGroups);
  local nt::AbellaType = nameToNonterminalType(first.1);
  local attrs::[String] = first.2;
  local prods::[(String, AbellaType)] =
        case findAssociated(first.1, prodGroups) of
        | nothing() -> []
        | just(lst) -> lst
        end;
  --theorems for this nonterminal and its attrs
  local here::String =
        foldr(
           \ p::(String, AbellaType) rest::String ->
             let children::[String] =
                 foldr(\ ty::AbellaType rest::[String] ->
                         makeUniqueNameFromTy(ty,
                            "Node"::"TreeName"::"T"::rest)::rest,
                       [], p.2.argumentTypes)
             in
               foldr(\ a::String rest::String ->
                       "Theorem " ++ equationName(a, nt) ++ name_sep ++
                          nameToProd(p.1) ++ " : forall " ++
                          implode(" ", children) ++
                          " Node TreeName T,\n   " ++
                       typeToStructureEqName(nt) ++ " T (" ++
                          nameToProd(p.1) ++ " " ++
                          implode(" ", children) ++ ") ->\n   " ++
                       equationName(a, nt) ++ " TreeName T Node ->" ++
                       "\n   " ++
                       equationName(a, nt) ++ name_sep ++ component ++
                          " TreeName (" ++ nameToProd(p.1) ++ " " ++
                          implode(" ", children) ++ ") Node.\n" ++
                       "skip.\n" ++ rest,
                     rest, attrs)
             end,
           "", prods);
  return
     case attrGroups of
     | [] -> ""
     | _::tl ->
       here ++ generatePrimaryComponentTheoremBodies(tl, prodGroups,
                                                     component)
     end;
}


function generateInhAttrChildEqTheorems
String ::= --[(attr, index (e.g. "child3"), top NT, prod, head term,
           --  clause bodies, not-this-prod)]
           inhAttrEqs::[(String, String, AbellaType, String, Term,
                         [[Metaterm]], DefClause)]
{
  local first::(String, String, AbellaType, String, Term,
                [[Metaterm]], DefClause) =
        head(inhAttrEqs);
  local attr::String = first.1;
  local index::String = first.2;
  local treeTy::AbellaType = first.3;
  local prod::String = first.4;

  local eqName::String = equationName(attr, treeTy);
  local childEqName::String =
        inhChildEquationName(attr, treeTy, prod, index);
  local here::String =
        "Theorem " ++ eqName ++ name_sep ++ "to" ++ name_sep ++
                      nameToProd(prod) ++ name_sep ++ index ++ " : " ++
           "forall TreeName Term NodeTree,\n" ++
        "   " ++ eqName ++ " TreeName Term NodeTree ->" ++ "\n" ++
        "   " ++ childEqName ++ " TreeName Term NodeTree." ++ "\n" ++
        "skip.\n";

  local rest::String =
        generateInhAttrChildEqTheorems(tail(inhAttrEqs));

  return case inhAttrEqs of
         | [] -> ""
         | _ -> here ++ rest
         end;
}


function generateWPDPrimaryComponentTheorems
String ::= prods::[(String, AbellaType)] component::String
{
  return
     case prods of
     | [] -> ""
     | (pr, ty)::rest ->
       let nt::AbellaType = ty.resultType
       in
       let children::[String] =
           foldr(\ ty::AbellaType rest::[String] ->
                   makeUniqueNameFromTy(ty,
                      "T"::"NodeTree"::rest)::rest,
                 [], ty.argumentTypes)
       in
         "Theorem " ++ wpdTypeName(nt) ++ name_sep ++ nameToProd(pr) ++
            " : forall T " ++ implode(" ", children) ++ " NodeTree," ++
            "\n   " ++
         typeToStructureEqName(nt) ++ " T (" ++ nameToProd(pr) ++
            " " ++ implode(" ", children) ++ ") ->\n   " ++
         wpdTypeName(nt) ++ " T NodeTree ->\n   " ++
         wpdTypeName(nt) ++ name_sep ++ component ++ " (" ++
            nameToProd(pr) ++ " " ++ implode(" ", children) ++
            ") NodeTree.\n" ++ "skip.\n" ++
         generateWPDPrimaryComponentTheorems(rest, component)
       end end
     end;
}


function generateNodeTreeFormTheorems
String ::= nonterminals::[String]
{
  return
     case nonterminals of
     | [] -> ""
     | nt::rest ->
       "Theorem " ++ wpdTypeName(nameToNonterminalType(nt)) ++
          name_sep ++ "ntr_" ++
          nameToNonterminal(nt) ++ " : forall Tree NodeTree,\n   " ++
       wpdTypeName(nameToNonterminalType(nt)) ++
          " Tree NodeTree ->\n   " ++
       "exists Node ChildList, NodeTree = " ++
          nodeTreeConstructorName(nameToNonterminalType(nt)) ++ 
          " Node ChildList.\n" ++
       "skip.\n" ++
       generateNodeTreeFormTheorems(rest)
     end;
}


function generateWpdToAttrEquationTheorems
String ::= attrOccurrences::[(String, [(String, AbellaType)])]
           localAttrs::[(String, [(String, AbellaType)])]
           env::Decorated Env
{
  --[(equation relation, attr, attr type, nonterminal)]
  local attrInfos::[(String, String, AbellaType, AbellaType)] =
        flatMap(\ p::(String, [(String, AbellaType)]) ->
                  map(\ ntty::(String, AbellaType) ->
                        (equationName(p.1,
                            nameToNonterminalType(ntty.1)),
                         p.1,
                         ntty.2,
                         nameToNonterminalType(ntty.1)),
                      p.2), attrOccurrences);
  --[(equation relation, prod, attr, attr type, nonterminal)]
  local locals::[(String, String, String, AbellaType, AbellaType)] =
        flatMap(\ p::(String, [(String, AbellaType)]) ->
                  map(\ pt::(String, AbellaType) ->
                        (localEquationName(p.1, pt.1), pt.1, p.1, pt.2,
                         lookupProdType(pt.1, env).resultType),
                      p.2), localAttrs);
  return
     --attrs
     foldr(\ p::(String, String, AbellaType, AbellaType) rest::String ->
             "Theorem $wpd" ++ name_sep ++ "to" ++ name_sep ++ p.2 ++
                name_sep ++ p.4.unparse ++
                " : forall Tree NodeTree,\n   " ++
             wpdTypeName(p.4) ++ " Tree NodeTree ->\n   " ++
             p.1 ++ " Tree Tree NodeTree.\n" ++
             "skip.\n" ++
             rest,
           "", attrInfos) ++
     --locals
     foldr(\ p::(String, String, String, AbellaType, AbellaType) rest::String ->
             "Theorem $wpd" ++ name_sep ++ "to" ++ name_sep ++ p.2 ++
                "_local_" ++ p.3 ++
                name_sep ++ p.5.unparse ++ " : forall Tree Tree' NodeTree," ++
                "\n   " ++
             typeToStructureEqName(p.5) ++ " Tree Tree' ->\n   " ++
             wpdTypeName(p.5) ++ " Tree NodeTree ->\n   " ++
             p.1 ++ " Tree Tree' NodeTree.\n" ++
             "skip.\n" ++
             rest,
           "", locals);
}


function generateStructureEqNtTheorems
String ::= nonterminals::[String] components::[String]
{
  return
     case nonterminals of
     | [] -> ""
     | nt::rest ->
       let ntTy::AbellaType = nameToNonterminalType(nt) in
         "Theorem " ++ typeToStructureEqName(ntTy) ++ name_sep ++ "equal" ++
            " : forall T1 T2,\n   " ++
         typeToStructureEqName(ntTy) ++ " T1 T2 -> T1 = T2.\n" ++
         "skip.\n" ++
         "Theorem " ++ typeToStructureEqName(ntTy) ++ name_sep ++ "symm" ++
            " : forall T1 T2,\n   " ++
         typeToStructureEqName(ntTy) ++ " T1 T2 ->\n   " ++
         typeToStructureEqName(ntTy) ++ " T2 T1.\n" ++
         "skip.\n" ++
         "Theorem " ++ typeToStructureEqName(ntTy) ++ name_sep ++ "wpd" ++
            " : forall T NTr,\n   " ++
         wpdTypeName(ntTy) ++ " T NTr -> " ++
            typeToStructureEqName(ntTy) ++ " T T.\n" ++
         "skip.\n" ++
         foldr(\ c::String rest::String ->
                 "Theorem " ++ structureEqExpansionTheorem(ntTy, c) ++
                    " : forall T1 T2,\n   " ++
                 typeToStructureEqName(ntTy) ++ name_sep ++ c ++
                    " T1 T2 ->\n   " ++
                 typeToStructureEqName(ntTy) ++ " T1 T2.\n" ++
                 "skip.\n" ++
                 rest,
               "", components) ++
         generateStructureEqNtTheorems(rest, components)
       end
     end;
}


function generateStructureEqPrimaryComponentTheorems
String ::= prods::[(String, AbellaType)] component::String
{
  return
     case prods of
     | [] -> ""
     | (prod, ty)::rest ->
       let nt::AbellaType = ty.resultType
       in
       let children::[String] =
           foldr(\ ty::AbellaType rest::[String] ->
                   makeUniqueNameFromTy(ty, "T"::rest)::rest,
                 [], ty.argumentTypes)
       in
         "Theorem $structure_eq" ++ name_sep ++
            nameToProd(prod) ++ " : forall T " ++
            implode(" ", children) ++ ",\n   " ++
         typeToStructureEqName(nt) ++ " T (" ++ nameToProd(prod) ++
            " " ++ implode(" ", children) ++ ") -> \n   " ++
         typeToStructureEqName(nt) ++ name_sep ++ component ++
            " T (" ++ nameToProd(prod) ++ " " ++
            implode(" ", children) ++ ").\n" ++
         "skip.\n" ++
         generateStructureEqPrimaryComponentTheorems(rest, component)
       end end
     end;
}


function generateSynAttrEquationComponentRelations
String ::= clauses::[(String, AbellaType, [DefClause])]
           component::String
{
  --grouped by attr and nonterminal
  local attrGroups::[(String, AbellaType, [DefClause])] =
        --Sort first to get all clauses for attr and NT together
        let sorted::[(String, AbellaType, [DefClause])] =
            sortBy(\ p1::(String, AbellaType, [DefClause])
                     p2::(String, AbellaType, [DefClause]) ->
                     case p1.2, p2.2 of
                     | nameAbellaType(x), nameAbellaType(y) ->
                       p1.1 < p2.1 || (p1.1 == p2.1 && x <= y)
                     | _, _ -> error("Should only have name types")
                     end,
                   clauses)
        in
        --Group by attr and nonterminal
        let grouped::[[(String, AbellaType, [DefClause])]] =
            groupBy(\ p1::(String, AbellaType, [DefClause])
                      p2::(String, AbellaType, [DefClause]) ->
                      p1.1 == p2.1 && tysEqual(p1.2, p2.2),
                    sorted)
        in
          map(\ l::[(String, AbellaType, [DefClause])] ->
                ( head(l).1, head(l).2,
                  foldr(\ p::(String, AbellaType, [DefClause])
                          rest::[DefClause] ->
                          p.3 ++ rest,
                        [], l) ),
              grouped)
        end end;
  return generateSynAttrEquationComponentRelations_help(attrGroups,
            component);
}
function generateSynAttrEquationComponentRelations_help
String ::= attrs::[(String, AbellaType, [DefClause])]
           component::String
{
  local here::(String, AbellaType, [DefClause]) = head(attrs);
  local body::silver:compiler:extension:abella_compilation:abella:Defs =
        if null(here.3)
        then singleAbellaDefs(factClause(trueMetaterm()))
        else
           foldr(consAbellaDefs(_, _), singleAbellaDefs(last(here.3)),
                 take(length(here.3) - 1, here.3));
  local equationRelation::String =
        equationName(here.1, here.2) ++ name_sep ++ component;
  local relType::AbellaType = equationRelType(here.2);
  local defn::Definition =
        definition([(equationRelation, new(relType))], body);
  return
     case attrs of
     | [] -> ""
     | hd::tl ->
       defn.unparse ++ "\n" ++
       generateSynAttrEquationComponentRelations_help(tl, component)
     end;
}



function completeInhEqs
--[(attr, index (e.g. "child3"), top NT, prod,
--  head term (rel tree nodetree), [clause bodies],
--  not-this-prod defining clause)]
[(String, String, AbellaType, String, Term, [[Metaterm]], DefClause)] ::=
   inhAttrEqInfo::[(String, String, AbellaType,
                    String, Term, [[Metaterm]], DefClause)]
   --[(prod name, prod type)]
   new_prods::[(String, AbellaType)]
   new_nonterminals::[String] new_inhAttrs::[String]
   --[(local name, [(production name, attr type)])]
   new_localAttrs::[(String, [(String, AbellaType)])]
   env::Decorated Env
{
  --[(NT, [(prod name, prod type)])]
  local allProds::[(String, [(String, AbellaType)])] =
        getProdsByType(env);
  --All known inherited attrs
  local inhAttrs::[String] =
        nub(map(colonsToEncoded, findAllInhAttrs(env)));
  --[(attr, [NT])]
  local allNewAssoc::[(String, [String])] =
        findAllPossibleNewAssociatedAttrs(new_nonterminals,
           new_inhAttrs, env);
  --[(attr, NT)]
  local expandedNewAssoc::[(String, String)] =
        flatMap(\ p::(String, [String]) -> map(pair(p.1, _), p.2),
                allNewAssoc);

  {-
    Find missing eqs for children
  -}
  --all equations which need to be defined for new associated attrs
  --[(attr, NT, prod, prodTy, numeric child index)]
  local expectedNewAssocEqs::[(String, AbellaType, String, AbellaType,
                               Integer)] =
        flatMap(--(inh attr, NT)
           \ p::(String, String) ->
             let prods::[(String, AbellaType)] =
                 case lookup(nameToNonterminal(p.2), allProds) of
                 | just(p) -> p
                 | nothing() -> []
                 end
             in
               flatMap(
                  \ prod::(String, AbellaType) ->
                    let iChildren::[(Integer, AbellaType)] =
                        zipWith(pair,
                           range(0, length(prod.2.argumentTypes)),
                           prod.2.argumentTypes)
                    in
                      map(\ ic::(Integer, AbellaType) ->
                            (p.1, nameToNonterminalType(p.2), prod.1,
                             prod.2, ic.1),
                          --only want NT children
                          filter(\ ic::(Integer, AbellaType) ->
                                   tyIsNonterminal(ic.2),
                                 iChildren))
                    end,
                  prods)
             end,
           expandedNewAssoc);
  --all equations which need to be defined for new prods
  --[(attr, NT, prod, prodTy, child index)]
  local expectedNewProdsEqs::[(String, AbellaType, String, AbellaType,
                               Integer)] =
        flatMap(
           \ prod::(String, AbellaType) ->
             let iChildren::[(Integer, AbellaType)] =
                 zipWith(pair,
                    range(0, length(prod.2.argumentTypes)),
                    prod.2.argumentTypes)
             in
               flatMap(\ ia::String ->
                         map(\ ic::(Integer, AbellaType) ->
                               (ia, prod.2.resultType, prod.1,
                                prod.2, ic.1),
                             --only want NT children
                             filter(\ ic::(Integer, AbellaType) ->
                                      tyIsNonterminal(ic.2),
                                    iChildren)),
                       inhAttrs)
             end,
           new_prods);
  --Because any known inh attrs cannot become occurring in the future,
  --   only want to set inhs on child when they already occur on NT
  local filteredChildOccurs::[(String, AbellaType, String, AbellaType, Integer)] =
        filter(\ p::(String, AbellaType, String, AbellaType, Integer) ->
                 !null(getOccursDcl(encodedToColons(p.1),
                          encodedToColons(p.4.resultType.unparse), env)),
               expectedNewAssocEqs ++ expectedNewProdsEqs);

  local missingChild::[(String, String, AbellaType,
                        String, Term, [[Metaterm]], DefClause)] =
        buildMissingChildEqInfo(
           --Remove duplicates so each rel is only made once
           nubBy(\ p1::(String, AbellaType, String, AbellaType, Integer)
                   p2::(String, AbellaType, String, AbellaType, Integer) ->
                   p1.1 == p2.1 && --top ty determined by prod
                   p1.3 == p2.3 && --prod ty determined by prod
                   p1.5 == p2.5,
                 filteredChildOccurs),
           inhAttrEqInfo);

  {-
    Find missing eqs for forward
  -}
  local fwdingProdNames::[String] = getForwardingProds(env);
  --
  local allFwdingProds::[(String, AbellaType)] =
        filter(\ p::(String, AbellaType) ->
                 contains(nameToProd(p.1), fwdingProdNames),
               flatMap(snd, allProds));
  --split forwarding prods into new in this grammar vs. imported
  local fwdingProdsSplit::([(String, AbellaType)],
                           [(String, AbellaType)]) =
        partition(\ p::(String, AbellaType) ->
                 contains(p.1, map(fst, new_prods)),
               allFwdingProds);
  --All inh attrs on new forwarding prods
  --[(attr, NT, prod, prodTy)]
  local expectedForwardNewProds::[(String, AbellaType, String, AbellaType)] =
        flatMap(\ prod::(String, AbellaType) ->
                  map(\ ia::String ->
                        (ia, prod.2.resultType, prod.1, prod.2),
                      inhAttrs),
                fwdingProdsSplit.1);
  --All new inh attrs on old forwarding prods
  local expectedForwardOldProds::[(String, AbellaType, String, AbellaType)] =
        flatMap(\ prod::(String, AbellaType) ->
                  map(\ ia::String ->
                        (ia, prod.2.resultType, prod.1, prod.2),
                      new_inhAttrs),
                fwdingProdsSplit.2);
  --Because any known inh attrs cannot become occurring in the future,
  --   only want to set inhs on forward when they already occur on NT
  local filteredFwdOccurs::[(String, AbellaType, String, AbellaType)] =
        filter(\ p::(String, AbellaType, String, AbellaType) ->
                 !null(getOccursDcl(encodedToColons(p.1),
                          encodedToColons(p.2.unparse), env)),
               expectedForwardNewProds ++ expectedForwardOldProds);

  local missingFwds::[(String, String, AbellaType,
                       String, Term, [[Metaterm]], DefClause)] =
        buildMissingFwdChildEqInfo(filteredFwdOccurs, inhAttrEqInfo);

  {-
    Find missing eqs for locals
  -}
  --We aren't guaranteed to find all the locals which exist in imported
  --   grammars, so we're only going to guarantee the known attributes
  --   on the locals in the current grammar, and nothing new being
  --   added to them.  Since a local can only be used in the current
  --   grammar, this shouldn't be a problem.

  --[(inh attr, local attr, local type, prod, prod type)]
  local expectedLocalAttrs::[(String, String, AbellaType,
                              String, AbellaType)] =
        flatMap(
           \ p::(String, [(String, AbellaType)]) ->
             flatMap(
                \ prod_localty::(String, AbellaType) ->
                  map(\ ia::String ->
                        (ia, p.1,
                         case prod_localty.2 of
                         | functorAbellaType(
                              functorAbellaType(
                                 nameAbellaType("$pair"), treeTy),
                               _) -> treeTy
                         | x -> x
                         end,
                         prod_localty.1,
                         lookup(prod_localty.1,
                            flatMap(snd, allProds)).fromJust),
                      inhAttrs),
                p.2),
           new_localAttrs);
  local filteredLocalOccurs::[(String, String, AbellaType,
                               String, AbellaType)] =
        filter(\ p::(String, String, AbellaType, String, AbellaType) ->
                 !null(getOccursDcl(encodedToColons(p.1),
                          encodedToColons(nonterminalToName(p.3.unparse)), env)),
               expectedLocalAttrs);
  local missingLocals::[(String, String, AbellaType,
                         String, Term, [[Metaterm]], DefClause)] =
        buildMissingLocalChildEqInfo(filteredLocalOccurs,
           inhAttrEqInfo);

  {-
    Final Result:  Includes original entries
  -}
  return inhAttrEqInfo ++ missingChild ++ missingFwds ++ missingLocals;
}

--Given information about the equations expected to be defined and those
--   that actually were, produce any missing definitions:  For prod children
--Missing defs just get no value for attr
function buildMissingChildEqInfo
--[(attr, index, top NT, prod, head term, [clause bodies],
--  not-this-prod clause)]
[(String, String, AbellaType, String, Term, [[Metaterm]], DefClause)] ::=
   --equations which were expected
   --[(attr, NT, prod, prod ty, child index)]
   expected::[(String, AbellaType, String, AbellaType, Integer)]
   --equations which were given
   defined::[(String, String, AbellaType, String, Term,
              [[Metaterm]], DefClause)]
{
  local first::(String, AbellaType, String, AbellaType, Integer) =
        head(expected);
  local stringIndex::String = "child" ++ toString(first.5);
  local found::Boolean =
        any(map(\ d::(String, String, AbellaType, String, Term,
                      [[Metaterm]], DefClause) ->
                  d.1 == first.1 && --same attr
                  d.2 == stringIndex && --same index
                  d.4 == first.3,   --same prod
                defined));

  local rel::String =
        inhChildEquationName(first.1, first.2, first.3, stringIndex);
  local iChildren::[(Integer, AbellaType)] =
        zipWith(pair, range(0, length(first.4.argumentTypes)),
                      first.4.argumentTypes);
  --We can hardcode the names here rather than use varTerm because the
  --   way we are generating them is guaranteed to be unique
  local childNames::[String] =
        map(\ p::(Integer, AbellaType) ->
              "C" ++ toString(p.1), iChildren);
  local prodBuilt::Term =
        buildApplication(nameTerm(nameToProd(first.3)),
           map(nameTerm, childNames));
  local nodetree::Term =
        buildApplication(nameTerm(nodeTreeConstructorName(first.2)),
           [nameTerm("Node"),
            foldr(\ p::(Integer, AbellaType) rest::Term ->
                    if tyIsNonterminal(p.2)
                    then consTerm(
                            buildApplication(
                               nameTerm(nodeTreeConstructorName(p.2)),
                               [nameTerm("C" ++ toString(p.1) ++ "Node"),
                                nameTerm("C" ++ toString(p.1) ++ "CL")]),
                            rest)
                    else rest,
                  nilTerm(), iChildren)]);
  local accessRel::String =
        accessRelationName(lookup(first.5, iChildren).fromJust, first.1);
  local childNode::String = "C" ++ toString(first.5) ++ "Node";
  --Building the equation information for the current missing equation
  local here::(String, String, AbellaType, String, Term,
               [[Metaterm]], DefClause) =
        ( first.1, stringIndex, first.2, first.3,
          --head term
          buildApplication(nameTerm(rel),
             [nameTerm("TreeName"), prodBuilt, nodetree]),
          --body for attr is empty on this child
          [[termMetaterm(
               buildApplication(nameTerm(accessRel),
                  [nameTerm("TreeName"), nameTerm(childNode),
                   nameTerm(attributeNotExistsName)]))]],
          --other production def clause
          ruleClause(
             termMetaterm(
                buildApplication(nameTerm(rel),
                   [nameTerm("TreeName"), nameTerm("Term"),
                    nameTerm("NodeTree")])),
             impliesMetaterm(
                bindingMetaterm(existsBinder(),
                   map(pair(_, nothing()), childNames),
                   eqMetaterm(nameTerm("Term"), prodBuilt)),
                falseMetaterm())) );

  local rest::[(String, String, AbellaType, String, Term,
                [[Metaterm]], DefClause)] =
        buildMissingChildEqInfo(tail(expected), defined);

  return case expected of
         | [] -> []
         | _ -> if found then rest else here::rest
         end;
}

--Given information about the equations expected to be defined and those
--   that actually were, produce any missing definitions:  For forwards
function buildMissingFwdChildEqInfo
--[(attr, index, top NT, prod, head term, [clause bodies],
--  not-this-prod clause)]
[(String, String, AbellaType, String, Term, [[Metaterm]], DefClause)] ::=
   --equations which were expected
   --[(attr, NT, prod, prod ty)]
   expected::[(String, AbellaType, String, AbellaType)]
   --equations which were given
   defined::[(String, String, AbellaType, String, Term,
              [[Metaterm]], DefClause)]
{
  local first::(String, AbellaType, String, AbellaType) =
        head(expected);
  local found::Boolean =
        any(map(\ d::(String, String, AbellaType, String, Term,
                      [[Metaterm]], DefClause) ->
                  d.1 == first.1 && --same attr
                  d.2 == "forward" && --same index
                  d.4 == first.3, --same prod
                defined));

  local rel::String =
        inhChildEquationName(first.1, first.2, first.3, "forward");
  local iChildren::[(Integer, AbellaType)] =
        zipWith(pair, range(0, length(first.4.argumentTypes)),
                      first.4.argumentTypes);
  local childNames::[String] =
        map(\ p::(Integer, AbellaType) ->
              "C" ++ toString(p.1), iChildren);
  local prodBuilt::Term =
        buildApplication(nameTerm(nameToProd(first.3)),
           map(nameTerm, childNames));
  local nodetree::Term =
        buildApplication(nameTerm(nodeTreeConstructorName(first.2)),
           [nameTerm("Node"),
            foldr(\ p::(Integer, AbellaType) rest::Term ->
                    if tyIsNonterminal(p.2)
                    then consTerm(
                            buildApplication(
                               nameTerm(nodeTreeConstructorName(p.2)),
                               [nameTerm("C" ++ toString(p.1) ++ "Node"),
                                nameTerm("C" ++ toString(p.1) ++ "CL")]),
                            rest)
                    else rest,
                  nilTerm(), iChildren)]);
  local fwdAccessRel::String = accessRelationName(first.2, "forward");
  local accessRel::String = accessRelationName(first.2, first.1);
  --Make all these varTerms so they get put into exists binding for us
  local fwdName::Term = varTerm("Fwd", genInt());
  local fwdNode::Term = varTerm("FwdNode", genInt());
  local fwdCL::Term = varTerm("FwdCL", genInt());
  local attrVal::Term =
        varTerm(capitalize(nameToShortName(first.1)), genInt());
  --
  local here::(String, String, AbellaType, String, Term,
               [[Metaterm]], DefClause) =
        ( first.1, "forward", first.2, first.3,
          --head term
          buildApplication(nameTerm(rel),
             [nameTerm("TreeName"), prodBuilt, nodetree]),
          --bodies for definition
          [ --no forward
            [termMetaterm(
                buildApplication(nameTerm(fwdAccessRel),
                   [nameTerm("TreeName"), nameTerm("Node"),
                    nameTerm(attributeNotExistsName)]))],
            --forward, copying no attr value from top
            [termMetaterm(
                buildApplication(nameTerm(fwdAccessRel),
                   [nameTerm("TreeName"), nameTerm("Node"),
                    buildApplication(nameTerm(attributeExistsName),
                       [buildApplication(nameTerm(pairConstructorName),
                           [fwdName,
                            buildApplication(
                               nameTerm(nodeTreeConstructorName(first.2)),
                               [fwdNode, fwdCL])])])])),
             termMetaterm(
                buildApplication(nameTerm(accessRel),
                   [nameTerm("TreeName"), nameTerm("Node"),
                    nameTerm(attributeNotExistsName)])),
             termMetaterm(
                buildApplication(nameTerm(accessRel),
                   [fwdName, fwdNode,
                    nameTerm(attributeNotExistsName)]))],
            --forward, copying attr value from top
            [termMetaterm(
                buildApplication(nameTerm(fwdAccessRel),
                   [nameTerm("TreeName"), nameTerm("Node"),
                    buildApplication(nameTerm(attributeExistsName),
                       [buildApplication(nameTerm(pairConstructorName),
                           [fwdName,
                            buildApplication(
                               nameTerm(nodeTreeConstructorName(first.2)),
                               [fwdNode, fwdCL])])])])),
             termMetaterm(
                buildApplication(nameTerm(accessRel),
                   [nameTerm("TreeName"), nameTerm("Node"),
                    buildApplication(nameTerm(attributeExistsName),
                       [attrVal])])),
             termMetaterm(
                buildApplication(nameTerm(accessRel),
                   [fwdName, fwdNode,
                    buildApplication(nameTerm(attributeExistsName),
                       [attrVal])]))]
          ],
          --other production def clause
          ruleClause(
             termMetaterm(
                buildApplication(nameTerm(rel),
                   [nameTerm("TreeName"), nameTerm("Term"),
                    nameTerm("NodeTree")])),
             impliesMetaterm(
                bindingMetaterm(existsBinder(),
                   map(pair(_, nothing()), childNames),
                   eqMetaterm(nameTerm("Term"), prodBuilt)),
                falseMetaterm())) );

  local rest::[(String, String, AbellaType, String, Term,
                [[Metaterm]], DefClause)] =
        buildMissingFwdChildEqInfo(tail(expected), defined);

  return case expected of
         | [] -> []
         | _ -> if found then rest else here::rest
         end;
}

--Given information about the equations expected to be defined and those
--   that actually were, produce any missing definitions:  For locals
function buildMissingLocalChildEqInfo
--[(attr, index, top NT, prod, head term, [clause bodies],
--  not-this-prod clause)]
[(String, String, AbellaType, String, Term, [[Metaterm]], DefClause)] ::=
   --equations which were expected
   --[(inh attr, local attr, local type, prod, prod type)]
   expected::[(String, String, AbellaType, String, AbellaType)]
   --equations which were given
   defined::[(String, String, AbellaType, String, Term,
              [[Metaterm]], DefClause)]
{
  local first::(String, String, AbellaType, String, AbellaType) =
        head(expected);
  local found::Boolean =
        any(map(\ d::(String, String, AbellaType, String, Term,
                      [[Metaterm]], DefClause) ->
                  d.1 == first.1 && --same attr
                  d.2 == first.2 && --same index
                  d.4 == first.4, --same prod
                defined));

  local rel::String =
        inhChildEquationName(first.1, first.5.resultType, first.4,
                             first.2);
  local iChildren::[(Integer, AbellaType)] =
        zipWith(pair, range(0, length(first.5.argumentTypes)),
                      first.5.argumentTypes);
  local childNames::[String] =
        map(\ p::(Integer, AbellaType) ->
              "C" ++ toString(p.1), iChildren);
  local prodBuilt::Term =
        buildApplication(nameTerm(nameToProd(first.4)),
           map(nameTerm, childNames));
  local nodetree::Term =
        buildApplication(
           nameTerm(nodeTreeConstructorName(first.5.resultType)),
           [nameTerm("Node"),
            foldr(\ p::(Integer, AbellaType) rest::Term ->
                    if tyIsNonterminal(p.2)
                    then consTerm(
                            buildApplication(
                               nameTerm(nodeTreeConstructorName(p.2)),
                               [nameTerm("C" ++ toString(p.1) ++ "Node"),
                                nameTerm("C" ++ toString(p.1) ++ "CL")]),
                            rest)
                    else rest,
                  nilTerm(), iChildren)]);
  local localAccessRel::String =
        localAccessRelationName(first.5.resultType, first.2, first.4);
  local accessRel::String = accessRelationName(first.3, first.1);
  --Make all these varTerms so they get put into exists binding for us
  local localName::Term =
        varTerm(capitalize(nameToShortName(first.2)), genInt());
  local localNode::Term =
        varTerm(capitalize(nameToShortName(first.2)) ++ "Node",
                genInt());
  local localCL::Term =
        varTerm(capitalize(nameToShortName(first.2)) ++ "CL",
                genInt());
  local attrVal::Term =
        varTerm(capitalize(nameToShortName(first.1)), genInt());
  --
  local here::(String, String, AbellaType, String, Term,
               [[Metaterm]], DefClause) =
        ( first.1, first.2, first.5.resultType, first.4,
          --head term
          buildApplication(nameTerm(rel),
             [nameTerm("TreeName"), prodBuilt, nodetree]),
          --bodies for definition
          [ --no value for local
            [termMetaterm(
                buildApplication(nameTerm(localAccessRel),
                   [nameTerm("TreeName"), nameTerm("Node"),
                    nameTerm(attributeNotExistsName)]))],
            --local, with no attr value
            [termMetaterm(
                buildApplication(nameTerm(localAccessRel),
                   [nameTerm("TreeName"), nameTerm("Node"),
                    buildApplication(nameTerm(attributeExistsName),
                       [buildApplication(nameTerm(pairConstructorName),
                           [localName,
                            buildApplication(
                               nameTerm(nodeTreeConstructorName(
                                           first.3)),
                               [localNode, localCL])])])])),
             termMetaterm(
                buildApplication(nameTerm(accessRel),
                   [localName, localNode,
                    nameTerm(attributeNotExistsName)]))]
          ],
          --other production def clause
          ruleClause(
             termMetaterm(
                buildApplication(nameTerm(rel),
                   [nameTerm("TreeName"), nameTerm("Term"),
                    nameTerm("NodeTree")])),
             impliesMetaterm(
                bindingMetaterm(existsBinder(),
                   map(pair(_, nothing()), childNames),
                   eqMetaterm(nameTerm("Term"), prodBuilt)),
                falseMetaterm())) );

  local rest::[(String, String, AbellaType, String, Term,
                [[Metaterm]], DefClause)] =
        buildMissingLocalChildEqInfo(tail(expected), defined);

  return case expected of
         | [] -> []
         | _ -> if found then rest else here::rest
         end;
}


function generateInhAttrChildEquationRelations
String ::= --[(attr, index (e.g. "child3", "forward"), top NT,
           --  prod, head term (rel tree nodetree), [clause bodies],
           --  def clause for not this prod)]
           info::[(String, String, AbellaType, String, Term,
                   [[Metaterm]], DefClause)]
{
  local first::(String, String, AbellaType, String, Term,
                [[Metaterm]], DefClause) =
        head(info);

  local relName::String =
        inhChildEquationName(first.1, first.3, first.4, first.2);
  local relation::[(String, AbellaType)] =
        [(relName, equationRelType(first.3))];

  --Clean up any equalities/repeated accesses/function calls which
  --   can be reduced to a single call
  local cleaned::[[Metaterm]] = cleanInhAttrChildEq(first.6);
  --Replace varTerms with actual names
  local noVars::(Term, [Metaterm]) =
        fillVars(first.5,
           map(\ body::[Metaterm] ->
                 if null(body)
                 then trueMetaterm() --put in true placeholder
                 else foldl(andMetaterm, head(body), tail(body)),
               cleaned));
  --Actual clauses for defining attr
  local clauses::AbellaDefs =
        foldr(\ body::Metaterm rest::AbellaDefs ->
                consAbellaDefs(
                  case body of
                  | trueMetaterm() -> --remove true placeholder
                    factClause(termMetaterm(noVars.1))
                  | _ -> ruleClause(termMetaterm(noVars.1), body)
                  end,
                  rest),
              --end with not-this-prod clause
              singleAbellaDefs(first.7), noVars.2);

  local thisDef::Definition = definition(relation, clauses);

  local rest::String =
        generateInhAttrChildEquationRelations(tail(info));

  return case info of
         | [] -> ""
         | _::_ -> thisDef.unparse ++ "\n" ++ rest
         end;
}


function generateInhAttrEquationComponentRelations
String ::= --[(attr, index (e.g. "child3", "forward"), top NT,
           --  prod, head term (rel tree nodetree), [clause bodies],
           --  def clause for not this prod)]
           info::[(String, String, AbellaType, String, Term,
                   [[Metaterm]], DefClause)]
           componentName::String
{
  --[(attr, index, top NT, prod)]
  local reduced::[(String, String, AbellaType, String)] =
        map(\ p::(String, String, AbellaType, String, Term,
                  [[Metaterm]], DefClause) ->
              (p.1, p.2, p.3, p.4),
            info);
  --Put all with same attr/NT together (same component rel)
  local groupedByRel::[[(String, String, AbellaType, String)]] =
        groupBy(\ p1::(String, String, AbellaType, String)
                  p2::(String, String, AbellaType, String) ->
                  p1.1 == p2.1 && tysEqual(p1.3, p2.3),
                reduced);
  --[(attr, NT, [rel name])]
  local groupedRels::[(String, AbellaType, [String])] =
        map(\ l::[(String, String, AbellaType, String)] ->
              (head(l).1, head(l).3,
               map(\ p::(String, String, AbellaType, String) ->
                     inhChildEquationName(p.1, p.3, p.4, p.2),
                   l)),
            groupedByRel);
  --[(attr, NT, [term applying child rel])]
  local groupedAppliedRels::[(String, AbellaType, [Metaterm])] =
        map(\ p::(String, AbellaType, [String]) ->
              (p.1, p.2,
               map(\ rel::String ->
                     termMetaterm(
                        buildApplication(nameTerm(rel),
                           [nameTerm("TreeName"), nameTerm("Term"),
                            nameTerm("NodeTree")])),
                   p.3)),
            groupedRels);
  --
  local defs::[Definition] =
        map(\ p::(String, AbellaType, [Metaterm]) ->
              let relName::String =
                  equationName(p.1, p.2) ++ name_sep ++
                  componentName
              in
                definition(
                   [(relName, equationRelType(p.2))],
                   singleAbellaDefs(
                      ruleClause(
                         termMetaterm(
                            buildApplication(
                               nameTerm(relName),
                               [nameTerm("TreeName"), nameTerm("Term"),
                                nameTerm("NodeTree")])),
                         foldr1(andMetaterm, p.3))))
              end,
            groupedAppliedRels);

  return implode("\n", map((.unparse), defs));
}



{-
  Build all new groups of (attr, [nonterminal]) which can arise from
     this grammar for inh attrs
  This is [(new attr, [existing nt])] + [(old attr, [new nt])]

  We need to create all of these pairs because a new production for an
  NT introduced in a further extension can set any inh attr on a child
  at some point in the future, so we need to set up for them becoming
  associated now.  This makes sure we will have consistent relations
  for the composition if two extensions both make an attr and NT
  become associated separately.  If that never happens, we don't hurt
  anything by having the extras.
-}
function findAllPossibleNewAssociatedAttrs
[(String, [String])] ::= new_nonterminals::[String] new_inhAttrs::[String]
                         e::Decorated Env
{
  return
     foldr(\ p::(String, [String]) rest::[(String, [String])] ->
             if contains(p.1, new_inhAttrs)
             then p::rest
             else let filtered::[String] =
                      filter(\ x::String ->
                               contains(x, new_nonterminals),
                             p.2)
                  in
                    if null(filtered)
                    then rest
                    else (p.1, filtered)::rest
                  end,
           [], findAllPossibleAssociatedAttrs(e));
}


{-
  Build the combination of all (inh attr, [NT]) which are possible
-}
function findAllPossibleAssociatedAttrs
[(String, [String])] ::= e::Decorated Env
{
  --Until I figure out how I want to handle silver:core, filter those out
  local known_nonterminals::[String] =
        filter(\ x::String -> !startsWith("silver:core:", x),
        flatMap(\ m::tmap:Map<String TypeDclInfo> ->
                  map((.fullName), map(snd, tmap:toList(m))),
                e.typeTree));
  local encoded_known_nonterminals::[String] =
        map(colonsToEncoded, nub(known_nonterminals));

  local known_inhAttrs::[String] = findAllInhAttrs(e);
  local encoded_known_inhAttrs::[String] =
        map(colonsToEncoded, nub(known_inhAttrs));

  return
     map(\ attr::String -> (attr, encoded_known_nonterminals),
         encoded_known_inhAttrs);
}

--
function findAllInhAttrs
[String] ::= e::Decorated Env
{
  return
     map(\ p::(String, AttributeDclInfo) -> p.2.fullName,
         filter(\ p::(String, AttributeDclInfo) ->
                  case p.2 of
                  | inhDcl(_, _, _) -> true
                  | _ -> false
                  end,
                flatMap(\ m::tmap:Map<String AttributeDclInfo> ->
                          tmap:toList(m), e.attrTree)));
}


--Build the clauses based on information for attribute equations
function produceSynClauses
[(String, AbellaType, [DefClause])] ::=
     --[(attr, top NT type, prod, head term (rel tree nodetree), [clause bodies])]
     syns::[(String, AbellaType, String, Term, [[Metaterm]])]
{
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
            cleanSyns);
  --
  return
     map(\ p::(String, AbellaType, String, Term, [Metaterm]) ->
           ( p.1, p.2,
             map(\ b::Metaterm ->
                   --optimize away useless `true`s
                   case b of
                   | trueMetaterm() -> factClause(termMetaterm(p.4))
                   | _ -> ruleClause(termMetaterm(p.4), b)
                   end,
                 p.5) ),
         noVars);
}


--Get all known productions by their nonterminal type
--[(nonterminal, [(prod name, prod type)])]
function getProdsByType
[(String, [(String, AbellaType)])] ::= env::Decorated Env
{
  local prodsByNT::[EnvTree<ValueDclInfo>] = env.prodsForNtTree;
  local prodsLst::[(String, ValueDclInfo)] =
        flatMap(tmap:toList(_), prodsByNT);
  local sorted::[(String, ValueDclInfo)] =
        sortBy(\ p1::(String, ValueDclInfo) p2::(String, ValueDclInfo) ->
                 p1.1 <= p2.1,
               prodsLst);
  local grouped::[[(String, ValueDclInfo)]] =
        groupBy(\ p1::(String, ValueDclInfo) p2::(String, ValueDclInfo) ->
                  p1.1 == p2.1,
                sorted);
  local prods::[(String, [ValueDclInfo])] =
        map(\ l::[(String, ValueDclInfo)] ->
              ( nameToNonterminal(colonsToEncoded(head(l).1)),
                map(snd, l) ),
            grouped);
  local expandProd::[(String, [(String, AbellaType)])] =
        map(\ p::(String, [ValueDclInfo]) ->
              ( p.1,
                map(\ d::ValueDclInfo ->
                      let pname::String = d.fullName
                      in
                        ( colonsToEncoded(pname),
                          lookupProdType(pname, env) )
                      end,
                    p.2) ),
            prods);
  return expandProd;
}

--Get all known productions which forward
function getForwardingProds
[String] ::= env::Decorated Env
{
  local prodsByNT::[EnvTree<ValueDclInfo>] = env.prodsForNtTree;
  local prodsLst::[(String, ValueDclInfo)] =
        flatMap(tmap:toList(_), prodsByNT);
  local fwds::[(String, ValueDclInfo)] =
         filter(\ p::(String, ValueDclInfo) -> p.2.hasForward,
                prodsLst);
  return map(\ p::(String, ValueDclInfo) -> nameToProd(p.2.fullName),
             fwds);
}


--Produce clauses allowing anything for attr eq relations for prods
--which don't have an equation for the attr
--[(attr, top NT type, prod, head term (rel tree nodetree), [clause bodies])]
function produceMissingSynEqInfo
[(String, AbellaType, String, Term, [[Metaterm]])] ::=
     --[(attr, top NT type, prod, head term (rel tree nodetree), [clause bodies])]
     synAttrEqInfo::[(String, AbellaType, String, Term, [[Metaterm]])]
     componentName::String env::Decorated Env
{
  --[(nonterminal name, [(prod name, prod type)])]
  local prodsByType::[(String, [(String, AbellaType)])] =
        getProdsByType(env);
  --Filter down to only the relevant information about equations
  --[(attr, top NT type, prod)]
  local filtered::[(String, AbellaType, String)] =
        map(\ p::(String, AbellaType, String, Term, [[Metaterm]]) ->
              (p.1, p.2, p.3), synAttrEqInfo);
  --Find the productions which are missing equations for each
  --   attribute and produce empty clauses
  local sortedAttrEquations::[(String, AbellaType, String)] =
        sortBy(\ p1::(String, AbellaType, String)
                 p2::(String, AbellaType, String) ->
                 case p1.2, p2.2 of
                 | nameAbellaType(n1), nameAbellaType(n2) ->
                   p1.1 < p2.1 || (p1.1 == p2.1 && n1 <= n2)
                 | _, _ -> error("Not possible")
                 end,
               filtered);
  --[[(attr, nonterminal, prod)]] for existing equations
  local groupedAttrsProds::[[(String, AbellaType, String)]] =
        groupBy(\ p1::(String, AbellaType, String)
                  p2::(String, AbellaType, String) ->
                  p1.1 == p2.1 && tysEqual(p1.2, p2.2),
                sortedAttrEquations);
  --[(attr, nonterminal, [prod])] for existing equations
  local foundProds::[(String, AbellaType, [String])] =
        map(\ l::[(String, AbellaType, String)] ->
              (head(l).1, head(l).2,
               map(\ p::(String, AbellaType, String) -> colonsToEncoded(p.3), l)),
            groupedAttrsProds);
  --[(attr, nonterminal, [(prod, prod type)])]
  local missingProdsByAttr::[(String, AbellaType, [(String, AbellaType)])] =
        map(\ eqs::(String, AbellaType, [String]) ->
              case eqs.2 of
              | nameAbellaType(nt) ->
                let prods::[(String, AbellaType)] =
                    findAssociated(nt, prodsByType).fromJust
                in
                --attr introduced in this grammar
                let attrNew::Boolean =
                    nameToGrammar(eqs.1) == componentName
                in
                --nonterminal introduced in this grammar
                let ntNew::Boolean =
                    nameToGrammar(nonterminalToName(nt)) == componentName
                in
                  ( eqs.1, eqs.2,
                    foldr(\ p::(String, AbellaType)
                            rest::[(String, AbellaType)] ->
                            if contains(p.1, eqs.3)
                            then rest
                            --only add it if its equation must be defined in this grammar
                            else if attrNew || ntNew ||
                                    nameToGrammar(p.1) == componentName
                            then p::rest
                            else rest,
                          [], prods) )
                end end end
              | _ -> error("Not possible")
              end,
            foundProds);
  return        --(attr,   NT,         [(prod,   prod type )])
     flatMap(\ p::(String, AbellaType, [(String, AbellaType)]) ->
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
                       ( p.1, p.2, prod.1,
                         buildApplication(
                            nameTerm(equationName(p.1, p.2) ++
                                     name_sep ++ componentName),
                            [nameTerm("TreeName"), treeTm,
                             nameTerm("NodeTree")]),
                         [[trueMetaterm()]] )
                     end,
                   p.3),
             missingProdsByAttr);
}


{-
  Generate a string of the Abella encoding based on the given information
-}
function generateContents
String ::= new_nonterminals::[String] new_attrs::[String]
           --(attribute name, [(nonterminal name, attr ty)])
           new_attrOccurrences::[(String, [(String, AbellaType)])]
           --[attr name]
           new_inheritedAttrs::[String]
           --(local name, [(production name, attr type)])
           new_localAttrs::[(String, [(String, AbellaType)])]
           --[(prod name, prod type)]
           new_prods::[(String, AbellaType)]
           --[(attr, top NT type, prod, head term (rel tree nodetree), [clause bodies])]
           synAttrEqInfo::[(String, AbellaType, String, Term, [[Metaterm]])]
           --[(attr, index (e.g. "child3", "forward"), top NT,
           --  prod, head term (rel tree nodetree), [clause bodies],
           --  not-this-prod defining clause)]
           inhAttrEqInfo::[(String, String, AbellaType, String, Term, [[Metaterm]], DefClause)]
           --fully-made definitions for local attributes
           localDefs::[Definition]
           --[(fun name, fun type, fun clauses)]
           funClauses::[(String, AbellaType, [DefClause])]
           componentName::String
           env::Decorated Env fenv::FlowEnv
{
  --[(attr, [NT])]
  local associatedAttrs::[(String, [String])] =
        findAllPossibleNewAssociatedAttrs(new_nonterminals,
                                          new_inheritedAttrs, env);
  --[(attr, [(nonterminal, attr ty)])]
  local associatedAttrsExpanded::[(String, [(String, AbellaType)])] =
        map(\ p::(String, [String]) ->
              (p.1, map(\ nt::String -> (nt, nameAbellaType("")), p.2)),
            associatedAttrs);

  --All new full equations are new occurrences + new associated + fwd on new NT
  --Need to make each combination occur only once
  local new_fullEqs::[(String, [(String, AbellaType)])] =
        map(\ p::(String, [(String, AbellaType)]) ->
              (p.1,
               nubBy(\ p1::(String, AbellaType)
                       p2::(String, AbellaType) -> p1.1 == p2.1,
                     p.2)),
            combineAssociations(
               combineAssociations(new_attrOccurrences,
                  associatedAttrsExpanded),
               [("forward",
                 map(\ nt::String ->
                       (nt, functorAbellaType(
                               functorAbellaType(pairType,
                                  nameToNonterminalType(nt)),
                               nodeTreeType)),
                     new_nonterminals))]));

  --All equation information, including for prods missing equations
  local allSynAttrEqInfo::[(String, AbellaType, String, Term, [[Metaterm]])] =
        synAttrEqInfo ++
        produceMissingSynEqInfo(synAttrEqInfo, componentName, env);
  --[(attr, nonterminal, [definitional clauses])]
  local synAttrEqClauses::[(String, AbellaType, [DefClause])] =
        produceSynClauses(allSynAttrEqInfo);

  --All equation information for inh attrs, including those where
  --   the equation is "no equation"
  local full_inhAttrEqInfo::[(String, String, AbellaType, String,
                              Term, [[Metaterm]], DefClause)] =
        completeInhEqs(inhAttrEqInfo, new_prods, new_nonterminals,
                       new_inheritedAttrs, new_localAttrs, env);

  return
     "%New syntax definitions\n" ++
     generateNonterminalTypes(new_nonterminals) ++ "\n" ++
     generateProductions(new_prods) ++ "\n\n" ++
     generateNodeTypes(new_nonterminals) ++ "\n\n" ++
     generateNodeTreeConstructors(new_nonterminals) ++ "\n\n" ++
     "%New attributes\n" ++
     generateAccessRelations(new_attrOccurrences) ++ "\n" ++
     generateLocalAccessRelations(new_localAttrs, env) ++ "\n" ++
     generateForwardAccessRelations(new_nonterminals, env) ++ "\n\n" ++
     generateInheritedInformation(new_inheritedAttrs) ++ "\n\n" ++
     "%New structural equality\n" ++
     generateStructureEqFull(new_nonterminals) ++ "\n" ++
     generateStructureEqComponent(new_prods, componentName) ++ "\n\n" ++
     --
     "%New equation relations\n" ++
     generateEquationsFull(new_fullEqs) ++ "\n" ++
     generateWpdRelationsFull(new_nonterminals) ++ "\n\n" ++
     "%New function relations\n" ++
     ( let funSplit::( [(String, AbellaType)],
                       [(String, AbellaType, [DefClause])] ) =
           foldr(\ p::(String, AbellaType, [DefClause])
                   rest::( [(String, AbellaType)],
                           [(String, AbellaType, [DefClause])] ) ->
                   if null(p.3)
                   then ( (p.1, p.2)::rest.1, rest.2 )
                   else ( rest.1, p::rest.2 ),
                 ([], []), funClauses)
       in
       let allDefClauses::[DefClause] =
           flatMap(\ p::(String, AbellaType, [DefClause]) -> p.3,
                   funSplit.2)
       in --Define functions which cannot have results
          --Safe to define these non-mutually-recursively because they
          --   can't call any other functions
         foldr(\ p::(String, AbellaType) rest::String ->
                 let args::String =
                     foldr(\ t::AbellaType rest::(Integer, String) ->
                             ( rest.1 + 1,
                               "A" ++ toString(rest.1) ++ " " ++ rest.2 ),
                           (0, ""), p.2.argumentTypes).2
                 in
                   "Define " ++ nameToFun(p.1) ++ " : " ++ p.2.unparse ++ " by\n" ++
                   "   " ++ nameToFun(p.1) ++ " " ++ args ++ " := false.\n" ++
                   rest
                 end,
               "", funSplit.1) ++
         if null(allDefClauses)
         then ""
         else definition(map(\ p::(String, AbellaType, [DefClause]) ->
                               (nameToFun(p.1), p.2), funSplit.2),
                 let rev::[DefClause] = reverse(allDefClauses)
                 in
                   foldr(consAbellaDefs(_, _), singleAbellaDefs(head(rev)),
                         reverse(tail(rev)))
                 end).unparse
       end end ) ++ "\n\n" ++
     "%New component and child equation relations\n" ++
     generateSynAttrEquationComponentRelations(synAttrEqClauses,
        componentName) ++ "\n" ++
     generateInhAttrChildEquationRelations(full_inhAttrEqInfo) ++ "\n" ++
     generateInhAttrEquationComponentRelations(
        full_inhAttrEqInfo, componentName) ++ "\n\n" ++
     "%New local equation relations\n" ++
     foldr(\ d::Definition rest::String -> d.unparse ++ rest,
           "", localDefs) ++ "\n\n" ++
     --
     "%New component WPD relations\n" ++
     generateWpdNodeRelationsComponent(new_attrOccurrences, new_localAttrs,
        associatedAttrs, env, componentName) ++ "\n" ++
     generateWpdNtRelationsComponent(new_prods, componentName) ++ "\n\n" ++
     --
     --Switch over to generating axioms
     --
     "%Access uniqueness axioms\n" ++
     generateAccessUniquenessAxioms(new_attrOccurrences,
                                    new_localAttrs, env) ++ "\n\n" ++
     "%Access is axioms\n" ++
     generateAccessIsAxioms(new_attrOccurrences,
                            new_localAttrs, env) ++ "\n\n" ++
     "%Equation primary component theorems\n" ++
     generatePrimaryComponentTheorems(allSynAttrEqInfo, componentName) ++
        "\n" ++
     generateInhAttrChildEqTheorems(full_inhAttrEqInfo) ++
        "\n\n" ++
     "%WPD primary component theorems\n" ++
     generateWPDPrimaryComponentTheorems(new_prods, componentName) ++
        "\n\n" ++
     "%Node tree form theorems\n" ++
     generateNodeTreeFormTheorems(new_nonterminals) ++ "\n\n" ++
     "%WPD to attribute equation relation theorems\n" ++
     generateWpdToAttrEquationTheorems(
        new_attrOccurrences ++ associatedAttrsExpanded,
        new_localAttrs, env) ++ "\n\n" ++
     "%Structure equality theorems\n" ++
     generateStructureEqNtTheorems(new_nonterminals, [componentName]) ++
        "\n\n" ++
     "%Structure equality primary component theorems\n" ++
     generateStructureEqPrimaryComponentTheorems(new_prods, componentName);
}

