grammar silver:compiler:definition:type:syntax;

imports silver:langutil:lsp as lsp;

-- '<' has precedence 9, assoc = left

terminal Arrow_t '->' association = right, lexer classes {SPECOP};

-- Ambiguity at '{' in production signature between an inh set type and the production body.
-- Since just `{inh}` doesn't make any sense in a production signature,
-- prefer '{' as the start of a production body. 
terminal InhSetLCurly_t /{/;
disambiguate LCurly_t, InhSetLCurly_t { pluck LCurly_t; }

terminal Boolean_tkwd    'Boolean'    lexer classes {TYPE,RESERVED};
terminal Decorated_tkwd  'Decorated'  lexer classes {TYPE,RESERVED}, precedence=1;
terminal PartiallyDecorated_tkwd 'PartiallyDecorated' lexer classes {TYPE,RESERVED}, precedence=1;
terminal Float_tkwd      'Float'      lexer classes {TYPE,RESERVED};
terminal Integer_tkwd    'Integer'    lexer classes {TYPE,RESERVED};
terminal String_tkwd     'String'     lexer classes {TYPE,RESERVED};
terminal TerminalId_tkwd 'TerminalId' lexer classes {TYPE,RESERVED};
terminal InhSet_tkwd     'InhSet'     lexer classes {TYPE}; -- Well, actually a kind

terminal RuntimeTypeable_kwd 'runtimeTypeable' lexer classes {KEYWORD,RESERVED};
terminal Subset_kwd          'subset'          lexer classes {KEYWORD};
terminal TypeError_kwd       'typeError'       lexer classes {KEYWORD};

terminal IdTypeVar_t /[a-z][A-Za-z0-9\_]*/ lexer classes {lsp:TypeParameter};

-- Avoid making these reserved, for now
disambiguate Subset_kwd, IdLower_t { pluck Subset_kwd; }
disambiguate TypeError_kwd, IdLower_t { pluck TypeError_kwd; }
