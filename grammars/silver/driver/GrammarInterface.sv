grammar silver:driver;


function compileInterface
IOVal<ParseResult<IRoot>> ::= sviParser::SVIParser  genPath::String  ioin::IO
{
  local file :: String = "Silver.svi";
  
  local attribute text :: IOVal<String>;
  text = readFile(genPath ++ file, print("\t[" ++ genPath ++ file ++ "]\n", ioin));

  local attribute ir :: ParseResult<IRoot>;
  ir = sviParser(text.iovalue, file);

  return ioval(text.io, ir);
}

