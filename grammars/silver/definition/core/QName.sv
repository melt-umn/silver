grammar silver:definition:core;

concrete production qNameId
top::QName ::= id::Name
{
  top.name = id.name;
  top.pp = id.pp;
  top.location = id.location;
  
  top.lookupValue = decorate customLookup("value", getValueDcl, top.name, top.location) with { env = top.env; };
  top.lookupType = decorate customLookup("type", getTypeDcl, top.name, top.location) with { env = top.env; };
  top.lookupAttribute = decorate customLookup("attribute", getAttrDcl, top.name, top.location) with { env = top.env; };
}

concrete production qNameCons
top::QName ::= id::Name ':' qn::QName
{
  top.name = id.name ++ ":" ++ qn.name;
  top.pp = id.pp ++ ":" ++ qn.pp;
  top.location = loc(top.file, $2.line, $2.column);
  
  top.lookupValue = decorate customLookup("value", getValueDcl, top.name, top.location) with { env = top.env; };
  top.lookupType = decorate customLookup("type", getTypeDcl, top.name, top.location) with { env = top.env; };
  top.lookupAttribute = decorate customLookup("attribute", getAttrDcl, top.name, top.location) with { env = top.env; };
}

synthesized attribute lookupValue :: Decorated QNameLookup occurs on QName;
synthesized attribute lookupType :: Decorated QNameLookup occurs on QName;
synthesized attribute lookupAttribute :: Decorated QNameLookup occurs on QName;

nonterminal QNameLookup with fullName, typerep, errors, env, dcls, dcl, dclBoundVars;

abstract production customLookup
top::QNameLookup ::= kindOfLookup::String lookupFunc::Function([Decorated DclInfo] ::= String Decorated Env) name::String l::Decorated Location 
{
  top.dcls = lookupFunc(name, top.env);
  top.dcl = head(top.dcls);
  
  top.fullName = top.dcl.fullName;
  
  top.typerep = if null(top.dcls) then errorType()  else top.dcl.typerep;
  top.dclBoundVars = if null(top.dcls) then []      else top.dcl.dclBoundVars;
  
  top.errors := (if null(top.dcls)
                  then [err(l, "Undeclared " ++ kindOfLookup ++ " '" ++ name ++ "'.")]
                  else [])
             ++ (if length(top.dcls) > 1
                  then [err(l, "Ambiguous reference to " ++ kindOfLookup ++ " '" ++ name ++ "'. Possibilities are:\n" ++ printPossibilities(top.dcls))] 
                  else []);
}

function printPossibilities
String ::= lst::[Decorated DclInfo]
{
  local attribute dcl :: Decorated DclInfo;
  dcl = head(lst);
  
  -- TODO: perhaps some way of including types, when they are relevant (attributes, values)
  return if null(lst) then ""
         else ("\t" ++ dcl.fullName ++ " (" ++ dcl.sourceLocation.fileName ++ ":" ++ toString(dcl.sourceLocation.line) ++ ")\n")
              ++ printPossibilities(tail(lst));
}

