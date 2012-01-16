grammar silver:definition:core;

{--
 - An identifier's (possibly qualified) name.
 -}
synthesized attribute name :: String;
{--
 - The pretty pretty of a syntax tree.
 -}
synthesized attribute pp :: String;
{--
 - The location of this node in the original source file.
 -}
synthesized attribute location :: Location;
{--
 - The file containing this tree. (Still used, but legacy now that terminals carry filenames.)
 -}
autocopy attribute file :: String;
{--
 - The grammar containing this tree.
 -}
autocopy attribute grammarName :: String;


{--
 - Grammar-wide imports definitions.  Exists because we need to place
 - a file's individual imports between grammar definitions and grammar
 - wide imports.
 -}
autocopy attribute globalImports :: Decorated Env;
{--
 - All grammars Silver looked at. Despite the name, including interface files.
 -}
autocopy attribute compiledGrammars :: [Decorated RootSpec];


{--
 - A list of (possibly qualified) identifiers
 -}
synthesized attribute names :: [String];
{--
 - Imported definitions.  On Root and ModuleStmts this is for the whole grammar.
 - On ImportStmts, this is just for the file.
 -}
synthesized attribute importedDefs :: Defs;

{--
 - The environment. Dun dun dunnn.
 -}
autocopy attribute env :: Decorated Env;

{--
 - Errors that should stop compilation from succeeding.
 -}
synthesized attribute errors :: [Message] with ++;
{--
 - Warnings of code that looks wrong.
 -}
synthesized attribute warnings :: [Message] with ++;

{--
 - A list of decorated expressions from an Exprs.
 -}
synthesized attribute exprs :: [Decorated Expr];

{--
 - The signature elements from the fun/produciton being aspected.
 -}
autocopy attribute realSignature :: [Decorated NamedSignatureElement];
{--
 - The signature of this fun/production, given to the production's body.
 -}
autocopy attribute signature :: Decorated NamedSignature;

{--
 - Defs of attributes that should be wrapped up as production attributes.
 -}
synthesized attribute productionAttributes :: Defs;

{--
 - The nonterminal being decorated. (Used for 'decorate with {}')
 -}
autocopy attribute decoratingnt :: TypeExp;

