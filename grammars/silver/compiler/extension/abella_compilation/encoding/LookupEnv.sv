grammar silver:compiler:extension:abella_compilation:encoding;


function lookupProdType
AbellaType ::= name::String env::Decorated Env
{
  local qname::QName = makeQName(name);
  qname.env = env;
  local ty::Type = qname.lookupValue.typeScheme.typerep;
  return ty.abellaType;
}


function makeQName
QName ::= n::String
{
  local colon_n::String = encodedToColons(n);
  local split::[String] = explode(":", colon_n);
  local names::[Name] =
        map(\ n::String -> name(n, bogusLoc()), split);
  return foldrLastElem(qNameCons(_, ':', _, location=bogusLoc()),
                       qNameId(_, location=bogusLoc()),
                       names);
}

