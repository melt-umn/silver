grammar stlc;



parser hostparse :: Root_c
{
  stlc;
}

function main
IO<Integer> ::= largs::[String]
{
  local attribute args::String;
  args = implode(" ", largs);

  local attribute result :: ParseResult<Root_c>;
  result = hostparse(args, "<<args>>");

  local attribute r_cst::Root_c;
  r_cst = result.parseTree;

  local attribute r::Root = r_cst.ast;

  local attribute print_success :: IO<Unit>;
  print_success =
       print("Expression:  " ++ r.pp ++ "\n" ++
             "Type:        " ++ typeToString(r.type) ++ "\n" ++
             "Errors:      " ++ errorsToString(r.errors) ++ "\n" ++
             "SingleSteps Attribute (Evaluation Trace):\n   " ++ listToString_Expression(r.singleSteps) ++ "\n");

  local attribute print_failure :: IO<Unit>;
  print_failure = print("Encountered a parse error:\n" ++
                        result.parseErrors ++ "\n");

  return do { new(if result.parseSuccess then print_success else print_failure); return 0; };
}


function typeToString
String ::= e::Either<String Type>
{
  return case e of
         | left(s) -> s
         | right(t) -> t.pp
         end;
}

function listToString_Expression
String ::= l::[Expression]
{
  return foldl(\x::String t::Expression ->
                 x ++ ",  " ++ t.pp, "[", l) ++ "]";
}


function errorsToString
String ::= l::[String]
{
  return foldl(\x::String t::String ->
                x ++ ", " ++ t, "", l);
}


function stepsAttrString
String ::= l::[[Expression]]
{
  return case l of
         | [] -> ""
         | h::t -> "   " ++ listToString_Expression(h) ++ "\n" ++ stepsAttrString(t)
         end;
}

