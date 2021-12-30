grammar silver:compiler:extension:abella_compilation:encoding;


import silver:util:treemap as tmap;


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


function generateAttrEquationComponentRelations
String ::= clauses::[(String, AbellaType, [DefClause])]
           component::String inheritedAttrs::[String]
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
  return generateAttrEquationComponentRelations_help(attrGroups,
            component, inheritedAttrs);
}
function generateAttrEquationComponentRelations_help
String ::= attrs::[(String, AbellaType, [DefClause])]
           component::String inheritedAttrs::[String]
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
  local relType::AbellaType =
        arrowAbellaType(here.2,
        arrowAbellaType(here.2,
        arrowAbellaType(nodeTreeType,
                        nameAbellaType("prop"))));
  local defn::Definition =
        definition([(equationRelation, new(relType))], body);
  return
     case attrs of
     | [] -> ""
     | hd::tl ->
       defn.unparse ++ "\n" ++
       generateAttrEquationComponentRelations_help(tl, component,
                                                   inheritedAttrs)
     end;
}


{-
  Find the new associated attrs (attrs which do not occur on the NT,
  but which are set by at least one of its prods on a child) induced
  by this grammar---need these to create new full relations
  Produces [(attr, [NT])]
-}
function findNewAssociatedAttrs
[(String, [String])] ::=
     --[(attr, [(NT, attr ty)])]
     new_attrOccurrences::[(String, [(String, AbellaType)])]
     --[(attr, top NT type, prod, head term (rel tree nodetree), [clause bodies])]
     attrEqInfo::[(String, AbellaType, String, Term, [[Metaterm]])]
     grammarName::String env::Decorated Env fenv::FlowEnv
{
  --Cut down the equation information to attr and tree root type
  local filtered::[(String, AbellaType)] =
      map(\ p::(String, AbellaType, String, Term, [[Metaterm]]) ->
            (p.1, p.2), attrEqInfo);
  --Cut down to unique pairs of attr and tree root type
  local cleaned::[(String, AbellaType)] =
      nubBy(\ p1::(String, AbellaType) p2::(String, AbellaType) ->
              p1.1 == p2.1 && tysEqual(p1.2, p2.2), filtered);
  --Sort by attribute
  local sorted::[(String, AbellaType)] =
      sortBy(\ p1::(String, AbellaType) p2::(String, AbellaType) ->
               p1.1 <= p2.1, cleaned);
  --Group by attribute
  local grouped::[[(String, AbellaType)]] =    
      groupBy(\ p1::(String, AbellaType) p2::(String, AbellaType) ->
                p1.1 == p2.1, sorted);
  --Put into groups of (attr, [nonterminals])
  local paired::[(String, [String])] =
      map(\ l::[(String, AbellaType)] ->
            ( head(l).1, map(\ p::(String, AbellaType) ->
                               nonterminalTypeToName(p.2),
                             l) ), grouped);
  --Get just the ones without any occurrence already, either here or in a previous grammar
  return
    flatMap(\ p::(String, [String]) ->
              let pOccurs::[String] =
                  case findAssociated(p.1, new_attrOccurrences) of
                  | nothing() -> [] --unknown because not declared in this grammar
                  | just(nts_tys) -> map(fst, nts_tys)
                  end
              in
                [( p.1,
                   foldr(\ nt::String rest::[String] ->
                           if contains(nt, pOccurs)
                           then rest
                           else if checkAttrNtFullRelationExists(p.1, nt, grammarName, env, fenv)
                           then rest
                           else nt::rest,
                         [], p.2) )]
              end, paired);
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
     --[(attr, top NT type, prod, head term (rel tree nodetree), [clause bodies])]
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
             map(\ b::Metaterm ->
                   --optimize away useless `true`s
                   case b of
                   | trueMetaterm() -> factClause(termMetaterm(p.4))
                   | _ -> ruleClause(termMetaterm(p.4), b)
                   end,
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
       let argsub::(silver:compiler:extension:abella_compilation:abella:TermList, [[Metaterm]], [[Metaterm]]) =
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
(silver:compiler:extension:abella_compilation:abella:TermList, [[Metaterm]], [[Metaterm]]) ::=
     hd1::silver:compiler:extension:abella_compilation:abella:TermList body1::[[Metaterm]]
     hd2::silver:compiler:extension:abella_compilation:abella:TermList body2::[[Metaterm]]
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
       let restsub::(silver:compiler:extension:abella_compilation:abella:TermList, [[Metaterm]], [[Metaterm]]) =
           makeConsistentNames_help_list(rest1, tsub.2, rest2, tsub.3)
       in
         ( consTermList(tsub.1, restsub.1), restsub.2, restsub.3 )
       end end
     | _, _ -> error("Unexpected case in makeConsistentNames_help_list")
     end;
}


--Get all known productions by their nonterminal type
--[(nonterminal, [(prod name, prod type)])]
function getProdsByType
[(String, [(String, AbellaType)])] ::= env::Decorated Env
{
  local prodsByNT::[EnvTree<ValueDclInfo>] =
        env.prodsForNtTree;
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


--Produce clauses allowing anything for attr eq relations for prods
--which don't have an equation for the attr
--[(attr, top NT type, prod, head term (rel tree nodetree), [clause bodies])]
function produceMissingEquationInfo
[(String, AbellaType, String, Term, [[Metaterm]])] ::=
     --[(attr, top NT type, prod, head term (rel tree nodetree), [clause bodies])]
     attrEqInfo::[(String, AbellaType, String, Term, [[Metaterm]])]
     componentName::String env::Decorated Env
{
  --[(nonterminal name, [(prod name, prod type)])]
  local prodsByType::[(String, [(String, AbellaType)])] =
        getProdsByType(env);
  --Filter down to only the relevant information about equations
  local filtered::[(String, AbellaType, String)] =
        map(\ p::(String, AbellaType, String, Term, [[Metaterm]]) ->
              (p.1, p.2, p.3), attrEqInfo);
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
                            else if attrNew || ntNew || nameToGrammar(p.1) == componentName
                            then p::rest
                            else rest,
                          [], prods) )
                end end end
              | _ -> error("Not possible")
              end,
            foundProds);
  return    --(attr,   NT,         [(prod,   prod type )])
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
           attrEqInfo::[(String, AbellaType, String, Term, [[Metaterm]])]
           --fully-made definitions for local attributes
           localDefs::[Definition]
           --[(fun name, fun type, fun clauses)]
           funClauses::[(String, AbellaType, [DefClause])]
           componentName::String
           env::Decorated Env fenv::FlowEnv
{
  --[(attr, [NT])]
  local associatedAttrs::[(String, [String])] =
        findNewAssociatedAttrs(new_attrOccurrences, attrEqInfo,
           encodedToColons(componentName), env, fenv);
  --[(attr, [(nonterminal, attr ty)])]
  local associatedAttrsExpanded::[(String, [(String, AbellaType)])] =
        map(\ p::(String, [String]) ->
              (p.1, map(\ nt::String -> (nt, nameAbellaType("")), p.2)),
            associatedAttrs);

  --All equation information, including for prods missing equations
  local allAttrEqInfo::[(String, AbellaType, String, Term, [[Metaterm]])] =
        attrEqInfo ++
        produceMissingEquationInfo(attrEqInfo, componentName, env);
  --[(attr, nonterminal, [definitional clauses])]
  local attrEqClauses::[(String, AbellaType, [DefClause])] =
        produceClauses(allAttrEqInfo, new_inheritedAttrs);

  return
     "%New syntax definitions\n" ++
     generateNonterminalTypes(new_nonterminals) ++ "\n" ++
     generateProductions(new_prods) ++ "\n\n" ++
     generateNodeTypes(new_nonterminals) ++ "\n\n" ++
     generateNodeTreeConstructors(new_nonterminals) ++ "\n\n" ++
     "%New attributes\n" ++
     generateAccessRelations(new_attrOccurrences) ++ "\n" ++
     generateLocalAccessRelations(new_localAttrs, env) ++ "\n\n" ++
     generateInheritedInformation(new_inheritedAttrs) ++ "\n\n" ++
     "%New structural equality\n" ++
     generateStructureEqFull(new_nonterminals) ++ "\n" ++
     generateStructureEqComponent(new_prods, componentName) ++ "\n\n" ++
     --
     "%New equation relations\n" ++
     generateEquationsFull(
        new_attrOccurrences ++ associatedAttrsExpanded) ++ "\n" ++
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
     "%New component equation relations\n" ++
     generateAttrEquationComponentRelations(attrEqClauses,
        componentName, new_inheritedAttrs) ++ "\n\n" ++
     "%New local equation relations\n" ++
     foldr(\ d::Definition rest::String -> d.unparse ++ rest,
           "", localDefs) ++ "\n\n" ++
     --
     "%New WPD relations\n" ++
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
     generatePrimaryComponentTheorems(allAttrEqInfo, componentName) ++
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

