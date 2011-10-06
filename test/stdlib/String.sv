

-- implode
equalityTest ( implode(", ", []), "",
               String, core_tests ) ;
equalityTest ( implode(", ", ["foo"]), "foo",
               String, core_tests ) ;
equalityTest ( implode(", ", ["foo", "bar"]), "foo, bar",
               String, core_tests ) ;

-- explode
equalityTest ( explode(", ", "foo, bar"), ["foo", "bar"],
               [String], core_tests ) ;
equalityTest ( explode(", ", "foobar"), ["foobar"],
               [String], core_tests ) ;
equalityTest ( null(explode(", ", "")), true,
               Boolean, core_tests ) ;
equalityTest ( explode(", ", ""), [],
               [String], core_tests ) ;
equalityTest ( explode("", "foo"), ["f","o","o"],
               [String], core_tests ) ;
equalityTest ( explode("", ""), [],
               [String], core_tests ) ;

-- im ex
equalityTest ( implode(".", explode(".", "bv.cd.ef")), "bv.cd.ef",
               String, core_tests ) ;
equalityTest ( implode(".", explode(".", "bv.cd.ef.")), "bv.cd.ef.",
               String, core_tests ) ;
equalityTest ( implode(".", explode(".", ".bv.cd.ef")), ".bv.cd.ef",
               String, core_tests ) ;
equalityTest ( implode(".", explode(".", "bvcdef")), "bvcdef",
               String, core_tests ) ;
equalityTest ( implode(".", explode(".", "...")), "...",
               String, core_tests ) ;

-- indexOf
equalityTest ( indexOf("foo", "foobarfoo"), 0,
               Integer, core_tests ) ;
equalityTest ( indexOf("foo", "oobar"), -1,
               Integer, core_tests ) ;
equalityTest ( indexOf("bar", "oobarfoo"), 2,
               Integer, core_tests ) ;
-- lastIndexOf
equalityTest ( lastIndexOf("foo", "foobarfoo"), 6,
               Integer, core_tests ) ;
equalityTest ( lastIndexOf("foo", "oobar"), -1,
               Integer, core_tests ) ;
equalityTest ( lastIndexOf("bar", "oobarfoo"), 2,
               Integer, core_tests ) ;
-- substring
equalityTest ( substring(0, 3, "oobarfoo"), "oob",
               String, core_tests ) ;
equalityTest ( substring(3, 5, "oobarfoo"), "ar",
               String, core_tests ) ;
equalityTest ( substring(indexOf(":", "oob:arf:oo"), lastIndexOf(":","oob:arf:oo"), "oob:arf:oo"), ":arf",
               String, core_tests ) ;
-- startsWith
equalityTest ( startsWith("foo", "foobar"), true,
               Boolean, core_tests ) ;
equalityTest ( startsWith("foo", "barfoo"), false,
               Boolean, core_tests ) ;
equalityTest ( startsWith("foo", ""), false,
               Boolean, core_tests ) ;
-- endsWith
equalityTest ( endsWith("foo", "foobarfoo"), true,
               Boolean, core_tests ) ;
equalityTest ( endsWith("foo", "foobar"), false,
               Boolean, core_tests ) ;
equalityTest ( endsWith("foo", ""), false,
               Boolean, core_tests ) ;

-- substitute
equalityTest ( substitute("abc", "def", "hijklmnop"), "hijklmnop",
               String, core_tests ) ;
equalityTest ( substitute("abc", "def", "abcdefhijklmnop"), "defdefhijklmnop",
               String, core_tests ) ;
equalityTest ( substitute("abc", "def", "abcabc"), "defdef",
               String, core_tests ) ;

-- replicate
equalityTest ( replicate(3, " "), "   ",
               String, core_tests ) ;
equalityTest ( replicate(2, "abc"), "abcabc",
               String, core_tests ) ;
equalityTest ( replicate(0, " "), "",
               String, core_tests ) ;

-- isDigit isAlpha isSpace isLower isUpper TODO

-- toIntSafe
equalityTest ( toIntSafe("2147483647").isJust, true,
               Boolean, core_tests ) ;
equalityTest ( toIntSafe("2147483647").fromJust, 2147483647,
               Integer, core_tests ) ;
equalityTest ( toIntSafe("2147483648").isJust, false,
               Boolean, core_tests ) ;



-- from lib:extcore!

equalityTest ( stripWhiteSpace ("asdf qwer \n asdf \t asdf\n"),
               "asdfqwerasdfasdf", String, core_tests) ;

equalityTest ( stripExtraWhiteSpace (" asdf qwer \n asdf \t asdf\n aa"),
               "asdf qwer asdf asdf aa", String, core_tests) ;

equalityTest ( replaceChars ( ".", "_", "sdf..sd_"), "sdf__sd_", String, core_tests) ;

equalityTest ( replaceChars ( ".", "_", ".1.2.3."), "_1_2_3_", String, core_tests) ;



