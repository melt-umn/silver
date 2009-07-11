grammar silver:analysis:typechecking:type:io;
import silver:analysis:typechecking:core;
import silver:definition:core;
import silver:definition:env;
import silver:definition:type:io;

--TODO fix signature

aspect production fileTimeFunction
top::Expr ::= i::FileTime_kwd l::LParen_t e1::Expr c::Comma_t e2::Expr r::RParen_t
{
  local attribute e1_error :: [Decorated Message];
  e1_error =   if (e1.typerep.isString) 
	       then []
	       else [err(top.location, "First parameter to fileTime must be of type String.")];


  local attribute e2_error :: [Decorated Message];
  e2_error =   if (e2.typerep.isIO) 
	       then []
	       else [err(top.location, "Second parameter to fileTime must be of type IO.")];

  top.typeErrors = e1_error ++ e2_error ++ e1.typeErrors ++ e2.typeErrors;
}

aspect production isFileFunction
top::Expr ::= i::IsFile_kwd l::LParen_t e1::Expr c::Comma_t e2::Expr r::RParen_t
{
  local attribute e1_error :: [Decorated Message];
  e1_error =   if (e1.typerep.isString) 
	       then []
	       else [err(top.location, "First parameter to isFile must be of type String.")];


  local attribute e2_error :: [Decorated Message];
  e2_error =   if (e2.typerep.isIO) 
	       then []
	       else [err(top.location, "Second parameter to isFile must be of type IO.")];

  top.typeErrors = e1_error ++ e2_error ++ e1.typeErrors ++ e2.typeErrors;
}

aspect production isDirectoryFunction
top::Expr ::= i::IsDirectory_kwd l::LParen_t e1::Expr c::Comma_t e2::Expr r::RParen_t
{
  local attribute e1_error :: [Decorated Message];
  e1_error =   if (e1.typerep.isString) 
	       then []
	       else [err(top.location, "First parameter to isDirectory must be of type String.")];


  local attribute e2_error :: [Decorated Message];
  e2_error =   if (e2.typerep.isIO) 
	       then []
	       else [err(top.location, "Second parameter to isDirectory must be of type IO.")];

  top.typeErrors = e1_error ++ e2_error ++ e1.typeErrors ++ e2.typeErrors;
}

aspect production readFunction
top::Expr ::= r::ReadFile_kwd l::LParen_t e1::Expr c::Comma_t e2::Expr rp::RParen_t
{
  local attribute e1_error :: [Decorated Message];
  e1_error =   if (e1.typerep.isString) 
	       then []
	       else [err(top.location, "First parameter to readFile must be of type String.")];


  local attribute e2_error :: [Decorated Message];
  e2_error =   if (e2.typerep.isIO) 
	       then []
	       else [err(top.location, "Second parameter to readFile must be of type IO.")];

  top.typeErrors = e1_error ++ e2_error ++ e1.typeErrors ++ e2.typeErrors;
}

aspect production cwdFunction
top::Expr ::= c::CWD_kwd l::LParen_t e::Expr r::RParen_t
{
  local attribute e1_error :: [Decorated Message];
  e1_error =   if (e.typerep.isIO) 
	       then []
	       else [err(top.location, "Second parameter to readFile must be of type IO.")];

  top.typeErrors = e1_error ++ e.typeErrors;
}



aspect production envVarFunction
top::Expr ::= e::EnvVar_kwd l::LParen_t e1::Expr c::Comma_t e2::Expr r::RParen_t
{

  local attribute e1_error :: [Decorated Message];
  e1_error =   if (e1.typerep.isString) 
	       then []
	       else [err(top.location, "First parameter to readFile must be of type String.")];


  local attribute e2_error :: [Decorated Message];
  e2_error =   if (e2.typerep.isIO) 
	       then []
	       else [err(top.location, "Second parameter to readFile must be of type IO.")];

  top.typeErrors = e1_error ++ e2_error ++ e1.typeErrors ++ e2.typeErrors;
}

aspect production systemFunction
top::Expr ::= s::System_kwd l::LParen_t e1::Expr c::Comma_t e2::Expr r::RParen_t
{
  local attribute e1_error :: [Decorated Message];
  e1_error =   if (e1.typerep.isString) 
	       then []
	       else [err(top.location, "First parameter to readFile must be of type String.")];


  local attribute e2_error :: [Decorated Message];
  e2_error =   if (e2.typerep.isIO) 
	       then []
	       else [err(top.location, "Second parameter to readFile must be of type IO.")];

  top.typeErrors = e1_error ++ e2_error ++ e1.typeErrors ++ e2.typeErrors;
}


aspect production writeFunction
top::Expr ::= w::WriteFile_kwd l::LParen_t e1::Expr c1::Comma_t e2::Expr c2::Comma_t e3::Expr r::RParen_t
{

  local attribute e1_error :: [Decorated Message];
  e1_error =   if (e1.typerep.isString) 
	       then []
	       else [err(top.location, "First parameter to writeFile must be of type String.")];


  local attribute e2_error :: [Decorated Message];
  e2_error =   if (e2.typerep.isString) 
	       then []
	       else [err(top.location, "Second parameter to writeFile must be of type String.")];


  local attribute e3_error :: [Decorated Message];
  e3_error =   if (e3.typerep.isIO) 
	       then []
	       else [err(top.location, "Third parameter to writeFile must be of type IO.")];

  top.typeErrors = e1_error ++ e2_error ++ e3_error ++ e1.typeErrors ++ e2.typeErrors ++ e3.typeErrors;

}

aspect production appendFunction
top::Expr ::= a::AppendFile_kwd l::LParen_t e1::Expr c1::Comma_t e2::Expr c2::Comma_t e3::Expr r::RParen_t
{

  local attribute e1_error :: [Decorated Message];
  e1_error =   if (e1.typerep.isString) 
	       then []
	       else [err(top.location, "First parameter to appendFile must be of type String.")];


  local attribute e2_error :: [Decorated Message];
  e2_error =   if (e2.typerep.isString) 
	       then []
	       else [err(top.location, "Second parameter to appendFile must be of type String.")];


  local attribute e3_error :: [Decorated Message];
  e3_error =   if (e3.typerep.isIO) 
	       then []
	       else [err(top.location, "Third parameter to appendFile must be of type IO.")];

  top.typeErrors = e1_error ++ e2_error ++ e3_error ++ e1.typeErrors ++ e2.typeErrors ++ e3.typeErrors;
}





aspect production printFunction
top::Expr ::= p::Print_kwd l::LParen_t e1::Expr c::Comma_t e2::Expr r::RParen_t
{

  local attribute e1_error :: [Decorated Message];
  e1_error =   if (e1.typerep.isString) 
	       then []
	       else [err(top.location, "First parameter to print must be of type String.")];


  local attribute e2_error :: [Decorated Message];
  e2_error =   if (e2.typerep.isIO) 
	       then []
	       else [err(top.location, "Second parameter to print must be of type IO.")];

  top.typeErrors = e1_error ++ e2_error ++ e1.typeErrors ++ e2.typeErrors;

}

aspect production listContentsFunction
top::Expr ::= li::ListContents_kwd l::LParen_t e1::Expr c::Comma_t e2::Expr r::RParen_t
{

  local attribute e1_error :: [Decorated Message];
  e1_error =   if (e1.typerep.isString) 
	       then []
	       else [err(top.location, "First parameter to listContents must be of type String.")];


  local attribute e2_error :: [Decorated Message];
  e2_error =   if (e2.typerep.isIO) 
	       then []
	       else [err(top.location, "Second parameter to listContents must be of type IO.")];

  local attribute ref :: [Decorated EnvItem];
  ref = getTypeDcl("core:StringList", top.env);

  local attribute error_sl :: [Decorated Message];
  error_sl = if !null(ref) then []
	     else [err(top.location, "StringList not found in the environment.  You must import the grammar core.")];

  top.typeErrors = e1_error ++ e2_error ++ e1.typeErrors ++ e2.typeErrors;
}

aspect production unsafeIOFunction
top::Expr ::= u::UnsafeIO_kwd
{  
   top.typeErrors = [];
}

aspect production genIntFunction
top::Expr ::= g::GenInt_kwd l::LParen_t r::RParen_t
{
  top.typeErrors = [];
}