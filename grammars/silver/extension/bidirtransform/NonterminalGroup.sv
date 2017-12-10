grammar silver:extension:bidirtransform;

synthesized attribute ntList::[Decorated FullNonterminal];
synthesized attribute ntProds::[Decorated NamedSignature];
synthesized attribute groupList::[DclInfo];
autocopy attribute grantedDefs::[Def];

attribute grantedDefs occurs on AGDcl;

nonterminal FullNonterminal with name, ntProds, location, errors, env, grantedDefs;
nonterminal NonterminalList with location, ntList, errors, env, grantedDefs;

terminal Nonterminals_kwd 'nonterminals' lexer classes{KEYWORD,RESERVED};

concrete production nonterminalGroup 
top::AGDcl ::= 'nonterminals' qn::QName '=' nts::NonterminalList ';' 
{
    top.errors := nts.errors;

    top.defs = [ntGroupDef(ntGroupDcl(top.grammarName, top.location, qn.name, nts))];
    
    nts.env = newScopeEnv(top.defs, top.env);
}

concrete production singleNt 
top::NonterminalList ::= fnt::FullNonterminal
{
    top.errors := fnt.errors;
    top.ntList = [fnt];

    fnt.env = top.env;
}

concrete production consNt 
top::NonterminalList ::= fnt::FullNonterminal ',' lst::NonterminalList 
{
    top.errors := fnt.errors ++ lst.errors;
    top.ntList = [fnt] ++ lst.ntList;

    fnt.env = top.env;
    lst.env = top.env;
}

concrete production fullNt 
top::FullNonterminal ::= qn::QName
{
    top.name = qn.name;
    top.ntProds = filterSigs(qn.name, prodsFromDefs(top.grantedDefs));

    top.errors := if length(getTypeDcl(top.name, top.env)) != 0 then []
        else [err(top.location, "Name " ++ top.name ++ " doesn't match any known nonterminal")];
}

function filterSigs
[Decorated NamedSignature] ::= nm::String toFilter::[Decorated NamedSignature]
{
    local hd::Decorated NamedSignature = head(toFilter);

    return if null(toFilter) then []
       else if unFull(hd.outputElement.typeName) == nm then [hd] ++ filterSigs(nm, tail(toFilter))
       else filterSigs(tail(toFilter)); 
}

function prodsFromDefs
[Decorated NamedSignature] ::= defs::[Def]
{
    return if length(defs) == 0 then []
        else prodFromDef(head(defs)) ++ prodsFromDefs(tail(defs));
}

function prodFromDef
[Decorated NamedSignature] ::= def::Def
{
    return if null(def.prodNamedSig) then [] 
        else [decorate head(def.prodNamedSig) with {}];
}

function prodsFromDcls
[Decorated NamedSignature] ::= dcls::[DclInfo]
{
    return if length(dcls) == 0 then []
        else prodFromDcl(head(dcls)) ++ prodsFromDcls(tail(dcls));
}

function prodFromDcl 
[Decorated NamedSignature] ::= dcl::DclInfo
{
    return case dcl of 
        | prodDcl(_,_,ns) -> [ns]
        | _ -> []
    end;
}