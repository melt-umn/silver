grammar silver:definition:type:syntax;

import silver:definition:core only IDENTIFIER;

lexer class TYPE_KEYWORD dominates IDENTIFIER;

terminal Boolean_kwd		'Boolean'	lexer classes {TYPE_KEYWORD};
terminal Decorated_kwd		'Decorated'	lexer classes {TYPE_KEYWORD};
terminal Float_kwd		'Float'		lexer classes {TYPE_KEYWORD};
terminal Function_kwd		'Function'	lexer classes {TYPE_KEYWORD};
terminal Integer_kwd		'Integer'	lexer classes {TYPE_KEYWORD};
terminal Production_kwd		'Production'	lexer classes {TYPE_KEYWORD};
terminal String_kwd		'String'	lexer classes {TYPE_KEYWORD};


