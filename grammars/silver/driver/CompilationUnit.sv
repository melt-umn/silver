grammar silver:driver;

{--
 - Top-level control flow for various compilation tasks.
 -}
nonterminal CompilationUnit with config, io, compiledGrammars, rParser, iParser, compiledList, seenGrammars, interfaces;


{--
 - The list of grammar names seen in total, so far.
 -}
synthesized attribute seenGrammars :: [String];
{--
 - The list of interface files used.
 -}
synthesized attribute interfaces :: [Decorated Interface];
{--
 - The list of RootSpecs belonging to grammars that were actually parsed.
 -}
synthesized attribute compiledList :: [Decorated RootSpec];


{--
 - Beginning with those in 'need', chase down and compile all grammars necessary to build those grammars in 'need'
 -
 - @param sPath   The search path (i.e. grammar path)
 - @param need    The list of needed grammars.
 - @param seen    The list of already compiled grammars. (initially [], most likely.)
 - @param clean   If true, ignore interface files entirely.
 - @param genPath The generated directory path. (i.e. where src/ and bin/ are)
 -}
abstract production compileGrammars
top::CompilationUnit ::= iIn::IO sPath::[String] need::[String] seen::[String] clean::Boolean genPath::String
{
  -- Build the first gramamr in the need list.
  production attribute now :: Grammar;
  now = compileGrammar(iIn, head(need), sPath, clean, genPath);
  now.rParser = top.rParser;
  now.iParser = top.iParser;
  now.compiledGrammars = top.compiledGrammars;
  now.config = top.config;

  -- Add the grammar we just build to the seen list
  local attribute new_seen :: [String];
  new_seen = [head(need)] ++  seen;

  -- Add this grammar's dependencies that we haven't already seen to the need list.
  local attribute new_need :: [String];
  new_need = makeSet(rem(now.rSpec.moduleNames, new_seen) ++ tail(need));

  -- Recurse for the rest of the grammars needed.
  production attribute recurse :: CompilationUnit;
  recurse = compileGrammars(now.io, sPath, new_need, new_seen, clean, genPath);
  recurse.rParser = top.rParser;
  recurse.iParser = top.iParser;
  recurse.compiledGrammars = top.compiledGrammars;
  recurse.config = top.config;
 

  top.seenGrammars = if null(need) then seen else recurse.seenGrammars;

  top.io = if null(need) then iIn else recurse.io;

  top.compiledList = if null(need)  
		     then [] 
		     else if !now.found || !null(now.interfaces)
			  then recurse.compiledList
			  else [now.rSpec] ++ recurse.compiledList;

  top.interfaces = if null(need)
		   then [] 
		   else if !now.found 
			then recurse.interfaces
			else now.interfaces ++ recurse.interfaces;
}


{--
 - Given the current compile state as input (seen, sofar, etc), triggers the building of
 - all appropriate conditionally compiled grammars.
 -}
abstract production compileConditionals
top::CompilationUnit ::= iIn::IO sPath::[String] seen::[String] clean::Boolean sofar::[Decorated RootSpec] genPath::String
{
  -- Compute whether there are any new triggered builds, given 'seen' as the current list of built grammars
  local attribute foundGrammar :: [String];
  foundGrammar = noninductiveExpansion(seen, normalizeCondBuilds(sofar));

  -- the current triggered grammar(s?)
  production attribute now :: CompilationUnit;
  now = compileGrammars(iIn, sPath, foundGrammar, seen, clean, genPath);
  now.rParser = top.rParser;
  now.iParser = top.iParser;
  now.compiledGrammars = top.compiledGrammars;
  now.config = top.config;

  -- the recursion
  production attribute recurse :: CompilationUnit;
  recurse = compileConditionals(now.io, sPath, now.seenGrammars, clean, now.compiledList ++ getSpecs(now.interfaces) ++ sofar, genPath);
  recurse.rParser = top.rParser;
  recurse.iParser = top.iParser;
  recurse.compiledGrammars = top.compiledGrammars;
  recurse.config = top.config;

  top.seenGrammars = if null(foundGrammar) then seen else recurse.seenGrammars;

  top.io = if null(foundGrammar) then iIn else recurse.io;

  top.compiledList = if null(foundGrammar)
		     then []
		     else now.compiledList ++ recurse.compiledList;

  top.interfaces = if null(foundGrammar)
		   then []
		   else now.interfaces ++ recurse.interfaces;
}

