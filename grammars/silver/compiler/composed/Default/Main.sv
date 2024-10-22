grammar silver:compiler:composed:Default;

import silver:compiler:host;

parser svParse::Root {
  silver:compiler:host;
}

fun main IO<Integer> ::= args::[String] = cmdLineRun(args, svParse);
