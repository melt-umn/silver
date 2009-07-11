grammar silver:translation:java:type:io;
import silver:translation:java:core;

import silver:definition:core;
import silver:definition:type:io;

aspect production fileTimeFunction
top::Expr ::= 'fileTime' '(' e1::Expr ',' e2::Expr ')'
{
  top.translation = "(new core.PioInteger(" ++ e2.translation ++ ", common.Util.fileTime(" ++ e1.translation ++ ")))";
}

aspect production isFileFunction
top::Expr ::= 'isFile' '(' e1::Expr ',' e2::Expr ')'
{
  top.translation = "(new core.PioBoolean(" ++ e2.translation ++ ", common.Util.isFile(" ++ e1.translation ++ ")))";
}

aspect production isDirectoryFunction
top::Expr ::= 'isDirectory' '(' e1::Expr ',' e2::Expr ')'
{
  top.translation = "(new core.PioBoolean(" ++ e2.translation ++ ", common.Util.isDirectory(" ++ e1.translation ++ ")))";
}

aspect production readFunction
top::Expr ::= 'readFile' '(' e1::Expr ',' e2::Expr ')'
{
  top.translation = "(new core.PioString(" ++ e2.translation ++ ", common.Util.readFile(" ++ e1.translation ++ ")))";
}

aspect production cwdFunction
top::Expr ::= 'cwd' '(' e::Expr ')'
{
  top.translation = "(new core.PioString(" ++ e.translation ++ ", common.Util.cwd()))";
}

--TODO
aspect production envVarFunction
top::Expr ::= 'envVar' '(' e1::Expr ',' e2::Expr ')'
{
  top.translation = "(new core.PioString(" ++ e2.translation ++ ", common.Util.env(" ++ e1.translation ++ ")))";
}

aspect production systemFunction
top::Expr ::= 'system' '(' e1::Expr ',' e2::Expr ')'
{
  top.translation = "(new core.PioString(" ++ e2.translation ++ ", common.Util.system(" ++ e1.translation ++ ")))";
}

aspect production writeFunction
top::Expr ::= 'writeFile' '(' e1::Expr ',' e2::Expr ',' e3::Expr ')'
{  
  top.translation = "(common.Util.io(" ++ e3.translation ++ ", common.Util.writeFile(" ++ e1.translation ++ ", " ++ e2.translation ++ ")))";
}

aspect production appendFunction
top::Expr ::= 'appendFile' '(' e1::Expr ',' e2::Expr ',' e3::Expr ')'
{
  top.translation = "(common.Util.io(" ++ e3.translation ++ ", common.Util.appendFile(" ++ e1.translation ++ ", " ++ e2.translation ++ ")))";
}

aspect production printFunction
top::Expr ::= 'print' '(' e1::Expr ',' e2::Expr ')'
{
  top.translation = "(common.Util.io(" ++ e2.translation ++ ", common.Util.print(" ++ e1.translation ++ ")))";
}

--TODO
aspect production listContentsFunction
top::Expr ::= 'listContents' '(' e1::Expr ',' e2::Expr ')'
{
  top.translation = "(new core.PioStringList(" ++ e2.translation ++ ", common.Util.listContents(" ++ e1.translation ++ ")))";
}

aspect production unsafeIOFunction
top::Expr ::= 'unsafeio'
{  
  top.translation = "null";
}

aspect production genIntFunction
top::Expr ::= 'genInt' '(' ')'
{
  top.translation = "(common.Util.genInt())";
}
