grammar silver:compiler:extension:abella_compilation;


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
           prods::[(String, AbellaType)]
{
  return
     case localAttrs of
     | [] -> ""
     | (attr, [])::rest ->
       generateLocalAccessRelations(rest, prods)
     | (attr, (prod, attrTy)::tl)::rest
       when findAssociated(prod, prods) matches just(prodTy) ->
       "Type " ++ localAccessRelationName(prodTy.resultType, attr, prod) ++
       "   " ++ prodTy.resultType.unparse ++ " -> " ++
       typeToNodeType(prodTy.resultType) ++ " -> " ++
       functorAbellaType(nameAbellaType(attrValTypeName), attrTy).unparse ++
       " -> prop.\n" ++
       generateLocalAccessRelations((attr, tl)::rest, prods)
     | _ -> error("Impossible for a well-typed grammar")
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
     "Define " ++ typeToStructureEqName(nt) ++ "__" ++ component ++
     " : " ++ nt.unparse ++ " -> " ++ nt.unparse ++ " -> prop by\n" ++
     foldr(\ p::(String, AbellaType) rest::String ->
             generateStructureEqComponentBodies(p.1, p.2,
                nt, component) ++ ".\n" ++ rest,
           "", group);
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
        typeToStructureEqName(nt) ++ "__" ++ component ++ " " ++
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
           prods::[(String, AbellaType)] component::String
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
                        case findAssociated(x.1, prods).fromJust.resultType of
                        | nameAbellaType(prodTy) ->
                          ("local", p.1, x.2, nonterminalToName(prodTy), x.1)
                        | _ ->
                          error("Production must build nonterminal")
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
     "Define " ++ wpdNodeTypeName(nt) ++ "__" ++ component ++ " : " ++
     nt.unparse ++ " -> $node_tree -> prop by\n" ++
     "   " ++ wpdNodeTypeName(nt) ++ "__" ++ component ++ " Tree (" ++
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
     "Define " ++ wpdTypeName(nt) ++ "__" ++ component ++
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
        wpdTypeName(nt) ++ "__" ++ component ++ " " ++
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
           prods::[(String, AbellaType)]
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
                           findAssociated(pt.1, prods).fromJust.resultType,
                           p.1, pt.1),
                      p.2), localAttrs);
  return
     foldr(\ acc::String rest::String ->
             "Theorem " ++ acc ++ "__unique : forall Tree Node V V',\n" ++
             "   " ++ acc ++ " Tree Node V ->\n" ++
             "   " ++ acc ++ " Tree Node V' -> V = V'.\n" ++
             "skip.\n" ++
             rest,
           "", attrs ++ locals);
}


function generateAccessIAxioms
String ::= attrOccurrences::[(String, [(String, AbellaType)])]
           localAttrs::[(String, [(String, AbellaType)])]
           prods::[(String, AbellaType)]
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
                            findAssociated(pt.1, prods).fromJust.resultType,
                            p.1, pt.1), pt.2,
                         findAssociated(pt.1, prods).fromJust.resultType),
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
               "Theorem " ++ p.1 ++ "__is : forall Tree Node CL "  ++
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
String ::= attrOccurrences::[(String, [(String, AbellaType)])]
           prods::[(String, AbellaType)]
           component::String
{
  --(nonterminal, [attribute name])
  local attrsByNT::[(String, [String])] =
        let expanded::[(String, String)] =
            flatMap(\ p::(String, [(String, AbellaType)]) ->
                      map(\ ntty::(String, AbellaType) ->
                            (p.1, ntty.1), p.2),
               attrOccurrences)
        in
        let sorted::[(String, String)] =
            sortBy(\ p1::(String, String) p2::(String, String) ->
                     p1.2 <= p2.2, expanded)
        in
        let grouped::[[(String, String)]] =
            groupBy(\ p1::(String, String) p2::(String, String) ->
                      p1.2 == p2.2, sorted)
        in
          map(\ l::[(String, String)] ->
                (head(l).2, map(\ p::(String, String) -> p.1, l)),
              grouped)
        end end end;
  --(nonterminal, [(prod name, prod type)])
  local prodsByNT::[(String, [(String, AbellaType)])] =
        let expanded::[(String, AbellaType, String)] =
            map(\ p::(String, AbellaType) ->
                  (p.1, p.2, nonterminalTypeToName(p.2.resultType)),
                prods)
        in
        let sorted::[(String, AbellaType, String)] =
            sortBy(\ p1::(String, AbellaType, String)
                     p2::(String, AbellaType, String) -> p1.3 <= p2.3,
                   expanded)
        in
        let grouped::[[(String, AbellaType, String)]] =
            groupBy(\ p1::(String, AbellaType, String)
                      p2::(String, AbellaType, String) -> p1.3 == p2.3,
                    sorted)
        in
          map(\ l::[(String, AbellaType, String)] ->
                (head(l).3, map(\ p::(String, AbellaType, String) ->
                                  (p.1, p.2), l)),
              grouped)
        end end end;
  return generatePrimaryComponentTheoremBodies(
            attrsByNT, prodsByNT, component);
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
                       "Theorem " ++ equationName(a, nt) ++ "__" ++
                          nameToProd(p.1) ++ " : forall " ++
                          implode(" ", children) ++
                          " Node TreeName T,\n   " ++
                       typeToStructureEqName(nt) ++ " T (" ++
                          nameToProd(p.1) ++ " " ++
                          implode(" ", children) ++ ") ->\n   " ++
                       equationName(a, nt) ++ " TreeName T Node ->" ++
                       "\n   " ++
                       equationName(a, nt) ++ "__" ++ component ++
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
         "Theorem " ++ wpdTypeName(nt) ++ "__" ++ nameToProd(pr) ++
            " : forall T " ++ implode(" ", children) ++ " NodeTree," ++
            "\n   " ++
         typeToStructureEqName(nt) ++ " T (" ++ nameToProd(pr) ++
            " " ++ implode(" ", children) ++ ") ->\n   " ++
         wpdTypeName(nt) ++ " T NodeTree ->\n   " ++
         wpdTypeName(nt) ++ "__" ++ component ++ " (" ++
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
          "__ntr_" ++
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
           prods::[(String, AbellaType)]
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
                         findAssociated(pt.1, prods).fromJust.resultType),
                      p.2), localAttrs);
  return
     --attrs
     foldr(\ p::(String, String, AbellaType, AbellaType) rest::String ->
             "Theorem $wpd__to__" ++ p.2 ++ "__" ++ p.4.unparse ++
                " : forall Tree NodeTree,\n   " ++
             wpdTypeName(p.4) ++ " Tree NodeTree ->\n   " ++
             p.1 ++ " Tree Tree NodeTree.\n" ++
             "skip.\n" ++
             rest,
           "", attrInfos) ++
     --locals
     foldr(\ p::(String, String, String, AbellaType, AbellaType) rest::String ->
             "Theorem $wpd__to__" ++ p.2 ++ "_local_" ++ p.3 ++
                "__" ++ p.5.unparse ++ " : forall Tree Tree' NodeTree," ++
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
         "Theorem " ++ typeToStructureEqName(ntTy) ++ "__equal" ++
            " : forall T1 T2,\n   " ++
         typeToStructureEqName(ntTy) ++ " T1 T2 -> T1 = T2.\n" ++
         "skip.\n" ++
         "Theorem " ++ typeToStructureEqName(ntTy) ++ "__symm" ++
            " : forall T1 T2,\n   " ++
         typeToStructureEqName(ntTy) ++ " T1 T2 ->\n   " ++
         typeToStructureEqName(ntTy) ++ " T2 T1.\n" ++
         "skip.\n" ++
         "Theorem " ++ typeToStructureEqName(ntTy) ++ "__wpd" ++
            " : forall T NTr,\n   " ++
         wpdTypeName(ntTy) ++ " T NTr -> " ++
            typeToStructureEqName(ntTy) ++ " T T.\n" ++
         "skip.\n" ++
         foldr(\ c::String rest::String ->
                 "Theorem " ++ structureEqExpansionTheorem(ntTy, c) ++
                    " : forall T1 T2,\n   " ++
                 typeToStructureEqName(ntTy) ++ "__" ++ c ++
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
         "Theorem $structure_eq__" ++
            nameToProd(prod) ++ " : forall T " ++
            implode(" ", children) ++ ",\n   " ++
         typeToStructureEqName(nt) ++ " T (" ++ nameToProd(prod) ++
            " " ++ implode(" ", children) ++ ") -> \n   " ++
         typeToStructureEqName(nt) ++ "__" ++ component ++
            " T (" ++ nameToProd(prod) ++ " " ++
            implode(" ", children) ++ ").\n" ++
         "skip.\n" ++
         generateStructureEqPrimaryComponentTheorems(rest, component)
       end end
     end;
}


{-
  The associatedAttrs are the attributes which don't occur on the
  nonterminals associated with them, but which are set by some
  production of that nonterminal on some child.
-}
function generateContents
String ::= nonterminals::[String] attrs::[String]
           --(attribute name, [(nonterminal name, attr ty)])
           attrOccurrences::[(String, [(String, AbellaType)])]
           inheritedAttrs::[String]
           --(local name, [(production name, attr type)])
           localAttrs::[(String, [(String, AbellaType)])]
           --(attr, [nonterminals])
           associatedAttrs::[(String, [String])]
           prods::[(String, AbellaType)]
           componentName::String
{
  local associatedAttrsExpanded::[(String, [(String, AbellaType)])] =
        map(\ p::(String, [String]) ->
              (p.1, map(\ nt::String -> (nt, nameAbellaType("")), p.2)),
            associatedAttrs);
  return
     generateNonterminalTypes(nonterminals) ++ "\n" ++
     generateProductions(prods) ++ "\n\n" ++
     generateNodeTypes(nonterminals) ++ "\n\n" ++
     "Kind $node_tree   type.\n\n" ++
     generateNodeTreeConstructors(nonterminals) ++ "\n\n" ++
     generateAccessRelations(attrOccurrences) ++ "\n" ++
     generateLocalAccessRelations(localAttrs, prods) ++ "\n\n" ++
     generateInheritedInformation(inheritedAttrs) ++ "\n\n" ++
     generateStructureEqFull(nonterminals) ++ "\n" ++
     generateStructureEqComponent(prods, componentName) ++ "\n\n" ++
     generateEquationsFull(
        attrOccurrences ++ associatedAttrsExpanded) ++ "\n" ++
     generateWpdRelationsFull(nonterminals) ++ "\n\n" ++
     --Here's where the component equation relations would go
     "Define $split : (A -> B -> prop) -> ($pair A B) -> prop by\n" ++
     "  $split SubRel ($pair_c A B) :=\n" ++
     "     SubRel A B.\n\n" ++
     generateWpdNodeRelationsComponent(attrOccurrences, localAttrs,
        associatedAttrs, prods, componentName) ++ "\n" ++
     generateWpdNtRelationsComponent(prods, componentName) ++ "\n\n" ++
     --
     --Switch over to generating axioms
     --
     generateAccessUniquenessAxioms(attrOccurrences,
                                    localAttrs, prods) ++ "\n\n" ++
     generateAccessIAxioms(attrOccurrences,
                           localAttrs, prods) ++ "\n\n" ++
     generatePrimaryComponentTheorems(
        attrOccurrences ++ associatedAttrsExpanded,
        prods, componentName) ++
        "\n\n" ++
     generateWPDPrimaryComponentTheorems(prods, componentName) ++
        "\n\n" ++
     generateNodeTreeFormTheorems(nonterminals) ++ "\n\n" ++
     generateWpdToAttrEquationTheorems(
        attrOccurrences ++ associatedAttrsExpanded,
        localAttrs, prods) ++ "\n\n" ++
     generateStructureEqNtTheorems(nonterminals, [componentName]) ++
        "\n\n" ++
     generateStructureEqPrimaryComponentTheorems(prods, componentName);
}

