grammar silver:definition:core;

synthesized attribute name :: String;
synthesized attribute pp :: String;
synthesized attribute location :: Decorated Location;
autocopy attribute file :: String;
autocopy attribute grammarName :: String;


autocopy attribute globalImports :: Decorated Env;
autocopy attribute compiledGrammars :: [Decorated RootSpec];


autocopy attribute expected :: Expected;

synthesized attribute names :: [String];
synthesized attribute importedDefs :: Defs;

autocopy attribute env :: Decorated Env;

synthesized attribute errors :: [Decorated Message] with ++;
synthesized attribute warnings :: [Decorated Message] with ++;

autocopy attribute expectedInputTypes :: [TypeExp];
synthesized attribute exprs :: [Decorated Expr];

autocopy attribute realSignature :: [Decorated NamedSignatureElement];
autocopy attribute signature :: Decorated NamedSignature;

synthesized attribute productionAttributes :: Defs;

autocopy attribute decoratingnt :: TypeExp;

