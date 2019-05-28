grammar silver:modification:copper;

{--
 - Reference to something declared as "parser attribute foo ..."
 -}
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

{--
 - The names of possible pluckable terminals are jammed in the environment using this dcl.
 -}
abstract production pluckTermDcl
top::DclInfo ::= sg::String sl::Location fn::String
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.typerep = terminalIdType(); -- TODO: Still needs work to prevent returning terminals
                                  -- that are not part of the disambiguation set.
  
  top.refDispatcher = pluckTerminalReference(_, location=_);
  top.defDispatcher = errorValueDef(_, _, location=_);
  top.defLHSDispatcher = errorDefLHS(_, location=_);
}

{--
 - Reference to a lexer class declaration. Has its own namespace in the environment, for now.
 -}
abstract production lexerClassDcl
top::DclInfo ::= sg::String sl::Location fn::String
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;
  
  -- If we made lexer classes types, it might simplify a lot of code.
  -- We wouldn't need a separate namespace, they could just be in the type namespace.
  top.typerep = error("Internal compiler error: lexer classes do not have types");
}

{--
 - lexeme/filename/line/column. Used in terminal and production action code.
 -}
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

{--
 - Reference to production's children from production action code.
 -}
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

{--
 - Reference to a local variable ("local foo :: Type = ...") inside an action block.
 -}
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

