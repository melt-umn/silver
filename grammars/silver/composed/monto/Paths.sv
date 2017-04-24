grammar silver:composed:monto;

function pathsToGrammarName
Maybe<String> ::= grammarRoot::String file::String
{
  local grammarRootEndingWithSlash :: String =
    if endsWith("/", grammarRoot) then
      grammarRoot
    else
      stringConcat(grammarRoot, "/");
  return if startsWith(grammarRootEndingWithSlash, file) then
    just(substring(length(grammarRootEndingWithSlash), length(file), file))
  else
    nothing();
}

function grammarName
Maybe<String> ::= silverHome::String projectPath::String file::String
{
  return orElse(
    pathsToGrammarName(silverHome, file),
    pathsToGrammarName(projectPath, file));
}

{--
  Returns an error if a relative path is specified. Otherwise, functions as the
  identity function.

  TODO This _will **not** work_ on Windows.
 -}
function mustBeAbsPath
String ::= path::String
{
  return if startsWith("/", path) then
    path
  else
    error(stringConcat("not an absolute path: ", path));
}
