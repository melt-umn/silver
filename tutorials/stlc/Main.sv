grammar stlc;



parser hostparse :: Root_c
{
  stlc;
}

function main
IOVal<Integer> ::= largs::[String] ioin::IOToken
{
  local attribute args::String;
  args = implode(" ", largs);

  local attribute result :: ParseResult<Root_c>;
  result = hostparse(args, "<<args>>");

  local attribute r_cst::Root_c;
  r_cst = result.parseTree;

  local attribute r::Root = r_cst.ast;

  local attribute print_success :: IOToken;
  print_success =
       printT("Expression:  " ++ r.pp ++ "\n" ++
             "Type:        " ++ typeToString(r.type) ++ "\n" ++
             "Errors:      " ++ errorsToString(r.errors) ++ "\n" ++
             "SingleSteps Attribute (Evaluation Trace):\n   " ++ listToString_Expression(r.singleSteps),
             ioin);

  local attribute print_failure :: IOToken;
  print_failure = printT("Encountered a parse error:\n" ++
                        result.parseErrors ++ "\n",
                        ioin);

  return ioval(if result.parseSuccess
               then print_success
               else print_failure, 0);
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

