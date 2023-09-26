grammar silver:compiler:modification:list;

imports silver:compiler:definition:type;
imports silver:compiler:definition:env;
imports silver:compiler:definition:core;
imports silver:compiler:definition:flow:env;

exports silver:compiler:modification:list:java with silver:compiler:translation:java:core;  -- special case for []
exports silver:compiler:modification:list:java:type with silver:compiler:translation:java:type;  -- listCtrType translation
