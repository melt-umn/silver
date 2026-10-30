
import silver:reflect only getSynthesized;

-- Basic collection tests
--------------------------------------------------------------------------------

synthesized attribute colSyn::String with ++;
inherited attribute colInh::String with ++;

nonterminal ColNT with colSyn,colInh;

abstract production colLeaf
top::ColNT ::=
{
  top.colSyn := "(" ++ top.colInh ++ ")";
}

-- Actually, this is sorta weird: a inherited collection defined using just '=' a problem?
equalityTest ( decorate colLeaf() with {colInh = "x";}.colSyn, "(x)", String, silver_tests ) ;

abstract production colNode
top::ColNT ::= c1::ColNT c2::ColNT
{
  top.colSyn := c1.colSyn ++ c2.colSyn;
  c1.colInh := " a ";
  c2.colInh := " d ";
}

aspect production colNode
top::ColNT ::= c1::ColNT c2::ColNT
{
  top.colSyn <- " b ";
  c1.colInh <- " c ";
  c2.colInh <- " e ";
}

-- a quick note on validity: base SHOULD come before any contributed pieces
-- so it's okay and deterministic to test 1 := and 1 <-, but not more than 1 <-

equalityTest ( colNode(colLeaf(), colLeaf()).colSyn, "( a  c )( d  e ) b ", String, silver_tests ) ;

abstract production colFwdNode
top::ColNT ::= c::ColNT
{
  top.colSyn <- " q ";
  forwards to colNode(^c,^c);
}

equalityTest ( colFwdNode(colLeaf()).colSyn, "( a  c )( d  e ) b  q ", String, silver_tests ) ;

abstract production colProdLeaf
top::ColNT ::=
{
  production attribute lo::String with ++;
  lo := " j ";
  top.colSyn := lo;
}

aspect production colProdLeaf
top::ColNT ::=
{
  lo <- " k ";
}

equalityTest ( colFwdNode(colProdLeaf()).colSyn, " j  k  j  k  b  q ", String, silver_tests ) ;
equalityTest ( colNode(colProdLeaf(), colLeaf()).colSyn, " j  k ( d  e ) b ", String, silver_tests ) ;

function testLocalInheritedCollections
String ::=
{
  t.colInh := "x";
  local t :: ColNT = colLeaf();
  return t.colSyn;
}

equalityTest ( testLocalInheritedCollections(), "(x)", String, silver_tests ) ;


-- Threaded collection attributes
--------------------------------------------------------------------------------

threaded attribute colTInh, colTSyn::[Integer] with ++ occurs on ColNT;
propagate colTInh, colTSyn on ColNT;

aspect production colNode
top::ColNT ::= c1::ColNT c2::ColNT
{
  c2.colTInh <- [1];
  top.colTSyn <- [2];
}

aspect colTSyn on ColNT using <- of
| colLeaf() -> [3]
end;


equalityTest ( decorate colNode(colLeaf(), colNode(colLeaf(), colLeaf())) with {colTInh = [];}.colTSyn, [3, 1, 3, 1, 3, 2, 2], [Integer], silver_tests ) ;

-- Tests involving collection/noncollection assignments
--------------------------------------------------------------------------------

wrongCode "is a collection attribute" {
  aspect production colNode
  top::ColNT ::= c1::ColNT c2::ColNT
  { top.colSyn = ""; }
}

wrongCode "is a collection attribute" {
  aspect production colNode
  top::ColNT ::= c1::ColNT c2::ColNT
  { c1.colInh = ""; }
}

synthesized attribute colNotCol :: String occurs on ColNT;

wrongCode "can only be used for collections" {
  aspect production colNode
  top::ColNT ::= c1::ColNT c2::ColNT
  { top.colNotCol := ""; }
}

wrongCode "can only be used for collections" {
  aspect production colNode
  top::ColNT ::= c1::ColNT c2::ColNT
  { top.colNotCol <- ""; }
}

-- Different types of collection operators
--------------------------------------------------------------------------------

-- Note: we've already tested strings

synthesized attribute colList :: [String] with ++;
synthesized attribute colOr :: Boolean with ||;
synthesized attribute colAnd :: Boolean with &&;
synthesized attribute colFun :: Maybe<Integer> with orElse;
synthesized attribute colProd :: ColNT with colNode;
synthesized attribute colTCFun :: [Integer] with union;

attribute colList, colOr, colAnd, colFun, colProd, colTCFun occurs on ColNT;

abstract production colTest1
top::ColNT ::=
{
  top.colList := ["one"];
  top.colList <- ["two"];
  --top.colOr := false; -- default below
  top.colOr <- true;
  --top.colAnd := true; -- default below
  top.colAnd <- false;
  --top.colFun := nothing(); -- default below
  top.colFun <- just(1);
  --top.colProd := colLeaf(); -- default below
  top.colProd <- colLeaf();
  top.colTCFun := [1, 2, 3];
  top.colTCFun <- [2, 3, 4];
}

equalityTest( colTest1().colList, ["one", "two"], [String], silver_tests );
equalityTest( colTest1().colOr, true, Boolean, silver_tests );
equalityTest( colTest1().colAnd, false, Boolean, silver_tests );
equalityTest( colTest1().colFun.isJust, true, Boolean, silver_tests );
equalityTest( fromMaybe(2, colTest1().colFun), 1, Integer, silver_tests );
equalityTest( colTest1().colProd.colSyn, "( a  c )( d  e ) b ", String, silver_tests );
equalityTest( colTest1().colTCFun, [1, 2, 3, 4], [Integer], silver_tests );

abstract production colTest2
top::ColNT ::=
{
  top.colList := ["one", "two"];
  top.colList <- [];
  top.colOr := true;
  top.colOr <- false;
  --top.colAnd := true; -- default below
  top.colAnd <- true;
  --top.colFun := nothing(); -- default below
  top.colFun <- nothing();
  top.colProd := colProdLeaf();
  top.colProd <- colLeaf();
  --top.colTCFun := []; -- default below
}

equalityTest( colTest2().colList, ["one", "two"], [String], silver_tests );
equalityTest( colTest2().colOr, true, Boolean, silver_tests );
equalityTest( colTest2().colAnd, true, Boolean, silver_tests );
equalityTest( colTest2().colFun.isJust, false, Boolean, silver_tests );
equalityTest( colTest2().colProd.colSyn, " j  k ( d  e ) b ", String, silver_tests );

abstract production colTest3
top::ColNT ::=
{
  --top.colList := []; -- default below
  top.colList <- ["one", "two"];
  --top.colOr := false; -- default below
  top.colOr <- false;
  top.colAnd := false;
  top.colAnd <- true;
  top.colFun := just(1);
  top.colFun <- just(2);
  --top.colProd := colLeaf(); -- default below
  top.colProd <- colProdLeaf();
  --top.colTCFun := []; -- default below
}

aspect default production top::ColNT ::=
{
  top.colList := [];
  top.colOr := false;
  top.colAnd := true;
  top.colFun := nothing();
  top.colProd := colLeaf();
  top.colTCFun := [];
}

equalityTest( colTest3().colList, ["one", "two"], [String], silver_tests );
equalityTest( colTest3().colOr, false, Boolean, silver_tests );
equalityTest( colTest3().colAnd, false, Boolean, silver_tests );
equalityTest( colTest3().colFun.isJust, true, Boolean, silver_tests );
equalityTest( fromMaybe(4, colTest3().colFun), 1, Integer, silver_tests );
equalityTest( colTest3().colProd.colSyn, "( a  c ) j  k  b ", String, silver_tests );

-- Regression test for #290: a default '<-' contributes to a production with its own ':='.

nonterminal NT209 with colSyn;

production prod209
top::NT209 ::=
{
  top.colSyn := "foo";
}

aspect production prod209
top::NT209 ::=
{
  top.colSyn <- "bar";
}

aspect default production
top::NT209 ::=
{
  top.colSyn <- "baz";
}

equalityTest(prod209().colSyn, "foobarbaz", String, silver_tests);

-- A forwarding production with only '<-': the forward's value, this production's piece, then the default's piece at
-- this production.
abstract production fwd209
top::NT209 ::=
{
  top.colSyn <- "qux";
  forwards to prod209();
}

equalityTest(fwd209().colSyn, "foobarbazquxbaz", String, silver_tests);

-- Default collection equations.  A default '<-' contributes to each production with an equation of its own, after
-- that production's own pieces.  A production with only '<-' takes its base from its forward, else from the default's
-- ':=' alone; a production with no equation takes its forward's value, else the default's base and pieces.
synthesized attribute defColList :: [String] with ++;
synthesized attribute defColName :: String;
nonterminal DefColNT with defColList, defColName;

aspect default production
top::DefColNT ::=
{
  top.defColList := ["dBase"];
  top.defColList <- ["d:" ++ top.defColName];
}

-- No equation and no forward: the default's base and piece.
abstract production defColNone
top::DefColNT ::=
{
  top.defColName = "none";
}

-- Only '<-': the default's ':=', this production's piece, then the default's piece once.
abstract production defColPieces
top::DefColNT ::=
{
  top.defColName = "pieces";
  top.defColList <- ["own"];
}

-- A ':=' of its own: this production's base and piece, then the default's piece.
abstract production defColBase
top::DefColNT ::=
{
  top.defColName = "based";
  top.defColList := ["ownBase"];
  top.defColList <- ["own"];
}

-- Forwards with only '<-': the forward's value, this production's piece, then the default's piece at this production.
abstract production defColFwdPieces
top::DefColNT ::=
{
  top.defColName = "fwdPieces";
  top.defColList <- ["own"];
  forwards to defColNone();
}

-- Forwards with no equation: the forward's value alone.
abstract production defColFwdNone
top::DefColNT ::=
{
  top.defColName = "fwdNone";
  forwards to defColPieces();
}

equalityTest(defColNone().defColList, ["dBase", "d:none"], [String], silver_tests);
equalityTest(defColPieces().defColList, ["dBase", "own", "d:pieces"], [String], silver_tests);
equalityTest(defColBase().defColList, ["ownBase", "own", "d:based"], [String], silver_tests);
equalityTest(defColFwdPieces().defColList, ["dBase", "d:none", "own", "d:fwdPieces"], [String], silver_tests);
equalityTest(defColFwdNone().defColList, ["dBase", "own", "d:pieces"], [String], silver_tests);

-- A default '<-' without a default ':=' is no base: a production that does not forward and has no ':=' of its own has
-- none.  getSynthesized catches the run-time error and gives the message of its innermost cause.
synthesized attribute noBaseList :: [String] with ++;
nonterminal NoBaseNT with noBaseList;

aspect default production
top::NoBaseNT ::=
{
  top.noBaseList <- ["d"];
}

-- No equation of its own.
abstract production noBaseNone
top::NoBaseNT ::=
{}

-- Only '<-'.
abstract production noBasePieces
top::NoBaseNT ::=
{
  top.noBaseList <- ["own"];
}

equalityTest(
  case getSynthesized(noBaseNone(), "silver_features:noBaseList") of
  | left(msg) -> msg
  | right(l) -> implode(",", l)
  end,
  "No base (:=) defined for collection attribute silver_features:noBaseList in silver_features:noBaseNone " ++
    "or its nonterminal's default production",
  String, silver_tests);
equalityTest(
  case getSynthesized(noBasePieces(), "silver_features:noBaseList") of
  | left(msg) -> msg
  | right(l) -> implode(",", l)
  end,
  "No base (:=) defined for collection attribute silver_features:noBaseList in silver_features:noBasePieces " ++
    "or its nonterminal's default production",
  String, silver_tests);
