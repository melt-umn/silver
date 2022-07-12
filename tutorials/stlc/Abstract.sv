grammar stlc;

restricted inherited attribute gamma::[Pair<String Type>];
implicit synthesized attribute type::Either<String Type>;
synthesized attribute errors::[String];

restricted inherited attribute substV::String;
restricted inherited attribute substE::Expression;
restricted synthesized attribute substed::Expression;
restricted synthesized attribute isvalue::Boolean;
implicit synthesized attribute nextStep::Maybe<Expression>;

synthesized attribute pp::String;



function lookupType
Maybe<Type> ::= name::String gamma::[Pair<String Type>]
{
  return if null(gamma)
         then nothing()
         else if head(gamma).fst == name
              then just(head(gamma).snd)
              else lookupType(name, tail(gamma));
}


synthesized attribute singleSteps::[Expression];
nonterminal Root with pp, type, errors, nextStep, singleSteps;

abstract production root
top::Root ::= e::Expression
{
  e.gamma = [];
  top.type = e.type;
  top.errors = e.errors;

  top.pp = e.pp;

  top.nextStep = e.nextStep;

  top.singleSteps = case e.nextStep of
                    | just(x) -> e::root(x).singleSteps
                    | nothing() -> [e]
                    end;
}



nonterminal Expression with
   gamma, type, errors,
   substV, substE, substed, isvalue, nextStep,
   pp;

abstract production var
top::Expression ::= name::String
{
  top.type = case lookupType(name, top.gamma) of
             | just(x) -> x
             | nothing() -> left("Unknown variable " ++ name)
             end;
  top.errors = case top.type of
               | left(s) -> [s]
               | _ -> []
               end;

  top.isvalue = false;

  top.substed = if top.substV == name
                then top.substE
                else top;

  implicit top.nextStep = ;

  top.pp = name;
}


abstract production abs
top::Expression ::= name::String ty::Type body::Expression
{
  body.gamma = [pair(name, ty)] ++ top.gamma;
  top.type = arrow(ty, body.type);
  top.errors = case top.type, body.type of
               | left(s), right(_) -> [s] ++ body.errors
               | _, _ -> body.errors
               end;

  top.isvalue = true;

  body.substV = top.substV;
  body.substE = top.substE;
  top.substed = if top.substV == name
                then top
                else abs(name, ty, body.substed);

  implicit top.nextStep = ;

  top.pp = "lambda " ++ name ++ ":" ++ ty.pp ++ ". " ++ body.pp;
}


abstract production app
top::Expression ::= t1::Expression t2::Expression
{
  t1.gamma = top.gamma;
  t2.gamma = top.gamma;
  top.type = case t1.type of
             | arrow(ty1, ty2) when tyEq(ty1, t2.type) -> ty2
             | arrow(_, _) -> left("Application type mismatch")
             | _ -> left("Non-function applied")
             end;
  top.errors = case top.type, t1.type, t2.type of
                 | left(s), right(ty1), right(ty2) -> [s] ++ t1.errors ++ t2.errors
                 | _, _, _ -> t1.errors ++ t2.errors
                 end;

  top.isvalue = false;

  t1.substV = top.substV;
  t2.substV = top.substV;
  t1.substE = top.substE;
  t2.substE = top.substE;
  top.substed = app(t1.substed, t2.substed);

  top.nextStep = case t1, t2 of
                 | abs(n, t, b), v when v.isvalue ->
                   decorate b with {substV=n; substE=v;}.substed
                 | v1, _ when !v1.isvalue -> app(t1.nextStep, t2)
                 | _, _ -> app(t1, t2.nextStep)
                 end;

  top.pp = "(" ++ t1.pp ++ ") (" ++ t2.pp ++ ")";
}


abstract production or
top::Expression ::= t1::Expression t2::Expression
{
  t1.gamma = top.gamma;
  t2.gamma = top.gamma;
  top.type = case t1.type, t2.type of
             | bool(), bool() -> bool()
             | _, _ -> left("Both disjuncts must be of type Bool")
             end;
  top.errors = case top.type, t1.type, t2.type of
                 | left(s), right(_), right(_) -> [s] ++ t1.errors ++ t2.errors
                 | _, _, _ -> t1.errors ++ t2.errors
                 end;

  top.isvalue = false;

  t1.substV = top.substV;
  t2.substV = top.substV;
  t1.substE = top.substE;
  t2.substE = top.substE;
  top.substed = or(t1.substed, t2.substed);

  top.nextStep = case t1, t2 of
                 | tru_a(), _ -> tru_a()
                 | fals_a(), _ -> t2
                 | _, _ -> or(t1.nextStep, t2)
                 end;

  top.pp = "(" ++ t1.pp ++ ") || (" ++ t2.pp ++ ")";
}


abstract production and
top::Expression ::= t1::Expression t2::Expression
{
  t1.gamma = top.gamma;
  t2.gamma = top.gamma;
  top.type = case t1.type, t2.type of
             | bool(), bool() -> bool()
             | _, _ -> left("Both conjuncts must be of type Bool")
             end;
  top.errors = case top.type, t1.type, t2.type of
                 | left(s), right(ty1), right(ty2) -> [s] ++ t1.errors ++ t2.errors
                 | _, _, _ -> t1.errors ++ t2.errors
                 end;

  top.isvalue = false;

  t1.substV = top.substV;
  t2.substV = top.substV;
  t1.substE = top.substE;
  t2.substE = top.substE;
  top.substed = and(t1.substed, t2.substed);

  top.nextStep = case t1, t2 of
                 | tru_a(), _ -> t2
                 | fals_a(), _ -> fals_a()
                 | _, _ -> and(t1.nextStep, t2)
                 end;

  top.pp = "(" ++ t1.pp ++ ") && (" ++ t2.pp ++ ")";
}


abstract production tru_a
top::Expression ::=
{
  top.type = bool();
  top.errors = [];

  top.isvalue = true;

  top.substed = top;

  implicit top.nextStep = ;

  top.pp = "true";
}


abstract production fals_a
top::Expression ::=
{
  top.type = bool();
  top.errors = [];

  top.isvalue = true;

  top.substed = top;

  implicit top.nextStep = ;

  top.pp = "false";
}


abstract production notOp
top::Expression ::= e::Expression
{
  e.gamma = top.gamma;
  top.type = case e.type of
             | bool() -> bool()
             | _ -> left("Not requires an argument of type Bool")
             end;
  top.errors = case top.type, e.type of
                 | left(s), right(_) -> [s] ++ e.errors
                 | _, _ -> e.errors
                 end;

  top.isvalue = false;

  e.substV = top.substV;
  e.substE = top.substE;
  top.substed = notOp(e.substed);

  top.nextStep = case e of
                 | tru_a() -> fals_a()
                 | fals_a() -> tru_a()
                 | _ -> notOp(e.nextStep)
                 end;

  top.pp = "!(" ++ e.pp ++ ")";
}




nonterminal Type with pp;

abstract production arrow
top::Type ::= t1::Type t2::Type
{
  top.pp = "(" ++ t1.pp ++ ") -> " ++ t2.pp;
}


abstract production bool
top::Type ::=
{
  top.pp = "Bool";
}



function tyEq
Boolean ::= t1::Type t2::Type
{
  return case t1, t2 of
         | bool(), bool() -> true
         | arrow(t11, t12), arrow(t21, t22) ->
           tyEq(t11, t21) && tyEq(t12, t22)
         | _, _ -> false
         end;
}
