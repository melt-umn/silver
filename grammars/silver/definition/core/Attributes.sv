grammar silver:definition:core;

{--
 - The grammar containing this tree.
 -}
autocopy attribute grammarName :: String;
{--
 - Errors that should stop compilation from succeeding.
 -}
synthesized attribute errors :: [Message] with ++;

