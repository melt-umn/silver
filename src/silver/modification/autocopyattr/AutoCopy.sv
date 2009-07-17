grammar silver:modification:autocopyattr;
export silver:modification:autocopyattr;

import silver:util;
import silver:definition:core;
import silver:definition:env;

terminal AutoCopy_kwd 'autocopy' lexer precedence = 5;

concrete production autocopyDcl
top::AGDcl ::= 'autocopy' 'attribute' a::Name '::' t::Type ';'
{
  top.pp = "autocopy attribute " ++ a.name ++ " :: " ++ t.pp ++ " ;" ;
  top.location = loc(top.file, $1.line, $1.column);

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs = addAttributeDcl(fName, copyTypeRep(t.typerep), 
             addInheritedDcl(fName, 
	     addFullNameDcl(a.name, fName,  emptyDefs())));

  forwards to attributeDclInh(terminal(Inherited_kwd, "inherited", $1.line, $1.column), $2, a, $4, t, $6);
}



-- TypeRep extension. 
synthesized attribute isAutoCopy :: Boolean;
attribute isAutoCopy occurs on TypeRep;

function copyTypeRep
Decorated TypeRep ::= tr::Decorated TypeRep
{
  return decorate i_copyTypeRep(tr) with {};
}

abstract production i_copyTypeRep
top::TypeRep ::= tr::Decorated TypeRep
{
  top.isAutoCopy = true;
  top.unparse = "autocopy(" ++ tr.unparse ++ ")";
  forwards to new(tr);
}
aspect production i_defaultTypeRep
top::TypeRep ::= 
{
  top.isAutoCopy = false;
}

