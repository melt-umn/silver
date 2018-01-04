grammar core:reflect;

function reflectAST
AST ::= x::a
{
  return error("Foreign function");
} foreign {
  "java" : return "(common.Reflection.reflect(%x%))";
}
