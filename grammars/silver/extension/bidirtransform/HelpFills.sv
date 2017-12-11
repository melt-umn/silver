grammar silver:extension:bidirtransform;

abstract production fillExpr
top::Expr ::= toFill::Expr exps::[Expr] names::[String]
{
    -- todo: fill out more cases
    -- todo also: convert all of this nonsense into attributes with aspect productions
    forwards to case toFill of 
        | nestedExpr(_, e, _) -> fillExpr(e,exps,names, location=toFill.location)
        | baseExpr(qn) -> fillExprEnd(toFill, exps, names, qn, location=toFill.location)
        | childReference(qn) -> fillExprEnd(toFill, exps, names, qn, location=toFill.location)
        | lhsReference(qn) -> fillExprEnd(toFill, exps, names, qn, location=toFill.location)
        | localReference(qn) -> fillExprEnd(toFill, exps, names, qn, location=toFill.location)
        | forwardReference(qn) -> fillExprEnd(toFill, exps, names, qn, location=toFill.location)
        | productionReference(qn) -> fillExprEnd(toFill, exps, names, qn, location=toFill.location)
        | functionReference(qn) -> fillExprEnd(toFill, exps, names, qn, location=toFill.location)                
        | globalValueReference(qn) -> fillExprEnd(toFill, exps, names, qn, location=toFill.location)
        | stringConst(s) -> fillStringConst(toFill, exps, names, s.lexeme, location=toFill.location)             
        | applicationEmpty(e, _, _) -> 
            applicationEmpty(fillExpr(e, exps, names, location=toFill.location), '(', ')', location=toFill.location) 
        | applicationExpr(e, _, appexps, _) ->
            applicationExpr(fillExpr(e, exps, names, location=toFill.location), 
            '(', fillAppExprs(appexps, exps, names, location=toFill.location), 
            ')', location=toFill.location)
        | application(e, _, appexps, _, annexps, _) ->
            application(fillExpr(e, exps, names, location=toFill.location), 
            '(', fillAppExprs(appexps, exps, names, location=toFill.location), 
            ',', 
            fillAnnoExprs(annexps, exps, names, location=toFill.location), 
            ')', location=toFill.location)
        | terminalConstructor(a, b, c, d, e1, f, e2, g) ->  
            terminalConstructor(a,b,c,d, 
                fillExpr(e1, exps, names, location=toFill.location),
                f,
                fillExpr(e2, exps, names, location=toFill.location),
                g, location=toFill.location)
        | terminalFunction(a,b,c,d,ex,f) ->
            terminalFunction(a,b,c,d,fillExpr(ex, exps, names, location=toFill.location), f, location=toFill.location)
        | caseExpr(es, x, e, y) ->
            caseExpr(
                map(\ e2::Expr -> fillExpr(e2, exps, names, location=toFill.location), es),
                x,
                fillExpr(e, exps, names, location=toFill.location),
                y, location=toFill.location)
        -- | _ -> toFill
        -- ???
        | toStringFunction(a,b,e,c) -> toStringFunction(a,b,
            fillExpr(e,exps,names,location=toFill.location), c, location=toFill.location)
        | intConst(i) -> intConst(i, location=toFill.location)
        | _ -> errorExpr([err(toFill.location, "Unexpected expr type: " ++ toFill.ppDebug)], location=toFill.location)
    end;
}

abstract production fillStringConst
top::Expr ::= toFill::Expr exps::[Expr] names::[String] s::String
{
    local idx::Integer = findIdx(names,s);

    forwards to 
        if !contains(s, names) then toFill -- Error
        else if idx == -1 || idx >= length(exps) then toFill -- Error 
        else idxOfExprs(exps, idx, location=toFill.location);
}

abstract production fillExprEnd
top::Expr ::= toFill::Expr exps::[Expr] names::[String] qn::Decorated QName
{   
    forwards to fillStringConst(toFill, exps, names, qn.name, location=toFill.location);
}

abstract production fillAppExprs
top::AppExprs ::= toFill::AppExprs exps::[Expr] names::[String]
{
    forwards to case toFill of 
        | snocAppExprs(es,_,e) -> snocAppExprs(fillAppExprs(es,exps,names, location=toFill.location),
          ',',
          fillAppExpr(e,exps,names, location=toFill.location), location=toFill.location)
        | oneAppExprs(e) -> oneAppExprs(fillAppExpr(e,exps,names, location=toFill.location), location=toFill.location)
        | _ -> toFill
    end;
}

abstract production fillAppExpr
top::AppExpr ::= toFill::AppExpr exps::[Expr] names::[String]
{
    forwards to case toFill of 
        | presentAppExpr(e) -> presentAppExpr(
            fillExpr(e,exps,names, location=toFill.location),location=toFill.location)
    end;
}

abstract production fillAnnoExprs
top::AnnoAppExprs ::= toFill::AnnoAppExprs exps::[Expr] names::[String]
{
    forwards to case toFill of 
        | snocAnnoAppExprs(es,_,e) -> 
          snocAnnoAppExprs(fillAnnoExprs(es,exps,names, location=toFill.location),
            ',',
            fillAnnoAppExpr(e,exps,names,location=toFill.location),location=toFill.location)
        | oneAnnoAppExprs(e) -> 
          oneAnnoAppExprs(fillAnnoAppExpr(e,exps,names,location=toFill.location),
            location=toFill.location)
        | _ -> toFill
    end;
}

abstract production fillAnnoAppExpr
top::AnnoExpr ::= toFill::AnnoExpr exps::[Expr] names::[String]
{
    forwards to case toFill of 
        | annoExpr(qn,_,ae) -> annoExpr(qn,'=',fillAppExpr(ae,exps,names, location=toFill.location),
            location=toFill.location)
    end;
}

abstract production idxOfExprs
top::Expr ::= ls::[Expr] idx::Integer
{
    forwards to if idx == 0 then head(ls) else idxOfExprs(tail(ls), idx-1, location=top.location);
}

function idxOf
top::a ::= ls::[a] idx::Integer
{
    return if idx == 0 then head(ls) else idxOf(tail(ls), idx-1);
}

function findIdx
Integer ::= ls::[String] item::String
{
    return findIdxHelper(ls, item, 0);
}

function findIdxHelper
Integer ::= ls::[String] item::String idx::Integer
{
    return if null(ls) then -1
        else if head(ls) == item then idx
        else findIdxHelper(tail(ls), item, idx+1);
}


abstract production fillExprPattern
top::Expr ::= toFill::Expr appexps::AppExprs pattern::[Pattern]
{
    local inputs::Pair<[Expr] [String]> = matchAppExpsToPattern(appexps, pattern);

    forwards to fillExpr(toFill, inputs.fst, inputs.snd, location=toFill.location);
}

function matchAppExpsToPattern
Pair<[Expr] [String]> ::= appexps::AppExprs pattern::[Pattern]
{
    return case appexps of
        | snocAppExprs(es, _, e) -> joinPair(
            matchAppExpsToPattern(es, allHead(pattern)),
            matchAppExpToPattern(e, last(pattern))
        )
        | oneAppExprs(e) -> matchAppExpToPattern(e, head(pattern))
        | _ -> pair([],[])
    end;
}

function matchAppExpToPattern
Pair<[Expr] [String]> ::= appexp::AppExpr pattern::Pattern
{
    return case appexp of 
        | missingAppExpr(_) -> pair([],[])
        | presentAppExpr(e) -> matchExpToPattern(e, pattern)
    end;
}

function matchExpToPattern
Pair<[Expr] [String]> ::= e::Expr pattern::Pattern
{
    -- todo: fill out more cases (lists)
    -- otherwise I'm 75% confident that, because you can't define patterns that 
    -- would match other expressions, we won't need to check against 
    -- other expression productions
    -- todo also: convert all of this into attributes with aspect productions
    return case pattern of 
        | prodAppPattern(_,_,pl,_) -> case e of 
            -- todo: this is never going to be an application,
            -- this is always going to be a single string const 
            -- or name 
            -- This means we need to take the function at the lhs 
            -- of the prod app pattern and recurse on ITS named
            -- signature?
            | application(e2, _, appexprs, _, _, _) -> 
                matchAppExpsToPattern(appexprs, pl.rawPatternList)
            | _ -> pair([],[])
        end
        | varPattern(v) -> pair([e],[v.name])
        | _ -> pair([],[]) -- we covered wildcard elsewhere, and others are constants
    end;
}

function pullOutAppExprs
[Expr] ::= aexprs::AppExprs
{
    return case aexprs of 
        | snocAppExprs(es,_,e) -> 
            pullOutAppExprs(es) ++ case e of presentAppExpr(e2) -> [e2] end
        | oneAppExprs(e) -> 
            case e of presentAppExpr(e2) -> [e2] end
        | _ -> []
    end;
}

function joinPair
Pair<[c] [d]> ::= a::Pair<[c] [d]> b::Pair<[c] [d]>
{
    return pair(a.fst ++ b.fst, a.snd ++ b.snd);
}

function lastElemPattern
Pattern ::= pl::PatternList
{
    return case pl of 
        | patternList_one(p) -> p
        | patternList_more(p,_,patternList_nil()) -> p      
        | patternList_more(_,_,pl) -> lastElemPattern(pl)
        | _ -> wildcPattern('_', location=pl.location) -- error out here
    end;
}

function leftTailPattern
PatternList ::= pl::PatternList 
{
    return case pl of 
        | patternList_more(p, _, patternList_one(_)) -> patternList_one(p, location=pl.location)
        | patternList_more(p, _, pl) -> patternList_more(p, ',', leftTailPattern(pl), location=pl.location)
        | _ -> patternList_nil(location=pl.location)
    end;
}