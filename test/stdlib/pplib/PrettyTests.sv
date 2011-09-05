
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

function args
Document ::= d1::Document ds::[Document] dm::Document d2::Document
{
  return cat(cat(d1, box(foldr_p(cat, text(""), intersperse(cat(dm, group(line())), ds)))), d2);
}

global doc5 :: Document =
  cat(text("int decl"), args(text("("), [text("int arg1"), text("int arg2"), text("int arg3"), text("int arg4")], text(","), text(")")));

equalityTest ( "\n" ++ show(0, doc5), "\nint decl(int arg1,\n         int arg2,\n         int arg3,\n         int arg4)", String, core_tests ) ;
equalityTest ( "\n" ++ show(80, doc5), "\nint decl(int arg1, int arg2, int arg3, int arg4)", String, core_tests ) ;
equalityTest ( "\n" ++ show(20, doc5), "\nint decl(int arg1, int arg2,\n         int arg3, int arg4)", String, core_tests ) ;
equalityTest ( "\n" ++ show(30, doc5), "\nint decl(int arg1, int arg2, int arg3,\n         int arg4)", String, core_tests ) ;

function sexp
Document ::= s::String d::[Document]
{
  return cat(cat(text("(" ++ s ++ " "), args(notext(), d, notext(), notext())), text(")"));
}

global doc6 :: Document = 
  sexp("cons", [sexp("list", [text("foo"), text("bar"), text("baz")]),
                sexp("cons", [sexp("hello", [text("world")]),
                              sexp("more", [text("qwerty")])])]);

equalityTest ( "\n" ++ show(0, doc6), "\n(cons (list foo\n            bar\n            baz)\n      (cons (hello world)\n            (more qwerty)))", String, core_tests ) ;
equalityTest ( "\n" ++ show(20, doc6), "\n(cons (list foo bar baz)\n      (cons (hello world)\n            (more qwerty)))", String, core_tests ) ;
equalityTest ( "\n" ++ show(25, doc6), "\n(cons (list foo bar baz) (cons (hello world)\n                               (more qwerty)))", String, core_tests ) ;
equalityTest ( "\n" ++ show(80, doc6), "\n(cons (list foo bar baz) (cons (hello world) (more qwerty)))", String, core_tests ) ;



