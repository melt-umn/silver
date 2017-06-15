grammar parsers;

imports silver:testing;
imports lib:extcore;
imports disamb as dis;
imports prodMod as pro;
--imports recNt as rec;
imports startMissing as sta;
imports noConcrete as ncc;
imports mda as mda;
exports host;

mainTestSuite copper_tests;

equalityTest(dis:extendedParser("","").parseSuccess, false, Boolean, copper_tests );
equalityTest(pro:extendedParser("","").parseSuccess, false, Boolean, copper_tests );
--equalityTest(rec:extendedParser("","").parseSuccess, false, Boolean, copper_tests );
equalityTest(sta:extendedParser("","").parseSuccess, false, Boolean, copper_tests );
equalityTest(ncc:extendedParser("","").parseSuccess, false, Boolean, copper_tests );
equalityTest(mda:extendedParser("","").parseSuccess, false, Boolean, copper_tests );
