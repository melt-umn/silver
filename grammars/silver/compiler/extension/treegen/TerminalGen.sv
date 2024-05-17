grammar silver:compiler:extension:treegen;

imports silver:core hiding empty, alt;
imports silver:regex;
imports silver:compiler:metatranslation;
imports silver:reflect;

import silver:util:treeset as set;  -- needed for freeVars equation

terminal GenArbTerminal_t 'genArbTerminal' lexer classes {KEYWORD, RESERVED};

-- Note that this construct ignores lexical dominates/submits to relationships
-- with other terminals, since we don't have a parser spec/syntax AST to extract
-- that information.
concrete production genArbTerminalNoLocExpr
top::Expr ::= 'genArbTerminal' '(' te::TypeExpr ',' '_' ')'
{
  top.unparse = s"genArbTerminal(${te.unparse}, _)";
  propagate grammarName, env, flowEnv, freeVars;
  
  nondecorated local regex::Regex =
    case getTypeDcl(te.typerep.typeName, top.env) of
    | termDcl(_, r, _, _) :: _ -> new(r)
    | _ -> empty()
    end;
  local genRepeatProb::Float =
    case getTypeDcl(te.typerep.typeName, top.env) of
    | termDcl(_, _, _, just(grp)) :: _ -> grp
    | _ -> 0.75
    end;

  forwards to
    if null(getAttrDcl("silver:regex:genArbMatch", top.env))
    then errorExpr([errFromOrigin(top, "Generation of arbitrary terminal values requires import of silver:regex")])
    else Silver_Expr {
      let genLexeme::RandomGen<String> =
        decorate $Expr{translate(reflect(regex))} with {
          starProb = $Expr{floatConst(terminal(Float_t, toString(genRepeatProb)))};
          altCountIn = 0;
        }.genArbMatch
      in \ loc::silver:core:Location -> silver:core:map(\ lexeme::String -> terminal($TypeExpr{@te}, lexeme, loc), genLexeme)
      end
    };
}

concrete production genArbTerminalExpr
top::Expr ::= 'genArbTerminal' '(' te::TypeExpr ',' loc::Expr ')'
{
  top.unparse = s"genArbTerminal(${te.unparse}, ${loc.unparse})";
  forwards to Silver_Expr { genArbTerminal($TypeExpr{@te}, _)($Expr{@loc}) };
}
