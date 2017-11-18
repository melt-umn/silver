grammar silver:extension:ntgroup;

synthesized attribute ntList::[FullNonterminal];
synthesized attribute ntProds::[NamedSignature];

nonterminal FullNonterminal with name, ntProds, location, errors, env;
nonterminal NonterminalList with location, ntList, errors, env;
nonterminal NonterminalGroup with location, errors, env;

concrete production nonterminalGroup 
top::NonterminalGroup ::= 'nonterminal' 'group' nts::NonterminalList ';' 
{
    top.errors := names.errors ++ nts.errors;
    top.ntList = nts.ntList;
    
    nts.env = top.env;
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
    top.ntName = qn.name;
    top.ntProds = prodsFromDcls(getProdsForNt(top.ntName, top.env));

    top.errors = if length(getTypeDcl(top.ntName) != 0 then []
        else [err(top.location, "Name " ++ top.ntName ++ " doesn't match any known nonterminal")];
}

function prodsFromDcls
[NamedSignature] ::= dcls::[DclInfo]
{
    return if length(dcls) == 0 then []
        else prodFromDcl(head(dcls)) ++ prodsFromDcls(tail(dcls));
}

function prodFromDcl 
[NamedSignature] :: = dcl::DclInfo
{
    return case dcl of 
        | prodDcl(_,_,ns) -> [ns]
        | _ -> []
    end;
}
