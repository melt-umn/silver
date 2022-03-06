grammar silver_features;

record FooRecord {
  recordField1::Integer,
  recordField2::String,
};

equalityTest(
  fooRecord(recordField1=5, recordField2="Hello, world!").recordField1,
  5,
  Integer,
  silver_tests);

equalityTest(
  fooRecord(recordField1=5, recordField2="Hello, world!").recordField2,
  "Hello, world!",
  String,
  silver_tests);

record BarRecord without declaring {recordField1, recordField2} {
  -- different types this time!
  recordField1::Integer,
  recordField2::Integer,
};

equalityTest(
  barRecord(recordField1=4, recordField2=2).recordField2,
  2,
  Integer,
  silver_tests);
