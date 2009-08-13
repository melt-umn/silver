grammar silver:definition:concrete_syntax;
import silver:definition:core;
import silver:definition:env;

nonterminal ParserDcl with location, grammarName, file, moduleNames, compiledGrammars, warnings, errors, defs, pp, parserDcls, fullName, typerep, nonTerminalDcls, terminalDcls, ruleDcls, env;
nonterminal ModuleStmtList with location, grammarName, file, moduleNames, compiledGrammars, warnings, errors, pp, nonTerminalDcls, terminalDcls, ruleDcls;

attribute ruleDcls, terminalDcls, nonTerminalDcls occurs on ModuleStmt, Module;

terminal Parser_kwd /parser/ lexer classes {KEYWORD};

concrete production nameIdParser
top::Name ::= /parser/
{
  forwards to nameId(terminal(Id_t, "parser", $1.line, $1.column));
}

concrete production parserDcl
top::AGDcl ::= p::ParserDcl{
  top.location = p.location;
  top.pp = p.pp;
  top.errors = p.errors;
  top.warnings = p.warnings;

  top.defs = p.defs;
  top.parserDcls = p.parserDcls;
  top.moduleNames = p.moduleNames;

  top.nonTerminalDcls = [];
  top.terminalDcls = [];
  top.ruleDcls = [];
}

concrete production parserStmt
top::ParserDcl ::= 'parser' n::Name '::' t::Type '{' m::ModuleStmtList '}' {
  top.pp = "parser " ++ m.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := t.errors ++ m.errors;
  top.warnings := t.warnings ++ m.warnings;

  top.fullName = top.grammarName ++ ":" ++ n.name;
  top.typerep = t.typerep;
  top.nonTerminalDcls = m.nonTerminalDcls;
  top.terminalDcls = m.terminalDcls;
  top.ruleDcls = m.ruleDcls;

  top.parserDcls = [parserSpec(top)];

  top.defs = addFullNameDcl(n.name, top.fullName,
	     addValueDcl(top.fullName, funTypeRep([stringTypeRep()], t.typerep),
	     emptyDefs()));

  top.moduleNames = m.moduleNames;
}

concrete production moduleStmtListOne
top::ModuleStmtList ::= c1::ModuleStmt ';' {

  top.pp = c1.pp;
  top.location = c1.location;
  top.moduleNames = c1.moduleNames;

  top.ruleDcls = c1.ruleDcls;
  top.terminalDcls = c1.terminalDcls;
  top.nonTerminalDcls = c1.nonTerminalDcls;

  top.warnings := c1.warnings;
  top.errors := c1.errors;
}

concrete production moduleStmtListCons
top::ModuleStmtList ::= c1::ModuleStmt ';' c2::ModuleStmtList {

  top.pp = c1.pp ++ ", " ++ c2.pp;
  top.location = c1.location;
  top.moduleNames = c1.moduleNames ++ c2.moduleNames;

  top.ruleDcls = c1.ruleDcls ++ c2.ruleDcls;
  top.terminalDcls = c1.terminalDcls ++ c2.terminalDcls;
  top.nonTerminalDcls = c1.nonTerminalDcls ++ c2.nonTerminalDcls;

  top.warnings := c1.warnings ++ c2.warnings;
  top.errors := c1.errors ++ c2.errors;
}

aspect production moduleAll
top::ModuleStmt ::= pkg::QName
{
  top.ruleDcls = m.ruleDcls;
  top.terminalDcls = m.terminalDcls;
  top.nonTerminalDcls = m.nonTerminalDcls;
}

aspect production module 
top::Module ::= c::[Decorated RootSpec] g::Decorated QName a::String o::[String] h::[String] w::[EnvMap] {

  top.ruleDcls = if null(mitem) 
	     then []
	     else head(mitem).ruleDcls;		  


  top.terminalDcls = if null(mitem) 
	     then []
	     else head(mitem).terminalDcls;		  


  top.nonTerminalDcls = if null(mitem) 
	     then []
	     else head(mitem).nonTerminalDcls;		  
}
