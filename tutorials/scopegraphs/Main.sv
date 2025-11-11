grammar scopegraphs;

imports silver:compiler:extension:scopegraphs;

--

fun main IO<Integer> ::= args::[String] = pure(0);

--

inherited attribute lex::[Decorated Scope with {lex, var}] occurs on Scope;
inherited attribute var::[Decorated Scope with {lex, var}] occurs on Scope;

inherited attribute s::Decorated Scope with {lex, var};
monoid attribute s_lex::[Decorated Scope with {lex, var}] with [], ++;
monoid attribute s_var::[Decorated Scope with {lex, var}] with [], ++;

--

nonterminal Root;

production root
top::Root ::= child::Child
{

  -- uncommenting this causes a cycle:
  --scope<{lex, var}> fooScope;
  
  ----------------------------------------------------
  -- `scope<{lex, var}> fooScope` should translate to:
  
  production attribute fooScope::Decorated Scope with {lex, var} = 
    decorate mkScope() with {
      lex = fooScope_lex;
      var = fooScope_var;
    };
  production attribute fooScope_lex::[Decorated Scope with {lex, var}] with ++;
  fooScope_lex := []; -- initial val
  production attribute fooScope_var::[Decorated Scope with {lex, var}] with ++;
  fooScope_var := []; -- initial val

  ----------------------------------------------------
  -- `scope<{lex, var}> barScope` should translate to:
  
  production attribute barScope::Decorated Scope with {lex, var} = 
    decorate mkScope() with {
      lex = barScope_lex;
      var = barScope_var;
    };
  production attribute barScope_lex::[Decorated Scope with {lex, var}] with ++;
  barScope_lex := []; -- initial val
  production attribute barScope_var::[Decorated Scope with {lex, var}] with ++;
  barScope_var := []; -- initial val

  ------------------------------------------------------
  -- `fooScope -[ var ]-> barScope` should translate to:

  fooScope_var <- [barScope];

  --------------------------------------------------------
  -- `child.s = fooScope` should produce these statements:

  -- `fooScope` passed to `child` as inh attr `s` - edge tgts are synthesized as `s_lex` and `s_var`
  fooScope_lex <- child.s_lex;
  fooScope_var <- child.s_var;

}


--

nonterminal Child with s, s_lex, s_var;

production child
top::Child ::=
{
  top.s_lex := [];
  top.s_var := [];
}