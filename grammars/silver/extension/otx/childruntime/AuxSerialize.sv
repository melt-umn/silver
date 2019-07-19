import core:monad;

function identityHashCode
Integer ::= nt::a
{
  return error("Not impl");
} foreign {
  "java" : return "(System.identityHashCode(%nt%))";
}

function idstr
String ::= nt::a
{
	return toString(identityHashCode(nt));
}

synthesized attribute auxSerialize<a>::Either<String a>;

attribute auxSerialize<String> occurs on AST;

aspect production nonterminalAST
top::AST ::= prodName::String children::ASTs annotations::NamedASTs
{
  top.auxSerialize =
    do (bindEither, returnEither) {
      childrenauxSerialize::[String] <- children.auxSerialize;
      annotationauxSerialize::[String] <- annotations.auxSerialize;
      return s"${idstr(top)}/${prodName}(${implode(", ", childrenauxSerialize ++ annotationauxSerialize)})";
    };
}

aspect production terminalAST
top::AST ::= terminalName::String lexeme::String location::Location
{
  top.auxSerialize =
    do (bindEither, returnEither) {
      locationauxSerialize::String <- auxSerialize_internal(new(location));
      return s"${idstr(top)}/terminal(${terminalName}, \"${escapeString(lexeme)}\", ${locationauxSerialize})";
    }; 
}

aspect production listAST
top::AST ::= vals::ASTs
{
  top.auxSerialize =
    do (bindEither, returnEither) {
      auxSerializeVals::[String] <- vals.auxSerialize;
      return s"${idstr(top)}/[${implode(", ", auxSerializeVals)}]";
    };
}

aspect production stringAST
top::AST ::= s::String
{
  top.auxSerialize = right(idstr(top)++"/"++s"\"${escapeString(s)}\"");
}

aspect production integerAST
top::AST ::= i::Integer
{
  top.auxSerialize = right(idstr(top)++"/"++toString(i));
}

aspect production floatAST
top::AST ::= f::Float
{
  top.auxSerialize = right(idstr(top)++"/"++toString(f));
}

aspect production booleanAST
top::AST ::= b::Boolean
{
  top.auxSerialize = right(idstr(top)++"/"++toString(b));
}

aspect production anyAST
top::AST ::= x::a
{
  top.auxSerialize =
    case reflectTypeName(x) of
      just(n) -> left(s"Can't auxSerialize anyAST (type ${n})")
    | nothing() -> left("Can't auxSerialize anyAST")
    end;
}

attribute auxSerialize<[String]> occurs on ASTs;

aspect production consAST
top::ASTs ::= h::AST t::ASTs
{
  top.auxSerialize =
    do (bindEither, returnEither) {
      hauxSerialize::String <- h.auxSerialize;
      tauxSerialize::[String] <- t.auxSerialize;
      return hauxSerialize :: tauxSerialize;
    };
}

aspect production nilAST
top::ASTs ::=
{
  top.auxSerialize = right([]);
}

attribute auxSerialize<[String]> occurs on NamedASTs;

aspect production consNamedAST
top::NamedASTs ::= h::NamedAST t::NamedASTs
{
  top.auxSerialize =
    do (bindEither, returnEither) {
      hauxSerialize::String <- h.auxSerialize;
      tauxSerialize::[String] <- t.auxSerialize;
      return hauxSerialize :: tauxSerialize;
    };
}

aspect production nilNamedAST
top::NamedASTs ::=
{
  top.auxSerialize = right([]);
}

attribute auxSerialize<String> occurs on NamedAST;

aspect production namedAST
top::NamedAST ::= n::String v::AST
{
  top.auxSerialize =
    do (bindEither, returnEither) {
      vauxSerialize::String <- v.auxSerialize;
      return s"${n}=${vauxSerialize}";
    };
}

function auxSerialize_internal
Either<String String> ::= x::a
{
  return reflect(x).auxSerialize;
}


function auxSerialize
String ::= nt::AST
{
	return case nt.auxSerialize of
		| right(x) -> x
		| left(x) -> error("auxSerialize failed: "++x)
	end;
}