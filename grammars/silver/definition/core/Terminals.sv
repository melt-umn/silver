grammar silver:definition:core;

lexer class IDENTIFIER;
lexer class KEYWORD dominates IDENTIFIER;
lexer class BUILTIN dominates IDENTIFIER;

-- TODO: there's a bunch of things here that really should be 'keywords' really
terminal Abstract_kwd		'abstract'	lexer classes {KEYWORD};
terminal As_kwd			'as'		lexer classes {KEYWORD};
terminal Aspect_kwd		'aspect'	lexer classes {KEYWORD};
terminal Attribute_kwd		'attribute'	lexer classes {KEYWORD};
terminal Closed_kwd             'closed';
terminal Concrete_kwd		'concrete'	lexer classes {KEYWORD};
terminal Decorate_kwd		'decorate'	lexer classes {KEYWORD};
terminal Else_kwd		'else'		lexer classes {KEYWORD}, precedence = 4;
terminal Exports_kwd		'exports'	lexer classes {KEYWORD};
terminal False_kwd		'false'		lexer classes {KEYWORD};
terminal Forwarding_kwd		'forwarding'	lexer classes {KEYWORD};
terminal Forward_kwd		'forward'	lexer classes {KEYWORD};
terminal Forwards_kwd		'forwards'	lexer classes {KEYWORD};
terminal Function_kwd		'function'	lexer classes {KEYWORD};
terminal Grammar_kwd		'grammar'	lexer classes {KEYWORD};
terminal Hiding_kwd		'hiding'	lexer classes {KEYWORD};
terminal If_kwd			'if'		lexer classes {KEYWORD};
terminal Import_kwd		'import'	lexer classes {KEYWORD};
terminal Imports_kwd		'imports'	lexer classes {KEYWORD};
terminal Inherited_kwd		'inherited'	lexer classes {KEYWORD};
terminal Local_kwd		'local'		lexer classes {KEYWORD};
terminal New_kwd		'new'		lexer classes {KEYWORD};
terminal NonTerminal_kwd	'nonterminal'	lexer classes {KEYWORD};
terminal Occurs_kwd		'occurs'	lexer classes {KEYWORD};
terminal On_kwd			'on'		lexer classes {KEYWORD};
terminal Only_kwd		'only'		lexer classes {KEYWORD};
terminal Optional_kwd           'option';
terminal Production_kwd		'production'	lexer classes {KEYWORD};
terminal Return_kwd		'return'	lexer classes {KEYWORD};
terminal Synthesized_kwd	'synthesized'	lexer classes {KEYWORD};
terminal Terminal_kwd		'terminal'	lexer classes {KEYWORD};
terminal Then_kwd		'then'		lexer classes {KEYWORD};
terminal To_kwd			'to'		lexer classes {KEYWORD};
terminal True_kwd		'true'		lexer classes {KEYWORD};
terminal With_kwd		'with'		lexer classes {KEYWORD};
terminal Global_kwd		'global'	lexer classes {KEYWORD};

terminal Length_kwd		'length'	lexer classes {BUILTIN};
terminal ToFloat_kwd		'toFloat'	lexer classes {BUILTIN};
terminal ToInt_kwd		'toInt'		lexer classes {BUILTIN};
terminal ToString_kwd		'toString'	lexer classes {BUILTIN};

terminal Comma_t	','  precedence = 4;
terminal Or_t		'||' precedence = 5, association = left;
terminal And_t		'&&' precedence = 6, association = left;
terminal Not_t		'!'  precedence = 7;
terminal GT_t		'>'  precedence = 9, association = left;
terminal LT_t		'<'  precedence = 9, association = left;
terminal GTEQ_t		'>=' precedence = 9, association = left;
terminal LTEQ_t		'<=' precedence = 9, association = left;
terminal EQEQ_t		'==' precedence = 9, association = left;
terminal NEQ_t		'!=' precedence = 9, association = left;
terminal PlusPlus_t	'++' precedence = 10, association = right; -- right because that's generally more efficient.
terminal Plus_t		'+'  precedence = 11, association = left;
terminal Minus_t	'-'  precedence = 11, association = left;
terminal Multiply_t	'*'  precedence = 12, association = left;
terminal Divide_t	'/'  precedence = 12, association = left;
terminal Modulus_t      '%'  precedence = 12, association = left;
terminal ColonColon_t	'::' precedence = 14, association = right; -- HasType AND cons. right due to cons.
terminal LParen_t	'('  precedence = 24;
terminal RParen_t	')'  ;
terminal LCurly_t	'{'  ;
terminal RCurly_t	'}'  ;
terminal Dot_t		'.'  precedence = 25, association = left;
terminal CCEQ_t		'::=';
terminal Semi_t		';'  ;
terminal Colon_t	':'  ;
terminal Equal_t	'='  ;
terminal UnderScore_t	'_'  ;

-- Unused infix operators: ~ ` @ # % ^ & | \
-- $ is used by convenience.

ignore terminal Comments /([\-][\-].*)/;
ignore terminal BlockComments /\{\-([^\-]|\-+[^\}\-])*\-+\}/; --this is a very careful regex. beware.
ignore terminal WhiteSpace /[\n\t\ ]+/;

terminal IdLower_t /[a-z][A-Za-z0-9\_]*/ lexer classes {IDENTIFIER};
terminal IdUpper_t /[A-Z][A-Za-z0-9\_]*/ lexer classes {IDENTIFIER};

terminal Int_t /[0-9]+/;
terminal Float_t /[0-9]+[\.][0-9]+/;
terminal String_t /[\"]([^\n\"\\]|[\\][\"]|[\\][\\]|[\\]n|[\\]r|[\\]t)*[\"]/;

