grammar silver:definition:type:io;
import silver:definition:core;
import silver:definition:env;

concrete production fileTimeFunction
top::Expr ::= 'fileTime' '(' e1::Expr ',' e2::Expr ')'
{
  top.pp = "fileTime ( " ++ e1.pp ++ ", " ++ e2.pp ++ " )";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := e1.errors ++ e2.errors;
  top.warnings := e1.warnings ++ e2.warnings;

  top.typerep = ntTypeRep("core:IOInteger");

  e1.expected = expected_default();
  e2.expected = expected_default();
}

concrete production isFileFunction
top::Expr ::= 'isFile' '(' e1::Expr ',' e2::Expr ')'
{
  top.pp = "isFile(" ++ e1.pp ++ ", " ++ e2.pp ++ ")";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := e1.errors ++ e2.errors;
  top.warnings := e1.warnings ++ e2.warnings;

  top.typerep = ntTypeRep("core:IOBoolean");

  e1.expected = expected_default();
  e2.expected = expected_default();
}

concrete production isDirectoryFunction
top::Expr ::= 'isDirectory' '(' e1::Expr ',' e2::Expr ')'
{
  top.pp = "isDirecory(" ++ e1.pp ++ ", " ++ e2.pp ++ ")";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := e1.errors ++ e2.errors;
  top.warnings := e1.warnings ++ e2.warnings;

  top.typerep = ntTypeRep("core:IOBoolean");

  e1.expected = expected_default();
  e2.expected = expected_default();
}

concrete production mkdirFunction
top::Expr ::= 'mkdir' '(' e1::Expr ',' e2::Expr ')'
{
  top.pp = "mkdir(" ++ e1.pp ++ ", " ++ e2.pp ++ ")";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := e1.errors ++ e2.errors;
  top.warnings := e1.warnings ++ e2.warnings;

  top.typerep = ntTypeRep("core:IOBoolean");

  e1.expected = expected_default();
  e2.expected = expected_default();
}

concrete production readFunction
top::Expr ::= 'readFile' '(' e1::Expr ',' e2::Expr ')'
{
  top.pp = "readFile(" ++ e1.pp ++ ", " ++ e2.pp ++ ")";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := e1.errors ++ e2.errors;
  top.warnings := e1.warnings ++ e2.warnings;

  top.typerep = ntTypeRep("core:IOString");

  e1.expected = expected_default();
  e2.expected = expected_default();
}

concrete production cwdFunction
top::Expr ::= 'cwd' '(' e::Expr ')'
{
  top.pp = "cwd(" ++ e.pp ++ ")";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := e.errors;
  top.warnings := e.warnings;

  top.typerep = ntTypeRep("core:IOString");

  e.expected = expected_default();
}

concrete production envVarFunction
top::Expr ::= 'envVar' '(' e1::Expr ',' e2::Expr ')'
{
  top.pp = "envVar(" ++ e1.pp ++ ", " ++ e2.pp ++ ")";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := e1.errors ++ e2.errors;
  top.warnings := e1.warnings ++ e2.warnings;

  top.typerep = ntTypeRep("core:IOString");

  e1.expected = expected_default();
  e2.expected = expected_default();
}

concrete production systemFunction
top::Expr ::= 'system' '(' e1::Expr ',' e2::Expr ')'
{
  top.pp = "system(" ++ e1.pp ++ ", " ++ e2.pp ++ ")";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := e1.errors ++ e2.errors;
  top.warnings := e1.warnings ++ e2.warnings;

  top.typerep = ntTypeRep("core:IOInteger");

  e1.expected = expected_default();
  e2.expected = expected_default();
}


concrete production writeFunction
top::Expr ::= 'writeFile' '(' e1::Expr ',' e2::Expr ',' e3::Expr ')'
{
  top.pp = "writeFile(" ++ e1.pp ++ ", " ++ e2.pp ++ ", " ++ e3.pp ++ ")";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := e1.errors ++ e2.errors ++ e3.errors;
  top.warnings := e1.warnings ++ e2.warnings ++ e3.warnings;

  top.typerep = ioTypeRep();

  e1.expected = expected_default();
  e2.expected = expected_default();
  e3.expected = expected_default();
}

concrete production appendFunction
top::Expr ::= 'appendFile' '(' e1::Expr ',' e2::Expr ',' e3::Expr ')'
{
  top.pp = "appendFile(" ++ e1.pp ++ ", " ++ e2.pp ++ ", " ++ e3.pp ++ ")";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := e1.errors ++ e2.errors ++ e3.errors;
  top.warnings := e1.warnings ++ e2.warnings ++ e3.warnings;

  top.typerep = ioTypeRep();

  e1.expected = expected_default();
  e2.expected = expected_default();
  e3.expected = expected_default();
}

concrete production printFunction
top::Expr ::= 'print' '(' e1::Expr ',' e2::Expr ')'
{
  top.pp = "print(" ++ e1.pp ++ ", " ++ e2.pp ++ ")";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := e1.errors ++ e2.errors;
  top.warnings := e1.warnings ++ e2.warnings;

  top.typerep = ioTypeRep();

  e1.expected = expected_default();
  e2.expected = expected_default();
}

concrete production listContentsFunction
top::Expr ::= 'listContents' '(' e1::Expr ',' e2::Expr ')'
{
  top.pp = "listContents(" ++ e1.pp ++ ", " ++ e2.pp ++ ")";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := e1.errors ++ e2.errors;
  top.warnings := e1.warnings ++ e2.warnings;

  top.typerep = ntTypeRep("core:IOStringList");

  e1.expected = expected_default();
  e2.expected = expected_default();
}

concrete production genIntFunction
top::Expr ::= 'genInt' '(' ')'
{
  top.pp = "genInt()";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := [];
  top.warnings := [];

  top.typerep = integerTypeRep();
}

concrete production unsafeIOFunction
top::Expr ::= 'unsafeio'
{
  top.pp = "unsafeio";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := [];
  top.warnings := [];

  top.typerep = ioTypeRep();
}
