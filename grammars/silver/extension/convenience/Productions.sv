grammar silver:extension:convenience;

import silver:definition:core;
import silver:definition:concrete_syntax;
import silver:translation:java:concrete_syntax:copper;

synthesized attribute trans :: [Trans];
nonterminal ProductionDclStmts with trans;
nonterminal ProductionDclStmt with trans;

terminal Productions_kwd 'productions' lexer classes {KEYWORD};

synthesized attribute transName :: Maybe<Name>;
synthesized attribute transSig :: ProductionSignature;
synthesized attribute transBody :: ProductionBody;
synthesized attribute transMods :: ProductionModifiers ;
synthesized attribute transAction :: Maybe<ActionCode_c> ;
nonterminal Trans with transName, transSig, transBody, transMods, transAction ;

concrete production productionDclC
top::AGDcl ::= c::'concrete' 'productions' stmts::ProductionDclStmts 
{
  forwards to buildCTrans( stmts.trans, 1, c.line, c.filename );
}

{-
concrete production productionDclA
top::AGDcl ::= 'abstract' 'productions' '{' stmts::ProductionDclStmts '}' {

  forwards to buildATrans(stmts.trans);
}
-}

concrete production productionDclStmtsOne
top::ProductionDclStmts ::= s::ProductionDclStmt {
  top.trans = s.trans;
}

concrete production productionDclStmtsCons
top::ProductionDclStmts ::= s::ProductionDclStmt ss::ProductionDclStmts {
  top.trans = s.trans ++ ss.trans;
}

--concrete production productionDclStmt
--top::ProductionDclStmt ::= id::Name ns::ProductionSignature b::ProductionBody {
--  top.trans = [makeTrans(id, ns, b)];
--  }


concrete production productionDclStmt
top::ProductionDclStmt ::= ns::ProductionSignature 
                           optn::OptionalName optm::OptionalModifiers
                           b::ProductionBody opta::OptionalAction {
  top.trans = [makeTrans( optn.transName, ns, b, optm.transMods, opta.transAction )];
}

concrete production productionDclStmtPrecedingName
top::ProductionDclStmt ::= pn::PrecedingName ns::ProductionSignature 
                           optm::OptionalModifiers
                           b::ProductionBody opta::OptionalAction {
  top.trans = [makeTrans( pn.transName, ns, b, optm.transMods, opta.transAction )];
}

nonterminal OptionalName with transName ;
concrete production noOptionalName
optn::OptionalName ::=
{ optn.transName = nothing() ; }
concrete production anOptionalName
optn::OptionalName ::= '(' id::Name ')'
{ optn.transName = just(id) ; }

nonterminal PrecedingName with transName ;
concrete production aPrecedingName
pn::PrecedingName ::= id::Name ':'
{ pn.transName = just(id) ; }


nonterminal OptionalModifiers with transMods ;
concrete production noOptionalModifiers
optm::OptionalModifiers ::=
{ optm.transMods = productionModifiersNone() ; }
concrete production someOptionalModifiers
optm::OptionalModifiers ::= m::ProductionModifiers
{ optm.transMods = m ; }


nonterminal OptionalAction with transAction ;
concrete production noOptionalAction
opta::OptionalAction ::=
{ opta.transAction = nothing() ; }
concrete production anOptionalAction
opta::OptionalAction ::= 'action' acode::ActionCode_c
{ opta.transAction = just(acode) ; }





{-
nonterminal AlternateProductionBodies ;

concrete production noAlternateProductionBodies
pb::AlternateProductionBodies ::=
{ }

concrete production consAlternateProductionBodies
pb::AlternateProductionBodies ::= '|' rest::AlternateProductionBodies
{ }
-}

function buildCTrans
AGDcl ::= ts::[Trans] n::Integer l::Integer f::String
{ return
    if   null(ts)
    then agDclNone()
    else agDclAppend(
           case t.transAction of
              nothing() ->
                concreteProductionDclModifiers('concrete', 'production', 
                  case t.transName of
                    just(tn) -> tn 
                  | nothing() -> nameIdLower(terminal(IdLower_t, name)) end ,
                  t.transSig, 
                  t.transMods, 
                  t.transBody 
                 )
            | just(a) ->
                concreteProductionDclModifiersAction('concrete', 'production',
                  case t.transName of
                    just(tn) -> tn 
                  | nothing() -> nameIdLower(terminal(IdLower_t, name)) end ,
                  t.transSig, 
                  t.transMods, 
                  t.transBody,
                  'action', a ) 
           end ,

           buildCTrans(tail(ts), n+1, l, f) );

  local t::Trans = head(ts) ;
  local name :: String = "anonymousProduction_" ++ toString(n) ++ 
                         "_in_block_on_line_" ++
                         toString(l) ++ "_in_file_" ++ 
                         implode("", takeWhile(isAlpha, explode("",f)) ) ;
}

{-
function buildATrans
AGDcl ::= t::[Trans] {
  return if null(t)
	 then agDclNone()
	 else agDclAppend(productionDcl('abstract', 'production', head(t).transName,
  				    head(t).transSig, head(t).transBody), buildATrans(tail(t)));
}
-}

abstract production makeTrans
t::Trans ::= i::Maybe<Name>  s::ProductionSignature  b::ProductionBody 
             m::ProductionModifiers  a::Maybe<ActionCode_c> {
  t.transName = i;
  t.transSig = s;
  t.transBody = b;
  t.transMods = m;
  t.transAction = a ;
}
