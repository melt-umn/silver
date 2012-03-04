grammar silver:definition:concrete_syntax:ast;

synthesized attribute dominatesXML :: String;
synthesized attribute submitsXML :: String;
synthesized attribute lexerclassesXML :: String;
synthesized attribute ignored :: Boolean;
synthesized attribute acode :: String;
synthesized attribute opPrecedence :: Maybe<Integer>;
synthesized attribute opAssociation :: Maybe<String>; -- TODO type?


{--
 - Modifiers for terminals.
 -}
nonterminal SyntaxTerminalModifiers with cstEnv, cstErrors, dominatesXML, submitsXML, ignored, acode, lexerclassesXML, opPrecedence, opAssociation, unparses;

abstract production consTerminalMod
top::SyntaxTerminalModifiers ::= h::SyntaxTerminalModifier  t::SyntaxTerminalModifiers
{
  top.cstErrors := h.cstErrors ++ t.cstErrors;
  top.dominatesXML = h.dominatesXML ++ t.dominatesXML;
  top.submitsXML = h.submitsXML ++ t.submitsXML;
  top.lexerclassesXML = h.lexerclassesXML ++ t.lexerclassesXML;
  top.ignored = h.ignored || t.ignored;
  top.acode = h.acode ++ t.acode;
  top.opPrecedence = orElse(h.opPrecedence, t.opPrecedence);
  top.opAssociation = orElse(h.opAssociation, t.opAssociation);
  top.unparses = h.unparses ++ t.unparses;
}

abstract production nilTerminalMod
top::SyntaxTerminalModifiers ::= 
{
  top.cstErrors := [];
  top.dominatesXML = "";
  top.submitsXML = "";
  top.lexerclassesXML = "";
  top.ignored = false;
  top.acode = "";
  top.opPrecedence = nothing();
  top.opAssociation = nothing();
  top.unparses = [];
}



{--
 - Modifiers for terminals.
 -}
nonterminal SyntaxTerminalModifier with cstEnv, cstErrors, dominatesXML, submitsXML, ignored, acode, lexerclassesXML, opPrecedence, opAssociation, unparses;

{--
 - If present, it's an ignore terminal, otherwise ordinary terminal.
 - Copper has no notion of an ignore terminal, this is translated away.
 -}
abstract production termIgnore
top::SyntaxTerminalModifier ::=
{
  top.cstErrors := [];
  top.dominatesXML = "";
  top.submitsXML = "";
  top.lexerclassesXML = "";
  top.ignored = true; -- the interesting one
  top.acode = "";
  top.opPrecedence = nothing();
  top.opAssociation = nothing();
  top.unparses = ["ignore()"];
}
{--
 - The terminal's precedence. (Resolves shift/reduce conflicts)
 -}
abstract production termPrecedence
top::SyntaxTerminalModifier ::= lvl::Integer
{
  top.cstErrors := [];
  top.dominatesXML = "";
  top.submitsXML = "";
  top.lexerclassesXML = "";
  top.ignored = false;
  top.acode = "";
  top.opPrecedence = just(lvl); -- the interesting one
  top.opAssociation = nothing();
  top.unparses = ["prec(" ++ toString(lvl) ++ ")"];
}
{--
 - The terminal's association. Either left, right, or nonassoc. TODO: a type?
 -}
abstract production termAssociation
top::SyntaxTerminalModifier ::= direction::String
{
  top.cstErrors := [];
  top.dominatesXML = "";
  top.submitsXML = "";
  top.lexerclassesXML = "";
  top.ignored = false;
  top.acode = "";
  top.opPrecedence = nothing();
  top.opAssociation = just(direction); -- the interesting one
  top.unparses = ["assoc(" ++ quoteString(direction) ++ ")"];
}
{--
 - The terminal's lexer classes.
 -}
abstract production termClasses
top::SyntaxTerminalModifier ::= cls::[String]
{
  local attribute clsRefs :: [[Decorated SyntaxDcl]];
  clsRefs = lookupStrings(cls, top.cstEnv);

  top.cstErrors := []; -- TODO error checking!
  top.dominatesXML = implode("", map((.classDomContribs), map(head, clsRefs))); -- ALSO interesting
  top.submitsXML = implode("", map((.classSubContribs), map(head, clsRefs))); -- ALSO interesting
  top.lexerclassesXML = implode("", map(xmlCopperClassRef, cls)); -- the interesting one
  top.ignored = false;
  top.acode = "";
  top.opPrecedence = nothing();
  top.opAssociation = nothing();
  top.unparses = ["classes(" ++ unparseStrings(cls) ++ ")"];
}
{--
 - The submits list for the terminal. Either lexer classes or terminals.
 -}
abstract production termSubmits
top::SyntaxTerminalModifier ::= sub::[String]
{
  local attribute subRefs :: [[Decorated SyntaxDcl]];
  subRefs = lookupStrings(sub, top.cstEnv);

  top.cstErrors := []; -- TODO error checking!
  top.dominatesXML = "";
  top.submitsXML = implode("", map(xmlCopperRef, map(head, subRefs))); -- the interesting one
  top.lexerclassesXML = "";
  top.ignored = false;
  top.acode = "";
  top.opPrecedence = nothing();
  top.opAssociation = nothing();
  top.unparses = ["sub(" ++ unparseStrings(sub) ++ ")"];
}
{--
 - The dominates list for the terminal. Either lexer classes or terminals.
 -}
abstract production termDominates
top::SyntaxTerminalModifier ::= dom::[String]
{
  local attribute domRefs :: [[Decorated SyntaxDcl]];
  domRefs = lookupStrings(dom, top.cstEnv);

  top.cstErrors := []; -- TODO error checking!
  top.dominatesXML = implode("", map(xmlCopperRef, map(head, domRefs))); -- the interesting one
  top.submitsXML = "";
  top.lexerclassesXML = "";
  top.ignored = false;
  top.acode = "";
  top.opPrecedence = nothing();
  top.opAssociation = nothing();
  top.unparses = ["dom(" ++ unparseStrings(dom) ++ ")"];
}
{--
 - The action to take whenever this terminal is SHIFTed.
 -}
abstract production termAction
top::SyntaxTerminalModifier ::= acode::String
{
  top.cstErrors := [];
  top.dominatesXML = "";
  top.submitsXML = "";
  top.lexerclassesXML = "";
  top.ignored = false;
  top.acode = acode; -- the interesting one
  top.opPrecedence = nothing();
  top.opAssociation = nothing();
  top.unparses = ["acode(\"" ++ escapeString(acode) ++ "\")"];
}

