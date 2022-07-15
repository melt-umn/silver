grammar silver:compiler:langserver;

monoid attribute valueRefLocs::[(Location, ValueDclInfo)];
monoid attribute typeRefLocs::[(Location, TypeDclInfo)];
monoid attribute attributeRefLocs::[(Location, AttributeDclInfo)];

attribute valueRefLocs, typeRefLocs, attributeRefLocs occurs on
  RootSpec, Grammar, Root, AGDcls, AGDcl,
  ProductionSignature, FunctionSignature, AspectProductionSignature, AspectFunctionSignature,
  ConstraintList, Constraint, ProductionLHS, FunctionLHS, AspectProductionLHS, AspectFunctionLHS,
  ProductionRHS, AspectRHS, ProductionRHSElem, AspectRHSElem,
  TypeExpr, Signature, SignatureLHS, TypeExprs, BracketedTypeExprs, BracketedOptTypeExprs,
  ProductionBody, ProductionStmts, ProductionStmt,
  Expr, Exprs, ExprInhs, ExprInh, ExprLHSExpr, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr;

propagate valueRefLocs, typeRefLocs, attributeRefLocs on
  RootSpec, Grammar, Root, AGDcls, AGDcl,
  ProductionSignature, FunctionSignature, AspectProductionSignature, AspectFunctionSignature,
  ConstraintList, Constraint, ProductionLHS, FunctionLHS, AspectProductionLHS, AspectFunctionLHS,
  ProductionRHS, AspectRHS, ProductionRHSElem, AspectRHSElem,
  TypeExpr, Signature, SignatureLHS, TypeExprs, BracketedTypeExprs, BracketedOptTypeExprs,
  ProductionBody, ProductionStmts, ProductionStmt,
  Expr, Exprs, ExprInhs, ExprInh, ExprLHSExpr, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr;

attribute typeRefLocs occurs on QNameType;
aspect typeRefLocs on top::QNameType using := of
| _ -> if top.lookupType.found then [(top.location, top.lookupType.dcl)] else []
end;

attribute attributeRefLocs occurs on QNameAttrOccur;
aspect attributeRefLocs on top::QNameAttrOccur using := of
| qNameAttrOccur(at) -> if top.found then [(at.location, top.attrDcl)] else []
end;

aspect valueRefLocs on Expr using <- of
| baseExpr(q) -> if q.lookupValue.found then [(q.location, q.lookupValue.dcl)] else []
end;

aspect attributeRefLocs on AnnoExpr using <- of
| annoExpr(q, _, _) -> if q.lookupAttribute.found then [(q.location, q.lookupAttribute.dcl)] else []
end;

aspect valueRefLocs on AGDcl using := of
| tcMonoidAttributeDcl(_, _, _, _, _, te, _) -> te.valueRefLocs
end;

synthesized attribute valueFileRefLocs::map:Map<String (Location, Decorated RootSpec, ValueDclInfo)>;
synthesized attribute typeFileRefLocs::map:Map<String (Location, Decorated RootSpec, TypeDclInfo)>;
synthesized attribute attributeFileRefLocs::map:Map<String (Location, Decorated RootSpec, AttributeDclInfo)>;

attribute valueFileRefLocs, typeFileRefLocs, attributeFileRefLocs occurs on Compilation;

aspect production compilation
top::Compilation ::= g::Grammars r::Grammars _ _
{
  top.valueFileRefLocs = buildFileRefs((.valueRefLocs), g.grammarList);
  top.typeFileRefLocs = buildFileRefs((.typeRefLocs), g.grammarList);
  top.attributeFileRefLocs = buildFileRefs((.attributeRefLocs), g.grammarList);
}

function buildFileRefs
annotation sourceGrammar occurs on a =>
map:Map<String (Location, Decorated RootSpec, a)> ::= accessor::([(Location, a)] ::= Decorated RootSpec) rs::[Decorated RootSpec]
{
  return directBuildTree(flatMap(\ r::Decorated RootSpec ->
    map(\ item::(Location, a) ->
      (r.grammarSource ++ item.1.filename, item.1, head(map:lookup(item.2.sourceGrammar, r.compiledGrammars)), item.2),
      accessor(r)),
    rs));
}

attribute valueRefLocs, typeRefLocs, attributeRefLocs occurs on InterfaceItems, InterfaceItem;
propagate valueRefLocs, typeRefLocs, attributeRefLocs on InterfaceItems;

aspect default production
top::InterfaceItem ::=
{
  top.valueRefLocs := [];
  top.typeRefLocs := [];
  top.attributeRefLocs := [];
}

abstract production refLocInterfaceItem
top::InterfaceItem ::= values::[(Location, ValueDclInfo)] types::[(Location, TypeDclInfo)] attrs::[(Location, AttributeDclInfo)]
{
  top.isEqual = true;  -- Don't rebuild downstream grammars when referenced locations change
  top.valueRefLocs := values;
  top.typeRefLocs := types;
  top.attributeRefLocs := attrs;
}

aspect function packInterfaceItems
InterfaceItems ::= r::Decorated RootSpec
{
  interfaceItems <- [
    refLocInterfaceItem(r.valueRefLocs, r.typeRefLocs, r.attributeRefLocs)
  ];
}

function lookupPos
[a] ::= line::Integer col::Integer items::[(Location, a)]
{
  return map(snd, filter(
    \ item::(Location, a) ->
      item.1.line <= line && item.1.endLine >= line && item.1.column <= col && item.1.endColumn >= col,
    items));
}

function updateLocPath
Location ::= p::String l::Location
{
  return loc(p, l.line, l.column, l.endLine, l.endColumn, l.index, l.endIndex);
}

function lookupDeclLocation
annotation sourceGrammar occurs on a,
annotation sourceLocation occurs on a =>
[Location] ::= fileName::String line::Integer col::Integer decls::map:Map<String (Location, Decorated RootSpec, a)>
{
  return unsafeTrace(map(\ item::(Decorated RootSpec, a) ->
    updateLocPath(item.1.grammarSource ++ item.2.sourceLocation.filename, item.2.sourceLocation),
    lookupPos(line, col, map:lookup(fileName, decls))), eprintlnT(s"${fileName} ${toString(line)} ${toString(col)} ${hackUnparse(map(fst, map:lookup(fileName, decls)))}\n\n", unsafeIO()));
}

function findDeclLocation
[Location] ::= fileName::String line::Integer col::Integer c::Decorated Compilation
{
  return
    lookupDeclLocation(fileName, line, col, c.valueFileRefLocs) ++
    lookupDeclLocation(fileName, line, col, c.typeFileRefLocs) ++
    lookupDeclLocation(fileName, line, col, c.attributeFileRefLocs);
}
