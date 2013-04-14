grammar silver:modification:impide;

import silver:modification:copper_mda only findSpec; -- TODO
import silver:driver:util only RootSpec;
import silver:extension:list;
import silver:analysis:typechecking:core;
import silver:modification:ffi;
import silver:definition:type;

-- We're going to make this an especially annoying looking declaration
-- to emphasize that this is currently a temporary hack just to get things
-- moving.
terminal ImpIde_t 'temp_imp_ide_dcl' lexer classes {KEYWORD,RESERVED};

terminal ImpIde_OptFunc_Property 'property' lexer classes {KEYWORD};

terminal ImpIde_PropType_string_t 'string' lexer classes {KEYWORD};
terminal ImpIde_PropType_integer_t 'integer' lexer classes {KEYWORD};
terminal ImpIde_PropType_path_t 'path' lexer classes {KEYWORD};
terminal ImpIde_PropType_url_t 'url' lexer classes {KEYWORD};


concrete production ideDcl
top::AGDcl ::= 'temp_imp_ide_dcl' parsername::QName fileextension::String_t optFunctions::IdeFunctions ';'
{
  top.pp = "temp_imp_ide_dcl " ++ parsername.pp ++ " " ++ fileextension.lexeme ++ "\n";

  top.defs = [];

  top.errors := parsername.lookupValue.errors;

  -- lexeme starts with ", but also ensure first character is a dot.
  top.errors <-
    if startsWith("\".", fileextension.lexeme) then []
    else [err(fileextension.location, "File extension should begin with dot (like \".sv\")")];
  
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
  
  top.ideSpecs = [ideSpec(fext, optFunctions.funcDcls, optFunctions.propDcls, head(spec))];
  
  top.errors <- optFunctions.errors;

  forwards to emptyAGDcl(location=top.location);
}


-- Functions

-- function called when build is triggered
terminal ImpIde_OptFunc_Builder 'builder';

-- function called in background thread, if builder returns without errors
terminal ImpIde_OptFunc_PostBuilder 'postbuilder';

-- function called when exporting is demanded
terminal ImpIde_OptFunc_Exporter 'exporter';

--funcDcls, propDcls are defined in ./IdeSpec.sv
nonterminal IdeFunctions with env, location, errors, grammarName, file, funcDcls, propDcls;
nonterminal IdeFunction with env, location, errors, grammarName, file, funcDcls, propDcls;
nonterminal IdeFunctionList with env, location, errors, grammarName, file, funcDcls, propDcls;

concrete production emptyIdeFunctions
top::IdeFunctions ::=
{
  top.errors := [];
  top.funcDcls := [];
  top.propDcls := [];
}

concrete production listIdeFunctions
top::IdeFunctions ::= '{' funcList::IdeFunctionList '}'
{
  top.errors := funcList.errors;
  top.funcDcls := funcList.funcDcls;
  top.propDcls := funcList.propDcls;
}

concrete production nilIdeFunctionList
top::IdeFunctionList ::= 
{
  top.errors := [];
  top.funcDcls := [];
  top.propDcls := [];
}

concrete production consIdeFunctionList
top::IdeFunctionList ::= func::IdeFunction funcList::IdeFunctionList
{
  top.errors := func.errors ++ funcList.errors;
  top.funcDcls := func.funcDcls ++ funcList.funcDcls;
  top.propDcls := func.propDcls ++ funcList.propDcls;
}

concrete production makeIdeFunction_Builder
top::IdeFunction ::= 'builder' builderName::QName ';' 
{
  top.funcDcls := [pair("builder", builderName.lookupValue.fullName)];
  top.propDcls := [];

  top.errors := builderName.lookupValue.errors;
  
  -- IOVal<[IdeMessage]> ::= [IdeProperty] IO
  local builderTypeExpected :: TypeExp =
    functionTypeExp(
      nonterminalTypeExp(
        "core:IOVal", 
        [listTypeExp(nonterminalTypeExp("ide:IdeMessage", []))]
      ),
      [listTypeExp(nonterminalTypeExp("ide:IdeProperty", [])),
        foreignTypeExp("core:IO", [])], []);
  
  local tc1 :: TypeCheck = check(freshenCompletely(builderName.lookupValue.typerep), builderTypeExpected);
  tc1.downSubst = emptySubst();
  tc1.finalSubst = tc1.upSubst;

  top.errors <-
    if !tc1.typeerror then []
    else [err(builderName.location, "Builder function should have type:\n\t" ++ tc1.rightpp 
        ++ "\nInstead it has the type:\n\t" ++ tc1.leftpp)];
}  

concrete production makeIdeFunction_PostBuilder
top::IdeFunction ::= 'postbuilder' postbuilderName::QName ';' 
{
  top.funcDcls := [pair("postbuilder", postbuilderName.lookupValue.fullName)];
  top.propDcls := [];

  top.errors := postbuilderName.lookupValue.errors;
  
  -- IOVal<[IdeMessage]> ::= [IdeProperty] IO
  local postbuilderTypeExpected :: TypeExp =
    functionTypeExp(
      nonterminalTypeExp(
        "core:IOVal", 
        [listTypeExp(nonterminalTypeExp("ide:IdeMessage", []))]
      ),
      [listTypeExp(nonterminalTypeExp("ide:IdeProperty", [])),
        foreignTypeExp("core:IO", [])], []);
  
  local tc1 :: TypeCheck = check(freshenCompletely(postbuilderName.lookupValue.typerep), postbuilderTypeExpected);
  tc1.downSubst = emptySubst();
  tc1.finalSubst = tc1.upSubst;

  top.errors <-
    if !tc1.typeerror then []
    else [err(postbuilderName.location, "Post-builder function should have type:\n\t" ++ tc1.rightpp 
        ++ "\nInstead it has the type:\n\t" ++ tc1.leftpp)];
}  

concrete production makeIdeFunction_Exporter
top::IdeFunction ::= 'exporter' exporterName::QName ';' 
{
  top.funcDcls := [pair("exporter", exporterName.lookupValue.fullName)];
  top.propDcls := [];

  top.errors := exporterName.lookupValue.errors;
  
  -- IOVal<[IdeMessage]> ::= [IdeProperty] IdeEnv IO
  local exporterTypeExpected :: TypeExp =
    functionTypeExp(
      nonterminalTypeExp(
        "core:IOVal", 
        [listTypeExp(nonterminalTypeExp("ide:IdeMessage", []))]
      ),
      [listTypeExp(nonterminalTypeExp("ide:IdeProperty", [])), 
        nonterminalTypeExp("ide:IdeEnv", []),
        foreignTypeExp("core:IO", [])], []);
  
  local tc1 :: TypeCheck = check(freshenCompletely(exporterName.lookupValue.typerep), exporterTypeExpected);
  tc1.downSubst = emptySubst();
  tc1.finalSubst = tc1.upSubst;

  top.errors <-
    if !tc1.typeerror then []
    else [err(exporterName.location, "Exporter function should have type:\n\t" ++ tc1.rightpp 
        ++ "\nInstead it has the type:\n\t" ++ tc1.leftpp)];
}  

concrete production makeIdeFunction_Porperty
top::IdeFunction ::= 'property' pname::IdLower_t ptype::TypeName ';' 
{
  top.funcDcls := [];

  top.propDcls := [makeIdeProperty(pname.lexeme, ptype.propType)];

  top.errors := [];
} 

nonterminal TypeName with propType;

concrete production propType_String
top::TypeName ::= 'string'
{
  top.propType = "string";
}

concrete production propType_Integer
top::TypeName ::= 'integer'
{
  top.propType = "integer";
}

concrete production propType_Path
top::TypeName ::= 'path'
{
  top.propType = "path";
}

concrete production propType_URL
top::TypeName ::= 'url'
{
  top.propType = "url";
}

