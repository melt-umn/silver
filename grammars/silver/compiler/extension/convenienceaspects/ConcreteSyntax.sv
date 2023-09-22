grammar silver:compiler:extension:convenienceaspects;
import silver:langutil:pp;
import silver:compiler:modification:collection;
import silver:compiler:extension:constructparser;

@{- @hide -}
synthesized attribute makeAspectEquation::(ProductionStmt ::= DefLHS QNameAttrOccur Expr Location);

@{-
  - A nonterminal describing what binding to use on the attribute in the generated aspects.
-}
tracked nonterminal ConvenienceAspectEquationKind with unparse, pp, makeAspectEquation;

@{- @hide -}
concrete productions top::ConvenienceAspectEquationKind
| 'using' '='
{
  top.makeAspectEquation = attributeDef(_,'.',_,'=',_,';');
  top.pp = pp"using =";
  top.unparse = "using =";
}
| 'using' ':='
{
  top.makeAspectEquation = attrContainsBase(_,'.',_,':=',_,';');
  top.pp = pp"using :=";
  top.unparse = "using :=";
}
| 'using' '<-'
{
  top.makeAspectEquation = attrContainsAppend(_,'.',_,'<-',_,';');
  top.pp = pp"using <-";
  top.unparse = "using <-";
}
|
{
  top.makeAspectEquation = attributeDef(_,'.',_,'=',_,';');
  top.pp = pp"";
  top.unparse = "";
}


@{- @hide -}
synthesized attribute aspectName::Name;
@{- @hide -}
synthesized attribute aspectType::TypeExpr;

@{-
  - Nonterminal for the name and type of the term for which you're constructing aspect productions for. -}
tracked nonterminal ConvAspectLHS with aspectName, aspectType, unparse;
@{- @hide -}
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
  top.aspectName = name("__generatedTop_" ++ toString(genInt()));
  top.unparse = ty.unparse;
}


@{- Takes in the following:
  - - attr: The attribute you're defining aspect productions for.
  - - aspectLHS: the type for your aspect productions, as well as a custom name for if you define it.
  - - eqKind: The binding method for defining the new attribute within the generated aspect productions.
  - - ml: A List of MatchRules that describe the aspects productions you'd like to make.

  - And returns a a single AgDcl defining all the aspect productions according to the parameters given.
  - This is the concrete syntax for defining convenience aspects.
-}
concrete production convenienceAspects_c
top::AGDcl ::= 'aspect' attr::QNameAttrOccur 'on' aspectLHS::ConvAspectLHS eqKind::ConvenienceAspectEquationKind 'of' Opt_Vbar_t ml::MRuleList 'end' ';'
{
  forwards to convenienceAspects(attr, aspectLHS, eqKind, ml);
}
