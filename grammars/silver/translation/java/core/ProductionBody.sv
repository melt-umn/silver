grammar silver:translation:java:core;

import silver:util;

synthesized attribute attrName :: String;

attribute attrName occurs on ForwardLHSExpr;

attribute setupInh, translation occurs on ProductionBody, ProductionStmts, ProductionStmt;
attribute           translation occurs on DefLHS, ForwardInhs, ForwardInh;

aspect production defaultProductionBody
top::ProductionBody ::= stmts::ProductionStmts
{
  top.setupInh := stmts.setupInh;
  top.translation = stmts.translation;
}

aspect production productionStmtsNone
top::ProductionStmts ::= 
{
  top.setupInh := "";
  top.translation = "";
}

aspect production productionStmts
top::ProductionStmts ::= stmt::ProductionStmt
{
  top.setupInh := stmt.setupInh;
  top.translation = stmt.translation;
}

aspect production productionStmtsCons
top::ProductionStmts ::= h::ProductionStmt t::ProductionStmts
{
  top.setupInh := h.setupInh ++ t.setupInh;
  top.translation = h.translation ++ t.translation;
}

aspect production productionStmtsAppend
top::ProductionStmts ::= h::ProductionStmts t::ProductionStmts
{
  top.setupInh := h.setupInh ++ t.setupInh;
  top.translation = h.translation ++ t.translation;
}

aspect production productionStmtAppend
top::ProductionStmt ::= h::ProductionStmt t::ProductionStmt
{
  top.setupInh := h.setupInh ++ t.setupInh;
  top.translation = h.translation ++ t.translation;
}

--------------------------------------------------------------------------------

aspect production defaultProductionStmt
top::ProductionStmt ::=
{
  top.setupInh := "";
  -- require a translation.
}

aspect production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
  local attribute className :: String;
  className = makeClassName(top.signature.fullName);

  top.translation = 
	"\t\t//" ++ top.pp ++ "\n" ++
	"\t\t" ++ className ++ ".forward = " ++ wrapLazy(e) ++ ";\n";
}

aspect production forwardsToWith
top::ProductionStmt ::= 'forwards' 'to' e::Expr 'with' '{' inh::ForwardInhs '}' ';'
{
  local attribute className :: String;
  className = makeClassName(top.signature.fullName);

  top.translation =
	"\t\t//" ++ top.pp ++ "\n" ++
	"\t\t" ++ className ++ ".forward = " ++ wrapLazy(e) ++ ";\n" ++
  	inh.translation;
}

aspect production forwardingWith
top::ProductionStmt ::= 'forwarding' 'with' '{' inh::ForwardInhs '}' ';'
{
  top.translation = inh.translation;   
}

aspect production forwardInh
top::ForwardInh ::= lhs::ForwardLHSExpr '=' e::Expr ';'
{
  local attribute className :: String;
  className = makeClassName(top.signature.fullName);

  top.translation = 
	"\t\t//" ++ top.pp ++ "\n" ++
	"\t\t" ++ className ++ ".forwardInheritedAttributes[" ++ lhs.attrName ++ "] = " ++ wrapLazy(e) ++ ";\n";

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
top::ForwardLHSExpr ::= q::QName
{
  top.attrName = occursCheck.dcl.attrOccursIndex;
}

aspect production localAttributeDcl
top::ProductionStmt ::= 'local' 'attribute' a::Name '::' te::Type ';'
{
  top.setupInh := if !te.typerep.mayBeSuppliedInhAttrs then  "" else
        	 "\t\t//" ++ top.pp ++ "\n" ++
		 "\t\t" ++ 
		 makeClassName(top.signature.fullName) ++ ".localInheritedAttributes.put(\"" ++ fName ++ "\", " ++ 
										    "new common.Lazy[" ++ makeNTClassName(te.typerep.typeName) ++ ".num_inh_attrs]);\n";
  top.translation = "";
}

aspect production productionAttributeDcl
top::ProductionStmt ::= 'production' 'attribute' a::Name '::' te::Type ';'
{
  top.setupInh := if !te.typerep.mayBeSuppliedInhAttrs then  "" else
	   	"\t\t//" ++ top.pp ++ "\n" ++		 
		"\t\t" ++ 
		 makeClassName(top.signature.fullName) ++ ".localInheritedAttributes.put(\"" ++ fName ++ "\", " ++ 
										    "new common.Lazy[" ++ makeNTClassName(te.typerep.typeName) ++ ".num_inh_attrs]);\n";
  top.translation = "";
}

aspect production childDefLHS
top::DefLHS ::= q::Decorated QName
{
  local attribute className :: String;
  className = makeClassName(top.signature.fullName);

  top.translation = className ++ ".childInheritedAttributes[" ++ className ++ ".i_" ++ q.lookupValue.fullName ++ "]";
}

aspect production lhsDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.translation = makeClassName(top.signature.fullName) ++ ".synthesizedAttributes";
}

aspect production localDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.translation = makeClassName(top.signature.fullName) ++ ".localInheritedAttributes.get(\"" ++ q.lookupValue.fullName ++ "\")";
}

aspect production forwardDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.translation = makeClassName(top.signature.fullName) ++ ".forwardInheritedAttributes";
}

aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  top.translation = 
	"\t\t// " ++ dl.pp ++ "." ++ attr.pp ++ " = " ++ e.pp ++ "\n" ++
        "\t\t" ++ dl.translation ++ "[" ++ occursCheck.dcl.attrOccursIndex ++ "] = " ++ wrapLazy(e) ++ ";\n";
}

aspect production inheritedAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  top.translation = 
	"\t\t// " ++ dl.pp ++ "." ++ attr.pp ++ " = " ++ e.pp ++ "\n" ++
        "\t\t" ++ dl.translation ++ "[" ++ occursCheck.dcl.attrOccursIndex ++ "] = " ++ wrapLazy(e) ++ ";\n";
}


aspect production localValueDef
top::ProductionStmt ::= val::Decorated QName '=' e::Expr
{
  local attribute className :: String;
  className = makeClassName(top.signature.fullName);

  top.translation =
	"\t\t// " ++ val.pp ++ " = " ++ e.pp ++ "\n" ++
	"\t\t" ++ className ++ ".localAttributes.put(\"" ++ val.lookupValue.fullName ++ "\", " ++ wrapLazy(e) ++ ");\n";
}

aspect production returnDef
top::ProductionStmt ::= 'return' e::Expr ';'
{
  local attribute className :: String;
  className = makeClassName(top.signature.fullName);

  top.translation =
	"\t\t//" ++ top.pp ++ "\n" ++
	"\t\t" ++ className ++ ".synthesizedAttributes[0] = " ++ wrapLazy(e) ++ ";\n";
}

