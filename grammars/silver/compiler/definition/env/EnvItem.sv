grammar silver:compiler:definition:env;

{--
 - An entry in the environment.
 -}
nonterminal EnvItem<a> with itemName, dcl<a>, envContribs<a>, filterItems, filterIncludeOnly, filterIncludeHiding, withRenames, renamed, pfx, prepended;

synthesized attribute itemName :: String;
synthesized attribute dcl<a> :: a;
synthesized attribute envContribs<a> :: [Pair<String a>];

inherited attribute filterItems::[String];
monoid attribute filterIncludeOnly::Boolean with true, &&;
monoid attribute filterIncludeHiding::Boolean with true, &&;

inherited attribute withRenames::[(String, String)];
functor attribute renamed;
inherited attribute pfx::String;
functor attribute prepended;

{--
 - Rare case: use of `import _ with _ as _` or `import _ as _` to rename.
 - Common case: `grammar:full:name` aka `name`. See `defaultEnvItem`.
 -}
abstract production renamedEnvItem
attribute fullName {} occurs on a =>
ei::EnvItem<a> ::= newname::String di::a
{
  ei.itemName = newname;
  ei.dcl = di;
  ei.envContribs =
    if newname != di.fullName
    then [pair(newname, di), pair(di.fullName, di)]
    else [pair(newname, di)];

  ei.filterIncludeOnly := contains(newname, ei.filterItems);
  ei.filterIncludeHiding := !contains(newname, ei.filterItems);
  ei.renamed =
    case lookup(newname, ei.withRenames) of
    | nothing() -> ei
    | just(result) -> renamedEnvItem(result, di)
    end;
  ei.prepended = renamedEnvItem(ei.pfx ++ newname, di);
}
{--
 - Entries at fullname ONLY.
 - Used for occurrences & production attributes, which are looked up
 - by the full nonterminal name (or production name) only, and a shortname is nonsense.
 -}
abstract production fullNameEnvItem
attribute fullName {} occurs on a =>
ei::EnvItem<a> ::= di::a
{
  ei.itemName = di.fullName;
  ei.dcl = di;
  ei.envContribs = [pair(di.fullName, di)];
  
  propagate filterIncludeOnly, filterIncludeHiding, renamed, prepended;  -- Always imported & not renamed
}
{--
 - Used for aspect local variables. The LHS and children have a full name
 - like `newname` and in the aspect we can rename it anything we want.
 - We should *not* see `newname` in the environment in those cases.
 -}
abstract production onlyRenamedEnvItem
ei::EnvItem<a> ::= newname::String di::a
{
  ei.itemName = newname;
  ei.dcl = di;
  ei.envContribs = [pair(newname, di)];
  
  propagate filterIncludeOnly, filterIncludeHiding, renamed, prepended;  -- Should never be imported
}

{--
 - The common case, normal shortnames.
 -}
function defaultEnvItem
attribute fullName {} occurs on a =>
EnvItem<a> ::= di::a
{
  return renamedEnvItem(fullNameToShort(di.fullName), di);
}
function fullNameToShort
String ::= s::String
{
  -- Works just fine, even when lastIndexOf returns -1
  return substring(lastIndexOf(":", s) + 1, length(s), s);
}



global mapGetDcls :: ([a] ::= [EnvItem<a>]) = map((.dcl), _);
global mapFullnameDcls :: attribute fullName {} occurs on a => ([EnvItem<a>] ::= [a]) =
  map(fullNameEnvItem, _);
global mapDefaultWrapDcls :: attribute fullName {} occurs on a => ([EnvItem<a>] ::= [a]) =
  map(defaultEnvItem, _);

{--
 - Maps a production's DclInfo into an EnvItem named for the nonterminal it constructs.
 -}
function envItemNTFromProdDcl
EnvItem<DclInfo> ::= di::DclInfo
{
  -- loooking up the full name of the nonterminal it creates will resolve this prodDcl
  return onlyRenamedEnvItem(di.namedSignature.outputElement.typerep.typeName, di);
}

