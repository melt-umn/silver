grammar silver:translation:java:core;

aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' t::Type '=' e::Expr ';'
{
  top.initValues := "\tpublic static final common.Thunk<" ++ t.typerep.transType ++ "> " ++ id.name 
      ++ " = new common.Thunk<" ++ t.typerep.transType ++ ">(" ++ wrapThunkText("common.TopNode.singleton", e.translation) ++ ");\n";
}
