grammar silver:extension:bidirtransform;

abstract production ntGroupDef 
top::Def ::= d::DclInfo
{
    top.dcl = d;
}

abstract production ntGroupDcl
top::DclInfo ::= sg::String sl::Location name::String nts::NonterminalList
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = name; --todo?
  top.unparse = ""; --todo?
}