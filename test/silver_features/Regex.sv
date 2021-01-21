grammar silver_features;

import silver:regex;

equalityTest("abc" =~ /abc/, true, Boolean, silver_tests);
equalityTest("cba" =~ /abc/, false, Boolean, silver_tests);
equalityTest("acxyxyxyxyccxyc" =~ /a(xy|c)+/, true, Boolean, silver_tests);
equalityTest("acxyxyxyxxyccxyc" =~ /a(xy|c)+/, false, Boolean, silver_tests);
equalityTest("acxyxyxyxyccxyca" =~ /a(xy|c)+a/, true, Boolean, silver_tests);
equalityTest("acxyxyxyxxyccxyca" =~ /a(xy|c)+a/, false, Boolean, silver_tests);
equalityTest("" =~ //, true, Boolean, silver_tests);
equalityTest("ac" =~ //, false, Boolean, silver_tests);
equalityTest("asd_f323" =~ /[a-zA-Z_][a-zA-Z_0-9]*/, true, Boolean, silver_tests);
equalityTest("asd_f3@23" =~ /[a-zA-Z_][a-zA-Z_0-9]*/, false, Boolean, silver_tests);
equalityTest("{-{-a-}{--bc--}d-}" =~ /\{\-(\{\-([^\-]|\-+[^\}\-])*\-+\}|[^\-]|\-+[^\}\-])*\-+\}/, true, Boolean, silver_tests);
equalityTest("{-{-a{--}-}{--bc--}d-}" =~ /\{\-(\{\-([^\-]|\-+[^\}\-])*\-+\}|[^\-]|\-+[^\}\-])*\-+\}/, false, Boolean, silver_tests);

equalityTest("// abc" =~ /\/\/.*/, true, Boolean, silver_tests);
equalityTest("// a\nbc" =~ /\/\/.*/, false, Boolean, silver_tests);

equalityTest(" \r\n\t" =~ /[\r\n\t ]+/, true, Boolean, silver_tests);
equalityTest(" blah\n" =~ /[\r\n\t ]+/, false, Boolean, silver_tests);
equalityTest("" =~ /[\r\n\t ]+/, false, Boolean, silver_tests);
