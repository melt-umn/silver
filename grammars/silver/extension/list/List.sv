grammar silver:extension:list;
import silver:definition:core;
import silver:definition:type:decorated;
import silver:definition:type:anytype;
import silver:definition:env;

import silver:analysis:typechecking:core;

terminal LSqr_t '[' lexer classes {KEYWORD};
terminal RSqr_t ']' lexer classes {KEYWORD};

-- Type Expressions
-------------------
concrete production listType
top::Type ::= '[' te::Type ']'
{
  top.typerep = listTypeRep(te.typerep);
  top.errors := te.errors;

  forwards to refType('Decorated', qNameId(nameId(terminal(Id_t, "AnyTypeList"))));
}

concrete production emptyList
top::Expr ::= '[' ']'
{
  top.pp = "[]";
  top.location = loc(top.file, $1.line, $1.column);

  local attribute tr :: Decorated TypeRep;
  tr = case top.expected of
	expected_type(t) -> t |
	_ -> topTypeRep() end;

  top.typerep = tr;
  forwards to emptyProductionApp(baseExpr(qNameId(nameId(terminal (Id_t, "nil_AnyTypeList")))), '(',')');
  -- TODO: just forward to the below? or vice versa?  or get rid of the below altogether?
}


concrete production emptyListWType
top::Expr ::= '[' '::' t::Type ']'
{
  top.pp = "[::" ++ t.pp ++ "]";
  top.location = loc(top.file, $1.line, $1.column);

  top.typerep = listTypeRep(t.typerep);
  top.errors := t.errors;

  forwards to emptyProductionApp(baseExpr(qNameId(nameId(terminal (Id_t, "nil_AnyTypeList")))), '(', ')');
}

-- List Literal
---------------
concrete production fullList
top::Expr ::= '[' es::Exprs ']'
{ 
  top.pp = "[ " ++ es.pp ++ " ]" ;
  top.location = loc(top.file, $1.line, $1.column);

  top.typeErrors = es.typeErrors ++
	         if checkListErrors(head(es.exprs).typerep, getTypesExprs(es.exprs))
	         then []
		 else [err(top.location, "Elements of a list must all be the same type.")];

  local attribute t :: Decorated TypeRep;
  t = head(es.exprs).typerep;

  local attribute expectedElementType :: Decorated TypeRep;
  expectedElementType = case top.expected of
                         expected_type(typ) -> typ.listComponent
                        | _ -> topTypeRep() end;

  top.typerep = if null(es.exprs) then listTypeRep(expectedElementType) else listTypeRep(t);

  -- This is useless as sent to the Exprs part in def/core, but we use it below for listtrans
  es.expectedInputTypes = makeListInputs(expectedElementType, length(es.exprs));

  -- Should these be here?
  -- top.errors := es.errors ++ es.listtrans.errors;
  -- top.warnings := es.warnings ++ es.listtrans.warnings;

  forwards to es.listtrans;
  -- TODO: We should build this with a function, rather than aspecting it.
  -- further, we should probably forward to cons() rather than direct function application!
}

function makeListInputs
[Decorated TypeRep] ::=  t::Decorated TypeRep c::Integer
{
  return if c <= 0 then [] else [t] ++ makeListInputs(t, c-1);
}

function checkListErrors
Boolean ::=  t::Decorated TypeRep ts::[Decorated TypeRep]
{
  return null(ts) || (t.typeEquals(t, head(ts)).bValue && checkListErrors(t, tail(ts)));
}

synthesized attribute listtrans :: Expr ;
attribute listtrans occurs on Exprs ;

aspect production exprsEmpty
top::Exprs ::=
{
}

aspect production exprsSingle
top::Exprs ::= e::Expr
{
  top.listtrans = 
   productionApp(baseExpr(qNameId(nameId(terminal(Id_t, "cons_AnyTypeList" )))),
		 '(', exprsCons(cast_t(coerce_expected(e, head(top.expectedInputTypes)), anyTypeTypeRep()),
		 ',', exprsSingle(emptyProductionApp(baseExpr(qNameId(nameId(terminal (Id_t, "nil_AnyTypeList")))),'(',')'))),')');
}

aspect production exprsCons
top::Exprs ::= e1::Expr c::Comma_t e2::Exprs
{
  top.listtrans = 
   productionApp(baseExpr(qNameId(nameId(terminal(Id_t, "cons_AnyTypeList")))),
                 '(', exprsCons(cast_t(coerce_expected(e1, head(top.expectedInputTypes)), anyTypeTypeRep()),
                 ',', exprsSingle(e2.listtrans)),')');
}

function makeNew
Expr ::= e::Expr
{
  return newFunction(terminal(New_kwd, ""), terminal(LParen_t, ""), e, terminal(RParen_t, ""));
}

-- Cons
-------
terminal Cons_t 'cons'  lexer classes {KEYWORD};

concrete production consList
top::Expr ::= 'cons' '(' h::Expr ',' t::Expr ')'
{ 
  top.pp = "cons (" ++ h.pp ++ ", " ++ t.pp ++ ")" ;
  top.location = loc(top.file, $1.line, $1.column);
 
  top.typerep = listTypeRep(h.typerep);

  local attribute e1 :: [Decorated Message];
  e1 = if t.typerep.isList then []
       else [err(top.location, "Second argument to cons must be a List.")];

  local attribute e2 :: [Decorated Message];
  e2 = if t.typerep.isList && !t.typerep.isEmpty && !(h.typerep.typeEquals(h.typerep, t.typerep.listComponent).bValue)
       then [err(top.location, "First argument to cons must be of type: " ++ t.typerep.listComponent.typeName ++ ".")]
       else [];

  top.typeErrors = h.typeErrors ++ t.typeErrors ++ e1 ++ e2;

  local attribute expectedType :: Decorated TypeRep;
  expectedType = case top.expected of
                  expected_type(typ) -> typ
                 | _ -> topTypeRep() end;

  forwards to
    productionApp(baseExpr(qNameId(nameId(terminal(Id_t, "cons_AnyTypeList")))),
                    '(', exprsCons(cast_t(coerce_expected(h, expectedType.listComponent), anyTypeTypeRep()),
                    ',', exprsSingle(coerce_expected(t, expectedType))),')');
}

-- TODO: BUG: '::' is HasType_t.  We probably want to have a different
-- terminal here, with different precedence!

concrete production consListOp
top::Expr ::= h::Expr '::' t::Expr{ 
  top.pp = "(" ++ h.pp ++ " :: " ++ t.pp ++ ")" ;
  forwards to  consList(terminal(Cons_t, "cons", $2.line, $2.column), '(', h, ',', t, ')');
}


aspect production plusPlus
top::Expr ::= e1::Expr '++' e2::Expr
{
  handler <- if e1.typerep.isList && e2.typerep.isList 
	     then [appendList(e1, e2)]
	     else [];
}

abstract production appendList
top::Expr ::= l::Expr r::Expr
{ 
  top.pp = l.pp ++ " ++ " ++ r.pp ;
  top.location = l.location;

  top.typerep = if l.typerep.isEmpty then r.typerep else l.typerep;

  local attribute e1 :: [Decorated Message];
  e1 = if l.typerep.isList then []
       else [err(top.location, "First argument to append must be a List.")];

  local attribute e2 :: [Decorated Message];
  e2 = if r.typerep.isList then []
       else [err(top.location, "Second argument to append must be a List.")];

  local attribute e3 :: [Decorated Message];
  e3 = if    l.typerep.isList 
	  && !l.typerep.isEmpty 
	  && r.typerep.isList 
	  && !r.typerep.isEmpty 
	  && !(l.typerep.typeEquals(l.typerep, r.typerep).bValue)
       then [err(top.location, "List types differ in application of append.")]
       else [];

  top.typeErrors = l.typeErrors ++ r.typeErrors ++ e1 ++ e2 ++ e3;

  local attribute expectedType :: Decorated TypeRep;
  expectedType = case top.expected of
                  expected_type(typ) -> typ
                 | _ -> topTypeRep() end;

  forwards to
   productionApp(baseExpr(qNameId(nameId(terminal(Id_t, "append_AnyTypeList")))),
                  '(', exprsCons(coerce_expected(l, expectedType), 
                                 ',',
                                 exprsSingle(coerce_expected(r, expectedType))), ')');
}

aspect production lengthFunction
top::Expr ::= 'length' '(' e::Expr ')'
{
  handlers <- if e.typerep.isList
	      then [listLength(e)]
	      else [];
}

-- Length
---------
abstract production listLength
top::Expr ::= e::Expr
{
  top.pp = "length ( " ++ e.pp ++ " )";
  top.location = e.location;

  top.typerep = integerTypeRep();
 
  top.errors := e.errors;
  top.typeErrors = e.typeErrors;

  forwards to attributeAccess(e, '.', qNameId(nameId(terminal(Id_t, "length_AnyTypeList"))));
}

-- Empty
--------
terminal Null_t 'null'  lexer classes {KEYWORD};

concrete production nullList
top::Expr ::= 'null' '(' l::Expr ')'
{ 
  top.pp = "null(" ++ l.pp ++ ")" ;

  top.location = loc(top.file, $1.line, $1.column);

  local attribute e1 :: [Decorated Message];
  e1 = if l.typerep.isList then []
       else [err(top.location, "Argument to null must be a List.")];

  top.typeErrors = l.typeErrors ++ e1;

  forwards to attributeAccess (l, '.', qNameId(nameId(terminal(Id_t, "empty_AnyTypeList"))));
}


-- Head
--------
terminal Head_t 'head'  lexer classes {KEYWORD};

concrete production headList
top::Expr ::= 'head' '(' l::Expr ')'
{ 
  top.location = loc(top.file, $1.line, $1.column);

  top.pp = "head(" ++ l.pp ++ ")" ;

  local attribute e1 :: [Decorated Message];
  e1 = if l.typerep.isList then []
       else [err(top.location, "Argument to head must be a List.")];

  top.typeErrors = l.typeErrors ++ e1;

  local attribute fn :: Expr;
  fn = cast_t(attributeAccess(l, '.', qNameId(nameId(terminal(Id_t, "head_AnyTypeList")))), l.typerep.listComponent);

  forwards to fn;
}

-- Tail
--------
terminal Tail_t 'tail'  lexer classes {KEYWORD};

concrete production tailList
top::Expr ::= 'tail' '(' l::Expr ')'
{ 
  top.pp =  "tail (" ++ l.pp ++ ")" ;
  top.typerep = l.typerep;

  top.location = loc(top.file, $1.line, $1.column);

  local attribute e1 :: [Decorated Message];
  e1 = if l.typerep.isList then []
       else [err(top.location, "Argument to tail must be a List.")];

  top.typeErrors = l.typeErrors ++ e1;
  
  forwards to attributeAccess(l, '.', qNameId(nameId(terminal(Id_t, "tail_AnyTypeList"))));
}

-- TypeRep
-----------

synthesized attribute listComponent :: Decorated TypeRep ;
attribute listComponent occurs on TypeRep ;

synthesized attribute isList :: Boolean;
attribute isList occurs on TypeRep ;
attribute isEmpty occurs on TypeRep ;


function listTypeRep
Decorated TypeRep ::= tr::Decorated TypeRep
{
  return decorate i_listTypeRep(false, tr) with {};
}

abstract production i_listTypeRep
top::TypeRep ::= e::Boolean tr::Decorated TypeRep
{
  top.isList = true;
  top.listComponent = tr;
  top.isEmpty = e;
  top.typeEquals = listTypeEquals;
  top.decoratedType = decorate i_listTypeRep(e,tr) with { }  ;

  top.unparse = "[" ++ tr.unparse ++ "]";

  forwards to i_refTypeRep(ntTypeRep("lists:anytype:AnyTypeList"));
}

aspect production i_defaultTypeRep
top::TypeRep ::= 
{
  top.isList = false;
  top.isEmpty = true;
  top.listComponent = top;
}

aspect production i_topTypeRep
top::TypeRep ::= 
{
  top.isList = true;
  top.isEmpty = true;
  top.listComponent = top;
}
abstract production listTypeEquals
top::TypeEquals ::= t1::Decorated TypeRep t2::Decorated TypeRep
{
    top.bValue =   t1.isList 
		&& t2.isList 
		&& t1.listComponent.typeEquals(t1.listComponent, t2.listComponent).bValue;
}
