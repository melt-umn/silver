grammar silver:compiler:extension:templating;

imports silver:compiler:definition:core;
imports silver:compiler:definition:env;
imports silver:compiler:definition:type;
imports silver:compiler:definition:type:syntax;
imports silver:compiler:metatranslation;
imports silver:reflect;
imports silver:langutil:pp;

imports silver:compiler:translation:java:core;

exports silver:compiler:extension:templating:syntax;

import silver:util:treeset as ts;

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

production stringAppendCall
top::Expr ::= a::Expr b::Expr
{
  top.unparse = s"${a.unparse} ++ ${b.unparse}";
  propagate freeVars;

  -- TODO: We really need eagerness analysis in Silver.
  -- Otherwise the translation for a large string template block contains
  -- new common.Thunk<Object>(new common.Thunk.Evaluable() { public final Object eval() { return ((common.StringCatter)silver.core.PstringAppend.invoke(${a.translation}, ${b.translation}); } })
  -- a ridiculous number of times, when it can just be translated as:
  top.translation = s"new common.StringCatter(${a.translation}, ${b.translation})";
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
  
  thread downSubst, upSubst on top, a, b, forward;
  -- These are wrapped in exprRef in the forward, so include their errors here:
  top.errors <- a.errors;
  top.errors <- b.errors;
  
  forwards to
    mkStrFunctionInvocation(
      top.location, "silver:core:stringAppend",
      [exprRef(a, location=a.location), exprRef(b, location=b.location)]);
}

terminal PPTemplate_kwd   'pp"""' lexer classes {LITERAL};
terminal SLPPTemplate_kwd 'pp"'   lexer classes {LITERAL};

-- These are translated by building a Document value and meta-translating the whole thing into an Expr
concrete production pptemplateExpr
top::Expr ::= PPTemplate_kwd t::TemplateString
layout {}
{
  forwards to translate(top.location, reflect(t.ppTemplate));
}

concrete production singleLinepptemplateExpr
top::Expr ::= SLPPTemplate_kwd t::SingleLineTemplateString
layout {}
{
  forwards to translate(top.location, reflect(t.ppTemplate));
}

production antiquoteDoc
top::Document ::= e::Expr
{ forwards to error("No forward"); }

aspect production nonterminalAST
top::AST ::= _ _ _
{
  directAntiquoteProductions <- ["silver:compiler:extension:templating:antiquoteDoc"];
}

synthesized attribute stringTemplate :: [Expr] occurs on TemplateString, SingleLineTemplateString,
                                                         TemplateStringBody, SingleLineTemplateStringBody,
                                                         TemplateStringBodyItem, SingleLineTemplateStringBodyItem, NonWater;
synthesized attribute ppTemplate :: Document occurs on TemplateString, SingleLineTemplateString,
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
  top.ppTemplate = notext();
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
  top.ppTemplate = notext();
}

aspect production bodyCons
top::TemplateStringBody ::= h::TemplateStringBodyItem  t::TemplateStringBody
{
  top.stringTemplate = h.stringTemplate ++ t.stringTemplate;
  top.ppTemplate = cat(h.ppTemplate, t.ppTemplate);
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
  top.ppTemplate = w.waterDoc;
}

aspect production singleLineBodyCons
top::SingleLineTemplateStringBody ::= h::SingleLineTemplateStringBodyItem  t::SingleLineTemplateStringBody
{
  top.stringTemplate = h.stringTemplate ++ t.stringTemplate;
  top.ppTemplate = cat(h.ppTemplate, t.ppTemplate);
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
  top.ppTemplate = w.waterDoc;
}

aspect production itemWaterEscape
top::TemplateStringBodyItem ::= w::Water nw::NonWater
{
  top.stringTemplate = [
    stringConst(terminal(String_t, "\"" ++ w.waterString ++ "\"", w.location), location=w.location)] ++
      nw.stringTemplate;
  top.ppTemplate = w.waterDoc;
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
  top.ppTemplate = w.waterDoc;
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
  top.ppTemplate = antiquoteDoc(mkStrFunctionInvocation(top.location, "silver:langutil:pp:pp", [e]));
}
