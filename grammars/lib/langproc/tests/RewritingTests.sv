grammar lib:langproc:tests ;

imports lib:langproc ;
imports lib:testing ;
imports lib:extcore ;

mainTestSuite rw ;

-- simple test just to get started
equalityTest ("ABC", "ABC", String, rw) ;

-- setting up some ids and sample expressions
global x :: Id_t = terminal(Id_t, "x") ;
global y :: Id_t = terminal(Id_t, "y") ;

global a :: Id_t = terminal(Id_t, "a") ;
global b :: Id_t = terminal(Id_t, "b") ;
global c :: Id_t = terminal(Id_t, "c") ;
global d :: Id_t = terminal(Id_t, "d") ;

global exy :: Expr = add(idRef(x),idRef(y)) ;

global eabcd :: Expr = add(add(add(idRef(a),idRef(b)),idRef(c)),idRef(d)) ;

global rename_eabcd :: Root = root(eabcd).rename_Root ;

-- testing pp on a few
equalityTest ( root(exy).pp, "(x + y)", String, rw ) ;
equalityTest ( root(eabcd).pp, "(((a + b) + c) + d)", String, rw ) ;

-- testing the add_suffix rewrite rule
equalityTest ( rename_eabcd.pp, "(((a_XX + b) + c) + d)", String, rw ) ;

{-
equalityTest ( decorate root(eabcd) 
                 with { rwrule_Expr = addSuffix_v2("XX") ; }.transform_Root.pp , 
               "(((a_XX + b) + c) + d)", String, rw ) ;
-}

equalityTest ( decorate root(eabcd) 
                 with { rwrule_v3_Expr = addSuffix_v3("XX") ; }.transform_Root.pp , 
               "(((a_XX + b) + c) + d)", String, rw ) ;




