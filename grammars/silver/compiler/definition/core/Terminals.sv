grammar silver:compiler:definition:core;

imports silver:langutil:lsp as lsp;

lexer class Silver
  prefix separator ":";

lexer class IDENTIFIER extends Silver;
lexer class RESERVED dominates IDENTIFIER;

lexer class COMMENT extends {Silver, lsp:Comment};
lexer class LITERAL extends Silver;
lexer class KEYWORD extends {Silver, lsp:Keyword};
lexer class MODSTMT extends {Silver};
lexer class OP      extends {Silver, lsp:Operator};
lexer class SPECOP  extends {Silver, lsp:Keyword};
lexer class BUILTIN extends {Silver, lsp:Keyword};
lexer class TYPE    extends {Silver, lsp:Type};
lexer class MODIFIER extends {Silver, lsp:Modifier};

terminal As_kwd       'as'      lexer classes {MODSTMT, lsp:Modifier, RESERVED};
terminal Exports_kwd  'exports' lexer classes {MODSTMT, lsp:Keyword};
terminal Grammar_kwd  'grammar' lexer classes {MODSTMT, lsp:Keyword, RESERVED};
terminal Hiding_kwd   'hiding'  lexer classes {MODSTMT, lsp:Modifier, RESERVED};
terminal Import_kwd   'import'  lexer classes {MODSTMT, lsp:Keyword};
terminal Imports_kwd  'imports' lexer classes {MODSTMT, lsp:Keyword};
terminal Only_kwd     'only'    lexer classes {MODSTMT, lsp:Modifier, RESERVED};
terminal Optional_kwd 'option'  lexer classes {MODSTMT, lsp:Keyword};
-- TODO with 

-- TODO A substantial fraction of these don't need to be reserved!
terminal Abstract_kwd    'abstract'     lexer classes {KEYWORD,RESERVED};
terminal Aspect_kwd      'aspect'       lexer classes {KEYWORD,RESERVED};
terminal Attribute_kwd   'attribute'    lexer classes {KEYWORD,RESERVED};
terminal Class_kwd       'class'        lexer classes {KEYWORD};
terminal Closed_kwd      'closed'       lexer classes {KEYWORD};
terminal Tracked_kwd     'tracked'      lexer classes {KEYWORD};
terminal Concrete_kwd    'concrete'     lexer classes {KEYWORD,RESERVED};
terminal Decorate_kwd    'decorate'     lexer classes {KEYWORD,RESERVED};
terminal Else_kwd        'else'         lexer classes {KEYWORD,RESERVED}, precedence = 4, association = left; -- Association needed for dangling else in action code.
terminal End_kwd         'end'          lexer classes {KEYWORD,RESERVED};
terminal Forwarding_kwd  'forwarding'   lexer classes {KEYWORD,RESERVED};
terminal Forward_kwd     'forward'      lexer classes {KEYWORD,RESERVED};
terminal Forwards_kwd    'forwards'     lexer classes {KEYWORD,RESERVED};
terminal Function_kwd    'function'     lexer classes {KEYWORD,RESERVED};
terminal Global_kwd      'global'       lexer classes {KEYWORD,RESERVED};
terminal If_kwd          'if'           lexer classes {KEYWORD,RESERVED};
terminal Inherited_kwd   'inherited'    lexer classes {KEYWORD,RESERVED};
terminal Instance_kwd    'instance'     lexer classes {KEYWORD};
terminal Local_kwd       'local'        lexer classes {KEYWORD,RESERVED};
terminal NonTerminal_kwd 'nonterminal'  lexer classes {KEYWORD,RESERVED};
terminal Occurs_kwd      'occurs'       lexer classes {KEYWORD,RESERVED};
terminal On_kwd          'on'           lexer classes {KEYWORD,RESERVED};
terminal Production_kwd  'production'   lexer classes {KEYWORD,RESERVED};
terminal Return_kwd      'return'       lexer classes {KEYWORD,RESERVED};
terminal Synthesized_kwd 'synthesized'  lexer classes {KEYWORD,RESERVED};
terminal Terminal_kwd    'terminal'     lexer classes {KEYWORD,RESERVED};
terminal Then_kwd        'then'         lexer classes {KEYWORD,RESERVED};
terminal To_kwd          'to'           lexer classes {KEYWORD,RESERVED};
terminal Type_t          'type'         lexer classes {KEYWORD};
terminal Undecorates_t   'undecorates'  lexer classes {KEYWORD,RESERVED};
terminal With_kwd        'with'         lexer classes {KEYWORD,RESERVED}, precedence = 3; -- Precedence to fix Decorated Decorated Expr with {}, which is a semantic error either way

terminal AttachNote_kwd 'attachNote' lexer classes {BUILTIN,RESERVED};

terminal DecSite_t     '@'  lexer classes {OP}, precedence = 2;
terminal Comma_t       ','  precedence = 4;
terminal Or_t          '||' lexer classes {OP}, precedence = 5, association = left;
terminal And_t         '&&' lexer classes {OP}, precedence = 6, association = left;
terminal Not_t         '!'  lexer classes {OP}, precedence = 7;
terminal GT_t          '>'  lexer classes {OP}, precedence = 9, association = left;
terminal LT_t          '<'  lexer classes {OP}, precedence = 9, association = left;
terminal GTEQ_t        '>=' lexer classes {OP}, precedence = 9, association = left;
terminal LTEQ_t        '<=' lexer classes {OP}, precedence = 9, association = left;
terminal EQEQ_t        '==' lexer classes {OP}, precedence = 9, association = left;
terminal NEQ_t         '!=' lexer classes {OP}, precedence = 9, association = left;
terminal PlusPlus_t    '++' lexer classes {OP}, precedence = 10, association = right; -- right because that's generally more efficient.
terminal Plus_t        '+'  lexer classes {OP}, precedence = 11, association = left;
terminal Minus_t       '-'  lexer classes {OP}, precedence = 11, association = left;
terminal Multiply_t    '*'  lexer classes {OP}, precedence = 12, association = left;
terminal Divide_t      '/'  lexer classes {OP}, precedence = 12, association = left;
terminal Modulus_t     '%'  lexer classes {OP}, precedence = 12, association = left;
terminal ColonColon_t  '::' lexer classes {OP}, precedence = 14, association = right; -- HasType AND cons. right due to cons.
terminal LParen_t      '('  precedence = 24;
terminal RParen_t      ')'  precedence = 1, association = left; -- Precedence and association eeded for dangling else in action code.
terminal LCurly_t      '{'  ;
terminal RCurly_t      '}'  ;
terminal Dot_t         '.'  precedence = 25, association = left;
terminal Semi_t        ';'  ;
terminal Colon_t       ':'  ;
terminal UnderScore_t  '_'  ;

terminal CCEQ_t        '::=' lexer classes {SPECOP};
terminal Equal_t       '='   lexer classes {SPECOP};
terminal CtxArrow_t    '=>'  lexer classes {SPECOP};

-- Unused infix operators: ~ ` # % ^ & | \
-- $ is used by convenience.

 -- this is a very careful regex. beware:
--ignore terminal BlockComments /\{\-([^\-]|\-+[^\}\-])*\-+\}/ lexer classes {COMMENT};
ignore terminal BlockComments /\{\-(\{\-([^\-]|\-+[^\}\-])*\-+\}|[^\-]|\-+[^\}\-])*\-+\}/ lexer classes {COMMENT}; -- Allows (one level of) nested comments.  
ignore terminal Comments /([\-][\-].*)/ lexer classes {COMMENT};

ignore terminal WhiteSpace /[\r\n\t\ ]+/;

ignore terminal LocationTag_t /#line -?[0-9]+/
  action {
    line = toInteger(substring(6, length(lexeme), lexeme));
  };
ignore terminal WarnTag_t /#warn [^\r\n]+/
  action {
    print s"${filename}:${toString(line)}: warning: ${substring(6, length(lexeme), lexeme)}";
  };

terminal IdLower_t /[a-z][A-Za-z0-9\_]*/ lexer classes {IDENTIFIER};
terminal IdUpper_t /[A-Z][A-Za-z0-9\_]*/ lexer classes {IDENTIFIER};

-- Identifier terminals emitted as semantic tokens
terminal IdGrammarName_t '' lexer classes {IDENTIFIER, lsp:Namespace};
terminal IdVariable_t '' lexer classes {IDENTIFIER, lsp:Variable};
terminal IdSigName_t '' lexer classes {IDENTIFIER, lsp:Parameter};
terminal IdSigNameDcl_t '' lexer classes {IDENTIFIER, lsp:Parameter, lsp:Declaration};
terminal IdFnProd_t '' lexer classes {IDENTIFIER, lsp:Function};
terminal IdFnProdDcl_t '' lexer classes {IDENTIFIER, lsp:Function, lsp:Declaration};
terminal IdType_t '' lexer classes {IDENTIFIER, lsp:Type};
terminal IdTypeDcl_t '' lexer classes {IDENTIFIER, lsp:Type, lsp:Declaration};
terminal IdTypeClass_t '' lexer classes {IDENTIFIER, lsp:Interface};
terminal IdTypeClassDcl_t '' lexer classes {IDENTIFIER, lsp:Interface, lsp:Declaration};
terminal IdTypeClassMember_t '' lexer classes {IDENTIFIER, lsp:Method};
terminal IdTypeClassMemberDcl_t '' lexer classes {IDENTIFIER, lsp:Method, lsp:Declaration};

terminal True_kwd  'true'   lexer classes {LITERAL,RESERVED, lsp:Keyword};
terminal False_kwd 'false'  lexer classes {LITERAL,RESERVED, lsp:Keyword};
terminal Int_t     /[\-]?[0-9]+/ lexer classes {LITERAL, lsp:Number};
terminal Float_t   /[\-]?[0-9]+[\.][0-9]+/ lexer classes {LITERAL, lsp:Number};
terminal String_t  /[\"]([^\r\n\"\\]|[\\][\"]|[\\][\\]|[\\]b|[\\]n|[\\]r|[\\]f|[\\]t)*[\"]/ lexer classes {LITERAL, lsp:String_};
