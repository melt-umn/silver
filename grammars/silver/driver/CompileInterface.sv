grammar silver:driver;

import silver:reflect;
import core:monad;

function compileInterface
IOVal<Either<String GrammarProperties>> ::= grammarName::String  genPath::String  ioin::IO
{
  local file :: String = "Silver.svi";
  
  local pr :: IO = 
    print("Found " ++ grammarName ++ "\n\t[" ++ genPath ++ file ++ "]\n", ioin);

  local text :: IOVal<String> =
    readFile(genPath ++ file, pr);

  local ir :: Either<String GrammarProperties> =
    do (bindEither, returnEither) {
      ast::AST <- deserializeAST(file, text.iovalue);
      reify(ast);
    };

  return ioval(text.io, ir);
}

