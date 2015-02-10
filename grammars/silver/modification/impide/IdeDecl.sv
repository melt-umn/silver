grammar silver:modification:impide;

import silver:modification:copper_mda only findSpec; -- TODO
import silver:driver:util only RootSpec;
import silver:extension:list;
import silver:analysis:typechecking:core;
import silver:modification:ffi;
import silver:definition:type;

autocopy attribute startNTName :: String;

-- We're going to make this an especially annoying looking declaration
-- to emphasize that this is currently a temporary hack just to get things
-- moving.
terminal ImpIde_t 'temp_imp_ide_dcl' lexer classes {KEYWORD,RESERVED};

terminal ImpIde_ProdInfo_Name_t 'name' lexer classes {KEYWORD};
terminal ImpIde_ProdInfo_Version_t 'version' lexer classes {KEYWORD};

terminal ImpIde_OptFunc_Builder 'builder' lexer classes {KEYWORD};
terminal ImpIde_OptFunc_PostBuilder 'postbuilder' lexer classes {KEYWORD};
terminal ImpIde_OptFunc_Exporter 'exporter' lexer classes {KEYWORD};
terminal ImpIde_OptFunc_Folder 'folder' lexer classes {KEYWORD};

terminal ImpIde_IdeResource 'resource' lexer classes {KEYWORD};

terminal ImpIde_Wizard 'wizard' lexer classes {KEYWORD};
terminal ImpIde_Wizard_StubGen 'stub generator' lexer classes {KEYWORD};
terminal ImpIde_Wizard_NewFile 'new file' lexer classes {KEYWORD};



concrete production ideDcl
top::AGDcl ::= 'temp_imp_ide_dcl' parsername::QName fileextension::String_t stmts::IdeStmts
{
  top.pp = "temp_imp_ide_dcl " ++ parsername.pp ++ " " ++ fileextension.lexeme ++ "\n"; -- TODO not finished

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

  stmts.startNTName = head(spec).startNT;

  -- If there were errors looking up the name, do nothing. If we couldn't find the
  -- parser, then raise the error message noting that the name isn't a parser!
  top.errors <- if !null(parsername.lookupValue.errors) || !null(spec) then []
                else [err(parsername.location, parsername.name ++ " is not a parser.")];
  
  -- Strip off the quotes AND the initial dot
  local fext :: String = substring(2, length(fileextension.lexeme) - 1, fileextension.lexeme);
  
  local ideName :: String =
    if null(stmts.ideNames) then deriveLangNameFromGrammar(top.grammarName) else head(stmts.ideNames);
  top.errors <- if length(stmts.ideNames) > 1 then [err(top.location, "Multiple name declarations")] else [];
  local ideVersion :: String =
    if null(stmts.ideVersions) then "1.0.0" else head(stmts.ideVersions);
  top.errors <- if length(stmts.ideVersions) > 1 then [err(top.location, "Multiple version declarations")] else [];
  

  top.ideSpecs = [ideSpec(top.grammarName, ideName, ideVersion, fext, stmts.ideFunctions, stmts.propDcls, stmts.wizards, head(spec), stmts.ideResources)];
  
  top.errors <- stmts.errors;

  forwards to emptyAGDcl(location=top.location);
}

function deriveLangNameFromGrammar
String ::= gram::String
{
  return toUpperCase(head(explode(":", gram)));
}

-- funcDcls, propDcls and optDcls are defined in ./IdeSpec.sv
nonterminal IdeStmts with env, location, errors, grammarName, ideFunctions, propDcls, wizards, startNTName, ideNames, ideVersions, ideResources;
nonterminal IdeStmt with env, location, errors, grammarName, ideFunctions, propDcls, wizards, startNTName, ideNames, ideVersions, ideResources;
nonterminal IdeStmtList with env, location, errors, grammarName, ideFunctions, propDcls, wizards, startNTName, ideNames, ideVersions, ideResources;

synthesized attribute ideNames :: [String];
synthesized attribute ideVersions :: [String];
synthesized attribute ideFunctions :: [IdeFunction];
synthesized attribute wizards :: [IdeWizardDcl];
synthesized attribute propDcls :: [IdeProperty];

concrete production emptyIdeStmts
top::IdeStmts ::= ';'
{
  top.errors := [];
  top.ideFunctions = [];
  top.propDcls = [];
  top.wizards = [];
  top.ideNames = [];
  top.ideVersions = [];
  top.ideResources = [];
}

concrete production listIdeStmts
top::IdeStmts ::= '{' stmtList::IdeStmtList '}'
{
  top.errors := stmtList.errors;
  top.ideFunctions = stmtList.ideFunctions;
  top.propDcls = stmtList.propDcls;
  top.wizards = stmtList.wizards;
  top.ideNames = stmtList.ideNames;
  top.ideVersions = stmtList.ideVersions;
  top.ideResources = stmtList.ideResources;
}

-- with optional ending ';'
concrete production listIdeStmts2
top::IdeStmts ::= '{' stmtList::IdeStmtList '}' ';'
{
  top.errors := stmtList.errors;
  top.ideFunctions = stmtList.ideFunctions;
  top.propDcls = stmtList.propDcls;
  top.wizards = stmtList.wizards;
  top.ideNames = stmtList.ideNames;
  top.ideVersions = stmtList.ideVersions;
  top.ideResources = stmtList.ideResources;
}

concrete production nilIdeStmtList
top::IdeStmtList ::= 
{
  top.errors := [];
  top.ideFunctions = [];
  top.propDcls = [];
  top.wizards = [];
  top.ideNames = [];
  top.ideVersions = [];
  top.ideResources = [];
}

concrete production consIdeStmtList
top::IdeStmtList ::= stmt::IdeStmt stmtList::IdeStmtList
{
  top.errors := stmt.errors ++ stmtList.errors;
  top.ideFunctions = stmt.ideFunctions ++ stmtList.ideFunctions;
  top.propDcls = stmt.propDcls ++ stmtList.propDcls;
  top.wizards = stmt.wizards ++ stmtList.wizards;
  top.ideNames = stmt.ideNames ++ stmtList.ideNames;
  top.ideVersions = stmt.ideVersions ++ stmtList.ideVersions;
  top.ideResources = stmt.ideResources ++ stmtList.ideResources;
}

aspect default production
top::IdeStmt ::=
{
  top.ideFunctions = [];
  top.propDcls = [];
  top.wizards = [];
  top.ideNames = [];
  top.ideVersions = [];
  top.ideResources = [];
}

concrete production makeIdeStmt_Builder
top::IdeStmt ::= 'builder' builderName::QName ';' 
{
  top.ideFunctions = [builderFunction(builderName.lookupValue.fullName)];
  top.errors := builderName.lookupValue.errors;
  
  -- IOVal<[IdeMessage]> ::= [IdeProperty] IdeEnv IO
  local builderTypeExpected :: TypeExp =
    functionTypeExp(
      nonterminalTypeExp(
        "core:IOVal", 
        [listTypeExp(nonterminalTypeExp("ide:IdeMessage", []))]
      ),
      [listTypeExp(nonterminalTypeExp("ide:IdeProperty", [])),
        nonterminalTypeExp("ide:IdeEnv", []),
        foreignTypeExp("core:IO", [])], []);
  
  local tc1 :: TypeCheck = check(freshenCompletely(builderName.lookupValue.typerep), builderTypeExpected);
  tc1.downSubst = emptySubst();
  tc1.finalSubst = tc1.upSubst;

  top.errors <-
    if !tc1.typeerror then []
    else [err(builderName.location, "Builder function should have type:\n\t" ++ tc1.rightpp 
        ++ "\nInstead it has the type:\n\t" ++ tc1.leftpp)];
}  

concrete production makeIdeStmt_PostBuilder
top::IdeStmt ::= 'postbuilder' postbuilderName::QName ';' 
{
  top.ideFunctions = [postbuilderFunction(postbuilderName.lookupValue.fullName)];
  top.errors := postbuilderName.lookupValue.errors;
  
  -- IOVal<[IdeMessage]> ::= [IdeProperty] IdeEnv IO
  local postbuilderTypeExpected :: TypeExp =
    functionTypeExp(
      nonterminalTypeExp(
        "core:IOVal", 
        [listTypeExp(nonterminalTypeExp("ide:IdeMessage", []))]
      ),
      [listTypeExp(nonterminalTypeExp("ide:IdeProperty", [])),
        nonterminalTypeExp("ide:IdeEnv", []),
        foreignTypeExp("core:IO", [])], []);
  
  local tc1 :: TypeCheck = check(freshenCompletely(postbuilderName.lookupValue.typerep), postbuilderTypeExpected);
  tc1.downSubst = emptySubst();
  tc1.finalSubst = tc1.upSubst;

  top.errors <-
    if !tc1.typeerror then []
    else [err(postbuilderName.location, "Post-builder function should have type:\n\t" ++ tc1.rightpp 
        ++ "\nInstead it has the type:\n\t" ++ tc1.leftpp)];
}  

concrete production makeIdeStmt_Exporter
top::IdeStmt ::= 'exporter' exporterName::QName ';' 
{
  top.ideFunctions = [exporterFunction(exporterName.lookupValue.fullName)];
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

concrete production makeIdeStmt_Folder
top::IdeStmt ::= 'folder' folderName::QName ';' 
{
  top.ideFunctions = [folderFunction(folderName.lookupValue.fullName)];
  top.errors := folderName.lookupValue.errors;
  
  -- [Location] ::= <<CST root's type>>
  local folderTypeExpected :: TypeExp =
    functionTypeExp(
      listTypeExp(nonterminalTypeExp("core:Location", [])),
      [nonterminalTypeExp(top.startNTName, [])], 
      []);
  
  local tc1 :: TypeCheck = check(freshenCompletely(folderName.lookupValue.typerep), folderTypeExpected);
  tc1.downSubst = emptySubst();
  tc1.finalSubst = tc1.upSubst;

  top.errors <-
    if !tc1.typeerror then []
    else [err(folderName.location, "Folder function for this language should have type:\n\t" ++ tc1.rightpp 
        ++ "\nInstead it has the type:\n\t" ++ tc1.leftpp)];
}  

--- Allows declarations of properties for the project.
concrete production makeIdeStmt_Porperty
top::IdeStmt ::= prop::Property
{
  top.propDcls = prop.propDcls;
  top.errors := prop.errors;
} 

concrete production nameIdeStmt
top::IdeStmt ::= 'name' ideName::String_t ';'
{
  -- Strip off the quotes
  local iName :: String = substring(1, length(ideName.lexeme) - 1, ideName.lexeme);
  
  top.errors :=
    if iName == "" then
      [wrn(ideName.location, "The name of IDE product is empty. A default name will be used.")] -- TODO: this will basically never fire. move to top level declaration.
    else if isDigit(substring(0,1,iName)) then
      [err(ideName.location, "The name of IDE product cannot be started with digital.")]
    else [];

  top.ideNames = [iName];
}

concrete production versionIdeStmt
top::IdeStmt ::= 'version' v::String_t ';'
{
  -- Strip off the quotes
  local iV :: String = substring(1, length(v.lexeme) - 1, v.lexeme);
 
  top.errors :=
    if iV == "" then
      [wrn(v.location, "The version of IDE product is empty. A default version number will be used.")] -- TODO: this will basically never fire. move to top level declaration.
    else if !isLegalVersion(iV) then
      [err(v.location, "The version of IDE product must comply to the format \"N+.N+\" or \"N+.N+.N+\".")]
    else [];

  top.ideVersions = [iV];
}

concrete production resourceIdeStmt
top::IdeStmt ::= 'resource' id::Name path::String_t ';'
{
  top.errors := []; -- TODO: duplicate name check or something?
  top.ideResources = [pair(id.name, substring(1, length(path.lexeme) - 1, path.lexeme))];
}

-- Wizards

concrete production newfileWizard_c
top::IdeStmt ::= 'wizard' 'new file' '{' generator::StubGenerator props::PropertyList '}'
{
  top.wizards = [newfileWizard(generator.funcDcl, props.propDcls)];
  top.errors := generator.errors ++ props.errors;
}


nonterminal StubGenerator with env, funcDcl, errors;

synthesized attribute funcDcl :: String;

concrete production makeStubGenerator
top::StubGenerator ::= 'stub generator' genName::QName ';' 
{
  top.funcDcl = genName.lookupValue.fullName;

  -- String ::= [IdeProperty]
  local stubGenTypeExpected :: TypeExp =
    functionTypeExp(
      stringTypeExp(),  -- return type
      [listTypeExp(nonterminalTypeExp("ide:IdeProperty", []))], -- argument type list
      []);
  
  local tc1 :: TypeCheck = check(freshenCompletely(genName.lookupValue.typerep), stubGenTypeExpected);
  tc1.downSubst = emptySubst();
  tc1.finalSubst = tc1.upSubst;

  top.errors :=
    if !tc1.typeerror then []
    else [err(genName.location, "Stub generator should have type:\n\t" ++ tc1.rightpp 
        ++ "\nInstead it has the type:\n\t" ++ tc1.leftpp)];
}


function isLegalVersion
Boolean ::= ver::String
{
  local parts::[String] = explode(".", ver);

  return (length(parts) == 2 || length(parts) == 3) && isAllDigital(parts);
}

function isAllDigital
Boolean ::= parts::[String]
{
  return null(parts) || isDigit(head(parts)) && isAllDigital(tail(parts));
}

