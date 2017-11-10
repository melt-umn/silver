grammar silver:extension:bidirtransform;

nonterminal Origin;

annotation redex :: Maybe<Origin>;
annotation labels :: [String];
annotation origin :: Origin;

synthesized attribute wasTransformed :: Boolean occurs on Origin;
synthesized attribute concreteOrigin :: Origin occurs on Origin;
synthesized attribute isBottomOrigin :: Boolean occurs on Origin;
synthesized attribute suppliedOrigin :: Origin;

-- If locations are special forms of origins
abstract production locOrigin
o::Origin ::= l::Location
{
    o.wasTransformed = false;
    o.concreteOrigin = o;
    o.isBottomOrigin = true;
}

-- a "bogus" origin
abstract production txtOrigin
o::Origin ::= s::String
{
    o.wasTransformed = false;
    o.concreteOrigin = o;
    o.isBottomOrigin = true;        
}

-- An origin indicating this is a part of some initial tree.
abstract production bottomOrigin
o::Origin ::= 
{
    o.wasTransformed = false;
    o.concreteOrigin = o;
    o.isBottomOrigin = true;     
}

function wasTransformed
Boolean ::= node::Origin redex::Maybe<Origin>
{
  return if redex.isJust then true else node.wasTransformed;
}

function getConcreteOrigin
Origin ::= node::Origin root::Origin
{
  return if node.isBottomOrigin then root else node.concreteOrigin;
}