grammar silver:compiler:extension:convenienceaspects;

import silver:compiler:analysis:typechecking:core;



-- Should produce/forward-to a list of AgDcl
abstract production convenienceAspects
top::AGDcl ::= attr::QName ty::TypeExpr ml::MRuleList
{

  local attrDcl::DclInfo = attr.lookupAttribute.dcl;


  local fwrd::AGDcl = makeAppendAGDclOfAGDcls(ml.aspectDcls);
  -- local trees::String = implode(",\n\n",(map(\agdcl::AGDcl -> agdcl.unparse, fwrd)));

  forwards to unsafeTrace(fwrd, print(fwrd.unparse ++ "\n\n", unsafeIO()));

}

synthesized attribute makeAspect::(AGDcl ::= Expr) occurs on Pattern;
synthesized attribute aspectDcl::AGDcl occurs on MatchRule;
synthesized attribute aspectDcls::AGDcls occurs on MRuleList;
autocopy attribute aspectTy::TypeExpr occurs on MRuleList, MatchRule, PatternList, Pattern;
autocopy attribute aspectAttr::QName occurs on MRuleList, MatchRule, PatternList, Pattern;


aspect production mRuleList_one
top::MRuleList ::= m::MatchRule
{
  top.aspectDcls = consAGDcls(m.aspectDcl, nilAGDcls(location=top.location),location=top.location);

}

aspect production mRuleList_cons
top::MRuleList ::= h::MatchRule '|' t::MRuleList
{
  top.aspectDcls = consAGDcls(h.aspectDcl, t.aspectDcls, location=top.location);

}

aspect production matchRule_c
top::MatchRule ::= pt::PatternList _ e::Expr
{

 top.aspectDcl = head(pt.patternList).makeAspect(e);
}

aspect default production
top::MatchRule ::=
{
  top.aspectDcl = errorAGDcl([err(top.location,
                            "Error on MatchRule aspectDcl production." )],
                         location=top.location);

}


-- function GenAspectParamFromPattern
-- AspectRHSElem ::= p::Pattern
-- {
--   case p of
--   |

-- }

-- function GenerateAspectParamsFromPatternList
-- [AspectRHSElem] ::=  ps::PatternList
-- {
--   case ps of
--   | patternList_one(p) ->
-- }


-- abstract production functionType
-- top::Type ::= out::Type params::[Type] namedParams::[NamedArgType]

aspect production prodAppPattern_named
top::Pattern ::= prod::QName '(' ps::PatternList ',' nps::NamedPatternList ')'
{
  -- local rhs::AspectRHS = aspectRHSElemNil(location=top.location);

  local parms :: Integer = prod.lookupValue.typeScheme.arity;
  local prodType :: Type = prod.lookupValue.typeScheme.typerep;
  local typeParams :: [Type] = case prodType of
                             | functionType(_,p,_) -> p
                             | _ -> []
                             end;
                             -- TODO: make error cases for the bits where its not a valid prod
  local paramsAspect :: AspectRHS =
        foldr(\asp1::AspectRHSElem asp2::AspectRHS -> aspectRHSElemCons(asp1,asp2, location=top.location),
              aspectRHSElemNil(location=top.location),
              (map((\ty::Type -> aspectRHSElemFull(name("generatedAspectArg" ++ toString(genInt()),top.location),
                                                   ty,
                                                   location = top.location)),
                    typeParams)));


  top.makeAspect = \rhsExpr::Expr ->
      Silver_AGDcl {
        aspect production $QName{prod}
        top::$TypeExpr{top.aspectTy} ::= $AspectRHS{paramsAspect}
        { top.$QName{top.aspectAttr} = $Expr{rhsExpr}; }
      };

}

-- In the Case.sv file (in silver/compiler/extensions/patternmatching) there's a
-- groupMRules function which groups AbstractMatchRule by production

aspect production wildcPattern
top::Pattern ::= '_'
{
  top.makeAspect = \rhsExpr::Expr ->
    Silver_AGDcl {
      aspect default production
      top::$TypeExpr{top.aspectTy} ::=
      { top.$QName{top.aspectAttr} = $Expr{rhsExpr}; }
    };

}

aspect default production
top::Pattern ::=
{
  top.makeAspect = \rhsExpr::Expr -> errorAGDcl([err(top.location,
                            "This production is not supported for convenience aspect productions." )],
                         location=top.location);

}


-- function generateAspectExprFromMatch
-- AGDcl ::= ty::TypeExpr attr::QName mRule::AbstractMatchRule
--  {
--    local rhsExpr::Expr = case mRule of
--    | matchRule(_,_,e) -> e
--    end;

--    local patternList::[Decorated Pattern] = case mRule of
--    | matchRule(pl,_,_) -> pl
--    end;


--    return case mRule.headPattern of
--    -- Not certain how varPatterns would work here
--    -- | varPattern(_) ->
--    --   Silver_AGDcl {
--    --     aspect default production
--    --     top::$TypeExpr{ty} ::=
--    --     { top.$QName{attr} = $Expr{rhsExpr}; }
--    --   }
--    | prodAppPattern_named(prod,_,_,_,_,_) ->
--      Silver_AGDcl {
--        aspect production $QName{prod}
--        top::$TypeExpr{ty} ::=
--        { top.$QName{attr} = $Expr{rhsExpr}; }
--      }
--    | wildcPattern(_) ->
--      Silver_AGDcl {
--        aspect default production
--        top::$TypeExpr{ty} ::=
--        { top.$QName{attr} = $Expr{rhsExpr}; }
--      }
--    | _ -> errorAGDcl([err(mRule.location,
--                             "This production is not supported for convenience aspect productions." )],
--                          location=mRule.location)
--    end;
--   }
