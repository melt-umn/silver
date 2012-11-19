grammar silver:definition:env;

-- TODO: it'd be nice to find a way to rejigger things so these imports can go away.
import silver:util:cmdargs only CmdArgs;
import silver:definition:flow:driver only ProductionGraph;
import silver:driver:util only RootSpec;

{- This grammar contains common definitions of attributes that
   are widely used in the Silver compiler.
   
   Attributes should NOT be added here lightly!
   Include a justification for each one.
   
   These are truely cross-cutting attributes, not simple
   things that occurs on a few pieces of syntax.
   A good rule of thumb is: is it used by RootSpec Root and IRoot?
 --------------------------------------------------------------------}

--
-- RootSpec, Root and IRoot grammar graph-related attributes.
--

{--
 - The name of the grammar this RootSpec represents.
 -}
synthesized attribute declaredName :: String;
{--
 - Grammars directly depended upon by this grammar.
 - i.e. imports, exports, parser components, etc.
 - NOT options, or triggers, or transitive dependencies.
 -}
synthesized attribute moduleNames :: [String];
{--
 - Grammars DIRECTLY exported by this grammar.
 -}
synthesized attribute exportedGrammars :: [String];
{--
 - Grammars this grammar specifies as optional modifications.
 - (i.e. grammars that introduce more productions that do not forward)
 -}
synthesized attribute optionalGrammars :: [String];
{--
 - A list of triggered builds. Format is actually [ [build x, with gram], ... ]
 -}
synthesized attribute condBuild :: [[String]];
{--
 - A list of TRUE dependencies of this grammar.
 - Closes over moduleNames using exports & triggers.
 -}
synthesized attribute allGrammarDependencies :: [String];


--
-- Standard "little attributes," used almost universally in the compiler.
--

{--
 - The pretty pretty of a syntax tree.
 -}
synthesized attribute pp :: String;
{--
 - The location of this node in the original source file.
 -}
synthesized attribute location :: Location;
{--
 - A list of definitions exported by this particular grammar.
 -}
synthesized attribute defs :: [Def];
{--
 - The environment. Dun dun dunnn.
 -}
autocopy attribute env :: Decorated Env;

--
-- Top-level, compiler-wide information passed down by the build process
--

{--
- All grammars Silver looked at. Despite the name, including interface files.
-}
autocopy attribute compiledGrammars :: EnvTree<Decorated RootSpec>;
{--
- Compiler configuration information, made available everywhere.
-}
autocopy attribute config :: Decorated CmdArgs;
{--
- Flow information computed for this grammar
-}
autocopy attribute productionFlowGraphs :: [ProductionGraph];
autocopy attribute grammarFlowTypes :: EnvTree<Pair<String String>>;

