grammar silver:extension:bidirtransform;

imports silver:definition:core;
imports silver:extension:convenience;
imports silver:definition:type:syntax;

terminal Transform_kwd 'transform' lexer classes {KEYWORD,RESERVED};

inherited attribute nonterminals NameList;

nonterminal TransformRuleList with nonterminals;
nonterminal TransformRule with nonterminals;
nonterminal ProductionDef;

concrete production transformAGDcl
ag::AGDcl ::= 'transform' name::QName '{' rules::TransformRuleList '}' '{' nts::NameList '}'
{
    rules.nonterminals = nts;
}

concrete production transformRuleCons
trl::TransformRuleList ::= l::TransformRule ',' r::TransformRuleList
{
    l.nonterminals = trl.nonterminals;
    r.nonterminals = trl.nonterminals;
}

concrete production transformRuleSingle
trl::TransformRuleList ::= rule::TransformRule 
{
    rule.nonterminals = trl.nonterminals;
}

concrete production transformRule
tr::TransformRule ::= l::ProductionDef "->" r::ProductionDef
{
    -- forward to aspect production for each type needed
}

concrete production productionDef
pd::ProductionDef ::= prd::QName '(' e::Expr ')'
{

}



