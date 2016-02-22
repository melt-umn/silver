---
layout: sv_wiki
title: Print
---


```
terminal Tab /\t/ action { print "saw tab!\n"; }

concrete production foo
f::Foo ::= Tab i::Id
{
} action {
  print "saw foo with id " ++ i.lexeme ++ "\n";
}
```

## Syntax

In **action blocks only**, there is a 'print' action available, generally for testing and debugging purposes only.

`print` _<Expr :: String>_ `;`
