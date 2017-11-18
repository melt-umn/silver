grammar silver:extension:ntgroup;

imports silver:definition:env;
imports silver:definition:core;

synthesized attribute ntList::[FullNonterminal];
synthesized attribute ntProds::[NamedSignature];
synthesized attribute groupList::[DclInfo];

nonterminal FullNonterminal with name, ntProds, location, errors, env;
nonterminal NonterminalList with location, ntList, errors, env;

terminal Nonterminals_kwd 'nonterminals' lexer classes{KEYWORD,RESERVED};
terminal From_kwd 'group' lexer classes{KEYWORD,RESERVED};

concrete production nonterminalGroup 
top::AGDcl ::= 'group' 'nonterminals' nts::NonterminalList ';' 
{
    top.errors := nts.errors;

    top.defs = [ntGroupDef(ntGroupDcl(top.grammarName, top.location, nts))];
    
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
    top.ntProds = prodsFromDcls(getProdsForNt(top.name, top.env));

    top.errors := if length(getTypeDcl(top.name, top.env)) != 0 then []
        else [err(top.location, "Name " ++ top.name ++ " doesn't match any known nonterminal")];
}

function prodsFromDcls
[NamedSignature] ::= dcls::[DclInfo]
{
    return if length(dcls) == 0 then []
        else prodFromDcl(head(dcls)) ++ prodsFromDcls(tail(dcls));
}

function prodFromDcl 
[NamedSignature] ::= dcl::DclInfo
{
    return case dcl of 
        | prodDcl(_,_,ns) -> [ns]
        | _ -> []
    end;
}