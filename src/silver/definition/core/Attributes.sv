grammar silver:definition:core;
import silver:definition:env;

autocopy attribute userFriendly :: Integer;
--autocopy userFriendly
autocopy attribute expected :: Expected;
--autocopy expected

synthesized attribute name :: String;
autocopy attribute grammarName :: String;
--autocopy grammarName
synthesized attribute fileName :: String;
synthesized attribute pp :: String;
synthesized attribute location :: Decorated Location;

autocopy attribute file :: String;
--autocopy file

synthesized attribute names :: [String];
synthesized attribute envMaps :: [EnvMap];
synthesized attribute importedDefs :: Decorated Defs;

synthesized attribute exportSelf :: Boolean;

autocopy attribute globalImports :: Decorated Defs;
--autocopy globalImports
autocopy attribute grammarExportSelf :: Boolean;
--autocopy globalImportsSelf

autocopy attribute env :: Decorated Env;
--autocopy env

autocopy attribute signatureEnv :: Decorated Env; 
--autocopy signatureEnv

autocopy attribute localsEnv :: Decorated Env; 
--autocopy localsEnv

synthesized attribute errors :: [Decorated Message] with ++;
synthesized attribute warnings :: [Decorated Message] with ++;

autocopy attribute compiledGrammars :: [Decorated RootSpec];
--autocopy compileGrammars

autocopy attribute expectedInputTypes :: [Decorated TypeRep];
--autocopy expectedInputTypes

synthesized attribute exprs :: [Decorated Expr];

autocopy attribute realSignature :: [Decorated NamedSignatureElement];
--autocopy realSignature
autocopy attribute signature :: Decorated NamedSignature;
--autocopy signature
synthesized attribute productionAttributes :: Decorated Defs;
