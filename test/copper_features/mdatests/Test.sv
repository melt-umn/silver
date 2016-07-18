grammar copper_features:mdatests;

imports silver:testing ;
imports lib:extcore ;
imports copper_features;


import copper_features:mdatests:host;
import copper_features:mdatests:ext;

copper_mda test(doParse) {
  copper_features:mdatests:ext;
}

parser shouldSucceed :: Root {
  copper_features:mdatests:host;
  copper_features:mdatests:ext prefix copper_features:mdatests:ext:C with '1:'; -- Explicitly give token name
  copper_features:mdatests:ext2 prefix with '2:'; -- Prefix marking terminals

  prefer copper_features:mdatests:ext:C over copper_features:mdatests:ext2:C;
}

equalityTest ( shouldSucceed("ab1:c2:cba", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( shouldSucceed("abc", "").parseSuccess, true, Boolean, copper_tests );


