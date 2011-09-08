grammar silver:definition:type:syntax;


lexer class TYPE_KEYWORD dominates IDENTIFIER;

terminal Boolean_tkwd		'Boolean'	lexer classes {TYPE_KEYWORD};
terminal Decorated_tkwd		'Decorated'	lexer classes {TYPE_KEYWORD};
terminal Float_tkwd		'Float'		lexer classes {TYPE_KEYWORD};
terminal Function_tkwd		'Function'	lexer classes {TYPE_KEYWORD};
terminal Integer_tkwd		'Integer'	lexer classes {TYPE_KEYWORD};
terminal Production_tkwd	'Production'	lexer classes {TYPE_KEYWORD};
terminal String_tkwd		'String'	lexer classes {TYPE_KEYWORD};

-- Right now, I'm going to make type variables appears at tick-prefixed
-- just to simplify the changes that need to be done.
-- TODO: require types to be capitalized, so we can distinguish TVs from identifiers
-- that way?
--terminal TypeVariable_t		/[a-z]+/	lexer classes {IDENTIFIER};

