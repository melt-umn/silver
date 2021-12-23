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
  forward.env = newScopeEnv(grammars.defs, top.env);

  production specEnv::Decorated Env = newScopeEnv(grammars.defs, emptyEnv());
  production specNTs::[TypeDclInfo] =
    filter(\
      d::TypeDclInfo -> d.isType && !d.isTypeAlias && d.typeScheme.monoType.isNonterminal,
      map((.dcl), foldr(consDefs, nilDefs(), grammars.defs).typeList));

  forwards to Silver_AGDcl {
    function $Name{n}
    $TypeExpr{t} ::= depth::Integer
    {
      $ProductionStmt{
        foldr(
          productionStmtAppend(_, _, location=top.location),
          errorProductionStmt([], location=top.location), -- TODO: No nullProductionStmt?
          map(genNtLocalDecl(top.location, top.env, specEnv, _), map((.fullName), specNTs)))}
      return $Expr{genForType(top.location, top.env, specEnv, Silver_Expr { depth }, t.typerep)};
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
  top.defs <- unsafeTracePrint([], hackUnparse(m) ++ ": " ++ hackUnparse(m.defs) ++ "\n\n\n");
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

    -- Lists are handled specially here, to allow recusively generating for
    -- e.g. lists of nonterminals in a production RHS.
    | appType(listCtrType(), elemT) ->
      Silver_Expr {
        map(
          \ depth::Integer -> $Expr{genForType(loc, env, specEnv, Silver_Expr { depth - 1 }, elemT)},
          take(toInteger(genRand() * toFloat($Expr{depth}))), reverse(range(0, $Expr{depth})))
      }

    -- Primitives and polymorphic nonterminals (e.g. Pair for tuples) are
    -- handled by the Arbitrary type class.
    | _ -> Silver_Expr { $Name{name("genArb", loc)}($Expr{depth}) }
    end; 
}

-- Determine whether we can generate an arbitrary value for some type.
function isTypeGeneratable
Boolean ::= env::Decorated Env  specEnv::Decorated Env  t::Type
{
  return
    case t of
    | nonterminalType(ntName, [], _) when getTypeDcl(ntName, specEnv) matches _ :: _ -> true
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

-- Used to sort productions according to their arity (number of children)
function prodDclInfoNumChildLte
Boolean ::= l::ValueDclInfo  r::ValueDclInfo
{
  return l.typeScheme.arity <= r.typeScheme.arity;
}
function prodDclInfoNumChildEq
Boolean ::= l::ValueDclInfo  r::ValueDclInfo
{
  return l.typeScheme.arity == r.typeScheme.arity;
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

-- local genExpr::(Expr ::= Integer) = \ depth::Integer -> ...;
function genNtLocalDecl
ProductionStmt ::= loc::Location  env::Decorated Env  specEnv::Decorated Env  nt::String
{
  -- All productions that are generatable for nt, sorted by arity
  local prods :: [ValueDclInfo] = 
    filter(isProdGeneratable(env, specEnv, _),
      sortBy(prodDclInfoNumChildLte,
        getKnownProds(nt, specEnv)));
  
  local num_lowest_arity :: Integer = length(takeWhile2(prodDclInfoNumChildEq, prods));
  
  local result::Expr =
    if null(prods)
    then Silver_Expr { error($Expr{stringConst(terminal(String_t, "\"no generatable productions for nonterminal " ++ nt ++ "\""), location=loc)}) }
    else Silver_Expr {
      let pval::Float =
        if depth <= 0
        then genRand() * $Expr{floatConst(terminal(Float_t, toString(toFloat(num_lowest_arity) / toFloat(length(prods)))), location=loc)}
        else genRand()
      in $Expr{generateExprChain(loc, env, specEnv, nt, 0, prods, length(prods))}
      end
    };
  
  return
    Silver_ProductionStmt {
      local $name{"gen_" ++ substitute(":", "_", nt)}::($TypeExpr{nominalTypeExpr(qName(loc, nt).qNameType, location=loc)} ::= Integer) =
        \ depth::Integer -> $Expr{result};
    };
}

{-

We got the id 'Expr' incoming.

We should look up 'Expr' and get a list of productions, from that we get the probabilities.

We then map genForType over the list of productions and generate a big if-then-else tree based on the genRand()

-}

function generateExprChain
Expr ::= loc::Location env::Decorated Env  specEnv::Decorated Env  nt::String index::Integer  lst::[ValueDclInfo]  total::Integer
{
  local prod::ValueDclInfo = head(lst);
  local prodType::Type = prod.typeScheme.typerep;
  local genProd::Expr =
    mkFullFunctionInvocation(loc, Silver_Expr { $name{prod.fullName} },
      map(genForType(loc, env, specEnv, Silver_Expr { depth - 1 }, _), prodType.inputTypes),
      zipWith(pair,
        map(fst, prodType.namedTypes),
        map(genForType(loc, env, specEnv, Silver_Expr { depth - 1 }, _), map(snd, prodType.namedTypes))));

  return if null(tail(lst)) then genProd
  else Silver_Expr {
    if pval < $Expr{floatConst(terminal(Float_t, toString(toFloat(index+ 1) / toFloat(total))), location=loc)}
    then $Expr{genProd}
    else $Expr{generateExprChain(loc, env, specEnv, nt, index + 1, tail(lst), total)}
  };
}
