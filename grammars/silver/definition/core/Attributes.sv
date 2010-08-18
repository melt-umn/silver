grammar silver:definition:core;
import silver:definition:env;


autocopy attribute expected :: Expected;
synthesized attribute name :: String;
autocopy attribute grammarName :: String;
synthesized attribute pp :: String;
synthesized attribute location :: Decorated Location;

autocopy attribute file :: String;

synthesized attribute names :: [String];
synthesized attribute importedDefs :: Defs;

autocopy attribute globalImports :: Decorated Env;

autocopy attribute env :: Decorated Env;

synthesized attribute errors :: [Decorated Message] with ++;
synthesized attribute warnings :: [Decorated Message] with ++;

autocopy attribute compiledGrammars :: [Decorated RootSpec];

autocopy attribute expectedInputTypes :: [TypeExp];
synthesized attribute exprs :: [Decorated Expr];

autocopy attribute realSignature :: [Decorated NamedSignatureElement];
autocopy attribute signature :: Decorated NamedSignature;

synthesized attribute productionAttributes :: Defs;
