---
layout: sv_wiki
title: Terminal declarations
---


```
terminal Identifier /[A-Za-z_][A-Za-z0-9_]*/;

terminal BlockComment / \/\* ([^\*]|\*+[^\*\/])* \*+\/ /;
```

## Syntax

Terminals are declared using the keyword `terminal` followed by a name, and a regular expression inside forward slashes (`/`).

`terminal` _Identifier_ `/` _regular expression_ `/` `;`

The identifier, like all type names in Silver, must start with a capital letter.  Only standard DFA-style regular expressions are supported (e.g. no back references, etc.)

Character classes are not currently supported, but are in the works.

Whitespace inside the regex is allowed and **ignored**. If whitespace is desired in the regular expression it should be escaped (`'\ '`).

## Easy terminal extension

An extension allows a terminal declaration to use single-quote non-regular expressions.  For example:

```
terminal Boolean  'bool';
terminal Multiply '*';
```

## "Attributes"

Values of terminal type have a small number of pseudo-attributes:

| Attribute | Type | Value |
|:----------|:-----|:------|
| lexeme    | String | The string the terminal matched |
| filename  | String | The filename the terminal purports to be from |
| line      | Integer | The starting line of the terminal (begins counting with 1) |
| column    | Integer | The starting column of the terminal (begins counting with 0) |

## Terminal Modifiers

### Ignore terminals

Ignored layout terminals can be specified for an entire grammar prefixing the terminal declaration with the `ignore` keyword:

```
ignore terminal WhiteSpace /[\n\t\ ]+/;
```

### Terminal precedence

Operator terminals can be given a parsing precedence:

```
terminal Plus  '+' precedence = 11;
terminal Times '*' precedence = 12;
```

The numbers are arbitrary, relative to each other. High number means "binds more tightly."

### Terminal association

The association for a terminal can also be given:

```
terminal Plus  '+' precedence = 11, association = left;
terminal Times '*' precedence = 12, association = left;
```

'left' and 'right' are valid. Absent a declaration, terminals are assumed to be non-associative.

## Copper-specific modifiers

### Lexer classes

Terminals can be assigned multiple lexer classes:

```
terminal Global 'global' lexer classes {KEYWORD};
terminal Length 'length' lexer classes {KEYWORD,BUILTIN};
```

### Dominates/submits

Terminals can have dominates and submits lists:

```
terminal Identifier /[a-z]+/ submits to {KEYWORD};
terminal Redundant 'keyword' dominates {Identifier};
```

Each element of the list should refer either to a lexer class, or a terminal.

It's generally preferred to do dominates/submits on the lexer class declarations than on the terminals themselves, however.

### Semantic action code

Terminals can provide code to be executed when that terminal is shifted by the parser (or ignored, in the case of layout terminals.)

```
terminal Magic 'more magic'
action { 
  print "how does " ++ lexeme ++ " work, but magic not?";
};
```

Inside this action block, a couple of variables are available:

| Variable | Type | Contents |
|:---------|:-----|:---------|
| lexeme   | String | The string matched by the regular expression |
| filename | String | The filename given to the parser, when it was invoked. (Unless otherwise changed!) |
| line     | Integer | The starting line of this terminal (begins with 1) |
| column   | Integer | The starting column of this terminal (begins with 0) |

In addition, the `filename`, `line`, and `column` variables can be assigned to in the action block, but this updates the position the _next_ terminal will believe it starts at, not the current terminal. (This is often used to deal with CPP directives in the parser.)
