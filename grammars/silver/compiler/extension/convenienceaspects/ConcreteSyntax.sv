grammar silver:compiler:extension:convenienceaspects;
import silver:langutil:pp;
import silver:compiler:modification:collection;
import silver:compiler:extension:constructparser;

synthesized attribute makeAspectEquation::(ProductionStmt ::= DefLHS QNameAttrOccur Expr Location);

nonterminal ConvenienceAspectEquationKind with location, unparse, pp, makeAspectEquation;

concrete productions top::ConvenienceAspectEquationKind
| 'using' '='
{
  top.makeAspectEquation = attributeDef(_,'.',_,'=',_,';',location=_);
  top.pp = pp"using =";
  top.unparse = "using =";
}
| 'using' ':='
{
  top.makeAspectEquation = attrContainsBase(_,'.',_,':=',_,';',location=_);
  top.pp = pp"using :=";
  top.unparse = "using :=";
}
| 'using' '<-'
{
  top.makeAspectEquation = attrContainsAppend(_,'.',_,'<-',_,';', location=_);
  top.pp = pp"using <-";
  top.unparse = "using <-";
}
|
{
  top.makeAspectEquation = attributeDef(_,'.',_,'=',_,';',location=_);
  top.pp = pp"";
  top.unparse = "";
}


synthesized attribute aspectName::Name;
synthesized attribute aspectType::TypeExpr;

nonterminal ConvAspectLHS with aspectName, aspectType;
concrete productions top::ConvAspectLHS
| name::Name '::' ty::TypeExpr
{
  top.aspectType = ty;
  top.aspectName = name;
}
| ty::TypeExpr
{
  top.aspectType = ty;
  top.aspectName = name("__generatedTop_" ++ toString(genInt()), ty.location);
}


concrete productions top::AGDcl
| 'aspect' attr::QNameAttrOccur 'on' aspectLHS::ConvAspectLHS eqKind::ConvenienceAspectEquationKind 'of' Opt_Vbar_t ml::MRuleList 'end' ';'
{
  forwards to convenienceAspects(attr, aspectLHS, eqKind, ml, location=top.location);
}
