grammar silver:util:cmdargs;

nonterminal CmdArgs with cmdRemaining, cmdError;

synthesized attribute cmdRemaining :: [String];
synthesized attribute cmdError :: Maybe<String>;

function interpretCmdArgs
CmdArgs ::= flags::[Pair<String Flag>]  input::[String]
{
  local attribute l :: Maybe<Flag>;
  l = lookupBy(stringEq, head(input), flags);
  
  local attribute here :: Flag;
  here = l.fromJust;
  here.flagInput = input;
  here.flagOriginal = interpretCmdArgs(flags, here.flagOutput);
  
  return if null(input) then endCmdArgs([])
         else if !l.isJust
         then if startsWith("-", head(input))
              then errorCmdArgs("Unrecognized flag: " ++ head(input))
              else endCmdArgs(input)
         else here.flagModified;
}

{--
 - For defining base, default values for any attributes on CmdArgs
 -}
abstract production endCmdArgs
top::CmdArgs ::= remaining::[String]
{
  top.cmdRemaining = remaining;
  top.cmdError = nothing();
}

{--
 - Only used when an error is encountered attempting to parse an option.
 - One should always check for .cmdError.isJust BEFORE accessing any other attributes.
 -}
abstract production errorCmdArgs
top::CmdArgs ::= errmsg::String
{
  top.cmdRemaining = [];
  top.cmdError = just(errmsg);
}

{--
 - Flags are a representation of what to do with command line flags/options.
 - It should not be necessary to define any new flags.
 -}
nonterminal Flag with flagInput, flagOutput, flagOriginal, flagModified;

inherited attribute flagInput::[String];
synthesized attribute flagOutput::[String];
inherited attribute flagOriginal::CmdArgs;
synthesized attribute flagModified::CmdArgs;

{--
 - In the terminology I've just made up, a 'flag' is a cmd line option
 - with no parameters.
 -}
abstract production flag
top::Flag ::= ast::Production(CmdArgs ::= CmdArgs)
{
  top.flagOutput = tail(top.flagInput);
  top.flagModified = ast(top.flagOriginal);
}

{--
 - In the terminology I've just made up, an 'option' is a cmd line option
 - with one, single parameter.
 -}
abstract production option
top::Flag ::= ast::Production(CmdArgs ::= String CmdArgs)
{
  top.flagOutput = if null(tail(top.flagInput)) then [] else tail(tail(top.flagInput));
  top.flagModified = if null(tail(top.flagInput))
                     then errorCmdArgs(head(top.flagInput) ++ " is missing its parameter.")
                     else ast(head(tail(top.flagInput)), top.flagOriginal);
}

{--
 - In the terminology I've just made up, 'nOptions' is a cmd line option
 - with n parameters.
 -}
abstract production nOptions
top::Flag ::= n::Integer  ast::Production(CmdArgs ::= [String] CmdArgs)
{
  top.flagOutput = if length(top.flagInput) <= n then [] else drop(n, top.flagInput);
  top.flagModified = if length(top.flagInput) <= n
                     then errorCmdArgs(head(top.flagInput) ++ " only has " ++ toString(length(top.flagInput)-1) ++ " parameters, when it should have " ++ toString(n))
                     else ast(take(n, top.flagInput), top.flagOriginal);
}

