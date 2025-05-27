grammar silver:compiler:extension:nanopass;

attribute includeTransDcls occurs on RootSpec, Grammar, File;
propagate includeTransDcls on Grammar, File;

synthesized attribute grammarAst::Grammar occurs on RootSpec;

aspect production grammarRootSpec
top::RootSpec ::= g::Grammar  oldInterface::Maybe<InterfaceItems>  grammarName::String  grammarSource::String  grammarTime::Integer  generateLocation::String
{
  top.grammarAst = ^g;
  top.includeTransDcls := g.includeTransDcls;
}

aspect production interfaceRootSpec
top::RootSpec ::= i::InterfaceItems  generateLocation::Maybe<String>  jarSource::Maybe<String>
{
  top.grammarAst = i.unpackGrammarAst.fromRight;
  local g::Grammar = top.grammarAst;
  g.config = top.config;
  g.compiledGrammars = top.compiledGrammars;
  g.grammarName = top.declaredName;
  g.grammarDependencies = i.allGrammarDependencies;
  g.env = toEnv(i.defs, i.occursDefs);
  g.flowEnv = flowEnv(i.specDefs, i.refDefs, i.sharedRefs, foldr(consFlow, nilFlow(), i.flowDefs));

  -- TODO: This is an unfortunate duplication from grammarRootSpec.
  local coreGrammar::Decorated RootSpec = head(searchEnvTree("silver:core", top.compiledGrammars));
  local coreEnv::Env =
    if contains("silver:core", g.moduleNames) || g.grammarName == "silver:core"
    then emptyEnv()
    else toEnv(coreGrammar.defs, coreGrammar.occursDefs);
  g.globalImports = occursEnv(g.importedOccursDefs, newScopeEnv(g.importedDefs, coreEnv));

  top.includeTransDcls := g.includeTransDcls;
}

aspect production errorRootSpec
top::RootSpec ::= e::[ParseError]  grammarName::String  grammarSource::String  grammarTime::Integer  generateLocation::String
{
  top.grammarAst = nilGrammar();
  top.includeTransDcls := mempty;
}

monoid attribute unpackGrammarAst :: Either<String Grammar> with left("Missing grammarAst"), alt
  occurs on InterfaceItems, InterfaceItem;
propagate unpackGrammarAst on InterfaceItems;

aspect production consInterfaceItem
top::InterfaceItems ::= h::InterfaceItem t::InterfaceItems
{
  top.interfaceErrors <-
    case top.unpackGrammarAst of
    | left(e) -> [e]
    | right(_) -> []
    end;
}

aspect default production
top::InterfaceItem ::=
{
  top.unpackGrammarAst := left("Missing grammarAst");
}

production grammarAstInterfaceItem
top::InterfaceItem ::= ser::ByteArray
{
  top.isEqual = true;  -- Dirty included grammars are handled specially in the driver
  top.unpackGrammarAst := deserializeBytes(ser);
}

aspect function packInterfaceItems
InterfaceItems ::= r::Decorated RootSpec
{
  local ser::ByteArray =
    case serializeBytes(r.grammarAst) of
    | left(e) -> error("Error serializing grammar AST: " ++ e)
    | right(b) -> b
    end;
  interfaceItems <- [grammarAstInterfaceItem(ser)];
}
