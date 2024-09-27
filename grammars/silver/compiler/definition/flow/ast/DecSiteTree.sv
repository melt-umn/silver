grammar silver:compiler:definition:flow:ast;

{--
 - Represents a decision tree of potential decoration sites to determine if an inherited attribute
 - has been supplied for some vertex type.
 -}
nonterminal DecSiteTree with decSitePP, decSiteAlts, decSiteReqs;
flowtype DecSiteTree = decorate {}, forward {};

synthesized attribute decSitePP::String;
synthesized attribute decSiteAlts::[DecSiteTree];
synthesized attribute decSiteReqs::[DecSiteTree];

aspect default production
top::DecSiteTree ::=
{
  top.decSiteAlts = [^top];
  top.decSiteReqs = [^top];
}

{--
 - No attributes can be known to be supplied.
 - Primarilly, denotes a cycle in decoration site resolution.
 -}
production neverDec
top::DecSiteTree ::= 
{
  top.decSitePP = "never";
  top.decSiteAlts = [];
}

{-
 - All attributes are known to be supplied.
 - Can occur if a dispatch signature has no implementing productions, or in the search reduction process.
 -}
production alwaysDec
top::DecSiteTree ::= 
{
  top.decSitePP = "always";
  top.decSiteReqs = [];
}

{--
 - An attribute can be supplied by either of these sites.
 -}
production altDec
top::DecSiteTree ::= d1::DecSiteTree d2::DecSiteTree
{
  top.decSitePP = implode(" | ", map((.decSitePP), top.decSiteAlts));
  top.decSiteAlts = d1.decSiteAlts ++ d2.decSiteAlts;
}

{--
 - An attribute is present if it is supplied by both of these sites.
 -}
production bothDec
top::DecSiteTree ::= d1::DecSiteTree d2::DecSiteTree
{
  top.decSitePP = s"{${implode(", ", map((.decSitePP), top.decSiteReqs))}}";
  top.decSiteReqs = d1.decSiteReqs ++ d2.decSiteReqs;
}

{--
 - An attribute can be supplied to a vertex type in some production.
 -}
production directDec
top::DecSiteTree ::= prodName::String vt::VertexType
{
  -- TODO: What if vt is an anonVertexType?
  top.decSitePP = s"${vt.vertexPP} of production ${prodName}";
}

{--
 - An attribute can be supplied via forwarding.
 - Inherited attributes on a translation attribute are only supplied if this is
 - the forward of the production (not a forward prod attr), and there is no
 - override equation for the translation attribute.
 -}
production forwardDec
top::DecSiteTree ::= prodName::String prodAttrName::Maybe<String>
{
  top.decSitePP =
    case prodAttrName of
    | just(attrName) -> s"via forward production attribute ${attrName} of production ${prodName}"
    | _ -> s"via forward of production ${prodName}"
    end;
}

{--
 - An inherited attribute on a translation attribute can be supplied via a translation attribute.
 -}
production transAttrDec
top::DecSiteTree ::= attrName::String d::DecSiteTree
{
  top.decSitePP = s"via translation attribute ${attrName}: ${d.decSitePP}";
}

fun foldAnyDecSite DecSiteTree ::= ds::[DecSiteTree] =
  foldr(altDec, neverDec(), ds);

fun foldAllDecSite DecSiteTree ::= ds::[DecSiteTree] =
  foldr(bothDec, alwaysDec(), ds);

fun prettyDecSites String ::= nest::Integer d::DecSiteTree =
  replicate(nest, "\t") ++
  if length(d.decSiteAlts) > 1
  then "any of\n" ++ implode("\n", map(prettyDecSites(nest + 1, _), d.decSiteAlts))
  else if length(d.decSiteReqs) > 1
  then "all of\n" ++ implode("\n", map(prettyDecSites(nest + 1, _), d.decSiteReqs))
  else d.decSitePP;

derive Eq, Ord on DecSiteTree;

-- Semigroup/monoid instances for composing alternatives, since that more common that conjunction
instance Semigroup DecSiteTree {
  append = altDec;
}

instance Monoid DecSiteTree {
  mempty = neverDec();
}