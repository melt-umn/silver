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
 - Errors that should stop compilation from succeeding.
 -}
synthesized attribute errors :: [Message] with ++;

