grammar silver:definition:core;

{--
 - The file containing this tree. (Still used, but legacy now that terminals carry filenames.)
 -}
autocopy attribute file :: String;
{--
 - The grammar containing this tree.
 -}
autocopy attribute grammarName :: String;
{--
 - The environment. Dun dun dunnn.
 -}
autocopy attribute env :: Decorated Env;

{--
 - The pretty pretty of a syntax tree.
 -}
synthesized attribute pp :: String;
{--
 - The location of this node in the original source file.
 -}
synthesized attribute location :: Location;

{--
 - Errors that should stop compilation from succeeding.
 -}
synthesized attribute errors :: [Message] with ++;

