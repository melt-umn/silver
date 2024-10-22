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
  top.lexerClassList = [^d];
  top.valueList = [^d];
}

fun parserAttrDef Def ::= sg::String sl::Location fn::String ty::Type =
  valueDef(defaultEnvItem(parserAttrDcl(fn,ty,sourceGrammar=sg,sourceLocation=sl)));

fun pluckTermDef Def ::= sg::String sl::Location fn::String =
  valueDef(defaultEnvItem(pluckTermDcl(fn,sourceGrammar=sg,sourceLocation=sl)));

fun lexerClassDef Def ::= sg::String sl::Location fn::String sc::[String] =
  lxrClsDef(defaultEnvItem(lexerClassDcl(fn,sc,sourceGrammar=sg,sourceLocation=sl)));

fun termAttrValueDef Def ::= sg::String sl::Location fn::String ty::Type =
  valueDef(defaultEnvItem(termAttrValueDcl(fn,ty,sourceGrammar=sg,sourceLocation=sl)));

fun actionChildDef Def ::= sg::String sl::Location fn::String ty::Type =
  valueDef(defaultEnvItem(actionChildDcl(fn,ty,sourceGrammar=sg,sourceLocation=sl)));

fun parserLocalDef Def ::= sg::String sl::Location fn::String ty::Type =
  valueDef(defaultEnvItem(parserLocalDcl(fn,ty,sourceGrammar=sg,sourceLocation=sl)));

--------------------------------------------------------------------------------
-- Env.sv

synthesized attribute lexerClassTree :: EnvTree<ValueDclInfo> occurs on Env;

aspect production emptyEnv
top::Env ::=
{
  top.lexerClassTree = emptyEnvTree();
}

aspect production appendEnv
top::Env ::= e1::Env  e2::Env
{
  top.lexerClassTree = appendEnvTree(e1.lexerClassTree, e2.lexerClassTree);
}

aspect production newScopeEnv
top::Env ::= _  e::Env
{
  top.lexerClassTree = consEnvTree(d.lexerClassList, e.lexerClassTree);
}

aspect production occursEnv
top::Env ::= _  e::Env
{
  top.lexerClassTree = e.lexerClassTree;
}

fun getLexerClassDcl [ValueDclInfo] ::= search::String e::Env =
  searchEnvTree(search, e.lexerClassTree);

fun expandTransitiveSuperClasses [String] ::= seen::[String] toExpand::[String] e::Env =
  case toExpand of
  | [] -> seen
  | c :: cs ->
    if contains(c, seen)
    then expandTransitiveSuperClasses(seen, cs, e)
    else expandTransitiveSuperClasses(
      c :: seen, flatMap((.superClasses), getLexerClassDcl(c, e)) ++ cs, e)
  end;

--------------------------------------------------------------------------------
-- QName.sv

synthesized attribute lookupLexerClass :: QNameLookup<ValueDclInfo> occurs on QName;

aspect production qNameId
top::QName ::= id::Name
{
  top.lookupLexerClass = customLookup("lexer class", getLexerClassDcl(top.name, top.env), top.name);
}

aspect production qNameCons
top::QName ::= id::Name ':' qn::QName
{
  top.lookupLexerClass = customLookup("lexer class", getLexerClassDcl(top.name, top.env), top.name);
}

aspect production qNameError
top::QName ::= msg::[Message]
{
  top.lookupLexerClass = errorLookup(msg);
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

