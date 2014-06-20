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
  copper_features:mdatests:ext prefix 'c' with '1:';
  copper_features:mdatests:ext2 prefix 'c' with '2:';
}

equalityTest ( shouldSucceed("ab1:c2:cba", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( shouldSucceed("abc", "").parseSuccess, false, Boolean, copper_tests );


