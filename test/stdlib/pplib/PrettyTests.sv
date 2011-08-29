
import silver:testing;
import lib:extcore;
import lib:lang:pp;
import stdlib;
import silver:util:deque;

-- should always be hello
equalityTest ( show(20, text("hello")), "hello", String, core_tests ) ;
equalityTest ( show(0, text("hello")), "hello", String, core_tests ) ;
-- outer group is always vertical
equalityTest ( show(20, line()), "\n", String, core_tests ) ;
equalityTest ( show(0, line()), "\n", String, core_tests ) ;
-- should be horizontal with space
equalityTest ( show(20, group(line())), " ", String, core_tests ) ;
equalityTest ( show(0, group(line())), "\n", String, core_tests ) ;
-- should be horizontal with space
equalityTest ( show(20, group(group(line()))), " ", String, core_tests ) ;
equalityTest ( show(0, group(group(line()))), "\n", String, core_tests ) ;


global doc1 :: Document =
  group(cat(cat(text("int var1"), line()), text("int var2")));

equalityTest ( show(80, doc1), "int var1 int var2", String, core_tests ) ;
equalityTest ( show(1, doc1), "int var1\nint var2", String, core_tests ) ;


global doc2 :: Document =
  group(cat(cat(cat(cat(text("int var1"), line()), text("int var2")), line()), text("int var3")));

equalityTest ( show(80, doc2), "int var1 int var2 int var3", String, core_tests ) ;
equalityTest ( show(1, doc2), "int var1\nint var2\nint var3", String, core_tests ) ;
equalityTest ( show(20, doc2), "int var1\nint var2\nint var3", String, core_tests ) ;

function debugshow
Decorated Document ::= width::Integer d::Document
{
  d.indent = 0;
  d.width = width;
  
  d.inPosition = 0;
  d.inDq = dqEmpty();
  d.inCHorizontals = false :: false :: d.horizontals;
  d.inRemaining = width;
  
  return d;
}

equalityTest ( debugshow(0, group(line())).horizontals, [false], [Boolean], core_tests ) ;
equalityTest ( debugshow(0, group(line())).outPosition, 1, Integer, core_tests ) ;
equalityTest ( dqIsEmpty(debugshow(0, group(line())).outDq), true, Boolean, core_tests ) ;
equalityTest ( debugshow(0, group(line())).outCHorizontals, [false,false], [Boolean], core_tests ) ;
equalityTest ( debugshow(0, group(line())).outRemaining, 0, Integer, core_tests ) ;

function debugshow2
Decorated Document ::= width::Integer d::Document
{
  d.indent = 0;
  d.width = width;
  
  d.inPosition = 1;
  d.inDq = dqEmpty();
  d.inCHorizontals = [false,false];
  d.inRemaining = width;
  
  return d;
}

equalityTest ( debugshow2(0, group(line())).horizontals, [false], [Boolean], core_tests ) ;
equalityTest ( debugshow2(0, group(line())).outPosition, 2, Integer, core_tests ) ;
equalityTest ( dqIsEmpty(debugshow2(0, group(line())).outDq), true, Boolean, core_tests ) ;
equalityTest ( debugshow2(0, group(line())).outCHorizontals, [false], [Boolean], core_tests ) ;
equalityTest ( debugshow2(0, group(line())).outRemaining, 0, Integer, core_tests ) ;

{- SO crash. here's where to start investigating!
global doc3so :: Document =
  cat(group(line()), group(line()));
  
equalityTest ( show(10, doc3so), "  ", String, core_tests ) ;
equalityTest ( show(1, doc3so), " \n", String, core_tests ) ;
equalityTest ( show(0, doc3so), "\n\n", String, core_tests ) ;



global doc3 :: Document =
  cat(cat(cat(cat(text("int var1"), group(line())), text("int var2")), group(line())), text("int var3"));

equalityTest ( show(80, doc3), "int var1 int var2 int var3", String, core_tests ) ;
equalityTest ( show(1, doc3), "int var1\nint var2\nint var3", String, core_tests ) ;
equalityTest ( show(20, doc3), "int var1 int var2\nint var3", String, core_tests ) ;
-}
