grammar silver:extension:convenience;
import silver:definition:core;
import silver:definition:concrete_syntax;

synthesized attribute trans :: [Trans];
nonterminal ProductionDclStmts with trans;
nonterminal ProductionDclStmt with trans;

terminal Productions_kwd 'productions' lexer classes {KEYWORD};


synthesized attribute transName :: Name;
synthesized attribute transSig :: ProductionSignature;
synthesized attribute transBody :: ProductionBody;
nonterminal Trans with transName, transSig, transBody;

concrete production productionDclC
top::AGDcl ::= 'concrete' 'productions' '{' stmts::ProductionDclStmts '}' {

  forwards to buildCTrans(stmts.trans);
}

concrete production productionDclA
top::AGDcl ::= 'abstract' 'productions' '{' stmts::ProductionDclStmts '}' {

  forwards to buildATrans(stmts.trans);
}

concrete production productionDclStmtsOne
top::ProductionDclStmts ::= s::ProductionDclStmt {
  top.trans = s.trans;
}

concrete production productionDclStmtsCons
top::ProductionDclStmts ::= s::ProductionDclStmt ss::ProductionDclStmts {
  top.trans = s.trans ++ ss.trans;
}

concrete production productionDclStmt
top::ProductionDclStmt ::= id::Name ns::ProductionSignature b::ProductionBody {
  top.trans = [makeTrans(id, ns, b)];
}

function buildCTrans
AGDcl ::= t::[Trans] {
  return if null(t)
	 then agDclNone()
	 else agDclAppend(concreteProductionDcl('concrete', 'production', head(t).transName,
  				    head(t).transSig, 
				    head(t).transBody), buildCTrans(tail(t)));
}

function buildATrans
AGDcl ::= t::[Trans] {
  return if null(t)
	 then agDclNone()
	 else agDclAppend(productionDcl('abstract', 'production', head(t).transName,
  				    head(t).transSig, head(t).transBody), buildATrans(tail(t)));
}

abstract production makeTrans
top::Trans ::= i::Name s::ProductionSignature b::ProductionBody {
  top.transName = i;
  top.transSig = s;
  top.transBody = b;
}
