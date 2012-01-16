
-- local attributes in parser action blocks
-- Integers, addition, string/list append
-- Assign to & use lexeme, filename, line, column & children in productions
-- 

parser attribute Acount :: Integer action {
  -- test local declarations
  local attribute a_local :: Integer;
  -- assignment to local
  a_local = 0;
  -- reference to local
  Acount = a_local;
};

terminal A 'a' action {
  -- assignment & reference to parser attribute
  Acount = Acount + 1;
  -- assignment to filename, reference to filename, line, column
  filename = filename ++ ":" ++ toString(line) ++ "." ++ toString(column);
  -- assignment to lexeme, reference to lexeme
  -- lexeme = lexeme ++ toString(Acount);  -- FEATURE DOES NOT EXIST (YET?)
};
terminal B 'b' action {
  -- assignment to line
  line = column;
  -- assignment to column
  column = Acount;
};

nonterminal AOrB with semResult;
synthesized attribute semResult :: String;

concrete production aorb_a
top::AOrB ::= 'a'
{
  top.semResult = "(" ++ implode(",", [$1.lexeme, $1.filename, toString($1.line), toString($1.column)]) ++ ")";
}
concrete production aorb_b
top::AOrB ::= 'b'
{
  top.semResult = "(" ++ implode(",", [$1.lexeme, $1.filename, toString($1.line), toString($1.column)]) ++ ")";
}

nonterminal AOrBs with semResult;

concrete production aorb_one
top::AOrBs ::= h::AOrB
{
  top.semResult = h.semResult;
}
concrete production aorb_cons
top::AOrBs ::= h::AOrB t::AOrBs
{
  top.semResult = h.semResult ++ t.semResult;
}

parser saParse :: AOrBs {
  copper_features;
}

equalityTest ( saParse("abab", "FILENAME").parseSuccess, true, Boolean, copper_tests ) ;
equalityTest ( saParse("abab", "FILENAME").parseTree.semResult, -- this is a bit silly:
"(a,FILENAME,1,0)(b,FILENAME:1.1,1,1)(a,FILENAME:1.1,2,1)(b,FILENAME:1.1:2.2,2,2)", String, copper_tests ) ;

-- TODO: in fact, there are several ways in which we could argue that result is broken...
-- - It claims actions take effect AFTER that terminal, which is counter-intuitive to a
--   filename= assignment in that terminal's action
-- - Assignment to some things is permanent.  Perhaps this is right.
-- TODO: access children in some way.


