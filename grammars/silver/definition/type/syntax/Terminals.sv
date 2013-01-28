grammar silver:definition:type:syntax;


lexer class TYPE_KEYWORD dominates IDENTIFIER;

terminal Boolean_tkwd		'Boolean'	lexer classes {TYPE_KEYWORD};
terminal Decorated_tkwd		'Decorated'	lexer classes {TYPE_KEYWORD};
terminal Float_tkwd		'Float'		lexer classes {TYPE_KEYWORD};
terminal Integer_tkwd		'Integer'	lexer classes {TYPE_KEYWORD};
terminal String_tkwd		'String'	lexer classes {TYPE_KEYWORD};


