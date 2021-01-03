grammar silver:reflect;

exports core:reflect;

import core:monad;

-- left(error message) or right(result)
synthesized attribute serialize<a>::Either<String a>;

attribute serialize<String> occurs on AST;

aspect production nonterminalAST
top::AST ::= prodName::String children::ASTs annotations::NamedASTs
{
  top.serialize =
    do (bindEither, returnEither) {
      childrenSerialize::[String] <- children.serialize;
      annotationSerialize::[String] <- annotations.serialize;
      return s"${prodName}(${implode(", ", childrenSerialize ++ annotationSerialize)})";
    };
}

aspect production terminalAST
top::AST ::= terminalName::String lexeme::String location::Location
{
  top.serialize =
    do (bindEither, returnEither) {
      locationSerialize::String <- serialize(new(location));
      return s"terminal(${terminalName}, \"${escapeString(lexeme)}\", ${locationSerialize})";
    }; 
}

aspect production listAST
top::AST ::= vals::ASTs
{
  top.serialize =
    do (bindEither, returnEither) {
      serializeVals::[String] <- vals.serialize;
      return s"[${implode(", ", serializeVals)}]";
    };
}

aspect production stringAST
top::AST ::= s::String
{
  top.serialize = right(s"\"${escapeString(s)}\"");
}

aspect production integerAST
top::AST ::= i::Integer
{
  top.serialize = right(toString(i));
}

aspect production floatAST
top::AST ::= f::Float
{
  top.serialize = right(toString(f));
}

aspect production booleanAST
top::AST ::= b::Boolean
{
  top.serialize = right(toString(b));
}

aspect production anyAST
top::AST ::= x::a
{
  top.serialize =
    case reflectTypeName(x) of
      just(n) -> left(s"Can't serialize anyAST (type ${n})")
    | nothing() -> left("Can't serialize anyAST")
    end;
}

synthesized attribute astsLength::Integer occurs on ASTs, NamedASTs;
attribute serialize<[String]> occurs on ASTs;

aspect production consAST
top::ASTs ::= h::AST t::ASTs
{
  top.astsLength = 1 + t.astsLength;
  top.serialize =
    do (bindEither, returnEither) {
      hSerialize::String <- h.serialize;
      tSerialize::[String] <- t.serialize;
      return hSerialize :: tSerialize;
    };
}

aspect production nilAST
top::ASTs ::=
{
  top.astsLength = 0;
  top.serialize = right([]);
}

attribute serialize<[String]> occurs on NamedASTs;

aspect production consNamedAST
top::NamedASTs ::= h::NamedAST t::NamedASTs
{
  top.astsLength = 1 + t.astsLength;
  top.serialize =
    do (bindEither, returnEither) {
      hSerialize::String <- h.serialize;
      tSerialize::[String] <- t.serialize;
      return hSerialize :: tSerialize;
    };
}

aspect production nilNamedAST
top::NamedASTs ::=
{
  top.astsLength = 0;
  top.serialize = right([]);
}

attribute serialize<String> occurs on NamedAST;

aspect production namedAST
top::NamedAST ::= n::String v::AST
{
  top.serialize =
    do (bindEither, returnEither) {
      vSerialize::String <- v.serialize;
      return s"${n}=${vSerialize}";
    };
}

function appendASTs
ASTs ::= a::ASTs b::ASTs
{
  return
    case a of
    | consAST(h, t) -> consAST(h, appendASTs(t, b))
    | nilAST() -> b
    end;
}
