grammar silver:extension:bidirtransform;

-- Don't look I'm doing terrible things
-- Q: What is the goal of these functions?
-- A: Finding things in the environment that we didn't create.
--    More specifically, finding things in the environment without
--    causing an infinite attribute definition loop. This means
--    we can't decorate anything that wants anything from the primary
--    transform abstract production, because almost everythgin in the
--    transform abstract production is based on the result of these
--    functions. 
--
--    This is a replacement for getProdsForNt, which does the decoration
--    that we can't tolerate. 
--
--    Functions that could be in the same function declaration but are 
--    split off are so in order to ease debugging until it works


function searchNtGroup
[Decorated NonterminalList] ::= fnnt::String e::Decorated Env
{
    return case e of 
        | i_emptyEnv() -> []
        | i_appendEnv(e2, e3) -> searchNtGroup(fnnt, e2) ++ searchNtGroup(fnnt, e3)
        | i_newScopeEnv(dfs, e2) -> searchNtGroup(fnnt, e2) ++ defsNtGroup(fnnt, dfs)
    end;
}

function defsNtGroup
[Decorated NonterminalList] ::= fnnt::String dfs::Defs
{
    return case dfs of 
        | nilDefs() -> []
        | consDefs(d, dfs2) -> if d.isLock then skipNtToNextLock(fnnt, dfs2)
            else defsNtGroup(fnnt, dfs2) ++
            case d of
                | ntGroupDef(dcl) -> if unFull(dcl.fullName) == fnnt then case dcl of 
                        | ntGroupDcl(_,_,_,ntlst) -> [ntlst] 
                        | _ -> []
                    end
                    else []
                | _ -> []
            end 
    end;
}

function skipNtToNextLock
[Decorated NonterminalList] ::= fnnt::String dfs::Defs 
{
    -- just kidding, this doesn't work?
    return [];
    -- return case dfs of 
    --     | nilDefs() -> []
    --     | consDefs(d, dfs2) -> 
    --         if d.isLock 
    --         then defsNtGroup(fnnt, dfs2)
    --         else skipNtToNextLock(fnnt, dfs2)
    -- end;
}

-- is is a production name we are trying to find the named signature of
function getProdFromGroup
[Decorated NamedSignature] ::= s::String ntlst::Decorated NonterminalList 
{
    return getProdFromList(s, ntlst.ntList);
}

function getProdFromList
[Decorated NamedSignature] ::= s::String nts::[Decorated FullNonterminal]
{
    return if length(nts) == 0 then []
        else getProdFromSig(s, head(nts).ntProds) ++ getProdFromList(s, tail(nts));
}

function getProdFromSig
[Decorated NamedSignature] ::= s::String prds::[Decorated NamedSignature]
{
    return if length(prds) == 0 then []
        else if unFull(head(prds).fullName) == s then [head(prds)]
        else getProdFromSig(s, tail(prds));
}

function getProdsFromNtHack
[DclInfo] ::= fnnt::String e::Decorated Env skipGrammar::String
{
    -- original:
    -- return searchEnvAll(fnnt, e.prodsForNtTree)
    return case e of 
        | i_emptyEnv() -> []
        | i_appendEnv(e2, e3) -> getProdsFromNtHack(fnnt, e2, skipGrammar) ++ getProdsFromNtHack(fnnt, e3, skipGrammar)
        | i_newScopeEnv(dfs, e2) -> getProdsFromDefs(fnnt, dfs, skipGrammar) ++ getProdsFromNtHack(fnnt, e2, skipGrammar)
    end;
}

function getProdsFromDefs
[DclInfo] ::= fnnt::String dfs::Defs skipGrammar::String
{
    return case dfs of 
        | nilDefs() -> []
        | consDefs(d, dfs2) -> getProdsFromConsDefs(fnnt, d, dfs2, skipGrammar)
    end;
}

function getProdsFromConsDefs
[DclInfo] ::= fnnt::String d::Def dfs::Defs skipGrammar::String
{
    return if d.isLock then skipToNextLock(fnnt, dfs, skipGrammar)
        else getProdsFromConsDefs2(fnnt, d, dfs, skipGrammar);
}


function skipToNextLock
[DclInfo] ::= fnnt::String dfs::Defs skipGrammar::String
{
    return case dfs of 
        | nilDefs() -> []
        | consDefs(d, dfs2) -> 
            if d.isLock 
            then getProdsFromDefs(fnnt, dfs2, skipGrammar)
            else skipToNextLock(fnnt, dfs2, skipGrammar)
    end;
}

function getProdsFromConsDefs2
[DclInfo] ::= fnnt::String d::Def dfs::Defs skipGrammar::String
{
    return case d of 
        | prodDclDef(ei) -> getProdsFromConsDefs3(fnnt,d,dfs,skipGrammar)
        | _ -> getProdsFromDefs2(fnnt, dfs, skipGrammar) 
    end;
}

function getProdsFromConsDefs3
[DclInfo] ::= fnnt::String d::Def dfs::Defs skipGrammar::String
{
    return case d.dcl of
        | prodDcl(sg,fn,ns, _) -> getProdsFromProdDcl(fnnt,d,dfs,sg,ns,skipGrammar)
        | _ -> getProdsFromDefs2(fnnt, dfs, skipGrammar)
    end;
}

function getProdsFromProdDcl
[DclInfo] ::= fnnt::String d::Def dfs::Defs sg::String ns::Decorated NamedSignature skipGrammar::String
{
    return if endsWith(skipGrammar,sg) then getProdsFromDefs2(fnnt, dfs, skipGrammar)
        else getProdsFromProdDcl2(fnnt,d,dfs,ns,skipGrammar);
}

function getProdsFromProdDcl2
[DclInfo] ::= fnnt::String d::Def dfs::Defs ns::Decorated NamedSignature skipGrammar::String
{
    return if nsMatchesStr(ns, fnnt)
        then [d.dcl] ++ getProdsFromDefs(fnnt, dfs, skipGrammar)
        else getProdsFromDefs2(fnnt, dfs, skipGrammar);
}

function getProdsFromDefs2
[DclInfo] ::= fnnt::String dfs::Defs skipGrammar::String
{
    return getProdsFromDefs(fnnt, dfs, skipGrammar);
}

function nsMatchesStr
Boolean ::= ns::Decorated NamedSignature fnnt::String 
{
    -- want this
    return unFull(ns.outputElement.typerep.typeName) == fnnt;
    -- but, accessing attributes is bad
    -- return case ns of 
    --     | namedSignature(_, _, oe, _) -> nsMatchesStr2(oe, fnnt)
    --     | _ -> false
    -- end;
}

function nsMatchesStr2
Boolean ::= oe::NamedSignatureElement fnnt::String
{
    return case oe of 
        | namedSignatureElement(_, ty) -> nsMatchesStr3(ty, fnnt) 
        | _ -> false
    end;
}

function nsMatchesStr3
Boolean ::= ty::Type fnnt::String
{
    return case ty of 
        | nonterminalType(s, _) -> unFull(s) == fnnt
        | _ -> false
    end;
}