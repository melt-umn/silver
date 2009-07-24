grammar silver:definition:type:higherorder;
import silver:definition:core;
import silver:definition:env;

concrete production nttType
top::Type ::= q::QName
{
  top.pp = q.pp;
  top.location = q.location;

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

  top.typerep = if null(types) then topTypeRep() else head(types).typerep;
--  top.typerep = ntTypeRep(fName);
}
