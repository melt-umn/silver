grammar simple:concretesyntax;

{--
 - A statement declaring a variable and its type.
 -}
closed nonterminal Decl with unparse, silver:langutil:location:location, ast<ast:Decl>;

concrete productions d::Decl
 | te::TypeExpr id::Id ';'  { d.unparse = te.unparse ++ " " ++ id.lexeme ++ "; \n";
                                   d.ast = ast:decl(te.ast, name(id)); }

{--
 - A concrete expression denoting a type
 -}
nonterminal TypeExpr with unparse, silver:langutil:location:location, ast<ast:TypeExpr>;

concrete productions t::TypeExpr
 | 'Integer'  { t.unparse = "Integer";
                t.ast = ast:typeExprInteger(); }

 | 'Float'    { t.unparse = "Float";
                t.ast = ast:typeExprFloat(); }
 | 'Boolean'  { t.unparse = "Boolean";
                t.ast = ast:typeExprBoolean(); }
