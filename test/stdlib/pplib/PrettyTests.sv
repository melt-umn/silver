
import silver:testing;
import lib:extcore;
import lib:lang:pp;
import stdlib;

global doc1 :: Document =
  group(cat(cat(text("int var1"), line()), text("int var2")));


equalityTest ( show(80, doc1), "int var1 int var2", String, core_tests ) ;
equalityTest ( show(1, doc1), "int var1\nint var2", String, core_tests ) ;


global doc2 :: Document =
  group(cat(cat(cat(cat(text("int var1"), line()), text("int var2")), line()), text("int var3")));

equalityTest ( show(80, doc2), "int var1 int var2 int var3", String, core_tests ) ;
equalityTest ( show(1, doc2), "int var1\nint var2\nint var3", String, core_tests ) ;
equalityTest ( show(20, doc2), "int var1\nint var2\nint var3", String, core_tests ) ;

{- SO crash. here's where to start investigating!
global doc3so :: Document =
  cat(group(line()), group(line()));

equalityTest ( show(10, doc3so), "  ", String, core_tests ) ;
equalityTest ( show(0, doc3so), "\n\n", String, core_tests ) ;

global doc3 :: Document =
  cat(cat(cat(cat(text("int var1"), group(line())), text("int var2")), group(line())), text("int var3"));

equalityTest ( show(80, doc3), "int var1 int var2 int var3", String, core_tests ) ;
equalityTest ( show(1, doc3), "int var1\nint var2\nint var3", String, core_tests ) ;
equalityTest ( show(20, doc3), "int var1 int var2\nint var3", String, core_tests ) ;
-}
