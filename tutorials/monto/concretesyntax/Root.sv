grammar monto:concretesyntax;


nonterminal Root_c with ast<abs:Root>;

concrete production root
r::Root_c ::= e::Expr_c
{
 r.ast = abs:root(e.ast);
}
