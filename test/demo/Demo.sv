grammar demo;

parser pfoo1::Root {
  core;
  demo;
}

parser pfoo2::Root {
  demo;
}

lexer class TERMINAL;

terminal Bar_t /.../ lexer classes {TERMINAL};
terminal Foo_t /.../ lexer classes {TERMINAL};

disambiguate Bar_t, Foo_t {
  pluck if lexeme == "123" then Bar_t else Foo_t;
}

ignore terminal I_t /bar/;

nonterminal Root;
nonterminal Foo;
nonterminal Bar;

inherited attribute c :: String with ++;
attribute c occurs on Bar;

inherited attribute auto :: String;
attribute auto occurs on Root;
autocopy Root.auto on root2;
autocopy Root.auto on root3;

synthesized attribute pp :: String;
attribute pp occurs on Root;
attribute pp occurs on Foo;
attribute pp occurs on Bar;



function main
IO ::= args::String io::IO{

  local attribute root :: Root;
  root = pfoo1(args);
 
  return case root of	
	  root1() -> print("root1\n" ++ root.pp ++ "\n", io)
	| root2(a,b) -> print("root2\n" ++ decorate root with {auto = "auto";}.pp ++ "\n", io)
	| root3(a,b) -> print("root3\n" ++ decorate root with {auto = "auto";}.pp ++ "\n", io)
  	
  end;
}

concrete production root1
top::Root ::= {
  top.pp = "";
}


abstract production root_str
top::Root ::= s::String{
  top.pp = s;
}

concrete production root2
top::Root ::= f::Foo r::Root{
  top.pp = "\t" ++ f.pp ++ "\n" ++ r.pp;
}

concrete production root3
top::Root ::= b::Bar r::Root{
  top.pp = "\t" ++ b.pp ++ "\n" ++ r.pp;
}


aspect production root3
top::Root ::= b::Bar r::Root{
  b.c <- " Add " ++ top.auto;
 }

aspect production root3
top::Root ::= b::Bar r::Root{
  b.c := " Base " ++ top.auto;
 }


concrete production foo
top::Foo ::= f::Foo_t{
  top.pp = "Foo [line:" ++ toString(f.line) ++ ", column:" ++ toString(f.column) ++ ", lexeme:" ++ f.lexeme ++ "]";
}

concrete production bar
top::Bar ::= b::Bar_t{
  top.pp = "Bar [line" ++ toString(b.line) ++ ", column:" ++ toString(b.column) ++ ", lexeme:" ++ b.lexeme ++ "]" ++ top.c;
}