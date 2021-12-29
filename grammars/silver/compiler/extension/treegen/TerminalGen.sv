grammar silver:compiler:extension:treegen;

import silver:compiler:metatranslation;
import silver:reflect;
import silver:regex;

terminal GenArbTerminal_t 'genArbTerminal' lexer classes {KEYWORD, RESERVED};

concrete production genArbTerminalNoLocExpr
top::Expr ::= 'genArbTerminal' '(' te::TypeExpr ',' starProb::Expr ',' '_' ')'
{
  top.unparse = s"genArbTerminal(${te.unparse}, ${starProb.unparse}, _)";
  
  local regex::Regex =
    case getTypeDcl(te.typerep.typeName, top.env) of
    | termDcl(_, r, _) :: _ -> r
    | _ -> empty()
    end;
  
  forwards to
    if null(getAttrDcl("silver:regex:genArbMatch", top.env))
    then errorExpr([err(top.location, "Generation of arbitrary terminal values requires import of silver:regex")], location=top.location)
    else Silver_Expr {
      let genLexeme::RandomGen<String> =
        decorate $Expr{translate(top.location, reflect(new(regex)))} with { starProb = $Expr{starProb}; altCountIn = 0; }.genArbMatch
      in \ loc::silver:core:Location -> silver:core:map(\ lexeme::String -> terminal($TypeExpr{te}, lexeme, loc), genLexeme)
      end
    };
}

concrete production genArbTerminalExpr
top::Expr ::= 'genArbTerminal' '(' te::TypeExpr ',' starProb::Expr ',' loc::Expr ')'
{
  top.unparse = s"genArbTerminal(${te.unparse}, ${starProb.unparse}, ${loc.unparse})";
  forwards to
    mkFunctionInvocation(top.location,
      genArbTerminalNoLocExpr('genArbTerminal', '(', te, ',', starProb, ',', '_', ')', location=top.location),
      [loc]);
}
