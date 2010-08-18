grammar silver:definition:env;
import silver:definition:regex;  -- soley for Terms. TODO : fix?
import silver:definition:type;

synthesized attribute sourceGrammar :: String;
synthesized attribute sourceLocation :: Decorated Location;
synthesized attribute fullName :: String;
synthesized attribute unparse :: String;

-- types
synthesized attribute typerep :: TypeExp;


synthesized attribute namedSignature :: Decorated NamedSignature;

{-
-- values
synthesized attribute refDispatcher :: Production(Expr ::= Decorated QName);
synthesized attribute defDispatcher :: Production(ProductionStmt ::= Decorated QName  Equal_t  Expr);
synthesized attribute defLHSDispatcher :: Production (DefLHS ::= Decorated QName);

-- attributes
synthesized attribute attrAccessDispatcher :: Production (Expr ::= Decorated Expr '.' Decorated QName);
synthesized attribute attrDefDispatcher :: Production (ProductionStmt ::= DefLHS '.' Decorated QName Equal_t Expr);
-}

-- occurs
synthesized attribute attrOccurring :: String;

-- production attribute
synthesized attribute attrDcl :: Decorated DclInfo;


-- on TYPEREP:
-- synthesized attribute applicationDispatcher :: Production (Expr ::= Decorated Expr Exprs);
-- synthesized attribute accessDispatcher :: Production (Expr ::= Decorated Expr '.' Decorated QName);

{- Algorithms:

  Expr.QName     accessDispatcher on Expr.typerep.  NT will dispatch on QName.attrAccessDispatcher.
  
  Expr(Exprs)    applicationDispatcher on Expr.typerep.
  
  QName          refDispatcher on QName
  
  QName = Expr   defDispatcher on QName
  
  DefLHS . QName = Expr   attrDefDispatcher. Give isInherited/isSynthesized to DefLHS (which is gotten via defLHSDispatcher)
  
-}

nonterminal DclInfo with sourceGrammar, sourceLocation, fullName, unparse, typerep, namedSignature, attrOccurring, attrDcl;

abstract production defaultDcl
top::DclInfo ::=
{
  -- This space intentionally left blank.
  -- All dcls must provide sourceGrammar, sourceLocation, fullName
  -- All dcls that appear in interface files must provide unparse
  
  -- All values must provide typerep. shit what about namedSignature TODO FUCK
  -- All attributes must provide typerep.
  -- All types must provide typerep.
  
  -- All production attributes must provide attrDcl.
  -- All occurs must provide attrOccurring.
  
  -- See silver:definition:core for more "musts"
  
  -- this exists because extensions/modifications MUST not add any more musts.
  -- And then you need defaulting.  See collection attributes for an example.
}

-- -- non-interface values
abstract production childDcl
top::DclInfo ::= sg::String sl::Decorated Location fn::String ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;
  top.unparse = --error("child values should never appear in interace files.");
        "DEBUG child(" ++ sl.unparse ++ ", " ++ fn ++ ", " ++ unparseType(ty) ++ ")";
  
  top.typerep = ty;
  forwards to defaultDcl();
}
abstract production lhsDcl
top::DclInfo ::= sg::String sl::Decorated Location fn::String ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;
  top.unparse = error("lhs values should never appear in interace files.");
  
  top.typerep = ty;
  forwards to defaultDcl();
}
abstract production localDcl
top::DclInfo ::= sg::String sl::Decorated Location fn::String ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;
  -- locals will appear inside interface files inside production attribute declarations
  top.unparse = "loc(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ unparseType(ty) ++ ")";
  
  top.typerep = ty;
  forwards to defaultDcl();
}
-- let ( possibly replacement? problem: caching result )
-- NEW shadowed syn attributes? or inh?
-- NEW specific production type?

-- -- interface values
abstract production prodDcl
top::DclInfo ::= sg::String sl::Decorated Location ns::Decorated NamedSignature
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = ns.fullName;
  top.unparse = "prod(" ++ sl.unparse ++ ", " ++ ns.unparse ++ ")";

  top.namedSignature = ns;  
  top.typerep = productionTypeExp(ns.outputElement.typerep, getTypesSignature(ns.inputElements));
  forwards to defaultDcl();
}
abstract production funDcl
top::DclInfo ::= sg::String sl::Decorated Location ns::Decorated NamedSignature
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = ns.fullName;
  top.unparse = "fun(" ++ sl.unparse ++ ", " ++ ns.unparse ++ ")";

  top.namedSignature = ns;  
  top.typerep = functionTypeExp(ns.outputElement.typerep, getTypesSignature(ns.inputElements));
  forwards to defaultDcl();
}
abstract production globalValueDcl
top::DclInfo ::= sg::String sl::Decorated Location fn::String ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;
  top.unparse = "glob(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ unparseType(ty) ++ ")";

  top.typerep = ty;
  forwards to defaultDcl();

}

-- -- interface types
abstract production ntDcl
top::DclInfo ::= sg::String sl::Decorated Location fn::String
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;
  top.unparse = "nt(" ++ sl.unparse ++ ", '" ++ fn ++ "')";
  
  top.typerep = nonterminalTypeExp(fn,[]); -- TODO
  forwards to defaultDcl();
}
abstract production termDcl
top::DclInfo ::= sg::String sl::Decorated Location fn::String regex::Decorated Regex_R
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;
  top.unparse = "term(" ++ sl.unparse ++ ", '" ++ fn ++ "', /" ++ regex.regString ++ "/)";
  
  top.typerep = terminalTypeExp(fn);
  forwards to defaultDcl();
}

-- -- interface Attributes
abstract production synDcl
top::DclInfo ::= sg::String sl::Decorated Location fn::String ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;
  top.unparse = "syn(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ unparseType(ty) ++ ")";
  
  top.typerep = ty;
  forwards to defaultDcl();
}
abstract production inhDcl
top::DclInfo ::= sg::String sl::Decorated Location fn::String ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;
  top.unparse = "inh(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ unparseType(ty) ++ ")";
  
  top.typerep = ty;
  forwards to defaultDcl();
}

-- -- interface Production attr (values)
abstract production paDcl
top::DclInfo ::= sg::String sl::Decorated Location fn::String dcl::DclInfo
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;
  top.unparse = "p@(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ top.attrDcl.unparse ++ ")";
  
  top.attrDcl = decorate dcl with {};
  forwards to defaultDcl();
}
abstract production forwardDcl
top::DclInfo ::= sg::String sl::Decorated Location ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = "forward";
  top.unparse = "fwd(" ++ sl.unparse ++ ", " ++ unparseType(ty) ++ ")";
  
  top.typerep = ty;
  forwards to defaultDcl();
}

-- -- interface other
abstract production occursDcl
top::DclInfo ::= sg::String sl::Decorated Location fnnt::String fnat::String
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fnnt;
  top.unparse = "@(" ++ sl.unparse ++ ", '" ++ fnnt ++ "', '" ++ fnat ++ "')";
  
  top.attrOccurring = fnat;
  forwards to defaultDcl();
}

