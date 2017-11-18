grammar silver:extension:ntgroup;

abstract production ntGroupDef 
top::Def ::= d::DclInfo
{
    top.dcl = d;
}

abstract production ntGroupDcl
top::DclInfo ::= sg::String sl::Location nts::NonterminalList
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = ""; --todo?
  top.unparse = ""; --todo?
}