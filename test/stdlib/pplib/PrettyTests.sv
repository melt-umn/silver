
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

-- basic test of cat and line breaks
global doc1 :: Document =
  group(cat(cat(text("int var1"), line()), text("int var2")));

equalityTest ( show(80, doc1), "int var1 int var2", String, core_tests ) ;
equalityTest ( show(1, doc1), "int var1\nint var2", String, core_tests ) ;

-- less basic test of concat and linebreaks
global doc2 :: Document =
  group(concat([text("int var1"), line(), text("int var2"), line(), text("int var3")]));

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

-- test of individually grouped linebreaks
global doc3 :: Document =
  concat([text("int var1"), group(line()), text("int var2"), group(line()), text("int var3")]);

equalityTest ( show(80, doc3), "int var1 int var2 int var3", String, core_tests ) ;
equalityTest ( show(1, doc3), "int var1\nint var2\nint var3", String, core_tests ) ;
--equalityTest ( show(20, doc3), "int var1 int var2\nint var3", String, core_tests ) ; -- Apparently not?
equalityTest ( show(10, doc3), "int var1 int var2\nint var3", String, core_tests ) ;

-- testing nest
global doc4 :: Document =
  cat(cat(text("{"), nest(3, group(
       concat([text("poiu"), line(), text("asdf"), line(), text("lkjh"), line()])
  ))), text("}"));

equalityTest ( show(20, doc4), "{poiu asdf lkjh }", String, core_tests ) ;
equalityTest ( show(10, doc4), "{poiu\n   asdf\n   lkjh\n   }", String, core_tests ) ;

-- TODO: This is an example of how to do formatting for argument lists to functions. It should be moved into the standard library.
function args
Document ::= d1::Document ds::[Document] dm::Document d2::Document
{
  return cat(cat(d1, box(concat(intersperse(cat(dm, group(line())), ds)))), d2);
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

global doc7 :: Document =
  group(concat([text(" 1234567890"), realLine(), text(" 1234567890"), line(), text("1234567890")]));

equalityTest ( "\n" ++ show(22, doc7), "\n 1234567890\n 1234567890 1234567890", String, core_tests ) ;
equalityTest ( "\n" ++ show(21, doc7), "\n 1234567890\n 1234567890\n1234567890", String, core_tests ) ;

-- TODO: This is an example of how to do formatting for statement lists. It should be moved into the standard library.
function dgroup
Document ::= d1::Document n::Integer ds::[Document] d2::Document
{
  -- care: the first line should be INSIDE the nest,
  -- the second line should be OUTSIDE the next
  -- both should be in the same group.
  return cat(cat(d1, group(cat(nest(n, cat(line(),foldr_p(cat, notext(), intersperse(line(), ds)))), line()))), d2);
}

global doc8 :: Document =
  cat(text("int main() "), dgroup(text("{"), 3, [text("stm1;"),text("stm2;"),text("stm3;"),text("stm4;"),text("stm5;")], text("}")));

equalityTest ( "\n" ++ show(20, doc8), "\nint main() {\n   stm1;\n   stm2;\n   stm3;\n   stm4;\n   stm5;\n}", String, core_tests ) ;
equalityTest ( "\n" ++ show(80, doc8), "\nint main() { stm1; stm2; stm3; stm4; stm5; }", String, core_tests ) ;

global doc9 :: Document =
  cat(text("int main() "), dgroup(text("{"), 3, [text("stm0;"),doc8,text("stm6;"),text("stm7;")], text("}")));

equalityTest ( "\n" ++ show(20, doc9), "\nint main() {\n   stm0;\n   int main() {\n      stm1;\n      stm2;\n      stm3;\n      stm4;\n      stm5;\n   }\n   stm6;\n   stm7;\n}", String, core_tests ) ;
equalityTest ( "\n" ++ show(60, doc9), "\nint main() {\n   stm0;\n   int main() { stm1; stm2; stm3; stm4; stm5; }\n   stm6;\n   stm7;\n}", String, core_tests ) ;

-- TODO: This is an example of how to do formatting for single statement indenting. It should be moved into the standard library.
function ifstmt
Document ::= d1::Document n::Integer d2::Document
{
  -- d1 d2
  -- d1
  --   d2
  return cat(d1, nest(n, cat(group(line()), d2)));
}

global doc10 :: Document =
  cat(text("int main() "), dgroup(text("{"), 3, [text("stm0;"),ifstmt(text("if(a boolean condition)"), 3, text("stm1;")),ifstmt(text("if(another boolean)"), 3, text("stm2;")), text("stm3;")], text("}")));

equalityTest ( "\n" ++ show(60, doc10), "\nint main() {\n   stm0;\n   if(a boolean condition) stm1;\n   if(another boolean) stm2;\n   stm3;\n}", String, core_tests ) ;
equalityTest ( "\n" ++ show(0, doc10), "\nint main() {\n   stm0;\n   if(a boolean condition)\n      stm1;\n   if(another boolean)\n      stm2;\n   stm3;\n}", String, core_tests ) ;

