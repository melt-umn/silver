grammar silver:definition:core;

temp_imp_ide_font font_comments color(82, 141, 115) italic; -- Good: same as java
temp_imp_ide_font font_literal color(50, 50, 250); -- BAD
temp_imp_ide_font font_keyword color(123, 0, 82) bold; -- Good: same as java
temp_imp_ide_font font_modword color(41,95,148) bold; -- maybe good? Unusual but looks good
temp_imp_ide_font font_type color(41,95,148); -- type coloring


lexer class IDENTIFIER;
lexer class RESERVED dominates IDENTIFIER;

lexer class COMMENT font = font_comments;
lexer class LITERAL font = font_literal;
lexer class KEYWORD font = font_keyword;
lexer class MODSTMT font = font_modword;
lexer class SPECOP  font = font_keyword;
lexer class BUILTIN font = font_keyword;
lexer class TYPE    font = font_type;

terminal As_kwd       'as'      lexer classes {MODSTMT,RESERVED};
terminal Exports_kwd  'exports' lexer classes {MODSTMT};
terminal Grammar_kwd  'grammar' lexer classes {MODSTMT,RESERVED};
terminal Hiding_kwd   'hiding'  lexer classes {MODSTMT,RESERVED};
terminal Import_kwd   'import'  lexer classes {MODSTMT};
terminal Imports_kwd  'imports' lexer classes {MODSTMT};
terminal Only_kwd     'only'    lexer classes {MODSTMT,RESERVED};
terminal Optional_kwd 'option'  lexer classes {MODSTMT};
-- TODO with 

-- TODO A substantial fraction of these don't need to be reserved!
terminal Abstract_kwd    'abstract'     lexer classes {KEYWORD,RESERVED};
terminal Aspect_kwd      'aspect'       lexer classes {KEYWORD,RESERVED};
terminal Attribute_kwd   'attribute'    lexer classes {KEYWORD,RESERVED};
terminal Closed_kwd      'closed'       lexer classes {KEYWORD};
terminal Concrete_kwd    'concrete'     lexer classes {KEYWORD,RESERVED};
terminal Decorate_kwd    'decorate'     lexer classes {KEYWORD,RESERVED};
terminal Else_kwd        'else'         lexer classes {KEYWORD,RESERVED}, precedence = 4;
terminal Forwarding_kwd  'forwarding'   lexer classes {KEYWORD,RESERVED};
terminal Forward_kwd     'forward'      lexer classes {KEYWORD,RESERVED};
terminal Forwards_kwd    'forwards'     lexer classes {KEYWORD,RESERVED};
terminal Function_kwd    'function'     lexer classes {KEYWORD,RESERVED};
terminal If_kwd          'if'           lexer classes {KEYWORD,RESERVED};
terminal Inherited_kwd   'inherited'    lexer classes {KEYWORD,RESERVED};
terminal Local_kwd       'local'        lexer classes {KEYWORD,RESERVED};
terminal New_kwd         'new'          lexer classes {KEYWORD,RESERVED};
terminal NonTerminal_kwd 'nonterminal'  lexer classes {KEYWORD,RESERVED};
terminal Occurs_kwd      'occurs'       lexer classes {KEYWORD,RESERVED};
terminal On_kwd          'on'           lexer classes {KEYWORD,RESERVED};
terminal Production_kwd  'production'   lexer classes {KEYWORD,RESERVED};
terminal Return_kwd      'return'       lexer classes {KEYWORD,RESERVED};
terminal Synthesized_kwd 'synthesized'  lexer classes {KEYWORD,RESERVED};
terminal Terminal_kwd    'terminal'     lexer classes {KEYWORD,RESERVED};
terminal Then_kwd        'then'         lexer classes {KEYWORD,RESERVED};
terminal To_kwd          'to'           lexer classes {KEYWORD,RESERVED};
terminal With_kwd        'with'         lexer classes {KEYWORD,RESERVED};
terminal Global_kwd      'global'       lexer classes {KEYWORD,RESERVED};

terminal Length_kwd    'length'    lexer classes {BUILTIN,RESERVED};
terminal ToFloat_kwd   'toFloat'   lexer classes {BUILTIN,RESERVED};
terminal ToInt_kwd     'toInt'     lexer classes {BUILTIN,RESERVED};
terminal ToString_kwd  'toString'  lexer classes {BUILTIN,RESERVED};

terminal Comma_t       ','  precedence = 4;
terminal Or_t          '||' precedence = 5, association = left;
terminal And_t         '&&' precedence = 6, association = left;
terminal Not_t         '!'  precedence = 7;
terminal GT_t          '>'  precedence = 9, association = left;
terminal LT_t          '<'  precedence = 9, association = left;
terminal GTEQ_t        '>=' precedence = 9, association = left;
terminal LTEQ_t        '<=' precedence = 9, association = left;
terminal EQEQ_t        '==' precedence = 9, association = left;
terminal NEQ_t         '!=' precedence = 9, association = left;
terminal PlusPlus_t    '++' precedence = 10, association = right; -- right because that's generally more efficient.
terminal Plus_t        '+'  precedence = 11, association = left;
terminal Minus_t       '-'  precedence = 11, association = left;
terminal Multiply_t    '*'  precedence = 12, association = left;
terminal Divide_t      '/'  precedence = 12, association = left;
terminal Modulus_t     '%'  precedence = 12, association = left;
terminal ColonColon_t  '::' precedence = 14, association = right; -- HasType AND cons. right due to cons.
terminal LParen_t      '('  precedence = 24;
terminal RParen_t      ')'  ;
terminal LCurly_t      '{'  ;
terminal RCurly_t      '}'  ;
terminal Dot_t         '.'  precedence = 25, association = left;
terminal Semi_t        ';'  ;
terminal Colon_t       ':'  ;
terminal UnderScore_t  '_'  ;

terminal CCEQ_t        '::=' lexer classes {SPECOP};
terminal Equal_t       '='   lexer classes {SPECOP};

-- Unused infix operators: ~ ` @ # % ^ & | \
-- $ is used by convenience.

 -- this is a very careful regex. beware:
ignore terminal BlockComments /\{\-([^\-]|\-+[^\}\-])*\-+\}/ lexer classes {COMMENT};
ignore terminal Comments /([\-][\-].*)/ lexer classes {COMMENT};

ignore terminal WhiteSpace /[\n\t\ ]+/;

terminal IdLower_t /[a-z][A-Za-z0-9\_]*/ lexer classes {IDENTIFIER};
terminal IdUpper_t /[A-Z][A-Za-z0-9\_]*/ lexer classes {IDENTIFIER};

terminal True_kwd  'true'   lexer classes {LITERAL,RESERVED};
terminal False_kwd 'false'  lexer classes {LITERAL,RESERVED};
terminal Int_t     /[\-]?[0-9]+/ lexer classes {LITERAL};
terminal Float_t   /[\-]?[0-9]+[\.][0-9]+/ lexer classes {LITERAL};
terminal String_t  /[\"]([^\n\"\\]|[\\][\"]|[\\][\\]|[\\]n|[\\]r|[\\]t)*[\"]/ lexer classes {LITERAL};

