grammar silver:compiler:extension:convenienceaspects;



-- Should produce/forward-to a list of AgDcl
abstract production convenienceAspects
top::[AGDcl] ::= attr::QName ty::TypeExpr ml::MRuleList
{

  local attrDcl::DclInfo = attr.lookupAttribute.dcl;


  local fwrd::[AGDcl] = map(generateAspectExprFromMatch(ty,attr,_), ml.matchRuleList);
  local trees::String = implode(",\n\n",(map(\agdcl::AGDcl -> agdcl.unparse, fwrd)));

  forwards to unsafeTrace(fwrd, print(trees ++ "\n\n", unsafeIO()));

}


function generateAspectExprFromMatch
AGDcl ::= ty::TypeExpr attr::QName mRule::AbstractMatchRule
 {
   local rhsExpr::Expr = case mRule of
   | matchRule(_,_,e) -> e
   end;

   local patternList::[Decorated Pattern] = case mRule of
   | matchRule(pl,_,_) -> pl
   end;


   return case mRule.headPattern of
   -- Not certain how varPatterns would work here
   -- | varPattern(_) ->
   --   Silver_AGDcl {
   --     aspect default production
   --     top::$TypeExpr{ty} ::=
   --     { top.$QName{attr} = $Expr{rhsExpr}; }
   --   }
   | prodAppPattern_named(prod,_,_,_,_,_) ->
     Silver_AGDcl {
       aspect production $QName{prod}
       top::$TypeExpr{ty} ::=
       { top.$QName{attr} = $Expr{rhsExpr}; }
     }
   | wildcPattern(_) ->
     Silver_AGDcl {
       aspect default production
       top::$TypeExpr{ty} ::=
       { top.$QName{attr} = $Expr{rhsExpr}; }
     }
   | _ -> errorAGDcl([err(mRule.location,
                            "This production is not supported for convenience aspect productions." )],
                         location=mRule.location)
   end;
  }
