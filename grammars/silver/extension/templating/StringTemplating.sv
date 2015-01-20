grammar silver:extension:templating;

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:type;
imports silver:definition:type:syntax;

exports silver:extension:templating:syntax;

terminal Template_kwd  'template' lexer classes {RESERVED, KEYWORD};
terminal SQuote_kwd    's"""'     lexer classes {RESERVED, KEYWORD};

concrete production templateExpr
top::Expr ::= 'template' t::TemplateString
{
  forwards to infold(plusPlus(_, '++', _, location=top.location), t.stringTemplate);
}

concrete production sExpr
top::Expr ::= 's"""' t::UnquoteTemplateString
layout {}
{
  forwards to infold(plusPlus(_, '++', _, location=top.location), t.stringTemplate);
}

terminal PPTemplate_kwd  'pptemplate' lexer classes {RESERVED, KEYWORD};
terminal PPQuote_kwd     'pp"""'      lexer classes {RESERVED, KEYWORD};

concrete production pptemplateExpr
top::Expr ::= 'pptemplate' t::TemplateString
{
  forwards to infold(catcall(_, _, top.location), t.ppTemplate);
}

concrete production ppExpr
top::Expr ::= 'pp"""' t::UnquoteTemplateString
layout {}
{
  forwards to infold(catcall(_, _, top.location), t.ppTemplate);
}

function catcall
Expr ::= a::Expr b::Expr l::Location
{
  return mkStrFunctionInvocation(l, "silver:langutil:pp:cat", [a, b]);
}

-- TODO: make standard somehow?
function infold
a ::= f::(a ::= a a) l::[a]
{
  return if null(l) then error("invalid use of infold")
  else if null(tail(l)) then head(l)
  else f(head(l), infold(f, tail(l)));
}

synthesized attribute stringTemplate :: [Expr] occurs on TemplateString, UnquoteTemplateString, TemplateStringBody, TemplateStringBodyItem, NonWater;
synthesized attribute ppTemplate :: [Expr] occurs on TemplateString, UnquoteTemplateString, TemplateStringBody, TemplateStringBodyItem, NonWater;

aspect production templateString
top::TemplateString ::= _ b::TemplateStringBody _
{
  top.stringTemplate = b.stringTemplate;
  top.ppTemplate = b.ppTemplate;
}

aspect production unquoteTemplateString
top::UnquoteTemplateString ::= b::TemplateStringBody _
{
  top.stringTemplate = b.stringTemplate;
  top.ppTemplate = b.ppTemplate;
}

aspect production templateStringEmpty
top::TemplateString ::= _ _
{
  top.stringTemplate = [stringConst(terminal(String_t, "\"\"", top.location), location=top.location)];
  top.ppTemplate = [mkStrFunctionInvocation(top.location, "silver:langutil:pp:notext", [])];
}

aspect production unquoteTemplateStringEmpty
top::UnquoteTemplateString ::= _
{
  top.stringTemplate = [stringConst(terminal(String_t, "\"\"", top.location), location=top.location)];
  top.ppTemplate = [mkStrFunctionInvocation(top.location, "silver:langutil:pp:notext", [])];
}

aspect production bodyCons
top::TemplateStringBody ::= h::TemplateStringBodyItem  t::TemplateStringBody
{
  top.stringTemplate = h.stringTemplate ++ t.stringTemplate;
  top.ppTemplate = h.ppTemplate ++ t.ppTemplate;
}

aspect production bodyOne
top::TemplateStringBody ::= h::TemplateStringBodyItem
{
  top.stringTemplate = h.stringTemplate;
  top.ppTemplate = h.ppTemplate;
}

aspect production bodyOneWater
top::TemplateStringBody ::= w::Water
{
  top.stringTemplate = [stringConst(terminal(String_t, "\"" ++ w.waterString ++ "\"", w.location), location=w.location)];
  top.ppTemplate = [
    mkStrFunctionInvocation(w.location, "silver:langutil:pp:text", [
      stringConst(terminal(String_t, "\"" ++ w.waterString ++ "\"", w.location), location=w.location)])];
}

aspect production itemWaterEscape
top::TemplateStringBodyItem ::= w::Water nw::NonWater
{
  top.stringTemplate = [
    stringConst(terminal(String_t, "\"" ++ w.waterString ++ "\"", w.location), location=w.location)] ++
      nw.stringTemplate;
  top.ppTemplate = [
    mkStrFunctionInvocation(w.location, "silver:langutil:pp:text", [
      stringConst(terminal(String_t, "\"" ++ w.waterString ++ "\"", w.location), location=w.location)])] ++
      nw.ppTemplate;
}

aspect production itemEscape
top::TemplateStringBodyItem ::= nw::NonWater
{
  top.stringTemplate = nw.stringTemplate;
  top.ppTemplate = nw.ppTemplate;
}

aspect production nonwater
top::NonWater ::= '${' e::Expr '}'
{
  top.stringTemplate = [e];
  top.ppTemplate = [e];
}

