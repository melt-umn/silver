grammar silver:translation:java:concrete_syntax:copper;

attribute javaClasses occurs on ParserDcl;
attribute disambiguationGroupDcls,parserAttrDcls occurs on ParserDcl, ModuleList, ModuleName, Module, ModuleExportedDefs;

aspect production parserDcl
top::AGDcl ::= p::ParserDcl
{
  top.javaClasses = p.javaClasses;
  -- note that ParserDcl has things like disambig, parsattrs, rules, etc. These do not appear here
  -- putting them here would be tanamount to "export all concrete syntax that appear in a parser dcl"
}

-- TODO: ParserDcl should be turned into a spec like all the rest. It's inconsistent.
-- LOL there is a ParserSpec. This is a mess!
aspect production parserStmt
top::ParserDcl ::= 'parser' n::Name '::' t::Type '{' m::ModuleList '}'
{

  local attribute className :: String;
  className = "P" ++ n.name;

  local attribute packageName :: String;
  packageName = makeName(top.grammarName);

  local attribute fullClassName :: String;
  fullClassName = packageName ++ "." ++ className;

  local attribute parserName :: String;
  parserName = makeParserName(top.fullName);

  top.javaClasses = [[className,
                      generateFunctionClassString(top.grammarName, n.name, namedSig, parseResult)
                    ]];
  
  local attribute parseResult :: String;
  parseResult = 
   "try {\n" ++
"\t\t\treturn new core.PparseSucceeded( new " ++ packageName ++ "." ++ parserName ++ "().parse(new java.io.StringReader(((common.StringCatter)getChild(0)).toString()), ((common.StringCatter)getChild(1)).toString()) );\n" ++
"\t\t} catch(edu.umn.cs.melt.copper.runtime.logging.CopperParserException e) {\n" ++
"\t\t\treturn new core.PparseFailed( new common.StringCatter(e.getMessage()) );\n" ++
"\t\t} catch(Throwable t) {\n" ++
"\t\t\tthrow new RuntimeException(\"An error occurs while parsing\", t);\n" ++
"\t\t}\n";

  top.disambiguationGroupDcls = m.disambiguationGroupDcls;
  top.parserAttrDcls = m.parserAttrDcls;
}

aspect production moduleListOne
top::ModuleList ::= c1::ModuleName ';'
{
  top.disambiguationGroupDcls = c1.disambiguationGroupDcls;
  top.parserAttrDcls = c1.parserAttrDcls;
}

aspect production moduleListCons
top::ModuleList ::= c1::ModuleName ';' c2::ModuleList
{
  top.disambiguationGroupDcls = c1.disambiguationGroupDcls ++ c2.disambiguationGroupDcls;
  top.parserAttrDcls = c1.parserAttrDcls ++ c2.parserAttrDcls;
}

aspect production moduleName
top::ModuleName ::= pkg::QName
{
  top.disambiguationGroupDcls = m.disambiguationGroupDcls;
  top.parserAttrDcls = m.parserAttrDcls;
}

aspect production module 
top::Module ::= c::[Decorated RootSpec] g::Decorated QName a::String o::[String] h::[String] w::[[String]]
{
  top.disambiguationGroupDcls = med.disambiguationGroupDcls;		  
  top.parserAttrDcls = med.parserAttrDcls;		  
}

aspect production moduleExportedDefs
top::ModuleExportedDefs ::= compiled::[Decorated RootSpec] need::[String] seen::[String]
{
  top.disambiguationGroupDcls = if null(need) || null(rs) then [] else (head(rs).disambiguationGroupDcls ++ recurse.disambiguationGroupDcls);
  top.parserAttrDcls = if null(need) || null(rs) then [] else (head(rs).parserAttrDcls ++ recurse.parserAttrDcls);
}

