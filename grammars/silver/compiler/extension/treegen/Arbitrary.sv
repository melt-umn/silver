grammar silver:compiler:extension:treegen;

imports silver:compiler:definition:core;
imports silver:compiler:definition:env;
imports silver:compiler:definition:concrete_syntax;
imports silver:compiler:definition:concrete_syntax:ast;
imports silver:compiler:definition:type;
imports silver:compiler:definition:type:syntax;
imports silver:compiler:definition:flow:env;
imports silver:compiler:extension:convenience;
imports silver:compiler:modification:list;
imports silver:compiler:extension:tuple;
imports silver:compiler:modification:lambda_fn;
imports silver:compiler:modification:let_fix;
imports silver:compiler:metatranslation;

import silver:util:treemap as tm;

terminal Generator_t 'generator' lexer classes {KEYWORD};

concrete production generatorDcl
top::AGDcl ::= 'generator' n::Name '::' t::TypeExpr '{' grammars::GeneratorComponents '}'
{
  top.unparse = s"generator ${n.unparse} :: ${t.unparse} { ${grammars.unparse} }";

  -- Compute the defs exported by the specified grammars
  local med::ModuleExportedDefs =
    moduleExportedDefs(
      top.compiledGrammars, top.grammarDependencies,
      grammars.moduleNames, []);
  production specEnv::Env = newScopeEnv(med.defs, emptyEnv());
  
  -- Override defs to suppress production attributes from flowing up as a paDef,
  -- to avoid a circularity as what production attributes are generated depends
  -- on the environment. Practically this means that we can't aspect a generator
  -- function and refer to its production attributes, which is probably good anyway.
  top.defs :=
    case forward of
    | functionDcl(_, _, ns, _) -> [funDef(top.grammarName, n.nameLoc, ns.namedSignature)]
    | _ -> error("forward should be a function")
    end;

  -- Build the syntax AST from the specified grammars to extract lexical precedence info 
  production syntax::SyntaxRoot = cstRoot(
    n.name, t.typerep.typeName, foldr(consSyntax, nilSyntax(), med.syntaxAst),
    nothing(), [], [], sourceGrammar=top.grammarName, location=n.nameLoc);

  production attribute implicitImports::[String] with ++;
  implicitImports := [];
  top.moduleNames <- implicitImports;
  local extraMED::ModuleExportedDefs =
    moduleExportedDefs(
      top.compiledGrammars, top.grammarDependencies,
      "silver:core" :: implicitImports, []);

  -- Generator components must be imported for the translation here,
  -- but an AGDcl can't (currently) forward to an import ModuleStmt -
  -- resorting to a slight interfering workaround for now.
  propagate moduleNames;
  forward.env = occursEnv(extraMED.occursDefs, newScopeEnv(extraMED.defs, specEnv));

  -- Implicitly import the random library
  implicitImports <- ["silver:util:random"];

  -- We also depend on the silver:regex library
  implicitImports <- ["silver:regex"];

  forwards to Silver_AGDcl {
    function $Name{@n}
    silver:core:RandomGen<$TypeExpr{@t}> ::= minDepth::Integer maxDepth::Integer
    {
      $ProductionStmt{
        foldr(
          productionStmtAppend(_, _),
          emptyProductionStmt(),
          map(genNtLocalDecl(forward.env, specEnv, _), map((.fullName), syntax.allNonterminals)) ++
          map(genTermLocalDecl(forward.env, specEnv, syntax.dominatingTerminals, _), map((.fullName), syntax.allTerminals)))}
      return $Expr{genForType(forward.env, specEnv, Silver_Expr { 0 }, t.typerep)};
    }
  };

  -- Uncomment for debugging
  --top.errors := unsafeTracePrint(forward.errors, forward.unparse);
}

tracked nonterminal GeneratorComponents with config, grammarName, unparse, errors, moduleNames, compiledGrammars, grammarDependencies;
tracked nonterminal GeneratorComponent with config, grammarName, unparse, errors, moduleNames, compiledGrammars, grammarDependencies;

propagate config, grammarName, compiledGrammars, grammarDependencies, env, errors, moduleNames on GeneratorComponents, GeneratorComponent;

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
Expr ::= env::Env  specEnv::Env  depth::Expr  t::Type
{
  return
    case t of
    -- Monomorphic nonterminals that don't have an explicit Arbitrary instance,
    -- call the appropriate local generator function.
    | nonterminalType(ntName, [], _, _)
      when (getTypeDcl(ntName, specEnv), getInstanceDcl("silver:util:random:Arbitrary", new(t), env))
      matches (dcl :: _, []) -> Silver_Expr { $Name{name("gen_" ++ substitute(":", "_", ntName))}($Expr{new(depth)}) }
    
    | terminalType(tName)
      when (getTypeDcl(tName, specEnv), getInstanceDcl("silver:util:random:Arbitrary", new(t), env))
      matches (dcl :: _, []) -> Silver_Expr { $Name{name("gen_" ++ substitute(":", "_", tName))}($Expr{new(depth)}) }

    -- Lists are handled specially here, to allow recusively generating for
    -- e.g. lists of nonterminals in a production RHS.
    | appType(listCtrType(), elemT) ->
      Silver_Expr {
        silver:core:bind(silver:util:random:randomRange(0, $Expr{new(depth)}), \ len::Integer ->
          silver:core:traverseA(
            \ depth::Integer -> $Expr{genForType(env, specEnv, Silver_Expr { depth - 1 }, new(elemT))},
            silver:core:take(len, silver:core:reverse(silver:core:range(0, $Expr{new(depth)})))))
      }

    -- Primitives and polymorphic nonterminals (e.g. Pair for tuples) are
    -- handled by the Arbitrary type class.
    | _ -> Silver_Expr { $Name{name("silver:util:random:genArb")}($Expr{new(depth)}) }
    end; 
}

-- Determine whether we can generate an arbitrary value for some type.
function isTypeGeneratable
Boolean ::= env::Env  specEnv::Env  t::Type
{
  return
    case t of
    | nonterminalType(ntName, [], _, _) when getTypeDcl(ntName, specEnv) matches _ :: _ -> true
    | terminalType(ntName) when getTypeDcl(ntName, specEnv) matches _ :: _ -> true
    | appType(listCtrType(), elemT) -> isTypeGeneratable(env, specEnv, new(elemT))
    | _ -> !null(getInstanceDcl("silver:util:random:Arbitrary", new(t), env))
    end;
}

-- Determine whether we can generate an arbitrary value for some production -
-- i.e. that it is monomorphic and all the RHS types are generatable.
function isProdGeneratable
Boolean ::= env::Env  specEnv::Env  p::ValueDclInfo
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
fun takeWhile2 [a] ::= f::(Boolean ::= a a)  l::[a] =
  if null(l) then []
  else if null(tail(l)) then l
  else if f(head(l), head(tail(l))) then head(l) :: takeWhile2(f, tail(l))
  else [head(l)];

-- local genExpr::(RandomGen<Expr> ::= Integer) = \ depth::Integer -> ...;
function genNtLocalDecl
ProductionStmt ::= env::Env  specEnv::Env  nt::String
{
  -- All productions that are generatable for nt, sorted by arity
  local prods :: [ValueDclInfo] = 
    filter(isProdGeneratable(env, specEnv, _),
      sortBy(prodDclInfoNumChildLte,
        getKnownProds(nt, specEnv)));
  
  local num_lowest_arity :: Integer = length(takeWhile2(prodDclInfoNumChildEq, prods));
  local num_nonzero_arity :: Integer = length(filter(prodDclInfoNumChildNonzero, prods));
  
  nondecorated local result::Expr =
    if null(prods)
    -- TODO: This could be a compile-time error, in theory
    then Silver_Expr { error($Expr{stringConst(terminal(String_t, "\"no generatable productions for nonterminal " ++ nt ++ "\""))}) }
    else Silver_Expr {
      silver:core:bind(
        if depth >= maxDepth
        -- Exclude all but the lowest-arity productions
        then randomRange(0, $Expr{intConst(terminal(Int_t, toString(num_lowest_arity - 1)))})
        else if depth < minDepth
        -- Exclude all arity-0 productions
        then randomRange(
          $Expr{intConst(terminal(Int_t, toString(if num_nonzero_arity == length(prods) then 0 else num_nonzero_arity)))},
          $Expr{intConst(terminal(Int_t, toString(length(prods) - 1)))})
        -- All productions
        else randomRange(0, $Expr{intConst(terminal(Int_t, toString(length(prods) - 1)))}),
        \ i::Integer -> $Expr{generateExprChain(env, specEnv, nt, 0, prods)})
    };
  
  return
    Silver_ProductionStmt {
      local $name{"gen_" ++ substitute(":", "_", nt)}::(silver:core:RandomGen<$TypeExpr{nominalTypeExpr(qName(nt).qNameType)}> ::= Integer) =
        \ depth::Integer -> $Expr{result};
    };
}

function genTermLocalDecl
ProductionStmt ::= env::Env  specEnv::Env  dominatingTerminals::EnvTree<Decorated SyntaxDcl> t::String
{
  nondecorated local te::TypeExpr = nominalTypeExpr(qName(t).qNameType);

  -- Filter out cantidate lexemes by checking if they match dominating terminal regexes.
  -- TODO: This a approach is somewhat somewhat inefficient, and fails with
  -- infinite recursion if a terminal is totally dominated by another.
  -- For example:
  -- terminal A 'x';
  -- terminal B 'x' dominates A;
  -- nonterminal C;
  -- concrete production d
  -- C ::= A  {}
  -- However this almost certainly points to a major problem with the grammar,
  -- since any productions that mention A can never match any strings.
  -- Is there a better approach involving regex subtraction as a primitive?
  -- Also note that this does not consider disambiguation functions, and may
  -- generate trees that could have arisen from different ambigous parses of the
  -- same source.
  nondecorated local termDominated::Expr =
    foldr(
      or(_, '||', _),
      falseConst('false'),
      map(
        \ term::Decorated SyntaxDcl -> Silver_Expr {
          silver:regex:matches($Expr{translate(reflect(term.terminalRegex))}, term.lexeme)
        },
        searchEnvTree(t, dominatingTerminals)));
  return
    Silver_ProductionStmt {
      local $name{"gen_" ++ substitute(":", "_", t)}::(silver:core:RandomGen<$TypeExpr{te}> ::= Integer) =
        \ depth::Integer ->
          silver:core:bind(
            silver:core:bind(silver:util:random:genArb(depth), genArbTerminal($TypeExpr{te}, _)),
            \ term::$TypeExpr{te} ->
              if $Expr{termDominated} then $name{"gen_" ++ substitute(":", "_", t)}(depth) else pure(term));
    };
}

{-

We got the id 'Expr' incoming.

We should look up 'Expr' and get a list of productions, from that we get the indices.

We then map genForType over the list of productions and generate a big if-then-else tree based on the randomRange()

Note that this expects lst to be non-empty!

-}

function generateExprChain
Expr ::= env::Env  specEnv::Env  nt::String index::Integer  lst::[ValueDclInfo]
{
  local prod::ValueDclInfo = head(lst);
  local prodType::Type = prod.typeScheme.typerep;
  local args::[(String, Type)] =
    zip(map(\ i::Integer -> "a" ++ toString(i), range(0, length(prodType.inputTypes))), prodType.inputTypes) ++
    prodType.namedTypes;
  local argGenExprs::[Expr] =
    map(genForType(env, specEnv, Silver_Expr { depth + 1 }, _), map(snd, args));
  nondecorated local genRes::Expr =
    mkFullFunctionInvocation(
      Silver_Expr { $name{prod.fullName} },
      map(\ i::Integer -> Silver_Expr { $name{"a" ++ toString(i)} }, range(0, length(prodType.inputTypes))),
      map(\ a::String -> (a, Silver_Expr { $name{a} }), map(fst, prodType.namedTypes)));
  nondecorated local lambdaChain::Expr =
    foldr(
      \ arg::(String, Type) res::Expr ->
        Silver_Expr { \ $name{arg.1}::$TypeExpr{typerepTypeExpr(arg.2)} -> $Expr{res} },
      genRes, args);
  nondecorated local genProd::Expr =
    if null(argGenExprs)
    then Silver_Expr { silver:core:pure($Expr{genRes}) }
    else foldl(
      \ e1::Expr e2::Expr -> Silver_Expr { silver:core:ap($Expr{e1}, $Expr{e2}) },
      Silver_Expr { silver:core:map($Expr{lambdaChain}, $Expr{head(argGenExprs)}) },
      tail(argGenExprs));

  return if null(tail(lst)) then genProd
  else Silver_Expr {
    if i == $Expr{intConst(terminal(Int_t, toString(index)))}
    then $Expr{genProd}
    else $Expr{generateExprChain(env, specEnv, nt, index + 1, tail(lst))}
  };
}
