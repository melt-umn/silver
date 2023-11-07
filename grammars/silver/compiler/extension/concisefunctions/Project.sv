grammar silver:compiler:extension:concisefunctions;

imports silver:compiler:definition:core;
imports silver:compiler:modification:lambda_fn;
imports silver:compiler:definition:type:syntax hiding Arrow_t; -- We use lambda_fn:Arrow_t
imports silver:compiler:definition:type;
imports silver:compiler:definition:env;
imports silver:compiler:definition:flow:env;