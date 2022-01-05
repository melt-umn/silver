grammar silver:compiler:extension:treegen;

imports silver:compiler:definition:core;
imports silver:compiler:definition:env;
imports silver:compiler:definition:concrete_syntax;
imports silver:compiler:definition:type;
imports silver:compiler:definition:type:syntax;
imports silver:compiler:extension:convenience;
imports silver:compiler:extension:list;
imports silver:compiler:extension:tuple;
imports silver:compiler:modification:lambda_fn;
imports silver:compiler:modification:let_fix;
imports silver:compiler:metatranslation;

terminal Generator_t 'generator' lexer classes {KEYWORD};

concrete production generatorDcl
top::AGDcl ::= 'generator' n::Name '::' t::TypeExpr '{' grammars::GeneratorComponents '}'
{
  top.unparse = s"generator ${n.unparse} :: ${t.unparse} { ${grammars.unparse} }";

  -- Generator components must be imported for the translation here,
  -- but an AGDcl can't (currently) forward to an import ModuleStmt -
  -- resorting to a slight interfering workaround for now.
  propagate moduleNames;
  forward.env = occursEnv(extraOccursDefs, newScopeEnv(grammars.defs ++ extraDefs, top.env));
  
  production attribute extraDefs::[Def] with ++;
  extraDefs := [];
  production attribute extraOccursDefs::[OccursDclInfo] with ++;
  extraOccursDefs := [];

  -- We also depend on the silver:regex library
  top.moduleNames <- ["silver:regex"];
  local regexMED::ModuleExportedDefs = moduleExportedDefs(top.location, top.compiledGrammars, top.grammarDependencies, ["silver:regex"], []);
  extraDefs <- regexMED.defs;
  extraOccursDefs <- regexMED.occursDefs;

  production specEnv::Decorated Env = newScopeEnv(grammars.defs, emptyEnv());
  production specTypeDcls::[TypeDclInfo] = map((.dcl), foldr(consDefs, nilDefs(), grammars.defs).typeList);
  production specNTs::[TypeDclInfo] =
    filter(
      \ d::TypeDclInfo -> d.isType && !d.isTypeAlias && d.typeScheme.monoType.isNonterminal,
      specTypeDcls);
  production specTerms::[TypeDclInfo] =
    filter(
      \ d::TypeDclInfo -> d.isType && !d.isTypeAlias && d.typeScheme.monoType.isTerminal,
      specTypeDcls);

  forwards to Silver_AGDcl {
    function $Name{n}
    silver:core:RandomGen<$TypeExpr{t}> ::= minDepth::Integer maxDepth::Integer
    {
      $ProductionStmt{
        foldr(
          productionStmtAppend(_, _, location=top.location),
          errorProductionStmt([], location=top.location), -- TODO: No nullProductionStmt?
          map(genNtLocalDecl(top.location, forward.env, specEnv, _), map((.fullName), specNTs)) ++
          map(genTermLocalDecl(top.location, forward.env, specEnv, _), map((.fullName), specTerms)))}
      return $Expr{genForType(top.location, forward.env, specEnv, Silver_Expr { 0 }, t.typerep)};
    }
  };

  -- Uncomment for debugging
  --top.errors := unsafeTracePrint(forward.errors, forward.unparse);
}

nonterminal GeneratorComponents with config, grammarName, location, unparse, errors, defs, moduleNames, compiledGrammars, grammarDependencies;
nonterminal GeneratorComponent with config, grammarName, location, unparse, errors, defs, moduleNames, compiledGrammars, grammarDependencies;

propagate errors, defs, moduleNames on GeneratorComponents, GeneratorComponent;

concrete production nilGeneratorComponent
top::GeneratorComponents ::=
{
  top.unparse = "";
}

concrete production consGeneratorComponent
top::GeneratorComponents ::= c1::GeneratorComponent  c2::GeneratorComponents
{
  top.unparse = c1.unparse ++ c2.unparse;
}

concrete production generatorComponent
top::GeneratorComponent ::= m::ModuleName ';'
{
  top.unparse = m.unparse ++ ";";
}

-- Generate the expression for constructing a type
function genForType
Expr ::= loc::Location  env::Decorated Env  specEnv::Decorated Env  depth::Expr  t::Type
{
  return
    case t of
    -- Monomorphic nonterminals that don't have an explicit Arbitrary instance,
    -- call the appropriate local generator function.
    | nonterminalType(ntName, [], _)
      when (getTypeDcl(ntName, specEnv), getInstanceDcl("silver:core:Arbitrary", t, env))
      matches (dcl :: _, []) -> Silver_Expr { $Name{name("gen_" ++ substitute(":", "_", ntName), loc)}($Expr{depth}) }
    
    | terminalType(tName)
      when (getTypeDcl(tName, specEnv), getInstanceDcl("silver:core:Arbitrary", t, env))
      matches (dcl :: _, []) -> Silver_Expr { $Name{name("gen_" ++ substitute(":", "_", tName), loc)}($Expr{depth}) }

    -- Lists are handled specially here, to allow recusively generating for
    -- e.g. lists of nonterminals in a production RHS.
    | appType(listCtrType(), elemT) ->
      Silver_Expr {
        silver:core:bind(random, \ len::Integer ->
          silver:core:sequence(
            \ depth::Integer -> $Expr{genForType(loc, env, specEnv, Silver_Expr { depth - 1 }, elemT)},
            silver:core:take(
              silver:core:toInteger(len * silver:core:toFloat($Expr{depth}))),
              silver:core:reverse(silver:core:range(0, $Expr{depth}))))
      }

    -- Primitives and polymorphic nonterminals (e.g. Pair for tuples) are
    -- handled by the Arbitrary type class.
    | _ -> Silver_Expr { $Name{name("silver:core:genArb", loc)}($Expr{depth}) }
    end; 
}

-- Determine whether we can generate an arbitrary value for some type.
function isTypeGeneratable
Boolean ::= env::Decorated Env  specEnv::Decorated Env  t::Type
{
  return
    case t of
    | nonterminalType(ntName, [], _) when getTypeDcl(ntName, specEnv) matches _ :: _ -> true
    | terminalType(ntName) when getTypeDcl(ntName, specEnv) matches _ :: _ -> true
    | appType(listCtrType(), elemT) -> isTypeGeneratable(env, specEnv, elemT)
    | _ -> !null(getInstanceDcl("silver:core:Arbitrary", t, env))
    end;
}

-- Determine whether we can generate an arbitrary value for some production -
-- i.e. that it is monomorphic and all the RHS types are generatable.
function isProdGeneratable
Boolean ::= env::Decorated Env  specEnv::Decorated Env  p::ValueDclInfo
{
  local prodType::Type = p.typeScheme.typerep;
  return
    null(p.typeScheme.boundVars) &&  -- Only consider generating for monomorphic productions, for now
    all(map(isTypeGeneratable(env, specEnv, _), prodType.inputTypes ++ map(snd, prodType.namedTypes)));
}

-- Used to sort productions according to their number of nonterminal children
function nonterminalArity
Integer ::= t::Type
{
  return length(filter((.isNonterminal), t.inputTypes));
}
function prodDclInfoNumChildLte
Boolean ::= l::ValueDclInfo  r::ValueDclInfo
{
  return nonterminalArity(l.typeScheme.typerep) <= nonterminalArity(r.typeScheme.typerep);
}
function prodDclInfoNumChildEq
Boolean ::= l::ValueDclInfo  r::ValueDclInfo
{
  return nonterminalArity(l.typeScheme.typerep) == nonterminalArity(r.typeScheme.typerep);
}
function prodDclInfoNumChildNonzero
Boolean ::= v::ValueDclInfo
{
  return nonterminalArity(v.typeScheme.typerep) > 0;
}

-- splits where operator becomes false in list
function takeWhile2
[a] ::= f::(Boolean ::= a a)  l::[a]
{
  return if null(l) then []
  else if null(tail(l)) then l
  else if f(head(l), head(tail(l))) then head(l) :: takeWhile2(f, tail(l))
  else [head(l)];
}

-- local genExpr::(RandomGen<Expr> ::= Integer) = \ depth::Integer -> ...;
function genNtLocalDecl
ProductionStmt ::= loc::Location  env::Decorated Env  specEnv::Decorated Env  nt::String
{
  -- All productions that are generatable for nt, sorted by arity
  local prods :: [ValueDclInfo] = 
    filter(isProdGeneratable(env, specEnv, _),
      sortBy(prodDclInfoNumChildLte,
        getKnownProds(nt, specEnv)));
  
  local num_lowest_arity :: Integer = length(takeWhile2(prodDclInfoNumChildEq, prods));
  local num_nonzero_arity :: Integer = length(filter(prodDclInfoNumChildNonzero, prods));
  
  local result::Expr =
    if null(prods)
    -- TODO: This could be a compile-time error, in theory
    then Silver_Expr { error($Expr{stringConst(terminal(String_t, "\"no generatable productions for nonterminal " ++ nt ++ "\""), location=loc)}) }
    else Silver_Expr {
      silver:core:bind(
        if depth >= maxDepth
        -- Exclude all but the lowest-arity productions
        then randomRange(0, $Expr{intConst(terminal(Int_t, toString(num_lowest_arity - 1)), location=loc)})
        else if depth < minDepth
        -- Exclude all arity-0 productions
        then randomRange(
          $Expr{intConst(terminal(Int_t, toString(if num_nonzero_arity == length(prods) then 0 else num_nonzero_arity)), location=loc)},
          $Expr{intConst(terminal(Int_t, toString(length(prods) - 1)), location=loc)})
        -- All productions
        else randomRange(0, $Expr{intConst(terminal(Int_t, toString(length(prods) - 1)), location=loc)}),
        \ i::Integer -> $Expr{generateExprChain(loc, env, specEnv, nt, 0, prods)})
    };
  
  return
    Silver_ProductionStmt {
      local $name{"gen_" ++ substitute(":", "_", nt)}::(silver:core:RandomGen<$TypeExpr{nominalTypeExpr(qName(loc, nt).qNameType, location=loc)}> ::= Integer) =
        \ depth::Integer -> $Expr{result};
    };
}

function genTermLocalDecl
ProductionStmt ::= loc::Location  env::Decorated Env  specEnv::Decorated Env  t::String
{
  local te::TypeExpr = nominalTypeExpr(qName(loc, t).qNameType, location=loc);
  return
    Silver_ProductionStmt {
      local $name{"gen_" ++ substitute(":", "_", t)}::(silver:core:RandomGen<$TypeExpr{te}> ::= Integer) =
        let genTerm::(silver:core:RandomGen<$TypeExpr{te}> ::= Location) = genArbTerminal($TypeExpr{te}, _)
        in \ depth::Integer -> silver:core:bind(silver:core:genArb(depth), genTerm)
        end;
    };
}

{-

We got the id 'Expr' incoming.

We should look up 'Expr' and get a list of productions, from that we get the indices.

We then map genForType over the list of productions and generate a big if-then-else tree based on the randomRange()

Note that this expects lst to be non-empty!

-}

function generateExprChain
Expr ::= loc::Location env::Decorated Env  specEnv::Decorated Env  nt::String index::Integer  lst::[ValueDclInfo]
{
  local prod::ValueDclInfo = head(lst);
  local prodType::Type = prod.typeScheme.typerep;
  local args::[(String, Type)] =
    zipWith(pair, map(\ i::Integer -> "a" ++ toString(i), range(0, length(prodType.inputTypes))), prodType.inputTypes) ++
    prodType.namedTypes;
  local argGenExprs::[Expr] =
    map(genForType(loc, env, specEnv, Silver_Expr { depth + 1 }, _), map(snd, args));
  local genRes::Expr =
    mkFullFunctionInvocation(
      loc, Silver_Expr { $name{prod.fullName} },
      map(\ i::Integer -> Silver_Expr { $name{"a" ++ toString(i)} }, range(0, length(prodType.inputTypes))),
      map(\ a::String -> (a, Silver_Expr { $name{a} }), map(fst, prodType.namedTypes)));
  local lambdaChain::Expr =
    foldr(
      \ arg::(String, Type) res::Expr ->
        Silver_Expr { \ $name{arg.1}::$TypeExpr{typerepTypeExpr(arg.2, location=loc)} -> $Expr{res} },
      genRes, args);
  local genProd::Expr =
    if null(argGenExprs)
    then Silver_Expr { silver:core:pure($Expr{genRes}) }
    else foldl(
      \ e1::Expr e2::Expr -> Silver_Expr { silver:core:ap($Expr{e1}, $Expr{e2}) },
      Silver_Expr { silver:core:map($Expr{lambdaChain}, $Expr{head(argGenExprs)}) },
      tail(argGenExprs));

  return if null(tail(lst)) then genProd
  else Silver_Expr {
    if i == $Expr{intConst(terminal(Int_t, toString(index)), location=loc)}
    then $Expr{genProd}
    else $Expr{generateExprChain(loc, env, specEnv, nt, index + 1, tail(lst))}
  };
}
