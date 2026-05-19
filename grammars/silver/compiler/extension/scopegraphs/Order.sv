grammar silver:compiler:extension:scopegraphs;

--


nonterminal SGOrderRoot with sgEnv, toExpr, toExpr2, errors, location, possibleLabs;

concrete production orderRoot
top::SGOrderRoot ::= o::SGOrder
{
  propagate errors, possibleLabs;

  top.toExpr = 
    if null(o.errors)
    then labMatcher
    else Silver_Expr { error("Should never be demanded!") };

  top.toExpr2 =
    if null(o.errors)
    then labMatcher2
    else Silver_Expr { error("Should never be demanded!") };

  nondecorated local labMatcher::Expr = caseExpr_c (
    'case',
    exprsCons(baseExpr(qName("l")),',',exprsSingle(baseExpr(qName("r")))),
    'of',
    terminal(Opt_Vbar_t, "", bogusLoc()),
    allCases,
    'end'
  );

  nondecorated local labMatcher2::Expr = caseExpr_c (
    'case',
    exprsCons(baseExpr(qName("l")),',',exprsSingle(baseExpr(qName("r")))),
    'of',
    terminal(Opt_Vbar_t, "", bogusLoc()),
    allCases2,
    'end'
  );

  nondecorated local allCases::MRuleList = foldr(
    \ord::(String, String) acc::MRuleList ->
      mRuleList_cons(
        matchRule_c(
          patternList_more(
            prodAppPattern(qName("label_" ++ ord.1), '(', patternList_nil(), ')'),
            ',',
            patternList_one(
              prodAppPattern(qName("label_" ++ ord.2), '(', patternList_nil(), ')')
            )
          ),
          terminal(Arrow_kwd, "->", bogusLoc()),
          Silver_Expr{ 1 }
        ),
        terminal(Vbar_kwd, "|", bogusLoc()),
        mRuleList_cons(
          matchRule_c(
            patternList_more(
              prodAppPattern(qName("label_" ++ ord.2), '(', patternList_nil(), ')'),
              ',',
              patternList_one(
                prodAppPattern(qName("label_" ++ ord.1), '(', patternList_nil(), ')')
              )
            ),
            terminal(Arrow_kwd, "->", bogusLoc()),
            Silver_Expr{ -1 }
          ),
          terminal(Vbar_kwd, "|", bogusLoc()),
          acc
        )
      ),
      mRuleList_one(defaultCase),
      o.ords
  );

  nondecorated local allCases2::MRuleList = foldr(
    \ord::(String, String) acc::MRuleList ->
      mRuleList_cons(
        matchRule_c(
          patternList_more(
            strPattern(terminal(String_t, "\"" ++ ord.1 ++ "\"")),
            ',',
            patternList_one(
              strPattern(terminal(String_t, "\"" ++ ord.2 ++ "\""))
            )
          ),
          terminal(Arrow_kwd, "->", bogusLoc()),
          Silver_Expr{ 1 }
        ),
        terminal(Vbar_kwd, "|", bogusLoc()),
        mRuleList_cons(
          matchRule_c(
            patternList_more(
              strPattern(terminal(String_t, "\"" ++ ord.2 ++ "\"")),
              ',',
              patternList_one(
                strPattern(terminal(String_t, "\"" ++ ord.1 ++ "\""))
              )
            ),
            terminal(Arrow_kwd, "->", bogusLoc()),
            Silver_Expr{ -1 }
          ),
          terminal(Vbar_kwd, "|", bogusLoc()),
          acc
        )
      ),
      mRuleList_one(defaultCase),
      o.ords
  );

  -- _, _ -> 0
  nondecorated local defaultCase::MatchRule =
    matchRule_c(
      patternList_more(wildcPattern('_'), ',', patternList_one(wildcPattern('_'))),
        terminal(Arrow_kwd, "->", bogusLoc()),
        Silver_Expr{0});

  o.gt = [];
  o.leftEqTo = [];
  o.labsUsed = [];
}

--

inherited attribute leftEqTo::[String];
inherited attribute gt::[String];
inherited attribute labsUsed::[String];
inherited attribute possibleLabs::[String];
synthesized attribute ords::[(String, String)];

nonterminal SGOrder with errors, location, leftEqTo, labsUsed, gt, ords, possibleLabs;

propagate errors, possibleLabs on SGOrder;

concrete production sgOrderCons
top::SGOrder ::= h::SGOrderOne '<' t::SGOrder
{
  h.labsUsed = top.labsUsed;

  t.gt = h.lab::(top.leftEqTo ++ top.gt);
  t.leftEqTo = [];
  t.labsUsed = h.lab::top.labsUsed;

  top.ords = map(\l::String -> (h.lab, l), top.gt) ++ t.ords;
}

concrete production sgOrderConsEq
top::SGOrder ::= h::SGOrderOne '=' t::SGOrder
{
  h.labsUsed = top.labsUsed;

  t.gt = top.gt;
  t.leftEqTo = h.lab::top.leftEqTo;
  t.labsUsed = h.lab::top.labsUsed;

  top.ords = map(\l::String -> (h.lab, l), top.gt) ++ t.ords;
}

concrete production sgOrderLast
top::SGOrder ::= h::SGOrderOne
{
  propagate labsUsed;
  top.ords = map(\l::String -> (h.lab, l), top.gt);
}

--

synthesized attribute lab::String;

nonterminal SGOrderOne with errors, location, lab, labsUsed, possibleLabs;

concrete production sgOrderOne
top::SGOrderOne ::= SGRegexBacktick_t l::Name
{
  -- multiple uses of same label
  top.errors :=
    if contains(top.lab, top.labsUsed)
    then [errFromOrigin(l, "Multiple uses of label '`" ++ top.lab ++ "' in query label order.")]
    else [];

  -- label doesn't exist
  top.errors <-
    if !contains(top.lab, top.possibleLabs)
    then [errFromOrigin(l, "Unknown label '`" ++ top.lab ++ "' in query label order.")]
    else [];

  top.lab = l.name;
}
