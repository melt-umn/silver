grammar xrobots:abstractsyntax;
import xrobots:terminals;

nonterminal Trans
                with pp, 
                     htrans,
                     stack,
                     nameIn,
                     tabs,
                     errors;

abstract production transition
t:: Trans ::= ut::Under_t cond::Expr n::Id_t args::[Expr]
{
 t.pp            = "Transition for " ++ t.nameIn.lexeme ;
 t.htrans        = t.tabs ++ "trans" ++ t.nameIn.lexeme ++ "Fnc :: " ++
                                "Stack Frame -> StackPointer -> " ++
                                 "(Bool, Stack Frame)\n" ++ 
                    t.tabs ++ "trans" ++ t.nameIn.lexeme ++ "Fnc "
                             ++ "stack sp = (True, stack)" ++
 "\n------------------------------------------------------------------------\n";
 t.errors        = if !equal_types(cond.type, boolType())
                    then mk_error("The condition of this transition is not "
				  ++ "of type boolean.",
				  n.line, n.column)
                        else if !isDefined(t.stack, n)
                              then mk_error("The target behavior "++
					    n.lexeme ++ " is undefined.",
					    n.line, n.column)
                               else case getType(t.stack, n) of
                                     behaviorType(tl) -> 
                                              check_args(n, tl, args)
                                     end;
 
}

function check_args
[String] ::= n::Id_t tl::[Type] args::[Expr]
{
 return if length(tl) == 0 && length(args) == 0
        then []
        else if length(tl) == 0
             then mk_error("Behavior " ++ n.lexeme ++  
                           "was given too many argumnts.", 
                            n.line, n.column)
             else if length(args) == 0 
                     then mk_error("Behavior " ++ n.lexeme ++  
                           "was given too few argumnts.", 
                            n.line, n.column)
                        else if !equal_types(head(tl), (head(args)).type)
                             then mk_error("A " ++ (head(args)).pp ++
					   " was supplied to " ++ n.lexeme ++
					   " when a " ++ (head(tl)).pp ++
					   " was required.", n.line, n.column)
                        else [];
}