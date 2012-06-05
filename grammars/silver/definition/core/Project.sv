grammar silver:definition:core;

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
option silver:modification:let_fix;
option silver:modification:patternmatching;
option silver:modification:ffi;
option silver:modification:typedecl;
option silver:modification:copper;
option silver:modification:defaultattr;
option silver:modification:copper_mda;

option silver:extension:testing; -- TODO this is about that buggy experiment of Eric's...

exports silver:analysis:typechecking:core;
exports silver:definition:flow:env;
imports silver:analysis:typechecking:core;
imports silver:definition:flow:env;

