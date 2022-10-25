grammar silver:compiler:extension:datalog;


import silver:compiler:extension:strategyattr;
import silver:compiler:extension:convenience;
import silver:compiler:definition:core;
import silver:compiler:definition:concrete_syntax;
import silver:compiler:definition:type:syntax;
import silver:compiler:definition:type;
import silver:compiler:definition:env;
import silver:compiler:extension:autoattr:convenience;
import silver:compiler:modification:list;
import silver:compiler:extension:autoattr;
import silver:compiler:modification:collection;

terminal Datalog  'datalog' lexer classes {KEYWORD};
terminal In       'in' lexer classes {KEYWORD};


function generateFactsAttribName
Name ::= a::Name loc::Location
{
  return name("datalog" ++ a.name ++ "Facts",loc);
  -- return name("foo",loc);
}

concrete production datalogInSyn
top::AGDcl ::= 'synthesized' 'datalog' 'in' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "synthesized attribute " ++ a.name ++ tl.unparse ++ " :: " ++ te.unparse ++ " occurs on " ++ qs.unparse ++ ";" ;
  local AGDclList::[AGDcl] =
    [attributeDclSyn($1, $4, a, tl, $7, te, $12, location=top.location),
     makeOccursDclsHelp(top.location, qNameWithTL(qNameId(a, location=a.location), tl), qs.qnames),
     Silver_AGDcl {
     monoid attribute $Name{generateFactsAttribName(a,top.location)} :: [Fact] with [],++ occurs on $QName{head(qs.qnames).qnwtQN};
     },
     Silver_AGDcl {
     propagate $Name{generateFactsAttribName(a,top.location)} on $QName{head(qs.qnames).qnwtQN};
     }
     ];
  local combinedAGDcls::AGDcls = foldr(
   consAGDcls(_,_,location=top.location),
   nilAGDcls(location=top.location),
   AGDclList);
  local fwrd::AGDcl = makeAppendAGDclOfAGDcls(combinedAGDcls);
  forwards to unsafeTracePrint(fwrd,"begin unparse\n" ++ fwrd.unparse ++ "\n");
}


concrete production datalogInMon
top::AGDcl ::= 'monoid' 'datalog' 'in' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'with' e::Expr ',' q::NameOrBOperator 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "monoid datalog in attribute " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ " with " ++ e.unparse ++ ", " ++ q.unparse ++ " occurs on " ++ qs.unparse ++ ";";
  local AGDclList::[AGDcl] =
    [monoidAttributeDcl($1, $4, a, tl, $7, te, $9, e, $11, q, $16, location=a.location),
     makeOccursDclsHelp($1.location, qNameWithTL(qNameId(a, location=a.location), botlNone(location=top.location)), qs.qnames),
     Silver_AGDcl {
     monoid attribute $Name{generateFactsAttribName(a,top.location)} :: [Fact] with [],++ occurs on $QName{head(qs.qnames).qnwtQN};
     },
     Silver_AGDcl {
     propagate $Name{generateFactsAttribName(a,top.location)} on $QName{head(qs.qnames).qnwtQN};
     }
     ];
  local combinedAGDcls::AGDcls = foldr(
   consAGDcls(_,_,location=top.location),
   nilAGDcls(location=top.location),
   AGDclList);
  local fwrd::AGDcl = makeAppendAGDclOfAGDcls(combinedAGDcls);
  forwards to unsafeTracePrint(fwrd,"begin unparse\n" ++ fwrd.unparse ++ "\n");
}
