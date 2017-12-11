grammar silver:extension:bidirtransform;

abstract production injectAnnos
top::Expr ::= toFill::Expr toInject::AnnoAppExprs needAnnos::[String]
{
    -- scan through e, find all applications,
    -- if they need these annotations, give them the annotations
    forwards to case toFill of        
        | applicationEmpty(e, _, _) -> 
            injectApplication(e, emptyAppExprs(location=toFill.location), emptyAnnoAppExprs(location=toFill.location),
                toInject, needAnnos, location=toFill.location)
        | applicationExpr(e, _, appexps, _) -> 
            injectApplication(e, appexps, emptyAnnoAppExprs(location=toFill.location), 
                toInject, needAnnos, location=toFill.location)
        | application(e, _, appexps, _, annexps, _) ->
            injectApplication(e, appexps, annexps, toInject, needAnnos, location=toFill.location)
        --
        --
        | baseExpr(qn) -> toFill
        | childReference(qn) -> toFill
        | lhsReference(qn) -> toFill
        | localReference(qn) -> toFill
        | forwardReference(qn) -> toFill
        | productionReference(qn) -> toFill
        | functionReference(qn) -> toFill              
        | globalValueReference(qn) -> toFill
        | stringConst(s) -> toFill             
        | intConst(i) -> toFill
        --
        --
        | nestedExpr(_, e, _) -> injectAnnos(e,toInject, needAnnos, location=toFill.location) 
        | terminalConstructor(a, b, c, d, e1, f, e2, g) ->  
            terminalConstructor(a,b,c,d, 
                injectAnnos(e1, toInject, needAnnos, location=toFill.location),
                f,
                injectAnnos(e2, toInject, needAnnos, location=toFill.location),
                g, location=toFill.location)
        | terminalFunction(a,b,c,d,ex,f) ->
            terminalFunction(a,b,c,d,injectAnnos(ex, toInject, needAnnos, location=toFill.location), f, location=toFill.location)
        | caseExpr(es, x, e, y) ->
            caseExpr(
                map(\ e2::Expr -> injectAnnos(e2, toInject, needAnnos, location=toFill.location), es),
                x,
                injectAnnos(e, toInject, needAnnos, location=toFill.location),
                y, location=toFill.location)
        | toStringFunction(a,b,e,c) -> toStringFunction(a,b,
            injectAnnos(e,toInject, needAnnos,location=toFill.location), c, location=toFill.location)
        | _ -> errorExpr([err(toFill.location, "Unexpected expr type: " ++ toFill.ppDebug)], location=toFill.location)
    end;
} 

abstract production injectApplication
top::Expr ::= toFill::Expr appExps::AppExprs annoExps::AnnoAppExprs 
              toInject::AnnoAppExprs needAnnos::[String]
{
    local baseAppExprs::AppExprs = injectAppExprs(appExps, toInject, needAnnos, location=toFill.location);
    local baseAnnoExprs::AnnoAppExprs = injectAnnoExprs(annoExps, toInject, needAnnos, location=toFill.location);

    top.errors := [err(top.location, toFill.pp ++ foldl(\ s1::String s::String -> s1++"-"++s, "", needAnnos))];

    forwards to application(
        toFill, 
        -- note disallowing production names that are generated from other productions
        -- or functions for now
        --injectAnnos(toFill, toInject, needAnnos, location=toFill.location),
        '(',
        baseAppExprs,
        ',',
        -- We assume here that none are already provided
        if contains(toFill.pp, needAnnos) then consAnnoAppExprs(baseAnnoExprs, toInject, location=toFill.location)
        else baseAnnoExprs,
        ')', location=toFill.location
    );
}

abstract production injectAppExprs
top::AppExprs ::= toFill::AppExprs toInject::AnnoAppExprs needAnnos::[String]
{
    forwards to case toFill of 
        | snocAppExprs(es,_,e) -> snocAppExprs(injectAppExprs(es,toInject,needAnnos, location=toFill.location),
          ',',
          injectAppExpr(e,toInject,needAnnos, location=toFill.location), location=toFill.location)
        | oneAppExprs(e) -> oneAppExprs(injectAppExpr(e,toInject,needAnnos, location=toFill.location), location=toFill.location)
        | _ -> toFill
    end;
}

abstract production injectAppExpr
top::AppExpr ::= toFill::AppExpr toInject::AnnoAppExprs needAnnos::[String]
{
    forwards to case toFill of 
        | presentAppExpr(e) -> presentAppExpr(
            injectAnnos(e,toInject,needAnnos, location=toFill.location),location=toFill.location)
    end;
}

abstract production injectAnnoExprs
top::AnnoAppExprs ::= toFill::AnnoAppExprs toInject::AnnoAppExprs needAnnos::[String]
{
    forwards to case toFill of 
        | snocAnnoAppExprs(es,_,e) -> 
          snocAnnoAppExprs(injectAnnoExprs(es,toInject,needAnnos, location=toFill.location),
            ',',
            injectAnnoExpr(e,toInject,needAnnos,location=toFill.location),location=toFill.location)
        | oneAnnoAppExprs(e) -> 
          oneAnnoAppExprs(injectAnnoExpr(e,toInject,needAnnos,location=toFill.location),
            location=toFill.location)
        | _ -> toFill
    end;
}

abstract production injectAnnoExpr
top::AnnoExpr ::= toFill::AnnoExpr toInject::AnnoAppExprs needAnnos::[String]
{
    forwards to case toFill of 
        | annoExpr(qn,_,ae) -> annoExpr(qn,'=',injectAppExpr(ae,toInject,needAnnos, location=toFill.location),
            location=toFill.location)
    end;
}