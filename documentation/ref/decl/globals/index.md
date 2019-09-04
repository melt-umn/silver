---
layout: sv_wiki
title: Global constant declarations
---


```
global defaultSuffix :: String = "_sfx";

global ones :: [Integer] = 1 :: ones;
```

## Syntax

Global values can be declared as follows:

`global` _identifier_ `::` _Type_ `=` _expression_ `;`

## FAQ

### What's the point?

Since Silver is a pure language (although holes exist), it's pretty much just for declaring constants.

It's used most often by testing related code.
