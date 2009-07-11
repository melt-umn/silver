grammar silver:extension:doc:driver;
export silver:extension:doc:driver;

import silver:util;

import silver:extension:doc;
import silver:extension:doc:command;

import silver:util:command;
import silver:driver;
import silver:definition:core;
import silver:definition:env;
import silver:definition:concrete_syntax;

import core;

aspect production run
top::RunUnit ::= iIn::IO args::String
{
  postOps <- if a.generateDoc then [genDoc(a, grammars)] else []; 
}

abstract production genDoc
top::Unit ::= a::Command specs::[Decorated RootSpec]
{
  forwards to genDocHelp(a, specs)
	with {
		ioIn = print("Generating Silver Doc.\n", top.ioIn);
	};
}

abstract production genDocHelp
top::Unit ::= a::Command specs::[Decorated RootSpec]
{
  top.io = writeDocFiles(top.ioIn, a, specs, GlobalDocSection());
  
  top.code = 0;
  top.order = 6;
}

function writeDocFiles
IO ::= i::IO a::Command r::[Decorated RootSpec] h::[Decorated DocSection]
{
  local attribute body :: String;
  body = 
	"<html>\n" ++
	"<head>\n" ++
	"<style>\n" ++
"*{\n" ++
"  font: 14px arial;\n" ++
"}\n" ++
"a, a:visited{\n" ++
"  color:#0052A5;\n" ++
"  text-decoration:none;\n" ++
"}\n" ++
"a:hover{\n" ++
"  color:#069;\n" ++
"  text-decoration:underline;\n" ++
"}\n" ++
".section {\n" ++
"  margin-bottom:10px;\n" ++
"}\n" ++
".info{\n" ++
"  padding-left:10px;\n" ++
"  font-size: 10px;\n" ++
"}\n" ++
".rhs{\n" ++
"  padding-left:10px;\n" ++
"}\n" ++
".rRHS{\n" ++
"  padding-left:10px;\n" ++
"}\n" ++
".header{\n" ++
"  font-size:18px;\n" ++
"  font-weight: bold;\n" ++
"}\n" ++
".type{\n" ++
"  font-variant: small-caps;\n" ++
"}\n" ++
".value{}\n" ++
".row{\n" ++
"  margin-bottom: 5px;\n" ++
"}\n" ++
	"</style>\n" ++
	"</head>\n" ++
	"<body>\n" ++
  makeBody(h, flattenDoc(docMergeSort(head(r).documentation))) ++
	"</body>\n" ++
	"</html>\n";

  local attribute i1:: IO;
  i1 = system("mkdir -p " ++ a.generatedPath ++ makeFileName(head(r).declaredName), i).io;

   
  return if null(r) then i 
	else if head(r).interface then writeDocFiles(i1, a, tail(r), h)
	else writeFile(a.generatedPath ++ makeFileName(head(r).declaredName) ++ "/SilverDoc.html", body, writeDocFiles(i1, a, tail(r), h));
}

function makeBody
String ::= h::[Decorated DocSection] r::[Decorated Doc]{

  local attribute header :: Decorated DocSection;
  header = getDocSection(head(r).docOrder, h);

  return if null(r) then ""
	else 	header.sectionStart ++ "\n" ++ 
		head(r).docMessage ++
		header.sectionEnd ++ "\n" ++
		makeBody(h, tail(r));
}

