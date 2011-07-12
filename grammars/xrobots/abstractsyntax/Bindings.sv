grammar xrobots:abstractsyntax;
import xrobots:terminals;

nonterminal Binding with name, pp, type;

synthesized attribute bindings :: [Binding] ;

function printBindings
String ::= bl:: [Binding]
{
 return if null(bl)
        then ""
        else (head(bl)).pp ++ "\t" ++ printBindings(tail(bl));
}

abstract production newBinding
b::Binding ::= n::Id_t t::Type
{
 b.pp   = "Binding(name: " ++ n.lexeme ++ ", Type: " ++ t.pp ++ ")\n";
 b.name = n;
 b.type = t;
}	       
