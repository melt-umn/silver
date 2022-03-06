# Records

```silver imports
grammar silver:compiler:extension:records;

import silver:compiler:definition:core;
import silver:compiler:definition:env;
import silver:compiler:definition:type;
import silver:compiler:definition:type:syntax;
```

The records extension is meant to codify a common pattern of defining product types that generates efficient code and has nice usability.

## Declaration

A record definition starts with the `record` keyword.

```silver
terminal RecordKwd_t 'record' lexer classes {KEYWORD};
```

A record definition looks basically like a nonterminal declaration followed by a list of fields.
This forwards to a nonterminal, a single production with no children, and an annotation that occurs on the nonterminal for each field.

```silver
concrete production recordDecl
this::AGDcl ::= quals::NTDeclQualifiers  'record'  name::IdUpper_t
  modifiers::NonterminalModifiers  '{'  fields::RecordFields0  '}'  ';'
{
  this.unparse =
    s"${quals.unparse}record ${name.lexeme} ${modifiers.unparse} {${fields.unparse}};";

  local ntDcl::AGDcl =
    nonterminalDcl(quals, 'nonterminal',
      nameIdUpper(name, location=this.location),
      botlNone(location=this.location), modifiers, ';', location=this.location);
  local ntType::TypeExpr =
    nominalTypeExpr(qNameTypeId(name, location=this.location),
      location=this.location);

  -- PARTIAL: head() and tail() are safe because names are nonempty.
  local nameChars::[String] = explode("", name.lexeme);
  local prodName::IdLower_t = terminal(IdLower_t,
    implode("", toLower(head(nameChars)) :: tail(nameChars)),
    name.location);

  local prodLHS::ProductionLHS = productionLHS(
    nameIdLower(terminal(IdLower_t, "this"), location=this.location), '::',
    ntType, location=this.location);
  local prodSig::ProductionSignature =
    productionSignatureNoCL(prodLHS, '::=',
      productionRHSNil(location=this.location), location=this.location);
  local prodDcl::AGDcl =
    productionDcl('abstract', 'production',
      nameIdLower(prodName, location=this.location), prodSig, 
      productionBody('{', productionStmtsNil(location=this.location), '}',
        location=this.location),
      location=this.location);

  forwards to appendAGDcl(ntDcl,
    appendAGDcl(prodDcl, fields.recordFieldAGDcl, location=this.location),
    location=this.location);
}
```

### `without declaring` Nonterminal Modifier

Note that to support multiple records with the same field names, we don't always declare a new annotation for the field.
The `without declaring` nonterminal modifier requests that an annotation not be declared.
It creates an entry in the `withoutDeclaring` synthesized attribute for the annotation.

```silver
synthesized attribute withoutDeclaring::[String] occurs on NonterminalModifier,
  NonterminalModifiers, NonterminalModifierList;

terminal Without_kwd   'without'   lexer classes {KEYWORD};
terminal Declaring_kwd 'declaring' lexer classes {KEYWORD};

concrete production withoutDeclaringOne
this::NonterminalModifier ::= 'without'  'declaring'  fieldName::IdLower_t
{
  propagate nonterminalModifiers;

  this.errors := [];
  this.unparse = s"without declaring ${fieldName.lexeme}";

  this.withoutDeclaring = [fieldName.lexeme];
}

concrete production withoutDeclaringMany
this::NonterminalModifier ::= 'without'  'declaring'  '{' 
  fieldNames::FieldNames0  '}'
{
  propagate nonterminalModifiers;

  this.errors := [];
  this.unparse = s"without declaring {${fieldNames.unparse}}";

  this.withoutDeclaring = fieldNames.names;
}

aspect default production
this::NonterminalModifier ::=
{ this.withoutDeclaring = []; }
```

We need the propagates for `nonterminalModifiers` because it's interfering...

```silver imports
import silver:compiler:definition:concrete_syntax only nonterminalModifiers;
```

We want to use our own `FieldNames0` nonterminal, because none of the built-in ones seem to be "just a plain list of `IdLower_t`s."
In particular, `QNames` is a list of type-qualified `QName`s, not just plain names.

```silver
synthesized attribute names::[String];

nonterminal FieldNames0 with location, unparse, names;
nonterminal FieldNames1 with location, unparse, names;

concrete production fieldNames0Empty
this::FieldNames0 ::=
{
  this.unparse = "";
  this.names = [];
}

concrete production fieldNames0Nonempty
this::FieldNames0 ::= fieldNames::FieldNames1
{
  this.unparse = fieldNames.unparse;
  this.names = fieldNames.names;
}

concrete production fieldNames0TrailingComma
this::FieldNames0 ::= fieldNames::FieldNames1  ','
{ forwards to fieldNames0Nonempty(fieldNames, location=this.location); }

concrete production fieldNames1One
this::FieldNames1 ::= name::IdLower_t
{
  this.unparse = name.lexeme;
  this.names = [name.lexeme];
}

concrete production fieldNames1Cons
this::FieldNames1 ::= init::FieldNames1  ','  last::IdLower_t
{
  this.unparse = s"${init.unparse}, ${last.lexeme}";
  this.names = init.names ++ [last.lexeme];
}
```

To make `withoutDeclaring` flow up through the nonterminal modifier lists, we need to add aspects for those nonterminals.

```silver
aspect production nonterminalModifiersNone
this::NonterminalModifiers ::=
{ this.withoutDeclaring = []; }

aspect production nonterminalModifierSome
this::NonterminalModifiers ::= modifiers::NonterminalModifierList
{ this.withoutDeclaring = modifiers.withoutDeclaring; }

aspect production nonterminalModifierSingle
this::NonterminalModifierList ::= modifier::NonterminalModifier
{ this.withoutDeclaring = modifier.withoutDeclaring; }

aspect production nonterminalModifiersCons
this::NonterminalModifierList ::= head::NonterminalModifier ',' tail::NonterminalModifierList
{ this.withoutDeclaring = head.withoutDeclaring ++ tail.withoutDeclaring; }
```

Once the `withoutDeclaring` values have flowed up through the modifiers, we push them back down to the fields.

```silver
inherited attribute withoutDeclaringInh::[String];

aspect production recordDecl
this::AGDcl ::= quals::NTDeclQualifiers  'record'  name::IdUpper_t
  modifiers::NonterminalModifiers  '{'  fields::RecordFields0  '}'  ';'
{
  fields.withoutDeclaringInh = modifiers.withoutDeclaring;
}
```

### Fields

Record fields are pretty straightforward, just a pair of a name and a type.
They define an `AGDcl` they correspond to too, for the particular nonterminal they should occur on.

1. Generate polymorphic annotations (and monomorphic occurrences)
2. Don't always declare the annotation, so that the annotation can be imported or be declared by another record.
   The `without declaring` nonterminal modifier causes a declaration to not be generated.

For example:

```silver example
record Foo without declaring field1 {
  field1::Integer,
  field2::String,
};

record Bar without declaring field2 {
  field1::Boolean,
  field2::Float,
};
```

The above code should forward to:

```silver example
nonterminal Foo;
abstract production foo
Foo ::=
{}

annotation field2<a>::a;
annotation field1<Integer> occurs on Foo;
annotation field2<String> occurs on Foo;

nonterminal Bar;
abstract production bar
Bar ::=
{}

annotation field1<a>::a;
annotation field1<Boolean> occurs on Bar;
annotation field2<Float> occurs on Bar;
```

Type inference should always be able to sort out what the type of `thing.field1` is correctly, although we don't currently have a way to talk about "types with a `field1` with type `String`."

```silver
synthesized attribute recordFieldAGDcl::AGDcl;

nonterminal RecordField with location, unparse, recordFieldAGDcl,
  recordFieldAGDclQName, withoutDeclaringInh;

concrete production recordField
this::RecordField ::= name::IdLower_t  '::'  typeExpr::TypeExpr
{
  this.unparse = s"${name.lexeme}::${typeExpr.unparse}";

  local qname::QName =
    qNameId(nameIdLower(name, location=this.location), location=this.location);

  local typeVarExpr::TypeExpr =
    typeVariableTypeExpr(terminal(IdLower_t, "a", this.location),
      location=this.location);
  local typeVarArgs::BracketedOptTypeExprs =
    botlSome(
      bTypeList('<', typeListSingle(typeVarExpr, location=this.location), '>',
        location=this.location),
      location=this.location);

  local typeArgs::BracketedOptTypeExprs =
    botlSome(
      bTypeList('<', typeListSingle(typeExpr, location=this.location), '>',
        location=this.location),
      location=this.location);

  local declare::AGDcl =
    annotationDcl('annotation', qname, typeVarArgs, '::', typeVarExpr, ';',
      location=this.location);
  local occur::AGDcl =
    annotateDcl('annotation', qname, typeArgs, 'occurs', 'on',
      this.recordFieldAGDclQName, botlNone(location=this.location), ';',
      location=this.location);

  this.recordFieldAGDcl =
    if contains(name.lexeme, this.withoutDeclaringInh)
    then occur
    else appendAGDcl(declare, occur, location=this.location);
}
```

Note that in order to know the name of the nonterminal the attributes should occur on, we pass it down as an inherited attribute.

```silver
inherited attribute recordFieldAGDclQName::QName;

aspect production recordDecl
this::AGDcl ::= quals::NTDeclQualifiers  'record'  name::IdUpper_t
  modifiers::NonterminalModifiers  '{'  fields::RecordFields0  '}'  ';'
{
  fields.recordFieldAGDclQName =
    qNameId(nameIdUpper(name, location=this.location), location=this.location);
}
```

### Field Lists

Sequences of fields exist in a pretty typical way.
There can be zero fields, a single field, or a series of fields; fields are comma-separated, and a trailing comma is allowed.
The grammar is:

```
RecordFields ::=
              |  RecordField
              |  RecordField ',' RecordFields
```

However, for parsing efficiency, we want to make this grammar left-recursive instead of right-recursive.
Instead, we structure it as:

```
RecordFields0 ::=
               |  RecordFields1
               |  RecordFields1 ','
RecordFields1 ::= RecordField
               |  RecordFields1 ',' RecordField
```

The trailing comma needs to be explicit in this form, but it can just be a forwarding production.

```silver
nonterminal RecordFields0 with location, unparse, recordFieldAGDcl,
  recordFieldAGDclQName, withoutDeclaringInh;
nonterminal RecordFields1 with location, unparse, recordFieldAGDcl,
  recordFieldAGDclQName, withoutDeclaringInh;

concrete production recordFields0Empty
this::RecordFields0 ::=
{
  this.unparse = "";

  this.recordFieldAGDcl = emptyAGDcl(location=this.location);
}

concrete production recordFields0Nonempty
this::RecordFields0 ::= fields::RecordFields1
{
  this.unparse = fields.unparse;

  this.recordFieldAGDcl = fields.recordFieldAGDcl;
  fields.recordFieldAGDclQName = this.recordFieldAGDclQName;
  fields.withoutDeclaringInh = this.withoutDeclaringInh;
}

concrete production recordFields0TrailingComma
this::RecordFields0 ::= fields::RecordFields1  ','
{ forwards to recordFields0Nonempty(fields, location=this.location); }

concrete production recordFields1One
this::RecordFields1 ::= field::RecordField
{
  this.unparse = field.unparse;

  this.recordFieldAGDcl = field.recordFieldAGDcl;
  field.recordFieldAGDclQName = this.recordFieldAGDclQName;
  field.withoutDeclaringInh = this.withoutDeclaringInh;
}

concrete production recordFields1Cons
this::RecordFields1 ::= init::RecordFields1  ','  last::RecordField
{
  this.unparse = s"${init.unparse}, ${last.unparse}";

  this.recordFieldAGDcl = appendAGDcl(init.recordFieldAGDcl,
    last.recordFieldAGDcl, location=this.location);

  init.recordFieldAGDclQName = this.recordFieldAGDclQName;
  init.withoutDeclaringInh = this.withoutDeclaringInh;

  last.recordFieldAGDclQName = this.recordFieldAGDclQName;
  last.withoutDeclaringInh = this.withoutDeclaringInh;
}
```
