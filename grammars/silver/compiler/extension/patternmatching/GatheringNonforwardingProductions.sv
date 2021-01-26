grammar silver:compiler:extension:patternmatching;


imports silver:compiler:driver;
imports silver:compiler:modification:defaultattr;
imports silver:compiler:modification:ffi;
imports silver:compiler:modification:copper;
imports silver:compiler:definition:concrete_syntax;
imports silver:compiler:modification:collection;
imports silver:compiler:modification:autocopyattr;
imports silver:compiler:modification:copper_mda;
imports silver:compiler:modification:impide;
imports silver:compiler:modification:let_fix;
{-
  We need a lot of imports so propagating nonforwardingProductions
  will work correctly, including hitting the modifications.
-}


--Gathering productions which do not forward
monoid attribute nonforwardingProductions::[DclInfo] with [], ++;
attribute nonforwardingProductions occurs on
          Grammar, Root, AGDcl, AGDcls, ImportStmt, ModuleStmt, ImportStmts, ModuleStmts;
propagate nonforwardingProductions on
          Grammar, Root, AGDcl, AGDcls, ImportStmt, ModuleStmt, ImportStmts, ModuleStmts
          excluding productionDcl, importStmt, importsStmt;

--Pairs of fully-qualified nonterminal name and (fully-qualified production names and number of args)
autocopy attribute requiredProductionPatterns::[Pair<String [Pair<String Integer>]>];
attribute requiredProductionPatterns occurs on
          Grammars, Grammar, Root, AGDcl, AGDcls, ProductionBody, ProductionStmts,
          ProductionStmt, Expr, Exprs, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr,
          MRuleList, PrimPattern, PrimPatterns;


aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  {-
    The "unique significant expression" for a production is the
    forward.  If it is null, this is a non-forwarding production.
  -}
  local doesForward::Boolean = ! null(body.uniqueSignificantExpression);

  top.nonforwardingProductions :=
      if doesForward
      then []
      else [prodDef(top.grammarName, id.location, namedSig, false).dcl];
}



aspect production importStmt
top::ImportStmt ::= 'import' m::ModuleExpr ';'
{
  top.nonforwardingProductions := defsToNonforwardingProductions(m.defs);
}


aspect production importsStmt
top::ModuleStmt ::= 'imports' m::ModuleExpr ';'
{
  top.nonforwardingProductions := defsToNonforwardingProductions(m.defs);
}


function defsToNonforwardingProductions
[DclInfo] ::= l::[Def]
{
  return
     case l of
     | [] -> []
     | h::t ->
       case h of
       | prodDclDef(_) ->
         case h.dcl of
         | prodDcl(_, forwardExists) ->
           if forwardExists
           then []
           else [h.dcl]
         | _ -> []
         end
       | _ -> []
       end ++ defsToNonforwardingProductions(t)
     end;
}




aspect production grammarRootSpec
top::RootSpec ::= g::Grammar  grammarName::String  grammarSource::String  grammarTime::Integer  generateLocation::String
{
  g.requiredProductionPatterns = groupNonforwardingProductions(g.nonforwardingProductions);
}






function groupNonforwardingProductions
[Pair<String [Pair<String Integer>]>] ::= prods::[DclInfo]
{
  local getTypeName::(String ::= DclInfo) =
        \ d::DclInfo ->
          getHeadTypeName(d.typeScheme.typerep.outputType);
  local getHeadTypeName::(String ::= Type) =
        \ ty::Type ->
          case ty of
          | nonterminalType(name, _, _) -> name
          | appType(ty1, _) -> getHeadTypeName(ty1)
          | ty -> error("Should only have applied nonterminal types given, not:  " ++ prettyType(ty))
          end;
  local eqFun::(Boolean ::= DclInfo DclInfo) =
        \ d1::DclInfo d2::DclInfo -> getTypeName(d1) == getTypeName(d2);
  local groups::[[DclInfo]] = groupBy(eqFun, prods);
  return map(\ dlst::[DclInfo] -> pair(getTypeName(head(dlst)), map(\ d::DclInfo -> pair(d.fullName, d.typeScheme.arity), dlst)), groups);
}

