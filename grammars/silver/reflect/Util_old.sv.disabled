grammar silver:reflect;

imports silver:reflect:concretesyntax;
import silver:langutil;

function reflect
AST ::= x::a
{
  return error("Foreign function");
} foreign {
  "java" : return "(common.Reflection.reflect(%x%))";
}

function reflectTypeName
Maybe<String> ::= x::a
{
  return error("Foreign function");
} foreign {
  "java" : return "(common.Reflection.reflectTypeName(%x%))";
}

function nativeToString
String ::= x::a
{
  return error("Foreign function");
} foreign {
  "java" : return "(new common.StringCatter(%x%.toString()))";
}

function applyAST
Either<String AST> ::= fn::AST args::[Maybe<AST>] namedArgs::[Pair<String Maybe<AST>>]
{
  return error("Foreign function");
} foreign {
  "java" : return "(common.Reflection.applyAST(%fn%, %args%, %namedArgs%))";
}

function serialize
Either<String String> ::= x::a
{
  return reflect(x).serialize;
}

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
