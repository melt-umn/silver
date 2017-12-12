grammar silver:extension:bidirtransform;

concrete production originDcl
top::AGDcl ::= 'apply' 'origins' 'on' nts::NonterminalList ';'
{
    forwards to applyOrigins(nts.ntList, location=top.location);
}

concrete production cncOriginDcl
top::AGDcl ::= 'apply' 'concrete' 'origins' 'on' nts::NonterminalList ';'
{
    forwards to cncApplyOrigins(nts.ntList, location=top.location);
}

abstract production cncApplyOrigins
top::AGDcl ::= ntList::[Decorated FullNonterminal]
{
    local cncNames :: [String] = map((.name), ntList);

    -- attribute suppliedOrigin occurs on $cncType;
    local agDcls::AGDcl = attrOn("suppliedOrigin", cncNames);

    -- abstract production origin_$type
    -- o::Origin ::= e::Decorated $type
    -- {
    --      o.isBottomOrigin = false;
    -- }
    --
    local agDcls2::AGDcl = appendAGDcl(foldl(\ agDcls::AGDcl qn::String->
         appendAGDcl(productionDcl('abstract', 'production', 
            name(mkOriginName(qn),top.location), mkProdSigDec("o", "Origin", "e", qn),
                prdBody([
                    attribDef("o", "isBottomOrigin", mkFalse())
                ])),
            agDcls),
        emptyAGDcl(), cncNames), agDcls);

    -- aspect all cnc origins with:
    --
    -- o.wasTransformed = false;
    -- o.concreteOrigin = o;
    local agDcls3::AGDcl = foldl(\ agDcls::AGDcl name::String->
        appendAGDcl(aspectProductionDcl('aspect', 'production', 
            qName(top.location, mkOriginName(name)), mkAspectProdSigDec("o", "Origin", "e", name),
                prdBody([
                    attribDef("o", "wasTransformed", mkFalse()),
                    attribDef("o", "concreteOrigin", baseName("o"))
                ])), agDcls),
        agDcls2, cncNames);

    default annotation location = top.location;    
    
    forwards to agDcls3;
}

abstract production applyOrigins 
top::AGDcl ::= ntList::[Decorated FullNonterminal]
{
    local absNames :: [String] = map((.name), ntList);    

    -- Occurances of attributes, annotations

    -- Problem in future: only apply this on attributes that they are not 
    -- already defined on. This doesn't work because checking if an attribute
    -- occurs on an element we're working with causes a loop.

    -- attribute wasTransformed occurs on $absType;
    local agDcls::AGDcl = attrOn("wasTransformed", absNames);

    -- abstract production origin_$type
    -- o::Origin ::= e::Decorated $type
    -- {
    --      o.isBottomOrigin = false;
    -- }
    --
    local agDcls2::AGDcl = appendAGDcl(foldl(\ agDcls::AGDcl qn::String->
         appendAGDcl(productionDcl('abstract', 'production', 
            name(mkOriginName(qn),top.location), mkProdSigDec("o", "Origin", "e", qn),
                prdBody([
                    attribDef("o", "isBottomOrigin", mkFalse())
                ])),
            agDcls),
        emptyAGDcl(), absNames), agDcls);

    -- aspect all abstract origins with:
    --
    -- o.wasTransformed = wasTransformed(e.origin, e.redex);
    -- o.concreteOrigin = getConcreteOrigin(e.origin, o);
    local agDcls3::AGDcl = foldl(\ agDcls::AGDcl name::String->
        appendAGDcl(aspectProductionDcl('aspect', 'production', 
            qName(top.location, mkOriginName(name)), mkAspectProdSigDec("o", "Origin", "e", name),
                prdBody([
                attribDef("o", "wasTransformed",
                    argFunc("wasTransformed", appExprList([
                        namedAccess("redex", "e"),
                        namedAccess("origin", "e")
                    ]))),
                attribDef("o", "concreteOrigin", 
                    argFunc("getConcreteOrigin", appExprList([
                        presentName("o"),
                        namedAccess("origin", "e")
                    ])))
                ])), agDcls),
        agDcls2, absNames);

    -- annotation redex occurs on $absType;
    local agDcls4::AGDcl = appendAGDcl(annoOn("redex", absNames), agDcls3);
    
    -- annotation labels occurs on $absType;
    local agDcls5::AGDcl = appendAGDcl(annoOn("labels", absNames), agDcls4);
    
    -- annotation origin occurs on $absType;
    local agDcls6::AGDcl = appendAGDcl(annoOn("origin", absNames), agDcls5);
    
    default annotation location = top.location;

    forwards to agDcls6;
}