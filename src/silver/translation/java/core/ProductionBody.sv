grammar silver:translation:java:core;
import silver:definition:core;
import silver:definition:env;
import silver:util;

synthesized attribute nodeName :: String;
synthesized attribute attrName :: String;
synthesized attribute isLocal :: Boolean;
synthesized attribute isLocalDcl :: Boolean;
synthesized attribute isChild :: Boolean;
synthesized attribute isParent :: Boolean;

attribute nodeName, attrName, isLocal, isLocalDcl, isChild, isParent occurs on LHSExpr;
attribute attrName occurs on ForwardLHSExpr;

attribute setupInh, translation occurs on ProductionBody, ProductionStmts, ProductionStmt, ForwardsToDcl, ForwardingWithDcl, LocalAttributeDcl, ProductionAttributeDcl, AttributeDef, ForwardInhs, ForwardInh, ReturnDef;

aspect production defaultProductionBody
top::ProductionBody ::= stmts::ProductionStmts
{
  top.setupInh = stmts.setupInh;
  top.translation = stmts.translation;
}

aspect production productionStmtsNone
top::ProductionStmts ::= 
{
  top.setupInh = "";
  top.translation = "";
}

aspect production productionStmts
top::ProductionStmts ::= stmt::ProductionStmt
{
  top.setupInh = stmt.setupInh;
  top.translation = stmt.translation;
}

aspect production productionStmtsCons
top::ProductionStmts ::= h::ProductionStmt t::ProductionStmts
{
  top.setupInh = h.setupInh ++ t.setupInh;
  top.translation = h.translation ++ t.translation;
}

aspect production productionStmtsAppend
top::ProductionStmts ::= h::ProductionStmts t::ProductionStmts
{
  top.setupInh = h.setupInh ++ t.setupInh;
  top.translation = h.translation ++ t.translation;
}

aspect production productionStmtAttributeDef
top::ProductionStmt ::= a::AttributeDef
{
  top.setupInh = a.setupInh;
  top.translation = a.translation;
}

aspect production productionStmtReturnDef
top::ProductionStmt ::= a::ReturnDef
{
  top.setupInh = a.setupInh;
  top.translation = a.translation;
}

aspect production productionStmtLocalAttribute
top::ProductionStmt ::= a::LocalAttributeDcl
{
  top.setupInh = a.setupInh;
  top.translation = a.translation;
}

aspect production productionStmtProductionAttribute
top::ProductionStmt ::= a::ProductionAttributeDcl
{
  top.setupInh = a.setupInh;
  top.translation = a.translation;
}

aspect production productionStmtForwardsTo
top::ProductionStmt ::= a::ForwardsToDcl
{
  top.setupInh = a.setupInh;
  top.translation = a.translation;
}

aspect production productionStmtForwardingWith
top::ProductionStmt ::= a::ForwardingWithDcl
{
  top.setupInh = a.setupInh;
  top.translation = a.translation;
}

aspect production forwardsTo
top::ForwardsToDcl ::= 'forwards' 'to' e::Expr ';'
{
  local attribute className :: String;
  className = makeClassName(top.signature.fullName);

  top.setupInh = "";
  top.translation = 
	"		//" ++ top.pp ++ "\n" ++
	"		" ++ className ++ ".forward = new common.Lazy(){\n" ++ 
	"			public Object eval(common.DecoratedNode context) {\n" ++
	"				return " ++ e.translation ++ ";\n" ++
	"			}\n" ++
	"		};\n";
}

aspect production forwardsToWith
top::ForwardsToDcl ::= 'forwards' 'to' e::Expr 'with' '{' inh::ForwardInhs '}' ';'
{
  local attribute className :: String;
  className = makeClassName(top.signature.fullName);

  top.setupInh = "";
  top.translation =
	"		//" ++ top.pp ++ "\n" ++
	"		" ++ className ++ ".forward = new common.Lazy(){\n" ++ 
	"			public Object eval(common.DecoratedNode context) {\n" ++
	"				return " ++ e.translation ++ ";\n" ++
	"			}\n" ++
	"		};\n" ++
  	inh.translation;
}

aspect production forwardingWith
top::ForwardingWithDcl ::= 'forwarding' 'with' '{' inh::ForwardInhs '}' ';'
{
  top.setupInh = "";
  top.translation = inh.translation;   
}

aspect production forwardInh
top::ForwardInh ::= lhs::ForwardLHSExpr '=' e::Expr ';'
{
  local attribute className :: String;
  className = makeClassName(top.signature.fullName);

  top.translation = 
	"		//" ++ top.pp ++ "\n" ++
	"		" ++ className ++ ".forwardAttributes.put(\"" ++ lhs.attrName ++ "\", new common.Lazy(){\n" ++ 
	"			public Object eval(common.DecoratedNode context) {\n" ++
	"				return " ++ e.translation ++ ";\n" ++
	"			}\n" ++
	"		});\n";

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
  top.attrName = fName;  
}

aspect production localAttributeDcl
top::LocalAttributeDcl ::= 'local' 'attribute' a::Name '::' te::Type ';'
{
  top.setupInh = if !te.typerep.isNonTerminal then  "" else
        	 "		//" ++ top.pp ++ "\n" ++
		 "		" ++ 
		 makeClassName(top.signature.fullName) ++ ".inheritedAttributes.put(\"" ++ fName ++ "\", " ++ 
										    "new java.util.HashMap<String, common.Lazy>());\n";
  top.translation = "";
}

aspect production productionAttributeDcl
top::ProductionAttributeDcl ::= 'production' 'attribute' a::Name '::' te::Type ';'
{
  top.setupInh = if !te.typerep.isNonTerminal then  "" else
	   	"		//" ++ top.pp ++ "\n" ++		 
		"		" ++ 
		 makeClassName(top.signature.fullName) ++ ".inheritedAttributes.put(\"" ++ fName ++ "\", " ++ 
										    "new java.util.HashMap<String, common.Lazy>());\n";
  top.translation = "";
}

aspect production attributeDef
top::AttributeDef ::= lhs::LHSExpr '=' e::Expr ';'
{
  local attribute className :: String;
  className = makeClassName(top.signature.fullName);

  top.setupInh = "";
  top.translation =
	"		//" ++ top.pp ++ "\n" ++
	if lhs.isLocalDcl then  
	"		" ++ className ++ ".localAttributes.put(\"" ++ lhs.nodeName ++ "\", new common.Lazy(){\n" ++ 
	"			public Object eval(common.DecoratedNode context) {\n" ++
	"				return " ++ e.translation ++ ";\n" ++
	"			}\n" ++
	"		});\n"
	else if lhs.isLocal then 
	"		" ++ className ++ ".inheritedAttributes.get(\"" ++ lhs.nodeName ++ "\").put(\"" ++ lhs.attrName ++ "\", new common.Lazy(){\n" ++ 
	"			public Object eval(common.DecoratedNode context) {\n" ++
	"				return " ++ e.translation ++ ";\n" ++
	"			}\n" ++
	"		});\n"
	else if lhs.isChild then 
	"		" ++ className ++ ".inheritedAttributes.get(" ++ className ++ ".i_" ++ lhs.nodeName ++ ").put(\"" ++ lhs.attrName ++ "\", new common.Lazy(){\n" ++ 
	"			public Object eval(common.DecoratedNode context) {\n" ++
	"				return " ++ e.translation ++ ";\n" ++
	"			}\n" ++
	"		});\n"

	else
	"		" ++ className ++ ".synthesizedAttributes.put(\"" ++ lhs.attrName ++ "\", new common.Lazy(){\n" ++ 
	"			public Object eval(common.DecoratedNode context) {\n" ++
	"				return " ++ e.translation ++ ";\n" ++
	"			}\n" ++
	"		});\n";
}

aspect production returnDef
top::ReturnDef ::= 'return' e::Expr ';'
{
  local attribute className :: String;
  className = makeClassName(top.signature.fullName);

  top.setupInh = "";
  top.translation =
	"		//" ++ top.pp ++ "\n" ++
	"		" ++ className ++ ".synthesizedAttributes.put(\"__return\", new common.Lazy(){\n" ++ 
	"			public Object eval(common.DecoratedNode context) {\n" ++
	"				return " ++ e.translation ++ ";\n" ++
	"			}\n" ++
	"		});\n";
}

aspect production fakeLHSExpr
top::LHSExpr ::= c1::QName c2::Decorated TypeRep
{
  top.nodeName = "_NULL_";
  top.attrName = c1.name;

  top.isLocal = false;
  top.isLocalDcl = false;
  top.isChild = false;
  top.isParent = false;
}

aspect production lhsExprOne
top::LHSExpr ::= id::Name
{
  top.nodeName = fName;
  top.attrName = "_NULL_";

  top.isLocal = false;
  top.isLocalDcl = true;
  top.isChild = false;
  top.isParent = false;
}

aspect production lhsExprTwo
top::LHSExpr ::= id::Name '.' q::QName
{
  top.nodeName = fName1;
  top.attrName = fName2;

  top.isLocal = !null(getValueDcl(fName1, top.localsEnv));
  top.isLocalDcl = false;
  top.isChild = contains(id.name, getNamesSignature(top.signature.inputElements));
  top.isParent = id.name == top.signature.outputElement.elementName;
}

