grammar silver:compiler:definition:type:syntax;

-- '<' has precedence 9, assoc = left

terminal Boolean_tkwd    'Boolean'    lexer classes {TYPE,RESERVED};
terminal Decorated_tkwd  'Decorated'  lexer classes {TYPE,RESERVED}, precedence=1;
terminal Float_tkwd      'Float'      lexer classes {TYPE,RESERVED};
terminal Integer_tkwd    'Integer'    lexer classes {TYPE,RESERVED};
terminal String_tkwd     'String'     lexer classes {TYPE,RESERVED};
terminal TerminalId_tkwd 'TerminalId' lexer classes {TYPE,RESERVED};

terminal RuntimeTypeable_kwd 'runtimeTypeable' lexer classes {KEYWORD,RESERVED};
