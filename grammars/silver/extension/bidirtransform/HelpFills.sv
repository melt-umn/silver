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

abstract production fillExpr
top::Expr ::= toFill::Expr exps::[Expr] names::[String]
{
    -- todo: fill out more cases
    -- todo also: convert all of this nonsense into attributes with aspect productions
    forwards to case toFill of 
        | nestedExpr(_, e, _) -> fillExpr(e,exps,names, location=toFill.location)
        | baseExpr(qn) -> if !contains(qn.name, names) then toFill
            else idxOf(exps, findIdx(names,qn.name), location=toFill.location)
        | application(e, _, appexps, _, annexps, _) ->
            application(fillExpr(e, exps, names, location=toFill.location), 
            '(', fillAppExprs(appexps, exps, names, location=toFill.location), 
            ',', 
            fillAnnoExprs(annexps, exps, names, location=toFill.location), 
            ')', location=toFill.location)
        | _ -> toFill
    end;
}

abstract production idxOf
top::Expr ::= ls::[Expr] idx::Integer
{
    forwards to if idx == 0 then head(ls) else idxOf(tail(ls), idx-1, location=top.location);
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
abstract production fillExprPattern
top::Expr ::= toFill::Expr appexps::AppExprs pattern::PatternList
{
    forwards to fillExprPatternHelper(toFill, matchAppExpsToPattern(appexps, pattern), location=toFill.location);
}

abstract production fillExprPatternHelper
top::Expr ::= toFill::Expr inputs::Pair<[Expr] [String]>
{
    forwards to fillExpr(toFill, inputs.fst, inputs.snd, location=toFill.location);
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