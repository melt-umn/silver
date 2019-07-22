
function mapASTs
ASTs ::= f::(AST ::= AST) xs::ASTs
{
    return case xs of
        | consAST(x, rest) -> consAST(f(x), mapASTs(f, rest))
        | x -> x
    end;
}

function setAnnoByName
NamedASTs ::= name::String val::AST xs::NamedASTs
{
    return case xs of
        | consNamedAST(namedAST(n, v), rest) ->
            consNamedAST((if n==name then namedAST(n, val) else namedAST(n, v)), setAnnoByName(name, val, rest))
        | x -> x
    end;
}

function duplicateAST
AST ::= rule::String h::AST
{
    return case h of
        | nonterminalAST(prodName, children, annos) ->
            nonterminalAST(prodName,
                mapASTs(duplicateAST(rule, _), children),
                setAnnoByName("example_elision:origin",
                    AST {core:just(core:pair(${h}, ${stringAST(rule)}))}, annos))
        | x -> x
    end;
}

function otxAssertReifyOk
a ::= h::Either<String a>
{
    return case h of
        | left(err) -> error("OTX Internal Error: otxAssertReifyOk:\nsilver:extension:otx:childruntime:duplicateAST failed: reflection failure: " ++ err)
        | right(x) -> x
    end;
}


function javaDup
a ::= arg::a rule::b
{
  return error("Not impl");
} foreign {
  "java" : return "(((common.Node)%arg%).duplicate(%rule%))";
}