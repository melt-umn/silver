grammar silver:definition:env;

-- TODO: it'd be nice to find a way to rejigger things so these imports can go away.
import silver:util:cmdargs only CmdArgs;
import silver:definition:flow:driver only ProductionGraph, FlowType;
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
monoid attribute moduleNames :: [String] with [], ++;
{--
 - Grammars DIRECTLY exported by this grammar.
 -}
monoid attribute exportedGrammars :: [String] with [], ++;
{--
 - Grammars this grammar specifies as optional modifications.
 - (i.e. grammars that introduce more productions that do not forward)
 -}
monoid attribute optionalGrammars :: [String] with [], ++;
{--
 - A list of triggered builds. Format is actually [ [build x, with gram], ... ]
 -}
monoid attribute condBuild :: [[String]] with [], ++;
{--
 - A list of TRUE dependencies of this grammar.
 - Closes over moduleNames using exports & triggers.
 -}
monoid attribute allGrammarDependencies :: [String] with [], ++;
{--
 - A list of attribute occurences that are exported by this particular grammar.
 - Seperate from defs as a workaround for circular dependency issues.
 -}
monoid attribute occursDefs :: [DclInfo] with [], ++;


--
-- Standard "little attributes," used almost universally in the compiler.
--

{--
 - A list of definitions exported by this particular grammar.
 -}
monoid attribute defs :: [Def] with [], ++;
{--
 - The environment. Dun dun dunnn.
 -}
autocopy attribute env :: Decorated Env;

--
-- Top-level, compiler-wide information passed down by the build process
-- TODO: these don't necessarily make sense. Can we move them pleaaase?!

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
autocopy attribute productionFlowGraphs :: EnvTree<ProductionGraph>;
autocopy attribute grammarFlowTypes :: EnvTree<FlowType>;

{--
 - The path to the origin of this root spec
 -}
synthesized attribute grammarSource :: String;
{--
 - The modification time of the source .sv files of this grammar.
 -}
synthesized attribute grammarTime :: Integer;
{--
 - The modification time of the interface file of this grammar.
 - If the grammar was read from source, == grammarTime.
 -}
synthesized attribute interfaceTime :: Integer;



