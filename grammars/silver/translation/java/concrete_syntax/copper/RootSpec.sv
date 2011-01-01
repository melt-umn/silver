grammar silver:translation:java:concrete_syntax:copper;

aspect production unparseRootSpec
top::RootSpecUnparse ::= r::Decorated RootSpec{
  unparses <- mapDGs(r.disambiguationGroupDcls);
  unparses <- mapPAs(r.parserAttrDcls);
}

function mapDGs
[String] ::= lst::[Decorated DisambiguationGroupSpec]
{
  return if null(lst) then [] else ["disambiguate [[" ++ implode(", ",quoteStrings(head(lst).groupMembers)) ++ "], \"" ++ escapeString(head(lst).actionCode) ++ "\"]"] ++ mapDGs(tail(lst));
}

function mapPAs
[String] ::= lst::[Decorated ParserAttrSpec]
{
  return if null(lst) then [] else ["parse_attr [" ++ quoteString(head(lst).name) ++ ", " ++ head(lst).typerep.unparse ++ ", \"" ++ escapeString(head(lst).actionCode) ++ "\"]"] ++ mapPAs(tail(lst));
}

function escapeString
String ::= s::String
{
  return substitute("\\\"", "\"", s);
}

function unescapeString
String ::= s::String
{
  return substitute("\"", "\\\"", s);
}


aspect production i_emptyRootSpec
top::RootSpec ::= 
{
  top.disambiguationGroupDcls = [];
  top.parserAttrDcls = [];
}

aspect production i_rootSpecRoot
top::RootSpec ::= c1::Decorated Root
{
  top.disambiguationGroupDcls = c1.disambiguationGroupDcls; 
  top.parserAttrDcls = c1.parserAttrDcls;
}

aspect production i_appendRootSpec
top::RootSpec ::= c1::Decorated RootSpec c2::Decorated RootSpec
{
  top.disambiguationGroupDcls = c1.disambiguationGroupDcls ++ c2.disambiguationGroupDcls;
  top.parserAttrDcls = c1.parserAttrDcls ++ c2.parserAttrDcls;
}

aspect production root
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls
{
  top.disambiguationGroupDcls = ags.disambiguationGroupDcls;
  top.parserAttrDcls = ags.parserAttrDcls;
}

aspect production agDclsOne
top::AGDcls ::= ag::AGDcl
{
  top.disambiguationGroupDcls = ag.disambiguationGroupDcls ;
  top.parserAttrDcls = ag.parserAttrDcls ;
}

aspect production agDclsCons
top::AGDcls ::= h::AGDcl t::AGDcls
{
  top.disambiguationGroupDcls = h.disambiguationGroupDcls ++ t.disambiguationGroupDcls;
  top.parserAttrDcls = h.parserAttrDcls ++ t.parserAttrDcls;
}

aspect production agDclsAppend
top::AGDcls ::= ag1::AGDcls ag2::AGDcls
{
  top.disambiguationGroupDcls = ag1.disambiguationGroupDcls ++ ag2.disambiguationGroupDcls;
  top.parserAttrDcls = ag1.parserAttrDcls ++ ag2.parserAttrDcls;
}

aspect production agDclDefault
top::AGDcl ::=
{
  top.disambiguationGroupDcls = [];
  top.parserAttrDcls = [];
}

aspect production agDclAppend
top::AGDcl ::= ag1::AGDcl ag2::AGDcl
{
  top.disambiguationGroupDcls = ag1.disambiguationGroupDcls ++ ag2.disambiguationGroupDcls;
  top.parserAttrDcls = ag1.parserAttrDcls ++ ag2.parserAttrDcls;
}



