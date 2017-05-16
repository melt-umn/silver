grammar silver:translation:java:core;

import silver:util;

synthesized attribute attrName :: String;

attribute attrName occurs on ForwardLHSExpr;

attribute setupInh, translation, valueWeaving occurs on ProductionBody, ProductionStmts, ProductionStmt;
attribute           translation               occurs on DefLHS, ForwardInhs, ForwardInh;

aspect production productionBody
top::ProductionBody ::= '{' stmts::ProductionStmts '}'
{
  top.setupInh := stmts.setupInh;
  top.translation = stmts.translation;
  top.valueWeaving := stmts.valueWeaving;
}

aspect production productionStmtsNil
top::ProductionStmts ::= 
{
  top.setupInh := "";
  top.translation = "";
  top.valueWeaving := "";
}

aspect production productionStmtsSnoc
top::ProductionStmts ::= h::ProductionStmts t::ProductionStmt
{
  top.setupInh := h.setupInh ++ t.setupInh;
  top.translation = h.translation ++ t.translation;
  top.valueWeaving := h.valueWeaving ++ t.valueWeaving;
}

-------

aspect production productionStmtAppend
top::ProductionStmt ::= h::ProductionStmt t::ProductionStmt
{
  top.setupInh := h.setupInh ++ t.setupInh;
  top.translation = h.translation ++ t.translation;
  top.valueWeaving := h.valueWeaving ++ t.valueWeaving;
}

aspect production errorProductionStmt
top::ProductionStmt ::= e::[Message]
{
  top.setupInh := "";
  top.translation = "";
  top.valueWeaving := "";
}

--------------------------------------------------------------------------------

aspect default production
top::ProductionStmt ::=
{
  top.setupInh := "";
  -- require a translation.
  top.valueWeaving := "";
}

aspect production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
  top.translation = "";
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
	"\t\t//" ++ top.pp ++ "\n" ++
	"\t\t" ++ top.frame.className ++ ".forwardInheritedAttributes[" ++ lhs.attrName ++ "] = " ++ wrapLazy(e) ++ ";\n";

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
  top.attrName = q.dcl.attrOccursIndex;
}

aspect production localAttributeDcl
top::ProductionStmt ::= 'local' 'attribute' a::Name '::' te::Type ';'
{
  local attribute ugh_dcl_hack :: DclInfo;
  ugh_dcl_hack = head(getValueDclAll(fName, top.env)); -- TODO really, we should have a DclInfo for ourselves no problem. but out current approach of constructing it via localDef makes this annoyingly difficult. this suggests a probably environment refactoring...
  
  top.valueWeaving := "public static final int " ++ ugh_dcl_hack.attrOccursIndexName ++ " = " ++ top.frame.prodLocalCountName ++ "++;\n";

  top.setupInh := 
    if !te.typerep.isDecorable then  "" else
    "\t\t//" ++ top.pp ++ "\n" ++
    "\t\t" ++ top.frame.className ++ ".localInheritedAttributes[" ++ ugh_dcl_hack.attrOccursIndex ++ "] = " ++ 
      "new common.Lazy[" ++ makeNTClassName(te.typerep.typeName) ++ ".num_inh_attrs];\n";

  top.setupInh <- "\t\t" ++ top.frame.className ++ ".occurs_local[" ++ ugh_dcl_hack.attrOccursIndex ++ "] = \"" ++ fName ++ "\";\n";

  top.translation = "";
}

aspect production childDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.translation = top.frame.className ++ ".childInheritedAttributes[" ++ top.frame.className ++ ".i_" ++ q.lookupValue.fullName ++ "]";
}

aspect production lhsDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.translation = top.frame.className ++ ".synthesizedAttributes";
}

aspect production localDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.translation = top.frame.className ++ ".localInheritedAttributes[" ++ q.lookupValue.dcl.attrOccursIndex ++ "]";
}

aspect production forwardDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.translation = top.frame.className ++ ".forwardInheritedAttributes";
}

aspect production errorDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.translation = error("Internal compiler error: translation not defined in the presence of errors");
}

aspect production errorAttributeDef
top::ProductionStmt ::= msg::[Message] dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  top.translation = error("Internal compiler error: translation not defined in the presence of errors");
}

aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  top.translation = 
    "\t\t// " ++ dl.pp ++ "." ++ attr.pp ++ " = " ++ e.pp ++ "\n" ++
    "\t\t" ++ dl.translation ++ "[" ++ attr.dcl.attrOccursIndex ++ "] = " ++ wrapLazy(e) ++ ";\n";
}

aspect production inheritedAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  top.translation = 
    "\t\t// " ++ dl.pp ++ "." ++ attr.pp ++ " = " ++ e.pp ++ "\n" ++
    "\t\t" ++ dl.translation ++ "[" ++ attr.dcl.attrOccursIndex ++ "] = " ++ wrapLazy(e) ++ ";\n";
}


aspect production errorValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
{
  top.translation = error("Internal compiler error: translation not defined in the presence of errors");
}

aspect production localValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
{
  top.translation =
	"\t\t// " ++ val.pp ++ " = " ++ e.pp ++ "\n" ++
	"\t\t" ++ top.frame.className ++ ".localAttributes[" ++ val.lookupValue.dcl.attrOccursIndex ++ "] = " ++ wrapLazy(e) ++ ";\n";
}

aspect production returnDef
top::ProductionStmt ::= 'return' e::Expr ';'
{
  top.translation = "";
}

