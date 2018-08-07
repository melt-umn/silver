grammar silver:modification:copper;

abstract production parserAttrDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::Type
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

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

  top.typerep = freshType(); -- no appropriate type to use...
  
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

  top.typerep = stringType();
  
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
}

abstract production termAttrValueDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::Type
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.typerep = ty;
  
  top.refDispatcher = termAttrValueReference(_, location=_);
  top.defDispatcher = termAttrValueValueDef(_, _, location=_);
  top.defLHSDispatcher = errorDefLHS(_, location=_);
}

abstract production actionChildDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::Type
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.typerep = ty;
  
  top.refDispatcher = actionChildReference(_, location=_);
  top.defDispatcher = errorValueDef(_, _, location=_);
  top.defLHSDispatcher = parserAttributeDefLHS(_, location=_); -- TODO: specialize this
}

abstract production parserLocalDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::Type
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.typerep = ty;
  
  -- TODO: use specialized ones that give better errors messages!
  top.refDispatcher = parserAttributeReference(_, location=_);
  top.defDispatcher = parserAttributeValueDef(_, _, location=_);
  top.defLHSDispatcher = parserAttributeDefLHS(_, location=_);
}

-- TODO: This sort of thing probably ought to be done on the CstAst and not be a part of the Silver environment pretending to be a value.
abstract production prefixSeparatorDcl
top::DclInfo ::= sg::String sl::Location sep::String
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = "_prefix_seperator";

  top.typerep = error("_prefix_seperator does not have a type");
}
