grammar silver:modification:copper;

--------------------------------------------------------------------------------
-- Defs.sv

synthesized attribute lexerClassList :: [EnvItem] occurs on Defs, Def;

aspect production nilDefs 
top::Defs ::= 
{
  top.lexerClassList = [];
}

aspect production consDefs 
top::Defs ::= e1::Def e2::Defs
{
  top.lexerClassList = e1.lexerClassList ++ e2.lexerClassList;
}

aspect default production
top::Def ::=
{
  top.lexerClassList = [];
}

abstract production lxrClsDef
top::Def ::= d::EnvItem
{
  top.dcl = d.dcl;
  top.lexerClassList = [d];
}

-- TODO: we don't do any renaming of lexer classes BUG

function parserAttrDef
Def ::= sg::String sl::Location fn::String ty::TypeExp
{
  return valueDef(defaultEnvItem(parserAttrDcl(sg,sl,fn,ty)));
}

function pluckTermDef
Def ::= sg::String sl::Location fn::String
{
  return valueDef(defaultEnvItem(pluckTermDcl(sg,sl,fn)));
}

function disambigLexemeDef
Def ::= sg::String sl::Location
{
  return valueDef(defaultEnvItem(disambigLexemeDcl(sg,sl)));
}

function lexerClassDef
Def ::= sg::String sl::Location fn::String
{
  return lxrClsDef(defaultEnvItem(lexerClassDcl(sg,sl,fn)));
}

function termAttrValueDef
Def ::= sg::String sl::Location fn::String ty::TypeExp
{
  return valueDef(defaultEnvItem(termAttrValueDcl(sg,sl,fn,ty)));
}

function actionChildDef
Def ::= sg::String sl::Location fn::String ty::TypeExp
{
  return valueDef(defaultEnvItem(actionChildDcl(sg,sl,fn,ty)));
}

function parserLocalDef
Def ::= sg::String sl::Location fn::String ty::TypeExp
{
  return valueDef(defaultEnvItem(parserLocalDcl(sg,sl,fn,ty)));
}

--------------------------------------------------------------------------------
-- Env.sv

synthesized attribute lexerClassTree :: Decorated EnvScope<DclInfo> occurs on Env;

aspect production i_emptyEnv
top::Env ::=
{
  top.lexerClassTree = emptyEnvScope();
}

aspect production i_appendEnv
top::Env ::= e1::Decorated Env  e2::Decorated Env
{
  top.lexerClassTree = appendEnvScope(e1.lexerClassTree, e2.lexerClassTree);
}

aspect production i_newScopeEnv
top::Env ::= d::Defs  e::Decorated Env
{
  top.lexerClassTree = consEnvScope(buildTree(d.lexerClassList), e.lexerClassTree);
}

function getLexerClassDcl
[DclInfo] ::= search::String e::Decorated Env
{
  return searchEnvScope(search, e.lexerClassTree);
}

--------------------------------------------------------------------------------
-- QName.sv

aspect production qNameId
top::QName ::= id::Name
{
  top.lookupLexerClass = decorate customLookup("lexer class", getLexerClassDcl(top.name, top.env), top.name, top.location) with {};
}

aspect production qNameCons
top::QName ::= id::Name ':' qn::QName
{
  top.lookupLexerClass = decorate customLookup("lexer class", getLexerClassDcl(top.name, top.env), top.name, top.location) with {};
}

synthesized attribute lookupLexerClass :: Decorated QNameLookup occurs on QName;

