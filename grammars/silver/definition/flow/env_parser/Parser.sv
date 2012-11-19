grammar silver:definition:flow:env_parser;

import silver:definition:env;
import silver:definition:env:env_parser;

import silver:definition:core only location, env;
import silver:definition:flow:env only flowDefs,flowDeps;
import silver:definition:flow:ast;
import silver:driver:util only RootSpec, interfaceRootSpec;

terminal FlowTerm 'flow' lexer classes {C_1};
terminal DefTerm 'def' lexer classes {C_1};
terminal ImplicitFwdAffectsTerm 'implicitFwdAffects' lexer classes {C_1};
terminal LhsSynVTerm     'lhsSynV' lexer classes {C_1};
terminal LhsInhVTerm     'lhsInhV' lexer classes {C_1};
terminal RhsVTerm        'rhsV' lexer classes {C_1};
terminal LocalEqVTerm    'localEqV' lexer classes {C_1};
terminal LocalVTerm 	 'localV' lexer classes {C_1}; 
terminal NtRefDefTerm 	 'ntRefFlowDef' lexer classes {C_1}; 
terminal NonHostSynTerm  'nonHostSyn' lexer classes {C_1}; 
terminal LocalTerm 	 'local' lexer classes {C_1}; 
terminal LocalInhTerm 	 'localInh' lexer classes {C_1};
terminal FwdInhTerm 	 'fwdInh' lexer classes {C_1};  
terminal ExtraTerm 	 'extra' lexer classes {C_1};  

attribute flowDefs occurs on IRoot, IRootPart;

--------------- i don't know yet ------------------------
aspect production interfaceRootSpec
top::RootSpec ::= p::IRoot
{
  top.flowDefs = p.flowDefs;
}
---------------------------------------------------------

aspect production aRoot1
top::IRoot ::= r::IRootPart
{
  top.flowDefs = r.flowDefs;
}

aspect production aRoot2
top::IRoot ::= r1::IRootPart r2::IRoot
{
  top.flowDefs = r1.flowDefs ++ r2.flowDefs;
}

----

aspect default production
top::IRootPart ::=
{
  top.flowDefs = [];
}

concrete production aRootFlow
top::IRootPart ::= 'flow' s::IFlows
{
  top.flowDefs = s.flowDefs;
}

nonterminal IFlows with flowDefs;
nonterminal IFlowsInner with flowDefs;
nonterminal IFlow with flowDefs;

nonterminal IFlowVertexes with flowDeps;
nonterminal IFlowVertexesInner with flowDeps;
nonterminal IFlowVertex with flowDeps;

-- Start change --
concrete production aFlowsNone_vertex
top::IFlowVertexes ::= '[' ']'
{
  top.flowDeps = [];
}

concrete production aFlowsSome_vertex
top::IFlowVertexes ::= '[' l::IFlowVertexesInner ']'
{
  top.flowDeps = l.flowDeps;
}

concrete production aFlowsOne_vertex
top::IFlowVertexesInner ::= l::IFlowVertex
{
  top.flowDeps = l.flowDeps;
}
concrete production aFlowsCons_vertex
top::IFlowVertexesInner ::= l::IFlowVertexesInner ',' r::IFlowVertex
{
  top.flowDeps = l.flowDeps ++ r.flowDeps;
}

concrete production aFlowSyn_vertex
top::IFlowVertex ::= 'lhsSynV' '('  attr::IName ')'
{
  top.flowDeps = [lhsSynVertex( attr.aname)];
}

concrete production aFlowInh_vertex
top::IFlowVertex ::= 'lhsInhV' '('  attr::IName ')'
{
  top.flowDeps = [lhsInhVertex( attr.aname)];
}

concrete production aFlowRhs_vertex
top::IFlowVertex ::= 'rhsV' '('  sigName::IName ',' attr::IName ')'
{
  top.flowDeps = [rhsVertex(sigName.aname, attr.aname)];
}

concrete production aFlowlocalEqV_vertex
top::IFlowVertex ::= 'localEqV' '('  fname::IName ')'
{
  top.flowDeps = [localEqVertex( fname.aname)];
}

concrete production aFlowlocalV_vertex
top::IFlowVertex ::= 'localV' '('  fname::IName ',' attr::IName ')'
{
  top.flowDeps = [localVertex( fname.aname,attr.aname)];
}





-- End change


concrete production aFlowsNone
top::IFlows ::= '[' ']'
{
  top.flowDefs = [];
}

concrete production aFlowsSome
top::IFlows ::= '[' l::IFlowsInner ']'
{
  top.flowDefs = l.flowDefs;
}

concrete production aFlowsOne
top::IFlowsInner ::= l::IFlow
{
  top.flowDefs = l.flowDefs;
}
concrete production aFlowsCons
top::IFlowsInner ::= l::IFlowsInner ',' r::IFlow
{
  top.flowDefs = l.flowDefs ++ r.flowDefs;
}

concrete production aFlowSyn
top::IFlow ::= 'syn' '(' prod::IName ',' attr::IName ',' fv::IFlowVertexes ',' a::IBool ')'
{
  top.flowDefs = [synEq(prod.aname, attr.aname,fv.flowDeps,a.bval)];
}

concrete production aFlowDef
top::IFlow ::= 'def' '(' nt::IName ',' attr::IName ',' fv::IFlowVertexes')'
{
  top.flowDefs = [defEq(nt.aname, attr.aname,fv.flowDeps)];
}

concrete production aFlowFwd
top::IFlow ::= 'fwd' '(' prod::IName ',' fv::IFlowVertexes ',' a::IBool')'
{
  top.flowDefs = [fwdEq(prod.aname,fv.flowDeps,a.bval)];
}

concrete production aFlowProd
top::IFlow ::= 'prod' '(' nt::IName ',' prod::IName ')'
{
  top.flowDefs = [prodFlowDef(nt.aname, prod.aname)];
}

concrete production aFlowRefFlowDef
top::IFlow ::= 'ntRefFlowDef' '(' nt::IName ',' prod::INames ')'
{
  top.flowDefs = [ntRefFlowDef(nt.aname, prod.names)];
}

concrete production aFlowImplicitFwdAffects
top::IFlow ::= 'implicitFwdAffects' '(' prd::IName ',' attrs::INames ')'
{
  top.flowDefs = [implicitFwdAffects(prd.aname, attrs.names)];
}

concrete production aFlowNonHostSyn
top::IFlow ::= 'nonHostSyn' '(' attr::IName ',' nt::IName  ')'
{
  top.flowDefs = [nonHostSynDef(attr.aname,nt.aname)];
}

concrete production aFlowInh
top::IFlow ::= 'inh' '('  prod::IName ',' sigName::IName ',' attr::IName ',' fv::IFlowVertexes  ')'
{
  top.flowDefs = [inhEq(prod.aname, sigName.aname,attr.aname,fv.flowDeps)];
}

concrete production aFlowFwdInh
top::IFlow ::= 'fwdInh' '('  prod::IName ',' attr::IName ',' fv::IFlowVertexes ')'
{
  top.flowDefs = [fwdInhEq(prod.aname,attr.aname,fv.flowDeps)];
}


concrete production aFlowLocalEq
top::IFlow ::= 'local' '('  prod::IName ',' fname::IName ',' typeName::IName ',' fv::IFlowVertexes  ')'
{
  top.flowDefs = [inhEq(prod.aname, fname.aname,typeName.aname,fv.flowDeps)];
}

concrete production aFlowLocalInhEq
top::IFlow ::= 'localInh' '('  prod::IName ',' fname::IName ',' attr::IName ',' fv::IFlowVertexes    ')'
{
  top.flowDefs = [localInhEq(prod.aname, fname.aname,attr.aname,fv.flowDeps)];
}

concrete production aFlowExtra
top::IFlow ::= 'extra' '(' prod::IName ',' ifv::IFlowVertex ',' fv::IFlowVertexes ',' a::IBool ')'
{
  top.flowDefs = [extraEq(prod.aname, head(ifv.flowDeps),fv.flowDeps,a.bval)];
}
