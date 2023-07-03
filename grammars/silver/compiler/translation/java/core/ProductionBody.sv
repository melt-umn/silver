grammar silver:compiler:translation:java:core;


synthesized attribute attrName :: String;

attribute attrName occurs on ForwardLHSExpr;

attribute setupInh, translation, valueWeaving occurs on ProductionBody, ProductionStmts, ProductionStmt;
attribute           translation               occurs on DefLHS, ForwardInhs, ForwardInh;

propagate setupInh, valueWeaving on ProductionBody, ProductionStmts;

synthesized attribute initTransInh :: String occurs on DefLHS;

aspect production productionBody
top::ProductionBody ::= '{' stmts::ProductionStmts '}'
{
  top.translation = stmts.translation;
}

aspect production productionStmtsNil
top::ProductionStmts ::= 
{
  top.translation = "";
}

aspect production productionStmtsSnoc
top::ProductionStmts ::= h::ProductionStmts t::ProductionStmt
{
  top.translation = h.translation ++ t.translation;
}

-------

aspect production productionStmtAppend
top::ProductionStmt ::= h::ProductionStmt t::ProductionStmt
{
  propagate setupInh, valueWeaving;
  top.translation = h.translation ++ t.translation;
}

aspect production errorProductionStmt
top::ProductionStmt ::= e::[Message]
{
  top.translation = "";
}

--------------------------------------------------------------------------------

aspect default production
top::ProductionStmt ::=
{
  -- Always require translation
  propagate setupInh, valueWeaving;
}

aspect production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
  top.translation = e.initTransDecSites;
}

aspect production forwardingWith
top::ProductionStmt ::= 'forwarding' 'with' '{' inh::ForwardInhs '}' ';'
{
  top.translation = inh.translation;   
}

aspect production forwardInh
top::ForwardInh ::= lhs::ForwardLHSExpr '=' e::Expr ';'
{
  top.translation = 
	s"\t\t//${top.unparse}\n" ++
	s"\t\t${top.frame.className}.forwardInheritedAttributes[${lhs.attrName}] = ${wrapLazy(e)};\n";
}

aspect production forwardInhsOne
top::ForwardInhs ::= lhs::ForwardInh
{
  top.translation = lhs.translation;
}

aspect production forwardInhsCons
top::ForwardInhs ::= lhs::ForwardInh rhs::ForwardInhs
{
  top.translation = lhs.translation ++ rhs.translation;
}

aspect production forwardLhsExpr
top::ForwardLHSExpr ::= q::QNameAttrOccur
{
  top.attrName = q.attrOccursInitIndex;
}

aspect production undecoratesTo
top::ProductionStmt ::= 'undecorates' 'to' e::Expr ';'
{
  top.translation = "";
}

aspect production localAttributeDcl
top::ProductionStmt ::= 'local' 'attribute' a::Name '::' te::TypeExpr ';'
{
  local attribute ugh_dcl_hack :: ValueDclInfo;
  ugh_dcl_hack = head(getValueDclAll(fName, top.env)); -- TODO really, we should have a DclInfo for ourselves no problem. but out current approach of constructing it via localDef makes this annoyingly difficult. this suggests a probably environment refactoring...
  
  top.valueWeaving := s"public static final int ${ugh_dcl_hack.attrOccursIndexName} = ${top.frame.prodLocalCountName}++;\n";

  top.setupInh :=
    if isDecorable(te.typerep, top.env)
    then
      s"\t\t//${top.unparse}\n" ++
      if te.typerep.isNonterminal || te.typerep.isUniqueDecorated
      then
        s"\t\t${top.frame.className}.localInheritedAttributes[${ugh_dcl_hack.attrOccursInitIndex}] = new common.Lazy[${makeNTName(te.typerep.typeName)}.num_inh_attrs];\n" ++
        s"\t\t${top.frame.className}.localTransInheritedAttributes[${ugh_dcl_hack.attrOccursInitIndex}] = new common.Lazy[${makeNTName(te.typerep.typeName)}.num_syn_attrs][];\n" ++
        s"\t\t${top.frame.className}.localTransDecSites[${ugh_dcl_hack.attrOccursInitIndex}] = new common.Lazy[${makeNTName(te.typerep.typeName)}.num_syn_attrs];\n"
      else s"\t\t${top.frame.className}.localInheritedAttributes[${ugh_dcl_hack.attrOccursInitIndex}] = new common.Lazy[${top.frame.className}.count_inh__ON__${makeIdName(transTypeNameWith(te.typerep, top.frame.signature.freeVariables))}];\n"
    else "";

  top.setupInh <- s"\t\t${top.frame.className}.occurs_local[${ugh_dcl_hack.attrOccursInitIndex}] = \"${fName}\";\n";

  top.translation = 
    case lookupLocalUniqueRefs(fName, top.flowEnv), lookupLocalRefDecSite(fName, top.flowEnv) of
    | [u], [v] ->
        s"\t\t//${top.unparse}\n" ++
        s"\t\t${top.frame.className}.localDecSites[${ugh_dcl_hack.attrOccursInitIndex}] = " ++
        s"(context) -> ${refAccessTranslation(top.env, top.flowEnv, u.sourceGrammar, top.frame.lhsNtName, v)};\n"
    | _, _ -> ""
    end;
}

aspect production productionAttributeDcl
top::ProductionStmt ::= 'production' 'attribute' a::Name '::' te::TypeExpr ';'
{
  local attribute ugh_dcl_hack :: ValueDclInfo;
  ugh_dcl_hack = head(getValueDclAll(fName, top.env)); -- TODO really, we should have a DclInfo for ourselves no problem. but out current approach of constructing it via localDef makes this annoyingly difficult. this suggests a probably environment refactoring...
  
  top.valueWeaving := s"public static final int ${ugh_dcl_hack.attrOccursIndexName} = ${top.frame.prodLocalCountName}++;\n";

  top.setupInh :=
    if isDecorable(te.typerep, top.env)
    then
      s"\t\t//${top.unparse}\n" ++
      if te.typerep.isNonterminal || te.typerep.isUniqueDecorated
      then
        s"\t\t${top.frame.className}.localInheritedAttributes[${ugh_dcl_hack.attrOccursInitIndex}] = new common.Lazy[${makeNTName(te.typerep.typeName)}.num_inh_attrs];\n" ++
        s"\t\t${top.frame.className}.localTransInheritedAttributes[${ugh_dcl_hack.attrOccursInitIndex}] = new common.Lazy[${makeNTName(te.typerep.typeName)}.num_syn_attrs][];\n" ++
        s"\t\t${top.frame.className}.localTransDecSites[${ugh_dcl_hack.attrOccursInitIndex}] = new common.Lazy[${makeNTName(te.typerep.typeName)}.num_syn_attrs];\n"
      else s"\t\t${top.frame.className}.localInheritedAttributes[${ugh_dcl_hack.attrOccursInitIndex}] = new common.Lazy[${top.frame.className}.count_inh__ON__${makeIdName(transTypeNameWith(te.typerep, top.frame.signature.freeVariables))}];\n"
    else "";

  top.setupInh <- s"\t\t${top.frame.className}.occurs_local[${ugh_dcl_hack.attrOccursInitIndex}] = \"${fName}\";\n";

  top.translation = 
    case lookupLocalUniqueRefs(fName, top.flowEnv), lookupLocalRefDecSite(fName, top.flowEnv) of
    | [u], [v] ->
        s"\t\t//${top.unparse}\n" ++
        s"\t\t${top.frame.className}.localDecSites[${ugh_dcl_hack.attrOccursInitIndex}] = " ++
        s"(context) -> ${refAccessTranslation(top.env, top.flowEnv, u.sourceGrammar, top.frame.lhsNtName, v)};\n"
    | _, _ -> ""
    end;
}

aspect production forwardProductionAttributeDcl
top::ProductionStmt ::= 'forward' 'production' 'attribute' a::Name ';'
{
  local attribute ugh_dcl_hack :: ValueDclInfo;
  ugh_dcl_hack = head(getValueDclAll(fName, top.env)); -- TODO really, we should have a DclInfo for ourselves no problem. but out current approach of constructing it via localDef makes this annoyingly difficult. this suggests a probably environment refactoring...
  
  top.valueWeaving := s"public static final int ${ugh_dcl_hack.attrOccursIndexName} = ${top.frame.prodLocalCountName}++;\n";

  top.setupInh :=
    s"\t\t//${top.unparse}\n" ++
    s"\t\t${top.frame.className}.localIsForward[${ugh_dcl_hack.attrOccursInitIndex}] = true;\n" ++ 
    s"\t\t${top.frame.className}.localInheritedAttributes[${ugh_dcl_hack.attrOccursInitIndex}] = new common.Lazy[${makeNTName(top.frame.lhsNtName)}.num_inh_attrs];\n" ++
    s"\t\t${top.frame.className}.localTransInheritedAttributes[${ugh_dcl_hack.attrOccursInitIndex}] = new common.Lazy[${makeNTName(top.frame.lhsNtName)}.num_syn_attrs][];\n" ++
    s"\t\t${top.frame.className}.localTransDecSites[${ugh_dcl_hack.attrOccursInitIndex}] = new common.Lazy[${makeNTName(top.frame.lhsNtName)}.num_syn_attrs];\n";

  top.setupInh <- s"\t\t${top.frame.className}.occurs_local[${ugh_dcl_hack.attrOccursInitIndex}] = \"${fName}\";\n";

  -- Decoration through a remote reference has no effect, since all inhs are supplied here via a forward parent
  top.translation = "";
}

aspect default production
top::DefLHS ::=
{
  top.initTransInh = "";
}

aspect production childDefLHS
top::DefLHS ::= q::Decorated! QName
{
  top.translation = s"${top.frame.className}.childInheritedAttributes[${top.frame.className}.i_${q.lookupValue.fullName}]";
}

aspect production lhsDefLHS
top::DefLHS ::= q::Decorated! QName
{
  top.translation = s"${top.frame.className}.synthesizedAttributes";
}

aspect production localDefLHS
top::DefLHS ::= q::Decorated! QName
{
  top.translation = s"${top.frame.className}.localInheritedAttributes[${q.lookupValue.dcl.attrOccursIndex}]";
}

aspect production forwardDefLHS
top::DefLHS ::= q::Decorated! QName
{
  top.translation = s"${top.frame.className}.forwardInheritedAttributes";
}

aspect production errorDefLHS
top::DefLHS ::= q::Decorated! QName
{
  top.translation = error("Internal compiler error: translation not defined in the presence of errors");
}

aspect production childTransAttrDefLHS
top::DefLHS ::= q::Decorated! QName  attr::Decorated! QNameAttrOccur
{
  top.translation = s"${top.frame.className}.childTransInheritedAttributes[${top.frame.className}.i_${q.lookupValue.fullName}][${attr.attrOccursIndex}]";
  top.initTransInh = s"if (${top.translation} == null) ${top.translation} = new common.Lazy[${makeNTName(attr.typerep.typeName)}.num_inh_attrs];\n";
}

aspect production localTransAttrDefLHS
top::DefLHS ::= q::Decorated! QName  attr::Decorated! QNameAttrOccur
{
  top.translation = s"${top.frame.className}.localTransInheritedAttributes[${q.lookupValue.dcl.attrOccursIndex}][${attr.attrOccursIndex}]";
  top.initTransInh = s"if (${top.translation} == null) ${top.translation} = new common.Lazy[${makeNTName(attr.typerep.typeName)}.num_inh_attrs];\n";
}

aspect production errorTransAttrDefLHS
top::DefLHS ::= q::Decorated! QName  attr::Decorated! QNameAttrOccur
{
  top.translation = error("Internal compiler error: translation not defined in the presence of errors");
}

aspect production errorAttributeDef
top::ProductionStmt ::= msg::[Message] dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  e::Expr
{
  top.translation = error("Internal compiler error: translation not defined in the presence of errors");
}

aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  e::Expr
{
  top.translation = 
    s"\t\t// ${dl.unparse}.${attr.unparse} = ${e.unparse}\n" ++
    dl.initTransInh ++ e.initTransDecSites ++
    s"\t\t${dl.translation}[${attr.attrOccursInitIndex}] = ${wrapLazy(e)};\n";
}

aspect production inheritedAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  e::Expr
{
  top.translation = 
    s"\t\t// ${dl.unparse}.${attr.unparse} = ${e.unparse}\n" ++
    dl.initTransInh ++
    s"\t\t${dl.translation}[${attr.attrOccursInitIndex}] = ${wrapLazy(e)};\n";
}


aspect production errorValueDef
top::ProductionStmt ::= val::Decorated! QName  e::Expr
{
  top.translation = error("Internal compiler error: translation not defined in the presence of errors");
}

aspect production localValueDef
top::ProductionStmt ::= val::Decorated! QName  e::Expr
{
  top.translation =
	s"\t\t// ${val.unparse} = ${e.unparse}\n" ++
  e.initTransDecSites ++
	s"\t\t${top.frame.className}.localAttributes[${val.lookupValue.dcl.attrOccursInitIndex}] = ${wrapLazy(e)};\n";
}

aspect production returnDef
top::ProductionStmt ::= 'return' e::Expr ';'
{
  top.translation = "";
}

aspect production attachNoteStmt
top::ProductionStmt ::= 'attachNote' note::Expr ';'
{
  top.translation = "";
}