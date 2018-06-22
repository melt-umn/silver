grammar silver:driver;

import silver:reflect;

function compileInterface
IOVal<Either<String GrammarProperties>> ::= grammarName::String  genPath::String  ioin::IO
{
  local file :: String = "Silver.svi";
  
  local pr :: IO = 
    print("Found " ++ grammarName ++ "\n\t[" ++ genPath ++ file ++ "]\n", ioin);

  local text :: IOVal<String> =
    readFile(genPath ++ file, pr);

  local ir :: Either<String GrammarProperties> = deserialize(file, text.iovalue);

  return ioval(text.io, ir);
}

