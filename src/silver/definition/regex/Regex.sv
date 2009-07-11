grammar silver:definition:regex;
export silver:definition:regex;

terminal Plus_t /[\+]/
    lexer precedence = 5;
terminal Kleene_t /[\*]/
     lexer precedence = 5;
terminal Optional_t /[\?]/
     lexer precedence = 5;
terminal Choice_t /[\|]/
     lexer precedence = 5;
terminal Range_t /[\-]/
     lexer precedence = 5;
terminal RegexNot_t /[\^]/
     lexer precedence = 5;
terminal RegexLBrack_t /[\[]/
     lexer precedence = 5;
terminal RegexRBrack_t /[\]]/
     lexer precedence = 5;
terminal RegexLParen_t /[\(]/
     lexer precedence = 5;
terminal RegexRParen_t /[\)]/
     lexer precedence = 5;
terminal RegexWildcard_t /[\.]/
     lexer precedence = 4;
terminal RegexChar_t /./
     lexer precedence = 2;
terminal EscapedChar_t /\\./
     lexer precedence = 3;

start nonterminal Regex_R;
nonterminal Regex_DR;
nonterminal Regex_UR;
nonterminal Regex_RR;
nonterminal Regex_G;
nonterminal Regex_RG;
nonterminal Regex_UG;
nonterminal Regex_CHAR;

concrete production RtoDR
r::Regex_R ::= dr::Regex_DR
{}

concrete production RtoDR_bar_R
r::Regex_R ::= first::Regex_DR sep::Choice_t rest::Regex_R
{}

concrete production DRtoUR_RR
dr::Regex_DR ::= first::Regex_UR rest::Regex_RR
{}

concrete production DRtoUR_star_RR
dr::Regex_DR ::= first::Regex_UR sep::Kleene_t rest::Regex_RR
{}

concrete production DRtoUR_plus_RR
dr::Regex_DR ::= first::Regex_UR sep::Plus_t rest::Regex_RR
{}

concrete production DRtoUR_question_RR
dr::Regex_DR ::= first::Regex_UR sep::Optional_t rest::Regex_RR
{}

concrete production RRtoDR
rr::Regex_RR ::= dr::Regex_DR
{}

concrete production RRtoeps
rr::Regex_RR ::=
{}

concrete production URtoCHAR
ur::Regex_UR ::= char::Regex_CHAR
{}

concrete production URtowildcard
ur::Regex_UR ::= wildcard::RegexWildcard_t
{}

concrete production URtolb_G_rb
ur::Regex_UR ::= lb::RegexLBrack_t g::Regex_G rb::RegexRBrack_t
{}

concrete production URtolb_not_G_rb
ur::Regex_UR ::= lb::RegexLBrack_t sep::RegexNot_t g::Regex_G rb::RegexRBrack_t
{}

concrete production URtolp_R_rp
ur::Regex_UR ::= lp::RegexLParen_t r::Regex_R rp::RegexRParen_t
{}

concrete production GtoUG_RG
g::Regex_G ::= ug::Regex_UG rg::Regex_RG
{}

concrete production UGtoCHAR
ug::Regex_UG ::= char::Regex_CHAR
{}

concrete production UGtoCHAR_dash_CHAR
ug::Regex_UG ::= leastchar::Regex_CHAR sep::Range_t greatestchar::Regex_CHAR
{}

concrete production RGtoG
rg::Regex_RG ::= g::Regex_G
{}

concrete production RGtoeps
rg::Regex_RG ::=
{}

concrete production CHARtochar
charRegex::Regex_CHAR ::= char::RegexChar_t
{}

concrete production CHARtoescaped
charRegex::Regex_CHAR ::= esc::EscapedChar_t
{}
