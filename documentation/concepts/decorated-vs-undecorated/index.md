---
layout: sv_wiki
title: Decorated vs undecorated
---

The distinction between decorated and undecorated types is one that some initially find confusing; this section aims to clear up such confusion.



# Decorated vs Undecorated

Attribute grammars are a way of doing computations over trees, and there are two conceptually different types of trees for each nonterminal:

  * The undecorated type is a bare-bones data type that simply represents the tree itself.  This is nearly identical to how the tree might be represented as a Haskell data type, for example.
  * The decorated type, on the other hand, is the fully attributed tree.  Decorated trees contain not just their (now decorated) children but also the values of all synthesized and inherited attributes that occur on the corresponding nonterminal.

There are a number of consequences to this distinction:

  * An undecorated tree is quite space-efficient. Its decorated trees may not be.
  * Attributes can only be accessed from decorated trees.  (Some tricks may obscure this fact, see below.)
  * Undecorated trees can be decorated any number of times, potentially with different inherited attributes each time.  Each of these will produce a distinct decorated tree.

## Why the disctinction?

It comes from the two-stage form of application that attribute grammars have.

Stage 1 takes a production and applies it to its children.  This yields an _undecorated_ type.

Stage 2 takes an undecorated type, and applies it to a set of inherited attributes.  This yields the _decorated_ type.

## Implicit decoration

From looking at simple examples of Silver code, one may not be aware of these distinctions between decorated and undecorated types. The reason is that Silver will hide this distinction in the common cases. Productions' (and functions') children and local attributes, despite having a declared undecorated type, are in fact treated as decorated when _referenced_.  This is because these may be supplied with inherited attributes elsewhere within the production body.

Failing to define all the necessary inherited attributes is not currently detected by Silver at compile time, and will cause a runtime error.

> _**Example:**_
> In the following production:
```
abstract production assignmentStmt
top::Stmt ::= l::Name '=' r::Expr
{
  local attribute lcopy :: Name;
  lcopy = l; -- Silver automatically undecorates l
  local attribute lref :: Decorated Name;
  lref = l;
}
```

> The child _`l`_ actually has type _`Decorated Name`_ in the body of the production.  Local attribute _`lcopy`_ also has type _`Decorated Name`_ for the same reason that the child _`l`_ is decorated.  However, the value of _`lcopy`_ is a **different decorated tree** than _`l`_. These two trees may be assigned different values for inherited attributes, though that is not shown in the example.

> Local _`lref`_ has the same decorated type, but the value of _`lref`_ is the **same decorated tree** as _`l`_.  Inherited attributes cannot be given to _`lref`_.

> _`assignmentStmt`_ must be invoked with an undecorated name and expression. Attempting to pass it an already decorated name will result in a type error. Similarly, _`lcopy`_ must be initialized with a value of undecorated type, though in the example above, _`l`_ is automatically undecorated.

This "automatic undecoration" behavior _only_ applies to names, not expressions.  If you call a function that returns a value of type _`Decorated Foo`_, and you try to assign this to a local of type _`Foo`_, you will receive a type error.  You will need to use the _`new`_ operator to manually un-decorate the type. See the [new operator]({{ "ref/expr/new/" | prepend: site.sv_wiki_base }}).

## Automatic decoration

In addition to this automatic undecoration of children and locals, attempting to access an attribute of an undecorated type will automatically decorate it with no inherited attributes, and then access the attribute from that resulting temporary decorated tree.  This behavior is purely for convenience: there are many common synthesized attributes that do not depend on any inherited attributes (consider `fst` and `snd` of the `Pair` type.)

This behavior _only_ applies to attribute accesses (and technically, also pattern matching), and not to any arbitrary expression that wants a decorated type.  If you try to call a function that takes a _`Decorated`_ parameter with an undecorated value, you will receive a type error. See [the decorate operator]({{ "ref/expr/decorate/" | prepend: site.sv_wiki_base }}) for explicitly creating a decorate value from an undecorated one.

## Concrete example

Consider the nonterminal type _`Expr`_ from the Simple tutorial grammar.  This nonterminal is decorated by the synthesized attribute _`errors`_ which depends on the inherited attribute _`env`_.  To compute errors on an expression such as "_`x + 3`_" we need contextual information indicating if _`x`_ has been declared or not.  This information is passed in as the inherited attribute _`env`_. Inside the production _`varRef`_ the value of _`errors`_ is based on the result of looking up the variable in the inherited attribute _`env`_.

> The production _`add`_, below, in the abstract syntax computes the value of its _`errors`_ attribute from that of its children. (The assignment operator _`:=`_ is described in [the section on collections]({{ "/concepts/collections/" | prepend: site.sv_wiki_base }}) and can be read as the standard assignment _`=`_ for our purposes here.)
```
abstract production add 
e::Expr ::= l::Expr r::Expr 
{
  l.env = e.env;
  r.env = e.env;
  e.errors := l.errors ++ r.errors ;
}
```
> What may be initially confusing is that the declared type of child _`l`_ is the undecorated type _`Expr`_ yet this production clearly accesses the synthesized attribute _`errors`_ on that node. The reason this is allowed is that inside the curly braces we are really working with decorated trees since inherited attributes of the children may be assigned here.  And we see above that the _`env`_ attribute is being assigned to the child _`l`_. In the body of the production _`l`_ does have the type _`Decorated Expr`_. The production _`add`_ does take an undecorated _`Expr`_ as its first (and second) argument, thus the type given for _`l`_ is accurate.  The production assigns the necessary inherited attributes to its children in its body and thus, in the body, the synthesized attributes of its children can be accessed.

## Examples

### Higher-order (undecorated)

An attribute of undecorated type is called a higher-order attribute.
Higher-order attributes are ideal for expressing
transformations from one tree to another.  See the Simple tutorial's concrete
syntax, and how it constructs abstract syntax using higher-order attributes.

### Reference (decorated)

An attribute of decorated type is called a reference attribute.
Reference attributes are ideal for connecting variable uses with variable
declarations.  This can be seen in the production _`varRef`_ in
the Simple tutorial grammar and shown in an example on the
[Production Attribute]({{ "/ref/stmt/locals/" | prepend: site.sv_wiki_base }}) page.

Since these attributes can also be viewed as records of the attribute values
that occur on their nonterminal, it is sometimes also preferable to pass
around more complex data structures using a reference attribute, rather than
a higher order one, since this avoids creating many copies of the decorated
tree everywhere the undecorated tree is used.

### Performance / forwarding

Forward clauses always take undecorated trees to forward to. However, the
children of the root production of that tree may be of decorated type.

One of the potential drawbacks of forwarding is the exponential growth in the
size of the decorated tree.  Consider the case of an operator that forwards to
a function, as one might expect to see in a simple extension.

```
concrete production twiddleOperator
top::Expr ::= foo::Expr '~~>' bar::Expr
{
  forwards to functionApp("ext:twiddle", exprsCons(foo, exprsSingle(bar)));
}
```

While the above code is perfectly fine by itself, one desired enhancement might
be to do type checking on this operator, instead of on the function after the
forward (for example, to give better error messages. Or, to determine which
function to forward to, based on type.)

However, this would result in exponential runtime for nested uses of the operator. The reason is that _`foo`_ and _`bar`_ are both type checked here, and then
again by the `functionApp` production.  This means that the undecorated trees get decorated once here, and once again after the forward. With repeated nesting of the operator, the problem should be obvious.

A solution in this case is to introduce a new constructor for Exprs:

```
abstract production exprsDecorated
top::Exprs ::= exps :: [Decorated Expr]
  ...

concrete production twiddleOperator
top::Expr ::= foo::Expr '~~>' bar::Expr
{
  forwards to functionApp("ext:twiddle", exprsDecorated([foo, bar]));
}
```

With this change, the children are only decorated once.  This eliminates the problem, but introduces a restriction: all inherited attributes the function application might wish to give to its children, must be given by the operator instead. In most cases, this restriction is a non-issue.
