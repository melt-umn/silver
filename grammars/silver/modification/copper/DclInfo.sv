grammar silver:modification:copper;

abstract production parserAttrDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  ty.boundVariables = top.boundVariables; -- explicit to make sure it errors if we can't
  top.unparse = "parse_attr(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ ty.unparse ++ ")";
  
  top.typerep = ty;
  
  top.refDispatcher = parserAttributeReference;
  top.defDispatcher = parserAttributeValueDef;
  top.defLHSDispatcher = parserAttributeDefLHS;
}

abstract production pluckTermDcl
top::DclInfo ::= sg::String sl::Location fn::String
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.unparse = error("Internal compiler error: locally scoped declaration that should never appear in interface files");
  
  --top.typerep = errorType();
  top.typerep = freshType(); -- #HACK2012 Issue 4
  
  top.refDispatcher = pluckTerminalReference;
  top.defDispatcher = errorValueDef;
  top.defLHSDispatcher = errorDefLHS;
}

abstract production disambigLexemeDcl
top::DclInfo ::= sg::String sl::Location
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = "lexeme";

  top.unparse = error("Internal compiler error: locally scoped declaration that should never appear in interface files");
  
  top.typerep = stringTypeExp();
  
  top.refDispatcher = disambigLexemeReference;
  top.defDispatcher = errorValueDef;
  top.defLHSDispatcher = errorDefLHS;
}

abstract production lexerClassDcl
top::DclInfo ::= sg::String sl::Location fn::String
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;
  
  top.typerep = error("Internal compiler error: lexer classes do not have types");

  top.unparse = "lexer_class(" ++ sl.unparse ++ ", '" ++ fn ++ "')";
}

abstract production termAttrValueDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.unparse = error("Internal compiler error: locally scoped declaration that should never appear in interface files");
  
  top.typerep = ty;
  
  top.refDispatcher = termAttrValueReference;
  top.defDispatcher = termAttrValueValueDef;
  top.defLHSDispatcher = errorDefLHS;
}

abstract production actionChildDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.unparse = error("Internal compiler error: locally scoped declaration that should never appear in interface files");
  
  top.typerep = ty;
  
  top.refDispatcher = actionChildReference;
  top.defDispatcher = errorValueDef;
  top.defLHSDispatcher = parserAttributeDefLHS; -- TODO: specialize this
}

abstract production parserLocalDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.unparse = error("Internal compiler error: locally scoped declaration that should never appear in interface files");
  
  top.typerep = ty;
  
  -- TODO: use specialized ones that give better errors messages!
  top.refDispatcher = parserAttributeReference;
  top.defDispatcher = parserAttributeValueDef;
  top.defLHSDispatcher = parserAttributeDefLHS;
}

