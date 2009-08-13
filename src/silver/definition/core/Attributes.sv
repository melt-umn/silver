grammar silver:definition:core;
import silver:definition:env;


autocopy attribute expected :: Expected;
synthesized attribute name :: String;
autocopy attribute grammarName :: String;
synthesized attribute fileName :: String;
synthesized attribute pp :: String;
synthesized attribute location :: Decorated Location;

autocopy attribute file :: String;

synthesized attribute names :: [String];
synthesized attribute envMaps :: [EnvMap];
synthesized attribute importedDefs :: Decorated Defs;

synthesized attribute exportSelf :: Boolean;

autocopy attribute globalImports :: Decorated Defs;
autocopy attribute grammarExportSelf :: Boolean;

autocopy attribute env :: Decorated Env;
autocopy attribute signatureEnv :: Decorated Env; 
autocopy attribute localsEnv :: Decorated Env; 


synthesized attribute errors :: [Decorated Message] with ++;
synthesized attribute warnings :: [Decorated Message] with ++;

autocopy attribute compiledGrammars :: [Decorated RootSpec];

autocopy attribute expectedInputTypes :: [Decorated TypeRep];
synthesized attribute exprs :: [Decorated Expr];

autocopy attribute realSignature :: [Decorated NamedSignatureElement];
autocopy attribute signature :: Decorated NamedSignature;

synthesized attribute productionAttributes :: Decorated Defs;
