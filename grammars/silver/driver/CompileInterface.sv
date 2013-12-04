grammar silver:driver;


function compileInterface
IOVal<ParseResult<IRoot>> ::= sviParser::SVIParser  genPath::String  ioin::IO
{
  local file :: String = "Silver.svi";
  
  local text :: IOVal<String> = readFile(genPath ++ file, print("\t[" ++ genPath ++ file ++ "]\n", ioin));

  local ir :: ParseResult<IRoot> = sviParser(text.iovalue, file);

  return ioval(text.io, ir);
}

