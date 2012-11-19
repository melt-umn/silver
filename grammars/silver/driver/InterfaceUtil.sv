grammar silver:driver;



function normalizer
[String] ::= f::([String] ::= Decorated RootSpec)  r::Decorated RootSpec
{
  return r.declaredName :: f(r);
}
function normalizeExports
[[String]] ::= ifs::[Decorated RootSpec]
{
  return map(normalizer((.exportedGrammars), _), ifs);
}
function normalizeImports
[[String]] ::= ifs::[Decorated RootSpec]
{
  return map(normalizer((.moduleNames), _), ifs);
}

function collectGrammars
[String] ::= lst::[Decorated RootSpec]
{
  return map((.declaredName), lst);
}

function getRootSpec
[Decorated RootSpec] ::= n::String rs::[Decorated RootSpec]
{
return if null(rs) then [] else if head(rs).declaredName == n then [head(rs)] else getRootSpec(n, tail(rs));
}
