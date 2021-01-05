grammar silver:extension:convenienceaspects;


nonterminal ConvenienceAspects with pp, unparse, location;

-- Should produce/forward-to a list of AgDcl
abstract production convenienceAspects
top::ConvenienceAspects ::= attr::QName ty::TypeExpr ml::MRuleList
{
  top.unparse = "aspect " ++ attr.unparse ++ " on " ++ ty.unparse ++ " of " ++ ml.unparse ++ " end";

  local attrDcl::DclInfo = attr.lookupAttribute.dcl;

}


function generateAspectExprFromMatch
AGDcl ::= ty::TypeExpr attr::QName matchRule::AbstractMatchRule
 {
   local rhsExpr::Expr = case matchRule of
   | AbstractMatchRule(_,_,e) -> e
   end;

   local patternList::[Pattern] = case matchRule of
   | AbstractMatchRule(pl,_,_) -> pl
   end;


   return case matchRule.headPattern of
   | wildcPattern(_) ->
     Silver_AGDcl {
       aspect default production
       top::$TypeExpr{ty} ::=
       { top.$QName{attr} = $Expr{rhsExpr}; }
     }
   end;
  }
