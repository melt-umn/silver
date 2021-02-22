grammar silver:compiler:extension:strategyattr;

imports silver:core hiding id, sequence, fail;

imports silver:compiler:definition:core;
imports silver:compiler:definition:env;
imports silver:compiler:definition:type;
imports silver:compiler:definition:type:syntax hiding Arrow_t;
imports silver:compiler:extension:autoattr;
imports silver:compiler:extension:patternmatching;
imports silver:compiler:extension:list;
--imports silver:compiler:extension:rewriting;
imports silver:compiler:extension:silverconstruction;
imports silver:compiler:modification:let_fix;
imports silver:compiler:modification:lambda_fn;

exports silver:compiler:extension:strategyattr:convenience;
exports silver:compiler:extension:strategyattr:construction;
