grammar silver:compiler:extension:abella_compilation;


aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 
{
  top.localAttrs := [];
  top.attrEqInfo := [];
}

aspect production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody 
{
  top.localAttrs :=
      error("Abella encoding cannot handle aspect functions");
  top.attrEqInfo := [];
}

aspect production functionDclFFI
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 'foreign' '{' ffidefs::FFIDefs '}'
{
  top.localAttrs :=
      error("Abella encoding cannot handle foreign functions");
  top.attrEqInfo := [];
}

aspect production returnDef
top::ProductionStmt ::= 'return' e::Expr ';'
{
}

