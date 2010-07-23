grammar lib:system:ioaction ;

nonterminal IOaction with ioIn, ioOut ;

inherited attribute ioIn :: IO ;
synthesized attribute ioOut :: IO ;


-- individual actions
------------------------------------------------------------

abstract production printString
t::IOaction ::= str::String
{ t.ioOut = print (str, t.ioIn);    }

abstract production printInteger
t::IOaction ::= i::Integer
{ t.ioOut = print (toString(i), t.ioIn);    }

abstract production writeString
t::IOaction ::= filename::String str::String
{ t.ioOut = writeFile(filename, str, t.ioIn);    }

abstract production skipIOaction
task::IOaction ::= 
{
 task.ioOut = task.ioIn ;
}

-- composite actions
------------------------------------------------------------
abstract production ioActionSequence
t::IOaction ::= t1::IOaction t2::IOaction
{
 t.ioOut = t2.ioOut ;
 t1.ioIn = t.ioIn ;
 t2.ioIn = t1.ioOut ;
}

abstract production optionalAction 
t::IOaction ::= cond::Boolean task::IOaction
{
 t.ioOut = if   cond
           then task.ioOut
           else t.ioIn ;

 task.ioIn = t.ioIn ;
}

abstract production condAction 
t::IOaction ::= cond::Boolean t1::IOaction t2::IOaction
{
 t.ioOut = if cond then t1.ioOut else t2.ioOut ;
 t1.ioIn = t.ioIn ;
 t2.ioIn = t.ioIn ;
}


-- functions over actions
------------------------------------------------------------

-- fold many actions into one, in sequence
function foldIOactions
IOaction ::= tasks::[IOaction]
{
 return if null(tasks)
        then skipIOaction()
        else ioActionSequence(head(tasks), 
                              foldIOactions(tail(tasks))) ;
}

-- perform an IOaction
function performAction
IO ::= task::IOaction token::IO
{
 local attribute instantiatedTask :: IOaction ;
 instantiatedTask = task ;

 instantiatedTask.ioIn = token ;

 return instantiatedTask.ioOut ;
}


