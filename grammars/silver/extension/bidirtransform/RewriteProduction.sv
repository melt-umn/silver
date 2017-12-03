grammar silver:extension:bidirtransform;

concrete production rewriteProduction
prd::RewriteProduction ::= qn::QName '(' args::RewriteProductionArgs ')'
{
    prd.pp = qn.pp ++ "(" ++ args.pp ++ ")";

    prd.inputNames = args.inputNames;
    prd.name = qn.name;

    prd.errors := args.errors;

    local absSig::[Decorated NamedSignature] = getProdFromGroup(qn.name, prd.absGroup);
    local cncSig::[Decorated NamedSignature] = getProdFromGroup(qn.name, prd.cncGroup);

    prd.decSig = if length(absSig) != 0 then head(absSig)
        else head(cncSig); 

    prd.typerep = prd.decSig.typerep;
}

abstract production emptyRewriteProduction
prd::RewriteProduction ::= 
{
    prd.pp = "";
    prd.errors := [];
}

concrete production rewriteProductionArgSingle
arg::RewriteProductionArgs ::= name::QName
{
    arg.pp = name.pp;
    arg.inputNames = [name.name];
    arg.errors := [];
} 

concrete production rewriteProductionArgMany
arg::RewriteProductionArgs ::= args::RewriteProductionArgs ',' name::QName {
    arg.pp = args.pp ++ "," ++ name.pp;
    arg.inputNames = args.inputNames ++ [name.name];
    arg.errors := args.errors;
}
