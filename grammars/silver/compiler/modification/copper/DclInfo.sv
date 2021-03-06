grammar silver:compiler:modification:copper;

{--
 - Reference to something declared as "parser attribute foo ..."
 -}
abstract production parserAttrDcl
top::DclInfo ::= fn::String ty::Type
{
  top.fullName = fn;

  top.typeScheme = monoType(ty);
  
  top.refDispatcher = parserAttributeReference(_, location=_);
  top.defDispatcher = parserAttributeValueDef(_, _, location=_);
  top.defLHSDispatcher = parserAttributeDefLHS(_, location=_);
}

{--
 - The names of possible pluckable terminals are jammed in the environment using this dcl.
 -}
abstract production pluckTermDcl
top::DclInfo ::= fn::String
{
  top.fullName = fn;

  -- TODO: Still needs work to prevent returning terminals
  -- that are not part of the disambiguation set.
  top.typeScheme = monoType(terminalIdType());
  
  top.refDispatcher = pluckTerminalReference(_, location=_);
  top.defDispatcher = errorValueDef(_, _, location=_);
  top.defLHSDispatcher = errorDefLHS(_, location=_);
}

{--
 - Reference to a lexer class declaration. Has its own namespace in the environment, for now.
 -}
abstract production lexerClassDcl
top::DclInfo ::= fn::String
{
  top.fullName = fn;
  
  -- If we made lexer classes proper types, it might simplify a lot of code.
  -- We wouldn't need a separate namespace, they could just be in the type namespace.
  -- Currently referencing a lexer class gives a list of its member's TerminalIds.
  top.typeScheme = monoType(listType(terminalIdType()));
  top.refDispatcher = lexerClassReference(_, location=_);
  top.defDispatcher = errorValueDef(_, _, location=_);
  top.defLHSDispatcher = errorDefLHS(_, location=_);
}

{--
 - lexeme/filename/line/column. Used in terminal and production action code.
 -}
abstract production termAttrValueDcl
top::DclInfo ::= fn::String ty::Type
{
  top.fullName = fn;

  top.typeScheme = monoType(ty);
  
  top.refDispatcher = termAttrValueReference(_, location=_);
  top.defDispatcher = termAttrValueValueDef(_, _, location=_);
  top.defLHSDispatcher = errorDefLHS(_, location=_);
}

{--
 - Reference to production's children from production action code.
 -}
abstract production actionChildDcl
top::DclInfo ::= fn::String ty::Type
{
  top.fullName = fn;

  top.typeScheme = monoType(ty);
  
  top.refDispatcher = actionChildReference(_, location=_);
  top.defDispatcher = errorValueDef(_, _, location=_);
  top.defLHSDispatcher = parserAttributeDefLHS(_, location=_); -- TODO: specialize this
}

{--
 - Reference to a local variable ("local foo :: Type = ...") inside an action block.
 -}
abstract production parserLocalDcl
top::DclInfo ::= fn::String ty::Type
{
  top.fullName = fn;

  top.typeScheme = monoType(ty);
  
  -- TODO: use specialized ones that give better errors messages!
  top.refDispatcher = parserAttributeReference(_, location=_);
  top.defDispatcher = parserAttributeValueDef(_, _, location=_);
  top.defLHSDispatcher = parserAttributeDefLHS(_, location=_);
}
