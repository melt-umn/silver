grammar silver:compiler:extension:abella_compilation;

imports silver:compiler:definition:concrete_syntax;
imports silver:compiler:modification:ffi;
imports silver:compiler:extension:autoattr;
imports silver:compiler:modification:defaultattr;
imports silver:compiler:modification:copper;
imports silver:compiler:modification:copper_mda;
imports silver:compiler:definition:flow:syntax;
imports silver:compiler:modification:collection;



attribute
   prods, nonterminals, attrs, attrOccurrences, localAttrs,
   inheritedAttrs, associatedAttrs,
   attrTypeSchemas_up, attrTypeSchemas
occurs on Grammar;

attribute
   prods, nonterminals, attrs, attrOccurrences, localAttrs,
   inheritedAttrs, associatedAttrs,
   attrTypeSchemas_up, attrTypeSchemas
occurs on Root;

attribute
   prods, nonterminals, attrs, attrOccurrences, localAttrs,
   inheritedAttrs, associatedAttrs,
   attrTypeSchemas_up, attrTypeSchemas
occurs on AGDcls;

attribute
   prods, nonterminals, attrs, attrOccurrences, localAttrs,
   inheritedAttrs, associatedAttrs,
   attrTypeSchemas_up, attrTypeSchemas
occurs on AGDcl;


aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature
               body::ProductionBody 
{
}

aspect production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName
               ns::AspectFunctionSignature body::ProductionBody 
{
}

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature
               body::ProductionBody
{
  top.prods <- [(id.name, ns.abellaType)];
}

aspect production concreteProductionDcl
top::AGDcl ::= 'concrete' 'production' id::Name ns::ProductionSignature
               pm::ProductionModifiers body::ProductionBody
{
}

aspect production attributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name
               tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.attrs <- [a.name];
  top.attrTypeSchemas_up <-
      [(a.name, te.typerep.abellaType, tl.freeVariables)];
}

aspect production attributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name tl::BracketedOptTypeExprs
               '::' te::TypeExpr ';'
{
  top.attrs <- [a.name];
  top.inheritedAttrs <- [a.name];
  top.attrTypeSchemas_up <-
      [(a.name, te.typerep.abellaType, tl.freeVariables)];
}

aspect production defaultAttributionDcl
top::AGDcl ::= at::Decorated QName attl::BracketedOptTypeExprs nt::QName nttl::BracketedOptTypeExprs
{
  top.attrOccurrences <-
      case findAssociated(at.name, top.attrTypeSchemas) of
      | just((attrTy, vars)) ->
        [(at.name, [(nt.name, replaceVars(vars, attl.abellaTys, attrTy))])]
      | nothing() ->
        error("Abella encoding not defined in presence of errors " ++
              "(could not find attribute " ++ at.name ++ " in top.attrTypeSchemas")
      end;
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

