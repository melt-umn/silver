grammar silver:compiler:extension:doc:core;

@{-
 - Represents a single setting (key = value) of a doc configuration option.
 - Some are file-scope, and some are grammar-scope (see @link[fileScope].)
 -}
data DocConfigSetting
  = splitConfig v::Boolean
  | fileSplitConfig v::Boolean
  | weightConfig v::Integer
  | grammarWeightConfig v::Integer
  | grammarTitleConfig v::String
  | titleConfig v::String
  | grammarNoDocsConfig v::Boolean
  | fileNoDocsConfig v::Boolean
  | tocConfig v::Boolean
  ;

@{-
 - Is this @link[DocConfigSetting] local to the file (e.g. @@title) or to the
 - grammar (e.g. @@grammarTitle)?
 -}
synthesized attribute fileScope::Boolean occurs on DocConfigSetting;
aspect fileScope on DocConfigSetting of
| splitConfig(_) -> true
| fileSplitConfig(_) -> true
| weightConfig(_) -> true
| grammarWeightConfig(_) -> false
| grammarTitleConfig(_) -> false
| titleConfig(_) -> true
| grammarNoDocsConfig(_) -> false
| fileNoDocsConfig(_) -> true
| tocConfig(_) -> true
end;


-- a grammar with @excludeGrammar containing file(s) with @excludeFile false will
-- only emit docs for that file(s)
fun doesExcludeFile Boolean ::= args::[DocConfigSetting] =
  case args of
  | fileNoDocsConfig(v)::_ -> v
  | grammarNoDocsConfig(true)::_ -> true
  | _::r -> doesExcludeFile(r)
  | [] -> false
  end;

fun getFileTitle String ::= args::[DocConfigSetting] fallback::String =
  case args of
  | titleConfig(x)::_ -> x
  | _::r -> getFileTitle(r, fallback)
  | [] -> fallback
  end;

fun getGrammarTitle String ::= args::[DocConfigSetting] fallback::String =
  case args of
  | grammarTitleConfig(x)::_ -> x
  | _::r -> getGrammarTitle(r, fallback)
  | [] -> fallback
  end;

fun getFileWeight Integer ::= args::[DocConfigSetting] =
  case args of
  | weightConfig(x)::_ -> x
  | _::r -> getFileWeight(r)
  | [] -> 0
  end;

fun getGrammarWeight Integer ::= args::[DocConfigSetting] =
  case args of
  | grammarWeightConfig(x)::_ -> x
  | _::r -> getGrammarWeight(r)
  | [] -> 0
  end;

fun getSplit Boolean ::= args::[DocConfigSetting] =
  case args of
  | fileSplitConfig(v)::_ -> v
  | splitConfig(true)::_ -> true
  | _::r -> getSplit(r)
  | [] -> false
  end;

fun getToc Boolean ::= args::[DocConfigSetting] =
  case args of
  | tocConfig(v)::_ -> v
  | _::r -> getToc(r)
  | [] -> false
  end;
