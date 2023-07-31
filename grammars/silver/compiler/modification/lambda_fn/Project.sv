grammar silver:compiler:modification:lambda_fn;

imports silver:compiler:definition:core;
imports silver:compiler:definition:env;
imports silver:compiler:definition:type;
imports silver:compiler:definition:type:syntax;
imports silver:compiler:analysis:typechecking:core;
imports silver:compiler:analysis:uniqueness;
imports silver:compiler:translation:java:core only finalType;

exports silver:compiler:modification:lambda_fn:java with silver:compiler:translation:java:core;
exports silver:compiler:modification:lambda_fn:java with silver:compiler:translation:java:type;

