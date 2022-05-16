grammar silver:compiler:extension:abella_compilation:encoding;


attribute
   prods, nonterminals, attrs, attrOccurrences, localAttrs,
   inheritedAttrs, synAttrEqInfo, inhAttrEqInfo, localAttrDefs, funRelClauses
occurs on Grammar;

attribute
   prods, nonterminals, attrs, attrOccurrences, localAttrs,
   inheritedAttrs, synAttrEqInfo, inhAttrEqInfo, localAttrDefs, funRelClauses
occurs on Root;

attribute
   prods, nonterminals, attrs, attrOccurrences, localAttrs,
   inheritedAttrs, synAttrEqInfo, inhAttrEqInfo, localAttrDefs, funRelClauses
occurs on AGDcls;

attribute
   prods, nonterminals, attrs, attrOccurrences, localAttrs,
   inheritedAttrs, synAttrEqInfo, inhAttrEqInfo, localAttrDefs, funRelClauses
occurs on AGDcl;


aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  top.prods <- [(buildEncodedName(top.grammarName, id.name), ns.abellaType)];
}

aspect production attributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name
               tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.attrs <- [buildEncodedName(top.grammarName, a.name)];
}

aspect production attributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name tl::BracketedOptTypeExprs
               '::' te::TypeExpr ';'
{
  top.attrs <- [buildEncodedName(top.grammarName, a.name)];
  top.inheritedAttrs <- [buildEncodedName(top.grammarName, a.name)];
}

aspect production defaultAttributionDcl
top::AGDcl ::= at::PartiallyDecorated QName attl::BracketedOptTypeExprs nt::QName nttl::BracketedOptTypeExprs
{
  top.attrOccurrences <-
      [(encodeName(at.lookupAttribute.fullName), [(encodeName(nt.lookupType.fullName), protoatty.abellaType)])];
}

aspect production nonterminalDcl
top::AGDcl ::= quals::NTDeclQualifiers 'nonterminal' id::Name
               tl::BracketedOptTypeExprs nm::NonterminalModifiers ';'
{
  top.nonterminals <- [buildEncodedName(top.grammarName, shortestName(id.name))];
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
top::AGDcl ::= 'equality' 'attribute' inh::Name ',' syn::QName ';'
{
}

aspect production monoidAttributeDcl
top::AGDcl ::= 'monoid' 'attribute' a::Name tl::BracketedOptTypeExprs
               '::' te::TypeExpr 'with' e::Expr ',' _ ';'
{
}

aspect production threadedAttributeDcl
top::AGDcl ::= 'threaded' 'attribute' inh::Name ',' syn::Name
               tl::BracketedOptTypeExprs '::' te::TypeExpr
               d::OptDirectionMod ';'
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
top::AGDcl ::= msg::[Message] at::PartiallyDecorated QName
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

