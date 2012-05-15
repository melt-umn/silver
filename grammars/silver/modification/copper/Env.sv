grammar silver:modification:copper;

--------------------------------------------------------------------------------
-- Defs.sv

synthesized attribute lexerClassList :: [EnvItem] occurs on Defs;

aspect function unparseDefs
String ::= d::Defs bv::[TyVar]
{
  dclinfos <- mapGetDcls(d.lexerClassList);
}

aspect production emptyDefs 
top::Defs ::= 
{
  top.lexerClassList = [];
}

aspect production appendDefs 
top::Defs ::= e1::Defs e2::Defs
{
  top.lexerClassList = e1.lexerClassList ++ e2.lexerClassList;
}

abstract production consLexerClassDef
top::Defs ::= d::EnvItem e2::Defs
{
  top.lexerClassList = d :: forward.lexerClassList;
  forwards to e2;
}

-- TODO: we don't do any renaming of lexer classes BUG

function addParserAttrDcl
Defs ::= sg::String sl::Location fn::String ty::TypeExp defs::Defs
{
  return consValueDef(defaultEnvItem(parserAttrDcl(sg,sl,fn,ty)), defs);
}

function addPluckTermDcl
Defs ::= sg::String sl::Location fn::String defs::Defs
{
  return consValueDef(defaultEnvItem(pluckTermDcl(sg,sl,fn)), defs);
}

function addDisambigLexemeDcl
Defs ::= sg::String sl::Location defs::Defs
{
  return consValueDef(defaultEnvItem(disambigLexemeDcl(sg,sl)), defs);
}

function addLexerClassDcl
Defs ::= sg::String sl::Location fn::String defs::Defs
{
  return consLexerClassDef(defaultEnvItem(lexerClassDcl(sg,sl,fn)), defs);
}

function addTermAttrValueDcl
Defs ::= sg::String sl::Location fn::String ty::TypeExp defs::Defs
{
  return consValueDef(defaultEnvItem(termAttrValueDcl(sg,sl,fn,ty)), defs);
}

function addActionChildDcl
Defs ::= sg::String sl::Location fn::String ty::TypeExp defs::Defs
{
  return consValueDef(defaultEnvItem(actionChildDcl(sg,sl,fn,ty)), defs);
}

function addParserLocalDcl
Defs ::= sg::String sl::Location fn::String ty::TypeExp defs::Defs
{
  return consValueDef(defaultEnvItem(parserLocalDcl(sg,sl,fn,ty)), defs);
}

--------------------------------------------------------------------------------
-- Env.sv

inherited attribute lexerClassTree :: Decorated EnvScope<DclInfo> occurs on Env;

aspect function emptyEnv
Decorated Env ::=
{
  top.lexerClassTree = emptyEnvScope();
}

aspect function toEnv
Decorated Env ::= d::Defs
{
  top.lexerClassTree = oneEnvScope(buildTree(d.lexerClassList));
}

aspect function appendEnv
Decorated Env ::= e1::Decorated Env  e2::Decorated Env
{
  top.lexerClassTree = appendEnvScope(e1.lexerClassTree, e2.lexerClassTree);
}

aspect function newScopeEnv
Decorated Env ::= d::Defs  e::Decorated Env
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
  top.lookupLexerClass = decorate customLookup("lexer class", getLexerClassDcl, top.name, top.location) with { env = top.env; };
}

aspect production qNameCons
top::QName ::= id::Name ':' qn::QName
{
  top.lookupLexerClass = decorate customLookup("lexer class", getLexerClassDcl, top.name, top.location) with { env = top.env; };
}

synthesized attribute lookupLexerClass :: Decorated QNameLookup occurs on QName;

