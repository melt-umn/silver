grammar simple:abstractsyntax;

{- Simple doesn't properly track location info in ast -}
abstract production locUnknown
l::Location ::=
{
  l.unparse = "??";
}


{- This file performs type checking on Simple programs by defining the
   attribute type::Type for all expressions (Expr).  If an error is
   detected then the an error message is added (via the <- assignment
   operator) to the collection attribute errors, which is declared and
   initialized (via the := assignment operator) in the file Expr.sv.
-}

attribute type occurs on Expr;

-- Constants
------------
{- These constructs do not create any errors and define their type
   attribute directly.  
-}
aspect production intLit
e::Expr ::= l::Location s::String
{
  e.type = integerType();
}

aspect production floatLit
e::Expr ::= l::Location s::String
{
  e.type = floatType();
}

aspect production boolLit
e::Expr ::= l::Location s::String
{
  e.type = booleanType();
}

aspect production stringLit
e::Expr ::= l::Location s::String
{
  e.type = stringType();
}


-- Variable Reference
---------------------
{- In the varRef production in Expr.sv, we defined declTypeExpr to be
   the optional decorated tree (a reference to) of the TypeExpr in the
   variable declaraion.  Since declTypeExpr is a production attribute
   it can be referenced in the aspect production below.  We check to
   see that it is defined, and if so, extract the "type" attribute
   from the referenced TypeExpr to assign to the variables "type"
   attribute. If it is not defined (it a "nothing()") we generate the
   error type.  

   There is no error to generate.  If it is not defined that error is
   generated in the production that defines declTypeExpr.  
-}

aspect production varRef
e::Expr ::= id::Name
{
  e.type = case id.lookup of
             just(dte) -> dte.type 
           | nothing() -> errorType() 
           end;
}


-- Arithmetic Operations
------------------------
{- We could further simply arithmetic productions if they forwarded to
   a generic numeric binary operation.  But we have not done that on
   this simple language.
-}
aspect production add
e::Expr ::= l::Expr r::Expr 
{
  e.type = resolveNumericTypes (l.type, r.type);
  e.errors <- (if isNumeric(l.type) then []
               else [err(locUnknown(), "Expression \"" ++ show(100,l.pp) ++ 
                      "\" must be of type Integer or Float.\n")]) ++
              (if isNumeric(r.type) then []
               else [err(locUnknown(), "Expression \"" ++ show(100,r.pp) ++ 
                      "\" must be of type Integer or Float.\n")]);
}
aspect production sub
e::Expr ::= l::Expr r::Expr 
{
  e.type = resolveNumericTypes (l.type, r.type);
  e.errors <- (if isNumeric(l.type) then []
               else [err(locUnknown(), "Expression \"" ++ show(100,l.pp) ++ 
                      "\" must be of type Integer or Float.\n")]) ++
              (if isNumeric(r.type) then []
               else [err(locUnknown(), "Expression \"" ++ show(100,r.pp) ++ 
                      "\" must be of type Integer or Float.\n")]);
}
aspect production mul
e::Expr ::= l::Expr r::Expr 
{
  e.type = resolveNumericTypes (l.type, r.type);
  e.errors <- (if isNumeric(l.type) then []
               else [err(locUnknown(), "Expression \"" ++ show(100,l.pp) ++ 
                      "\" must be of type Integer or Float.\n")]) ++
              (if isNumeric(r.type) then []
               else [err(locUnknown(), "Expression \"" ++ show(100,r.pp) ++ 
                      "\" must be of type Integer or Float.\n")]);
}
aspect production div
e::Expr ::= l::Expr r::Expr 
{
  e.type = resolveNumericTypes (l.type, r.type);
  e.errors <- (if isNumeric(l.type) then []
               else [err(locUnknown(), "Expression \"" ++ show(100,l.pp) ++ 
                      "\" must be of type Integer or Float.\n")]) ++
              (if isNumeric(r.type) then []
               else [err(locUnknown(), "Expression \"" ++ show(100,r.pp) ++ 
                      "\" must be of type Integer or Float.\n")]);
}

function resolveNumericTypes
Type ::= lType::Type rType::Type
{
  return
    case lType, rType of
      integerType(), integerType() -> integerType()
    | integerType(), floatType()   -> floatType()
    | floatType(),   integerType() -> floatType()
    | floatType(),   floatType()   -> floatType()
    | _, _ -> errorType()
    end;
}

function isNumeric
Boolean ::= t::Type
{
  return case t of
           integerType() -> true
         | floatType() -> true
         | _ -> false
         end;
}


-- Relational and Logical Operations
------------------------------------ 
{- Because of forwarding in Expr.sv, we do not need to write aspect
   productions for "or", "neq", "lte", "gt", or "gte", only these below.
-}
aspect production eq
e::Expr ::= l::Expr r::Expr 
{
  e.type = booleanType();
  e.errors <- (if isNumeric(l.type) then []
               else [err(locUnknown(), "Expression \"" ++ show(100,l.pp) ++ 
                      "\" must be of type Integer or Float.\n")]) ++
              (if isNumeric(r.type) then []
               else [err(locUnknown(), "Expression \"" ++ show(100,r.pp) ++ 
                      "\" must be of type Integer or Float.\n")]);
}
aspect production lt
e::Expr ::= l::Expr r::Expr 
{
  e.type = booleanType();
  e.errors <- (if isNumeric(l.type) then []
               else [err(locUnknown(), "Expression \"" ++ show(100,l.pp) ++ 
                      "\" must be of type Integer or Float.\n")]) ++
              (if isNumeric(r.type) then []
               else [err(locUnknown(), "Expression \"" ++ show(100,r.pp) ++ 
                      "\" must be of type Integer or Float.\n")]);
}

aspect production and
e::Expr ::= l::Expr r::Expr 
{
  e.type = booleanType();
  e.errors <- (if isBoolean(l.type) then []
               else [err(locUnknown(), "Expression \"" ++ show(100,l.pp) ++ 
                      "\" must be of type Boolean.\n")]) ++
              (if isBoolean(r.type) then []
               else [err(locUnknown(), "Expression \"" ++ show(100,r.pp) ++ 
                      "\" must be of type Boolean.\n")]);
}

aspect production not
e::Expr ::= ne::Expr 
{
  e.type = booleanType();
  e.errors <- if isBoolean(ne.type) then []
              else [err(locUnknown(), "Expression \"" ++ show(100,ne.pp) ++ 
                     "\" must be of type Boolean.\n")];
}

function isBoolean
Boolean ::= t::Type
{
  return case t of booleanType() -> true  | _ -> false  end;
}



-- Statements
-------------

aspect production ifthen
s::Stmt ::= c::Expr t::Stmt 
{
  s.errors <- if isBoolean(c.type) then []
              else [err(locUnknown(), "Expression \"" ++ show(100,c.pp) ++ 
                     "\" must be of type Boolean.\n")];
}

aspect production ifelse
s::Stmt ::= c::Expr t::Stmt e::Stmt 
{
  s.errors <- if isBoolean(c.type) then []
              else [err(locUnknown(), "Expression \"" ++ show(100,c.pp) ++ 
                     "\" must be of type Boolean.\n")];
}



