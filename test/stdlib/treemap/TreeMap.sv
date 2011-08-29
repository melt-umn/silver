

import silver:testing;
import lib:extcore;
import silver:util:treemap;
import stdlib;

global e :: TreeMap<String Integer> = treeNew(compareString);

global t1 :: TreeMap<String Integer> =
  treeInsert("1", 1, 
   treeInsert("2", 2, 
    treeInsert("3", 3, 
     treeInsert("4", 4, 
      treeInsert("5", 5, 
       treeInsert("6", 6, 
        treeInsert("1", 1, 
         treeInsert("2", 2, 
          treeInsert("0", 0, 
           treeInsert("7", 7, e))))))))));

global t2 :: TreeMap<String Integer> =
  treeInsert("g", 1, 
   treeInsert("f", 2, 
    treeInsert("d", 3, 
     treeInsert("s", 4, 
      treeInsert("a", 5, 
       treeInsert("p", 6, 
        treeInsert("q", 1, 
         treeInsert("h", 2, 
          treeInsert("i", 0, 
           treeInsert("q", 7, t1))))))))));

equalityTest ( t1.blackHeight == 3, true, Boolean, core_tests ) ;
equalityTest ( t2.blackHeight == 3, true, Boolean, core_tests ) ;
equalityTest ( t1.debugHeight == 4, true, Boolean, core_tests ) ;
equalityTest ( t2.debugHeight == 6, true, Boolean, core_tests ) ;

equalityTest ( treeLookup("1", t2), [1,1], [Integer], core_tests ) ;
equalityTest ( treeLookup("2", t2), [2,2], [Integer], core_tests ) ;
equalityTest ( treeLookup("3", t2), [3], [Integer], core_tests ) ;
equalityTest ( treeLookup("4", t2), [4], [Integer], core_tests ) ;
equalityTest ( treeLookup("5", t2), [5], [Integer], core_tests ) ;
equalityTest ( treeLookup("6", t2), [6], [Integer], core_tests ) ;
equalityTest ( treeLookup("7", t2), [7], [Integer], core_tests ) ;
equalityTest ( treeLookup("8", t2), [], [Integer], core_tests ) ;
equalityTest ( treeLookup("0", t2), [0], [Integer], core_tests ) ;

equalityTest ( treeLookup("g", t2), [1], [Integer], core_tests ) ;
equalityTest ( treeLookup("h", t2), [2], [Integer], core_tests ) ;
equalityTest ( treeLookup("j", t2), [], [Integer], core_tests ) ;
equalityTest ( treeLookup("k", t2), [], [Integer], core_tests ) ;
equalityTest ( treeLookup("i", t2), [0], [Integer], core_tests ) ;
equalityTest ( treeLookup("f", t2), [2], [Integer], core_tests ) ;
equalityTest ( treeLookup("a", t2), [5], [Integer], core_tests ) ;

function genTree
TreeMap<String Integer> ::= i::Integer t::TreeMap<String Integer>
{
  return if i > 0 then genTree(i-1, treeInsert(toString(i), i, t))
         else t;
}

global t3 :: TreeMap<String Integer> = genTree(50, e);
equalityTest ( t3.blackHeight * 2 >= t3.debugHeight, true, Boolean, core_tests ) ;
equalityTest ( 6 <= t3.debugHeight, true, Boolean, core_tests ) ;

global t4 :: TreeMap<String Integer> = genTree(500, e);
equalityTest ( t4.blackHeight * 2 >= t4.debugHeight, true, Boolean, core_tests ) ;
equalityTest ( 9 <= t4.debugHeight, true, Boolean, core_tests ) ;

global t5 :: TreeMap<String Integer> = genTree(5000, e);
equalityTest ( t4.blackHeight * 2 >= t4.debugHeight, true, Boolean, core_tests ) ;
equalityTest ( 12 <= t4.debugHeight, true, Boolean, core_tests ) ;


global l1 :: [Pair<String Integer>] =
 [pair("hi", 2), pair("hello", 4), pair("hola", 6), pair("quepasa", 11)];

global t6 :: TreeMap<String Integer> = treeConvert(l1, t2);
equalityTest ( treeLookup("1", t6), [1,1], [Integer], core_tests ) ;
equalityTest ( treeLookup("5", t6), [5], [Integer], core_tests ) ;
equalityTest ( treeLookup("4", t6), [4], [Integer], core_tests ) ;
equalityTest ( treeLookup("h", t6), [2], [Integer], core_tests ) ;
equalityTest ( treeLookup("8", t6), [], [Integer], core_tests ) ;
equalityTest ( treeLookup("j", t6), [], [Integer], core_tests ) ;
equalityTest ( treeLookup("i", t6), [0], [Integer], core_tests ) ;
equalityTest ( treeLookup("f", t6), [2], [Integer], core_tests ) ;
equalityTest ( treeLookup("a", t6), [5], [Integer], core_tests ) ;
equalityTest ( treeLookup("hi", t6), [2], [Integer], core_tests ) ;
equalityTest ( treeLookup("hello", t6), [4], [Integer], core_tests ) ;
equalityTest ( treeLookup("hola", t6), [6], [Integer], core_tests ) ;
equalityTest ( treeLookup("quepasa", t6), [11], [Integer], core_tests ) ;

global l2 :: [Pair<String Integer>] =
 treeDeconvert( treeConvert( l1, e ) );

equalityTest ( head(l2).fst, "hello", String, core_tests ) ;
equalityTest ( head(tail(l2)).fst, "hi", String, core_tests ) ;
equalityTest ( head(tail(tail(l2))).fst, "hola", String, core_tests ) ;
equalityTest ( head(tail(tail(tail(l2)))).fst, "quepasa", String, core_tests ) ;

equalityTest ( head(l2).snd, 4, Integer, core_tests ) ;
equalityTest ( head(tail(l2)).snd, 2, Integer, core_tests ) ;
equalityTest ( head(tail(tail(l2))).snd, 6, Integer, core_tests ) ;
equalityTest ( head(tail(tail(tail(l2)))).snd, 11, Integer, core_tests ) ;

equalityTest ( null(tail(tail(tail(tail(l2))))), true, Boolean, core_tests ) ;

-- Extra testing stuff
synthesized attribute debugIdentity :: String occurs on TreeMap<a b>;
synthesized attribute debugDot :: String occurs on TreeMap<a b>;
synthesized attribute debugHeight :: Integer occurs on TreeMap<a b>;
synthesized attribute blackHeight :: Integer occurs on TreeMap<a b>;

function escape
String ::= s::String
{
  return implode("\\\"", explode("\"", s));
}

aspect production leaf
top::TreeMap<a b> ::= CMP :: Function(Integer ::= a a)
{
  top.debugIdentity = "leaf" ++ toString(genInt());
  top.debugDot = top.debugIdentity ++ "[color=black, style=filled];\n";
  top.debugHeight = 0;
  top.blackHeight = 0;
}

aspect production node
top::TreeMap<a b> ::= black::Boolean lefttree::TreeMap<a b> righttree::TreeMap<a b>
                      label::a  values::[b] 
                      CMP :: Function(Integer ::= a a)
{
  top.debugIdentity = "node" ++ toString(genInt());
  top.debugDot = top.debugIdentity ++ "[color=" ++ (if black then "black" else "red") ++ ", label=\"" ++ escape(hackUnparse(label) ++ " -> " ++ hackUnparse(values)) ++ "\"];\n"
              ++ top.debugIdentity ++ " -> " ++ lefttree.debugIdentity ++ " [label=\"l\"];\n"
              ++ top.debugIdentity ++ " -> " ++ righttree.debugIdentity ++ " [label=\"r\"];\n"
              ++ lefttree.debugDot ++ righttree.debugDot;
  top.debugHeight = if lefttree.debugHeight > righttree.debugHeight
                    then lefttree.debugHeight + 1
                    else righttree.debugHeight + 1;
  top.blackHeight = if lefttree.blackHeight != righttree.blackHeight
                    then -9999999
                    else righttree.blackHeight + if black then 1 else 0;
}

-- Write out a DOT file of the tree, for visual inspection if desired.
equalityTest ( unsafeTrace("", writeFile("rbtree.dotty.test.output", "digraph test {\n" ++ t6.debugDot ++ "}", unsafeIO())), "", String, core_tests ) ;


