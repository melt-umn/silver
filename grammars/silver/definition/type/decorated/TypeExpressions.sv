grammar silver:definition:type:decorated;
import silver:definition:core;
import silver:definition:env;

concrete production refType
top::Type ::= 'Decorated' q::QName
{
  top.pp = "Decorated " ++ q.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.typerep = if null(types) then topTypeRep() else refTypeRep(head(types).typerep);

  top.warnings := [];
  top.errors := if null(types) 
	        then [err(top.location, "'" ++ q.name ++ "' is not bound to a type.")]
	        else [];

  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDcl(q.name, top.env);

  production attribute fName :: String;
  fName = if !null(fNames) then head(fNames).fullName else q.name;

  production attribute types :: [Decorated EnvItem];
  types = getTypeDcl(fName, top.env);
}
