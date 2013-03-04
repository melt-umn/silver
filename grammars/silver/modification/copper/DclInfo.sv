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
  
  top.refDispatcher = parserAttributeReference(_, location=_);
  top.defDispatcher = parserAttributeValueDef(_, _, location=_);
  top.defLHSDispatcher = parserAttributeDefLHS(_, location=_);
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
  
  top.refDispatcher = pluckTerminalReference(_, location=_);
  top.defDispatcher = errorValueDef(_, _, location=_);
  top.defLHSDispatcher = errorDefLHS(_, location=_);
}

abstract production disambigLexemeDcl
top::DclInfo ::= sg::String sl::Location
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = "lexeme";

  top.unparse = error("Internal compiler error: locally scoped declaration that should never appear in interface files");
  
  top.typerep = stringTypeExp();
  
  top.refDispatcher = disambigLexemeReference(_, location=_);
  top.defDispatcher = errorValueDef(_, _, location=_);
  top.defLHSDispatcher = errorDefLHS(_, location=_);
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
  
  top.refDispatcher = termAttrValueReference(_, location=_);
  top.defDispatcher = termAttrValueValueDef(_, _, location=_);
  top.defLHSDispatcher = errorDefLHS(_, location=_);
}

abstract production actionChildDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.unparse = error("Internal compiler error: locally scoped declaration that should never appear in interface files");
  
  top.typerep = ty;
  
  top.refDispatcher = actionChildReference(_, location=_);
  top.defDispatcher = errorValueDef(_, _, location=_);
  top.defLHSDispatcher = parserAttributeDefLHS(_, location=_); -- TODO: specialize this
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
  top.refDispatcher = parserAttributeReference(_, location=_);
  top.defDispatcher = parserAttributeValueDef(_, _, location=_);
  top.defLHSDispatcher = parserAttributeDefLHS(_, location=_);
}

