grammar silver:reflect;

imports silver:reflect:concretesyntax;

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

parser astParser :: AST_c {
  silver:host;
  silver:extension:list;
  
  silver:reflect:concretesyntax;
}

function parseAST
Either<String AST> ::= fileName::String text::String
{
  local result::ParseResult<AST_c> = astParser(text, fileName);

  return
    if result.parseSuccess
    then right(result.parseTree.ast)
    else left(result.parseErrors);
}
