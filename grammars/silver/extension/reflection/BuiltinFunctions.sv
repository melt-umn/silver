grammar silver:extension:reflection;

imports silver:definition:core;
imports silver:definition:type;
imports silver:definition:env;
imports silver:extension:patternmatching;
imports silver:modification:let_fix;

terminal ReifyUnchecked_kwd 'reifyUnchecked' lexer classes {BUILTIN,RESERVED};
terminal Deserialize_kwd 'deserialize' lexer classes {BUILTIN,RESERVED};

-- Useful shorthand in cases when we want to immediately raise an error on failure 
concrete production reifyUncheckedFunction
top::Expr ::= 'reifyUnchecked' '(' e::Expr ')'
{
  forwards to
    Silver_Expr {
      case reify($Expr{e}) of
      | left(msg) -> error(msg)
      | right(a) -> a
      end
    };
}

{--
 - This production deserializes a string to what it represents, in contrast to
 - `reify` which "deserializes" an `AST` to what it represents, and in contrast to
 - `deserializeAST` which deserializes a string to an `AST`.
 - i.e. this function is `reify . deserializeAST` so to speak.
 -
 - This also has to be a built-in function because reification requires an
 - explicit concrete type at its call site, which makes it difficult to write ordinary
 - functions for. Something we might someday be able to solve with a typeclass?
-}
concrete production deserializeFunction
top::Expr ::= 'deserialize' '(' fileName::Expr ',' text::Expr ')'
{
  top.unparse = s"deserialize(${fileName.unparse}, ${text.unparse})";
  
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
