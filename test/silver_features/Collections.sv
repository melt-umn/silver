

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

