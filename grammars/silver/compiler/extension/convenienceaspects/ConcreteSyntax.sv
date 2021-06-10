grammar silver:compiler:extension:convenienceaspects;
import silver:langutil:pp;
import silver:compiler:modification:collection;
import silver:compiler:extension:constructparser;

@{- @hide -}
synthesized attribute makeAspectEquation::(ProductionStmt ::= DefLHS QNameAttrOccur Expr Location);

@{-
  - A nonterminal describing what binding to use on the attribute in the generated aspects.
-}
nonterminal ConvenienceAspectEquationKind with location, unparse, pp, makeAspectEquation;

@{- @hide -}
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


@{- @hide -}
synthesized attribute aspectName::Name;
@{- @hide -}
synthesized attribute aspectType::TypeExpr;

@{-
  - Nonterminal for the name and type of the term for which you're constructing aspect productions for. -}
nonterminal ConvAspectLHS with aspectName, aspectType, unparse;
concrete productions top::ConvAspectLHS
| name::Name '::' ty::TypeExpr
{
  top.aspectType = ty;
  top.aspectName = name;
  top.unparse = name.unparse ++ "::" ++ ty.unparse;
}
| ty::TypeExpr
{
  top.aspectType = ty;
  top.aspectName = name("__generatedTop_" ++ toString(genInt()), ty.location);
  top.unparse = ty.unparse;
}


@{-
  - @param aspect The aspect keyword
  - @param attr The attribute you're defining aspect productions for.
  - @param on keyword, concrete syntax
  - @param aspectLHS the type for your aspect productions, as well as a custom name for if you define it.
  - @param eqKind The binding method for defining the new attribute within the generated aspect productions.
  - @param of keyword, concrete syntax.
  - @param Opt_Vbar_t vertical bar
  - @param ml The Match Rules defining the aspect productions you'd like to make
  - @param end the end keyword, concrete syntax.
  - @param semicolon a literal semicolon.
  - A single AgDcl defining all the aspect productions according to the parameters given.
  - This is the concrete syntax for defining convenience aspects.
-}
concrete production convenienceAspects_c
top::AGDcl ::= 'aspect' attr::QNameAttrOccur 'on' aspectLHS::ConvAspectLHS eqKind::ConvenienceAspectEquationKind 'of' Opt_Vbar_t ml::MRuleList 'end' ';'
{
  forwards to convenienceAspects(attr, aspectLHS, eqKind, ml, location=top.location);
}
