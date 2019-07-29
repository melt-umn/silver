
function makeDuplicateImpls
AGDcl ::= env::Decorated Env name::QName
{
	local foundProds :: [DclInfo] = getKnownProds(decorate name with {env=env;}.lookupType.fullName, env);
	return errorAGDcl([wrn(name.location, "KNOWNPRODS = "++hackUnparse(map((.fullName), foundProds)))], location=name.location);
}