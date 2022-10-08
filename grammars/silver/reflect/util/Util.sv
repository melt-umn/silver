grammar silver:reflect:util;

import silver:langutil;

function reflect
AST ::= x::a
{
  return error("Foreign function");
} foreign {
  "java" : return "(common.Reflection.reflect((originCtx!=null)?originCtx.rulesAsSilverList():null, %x%))";
}

function reify
runtimeTypeable a => Either<String a> ::= x::AST
{
  return error("Foreign function");
} foreign {
  "java" : return "common.Reflection.reifyChecked((originCtx!=null)?originCtx.rulesAsSilverList():null, %@0@%, (silver.core.NAST)%x%)";
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
  "java" : return "(common.Reflection.applyAST(originCtx, %fn%, %args%, %namedArgs%))";
}

function reifyUnchecked
runtimeTypeable a => a ::= x::AST
{
  return 
    case reify(x) of
    | left(msg) -> error(msg)
    | right(a) -> a
    end;
}
