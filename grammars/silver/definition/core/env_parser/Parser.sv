grammar silver:definition:core:env_parser;

import silver:definition:env;
import silver:definition:env:parser;

import silver:definition:core;

-- TODO: this whole file. Why is it here? Do we really need this?

terminal ImpliedNameTerm 'impliedName' lexer classes {C_1};

attribute impliedName occurs on IRootSpecPart, IRootSpecParts;

aspect production parserRootSpec
top::RootSpec ::= p::IRootSpecParts _
{
  top.interface= true;
  top.impliedName = p.impliedName;
}

aspect production aRoot1
top::IRootSpecParts ::= r::IRootSpecPart
{
  top.impliedName = r.impliedName; 
}

aspect production aRoot2
top::IRootSpecParts ::= r1::IRootSpecPart r2::IRootSpecParts
{
  top.impliedName = if r1.impliedName == "" then r2.impliedName else r1.impliedName; 
}

aspect production aRootSpecDefault
top::IRootSpecPart ::= 
{
  top.impliedName = "";

}

concrete production aRootImpliedName
top::IRootSpecPart ::= n::ImpliedNameTerm i::silver:definition:env:parser:Name
{
  top.impliedName = i.aname;
  forwards to aRootSpecDefault();
}
