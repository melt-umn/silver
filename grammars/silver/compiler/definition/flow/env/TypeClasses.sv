grammar silver:compiler:definition:flow:env;

import silver:compiler:definition:type:syntax;

attribute flowDefs, flowEnv occurs on
  ClassBody, ClassBodyItem, InstanceBody, InstanceBodyItem;
propagate flowDefs, flowEnv on
  ClassBody, ClassBodyItem, InstanceBody, InstanceBodyItem;
