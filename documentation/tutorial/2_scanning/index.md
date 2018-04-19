---
layout: sv_wiki
title: Scanning
menu_title: Scanning
menu_weight: 20.0
---

The role of the scanner is to take the program text as input and recognize the keywords, constants,
operator symbols and other items in the input text.  It also recognizes comments and discards them
as they are not needed for processing the program.

The output of the scanner can be thought of as a sequence of tokens; each indicating the type of
symbol recognized, the string in the input that was recognized, and, typically, a line and column
number.

The "type" of symbols that are recognized in a language is determined by specifying a set of
*terminal symbols*, simply called *terminals*.  For example, a terminal may be defined that matches
integer  constants,  another  for  program  variable  names,  another  for  the  "while"  keyword  in  an
imperative language.  To specify this,  terminals are defined by a name and a regular expression
defines the strings the terminal symbol should match.  We may define a terminal symbol named *Int<sub>t</sub>*
with a regular expression that matches a sequence of 1 or more digits.

A token generated for a sequence of characters is an object that specifies what type of token,
the text that was recognized as generating the token (called the *lexeme*), and in many cases, the
line and column number of the file where the lexeme is located.

A scanner is constructed from the specifications of terminal symbols in the grammar.  Terminal
symbols are given names which must be capitalized since terminals are essentially types in Silver
and type names must be capitalized.

Regular expressions associated with a terminal are written after the terminal name between forward
slashes.  The file `Terminals.sv` in the tutorial grammar `dc` contains the following declaration
for integer literal terminals.

    terminal IntLit_t /[0-9]+/ ;

Regular expressions that are constant strings may be represented using single quotes, as seen in
the declaration of the plus symbol in the same file:

    terminal Plus t ’+’ ;


Terminal declarations have the form

    terminal <name> ( /<regex>/ | ’<string>’ ) <optional-clauses> ;

Terminals in which the regular expression is written between single quotes require that the regular
expression be a simple string that when considered as a regular expression will match only that
string. In Silver specifications we can then refer to that terminal symbol using the quoted string
instead of its name.

Consider a simple imperative language, such as Simple defined in the tutorial grammar `simple`.
Because the regular expression for the keywords such as *While<sub>t</sub>*, *If<sub>t</sub>*, and *Else<sub>t</sub>* all overlap with the
language defined by the regular expression for identifiers *Id<sub>t</sub>* we need some mechanism to disambiguate
them. This is done by giving the keyword terminal *lexical precedence* over the identifier
terminal. Thus the string "while" which matches both the regular expression of *While<sub>t</sub>* and *Id<sub>t</sub>*
will be matched with *While<sub>t</sub>* as we would expect. Lexical precedence can be specified in two ways.
First, as done in the example, an optional clause of the form

    submits to { <comma-separated-names> }

indicates that the terminal being defined has lower lexical precedence than those listed. A second
form

    dominates { <comma-separated-names> }

indicates the opposite, that the terminal being defined has higher lexical precedence than those in
the list. The names between curly braces may be the names of terminal symbols or of *lexer classes*,
to which terminals may claim membership and thus be considered in any lexical precedence relations
specified using the lexer class name. Examples of this can be found in the `Terminals.sv` file in the
tutorial grammar `simple:terminals`, a few of which are copied below:

```
lexer class keywords ;

terminal If_t    'if'   lexer classes { keywords } ;
terminal Else_t  'else' lexer classes { keywords } ;
terminal While_t 'while' lexer classes { keywords } ;

terminal Id_t /[a-zA-Z][a-zA-Z0-9_]*/ submits to { keywords } ;
```

Here, keywords specify that they are members of the `keywords` class. The identifier terminal
indicates that it has lower lexical precedence than keywords as desired.

Terminals declarations with the optional leading `ignore` keyword are recognized by the scanner
but then dropped and not returned to the parser. Below are the specifications of white space and
line comments from `simple`, both of which are recognized by the scanner but not returned to the
parser.

```
ignore terminal WhiteSpace_t /[\t\n\ ]+/ ;     -- white space
ignore terminal LineComment_P /[\/][\/].*/ ;   -- line comments
```

Next Section: [Parsing](../3_parsing)
