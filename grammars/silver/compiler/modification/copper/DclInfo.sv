grammar silver:compiler:modification:copper;

monoid attribute superClasses::[String] occurs on ValueDclInfo;

aspect default production
top::ValueDclInfo ::=
{
  top.superClasses := [];
}

{--
 - Reference to something declared as "parser attribute foo ..."
 -}
abstract production parserAttrDcl
top::ValueDclInfo ::= fn::String ty::Type
{
  top.fullName = fn;
  propagate isEqual;

  top.typeScheme = monoType(ty);
  
  top.refDispatcher = parserAttributeReference;
  top.defDispatcher = parserAttributeValueDef;
  top.defLHSDispatcher = parserAttributeDefLHS;
  top.transDefLHSDispatcher = \ q::Decorated! QName  Decorated! QNameAttrOccur ->
    parserAttributeDefLHS(q);
}

{--
 - The names of possible pluckable terminals are jammed in the environment using this dcl.
 -}
abstract production pluckTermDcl
top::ValueDclInfo ::= fn::String
{
  top.fullName = fn;
  propagate isEqual;

  -- TODO: Still needs work to prevent returning terminals
  -- that are not part of the disambiguation set.
  top.typeScheme = monoType(terminalIdType());
  
  top.refDispatcher = pluckTerminalReference;
  top.defDispatcher = errorValueDef;
  top.defLHSDispatcher = errorDefLHS;
  top.transDefLHSDispatcher = errorTransAttrDefLHS;
}

{--
 - Reference to a lexer class declaration. Has its own namespace in the environment, for now.
 -}
abstract production lexerClassDcl
top::ValueDclInfo ::= fn::String  superClasses::[String]
{
  top.fullName = fn;
  propagate isEqual;
  top.superClasses := superClasses;
  
  -- If we made lexer classes proper types, it might simplify a lot of code.
  -- We wouldn't need a separate namespace, they could just be in the type namespace.
  -- Currently referencing a lexer class gives a list of its member's TerminalIds.
  top.typeScheme = monoType(listType(terminalIdType()));
  top.refDispatcher = lexerClassReference;
  top.defDispatcher = errorValueDef;
  top.defLHSDispatcher = errorDefLHS;
  top.transDefLHSDispatcher = errorTransAttrDefLHS;
}

{--
 - lexeme/filename/line/column. Used in terminal and production action code.
 -}
abstract production termAttrValueDcl
top::ValueDclInfo ::= fn::String ty::Type
{
  top.fullName = fn;
  propagate isEqual;

  top.typeScheme = monoType(ty);
  
  top.refDispatcher = termAttrValueReference;
  top.defDispatcher = termAttrValueValueDef;
  top.defLHSDispatcher = errorDefLHS;
  top.transDefLHSDispatcher = errorTransAttrDefLHS;
}

{--
 - Reference to production's children from production action code.
 -}
abstract production actionChildDcl
top::ValueDclInfo ::= fn::String ty::Type
{
  top.fullName = fn;
  propagate isEqual;

  top.typeScheme = monoType(ty);
  
  top.refDispatcher = actionChildReference;
  top.defDispatcher = errorValueDef;
  top.defLHSDispatcher = parserAttributeDefLHS; -- TODO: specialize this
  top.transDefLHSDispatcher = \ q::Decorated! QName  Decorated! QNameAttrOccur ->
    parserAttributeDefLHS(q);
}

{--
 - Reference to a local variable ("local foo :: Type = ...") inside an action block.
 -}
abstract production parserLocalDcl
top::ValueDclInfo ::= fn::String ty::Type
{
  top.fullName = fn;
  propagate isEqual;

  top.typeScheme = monoType(ty);
  
  -- TODO: use specialized ones that give better errors messages!
  top.refDispatcher = parserAttributeReference;
  top.defDispatcher = parserAttributeValueDef;
  top.defLHSDispatcher = parserAttributeDefLHS;
  top.transDefLHSDispatcher = \ q::Decorated! QName  Decorated! QNameAttrOccur ->
    parserAttributeDefLHS(q);
}
