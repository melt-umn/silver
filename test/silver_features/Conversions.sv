grammar silver_features;

equalityTest(toInteger(3.14), 3, Integer, silver_tests);
equalityTest(toInteger(1), 1, Integer, silver_tests);
equalityTest(toInteger(true), 1, Integer, silver_tests);
equalityTest(toInteger(false), 0, Integer, silver_tests);
equalityTest(toInteger("42"), 42, Integer, silver_tests);

equalityTest(toFloat(3.14), 3.14, Float, silver_tests);
equalityTest(toFloat(1), 1.0, Float, silver_tests);
equalityTest(toFloat(true), 1.0, Float, silver_tests);
equalityTest(toFloat(false), 0.0, Float, silver_tests);
equalityTest(toFloat("12.34"), 12.34, Float, silver_tests);

equalityTest(toBoolean(3.14), true, Boolean, silver_tests);
equalityTest(toBoolean(1), true, Boolean, silver_tests);
equalityTest(toBoolean(true), true, Boolean, silver_tests);
equalityTest(toBoolean(false), false, Boolean, silver_tests);
equalityTest(toBoolean("true"), true, Boolean, silver_tests);
equalityTest(toBoolean("false"), false, Boolean, silver_tests);

equalityTest(toString(3.14), "3.14", String, silver_tests);
equalityTest(toString(1), "1", String, silver_tests);
equalityTest(toString(true), "true", String, silver_tests);
equalityTest(toString(false), "false", String, silver_tests);
equalityTest(toString("hello"), "hello", String, silver_tests);
