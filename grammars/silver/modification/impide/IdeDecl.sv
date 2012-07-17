grammar silver:modification:impide;

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:concrete_syntax;

import silver:modification:copper_mda only findSpec; -- TODO

-- We're going to make this an especially annoying looking declaration
-- to emphasize that this is currently a temporary hack just to get things
-- moving.
terminal ImpIde_t 'temp_imp_ide_dcl' lexer classes {KEYWORD};

concrete production ideDcl
top::AGDcl ::= 'temp_imp_ide_dcl' parsername::QName fileextension::String_t ';'
{
  top.pp = "temp_imp_ide_dcl " ++ parsername.pp ++ " " ++ fileextension.lexeme ++ "\n";
  top.location = loc(top.file, $1.line, $1.column);

  top.defs = emptyDefs();

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
  
  top.ideSpecs = [ideSpec(fext, head(spec))];
}

