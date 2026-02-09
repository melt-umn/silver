grammar silver:compiler:extension:scopegraphs;

imports silver:compiler:extension:patternmatching;

--

terminal SGOrderBraceLeft_t '{{';
terminal SGOrderBraceRight_t '}}';

--

concrete production sgOrder_c
top::Expr ::= RegexSlash_t r::SGOrderOption RegexSlash_t
{

  -- | _, _ -> 0
  nondecorated local defaultCase::MatchRule =
    matchRule_c(
      patternList_more(
        wildcPattern('_'),
        ',',
        patternList_one(
          wildcPattern('_')
        )
      ),
      terminal(Arrow_kwd, "->", bogusLoc()),
      Silver_Expr{0}
    )
  ;

  nondecorated local allCases::MRuleList =
    foldr(
      (
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
          )
      ),
      mRuleList_one(defaultCase),
      r.ords
    )
  ;

  nondecorated local labCase::Expr =
    caseExpr_c(
      'case',
      exprsCons(
        baseExpr(qName("left")),
        ',',
        exprsSingle(
          baseExpr(qName("right"))
        )
      ),
      'of',
      terminal(Opt_Vbar_t, "", bogusLoc()),
      allCases,
      'end'
    )
  ;

  forwards to
    Silver_Expr{
      \left::Label<{lex, var, mod, imp}> right::Label<{lex, var, mod, imp}> ->
        $Expr{labCase}
    }
  ;

}

--

synthesized attribute ords::[(String, String)];

nonterminal SGOrderOption with ords;

concrete production orderSome_c
top::SGOrderOption ::= o::SGOrder
{
  top.ords = o.ords;
}

concrete production orderNone_c
top::SGOrderOption ::=
{
  top.ords = [];
}

--

nonterminal SGOrder with ords;

concrete production sgOrderCons_c
top::SGOrder ::= h::SGOrderOne ',' t::SGOrder
{
  top.ords = h.ords ++ t.ords;
}

concrete production sgOrderLast_c
top::SGOrder ::= h::SGOrderOne
{
  top.ords = h.ords;
}

--

nonterminal SGOrderOne with ords;

concrete production sgOrderOne_c
top::SGOrderOne ::= l::IdLower_t '>' r::IdLower_t
{
  top.ords = [(l.lexeme, r.lexeme)];
}