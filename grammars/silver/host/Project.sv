grammar silver:host;

{- Silver is built as an extensible language with a core "host" language and a
 - number of extensions and modifications containing additional features.
 - However many of these extensions we typically always want to include by
 - when building extended versions of Silver, and it becomes cumbersome to list
 - them repeatedly.
 - Thus we provide this grammar that exports all the components of the
 - "default" Silver host language in one place.
 - Note that this list may grow over time.
 -} 

-- The "core" host language:
exports silver:host:core;

-- Modifications to Silver = optional features that are not pure extensions.
-- These are explicitly annotated as "options" within the core host language
exports silver:modification:let_fix;
exports silver:modification:lambda_fn;
exports silver:modification:collection;
exports silver:modification:primitivepattern;
exports silver:modification:autocopyattr;
exports silver:modification:ffi;
exports silver:modification:typedecl;
exports silver:modification:copper;
exports silver:modification:defaultattr;
-- slight hacks, for the moment
exports silver:modification:copper_mda;
exports silver:modification:impide;

-- Pure extensions to Silver
exports silver:extension:doc;
exports silver:extension:convenience;
exports silver:extension:list; -- Not really a pure extension, yuck.
exports silver:extension:easyterminal;
exports silver:extension:deprecation;
exports silver:extension:testing;
exports silver:extension:auto_ast;
exports silver:extension:templating;
exports silver:extension:patternmatching;
exports silver:extension:treegen;
exports silver:extension:doc;
exports silver:extension:functorattrib;
exports silver:extension:monad;
exports silver:extension:reflection;
exports silver:extension:rewriting;
exports silver:extension:silverconstruction;
exports silver:extension:astconstruction;
exports silver:extension:constructparser;
exports silver:extension:castmatching;

-- Other generally useful stuff:
exports silver:translation:java;
exports silver:driver;
exports silver:analysis:warnings:flow;
exports silver:analysis:warnings:exporting;
