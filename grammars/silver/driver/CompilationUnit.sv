grammar silver:driver;

synthesized attribute compiledList :: [Decorated RootSpec];
synthesized attribute needGrammars :: [String];
synthesized attribute seenGrammars :: [String];
synthesized attribute interfaces :: [Decorated Interface];

{--
 - Top-level control flow for various compilation tasks.
 -}
nonterminal CompilationUnit with io, compiledGrammars, rParser, iParser, compiledList, needGrammars, seenGrammars, interfaces;


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
  -- the current grammar
  production attribute now :: Grammar;
  now = compileGrammar(iIn, head(need), sPath, clean, genPath);
  now.rParser = top.rParser;
  now.iParser = top.iParser;
  now.compiledGrammars = top.compiledGrammars;

  local attribute new_seen :: [String];
  new_seen = [head(need)] ++  seen;

  -- This line first removes from the the new grammarIncludes the ones we have already
  -- compiled (new_seen) and then appends them to the list of grammars we still need to
  -- parse.  It then makes a set of the strings so we do not compile the same grammar
  -- twice.
  local attribute new_need :: [String];
  new_need = makeSet(rem(now.rSpec.moduleNames, new_seen) ++ tail(need));

  top.seenGrammars = if null(need) then seen else recurse.seenGrammars;
  top.needGrammars = [];

  --the recursion
  production attribute recurse :: CompilationUnit;
  recurse = compileGrammars(now.io, sPath, new_need, new_seen, clean, genPath);
  recurse.rParser = top.rParser;
  recurse.iParser = top.iParser;
  recurse.compiledGrammars = top.compiledGrammars;
 
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
  local attribute foundGrammar :: [String];
  foundGrammar = noninductiveExpansion(seen, normalizeCondBuilds(sofar));

  -- the current grammar
  production attribute now :: CompilationUnit;
  now = compileGrammars(iIn, sPath, foundGrammar, seen, clean, genPath);
  now.rParser = top.rParser;
  now.iParser = top.iParser;
  now.compiledGrammars = top.compiledGrammars;

  top.seenGrammars = if null(foundGrammar) then seen else recurse.seenGrammars;
  top.needGrammars = [];

  --the recursion
  production attribute recurse :: CompilationUnit;
  recurse = compileConditionals(now.io, sPath, now.seenGrammars, clean, now.compiledList ++ getSpecs(now.interfaces) ++ sofar, genPath);
  recurse.rParser = top.rParser;
  recurse.iParser = top.iParser;
  recurse.compiledGrammars = top.compiledGrammars;

  top.io = if null(foundGrammar) then iIn else recurse.io;

  top.compiledList = if null(foundGrammar)
		     then []
		     else now.compiledList ++ recurse.compiledList;

  top.interfaces = if null(foundGrammar)
		   then []
		   else now.interfaces ++ recurse.interfaces;
}


--This production compiles a list of grammars (each String in the list
--represents an entire grammar).  It does not track down new grammars, 
--that is handled by the compileGrammars production.  It produces a list of
--compiled Grammars and a list of grammars that it needs to be found.
--it takes in as an inherited attribute a list of found grammars.
--idealy there is a found grammar for every needed grammar.
abstract production compileAllExtra
top::CompilationUnit ::= grams::[[String]] need::[String] seen::[String]
{
  local attribute g :: String;
  g = head(tail(head(grams)));  
  
  local attribute gn :: String;
  gn = head(head(grams));

  -- the root of the grammar we are compiling
  local attribute r :: Root;
  r = top.rParser(g, "internal " ++ gn).parseTree; -- Since this is an "internal grammar" I'll assume it never parse errors.
  r.grammarName = gn;
  r.compiledGrammars = top.compiledGrammars;
  r.globalImports = toEnv(r.importedDefs);
  r.env = toEnv(r.defs);
  r.file = gn;

  --the root spec
  local attribute rs :: Decorated RootSpec;
  rs = rootSpecRoot(r);

  local attribute inf :: Interface;
  inf = rootSpecInterface(rs);

  --the set of grammars that we have seen and do not need to be compiled.
  local attribute new_seen :: [String];
  new_seen = [r.declaredName] ++ seen;

  --this is the set of grammars that we need compileGrammars to track down for us.
  local attribute new_need :: [String];
  new_need = makeSet(rs.moduleNames ++ need);

  --the recursion.
  local attribute recurse :: CompilationUnit;
  recurse = compileAllExtra(tail(grams), new_need, new_seen);
  recurse.rParser = top.rParser;
  recurse.iParser = top.iParser;
  recurse.compiledGrammars = top.compiledGrammars;
 
  top.compiledList = if null(grams) then [] else [rs] ++ recurse.compiledList;

  --This is kindof tricky.  We keeping passing down a growing list of need and seens (look at recurse)
  --then at the end we mod need by seen.  these are the grammars we have not seen in the extra grammars
  --and thus we need someone else to find for us.
  top.needGrammars = if null(grams) then rem(need, seen) else recurse.needGrammars;  
  top.seenGrammars = if null(grams) then seen else recurse.seenGrammars;
  top.interfaces = if null(grams) then [] else [inf] ++ recurse.interfaces;
}

