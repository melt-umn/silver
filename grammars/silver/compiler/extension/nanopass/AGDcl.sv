grammar silver:compiler:extension:nanopass;

monoid attribute includedGrammars :: [String] occurs on AGDcls, AGDcl;
propagate includedGrammars on AGDcls, AGDcl;

monoid attribute includeTransDcls :: ([AGDcl] ::= Decorated TransformStmts) occurs on AGDcls, AGDcl;
propagate includeTransDcls on AGDcls;

aspect includeTransDcls on top::AGDcl using := of
| appendAGDcl(h, t) -> append(h.includeTransDcls, t.includeTransDcls)
| attributionDcl(_, at, attl, _, _, nt, nttl, _) -> transDclsWhenIncluded(top, _)
| _ -> transDclsWhenIncluded(top, _)
end;

fun transDclsWhenIncluded [AGDcl] ::= top::Decorated AGDcl tr::Decorated TransformStmts =
  if top.isIncluded(tr) then [top.includeTrans(tr)] else [];

synthesized attribute isIncluded :: (Boolean ::= Decorated TransformStmts)
  occurs on AGDcl;
aspect isIncluded on top::AGDcl of
| nonterminalDcl(_, _, n, _, _, _) -> isTypeIncluded(_, fName)
| annotationDcl(_, n, _, _, _, _) -> isAttributeIncluded(_, fName)
| attributeDclInh(_, _, n, _, _, _, _) -> isAttributeIncluded(_, fName)
| attributeDclSyn(_, _, n, _, _, _, _) -> isAttributeIncluded(_, fName)
| translationPassAttrDcl(_, _, _, _, _, _, _) -> ff
| translationPassExcludingDcl(_, _, _, _, _, _, _, _, _, _) -> ff
| attributeDclTrans(_, _, n, _, _, _, _) -> isAttributeIncluded(_, fName)
| attributionDcl(_, at, _, _, _, nt, _, _) ->
  isAttributeIncluded(_, at.lookupAttribute.fullName) &&
  isTypeIncluded(_, nt.lookupType.fullName)
| productionDcl(_, _, n, _, _, _) -> isValueIncluded(_, fName)
| functionDcl(_, n, _, _) -> isValueIncluded(_, fName)
| aspectProductionDcl(_, _, n, _, _) -> isValueIncluded(_, n.lookupValue.fullName)
| aspectFunctionDcl(_, _, n, _, _) -> isValueIncluded(_, n.lookupValue.fullName)
| aspectDefaultProduction(_, _, _, ns, _) -> ns.namedSignature.outputElement.typerep.isIncluded
| typeClassDcl(_, _, _, n, _, _, _, _) -> isTypeIncluded(_, fName)
| instanceDcl(_, _, _, n, ty, _, _, _) ->
  isTypeIncluded(_, n.lookupType.fullName) &&
  ty.isIncluded
| globalValueDclConcrete(_, _, n, _, _, _, _, _, _) -> isValueIncluded(_, fName)
| shortFunctionDcl(_, n, _, _, _, _) -> isValueIncluded(_, fName)
| dispatchSigDcl(_, n, _, _, _) -> isTypeIncluded(_, fName)
| collectionAttributeDclSyn(_, _, n, _, _, _, _, _, _) -> isAttributeIncluded(_, fName)
| collectionAttributeDclInh(_, _, n, _, _, _, _, _, _) -> isAttributeIncluded(_, fName)
| flowtypeDcl(_, _, _, _, _) -> ff
| flowtypeAttrDcl(_, _, _, _, _) -> ff
| terminalDclDefault(_, _, _, _) -> ff
| _ -> ff
end;

functor attribute includeTrans ::= Decorated TransformStmts occurs on
  AGDcl,
  ProductionImplements, ProductionSignature, ProductionLHS, ProductionRHS, ProductionRHSElem,
  AspectProductionSignature, AspectProductionLHS, AspectFunctionSignature, AspectFunctionLHS, AspectRHS, AspectRHSElem,
  AspectDefaultProductionSignature,
  ClassBody, ClassBodyItem, InstanceBody, InstanceBodyItem;
propagate includeTrans on AGDcl,
  ProductionImplements, ProductionSignature, ProductionLHS, ProductionRHS, ProductionRHSElem,
  AspectProductionSignature, AspectProductionLHS, AspectFunctionSignature, AspectFunctionLHS, AspectRHS, AspectRHSElem,
  AspectDefaultProductionSignature,
  ClassBody, ClassBodyItem, InstanceBody, InstanceBodyItem
excluding nonterminalDcl, defaultAttributionDcl, errorAttributionDcl, aspectProductionDcl, aspectFunctionDcl, instanceDcl;

aspect includeTrans on top::AGDcl of
| nonterminalDcl(q, _, n, tl, nm, _) -> \ tr::Decorated TransformStmts ->
  if ts:contains(fName, tr.closedNonterminals) && !q.closed
  then nonterminalDcl(closedNTQualifier('closed', ^q), 'nonterminal', ^n, ^tl, ^nm, ';')
  else if ts:contains(fName, tr.openedNonterminals) && q.closed
  then nonterminalDcl(q.removeClosed, 'nonterminal', ^n, ^tl, ^nm, ';')
  else nonterminalDcl(^q, 'nonterminal', ^n, ^tl, ^nm, ';')
| attributionDcl(_, at, attl, _, _, nt, nttl, _) -> \ tr::Decorated TransformStmts ->
  attributionDcl('attribute',
    qName(unqualifyIfSameGrammar(top.grammarName, at.lookupAttribute.fullName)),
    attl.includeTrans(tr),
    'occurs', 'on',
    qName(unqualifyIfSameGrammar(top.grammarName, nt.lookupType.fullName)),
    nttl.includeTrans(tr), ';')
| defaultAttributionDcl(_, _, _, _) -> error("includeTrans should not be demanded on defaultAttributionDcl")
| errorAttributionDcl(_, _, _, _, _) -> error("includeTrans should not be demanded on errorAttributionDcl")
| aspectProductionDcl(_, _, n, s, b) -> \ tr::Decorated TransformStmts ->
  aspectProductionDcl('aspect', 'production',
    qName(unqualifyIfSameGrammar(top.grammarName, n.lookupValue.fullName)),
    s.includeTrans(tr), b.includeTrans(tr))
| aspectFunctionDcl(_, _, n, s, b) -> \ tr::Decorated TransformStmts ->
  aspectFunctionDcl('aspect', 'function',
    qName(unqualifyIfSameGrammar(top.grammarName, n.lookupValue.fullName)),
    s.includeTrans(tr), b.includeTrans(tr))
| instanceDcl(_, cl, _, n, ty, _, b, _) -> \ tr::Decorated TransformStmts ->
  instanceDcl('instance', cl.includeTrans(tr), '=>',
    qName(unqualifyIfSameGrammar(top.grammarName, n.lookupType.fullName)).qNameType,
    ty.includeTrans(tr), '{', b.includeTrans(tr), '}')
end;

functor attribute removeClosed occurs on NTDeclQualifiers;
propagate removeClosed on NTDeclQualifiers excluding closedNTQualifier;
aspect removeClosed on top::NTDeclQualifiers of
| closedNTQualifier(_, rest) -> rest.removeClosed
end;

fun shortNameOf String ::= fn::String =
  substring(lastIndexOf(":", fn) + 1, length(fn), fn);
fun grammarNameOf String ::= fn::String =
  substring(0, lastIndexOf(":", fn), fn);
fun unqualifyIfSameGrammar String ::= gn::String fn::String =
  if grammarNameOf(fn) == gn then shortNameOf(fn) else fn;

fun isTypeIncluded Boolean ::= tr::Decorated TransformStmts fn::String =
  !ts:contains(fn, tr.excludedTypes);
fun isValueIncluded Boolean ::= tr::Decorated TransformStmts fn::String =
  !ts:contains(fn, tr.excludedValues);
fun isAttributeIncluded Boolean ::= tr::Decorated TransformStmts fn::String =
  !ts:contains(fn, tr.excludedAttributes);
