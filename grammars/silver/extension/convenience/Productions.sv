grammar silver:extension:convenience;
import silver:definition:core;
import silver:definition:concrete_syntax;

--   TEMPORARILY(?) DISABLED wasn't used, wasn't hard pressed to fix it yet!

synthesized attribute trans :: [Trans];
nonterminal ProductionDclStmts with trans;
nonterminal ProductionDclStmt with trans;

terminal Productions_kwd 'productions' lexer classes {KEYWORD};

synthesized attribute transName :: Maybe<Name>;
synthesized attribute transSig :: ProductionSignature;
synthesized attribute transBody :: ProductionBody;
nonterminal Trans with transName, transSig, transBody;

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

concrete production productionDclStmtNoName
top::ProductionDclStmt ::= ns::ProductionSignature b::ProductionBody {
  top.trans = [makeTrans( nothing(), ns, b)];
}

concrete production productionDclStmtWithName
top::ProductionDclStmt ::= ns::ProductionSignature  '(' id::Name ')' b::ProductionBody 
{
  top.trans = [makeTrans(just(id), ns, b)];
}
{-
concrete production productionDclStmtWithNameBefore
top::ProductionDclStmt ::= id::Name ':' ns::ProductionSignature  b::ProductionBody 
{
  top.trans = [makeTrans(just(id), ns, b)];
}
-}
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
AGDcl ::= t::[Trans] n::Integer l::Integer f::String
{ return
    if   null(t)
    then agDclNone()
    else agDclAppend(
           concreteProductionDcl('concrete', 'production', 
             case head(t).transName of
               just(tn) -> tn 
             | nothing() -> nameIdLower(terminal(IdLower_t, name)) end ,
             head(t).transSig, 
             head(t).transBody ), 
           buildCTrans(tail(t), n+1, l, f) );

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
top::Trans ::= i::Maybe<Name>
               s::ProductionSignature b::ProductionBody {
  top.transName = i;
  top.transSig = s;
  top.transBody = b;
}
