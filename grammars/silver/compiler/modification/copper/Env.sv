grammar silver:compiler:modification:copper;

--------------------------------------------------------------------------------
-- Defs.sv

synthesized attribute lexerClassList :: [EnvItem<ValueDclInfo>] occurs on Defs, Def;

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
top::Def ::= d::EnvItem<ValueDclInfo>
{
  propagate filterItems, filterIncludeOnly, filterIncludeHiding, withRenames, renamed, pfx, prepended, compareTo, isEqual;
  top.lexerClassList = [d];
  top.valueList = [d];
}

function parserAttrDef
Def ::= sg::String sl::Location fn::String ty::Type
{
  return valueDef(defaultEnvItem(parserAttrDcl(fn,ty,sourceGrammar=sg,sourceLocation=sl)));
}

function pluckTermDef
Def ::= sg::String sl::Location fn::String
{
  return valueDef(defaultEnvItem(pluckTermDcl(fn,sourceGrammar=sg,sourceLocation=sl)));
}

function lexerClassDef
Def ::= sg::String sl::Location fn::String sc::[String]
{
  return lxrClsDef(defaultEnvItem(lexerClassDcl(fn,sc,sourceGrammar=sg,sourceLocation=sl)));
}

function termAttrValueDef
Def ::= sg::String sl::Location fn::String ty::Type
{
  return valueDef(defaultEnvItem(termAttrValueDcl(fn,ty,sourceGrammar=sg,sourceLocation=sl)));
}

function actionChildDef
Def ::= sg::String sl::Location fn::String ty::Type
{
  return valueDef(defaultEnvItem(actionChildDcl(fn,ty,sourceGrammar=sg,sourceLocation=sl)));
}

function parserLocalDef
Def ::= sg::String sl::Location fn::String ty::Type
{
  return valueDef(defaultEnvItem(parserLocalDcl(fn,ty,sourceGrammar=sg,sourceLocation=sl)));
}

--------------------------------------------------------------------------------
-- Env.sv

synthesized attribute lexerClassTree :: EnvTree<ValueDclInfo> occurs on Env;

aspect production i_emptyEnv
top::Env ::=
{
  top.lexerClassTree = emptyEnvTree();
}

aspect production i_appendEnv
top::Env ::= e1::Decorated Env  e2::Decorated Env
{
  top.lexerClassTree = appendEnvTree(e1.lexerClassTree, e2.lexerClassTree);
}

aspect production i_newScopeEnv
top::Env ::= d::Defs  e::Decorated Env
{
  top.lexerClassTree = consEnvTree(d.lexerClassList, e.lexerClassTree);
}

aspect production i_occursEnv
top::Env ::= _  e::Decorated Env
{
  top.lexerClassTree = e.lexerClassTree;
}

function getLexerClassDcl
[ValueDclInfo] ::= search::String e::Decorated Env
{
  return searchEnvTree(search, e.lexerClassTree);
}

function expandTransitiveSuperClasses
[String] ::= seen::[String] toExpand::[String] e::Decorated Env
{
  return
    case toExpand of
    | [] -> seen
    | c :: cs ->
      if contains(c, seen)
      then expandTransitiveSuperClasses(seen, cs, e)
      else expandTransitiveSuperClasses(
        c :: seen, flatMap((.superClasses), getLexerClassDcl(c, e)) ++ cs, e)
    end;
}

--------------------------------------------------------------------------------
-- QName.sv

synthesized attribute lookupLexerClass :: Decorated QNameLookup<ValueDclInfo> occurs on QName;

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

aspect production qNameError
top::QName ::= msg::[Message]
{
  top.lookupLexerClass = decorate errorLookup(msg) with {};
}


--------------------------------------------------------------------------------

-- Some pre-defined variables in certain contexts

global i_lexemeVariable :: [Def] =
  [termAttrValueDef("DBGtav", bogusLoc(), "lexeme", stringType())];
global i_shiftableVariable :: [Def] =
  [termAttrValueDef("DBGtav", bogusLoc(), "shiftable", listType(terminalIdType()))];
global i_locVariables :: [Def] = [
  termAttrValueDef("DBGtav", bogusLoc(), "filename", stringType()),
  termAttrValueDef("DBGtav", bogusLoc(), "line", intType()),
  termAttrValueDef("DBGtav", bogusLoc(), "column", intType())];

global terminalActionVars :: [Def] = i_lexemeVariable ++ i_locVariables;
global productionActionVars :: [Def] = i_locVariables;
global disambiguationActionVars :: [Def] = i_lexemeVariable ++ i_locVariables;
global disambiguationClassActionVars :: [Def] = i_lexemeVariable ++ i_shiftableVariable ++ i_locVariables;

