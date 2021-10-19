grammar silver:compiler:modification:impide;

--------------------------------------------------------------------------------
-- DclInfo.sv

-- We actually don't need to put font info on this, do we? cool!

-- Ugh, typeScheme needs to occur here since we are (ab)using QNameLookup
nonterminal FontDclInfo with fullName, typeScheme, sourceGrammar, sourceLocation;

abstract production fontDcl
top::FontDclInfo ::= fn::String
{
  top.fullName = fn;
  top.typeScheme = error("Internal compiler error: font style do not have types");
}

--------------------------------------------------------------------------------
-- Defs.sv

synthesized attribute fontDefList :: [EnvItem<FontDclInfo>] occurs on Defs, Def;

aspect production nilDefs 
top::Defs ::= 
{
  top.fontDefList = [];
}

aspect production consDefs 
top::Defs ::= e1::Def e2::Defs
{
  top.fontDefList = e1.fontDefList ++ e2.fontDefList;
}

aspect default production
top::Def ::=
{
  top.fontDefList = [];
}

abstract production fontStyleDef
top::Def ::= d::EnvItem<FontDclInfo>
{
  propagate filterItems, filterIncludeOnly, filterIncludeHiding, withRenames, renamed, pfx, prepended;
  top.fontDefList = [d];
}

-- TODO: we don't do any renaming of fonts or anything

function fontDef
Def ::= sg::String sl::Location fn::String
{
  return fontStyleDef(defaultEnvItem(fontDcl(fn,sourceGrammar=sg,sourceLocation=sl)));
}

--------------------------------------------------------------------------------
-- Env.sv

synthesized attribute fontDefTree :: EnvTree<FontDclInfo> occurs on Env;

aspect production i_emptyEnv
top::Env ::=
{
  top.fontDefTree = emptyEnvTree();
}

aspect production i_appendEnv
top::Env ::= e1::Decorated Env  e2::Decorated Env
{
  top.fontDefTree = appendEnvTree(e1.fontDefTree, e2.fontDefTree);
}

aspect production i_newScopeEnv
top::Env ::= d::Defs  e::Decorated Env
{
  top.fontDefTree = consEnvTree(d.fontDefList, e.fontDefTree);
}

aspect production i_occursEnv
top::Env ::= _  e::Decorated Env
{
  top.fontDefTree = e.fontDefTree;
}

function getFontDcl
[FontDclInfo] ::= search::String e::Decorated Env
{
  return searchEnvTree(search, e.fontDefTree);
}

--------------------------------------------------------------------------------
-- QName.sv

aspect production qNameId
top::QName ::= id::Name
{
  top.lookupFont = decorate customLookup("font style", getFontDcl(top.name, top.env), top.name, top.location) with {};
}

aspect production qNameCons
top::QName ::= id::Name ':' qn::QName
{
  top.lookupFont = decorate customLookup("font style", getFontDcl(top.name, top.env), top.name, top.location) with {};
}

aspect production qNameError
top::QName ::= msg::[Message]
{
  top.lookupFont = decorate errorLookup(msg) with {};
}

synthesized attribute lookupFont :: Decorated QNameLookup<FontDclInfo> occurs on QName;

