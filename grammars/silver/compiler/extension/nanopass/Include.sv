grammar silver:compiler:extension:nanopass;

terminal Include_t 'include' lexer classes {KEYWORD};
terminal Exclude_t 'exclude' lexer classes {KEYWORD};
terminal Annotate_t 'annotate' lexer classes {KEYWORD};
terminal Open_t 'open' lexer classes {KEYWORD};
terminal Close_t 'close' lexer classes {KEYWORD};
terminal Nonterminals_t 'nonterminals' lexer classes {KEYWORD};
terminal Productions_t 'productions' lexer classes {KEYWORD};
terminal Attributes_t 'attributes' lexer classes {KEYWORD};

concrete production includeGrammar
top::AGDcl ::= 'include' m::QName '{' t::TransformStmts '}'
{
  top.unparse = "include " ++ m.unparse ++ " {" ++ t.unparse ++ "}";
  
  forwards to
    case searchEnvTree(m.name, top.compiledGrammars) of
    | r :: _ -> foldr(appendAGDcl, emptyAGDcl(), r.includeTransDcls(t))
    | _ -> errorAGDcl([errFromOrigin(m, s"Grammar ${m.name} not found")])
    end;
}

monoid attribute excludedTypes :: ts:Set<String>;
monoid attribute closedNonterminals :: ts:Set<String>;
monoid attribute openedNonterminals :: ts:Set<String>;
monoid attribute excludedValues :: ts:Set<String>;
monoid attribute excludedAttributes :: ts:Set<String>;
monoid attribute annotatedAttributes :: ts:Set<String>;

propagate
  grammarName, env,
  excludedTypes, closedNonterminals, openedNonterminals, excludedValues, excludedAttributes, annotatedAttributes
  on TransformStmts, TransformStmt;

nonterminal TransformStmts with grammarName, env, unparse, errors,
  excludedTypes, closedNonterminals, openedNonterminals, excludedValues, excludedAttributes, annotatedAttributes;

concrete production consTransformStmt
top::TransformStmts ::= t::TransformStmt ts::TransformStmts
{
  top.unparse = t.unparse ++ ts.unparse;
}

concrete production nilTransformStmt
top::TransformStmts ::=
{
  top.unparse = "";
}

nonterminal TransformStmt with grammarName, env, unparse, errors,
  excludedTypes, closedNonterminals, openedNonterminals, excludedValues, excludedAttributes, annotatedAttributes;

concrete production excludeNonterminals
top::TransformStmt ::= 'exclude' 'nonterminals' ns::NtNames ';'
{
  top.unparse = "exclude nonterminals " ++ ns.unparse ++ ";";
  top.excludedTypes <- ts:fromList(ns.names);
}
concrete production closeNonterminals
top::TransformStmt ::= 'close' 'nonterminals' ns::NtNames ';'
{
  top.unparse = "close nonterminals " ++ ns.unparse ++ ";";
  top.closedNonterminals <- ts:fromList(ns.names);
  top.errors <- ns.closeErrors;
}
concrete production openNonterminals
top::TransformStmt ::= 'open' 'nonterminals' ns::NtNames ';'
{
  top.unparse = "open nonterminals " ++ ns.unparse ++ ";";
  top.openedNonterminals <- ts:fromList(ns.names);
  top.errors <- ns.openErrors;
}
concrete production excludeProductions
top::TransformStmt ::= 'exclude' 'productions' ns::ProdNames ';'
{
  top.unparse = "exclude productions " ++ ns.unparse ++ ";";
  top.excludedValues <- ts:fromList(ns.names);
}
concrete production excludeAttributes
top::TransformStmt ::= 'exclude' 'attributes' ns::AttrNames ';'
{
  top.unparse = "exclude attributes " ++ ns.unparse ++ ";";
  top.excludedAttributes <- ts:fromList(ns.names);
}
concrete production annotateAttributes
top::TransformStmt ::= 'annotate' 'attributes' ns::AttrNames ';'
{
  top.unparse = "annotate attributes " ++ ns.unparse ++ ";";
  top.annotatedAttributes <- ts:fromList(ns.names);
}

monoid attribute closeErrors :: [Message];
monoid attribute openErrors :: [Message];
nonterminal NtNames with env, unparse, names, errors, closeErrors, openErrors;
propagate env, errors, closeErrors, openErrors on NtNames;

concrete production consNtName
top::NtNames ::= n::QNameType ns::NtNames
{
  top.unparse = n.name ++ ", " ++ ns.unparse;
  top.names = n.lookupType.fullName :: ns.names;
  top.errors <- n.lookupType.errors;
  top.closeErrors <-
    if n.lookupType.found && n.lookupType.dcl.isClosed
    then [errFromOrigin(n, s"Nonterminal ${n.name} is already closed")]
    else [];
  top.openErrors <-
    if n.lookupType.found && !n.lookupType.dcl.isClosed
    then [errFromOrigin(n, s"Nonterminal ${n.name} is already open")]
    else [];
}
concrete production nilNtName
top::NtNames ::=
{
  top.unparse = "";
  top.names = [];
}

nonterminal ProdNames with env, unparse, names, errors;
propagate env, errors on ProdNames;

concrete production consProdName
top::ProdNames ::= n::QName ns::ProdNames
{
  top.unparse = n.name ++ ", " ++ ns.unparse;
  top.names = n.lookupValue.fullName :: ns.names;
  top.errors <- n.lookupValue.errors;
}
concrete production nilProdName
top::ProdNames ::=
{
  top.unparse = "";
  top.names = [];
}

monoid attribute annotateErrors :: [Message];
nonterminal AttrNames with env, unparse, names, errors, annotateErrors;
propagate env, errors, annotateErrors on AttrNames;

concrete production consAttrName
top::AttrNames ::= n::QName ns::AttrNames
{
  top.unparse = n.name ++ ", " ++ ns.unparse;
  top.names = n.lookupAttribute.fullName :: ns.names;
  top.errors <- n.lookupAttribute.errors;
  top.annotateErrors <-
    if n.lookupAttribute.found && n.lookupAttribute.dcl.isAnnotation
    then [errFromOrigin(n, s"${n.name} is already an annotation")]
    else [];
}
concrete production nilAttrName
top::AttrNames ::=
{
  top.unparse = "";
  top.names = [];
}

