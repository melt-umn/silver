grammar silver:extension:bidirtransform;

concrete production transformRule
tr::TransformRule ::= l::ProductionDef '->' r::Expr
{
    l.env = tr.env;
    
    tr.pp = l.pp ++ "->" ++ r.pp;

    tr.namedSig = l.namedSig;
    tr.matchProd = l.matchProd;    
    tr.errors := l.errors ++ r.errors;
    tr.outputStmt = (\ e::Expr ->
        case e of application(_,_,aexpr,_,_,_) ->
            -- need to pass in e here and supply all of e's inherited attributes
            -- aka this needs to be a production
            fillExprPattern(r, aexpr, l.patternList, location=e.location)
        end
    );

    -- Do the productions in both the lhs and rhs result in the same type?
    -- tr.errors <- if !check(l.typerep, r.typerep).typeerror then []
    --              else [err(tr.location, "Transformation rule type mismatch")];
}

    -- -- b: are there anonymous variables referred to in the rhs that are not defined in the lhs?
    -- --    (error rhs)
    -- local anonVarsMatch :: Boolean = containsAll(prd.definedAnonVars, trr.providedVars); 
    -- trr.errors <- if anonVarsMatch then [] 
    --               else err(trr.location, "Undefined anonymous variables in transformation rule")
    -- -- c: are there anonymous variables defined in the lhs that are not used in the rhs? 
    -- --    (message: replace these with _)
    -- --    (error rhs)
    -- trr.errors <- if containsAll(trr.providedVars, prd.definedAnonVars) then [] 
    --               else err(trr.location, "Unused anonymous variables in transformation rule")
    -- -- d: are anonymous variables taken from the lhs used as the same type in the rhs?
    -- --    (error rhs)
    -- trr.errors <- if !anonVarsMatch then []
    --               else if typesMatch(prd.definedAnonVars, trr.providedVars) then []
    --               else err(trr.location, "Type mismatch in transformation rule")