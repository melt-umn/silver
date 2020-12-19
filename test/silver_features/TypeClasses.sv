grammar silver_features;

class CFoo a
{
  cx :: a;
}

class CFoo a => CBar a
{}

class CBaz a
{
  cy :: a;
}

instance CBaz a => CFoo [a]
{
  cx = [cy];
}

instance CBaz Integer
{
  cy = 42;
}

instance CBaz String
{
  cy = "hello";
}

instance CBaz a => CBar [a]
{}

global cxi::[Integer] = cx;
equalityTest(hackUnparse(cxi), "[42]", String, silver_tests);

global cxs::[String] = cx;
equalityTest(hackUnparse(cxs), "[\"hello\"]", String, silver_tests);
