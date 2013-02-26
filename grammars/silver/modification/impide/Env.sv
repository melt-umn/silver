grammar silver:modification:impide;

--------------------------------------------------------------------------------
-- DclInfo.sv

-- We actually don't need to put font info on this, do we? cool!

abstract production fontDcl
top::DclInfo ::= sg::String sl::Location fn::String
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;
  
  top.typerep = error("Internal compiler error: font style do not have types");

  top.unparse = "font(" ++ sl.unparse ++ ", '" ++ fn ++ "')";
}

--------------------------------------------------------------------------------
-- Defs.sv

synthesized attribute fontDefList :: [EnvItem] occurs on Defs, Def;

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
top::Def ::= d::EnvItem
{
  top.dcl = d.dcl;
  top.fontDefList = [d];
}

-- TODO: we don't do any renaming of fonts or anything

function fontDef
Def ::= sg::String sl::Location fn::String
{
  return fontStyleDef(defaultEnvItem(fontDcl(sg,sl,fn)));
}

--------------------------------------------------------------------------------
-- Env.sv

synthesized attribute fontDefTree :: Decorated EnvScope<DclInfo> occurs on Env;

aspect production i_emptyEnv
top::Env ::=
{
  top.fontDefTree = emptyEnvScope();
}

aspect production i_appendEnv
top::Env ::= e1::Decorated Env  e2::Decorated Env
{
  top.fontDefTree = appendEnvScope(e1.fontDefTree, e2.fontDefTree);
}

aspect production i_newScopeEnv
top::Env ::= d::Defs  e::Decorated Env
{
  top.fontDefTree = consEnvScope(buildTree(d.fontDefList), e.fontDefTree);
}

function getFontDcl
[DclInfo] ::= search::String e::Decorated Env
{
  return searchEnvScope(search, e.fontDefTree);
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

synthesized attribute lookupFont :: Decorated QNameLookup occurs on QName;

