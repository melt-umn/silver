grammar silver:modification:annotation;

function annoDef
Def ::= sg::String  sl::Location  fn::String  bound::[TyVar]  ty::TypeExp
{
  return attrDef(defaultEnvItem(annoDcl(sg,sl,fn,bound,ty)));
}
function annoInstanceDef
Def ::= sg::String  sl::Location  fnnt::String  fnat::String  ntty::TypeExp  atty::TypeExp
{
  return oDef(annoInstanceDcl(sg,sl,fnnt,fnat,ntty,atty));
}

-- This ensure the annotation list is in the properly sorted order!
function annotationsForNonterminal
[NamedSignatureElement] ::= nt::TypeExp  env::Decorated Env
{
  local annos :: [DclInfo] =
    filter((.isAnnotation), getAttrsOn(nt.typeName, env));
  
  return sortBy(namedSignatureElementLte, map(annoInstanceToNamed(nt, _), annos));
}

function annoInstanceToNamed
NamedSignatureElement ::= nt::TypeExp  anno::DclInfo
{
  -- Used to compute the local typerep for this nonterminal
  anno.givenNonterminalType = nt;
  
  -- We are currently cutting annotations down to just their SHORT NAME for the purpose of supplying the values.
  -- Basically, nonterminals should have curated the list of annotations so the names do not overlap.
  
  local annoShortName :: String =
    substring(lastIndexOf(":", anno.attrOccurring) + 1, length(anno.attrOccurring), anno.attrOccurring);
  
  return namedSignatureElement(annoShortName, anno.typerep);
}

function findNamedSigElem
Integer ::= s::String l::[NamedSignatureElement] z::Integer
{
  return if null(l) then -1
  else if s == head(l).elementName then z
  else findNamedSigElem(s, tail(l), z+1);
}

