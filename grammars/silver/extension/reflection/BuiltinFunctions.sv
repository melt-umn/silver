grammar silver:extension:reflection;

imports silver:definition:core;
imports silver:definition:type;
imports silver:definition:env;
imports silver:extension:patternmatching;
imports silver:modification:let_fix;

terminal Deserialize_kwd 'deserialize' lexer classes {BUILTIN,RESERVED};

concrete production deserializeFunction
top::Expr ::= 'deserialize' '(' fileName::Expr ',' text::Expr ')'
{
  top.pp = s"deserialize(${fileName.pp}, ${text.pp})";
  
  local errCheck1::TypeCheck = check(fileName.typerep, stringType());
  errCheck1.finalSubst = top.finalSubst;
  local errCheck2::TypeCheck = check(text.typerep, stringType());
  errCheck2.finalSubst = top.finalSubst;
  
  fileName.downSubst = top.downSubst;
  text.downSubst = fileName.upSubst;
  errCheck1.downSubst = text.upSubst;
  errCheck2.downSubst = errCheck1.upSubst;
  --top.upSubst = errCheck2.upSubst;
  
  local localErrors::[Message] =
    (if errCheck1.typeerror
     then [err(fileName.location, "First operand to 'deserialize(fileName, text)' must be a String, instead it is " ++ errCheck1.leftpp)]
     else []) ++
    (if errCheck2.typeerror
     then [err(text.location, "Second operand to 'deserialize(fileName, text)' must be a String, instead it is " ++ errCheck2.leftpp)]
     else []);
  
  local fwrd::Expr =
    Silver_Expr {
      case deserializeAST($Expr {exprRef(fileName, location=builtin)}, $Expr {exprRef(text, location=builtin)}) of
      | left(msg) -> left(msg)
      | right(ast) -> reify(ast)
      end
    };
  
  forwards to if !null(localErrors) then errorExpr(localErrors, location=top.location) else fwrd;
}

global builtin::Location = builtinLoc("reflection");
