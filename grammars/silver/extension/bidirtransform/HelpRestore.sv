grammar silver:extension:bidirtransform;

abstract production restoreExpr
top::Expr ::= toFill::Expr exps::[Expr] names::[String] ns::Decorated NamedSignature
{
    -- access .restored_$cncType on all elements of the filled expr
    -- todo: error check, this right now assumes the user
    -- only writes recursive restoration rules on things that make sense,
    -- i.e. applications where a type can be inferred for what the arguments
    -- have to be converted into. If this is not true, this breaks right now,
    -- and nothing prevents the user from writing mul(x,y) ~~~> y
    forwards to case fillExpr(toFill, exps, names, location=toFill.location) of application(e, x, appexps, y, annexps, z) ->
        application(e, x, restoreAppExprs(appexps, reverse(map((.typeName), ns.inputTypes)), location=toFill.location), y, annexps, z, location=toFill.location)
    end;
}

abstract production restoreAppExprs
top::AppExprs ::= toFill::AppExprs inputTypes::[String]
{
    forwards to if null(inputTypes) then toFill else 
        case toFill of 
            | snocAppExprs(es,_,e) -> snocAppExprs(restoreAppExprs(es, tail(inputTypes), location=toFill.location),
            ',',
            restoreAppExpr(e, head(inputTypes), location=toFill.location), location=toFill.location)
            | oneAppExprs(e) -> oneAppExprs(restoreAppExpr(e, head(inputTypes), location=toFill.location), location=toFill.location)
            --| _ -> toFill
        end;
}

abstract production restoreAppExpr
top::AppExpr ::= toFill::AppExpr inType::String
{
    forwards to case toFill of presentAppExpr(e) -> presentAppExpr( 
        case e.typerep of 
            | terminalType(_) -> e
            | _ -> qAccess(restoreNm(unFull(inType)), e, location=toFill.location)
        end, location=toFill.location)
    end;
}
