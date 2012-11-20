grammar silver:modification:impide;

--import silver:definition:env only emptyDefs;

import silver:modification:copper_mda only findSpec; -- TODO
import silver:modification:impide:cstast;
import silver:driver:util only RootSpec;
import silver:extension:list;
import silver:analysis:typechecking:core;
import silver:modification:ffi;
import silver:definition:env;
import silver:definition:type;

-- We're going to make this an especially annoying looking declaration
-- to emphasize that this is currently a temporary hack just to get things
-- moving.
terminal ImpIde_t 'temp_imp_ide_dcl' lexer classes {KEYWORD};

terminal ImpIde_OptFunc_Analyzer 'analyzer';

nonterminal IdeFunctions with env, location, errors, grammarName, file, funcDcls;--funcDcls is defined in ./IdeSpec.sv
nonterminal IdeFunction with env, location, errors, grammarName, file, funcDcls;
nonterminal IdeFunctionList with env, location, errors, grammarName, file, funcDcls;

concrete production emptyIdeFunctions
top::IdeFunctions ::=
{
  top.location = loc(top.file, -1, -1);
  top.errors := [];
  top.funcDcls := [];
}

concrete production listIdeFunctions
top::IdeFunctions ::= '{' funcList::IdeFunctionList '}'
{
  top.location = loc(top.file, $1.line, $1.column);
  top.errors := funcList.errors;
  top.funcDcls := funcList.funcDcls;
}

concrete production nilIdeFunctionList
top::IdeFunctionList ::= 
{
  top.location = loc(top.file, -1, -1);
  top.errors := [];
  top.funcDcls := [];
}

concrete production consIdeFunctionList
top::IdeFunctionList ::= func::IdeFunction funcList::IdeFunctionList
{
  top.location = func.location;
  top.errors := func.errors ++ funcList.errors;
  top.funcDcls := func.funcDcls ++ funcList.funcDcls;
}

concrete production makeIdeFunction_Analyzer
top::IdeFunction ::= 'analyzer' analyzerName::QName ';' 
{
  top.location = loc(top.file, $1.line, $1.column);

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ analyzerName.name;

  top.funcDcls := [pair("analyzer", fName)];

  top.errors := [];

  local attribute analyzerFuncDcls :: [DclInfo] = getValueDclAll(fName, top.env);
  
  local attribute analyzerFunc :: DclInfo = head(analyzerFuncDcls);

  top.errors <-
        if length(analyzerFuncDcls) < 1
        then [err(top.location, "[IDE] Analyzer function '" ++ fName ++ "' doesn't exist.")]
        else 
	    case analyzerFunc of
	      funDcl(_, _, funcSign) -> checkAnalyzerSignature(fName, top.location, funcSign)
	      | _ -> [err(top.location, "[IDE] Analyzer function '" ++ fName ++ "' is not a function.")]
	    end;

}  

function checkAnalyzerSignature
[Message] ::= fName::String loc::Location sign::NamedSignature
{
  local attribute ie ::[NamedSignatureElement] = sign.inputElements;
  local attribute fstArg :: NamedSignatureElement = head(ie);
  local attribute sndArg :: NamedSignatureElement = head(tail(ie));

  local attribute ret :: NamedSignatureElement = sign.outputElement;

  production attribute errors :: [Message] with ++;
  errors := [];

  errors <- if length(ie) != 2
        then [err(loc, "[IDE] The signature of analyzer function '" ++ fName ++ "' is not \"[String] ::= [String] IO\"")]
        else [];

  local attribute tc1 :: TypeCheck = check(fstArg.typerep, listTypeExp(stringTypeExp()));
  errors <- if length(ie) == 2 
	    then if tc1.leftpp == tc1.rightpp
                then []
                else [err(loc, "[IDE] The first argument of analyzer function '" ++ fName ++ 
                     "' must be of type [String]\"; it is however " ++ tc1.leftpp ++ ".")]
            else []; -- illegal

  local attribute tc2 :: TypeCheck = check(sndArg.typerep, foreignTypeExp("core:IO", []));
  errors <- if length(ie) == 2 
	    then if tc2.leftpp == tc2.rightpp
                 then []
                 else [err(loc, "[IDE] The second argument of analyzer function '" ++ fName ++ 
                      "' must be of type IO; it is however " ++ tc2.leftpp ++ ".")]
            else []; -- illegal

  local attribute tcRet :: TypeCheck = check(ret.typerep, listTypeExp(stringTypeExp()));
  errors <- if tcRet.leftpp == tcRet.rightpp
            then []
            else [err(loc, "[IDE] The returned value of analyzer function '" ++ fName ++ 
                  "' must be of type [String]; it is however " ++ tcRet.leftpp ++ ".")];

  return errors;
}

--action {
--  print "!!!****** Saw analyzer function with sig = " ++ analyzerName.lookupValue.dcl.unparse ++ "\n";
--}

concrete production ideDcl
top::AGDcl ::= 'temp_imp_ide_dcl' parsername::QName fileextension::String_t optFunctions::IdeFunctions ';'
{
  top.pp = "temp_imp_ide_dcl " ++ parsername.pp ++ " " ++ fileextension.lexeme ++ "\n";
  top.location = loc(top.file, $1.line, $1.column);

  top.defs = [];

  top.errors := parsername.lookupValue.errors;

  -- lexeme starts with ", but also ensure first character is a dot.
  top.errors <-
    if startsWith("\".", fileextension.lexeme) then []
    else [err(top.location, "File extension should begin with dot (like \".sv\")")];
  
  -- This gets the compiler's representation of the grammar the parser is declared in
  -- This should NOT be accessed unless we know the lookup for the name succeeded
  -- since we're unconditionally calling 'head' here!
  local attribute parsergrammar :: Decorated RootSpec;
  parsergrammar = head(searchEnvTree(parsername.lookupValue.dcl.sourceGrammar, top.compiledGrammars));
  
  -- This looks up the actual specification of the parser in that grammar.
  local attribute spec :: [ParserSpec];
  spec = findSpec(parsername.lookupValue.fullName, parsergrammar.parserSpecs);
  
  -- If there were errors looking up the name, do nothing. If we couldn't find the
  -- parser, then raise the error message noting that the name isn't a parser!
  top.errors <- if !null(parsername.lookupValue.errors) || !null(spec) then []
                else [err(parsername.location, parsername.name ++ " is not a parser.")];
  
  -- Strip off the quotes AND the initial dot
  local fext :: String = substring(2, length(fileextension.lexeme) - 1, fileextension.lexeme);
  
  top.ideSpecs = [ideSpec(fext, optFunctions.funcDcls, head(spec))];
  
  top.errors <- optFunctions.errors;

  forwards to emptyAGDcl();
}

--Type: Color
synthesized attribute r :: Integer;
synthesized attribute g :: Integer;
synthesized attribute b :: Integer;
nonterminal Color with r, g, b;
abstract production makeColor
top::Color ::= r::Integer g::Integer b::Integer
{
  top.r = r;
  top.g = g;
  top.b = b;
}

--Type: Font
synthesized attribute color :: Color;
synthesized attribute isBold :: Boolean;
synthesized attribute isItalic :: Boolean;
nonterminal Font with color, isBold, isItalic;
abstract production font
top::Font ::= color::Color isBold::Boolean isItalic::Boolean
{
  top.color = color;
  top.isBold = isBold;
  top.isItalic = isItalic;
}

--CST for font declaration
terminal ImpFont_t 'temp_imp_ide_font' lexer classes {KEYWORD};
terminal Color_kwd 'color' ;
terminal Bold_kwd 'bold' ;
terminal Italic_kwd 'italic' ;

synthesized attribute fontList :: [Pair<String Font>];
attribute fontList occurs on Syntax, SyntaxDcl;

concrete production fontDcl
top::AGDcl ::= 'temp_imp_ide_font' fontName::IdLower_t 'color' '(' r::Int_t ',' g::Int_t ',' b::Int_t ')' fontStyles::FontStyles ';'
{

  top.pp = "temp_imp_ide_font " ++ fontName.lexeme ++ " color(" ++ r.lexeme ++ ", " ++ g.lexeme ++ ", " ++ b.lexeme ++ ")" ++ fontStyles.pp ++ "\n";
  top.location = loc(top.file, $1.line, $1.column);

  top.defs = [];
  
  top.errors := [];--TODO: add errors later

  top.syntaxAst = [syntaxFont(
                   fontName.lexeme, 
                   font(makeColor(toInt(r.lexeme),toInt(g.lexeme),toInt(b.lexeme)), 
                        fontStyles.isBold, 
                        fontStyles.isItalic)
		  )];

  top.ideSpecs = [];

  forwards to emptyAGDcl();
}

nonterminal FontStyles with isBold, isItalic, pp;
nonterminal FontStyle with isBold, isItalic;

concrete production consFontStylesDcl
top::FontStyles ::= fontStyle::FontStyle fontStyles::FontStyles
{
  top.isBold = fontStyle.isBold || fontStyles.isBold;
  top.isItalic = fontStyle.isItalic || fontStyles.isItalic;

  top.pp = (if(top.isBold) then "<bold>" else "") ++ (if(top.isItalic) then "<italic>" else "");
}
concrete production nilFontStylesDcl
top::FontStyles ::= 
{
  top.isBold = false;
  top.isItalic = false;
}

concrete production fontStyleBoldDcl
top::FontStyle ::= 'bold'
{
  top.isBold = true;
  top.isItalic = false;
}
concrete production fontStyleItalicDcl
top::FontStyle ::= 'italic'
{
  top.isBold = false;
  top.isItalic = true;
}


-- temp_imp_ide_font KeywordFont color(255,0,0) bold;
