grammar lib:testing ;

nonterminal Test with ioIn, ioOut ;
nonterminal TestSuite with ioIn, ioOut ;

synthesized attribute ioOut :: IO ;
inherited attribute ioIn :: IO ;

synthesized attribute pass :: Boolean occurs on Test ;

synthesized attribute msg :: String occurs on Test, TestSuite ;
synthesized attribute numTests :: Integer occurs on TestSuite ;
synthesized attribute numPassed :: Integer occurs on TestSuite ;
synthesized attribute numFailed :: Integer occurs on TestSuite ;


abstract production defTest
t::Test ::=
{ t.pass = false ;
  t.msg = "" ;
  t.ioOut = t.ioIn ;
}

abstract production testsCollect  
t::TestSuite ::= tss::[TestSuite]
{ forwards to consolidateTestSuite ( tss ) ;  }

abstract production tests
t::TestSuite ::= ts::[Test]
{ forwards to testsAsNT ( ts ) ;  }


function testsAsNT 
TestSuite ::= ts::[Test]
{
 return if   null (ts)
        then testNone()
        else testCons( head(ts), testsAsNT(tail(ts)) ) ;
}

function consolidateTestSuite
TestSuite ::= ts::[TestSuite]
{
 return if   null (ts)
        then testSuiteNone()
        else testSuiteSeq ( head(ts), consolidateTestSuite(tail(ts)) ) ;
}

abstract production testNone
ts::TestSuite ::=
{
 ts.msg = "" ;
 ts.numTests = 0 ;
 ts.numPassed = 0 ;
 ts.numFailed = ts.numTests - ts.numPassed ;
 ts.ioOut = ts.ioIn ;
}

abstract production testCons
ts::TestSuite ::= t::Test rest::TestSuite
{
 ts.msg = (if t.pass then "." else "\n" ++ t.msg) ++ rest.msg ;
 ts.numTests = 1 + rest.numTests ;
 ts.numPassed = (if t.pass then 1 else 0) + rest.numPassed;
 ts.numFailed = ts.numTests - ts.numPassed ;

 rest.ioIn = ts.ioIn ;
 t.ioIn = rest.ioOut ;
 ts.ioOut = t.ioOut ;

{-
 -- pass the IO token though based on structure.  This causes
 -- the actions to be executed in reverse order.
 t.ioIn = ts.ioIn ;
 rest.ioIn = t.ioOut ;
 ts.ioOut = rest.ioOut ;
 -}
}

abstract production testSuiteNone
ts::TestSuite ::=
{
 ts.msg = "" ;
 ts.numTests = 0 ;
 ts.numPassed = 0 ;
 ts.numFailed = ts.numTests - ts.numPassed ;
 ts.ioOut = ts.ioIn ;
}

abstract production testSuiteSeq
ts::TestSuite ::= ts1::TestSuite ts2::TestSuite
{
 ts.msg = ts1.msg ++ ts2.msg ;
 ts.numTests = ts1.numTests + ts2.numTests ;
 ts.numPassed = ts1.numPassed + ts2.numPassed ;
 ts.numFailed = ts.numTests - ts.numPassed ;
 ts1.ioIn = ts.ioIn ;
 ts2.ioIn = ts1.ioOut ;
 ts.ioOut = ts2.ioOut ;
}
