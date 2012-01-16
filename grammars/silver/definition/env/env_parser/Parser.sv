grammar silver:definition:env:env_parser;

import silver:definition:env;
import silver:definition:type:io;
import silver:definition:regex hiding RegexRBrack_t, RegexLBrack_t, RegexLParen_t, RegexRParen_t; -- TODO: a bit of a hack?
import silver:definition:type;

import silver:definition:core only grammarName, location, env;

lexer class C_0;
lexer class C_1 dominates C_0;

ignore terminal WS /[\ \n\t]+/ lexer classes {C_0};

terminal LB_t    '[';
terminal RB_t    ']';
terminal Comma_t ',';
terminal LParent_t    '(';
terminal RParent_t    ')';
terminal RegExprDelim '/' lexer classes {C_0};

terminal Sigturnstile '::=' ;

terminal Id_t /[\']([^\'\\]|[\\][\']|[\\][\\]|[\\]n|[\\]r|[\\]t)*[\']/ lexer classes {C_0};
terminal Num_t /\-?[0-9]+/ lexer classes {C_0};

terminal DefaultTerm  'default' lexer classes {C_1};

-- Dcls
terminal LocalTerm       'loc'  lexer classes {C_1};
terminal InheritedTerm   'inh'  lexer classes {C_1};
terminal SynthesizedTerm 'syn'  lexer classes {C_1};
terminal OccursTerm      '@'    lexer classes {C_1};
terminal ProdAttrTerm    'p@'   lexer classes {C_1};
terminal ForwardTerm     'fwd'  lexer classes {C_1};
terminal GlobTerm        'glob' lexer classes {C_1};

--shared dcl/type
terminal ProductionTerm  'prod' lexer classes {C_1};
terminal FunctionTerm    'fun'  lexer classes {C_1};
terminal TerminalTerm    'term' lexer classes {C_1};
terminal NonterminalTerm 'nt'   lexer classes {C_1};

-- Types
terminal IntegerTerm     'int'       lexer classes {C_1};
terminal FloatTerm       'float'     lexer classes {C_1};
terminal StringTerm      'string'    lexer classes {C_1};
terminal BooleanTerm     'bool'      lexer classes {C_1};
terminal DecoratedTerm   'decorated' lexer classes {C_1};
terminal IOTerm          'io'        lexer classes {C_1};
terminal ITyVar /[A-Za-z]+/ lexer classes {C_0}; 

-- signatures
terminal SignatureTerm      'signature' lexer classes {C_1};
terminal SignatureElementTerm 'element' lexer classes {C_1};

-- top level, root spec parts
terminal DeclaredNameTerm     'declaredName'     lexer classes {C_1};
terminal ModuleNamesTerm      'moduleNames'      lexer classes {C_1};
terminal DefsTerm             'defs'             lexer classes {C_1};
terminal ExportedGrammarsTerm 'exportedGrammars' lexer classes {C_1};
terminal CondBuildTerm        'condBuild'        lexer classes {C_1};


synthesized attribute spec :: Decorated RootSpec;
synthesized attribute signature :: Decorated NamedSignature;
synthesized attribute elements :: [Decorated NamedSignatureElement];
synthesized attribute element :: Decorated NamedSignatureElement;
synthesized attribute typereps :: [TypeExp];
synthesized attribute names :: [String];
synthesized attribute aname :: String;
synthesized attribute tyvars :: [TyVar];

{- The "uninteresting" plumbing of interface files: -}

nonterminal IRootSpec with spec;
nonterminal IRootSpecParts with defs, exportedGrammars, condBuild, declaredName, moduleNames, grammarName;
nonterminal IDefs with defs, env, grammarName; -- including square brackets
nonterminal IDefsInner with defs, env, grammarName; -- inside square brackets
nonterminal ITypeReps with env, typereps, grammarName; -- including square brackets
nonterminal ITypeRepsInner with env, typereps, grammarName; -- inside square brackets

{- Extension points! -}

{- Top-level elements of the interface file -}
nonterminal IRootSpecPart with defs, exportedGrammars, condBuild, declaredName, moduleNames, grammarName;
{- A DclInfo record -}
nonterminal IDclInfo with defs, env, grammarName;
{- A TypeExp record -}
nonterminal ITypeRep with env, typerep, grammarName;

{- Utilities -}
nonterminal ITyVarDcls with defs, tyvars;
 nonterminal ITyVarDclsInner with defs, tyvars;
nonterminal INamedSignature with signature, env, grammarName;
 nonterminal INamedSignatureElement with element, env, grammarName;
 nonterminal INamedSignatureElements with elements, env, grammarName;
 nonterminal INamedSignatureElementsInner with elements, env, grammarName;
{- List of (single-quoted) names, inside brackets [] -}
nonterminal INames with names;
nonterminal INamesInner with names;
{- A (single-quoted) name -}
nonterminal IName with aname;
{- Location info (Used by dclinfos, usually) -}
nonterminal ILocation with location;

-- a few simple utilities

concrete production quoted_name
top::IName ::= i::Id_t
{
  top.aname = substring(1, length(i.lexeme)-1, i.lexeme);
}

concrete production aLocationInfo
top::ILocation ::= filename::IName ',' line::Num_t ',' column::Num_t
{
  top.location = loc(filename.aname, toInt(line.lexeme), toInt(column.lexeme));
}

-- Exposing the interface to the outside world

abstract production parserRootSpec
top::RootSpec ::= p::IRootSpecParts
{
  p.grammarName = p.declaredName;

  top.unparse = unparseRootSpec(top).unparse;

  top.declaredName = p.declaredName; 
  top.moduleNames = p.moduleNames;
  top.defs = p.defs;
  top.exportedGrammars = p.exportedGrammars;
  top.condBuild = p.condBuild;

  forwards to i_emptyRootSpec();
}


--The Grammar 

concrete production aRootFull
top::IRootSpec ::= r::IRootSpecParts{
  top.spec = decorate parserRootSpec(r) with { };
}

concrete production aRoot1
top::IRootSpecParts ::= r::IRootSpecPart{
  top.declaredName = r.declaredName; 
  top.defs = r.defs;
  top.moduleNames = [];
  top.exportedGrammars = r.exportedGrammars;
  top.condBuild = r.condBuild;  
}

concrete production aRoot2
top::IRootSpecParts ::= r1::IRootSpecPart r2::IRootSpecParts{
  top.declaredName = if r1.declaredName == "" then r2.declaredName else r1.declaredName; 
  top.defs = appendDefs(r1.defs, r2.defs);
  top.moduleNames = r1.moduleNames ++ r2.moduleNames;
  top.exportedGrammars = r1.exportedGrammars ++ r2.exportedGrammars;
  top.condBuild = r1.condBuild ++ r2.condBuild;
}

--The pieces
abstract production aRootSpecDefault
top::IRootSpecPart ::= {
  top.declaredName = "";
  top.moduleNames = [];
  top.defs = emptyDefs();
  top.exportedGrammars = [];
  top.condBuild = [];
}

concrete production aRootDeclaredName
top::IRootSpecPart ::= 'declaredName' i::IName{
  top.declaredName = i.aname;
  forwards to aRootSpecDefault();
}

concrete production aRootModuleNames
top::IRootSpecPart ::= 'moduleNames' i::INames{
  top.moduleNames = i.names;
  forwards to aRootSpecDefault();
}

concrete production aRootDefs
top::IRootSpecPart ::= 'defs' i::IDefs{
  top.defs = i.defs;
  i.env = emptyEnv();
  forwards to aRootSpecDefault();
}

concrete production aRootExportedGrammars
top::IRootSpecPart ::= 'exportedGrammars' i::INames{
  top.exportedGrammars = i.names;
  forwards to aRootSpecDefault();
}

concrete production aRootCondBuilds
top::IRootSpecPart ::= 'condBuild' i::INames{
  top.condBuild = unfoldCB(i.names);
  forwards to aRootSpecDefault();
}

function unfoldCB
[[String]] ::= lst::[String]
{
  return if null(lst) then [] else cons( [head(lst), head(tail(lst))], unfoldCB(tail(tail(lst))));
}

--The lists
concrete production aDefsNone
top::IDefs ::= '[' ']'
{
  top.defs = emptyDefs();
}

concrete production aDefsOne
top::IDefs ::= '[' d::IDefsInner ']'
{
  top.defs = d.defs;
}

concrete production aDefsInnerOne
top::IDefsInner ::= d::IDclInfo
{
  top.defs = d.defs;
}

concrete production aDefsInnerCons
top::IDefsInner ::= d1::IDclInfo ',' d2::IDefsInner
{
  top.defs = appendDefs(d1.defs, d2.defs);
}

concrete production aNamesNone
top::INames ::= '[' ']'
{
  top.names = [];
}

concrete production aNamesOne
top::INames ::= '[' d::INamesInner ']'
{
  top.names = d.names;
}

concrete production aNamesInnerOne
top::INamesInner ::= d::IName
{
  top.names = [d.aname];
}

concrete production aNamesInnerCons
top::INamesInner ::= d1::IName ',' d2::INamesInner
{
  top.names = [d1.aname] ++ d2.names;
}

concrete production aTypeRepsNone
top::ITypeReps ::= '[' ']'
{
  top.typereps = [];
}

concrete production aTypeRepsOne
top::ITypeReps ::= '[' t::ITypeRepsInner ']'
{
  top.typereps = t.typereps;
}

concrete production aTypeRepsInnerOne
top::ITypeRepsInner ::= t::ITypeRep
{
  top.typereps = [t.typerep];
}

concrete production aTypeRepsInnerCons
top::ITypeRepsInner ::= t1::ITypeRep ',' t2::ITypeRepsInner
{
  top.typereps = [t1.typerep] ++ t2.typereps;
}

concrete production aNamedSignatureElementsNone
top::INamedSignatureElements ::= '['']'
{
  top.elements = [];
}

concrete production aNamedSignatureElementsOne
top::INamedSignatureElements ::= '[' t::INamedSignatureElementsInner ']'
{
  top.elements = t.elements;
}

concrete production aNamedSignatureElementsInnerOne
top::INamedSignatureElementsInner ::= t::INamedSignatureElement
{
  top.elements = [t.element];
}

concrete production aNamedSignatureElementsInnerCons
top::INamedSignatureElementsInner ::= t1::INamedSignatureElement ',' t2::INamedSignatureElementsInner
{
  top.elements = [t1.element] ++ t2.elements;
}

concrete production aTyVarDclsOne
top::ITyVarDcls ::= '[' t::ITyVarDclsInner ']'
{
  top.defs = t.defs;
  top.tyvars = t.tyvars;
}
concrete production aTyVarDclsNone
top::ITyVarDcls ::= '[' ']'
{
  top.defs = emptyDefs();
  top.tyvars = [];
}

concrete production aTyVarDclsInnerOne
top::ITyVarDclsInner ::= t1::ITyVar
{
  local attribute tv :: TyVar;
  tv = freshTyVar();
  
  top.defs = addLexTyVarDcl("IFACE", loc("IFACE",-1,-1), t1.lexeme, skolemTypeExp(tv), emptyDefs());
  top.tyvars = [tv];
}

concrete production aTyVarDclsInnerCons
top::ITyVarDclsInner ::= t1::ITyVar ',' t2::ITyVarDclsInner
{
  local attribute tv :: TyVar;
  tv = freshTyVar();
  
  top.defs = addLexTyVarDcl("IFACE", loc("IFACE",-1,-1), t1.lexeme, skolemTypeExp(tv), t2.defs);
  top.tyvars = [tv] ++ t2.tyvars;
}

--The DclInfos

concrete production aDclInfoLocal
top::IDclInfo ::= 'loc' '(' l::ILocation ',' fn::IName ',' t::ITypeRep ')'
{
  top.defs = addLocalDcl(top.grammarName, l.location, fn.aname, t.typerep, emptyDefs());
}

concrete production aDclInfoProduction
top::IDclInfo ::= 'prod' '(' l::ILocation ',' td::ITyVarDcls ',' s::INamedSignature ')'
{
  s.env = newScopeEnv(td.defs, top.env);
  
  top.defs = addProdDcl(top.grammarName, l.location, s.signature, emptyDefs());
}

concrete production aDclInfoFunction
top::IDclInfo ::= 'fun' '(' l::ILocation ',' td::ITyVarDcls ',' s::INamedSignature ')'
{
  s.env = newScopeEnv(td.defs, top.env);
  
  top.defs = addFunDcl(top.grammarName, l.location, s.signature, emptyDefs());
}

concrete production aDclInfoGlobalValue
top::IDclInfo ::= 'glob' '(' l::ILocation ',' fn::IName ',' t::ITypeRep ')'
{
  top.defs = addGlobalValueDcl(top.grammarName, l.location, fn.aname, t.typerep, emptyDefs());
}

concrete production aDclInfoNonterminal
top::IDclInfo ::= 'nt' '(' l::ILocation ',' s::IName ',' td::ITyVarDcls ',' t::ITypeRep ')'
{
  t.env = newScopeEnv(td.defs, top.env);
  
  top.defs = addNtDcl(top.grammarName, l.location, s.aname, td.tyvars, t.typerep, emptyDefs());
}

concrete production aDclInfoTerminal
top::IDclInfo ::= 'term' '(' l::ILocation ',' n::IName ',' '/' r::Regex_R '/' ')'
{
  top.defs = addTermDcl(top.grammarName, l.location, n.aname, r, emptyDefs());
}

concrete production aDclInfoSynthesized
top::IDclInfo ::= 'syn' '(' l::ILocation ',' fn::IName ',' td::ITyVarDcls ',' t::ITypeRep ')'
{
  t.env = newScopeEnv(td.defs, top.env);
  
  top.defs = addSynDcl(top.grammarName, l.location, fn.aname, td.tyvars, t.typerep, emptyDefs());
}

concrete production aDclInfoInherited
top::IDclInfo ::= 'inh' '(' l::ILocation ',' fn::IName ',' td::ITyVarDcls ',' t::ITypeRep ')'
{
  t.env = newScopeEnv(td.defs, top.env);
  
  top.defs = addInhDcl(top.grammarName, l.location, fn.aname, td.tyvars, t.typerep, emptyDefs());
}

concrete production aDclInfoProdAttr
top::IDclInfo ::= 'p@' '(' l::ILocation ',' fn::IName ',' td::ITyVarDcls ',' ot::ITypeRep '::=' its::ITypeReps ',' t::IDefs ')'
{
  ot.env = newScopeEnv(td.defs, top.env);
  its.env = ot.env;
  t.env = ot.env;

  top.defs = addPaDcl(top.grammarName, l.location, fn.aname, ot.typerep, its.typereps, t.defs, emptyDefs());
}

concrete production aDclInfoForward
top::IDclInfo ::= 'fwd' '(' l::ILocation ',' t::ITypeRep ')'
{
  top.defs = addForwardDcl(top.grammarName, l.location, t.typerep, emptyDefs());
}

concrete production aDclInfoOccurs
top::IDclInfo ::= '@' '(' l::ILocation ',' fnnt::IName ',' fnat::IName ',' td::ITyVarDcls ',' ntt::ITypeRep ',' att::ITypeRep ')'
{
  ntt.env = newScopeEnv(td.defs, top.env);
  att.env = ntt.env;
  
  local attribute fresh :: [TyVar];
  fresh = freshTyVars(length(td.tyvars));

  -- Recall that constraint on occurs DclInfos: the types need to be tyvars, not skolem constants.
  
  top.defs = addOccursDcl( top.grammarName, l.location, fnnt.aname, fnat.aname, 
                           freshenTypeExpWith(ntt.typerep, td.tyvars, fresh),
                           freshenTypeExpWith(att.typerep, td.tyvars, fresh),
                           emptyDefs());
}

--The TypeReps
concrete production aTypeRepInteger
top::ITypeRep ::= 'int'
{
  top.typerep = intTypeExp();
}

concrete production aTypeRepFloat
top::ITypeRep ::= 'float'
{
  top.typerep = floatTypeExp();
}

concrete production aTypeRepString
top::ITypeRep ::= 'string'
{
  top.typerep = stringTypeExp();
}

concrete production aTypeRepBoolean
top::ITypeRep ::= 'bool'
{
  top.typerep = boolTypeExp();
}

concrete production aTypeRepTerminal
top::ITypeRep ::= 'term' '(' n::IName ')'
{
  top.typerep = terminalTypeExp(n.aname);
}

concrete production aTypeRepNonterminal
top::ITypeRep ::= 'nt' '(' n::IName ',' ty::ITypeReps ')'
{
  top.typerep = nonterminalTypeExp(n.aname, ty.typereps);
}

concrete production aTypeRepDecorated
top::ITypeRep ::= 'decorated' '(' t::ITypeRep ')'
{
  top.typerep = decoratedTypeExp(t.typerep);
}

concrete production aTypeRepProduction
top::ITypeRep ::= 'prod' '(' it::ITypeReps ','  ot::ITypeRep ')'
{
  top.typerep = functionTypeExp(ot.typerep, it.typereps);
}

concrete production aTypeRepFunction
top::ITypeRep ::= 'fun' '(' it::ITypeReps ','  ot::ITypeRep ')'
{
  top.typerep = functionTypeExp(ot.typerep, it.typereps);
}

concrete production aTypeRepIO
top::ITypeRep ::= 'io'
{
  top.typerep = ioTypeExp();
}

concrete production aTypeRepVar
top::ITypeRep ::= t::ITyVar
{
  local attribute res :: [Decorated DclInfo];
  res = getTypeDcl(t.lexeme, top.env);
  
  top.typerep = if null(res)
                then error("INTERNAL ERROR: interface file for " ++ top.grammarName ++
                           " lacks type for tyvar " ++ t.lexeme ++
                           " on line " ++ toString(t.line) ++
                           " column " ++ toString(t.column))
                else head(res).typerep;
}

--The NamedSignatures
concrete production aNamedSignatureDcl
top::INamedSignature ::= 'signature' '(' fn::IName ',' i::INamedSignatureElements ',' o::INamedSignatureElement ')'
{
  top.signature = namedSignatureDcl(fn.aname, i.elements, o.element);
}

concrete production aNamedSignatureDefault
top::INamedSignature ::= 'signature'
{ -- TODO: maybe remove this?
  top.signature = decorate namedSignatureDefault() with {};
}

concrete production aNamedSignatureElementDcl
top::INamedSignatureElement ::= 'element' '(' n::IName ',' t::ITypeRep ')'
{
  top.element = namedSignatureElement(n.aname, t.typerep);
}

concrete production aNamedSignatureElementDclDefault
top::INamedSignatureElement ::= 'element'
{ -- TODO: remove this?
  top.element = decorate namedSignatureElementDefault() with {};
}
