grammar silver:definition:core;

{--
 - The grammar containing this tree.
 -}
autocopy attribute grammarName :: String;

{--
 - The name to use for the generated .jar if not overridden by -o command-line option.
 -}
synthesized attribute jarName :: Maybe<String>;

