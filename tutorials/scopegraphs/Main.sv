grammar scopegraphs;

imports silver:compiler:extension:scopegraphs2;

--

fun main IO<Integer> ::= args::[String] = pure(0);

--

scope graph edges { lex, var, imp, mod }; -- translates to:
{-
type Scope = Decorated SGScope with {lex, var, imp, mod};
inherited attribute lex::[Scope];
  attribute lex occurs on SGScope;
inherited attribute var::[Scope];
  attribute var occurs on SGScope;
inherited attribute imp::[Scope];
  attribute imp occurs on SGScope;
inherited attribute mod::[Scope];
  attribute mod occurs on SGScope;
-}


--

nonterminal Root;

production root
top::Root ::= child::Child
{

  mkscope s2; -- translates to:
  {-
  production attribute s2::Scope = decorate scope() with {
    lex = local_s2_lex;  var = local_s2_var;
    imp = local_s2_imp;  mod = local_s2_mod;
  };
  production attribute local_s2_lex::[Scope] with ++;
    local_s2_lex := [];
  production attribute local_s2_var::[Scope] with ++;
    local_s2_var := [];
  production attribute local_s2_imp::[Scope] with ++;
    local_s2_imp := [];
  production attribute local_s2_mod::[Scope] with ++;
    local_s2_mod := [];
  -}

  child.s = s2;

}


--

inherited attribute s::Scope;

nonterminal Child with s;

production child
top::Child ::=
{

}