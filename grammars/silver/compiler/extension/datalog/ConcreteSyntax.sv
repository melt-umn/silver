grammar silver:compiler:extension:datalog;


import silver:compiler:extension:strategyattr;
import silver:compiler:extension:convenience;
import silver:compiler:definition:core;
import silver:compiler:definition:concrete_syntax;
import silver:compiler:definition:type:syntax;
import silver:compiler:definition:type;
import silver:compiler:definition:env;
import silver:compiler:extension:autoattr:convenience;
import silver:compiler:extension:autoattr;
import silver:compiler:metatranslation;
import silver:compiler:modification:collection;
import silver:compiler:modification:list;
import silver:compiler:modification:defaultattr;
import silver:reflect:util;

terminal Datalog  'datalog' lexer classes {KEYWORD};
-- TODO: Check if keyword is the least powerful option here.
terminal In       'in' lexer classes {KEYWORD};
terminal Computes  'computes' lexer classes {KEYWORD};


function generateFactsAttribName
Name ::= a::String loc::Location
{
  return name("datalog" ++ capitalize(a) ++ "Facts",loc);
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
     monoid attribute $Name{generateFactsAttribName(a.name,top.location)} :: [Fact] with [],++ occurs on $QName{head(qs.qnames).qnwtQN};
     },
     Silver_AGDcl {
     propagate $Name{generateFactsAttribName(a.name,top.location)} on $QName{head(qs.qnames).qnwtQN};
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
     monoid attribute $Name{generateFactsAttribName(a.name,top.location)} :: [Fact] with [],++ occurs on $QName{head(qs.qnames).qnwtQN};
     },
     Silver_AGDcl {
     propagate $Name{generateFactsAttribName(a.name,top.location)} on $QName{head(qs.qnames).qnwtQN};
     }
     ];
  local combinedAGDcls::AGDcls = foldr(
   consAGDcls(_,_,location=top.location),
   nilAGDcls(location=top.location),
   AGDclList);
  local fwrd::AGDcl = makeAppendAGDclOfAGDcls(combinedAGDcls);
  forwards to unsafeTracePrint(fwrd,"begin unparse\n" ++ fwrd.unparse ++ "\n");
}


concrete production datalogComputesOn
--- TODO: Make QName a QNames when you fix antiquotes for QNames.
top::AGDcl ::= 'datalog' 'computes' 'on' q::QName ';'
{
  top.unparse = "datalog computes on " ++ q.unparse ++ ";";
  local AGDclList::[AGDcl] =
    [Silver_AGDcl{ attribute datalogOutFactNames occurs on $QName{q};},
     Silver_AGDcl{ attribute datalogSynthesizedFacts occurs on $QName{q};},
     Silver_AGDcl{ attribute datalogOutputFacts occurs on $QName{q};},
     Silver_AGDcl{ attribute datalogInput occurs on $QName{q};}
    ];
  local combinedAGDcls::AGDcls = foldr(
   consAGDcls(_,_,location=top.location),
   nilAGDcls(location=top.location),
   AGDclList);
  local fwrd::AGDcl = makeAppendAGDclOfAGDcls(combinedAGDcls);
  forwards to unsafeTracePrint(fwrd,"begin unparse\n" ++ fwrd.unparse ++ "\n");
}

concrete production datalogAttributeDef
top::ProductionStmt ::= '[' inAttrs::QNames ']' '->' '[' outAttrs::QNames ']' '->' 'datalog' ';'
{
  top.unparse = "TODO!";

  local listNamesOut :: [String] =
    map(\qn::QNameWithTL -> capitalize(qn.qnwtQN.name), outAttrs.qnames);
  local listSynthFactLists :: [Expr] =
    map(\qn::QNameWithTL ->
      Silver_Expr{$name{topName}.$QName{qNameId(generateFactsAttribName(qn.qnwtQN.name,top.location),location=top.location)}}, inAttrs.qnames);
  local listSynthFactListsExpr :: Expr =
    foldr(consListOp(_,'::',_,location=top.location),
    mkStrFunctionInvocation(top.location, "silver:core:nil", []),listSynthFactLists);

  local topName :: String = top.frame.signature.outputElement.elementName;

  local prodStmtList::[ProductionStmt] =
    [ Silver_ProductionStmt {
     $name{topName}.datalogOutFactNames = $Expr{translate(top.location,reflect(listNamesOut))};
    },
     Silver_ProductionStmt {
     $name{topName}.datalogSynthesizedFacts = $Expr{listSynthFactListsExpr};
     },
     Silver_ProductionStmt {
     $name{topName}.datalogOutputFacts = runDatalog($name{topName}.datalogSynthesizedFacts,$name{topName}.datalogOutFactNames, implode("\n",$name{topName}.datalogInput));
     }
    ];
  local combinedProdStmts::ProductionStmt = foldr1(
   productionStmtAppend(_,_,location=top.location),
   prodStmtList);

  forwards to combinedProdStmts;
}

terminal DatalogDefStart  'datalog!' lexer classes {KEYWORD};
terminal DatalogDefInputs  'inputs' lexer classes {KEYWORD};
terminal DatalogDefOutputs  'outputs' lexer classes {KEYWORD};

monoid attribute typereps :: [Type] with [],++;
nonterminal QNameAttrsOccur with unparse,attrFor;
-- propagate typereps on QNameAttrsOccur;

concrete production qNameAttrsOccurSingle
top::QNameAttrsOccur ::= qn::QNameAttrOccur
{
  top.unparse = qn.unparse;
  qn.attrFor = top.attrFor;
  -- top.typereps <- [qn.typerep];
}

concrete production qNameAttrsOccurCons
top::QNameAttrsOccur ::= qn1::QNameAttrOccur ',' qn2::QNameAttrsOccur
{
  top.unparse = qn1.unparse ++ ", " ++ qn2.unparse ;
  qn1.attrFor = top.attrFor;
  qn2.attrFor = top.attrFor;
  -- top.typereps <- [qn1.typerep] ++ qn2.typereps;
}

concrete production datalogDefAnalysis
top::AGDcl ::= 'datalog!' analysisName::QName '{' 'on' occurringNodes::QNames ';' 'with' 'inputs' ':' inAttrs::QNameAttrsOccur ';' 'outputs' ':' outAttrs::QNameAttrsOccur ';' dlogContent::DatalogContent '}'
{
  top.unparse = "TODO!";

  local inputName::Name = name("datalog" ++ capitalize(analysisName.name) ++ "Input",top.location);
  local inputQName::QName = qNameId(inputName,location=top.location);

  local attributeDef::AGDcl = Silver_AGDcl{ monoid attribute $Name{inputName}::[String] with [],++; };
  local occursOnList::[AGDcl] = map(\elem::QNameWithTL ->
  Silver_AGDcl{ attribute $Name{inputName} occurs on $QName{elem.qnwtQN}; },
  occurringNodes.qnames);

  -- local nodesOccurringTypes::[Type] = map(
  --   \elem::QNameWithTL -> elem.qnwtQN.lookupType.dcl.typeScheme.typerep,
  --   occurringNodes.qnames);
  -- -- TODO: Redecorate with different ones later?
  -- inAttrs.attrFor = head(nodesOccurringTypes);
  -- outAttrs.attrFor = head(nodesOccurringTypes);
  -- local typesOccurring::[Type] = nub(inAttrs.typereps ++ outAttrs.typereps);

  -- local attrsNames::[AGDcl] = flatMap(\elem::Type ->
  --   let inhName :: Name = name("datalog" ++ capitalize(analysisName.name) ++ elem.typeName ++ "Inh"),
  --       synName :: Name = name("datalog" ++ capitalize(analysisName.name) ++ elem.typeName ++ "Syn")
  --   in


  --  [
  --   inherited attribute stmtsInh :: [Decorated Stmt] occurs on Stmt,Root;
  --   monoid attribute stmtsSyn :: [Decorated Stmt] with [],++ occurs on Stmt,Root;
  --   propagate stmtsSyn on Stmt,Root;
  --   propagate stmtsInh on Stmt;
  --  ];




-- )
    -- [Silver_AGDcl{ attribute datalogOutFactNames occurs on $QName{q};},
    --  Silver_AGDcl{ attribute datalogSynthesizedFacts occurs on $QName{q};},
    --  Silver_AGDcl{ attribute datalogOutputFacts occurs on $QName{q};},
    --  Silver_AGDcl{ attribute datalogInput occurs on $QName{q};}
    -- ];

-- TODO: Uncomment when $QNames antiquotes start working.
--   local occursOnList::[AGDcl] =
--   [
--   Silver_AGDcl{ attribute $Name{inputName} occurs on $QNames{elem.qnwtQN}; },
-- ]
  -- occurringNodes.qnames;

  local aspectDatalogRules::[AGDcl] = map(\elem::QNameWithTL ->
  Silver_AGDcl{
    aspect default production
    top::$TypeExpr{
      nominalTypeExpr(
        makeQNameType(elem.qnwtQN.name,top.location),
        location=top.location)}
    ::= {

    }
  },
  occurringNodes.qnames);


  local AGDclList::[AGDcl] = attributeDef::occursOnList;
  local combinedAGDcls::AGDcls = foldr(
   consAGDcls(_,_,location=top.location),
   nilAGDcls(location=top.location),
   AGDclList);
  local fwrd::AGDcl = makeAppendAGDclOfAGDcls(combinedAGDcls);
  forwards to unsafeTracePrint(fwrd,"begin unparse\n" ++ fwrd.unparse ++ "\n");

}



nonterminal DatalogContent with unparse;

concrete production datalogContentMany_c
top::DatalogContent ::= a::DatalogContent b::DatalogContent_t
{
  top.unparse = a.unparse ++ b.lexeme;
}

concrete production datalogContentOne_c
top::DatalogContent ::= a::DatalogContent_t
{
  top.unparse = a.lexeme;
}

terminal DatalogContent_t /[^"}\ \n\r\t]+/ submits to Comments;
-- terminal DatalogRuleClauseName_t /([a-zA-Z])+/;
-- terminal DatalogRuleBody_t /([a-zA-Z\(\),\ ])+/;
-- terminal DatalogArrow ':-';

-- concrete production datalogRule_c
-- top::DatalogRule ::= head::DatalogRuleHead_t ':-' body::DatalogRuleBody_t '.'
-- {

-- }

-- nonterminal DatalogRule with unparse;
-- nonterminal DatalogRules with unparse;

-- concrete production datalogRulesSingle
-- top::DatalogRules ::= line::DatalogRule
-- {

-- }

-- concrete production datalogRulesCons
-- top::DatalogRules ::= line1::DatalogRule line2::DatalogRules
-- {
--   top.unparse = line1.unparse ++ "." ++ line2.unparse ;
-- }

