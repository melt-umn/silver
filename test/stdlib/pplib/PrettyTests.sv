
import silver:testing;
import lib:extcore;
import silver:langutil:pp;
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


-- This is a canary for a bug with an append optimization where it was
-- being insufficiently lazy!! Fixed in revision 1747
global doc3so :: Document =
  cat(group(line()), group(line()));
  
equalityTest ( show(10, doc3so), "  ", String, core_tests ) ;
equalityTest ( show(1, doc3so), " \n", String, core_tests ) ;
equalityTest ( show(0, doc3so), "\n\n", String, core_tests ) ;


global doc3 :: Document =
  cat(cat(cat(cat(text("int var1"), group(line())), text("int var2")), group(line())), text("int var3"));

equalityTest ( show(80, doc3), "int var1 int var2 int var3", String, core_tests ) ;
equalityTest ( show(1, doc3), "int var1\nint var2\nint var3", String, core_tests ) ;
--equalityTest ( show(20, doc3), "int var1 int var2\nint var3", String, core_tests ) ; -- Apparently not?
equalityTest ( show(10, doc3), "int var1 int var2\nint var3", String, core_tests ) ;


global doc4 :: Document =
  cat(cat(text("{"), nest(3, group(
       cat(cat(cat(cat(cat(text("poiu"), line()), text("asdf")), line()), text("lkjh")), line())
  ))), text("}"));

equalityTest ( show(20, doc4), "{poiu asdf lkjh }", String, core_tests ) ;
equalityTest ( show(10, doc4), "{poiu\n   asdf\n   lkjh\n   }", String, core_tests ) ;



