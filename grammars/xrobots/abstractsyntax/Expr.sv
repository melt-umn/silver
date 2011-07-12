grammar xrobots:abstractsyntax ;

-- This file defines arithmetic as was done in tutorial:dc.

import xrobots:terminals ;

nonterminal Expr with pp, type, noOfStaticLinks,  errors, htrans, stack ;


abstract production add
sum::Expr ::= l::Expr  op::Plus_t  r::Expr
{
 sum.pp               = l.pp ++ " + " ++ r.pp ;
 sum.type             = l.type;
 sum.noOfStaticLinks  = 0 ;
 sum.errors           = case l.type of
                         boolType()
                           -> mk_error("Cannot add boolean types.", 
				       op.line, op.column)
                          | _ -> if equal_types (l.type, r.type)
                                 then []
                                 else mk_error("cannot add " ++ l.type.pp
				    ++ " and " ++  r.type.pp,
				    op.line, op.column)
                       end;

 sum.htrans           = case l.type of
                         intType() 
                           -> "(add " ++l.htrans++ " " ++ r.htrans ++ ")"
                       | floatType()
                           -> "(add " ++l.htrans++ " " ++ r.htrans ++ ")"
                       | _ -> ""
                       end;

 l.stack = sum.stack;
 r.stack = sum.stack;
 
}
  
abstract production sub
dff::Expr ::= l::Expr  op::Dash_t  r::Expr
{ 
 dff.pp               = l.pp ++ " - " ++ r.pp ;
 dff.type             = l.type;
 dff.noOfStaticLinks  = 0 ;
 dff.errors           = case l.type of
                       boolType()
                           -> mk_error("Cannot subtract boolean types.", 
				       op.line, op.column)
                       | _ -> if equal_types (l.type, r.type)
                              then []
                              else mk_error("cannot subtract " ++ r.type.pp
				    ++ " from " ++  l.type.pp,
				    op.line, op.column)
                       end;

 dff.htrans           =   case l.type of
                         intType() 
                           -> "(sub " ++l.htrans++ " " ++ r.htrans ++ ")"
                       | floatType()
                           -> "(sub " ++l.htrans++ " " ++ r.htrans ++ ")"
                       | _ -> ""
                       end;
 l.stack = dff.stack;
 r.stack = dff.stack;
 
}

abstract production mul
prd::Expr ::= l::Expr  op::Star_t  r::Expr
{
 prd.pp               = l.pp ++ " * " ++ r.pp ;
 prd.type             = l.type;
 prd.noOfStaticLinks  = 0 ;
 prd.errors           = case l.type of
                       boolType()
                           -> mk_error("Cannot multiply boolean types.", 
				       op.line, op.column)
                       | _ -> if equal_types (l.type, r.type)
                              then []
                              else mk_error("cannot multiply " ++ l.type.pp
				    ++ " and " ++  r.type.pp,
				    op.line, op.column)
                       end;

 prd.htrans           =  case l.type of
                         intType() 
                           -> "(mul " ++l.htrans++ " " ++ r.htrans ++ ")"
                       | floatType()
                           -> "(mul " ++l.htrans++ " " ++ r.htrans ++ ")"
                       | _ -> ""
                       end;

 l.stack = prd.stack;
 r.stack = prd.stack;
 
}

abstract production div
quo::Expr ::= l::Expr  op::Slash_t  r::Expr
{
 quo.pp               = l.pp ++ " / " ++ r.pp ;
 quo.type             = l.type;
 quo.noOfStaticLinks  = 0 ;
 quo.errors           = case l.type of
                       boolType()
                           -> mk_error("Cannot divide boolean types.", 
				       op.line, op.column)
                       | _ -> if equal_types (l.type, r.type)
                              then []
                              else mk_error("cannot divide " ++ l.type.pp
				    ++ " by " ++  r.type.pp,
				    op.line, op.column)
                       end;

 quo.htrans           =  case l.type of
                         intType() 
                           -> "(divide " ++l.htrans++ " " ++ r.htrans ++ ")"
                       | floatType()
                           -> "(divide " ++l.htrans++ " " ++ r.htrans ++ ")"
                       | _ -> ""
                       end;
 l.stack = quo.stack;
 r.stack = quo.stack;
 
}

abstract production power
p::Expr ::= l::Expr  op::Power_t  r::Expr
{
 p.pp               = l.pp ++ " + " ++ r.pp ;
 p.type             = l.type;
 p.noOfStaticLinks  = 0 ;
 p.errors           = case l.type of
                       boolType()
                           -> mk_error("Cannot expontentiate boolean types.", 
				       op.line, op.column)
                       | _ -> if equal_types (l.type, r.type)
                              then []
                              else mk_error("cannot raise " ++ l.type.pp
				    ++ " to the power " ++  r.type.pp,
				    op.line, op.column)
                       end;
 p.htrans           =   case l.type of
                         intType() 
                           -> "(power " ++l.htrans++ " " ++ r.htrans ++ ")"
                       | floatType()
                           -> "(power " ++l.htrans++ " " ++ r.htrans ++ ")"
                       | _ -> ""
                       end;
 l.stack = p.stack;
 r.stack = p.stack;
 
}
abstract production integerConstant
e::Expr ::= i::IntLit_t
{
 e.pp               = i.lexeme ;
 e.type             = intType();
 e.noOfStaticLinks  = 0 ;
 e.errors           = [ ] ;
 e.htrans =        "(IntValue " ++ i.lexeme ++ ")" ;
}

abstract production falseConstant
e::Expr ::= i::False_t
{ 
 e.pp               = i.lexeme ;
 e.type             = boolType();
 e.noOfStaticLinks  = 0 ;
 e.errors           = [ ] ;
 e.htrans =        "(BoolValue " ++ i.lexeme ++ ")" ;
}

abstract production trueConstant
e::Expr ::= i::True_t
{
 e.pp               = i.lexeme ;
 e.type          = boolType();
 e.noOfStaticLinks  = 0 ;
 e.errors           = [ ] ;
 e.htrans =        "(BoolValue " ++ i.lexeme ++ ")" ;
}


abstract production negIntegerConstant
e::Expr ::= i::IntLit_t
{
 e.pp               = i.lexeme ;
 e.type          = intType();
 e.noOfStaticLinks  = 0 ;
 e.errors           = [ ] ;
 e.htrans =        "IntValue( -" ++ i.lexeme ++ ")" ;
}

abstract production floatConstant
e::Expr ::= i::IntLit_t  d::IntLit_t
{
 e.pp               = i.lexeme ++ "." ++ d.lexeme ;
 e.type             = floatType();
 e.noOfStaticLinks  = 0 ;
 e.errors           = [ ] ;
 e.htrans =        "( FloatValue " ++ i.lexeme ++ "." ++ d.lexeme ++ ")" ;
}

abstract production negFloatConstant
e::Expr ::= i::IntLit_t  d::IntLit_t
{
 e.pp               = i.lexeme ++ "." ++ d.lexeme ;
 e.type             = floatType();
 e.noOfStaticLinks  = 0 ;
 e.errors           = [ ] ;
 e.htrans =        "FloatValue( -" ++ i.lexeme ++ "." ++ d.lexeme ++ ")" ;
}


abstract production varAccess
e::Expr ::= n::Id_t
{
 e.pp               = "Accessing :" ++ n.lexeme ++ "...\n" ;
 e.type             = getType(e.stack, n);
 e.noOfStaticLinks  = getStaticLink(e.stack, n ) ;
 e.errors           = if isDefined(e.stack, n)
                       then []
                       else mk_error((n.lexeme ++  " is undefined"), 
                                        n.line, n.column);
 e.htrans =        "lookup_b((bindings  getFrameByStaticLinks " ++
                                  "(stack, "   ++ 
                                        toString(getStaticLink(e.stack, n)) ++
                                       ")), id)" ;
}

abstract production refAccess
e::Expr ::= n::Id_t
{
 e.pp               = "Accessing :" ++ n.lexeme ++ "...\n" ;
 e.type             = getType(e.stack, n);
 e.noOfStaticLinks  = getStaticLink(e.stack, n) ;
 e.errors           = if isDefined(e.stack, n) then
                        case getType(e.stack, n) of
				  refType(x)    ->  []
                                  | _ -> mk_error((n.lexeme++" is not a ref."), 
                                        n.line, n.column)
                                        --[n.lexeme ++ " is not a reference."]
                        end
                      else  mk_error((n.lexeme ++  " is undefined"), 
                                        n.line, n.column);
		      
 e.htrans           = "dereference(stack, " ++
                          "lookup_b((bindings  getFrameByStaticLinks " ++
                                  "(stack, "   ++ 
                                        toString(getStaticLink(e.stack, n)) ++
                                       ")), id)" ;
}

abstract production undefinedExpr
e::Expr ::= 
{
 e.pp               = "An expression was used that we are not compiling yet";
 e.type             = errorType();
 e.noOfStaticLinks  = 0 ;
 e.errors           = ["An expression was used that we are not compiling yet"];
 e.htrans           = "An expression was used that we are not compiling yet" ;
}