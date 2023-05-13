```silver
grammar silver:compiler:extension:attrsection;

imports silver:compiler:definition:core;
imports silver:compiler:definition:type:syntax;
imports silver:compiler:definition:type;
imports silver:compiler:definition:env;
imports silver:compiler:modification:lambda_fn;

import silver:util:treeset as ts;
```

Attribute sections are a shorthand syntax for a lambda function that simply access an attribute on their argument. 

```silver
concrete production attributeSection
top::Expr ::= '(' '.' q::QNameAttrOccur ')'
{
  top.unparse = s"(.${q.unparse})";
  propagate freeVars, env, grammarName;
```

In constructing the forward we need to know on what type the attribute will be accessed.
This requires knowing the final substitution, thus the forward here depends on top.finalSubst.
Thus type inference must take place without looking at the forward to avoid a circularity.
  
First, try to determine the type of the attribute if it can be looked up unambiguously.
Note that this will have fresh type variables filled in if the attribute has type arguments.
E.g. with `synthesized attribute bar<a>::[a];`, `attrType` for `(.bar)` will always be
`listType(freshType())`.
```silver
  local attrType::Type =
    case q of
    | qNameAttrOccur(at) when at.lookupAttribute.dcls matches [dcl] -> dcl.typeScheme.typerep
    | _ -> freshType()
    end;
```

We must override top.typerep and upSubst as these are used in type inference to compute top.finalSubst.
Construct a function type with a fresh type variable as the input type,
and a "closest guess" of the attribute's type as the output.  This type must eventually unify with
the actual input and output types elsewhere in type inference.
```silver
  top.typerep = appTypes(functionType(1, []), [freshType(), attrType]);
```

No direct type inference happens here.
```silver
  top.upSubst = top.downSubst;
```

Determine the actual final input and output types that were computed elsewhere during type inference.
```silver
  local finalTy::Type = performSubstitution(top.typerep, top.finalSubst);
  local inputTy::Type = head(finalTy.inputTypes);
  local outputTy::Type = finalTy.outputType;

  q.attrFor = inputTy;
```

The inferred output type must be unambiguous: otherwise it is possible that a consumer of this
expression (e.g. another attribute section...) could assume, after type inference, that some more
specific type that differs from the output type is safe to use for the attribute section here.
```silver
  top.errors :=
    if !null(outputTy.freeFlexibleVars)
    then [err(top.location, s"The attribute section ${top.unparse} has an ambiguous inferred output type ${prettyType(outputTy)}, where ${implode(", ", map(findAbbrevFor(_, outputTy.freeVariables), outputTy.freeFlexibleVars))} ${if length(outputTy.freeFlexibleVars) > 1 then "are" else "is"} unspecialized")]
    else forward.errors;
```

Finally, check that the output type that was inferred matches the type that was actually computed
on the forward (where the type of the attribute was properly looked up knowing the input type that
was inferred elsewhere.)
```silver 
  local checkOutputType::TypeCheck = check(outputTy, forward.typerep.outputType);
  checkOutputType.downSubst = forward.upSubst;
  checkOutputType.finalSubst = composeSubst(forward.upSubst, top.finalSubst);
  top.errors <-
    if checkOutputType.typeerror
    then [err(top.location, s"Attribute section ${top.unparse} has inferred output type ${checkOutputType.leftpp} that does not match the attribute's type ${checkOutputType.rightpp}")]
    else [];
```

The forward is just equivalent to `\ x::inputTy -> x.attr`
```silver
  forwards to
    lambdap(
      productionRHSCons(
        productionRHSElem(name("x", top.location), '::', typerepTypeExpr(inputTy, location=top.location), location=top.location),
        productionRHSNil(location=top.location),
        location=top.location),
      access(baseExpr(qName(top.location, "x"), location=top.location), '.', q, location=top.location),
      location=top.location);
}
```
