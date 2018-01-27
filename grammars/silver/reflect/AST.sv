grammar silver:reflect;

exports core:reflect;

imports silver:langutil;
imports silver:langutil:pp;

attribute pp occurs on AST;
attribute unparse occurs on AST;

aspect production nonterminalAST
top::AST ::= prodName::String children::ASTs annotations::NamedASTs
{
  top.pp = cat(text(prodName), parens(ppImplode(pp", ", children.pps ++ annotations.pps)));
  top.unparse = s"${prodName}(${implode(", ", children.unparses ++ annotations.unparses)})";
}

aspect production listAST
top::AST ::= vals::ASTs
{
  top.pp = brackets(ppImplode(pp", ", vals.pps));
  top.unparse = s"[${implode(", ", vals.unparses)}]";
}

aspect production stringAST
top::AST ::= s::String
{
  -- TODO: Handle escaping properly
  top.pp = pp"\"${text(s)}\"";
  top.unparse = s"\"${s}\"";
}

aspect production integerAST
top::AST ::= i::Integer
{
  top.pp = text(toString(i));
  top.unparse = toString(i);
}

aspect production floatAST
top::AST ::= f::Float
{
  top.pp = text(toString(f));
  top.unparse = toString(f);
}

aspect production booleanAST
top::AST ::= b::Boolean
{
  top.pp = text(toString(b));
  top.unparse = toString(b);
}

aspect production anyAST
top::AST ::= x::a
{
  top.pp =
    case reflectTypeName(x) of
      just(n) -> pp"<OBJECT :: ${text(n)}>"
    | nothing() -> pp"<OBJECT>"
    end;
  top.unparse = error("Can't unparse anyAST");
}

synthesized attribute unparses::[String];

attribute pps occurs on ASTs;
attribute unparses occurs on ASTs;

aspect production consAST
top::ASTs ::= h::AST t::ASTs
{
  top.pps = h.pp :: t.pps;
  top.unparses = h.unparse :: t.unparses;
}

aspect production nilAST
top::ASTs ::=
{
  top.pps = [];
  top.unparses = [];
}

attribute pps occurs on NamedASTs;
attribute unparses occurs on NamedASTs;

aspect production consNamedAST
top::NamedASTs ::= h::NamedAST t::NamedASTs
{
  top.pps = h.pp :: t.pps;
  top.unparses = h.unparse :: t.unparses;
}

aspect production nilNamedAST
top::NamedASTs ::=
{
  top.pps = [];
  top.unparses = [];
}

attribute pp occurs on NamedAST;
attribute unparse occurs on NamedAST;

aspect production namedAST
top::NamedAST ::= n::String v::AST
{
  top.pp = pp"${text(n)}=${v.pp}";
  top.unparse = s"${n}=${v.unparse}";
}