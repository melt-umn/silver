grammar type:syntax;

import silver:definition:type;
import silver:definition:type:syntax;
import silver:definition:core;
import silver:definition:env;
import edu:umn:cs:melt:testing;
import silver:util;

parser typarse :: Type {
 silver:definition:type:syntax;
 silver:definition:core; -- this is a bit buggy. copper will error, but still create the parser.
}

-- convenience
global tyva :: TypeVar = freshTyVar();
global tyvb :: TypeVar = freshTyVar();
global tya :: TypeExp = varTypeExp(tyva);
global tyb :: TypeExp = varTypeExp(tyvb);


function main 
IOVal<Integer> ::= largs::[String] ioin::IO
{
  local attribute args :: String;
  args = implode(" ", largs);

  local attribute tree :: Type;
  tree = typarse(args);
  tree.file = "STDIN";
  tree.grammarName = "s:t:d:in";
  
  tree.env = toEnv( 
                   appendDefs(
                    addNewLexicalTyVars("",error("g"), tree.lexicalTypeVariables),
                    
                    addNtDcl("",error("f"),"Pair", [tyva,tyvb], nonterminalTypeExp("Pair", [tya,tyb]),
                    emptyDefs()) ) );

  return ioval(print( args ++ "\n" ++ 
                tree.pp ++ "\n" ++ 
                prettyType(tree.typerep) ++ "\n" ++
                foldMessages(tree.errors) ++ "\n", ioin), 0);
}
