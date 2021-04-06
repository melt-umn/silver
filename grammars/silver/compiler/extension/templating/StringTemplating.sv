grammar silver:compiler:extension:templating;

imports silver:compiler:definition:core;
imports silver:compiler:definition:env;
imports silver:compiler:definition:type;
imports silver:compiler:definition:type:syntax;

imports silver:compiler:translation:java:core;

exports silver:compiler:extension:templating:syntax;

terminal Template_kwd   's"""' lexer classes {LITERAL};
terminal SLTemplate_kwd 's"'   lexer classes {LITERAL};

concrete production templateExpr
top::Expr ::= Template_kwd t::TemplateString
layout {}
{
  forwards to foldr1(stringAppendCall(_, _, location=top.location), t.stringTemplate);
}

concrete production singleLineTemplateExpr
top::Expr ::= SLTemplate_kwd t::SingleLineTemplateString
layout {}
{
  forwards to foldr1(stringAppendCall(_, _, location=top.location), t.stringTemplate);
}

terminal PPTemplate_kwd   'pp"""' lexer classes {LITERAL};
terminal SLPPTemplate_kwd 'pp"'   lexer classes {LITERAL};

concrete production pptemplateExpr
top::Expr ::= PPTemplate_kwd t::TemplateString
layout {}
{
  forwards to foldr1(catcall(_, _, top.location), t.ppTemplate);
}

concrete production singleLinepptemplateExpr
top::Expr ::= SLPPTemplate_kwd t::SingleLineTemplateString
layout {}
{
  forwards to foldr1(catcall(_, _, top.location), t.ppTemplate);
}

production stringAppendCall
top::Expr ::= a::Expr b::Expr
{
  -- TODO: We really need eagerness analysis in Silver.
  -- Otherwise the translation for a large string template block contains
  -- new common.Thunk<Object>(new common.Thunk.Evaluable() { public final Object eval() { return ((common.StringCatter)silver.core.PstringAppend.invoke(${a.translation}, ${b.translation}); } })
  -- a ridiculous number of times, when it can just be translated as:
  top.translation = s"new common.StringCatter(${a.translation}, ${b.translation})";
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
  
  thread downSubst, upSubst on top, a, b, forward;
  
  forwards to
    mkStrFunctionInvocation(
      top.location, "silver:core:stringAppend",
      [exprRef(a, location=a.location), exprRef(b, location=b.location)]);
}

function catcall
Expr ::= a::Expr b::Expr l::Location
{
  return mkStrFunctionInvocation(l, "silver:langutil:pp:cat", [a, b]);
}

synthesized attribute stringTemplate :: [Expr] occurs on TemplateString, SingleLineTemplateString,
                                                         TemplateStringBody, SingleLineTemplateStringBody,
                                                         TemplateStringBodyItem, SingleLineTemplateStringBodyItem, NonWater;
synthesized attribute ppTemplate :: [Expr] occurs on TemplateString, SingleLineTemplateString,
                                                     TemplateStringBody, SingleLineTemplateStringBody,
                                                     TemplateStringBodyItem, SingleLineTemplateStringBodyItem, NonWater;

aspect production templateString
top::TemplateString ::= b::TemplateStringBody _
{
  top.stringTemplate = b.stringTemplate;
  top.ppTemplate = b.ppTemplate;
}

aspect production templateStringEmpty
top::TemplateString ::= _
{
  top.stringTemplate = [stringConst(terminal(String_t, "\"\"", top.location), location=top.location)];
  top.ppTemplate = [mkStrFunctionInvocation(top.location, "silver:langutil:pp:notext", [])];
}

aspect production singleLineTemplateString
top::SingleLineTemplateString ::= b::SingleLineTemplateStringBody _
{
  top.stringTemplate = b.stringTemplate;
  top.ppTemplate = b.ppTemplate;
}

aspect production singleLineTemplateStringEmpty
top::SingleLineTemplateString ::= _
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

aspect production singleLineBodyCons
top::SingleLineTemplateStringBody ::= h::SingleLineTemplateStringBodyItem  t::SingleLineTemplateStringBody
{
  top.stringTemplate = h.stringTemplate ++ t.stringTemplate;
  top.ppTemplate = h.ppTemplate ++ t.ppTemplate;
}

aspect production singleLineBodyOne
top::SingleLineTemplateStringBody ::= h::SingleLineTemplateStringBodyItem
{
  top.stringTemplate = h.stringTemplate;
  top.ppTemplate = h.ppTemplate;
}

aspect production singleLineBodyOneWater
top::SingleLineTemplateStringBody ::= w::SingleLineWater
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

aspect production singleLineItemWaterEscape
top::SingleLineTemplateStringBodyItem ::= w::SingleLineWater nw::NonWater
{
  top.stringTemplate = [
    stringConst(terminal(String_t, "\"" ++ w.waterString ++ "\"", w.location), location=w.location)] ++
      nw.stringTemplate;
  top.ppTemplate = [
    mkStrFunctionInvocation(w.location, "silver:langutil:pp:text", [
      stringConst(terminal(String_t, "\"" ++ w.waterString ++ "\"", w.location), location=w.location)])] ++
      nw.ppTemplate;
}

aspect production singleLineItemEscape
top::SingleLineTemplateStringBodyItem ::= nw::NonWater
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
