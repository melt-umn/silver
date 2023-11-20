grammar silver:reflect;

exports silver:reflect:util;

imports silver:reflect:concretesyntax;
import silver:langutil;

fun serialize Either<String String> ::= x::a = reflect(x).serialize;

parser astParser :: AST_c {
  silver:reflect:concretesyntax;
}

function deserializeAST
Either<String AST> ::= fileName::String text::String
{
  local result::ParseResult<AST_c> = astParser(text, fileName);
  local parseTree::AST_c = result.parseTree;

  return
    if !result.parseSuccess
    then left(result.parseErrors)
    else if !null(parseTree.errors)
    then left(messagesToString(parseTree.errors))
    else right(parseTree.ast);
}

fun deserialize runtimeTypeable a => Either<String a> ::= fileName::String text::String =
  bind(deserializeAST(fileName, text), reify);
