grammar silver:extension:bidirtransform;

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

function fillExpr
Expr ::= toFill::Expr exps::[Expr] names::[String]
{
    -- todo: fill out more cases
    -- todo also: convert all of this nonsense into attributes with aspect productions
    return case toFill of 
        | nestedExpr(_, e, _) -> fillExpr(e,exps,names)
        | baseExpr(qn) -> if !contains(qn.name, names) then toFill
            else idxOf(exps, findIdx(names,qn.name))
        | application(e, _, appexps, _, annexps, _) ->
            application(fillExpr(e, exps, names), 
            '(', fillAppExprs(appexps, exps, names), ',', 
            fillAnnoExprs(annexps, exps, names), ')', location=toFill.location)
        | _ -> toFill
    end;
}

function idxOf
a ::= ls::[a] idx::Integer
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

-- We're doing this recursive structure because the official silver docs say that
-- let expressions are deprecated
function fillExprPattern
Expr ::= toFill::Expr appexps::AppExprs pattern::PatternList
{
    return fillExprPatternHelper(toFill, matchAppExpsToPattern(appexps, pattern));
}

function fillExprPatternHelper
Expr ::= toFill::Expr inputs::Pair<[Expr] [String]>
{
    return fillExpr(toFill, inputs.fst, inputs.snd);
}

function matchAppExpsToPattern
Pair<[Expr] [String]> ::= appexps::AppExprs pattern::PatternList
{
    return case appexps of
        | snocAppExprs(es, _, e) -> case pattern of patternList_more(p, _, pl) ->
            joinPair(matchAppExpsToPattern(es, patternList_more(p, ',', leftTailPattern(pl), location=pattern.location)),
                matchAppExpToPattern(e, lastElemPattern(pl)))
        end
        | oneAppExprs(e) -> case pattern of patternList_one(p) ->
            matchAppExpToPattern(e, p)
        end
        | _ -> pair([],[])
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

function matchAppExpToPattern
Pair<[Expr] [String]> ::= appexp::AppExpr pattern::Pattern
{
    return case appexp of 
        | missingAppExpr(_) -> pair([],[])
        | presentAppExpr(e) -> case pattern of 
            | wildcPattern(_) -> pair([],[])
            | _ -> matchExpToPattern(e, pattern)
        end
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
            | application(e2, _, appexprs, _, _, _) -> 
                matchAppExpsToPattern(appexprs, pl)
            | _ -> pair([],[])
        end
        | varPattern(v) -> pair([e],[v.name])
        | _ -> pair([],[]) -- we covered wildcard elsewhere, and others are constants
    end;
}

function fillAppExprs
AppExprs ::= toFill::AppExprs exps::[Expr] names::[String]
{
    return case toFill of 
        | snocAppExprs(es,_,e) -> snocAppExprs(fillAppExprs(es,exps,names),',',fillAppExpr(e,exps,names), location=toFill.location)
        | oneAppExprs(e) -> oneAppExprs(fillAppExpr(e,exps,names), location=toFill.location)
        | _ -> toFill
    end;
}

function fillAppExpr
AppExpr ::= toFill::AppExpr exps::[Expr] names::[String]
{
    return case toFill of 
        | presentAppExpr(e) -> presentAppExpr(fillExpr(e,exps,names),location=toFill.location)
    end;
}

function fillAnnoExprs
AnnoAppExprs ::= toFill::AnnoAppExprs exps::[Expr] names::[String]
{
    return case toFill of 
        | snocAnnoAppExprs(es,_,e) -> snocAnnoAppExprs(fillAnnoExprs(es,exps,names),',',fillAnnoAppExpr(e,exps,names),location=toFill.location)
        | oneAnnoAppExprs(e) -> oneAnnoAppExprs(fillAnnoAppExpr(e,exps,names),location=toFill.location)
        | _ -> toFill
    end;
}

function fillAnnoAppExpr
AnnoExpr ::= toFill::AnnoExpr exps::[Expr] names::[String]
{
    return case toFill of 
        | annoExpr(qn,_,ae) -> annoExpr(qn,'=',fillAppExpr(ae,exps,names), location=toFill.location)
    end;
}