grammar silver:compiler:extension:abella_compilation:parsing_thms;


terminal Qname_colon_t  /([A-Za-z0-9_]+:)+[-A-Za-z^=`'?$][-A-Za-z^=`'?$0-9_*@+#!~\/]*/
   submits to {Exists_t, False_t, Forall_t, Nabla_t, True_t, Nil_t,
               Modulus_t, No_t, Value_t, Minus_t, Eq_t};
terminal Separate_t     '&';
terminal SplitInterf_t  '$Spl';
terminal DefInterf_t    '$Def';



terminal Exists_t       'exists';
terminal False_t        'false';
terminal Forall_t       'forall';
terminal Nabla_t        'nabla';
terminal True_t         'true';
terminal Comma_t        ','     precedence=3;
terminal Period_t       '.';
terminal Semicolon_t    ';';
terminal Backslash_t    '\'     precedence=7;
terminal LParen_t       '(';
terminal RParen_t       ')';
terminal Eq_t           '=';
terminal Colon_t        ':';
terminal RightArrow_t   '->'    precedence=4, association=right;
terminal At_t           '@';
terminal Octothorpe_t   '#';
terminal Or_t           '\/'    precedence=5, association=left;
terminal And_t          '/\'    precedence=6, association=left;
terminal LBracket_t     '[';
terminal RBracket_t     ']';
terminal Underscore_t   '_';
terminal OptSemi_t      /;?/;
terminal Cons_t         '::'    precedence=11, association=right;
terminal Nil_t          'nil';
--These two have Abella uses with no precedence or associativity
--   and Silver uses with precedence and associativity (arithmetic)
terminal Plus_t         '+'     precedence=9, association=left;
terminal Star_t         '*'     precedence=10, association=left;


terminal Id_t  /[-A-Za-z^=`'?][-A-Za-z^=`'?$0-9_*@+#!~\/]*/
   submits to {Exists_t, False_t, Forall_t, Nabla_t, True_t, Nil_t,
               Modulus_t, No_t, Value_t, Minus_t, Eq_t};
terminal QString_t  /"[^"]*"/;
terminal Number_t  /[0-9]+/;


--These are the things which we are adding to Abella for Silver
terminal AttrAccess_t  /[a-zA-Z][A-Za-z0-9\_]*\.[a-zA-Z][A-Za-z0-9\_]*/;
terminal SilverString_t  /"([^"]|(\\"))*"/;
terminal SilverNegativeInteger_t  /-[0-9]+/ dominates Id_t;

terminal Minus_t      '-'    precedence=9, association=left;
terminal Divide_t     '/'    precedence=10, association=left;
--We use 'mod' instead of '%' because of Abella comments
--Alternatively, we could change comments for the interface, but I'd
--   like to leave the syntax the same as much as possible.
terminal Modulus_t    'mod'  precedence=10, association=left;
terminal Less_t       '<'    precedence=7, association=left;
terminal LessEq_t     '<='   precedence=7, association=left;
terminal Greater_t    '>'    precedence=7, association=left;
terminal GreaterEq_t  '>='   precedence=7, association=left;
terminal Append_t     '++'   precedence=8, association=left;
terminal SilverOr_t   '||'   precedence=3, association=left;
terminal SilverAnd_t  '&&'   precedence=4, association=left;
terminal SilverNot_t  '!'    precedence=5;
terminal Tilde_t      '~';
--Attributes not having any value
terminal No_t         'no';
terminal Value_t      'value';


ignore terminal Whitespace_t /[\ \t\n\r]+/;

