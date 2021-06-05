grammar silver:compiler:extension:abella_compilation;


imports silver:compiler:definition:core;
imports silver:compiler:definition:type;
imports silver:compiler:definition:type:syntax;
imports silver:compiler:extension:list;


--Translating types and production signatures
synthesized attribute abellaType::AbellaType;
--
inherited attribute outType::AbellaType;


attribute
   abellaType
occurs on ProductionSignature;

aspect production productionSignatureNoCL
top::ProductionSignature ::= lhs::ProductionLHS '::=' rhs::ProductionRHS
{
  rhs.outType = lhs.abellaType;
  top.abellaType = rhs.abellaType;
}

aspect production productionSignature
top::ProductionSignature ::= cl::ConstraintList '=>' lhs::ProductionLHS '::=' rhs::ProductionRHS
{
  rhs.outType = lhs.abellaType;
  top.abellaType = rhs.abellaType;
}



attribute
   abellaType
occurs on ProductionLHS;

aspect production productionLHS
top::ProductionLHS ::= id::Name '::' t::TypeExpr
{
  --Missing equation in host
  local newt::TypeExpr = t;
  newt.onNt = error("Is onNt needed? (productionLHS)");
  newt.grammarName = top.grammarName;
  newt.env = top.env;
  newt.flowEnv = top.flowEnv;
  newt.config = top.config;
  --
  top.abellaType = newt.typerep.abellaType;
}



attribute
   abellaType, outType
occurs on ProductionRHS;

aspect production productionRHSNil
top::ProductionRHS ::=
{
  top.abellaType = top.outType;
}

aspect production productionRHSCons
top::ProductionRHS ::= h::ProductionRHSElem t::ProductionRHS
{
  top.abellaType = arrowAbellaType(h.abellaType, t.abellaType);
  t.outType = top.outType;
}



attribute
   abellaType
occurs on ProductionRHSElem;

aspect production productionRHSElem
top::ProductionRHSElem ::= id::Name '::' t::TypeExpr
{
  --Missing equation in host
  local newt::TypeExpr = t;
  newt.onNt = error("Is onNt needed? (productionRHSElem)");
  newt.grammarName = top.grammarName;
  newt.env = top.env;
  newt.flowEnv = top.flowEnv;
  newt.config = top.config;
  --
  top.abellaType = newt.typerep.abellaType;
}






attribute
   abellaType
occurs on Type;

aspect production varType
top::Type ::= tv::TyVar
{
  top.abellaType = varAbellaType(tv);
      --error("Should not access abellaType on varType");
}

aspect production skolemType
top::Type ::= tv::TyVar
{
  top.abellaType = varAbellaType(tv);
      --error("Should not access abellaType on skolemType");
}

aspect production appType
top::Type ::= c::Type a::Type
{
  top.abellaType = functorAbellaType(c.abellaType, a.abellaType);
}

aspect production errorType
top::Type ::=
{
  top.abellaType = nameAbellaType("Whence is an errorType coming?");
--      error("Cannot translate to Abella in presence of errors");
}

aspect production intType
top::Type ::=
{
  top.abellaType = nameAbellaType("integer");
}

aspect production boolType
top::Type ::=
{
  top.abellaType = nameAbellaType("bool");
}

aspect production floatType
top::Type ::=
{
  top.abellaType = nameAbellaType("float");
      --error("Cannot translate float types to Abella yet");
}

aspect production stringType
top::Type ::=
{
  top.abellaType = stringAbellaType;
}

aspect production listType
top::Type ::= el::Type
{
  top.abellaType =
      functorAbellaType(nameAbellaType("list"), el.abellaType);
}

aspect production terminalIdType
top::Type ::=
{
  top.abellaType = nameAbellaType("terminalIdType");
      --error("Should not access abellaType for terminalIdType");
}

aspect production nonterminalType
top::Type ::= fn::String ks::[Kind] tracked::Boolean
{
  top.abellaType =
      if fn == "silver:core:List"
      then nameAbellaType("list")
      else if fn == "silver:core:Pair"
           then nameAbellaType("$pair")
           else nameToNonterminalType(shortestName(fn));
}

aspect production terminalType
top::Type ::= fn::String
{
  top.abellaType = nameAbellaType("terminalType(" ++ fn ++ ")");
      --error("Should not access abellaType for terminalType");
}

aspect production decoratedType
top::Type ::= te::Type i::Type
 {
   top.abellaType = te.abellaType;
}

aspect production ntOrDecType
top::Type ::= nt::Type inhs::Type hidden::Type
{
  top.abellaType = nt.abellaType;
}

aspect production functionType
top::Type ::= params::Integer namedParams::[String]
{
  top.abellaType = nameAbellaType("functionType(" ++ toString(params) ++ ")");
      --error("Should not access abellaType on functionType");
}

aspect production foreignType
top::Type ::= fn::String  transType::String  params::[Type]
{
  top.abellaType = nameAbellaType("foreign(" ++ fn ++ ", " ++ transType ++ ")");
}

aspect production inhSetType
top::Type ::= inhs::[String]
{
  top.abellaType = error("I don't know what inhSetType is");
}




synthesized attribute abellaTys::[AbellaType];

attribute
   abellaTys
occurs on BracketedOptTypeExprs;

attribute
   abellaTys
occurs on BracketedTypeExprs;

attribute
   abellaTys
occurs on TypeExprs;


aspect production botlSome
top::BracketedOptTypeExprs ::= btl::BracketedTypeExprs
{
  top.abellaTys = btl.abellaTys;
}

aspect production bTypeList
top::BracketedTypeExprs ::= '<' tl::TypeExprs '>'
{
  top.abellaTys = tl.abellaTys;
}



aspect production typeListNone
top::TypeExprs ::=
{
  top.abellaTys = [];
}

aspect production typeListCons
top::TypeExprs ::= t::TypeExpr list::TypeExprs
{
  --Missing equation in host
  local newt::TypeExpr = t;
  newt.onNt = error("Is onNt needed? (typeListCons)");
  newt.grammarName = top.grammarName;
  newt.env = top.env;
  newt.flowEnv = top.flowEnv;
  newt.config = top.config;
  --
  top.abellaTys = newt.typerep.abellaType::list.abellaTys;
}

aspect production typeListConsMissing
top::TypeExprs ::= '_' list::TypeExprs
{
  top.abellaTys = error("This shouldn't show up in our use cases");
}

