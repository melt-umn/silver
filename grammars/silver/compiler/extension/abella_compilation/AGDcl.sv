grammar silver:compiler:extension:abella_compilation;

imports silver:compiler:definition:concrete_syntax;
imports silver:compiler:modification:ffi;
imports silver:compiler:extension:autoattr;
imports silver:compiler:modification:defaultattr;
imports silver:compiler:modification:copper;
imports silver:compiler:modification:copper_mda;
imports silver:compiler:definition:flow:syntax;
imports silver:compiler:modification:collection;
imports silver:compiler:definition:env;



attribute
   prods, nonterminals, attrs, attrOccurrences, localAttrs,
   inheritedAttrs, attrEqInfo
occurs on Grammar;

attribute
   prods, nonterminals, attrs, attrOccurrences, localAttrs,
   inheritedAttrs, attrEqInfo
occurs on Root;

attribute
   prods, nonterminals, attrs, attrOccurrences, localAttrs,
   inheritedAttrs, attrEqInfo
occurs on AGDcls;

attribute
   prods, nonterminals, attrs, attrOccurrences, localAttrs,
   inheritedAttrs, attrEqInfo
occurs on AGDcl;


aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  top.prods <- [(id.name, ns.abellaType)];
}

aspect production attributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name
               tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  --Apparently there are equations missing in the host language
  local newtl::BracketedOptTypeExprs = tl;
  local newte::TypeExpr = te;
  newtl.config = top.config;
  newtl.grammarName = top.grammarName;
  newtl.env = top.env;
  newtl.flowEnv = top.flowEnv;
  newte.config = top.config;
  newte.onNt = error("Is onNt needed?  I don't know what it is. (attributeDclInh)");
  newte.grammarName = top.grammarName;
  newte.env = top.env;
  newte.flowEnv = top.flowEnv;
  --
  top.attrs <- [a.name];
}

aspect production attributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name tl::BracketedOptTypeExprs
               '::' te::TypeExpr ';'
{
  --Apparently there are equations missing in the host language
  local newtl::BracketedOptTypeExprs = tl;
  local newte::TypeExpr = te;
  newtl.config = top.config;
  newtl.grammarName = top.grammarName;
  newtl.env = top.env;
  newtl.flowEnv = top.flowEnv;
  newte.config = top.config;
  newte.onNt = error("Is onNt needed?  I don't know what it is. (attributeDclInh)");
  newte.grammarName = top.grammarName;
  newte.env = top.env;
  newte.flowEnv = top.flowEnv;
  --
  top.attrs <- [a.name];
  top.inheritedAttrs <- [a.name];
}

aspect production defaultAttributionDcl
top::AGDcl ::= at::Decorated QName attl::BracketedOptTypeExprs nt::QName nttl::BracketedOptTypeExprs
{
  top.attrOccurrences <-
      [(at.name, [(nt.name, protoatty.abellaType)])];
}

aspect production nonterminalDcl
top::AGDcl ::= quals::NTDeclQualifiers 'nonterminal' id::Name
               tl::BracketedOptTypeExprs nm::NonterminalModifiers ';'
{
  top.nonterminals <- [shortestName(id.name)];
}

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName
               ns::AspectProductionSignature body::ProductionBody 
{
}

aspect production terminalDclDefault
top::AGDcl ::= t::TerminalKeywordModifier id::Name r::RegExpr
               tm::TerminalModifiers
{
}

aspect production annotationDcl
top::AGDcl ::= 'annotation' a::QName tl::BracketedOptTypeExprs '::'
               te::TypeExpr ';'
{
}

aspect production equalityAttributeDcl
top::AGDcl ::= 'equality' 'attribute' inh::Name ',' syn::Name ';'
{
}

aspect production monoidAttributeDcl
top::AGDcl ::= 'monoid' 'attribute' a::Name tl::BracketedOptTypeExprs
               '::' te::TypeExpr 'with' e::Expr ',' _ ';'
{
}

aspect production threadedAttributeDcl
top::AGDcl ::= 'threaded' 'attribute' inh::Name ',' syn::Name
               tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
}

aspect production functorAttributeDcl
top::AGDcl ::= 'functor' 'attribute' a::Name ';'
{
}

aspect production aspectDefaultProduction
top::AGDcl ::= 'aspect' 'default' 'production' lhs::Name '::' _ '::='
               body::ProductionBody 
{
}

aspect production ffiTypeDclUgly
top::AGDcl ::= 'type' id::Name tl::BracketedOptTypeExprs 'foreign' '='
               trans::String_t ';'
{
}

aspect production emptyAGDcl
top::AGDcl ::=
{
}

aspect production flowtypeDcl
top::AGDcl ::= 'flowtype' nt::QName '=' specs::FlowSpecs ';'
{
}

aspect production flowtypeAttrDcl
top::AGDcl ::= 'flowtype' attr::FlowSpec 'on' nts::NtList ';'
{
}

aspect production attributeDclParser
top::AGDcl ::= 'parser' 'attribute' a::Name '::' te::TypeExpr 'action'
               acode::ActionCode_c ';'
{
}

aspect production attributeAspectParser
top::AGDcl ::= 'aspect' 'parser' 'attribute' a::QName 'action'
               acode::ActionCode_c ';'
{
}

aspect production errorAttributionDcl
top::AGDcl ::= msg::[Message] at::Decorated QName
               attl::BracketedOptTypeExprs nt::QName
               nttl::BracketedOptTypeExprs
{
}

aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' cl::ConstraintList '=>'
               t::TypeExpr '=' e::Expr ';'
{
}

aspect production typeAliasDecl
top::AGDcl ::= 'type' id::Name tl::BracketedOptTypeExprs '='
               te::TypeExpr ';'
{
}

