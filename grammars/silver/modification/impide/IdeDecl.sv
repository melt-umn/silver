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

terminal ImpIde_Product_t 'product' lexer classes {KEYWORD};
terminal ImpIde_ProdInfo_Name_t 'name' lexer classes {KEYWORD};
terminal ImpIde_ProdInfo_Version_t 'version' lexer classes {KEYWORD};

concrete production ideDcl
top::AGDcl ::= 'temp_imp_ide_dcl' parsername::QName fileextension::String_t optFunctions::IdeStmts ';'
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

  optFunctions.startNTName = makeGrammarName(head(spec).cstAst.startNT);

  -- If there were errors looking up the name, do nothing. If we couldn't find the
  -- parser, then raise the error message noting that the name isn't a parser!
  top.errors <- if !null(parsername.lookupValue.errors) || !null(spec) then []
                else [err(parsername.location, parsername.name ++ " is not a parser.")];
  
  -- Strip off the quotes AND the initial dot
  local fext :: String = substring(2, length(fileextension.lexeme) - 1, fileextension.lexeme);
  
  local info :: IdeProductInfo = getIdeProductInfo(optFunctions);

  top.ideSpecs = [ideSpec(fext, optFunctions.funcDcls, optFunctions.propDcls, head(spec), info)];
  
  top.errors <- optFunctions.errors;

  forwards to emptyAGDcl(location=top.location);
}

function getIdeProductInfo
IdeProductInfo ::= stmts::IdeStmts
{
    return   
      case stmts of
        listIdeStmts(_, stmtList, _) -> recurGetIdeProductInfo(stmtList)
        | _ -> makeDefaultIdeProductInfo()
      end;
}

function recurGetIdeProductInfo
IdeProductInfo ::= stmtList::IdeStmtList
{
    return  
     case stmtList of
       nilIdeStmtList() -> makeDefaultIdeProductInfo()
     | consIdeStmtList(h, t) ->  
         case h of
         makeIdeStmt_Product(_, _, _, _) -> h.productInfo
         | _ -> recurGetIdeProductInfo(t)
         end
     end;
}

nonterminal IdeProperty with propName, propType;

synthesized attribute propName :: String;
synthesized attribute propType :: String;

autocopy attribute startNTName :: String;

abstract production makeIdeProperty
top::IdeProperty ::= propName::String propType::String
{
  top.propName = propName;
  top.propType = propType;
}

-- Functions

-- function called when build is triggered
terminal ImpIde_OptFunc_Builder 'builder';

-- function called in background thread, if builder returns without errors
terminal ImpIde_OptFunc_PostBuilder 'postbuilder';

-- function called when exporting is demanded
terminal ImpIde_OptFunc_Exporter 'exporter';

-- function to mark the foldable ranges on the source file; called after parsing
terminal ImpIde_OptFunc_Folder 'folder';

--funcDcls, propDcls are defined in ./IdeSpec.sv
nonterminal IdeStmts with env, location, errors, grammarName, file, funcDcls, propDcls, startNTName;
nonterminal IdeStmt with env, location, errors, grammarName, file, funcDcls, propDcls, startNTName, productInfo;
nonterminal IdeStmtList with env, location, errors, grammarName, file, funcDcls, propDcls, startNTName;

function makeGrammarName
String ::= str::String
{
  return substitute(".", ":", str);
}

concrete production emptyIdeStmts
top::IdeStmts ::=
{
  top.errors := [];
  top.funcDcls := [];
  top.propDcls := [];
}

concrete production listIdeStmts
top::IdeStmts ::= '{' funcList::IdeStmtList '}'
{
  top.errors := funcList.errors;
  top.funcDcls := funcList.funcDcls;
  top.propDcls := funcList.propDcls;
}

concrete production nilIdeStmtList
top::IdeStmtList ::= 
{
  top.errors := [];
  top.funcDcls := [];
  top.propDcls := [];
}

concrete production consIdeStmtList
top::IdeStmtList ::= func::IdeStmt funcList::IdeStmtList
{
  top.errors := func.errors ++ funcList.errors;
  top.funcDcls := func.funcDcls ++ funcList.funcDcls;
  top.propDcls := func.propDcls ++ funcList.propDcls;
}

aspect default production
top::IdeStmt ::=
{
  top.productInfo = error("Internal compiler error: should only ever be demanded of Production Info declaration.");
}

concrete production makeIdeStmt_Builder
top::IdeStmt ::= 'builder' builderName::QName ';' 
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

concrete production makeIdeStmt_Folder
top::IdeStmt ::= 'folder' folderName::QName ';' 
{
  top.funcDcls := [pair("folder", folderName.lookupValue.fullName)];
  top.propDcls := [];

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

concrete production makeIdeStmt_Porperty
top::IdeStmt ::= 'property' pname::IdLower_t ptype::TypeName ';' 
{
  top.funcDcls := [];

  top.propDcls := [makeIdeProperty(pname.lexeme, ptype.propType)];

  top.errors := [];
} 

concrete production makeIdeStmt_Product
top::IdeStmt ::= 'product' '{' dcls::IdeProductInfoDcls '}' 
{
  top.funcDcls := [];

  top.propDcls := [];

  top.productInfo = makeIdeProductInfo(dcls.info);

  top.errors := [];

  top.errors <- dcls.errors;
} 

synthesized attribute info :: [Pair<String String>] with ++;

nonterminal IdeProductInfoDcls with errors, info;
nonterminal IdeProductInfoDcl with errors, info;

concrete production nilIdeProductInfoDcls
top::IdeProductInfoDcls ::= 
{
  top.info := [];
  top.errors := [];
}

concrete production consIdeProductInfoDcls
top::IdeProductInfoDcls ::= h::IdeProductInfoDcl t::IdeProductInfoDcls
{
  top.info := h.info ++ t.info;
  top.errors := h.errors ++ t.errors;
}

concrete production ideProductInfoDcl_name
top::IdeProductInfoDcl ::= 'name' ideName::String_t ';'
{
  -- Strip off the quotes
  local iName :: String = substring(1, length(ideName.lexeme) - 1, ideName.lexeme);
  
  top.errors := [];
  top.errors <- 
        if (iName=="") then [err(ideName.location, "The name of IDE product cannot be empty. Delete the name declaration you want to the default name.")]
        else if isDigit(substring(0,1,iName)) then [err(ideName.location, "The name of IDE product cannot be started with digital.")]
        else [];

  top.info := [pair("name", iName)];
}

concrete production ideProductInfoDcl_version
top::IdeProductInfoDcl ::= 'version' v::String_t ';'
{
  -- Strip off the quotes
  local iV :: String = substring(1, length(v.lexeme) - 1, v.lexeme);
 
  top.errors := [];
  top.errors <- 
        if (iV=="") then [err(v.location, "The version of IDE product cannot be empty. Delete the name declaration you want to the default name.")]
        else if !isLegalVersion(iV) then [err(v.location, "The version of IDE product must comply to the format \"N+.N+\" or \"N+.N+.N+\".")]
        else [];

  top.info := [pair("version", iV)];
}

function isLegalVersion
Boolean ::= ver::String
{
    local parts::[String] = explode(".", ver);

    return if(length(parts)==2 || length(parts)==3) 
           then isAllDigital(parts) 
           else false;
}

function isAllDigital
Boolean ::= parts::[String]
{
    return if(null(parts))
           then true
           else if isDigit(head(parts))
                then isAllDigital(tail(parts)) 
                else false;
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

