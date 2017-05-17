grammar silver:driver;


function compileInterface
IOVal<ParseResult<IRoot>> ::= sviParser::SVIParser  grammarName::String  genPath::String  ioin::IO
{
  local file :: String = "Silver.svi";
  
  local pr :: IO = 
    print("Found " ++ grammarName ++ "\n\t[" ++ genPath ++ file ++ "]\n", ioin);

  local text :: IOVal<String> =
    readFile(genPath ++ file, pr);

  local ir :: ParseResult<IRoot> = sviParser(text.iovalue, file);

  return ioval(text.io, ir);
}

