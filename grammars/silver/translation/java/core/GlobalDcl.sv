grammar silver:translation:java:core;

aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' t::TypeExpr '=' e::Expr ';'
{
  -- TODO: would be nice to use more specific types. double TODO: figure out what the problem was?
  top.initValues :=
      s"\tpublic static final common.Thunk<Object> ${id.name}" ++
      s" = ${wrapThunkText("common.TopNode.singleton", e.translation, "Object")};\n";
}
