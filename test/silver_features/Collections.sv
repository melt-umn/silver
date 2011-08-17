
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
  forwards to colNode(c,c);
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

attribute colList, colOr, colAnd, colFun, colProd occurs on ColNT;

abstract production colTest1
top::ColNT ::=
{
  top.colList := ["one"];
  top.colList <- ["two"];
  top.colOr := false;
  top.colOr <- true;
  top.colAnd := true;
  top.colAnd <- false;
  top.colFun := nothing();
  top.colFun <- just(1);
  top.colProd := colLeaf();
  top.colProd <- colLeaf();
}

equalityTest( colTest1().colList, ["one", "two"], [String], silver_tests );
equalityTest( colTest1().colOr, true, Boolean, silver_tests );
equalityTest( colTest1().colAnd, false, Boolean, silver_tests );
equalityTest( colTest1().colFun.isJust, true, Boolean, silver_tests );
equalityTest( fromMaybe(2, colTest1().colFun), 1, Integer, silver_tests );
equalityTest( colTest1().colProd.colSyn, "( a  c )( d  e ) b ", String, silver_tests );

abstract production colTest2
top::ColNT ::=
{
  top.colList := ["one", "two"];
  top.colList <- [];
  top.colOr := true;
  top.colOr <- false;
  top.colAnd := true;
  top.colAnd <- true;
  top.colFun := nothing();
  top.colFun <- nothing();
  top.colProd := colProdLeaf();
  top.colProd <- colLeaf();
}

equalityTest( colTest2().colList, ["one", "two"], [String], silver_tests );
equalityTest( colTest2().colOr, true, Boolean, silver_tests );
equalityTest( colTest2().colAnd, true, Boolean, silver_tests );
equalityTest( colTest2().colFun.isJust, false, Boolean, silver_tests );
equalityTest( colTest2().colProd.colSyn, " j  k ( d  e ) b ", String, silver_tests );

abstract production colTest3
top::ColNT ::=
{
  top.colList := [];
  top.colList <- ["one", "two"];
  top.colOr := false;
  top.colOr <- false;
  top.colAnd := false;
  top.colAnd <- true;
  top.colFun := just(1);
  top.colFun <- just(2);
  top.colProd := colLeaf();
  top.colProd <- colProdLeaf();
}

equalityTest( colTest3().colList, ["one", "two"], [String], silver_tests );
equalityTest( colTest3().colOr, false, Boolean, silver_tests );
equalityTest( colTest3().colAnd, false, Boolean, silver_tests );
equalityTest( colTest3().colFun.isJust, true, Boolean, silver_tests );
equalityTest( fromMaybe(4, colTest3().colFun), 1, Integer, silver_tests );
equalityTest( colTest3().colProd.colSyn, "( a  c ) j  k  b ", String, silver_tests );

