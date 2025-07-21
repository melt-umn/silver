grammar silver:compiler:extension:nanopass;

aspect production compilation
top::Compilation ::= g::Grammars  r::Grammars  _ _ _
{
  astDependentGrammars <- flatMap(
    \ root::Decorated RootSpec -> map(\ gn::String -> (gn, root.declaredName), root.includedGrammars),
    grammarsRelevant);
}

attribute includedGrammars, includeTransDcls occurs on RootSpec, Grammar, File;
propagate includedGrammars, includeTransDcls on Grammar, File;
propagate includedGrammars on RootSpec;

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
  top.grammarAst =
    case deserializeBytes(i.unpackGrammarAst.fromJust) of
    | left(e) -> error("Error deserializing grammar AST: " ++ e)
    | right(g) -> g
    end;
  -- TODO: The deserialized AST lacks origin info, so non-clean builds will not
  -- have location info when reporting errors in the included declarations.
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

monoid attribute hasIncludedGrammars :: Boolean with false, ||;
attribute includedGrammars, hasIncludedGrammars occurs on InterfaceItems, InterfaceItem;

monoid attribute unpackGrammarAst :: Maybe<ByteArray> with empty, alt
  occurs on InterfaceItems, InterfaceItem;

propagate includedGrammars, hasIncludedGrammars, unpackGrammarAst on InterfaceItems;

aspect production consInterfaceItem
top::InterfaceItems ::= h::InterfaceItem t::InterfaceItems
{
  top.interfaceErrors <- if !top.hasIncludedGrammars then ["Missing item includedGrammars"] else [];
  top.interfaceErrors <- if !top.unpackGrammarAst.isJust then ["Missing item grammarAst"] else [];
}

aspect default production
top::InterfaceItem ::=
{
  top.includedGrammars := [];
  top.hasIncludedGrammars := false;
  top.unpackGrammarAst := empty;
}

production includedGrammarsInterfaceItem
top::InterfaceItem ::= val::[String]
{
  propagate isEqual;
  top.includedGrammars := val;
  top.hasIncludedGrammars := true;
}

production grammarAstInterfaceItem
top::InterfaceItem ::= ser::ByteArray
{
  top.isEqual = true;  -- Dirty included grammars are handled specially in the driver

  top.unpackGrammarAst := just(ser);
}

aspect function packInterfaceItems
InterfaceItems ::= r::Decorated RootSpec
{
  interfaceItems <- [includedGrammarsInterfaceItem(r.includedGrammars)];
  local ser::ByteArray =
    case serializeBytes(r.grammarAst) of
    | left(e) -> error("Error serializing grammar AST: " ++ e)
    | right(b) -> b
    end;
  interfaceItems <- [grammarAstInterfaceItem(ser)];
}
