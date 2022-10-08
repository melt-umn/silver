grammar silver:compiler:definition:core;

exports silver:langutil;

-- The following are grammar-wide imports for 'core'

-- The 'Type' syntax. (I made this separate to try to make s:d:core less of a "dump everything here" grammar.)
imports silver:compiler:definition:type:syntax;

-- Type Representation
imports silver:compiler:definition:type;

-- Environment Representation
imports silver:compiler:definition:env;

-- Utilities

option silver:compiler:definition:concrete_syntax;
option silver:compiler:definition:flow:syntax;
option silver:compiler:modification:lambda_fn;
option silver:compiler:modification:let_fix;
option silver:compiler:modification:primitivepattern;
option silver:compiler:modification:ffi;
option silver:compiler:modification:copper;
option silver:compiler:modification:defaultattr;
option silver:compiler:modification:collection;
option silver:compiler:modification:copper_mda;

-- The list extension doesn't need to be an option here,
-- it only needs to be one of silver:compiler:definition:type
-- option silver:compiler:modification:list;

option silver:compiler:extension:testing; -- TODO this is about that buggy experiment of Eric's...

-- These are somewhat less than desirable exports, due to the modularity analysis.
exports silver:compiler:analysis:typechecking:core;
exports silver:compiler:definition:flow:env;

