grammar silver:extension:templating;

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:type;
imports silver:definition:type:syntax;

exports silver:extension:templating:syntax;

terminal Template_kwd  'template' lexer classes {RESERVED, KEYWORD};

-- TODO: make standard somehow?
function infold
a ::= f::(a ::= a a) l::[a]
{
  return if null(l) then error("invalid use of infold")
  else if null(tail(l)) then head(l)
  else f(head(l), infold(f, tail(l)));
}

concrete production templateExpr
top::Expr ::= 'template' t::TemplateString
{
  forwards to infold(plusPlus(_, '++', _, location=top.location), t.stringTemplate);
}

synthesized attribute stringTemplate :: [Expr] occurs on TemplateString, TemplateStringBody, TemplateStringBodyItem;

aspect production templateString
top::TemplateString ::= _ b::TemplateStringBody _
{
  top.stringTemplate = b.stringTemplate;
}

aspect production templateStringEmpty
top::TemplateString ::= _ _
{
  top.stringTemplate = [stringConst(terminal(String_t, "\"\"", top.location), location=top.location)];
}

aspect production bodyCons
top::TemplateStringBody ::= h::TemplateStringBodyItem  t::TemplateStringBody
{
  top.stringTemplate = h.stringTemplate ++ t.stringTemplate;
}

aspect production bodyOne
top::TemplateStringBody ::= h::TemplateStringBodyItem
{
  top.stringTemplate = h.stringTemplate;
}

aspect production bodyOneWater
top::TemplateStringBody ::= w::Water
{
  top.stringTemplate = [stringConst(terminal(String_t, "\"" ++ w.waterString ++ "\"", w.location), location=top.location)];
}

aspect production itemWaterEscape
top::TemplateStringBodyItem ::= w::Water '${' e::Expr '}'
{
  top.stringTemplate = [stringConst(terminal(String_t, "\"" ++ w.waterString ++ "\"", w.location), location=w.location), e];
}

aspect production itemEscape
top::TemplateStringBodyItem ::= '${' e::Expr '}'
{
  top.stringTemplate = [e];
}

