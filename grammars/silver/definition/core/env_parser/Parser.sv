grammar silver:definition:core:env_parser;

import silver:definition:env;
import silver:definition:env:parser;

import silver:definition:core;

terminal ImpliedNameTerm 'impliedName' lexer classes {C_1};

attribute impliedName occurs on aRootSpecPart, aRootSpecParts;

aspect production parserRootSpec
top::RootSpec ::= p::aRootSpecParts _{
  top.interface= true;
  top.impliedName = p.impliedName;
}

aspect production aRoot1
top::aRootSpecParts ::= r::aRootSpecPart{
  top.impliedName = r.impliedName; 
}

aspect production aRoot2
top::aRootSpecParts ::= r1::aRootSpecPart r2::aRootSpecParts{
  top.impliedName = if r1.impliedName == "" then r2.impliedName else r1.impliedName; 
}

aspect production aRootSpecDefault
top::aRootSpecPart ::= {
  top.impliedName = "";

}

concrete production aRootImpliedName
top::aRootSpecPart ::= n::ImpliedNameTerm i::silver:definition:env:parser:Name{
  top.impliedName = i.aname;
  forwards to aRootSpecDefault();
}
