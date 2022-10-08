grammar copper_features:mdatests;

imports silver:testing ;
imports copper_features;


import copper_features:mdatests:host;
import copper_features:mdatests:ext;

copper_mda test(doParse) {
  copper_features:mdatests:ext;
}

parser shouldSucceed :: Root {
  copper_features:mdatests:host;
  
  -- Explicitly give token name, use terminal literal as separator
  copper_features:mdatests:ext prefix copper_features:mdatests:ext:C with '1:';
  
  -- Prefix marking terminals, use prefix separator
  copper_features:mdatests:ext2 prefix with "2";

  prefer copper_features:mdatests:ext:C over copper_features:mdatests:ext2:C;
}

equalityTest ( shouldSucceed("ab1:c2:cba", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( shouldSucceed("abc", "").parseSuccess, true, Boolean, copper_tests );


