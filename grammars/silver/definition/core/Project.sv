grammar silver:definition:core;

exports silver:langutil;

-- The following are grammar-wide imports for 'core'

-- The 'Type' syntax. (I made this separate to try to make s:d:core less of a "dump everything here" grammar.)
imports silver:definition:type:syntax;

-- Type Representation
imports silver:definition:type;

-- Environment Representation
imports silver:definition:env;

-- Utilities
imports silver:util;

option silver:definition:concrete_syntax;
option silver:definition:flow:syntax;
option silver:modification:lambda_fn;
option silver:modification:let_fix;
option silver:modification:primitivepattern;
option silver:modification:ffi;
option silver:modification:typedecl;
option silver:modification:copper;
option silver:modification:defaultattr;
option silver:modification:collection;
option silver:modification:copper_mda;

option silver:extension:testing; -- TODO this is about that buggy experiment of Eric's...

-- These are somewhat less than desirable exports, due to the modularity analysis.
exports silver:analysis:typechecking:core;
exports silver:definition:flow:env;

