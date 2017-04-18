grammar lib:monto:helpers;

import lib:json;
import silver:langutil;

lexer class MONTO_COMMENT;
lexer class MONTO_FUNCTION;
lexer class MONTO_IDENTIFIER;
lexer class MONTO_KEYWORD;
lexer class MONTO_LITERAL;
lexer class MONTO_OPERATOR;
lexer class MONTO_TYPE;

{--
 - The highlighting styles used by the montoLexerClassHighlightingCallback.
 - Appropriate styles are applied to each of the lexer classes defined in this
 - grammar.
 -}
global lexerClassHighlightStyles :: [Pair<String Style>] =
  [ pair("lib:monto:helpers:MONTO_COMMENT", style(["comment"], nothing()))
  , pair("lib:monto:helpers:MONTO_FUNCTION", style(["function", "name"], nothing()))
  , pair("lib:monto:helpers:MONTO_IDENTIFIER", style(["variable", "name"], nothing()))
  , pair("lib:monto:helpers:MONTO_KEYWORD", style(["keyword"], nothing()))
  , pair("lib:monto:helpers:MONTO_LITERAL", style(["constant"], nothing()))
  , pair("lib:monto:helpers:MONTO_OPERATOR", style(["operator"], nothing()))
  ];

{--
 - Creates a highlighting callback that highlights based on the lexer classes
 - defined in this grammar.
 -}
function montoLexerClassHighlightingCallback
(Json ::= String String) ::= parse::(ParseResult<a> ::= String String)
{
  return listBasedHighlightingCallback(parse, lexerClassHighlightStyles);
}

{--
 - Creates a highlighting callback that highlights lexer classes that appear in
 - the given list with the appropriate styles.
 -}
function listBasedHighlightingCallback
(Json ::= String String) ::= parse::(ParseResult<a> ::= String String) styles::[Pair<String Style>]
{
  return makeHighlightingCallback(parse, getStyleFromList(styles, _));
}

{--
 - Looks up the style for a given TerminalDescriptor based on the lexer classes
 - it contains and the styles list it is passed.
 -}
function getStyleFromList
Style ::= styles::[Pair<String Style>] td::TerminalDescriptor
{
  return case filter(\p::Pair<String Style> -> containsBy(stringEq, p.fst, td.lexerClasses), styles) of
  | [] -> unsafeTrace(
            style([], nothing()),
            print("Unhighlighted TerminalDescriptor: " ++ td.unparse, unsafeIO()))
  | l  -> head(l).snd
  end;
}

{--
 - Creates a highlighting callback that uses the given function to style tokens.
 -}
function makeHighlightingCallback
(Json ::= String String) ::= parse::(ParseResult<a> ::= String String) tdToStyle::(Style ::= TerminalDescriptor)
{
  return \src::String fileName::String ->
    jsonArray(
      map((.json),
        map(
          terminalDescriptorToToken(tdToStyle, _),
          parse(src, fileName).parseTerminals)));
}

{--
 - Converts a TerminalDescriptor to a Token. Tokens are used by Monto, while
 - TerminalDescriptors are produced by parsing.
 -}
function terminalDescriptorToToken
Token ::= highlight::(Style ::= TerminalDescriptor) td::TerminalDescriptor
{
  return token(
    td.lexerClasses,
    td.terminalLocation.index,
    td.terminalLocation.endIndex - td.terminalLocation.index,
    highlight(td));
}

{--
 - A single lexical token.
 -}
nonterminal Token with json, lexerClasses, tokenOffset, tokenLength, style;

{--
 - The offset from the start of the file.
 -}
synthesized attribute tokenOffset :: Integer;

{--
 - The length of the token.
 -}
synthesized attribute tokenLength :: Integer;

{--
 - The style of the token.
 -}
synthesized attribute style :: Style;

{--
 - Creates a new token with the given properties.
 -}
abstract production token
top::Token ::= lexerClasses::[String] tokenOffset::Integer tokenLength::Integer style::Style
{
  top.lexerClasses = lexerClasses;
  top.tokenOffset = tokenOffset;
  top.tokenLength = tokenLength;
  top.style = style;
  local font :: Pair<String Json> =
    pair("font", fromMaybe(jsonObject([]), mapMaybe((.json), top.style.font)));
  top.json = jsonObject(
    [ font
    , pair("lexer_classes", jsonArray(map(jsonString, top.lexerClasses)))
    , pair("offset", jsonInteger(top.tokenOffset))
    , pair("length", jsonInteger(top.tokenLength))
    , pair("style", jsonArray(map(jsonString, top.style.styleClasses)))
    ]);
}

{--
 - A style, as applied to a token.
 -}
nonterminal Style with font, styleClasses;

{--
 - The styling characteristics that should be applied to the token.
 -
 - This is specified by the Monto specification, but is something we might want
 - to disagree with the spec on -- many editors don't support changing the font
 - face or font size inline with the buffer, and it makes more sense regardless
 - to use the editor's styling for e.g. literals rather than to force the user
 - to configure every server with their preferred settings.
 -}
synthesized attribute font :: Maybe<Font>;

{--
 - The "style classes" that should be applied to the token. These style classes
 - coorespond closely to certain types of lexer classes, without having to
 - depend on any specific lexer classes being present in the grammar.
 -
 - This is based on Atom's highlighting, and is likely easily portable to other
 - editors as well.
 -}
synthesized attribute styleClasses :: [String];

{--
 - Creates a new style with the given properties.
 -}
abstract production style
top::Style ::= styleClasses::[String] font::Maybe<Font>
{
  top.styleClasses = styleClasses;
  top.font = font;
}
