grammar silver:util:cmdargs;


@{-
  - The type of lists of arguments given on the command line
  -
  - Define a new production (as a cons cell for the CmdArgs list) for each flag given on the command line:
  - - For a flag that takes no arguments on the command line, define a production of type (CmdArgs ::= CmdArgs)
  - - For a flag that takes a single argument, define a production of type (CmdArgs ::= String CmdArgs)
  - - For a flag that takes more arguments, define a prodution of type (CmdArgs ::= [String] CmdArgs)
  -}
nonterminal CmdArgs with cmdRemaining, cmdError;

@{-
  - The remaining arguments from the commandline after parsing the flags
  -}
synthesized attribute cmdRemaining :: [String];
@{-
  - The errors from parsing commandline arguments
  -}
synthesized attribute cmdError :: Maybe<String>;


@{-
  - Produce a parsed list of arguments using the given flags from the given input
  -
  - @param flags  A list of pairs of flag names (starting with hyphens) and the Flag for handling its parsing
  - @param input  A list of strings, generally the commandline arguments
  - @return The parsed list of arguments
  - @warning The flag names in the flags parameter MUST start with a hyphen
  -}
function interpretCmdArgs
CmdArgs ::= flags::[Pair<String Flag>]  input::[String]
{
  local attribute l :: Maybe<Flag>;
  l = lookup(head(input), flags);
  
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

@{--
  - For defining base, default values for any attributes on CmdArgs
  -
  - @param remaining  Commandline arguments remaining after parsing flags
  -}
abstract production endCmdArgs
top::CmdArgs ::= remaining::[String]
{
  top.cmdRemaining = remaining;
  top.cmdError = nothing();
}

@{--
  - Only used when an error is encountered attempting to parse an option.
  - One should always check for .cmdError.isJust BEFORE accessing any other attributes.
  -
  - @param errmsg  Error message
  -}
abstract production errorCmdArgs
top::CmdArgs ::= errmsg::String
{
  top.cmdRemaining = [];
  top.cmdError = just(errmsg);
  forwards to endCmdArgs([]); -- Well, this is an abuse, but this whole thing is an abuse, really.
}

@{--
  - Flags are a representation of what to do with command line flags/options.
  - It should not be necessary to define any new flags.
  -}
nonterminal Flag with flagInput, flagOutput, flagOriginal, flagModified;

inherited attribute flagInput::[String];
synthesized attribute flagOutput::[String];
inherited attribute flagOriginal::CmdArgs;
synthesized attribute flagModified::CmdArgs;

@{--
 - In the terminology I've just made up, a 'flag' is a cmd line option
 - with no parameters.
 -
 - @param ast  Production for handling this commandline option being given
 -}
abstract production flag
top::Flag ::= ast::(CmdArgs ::= CmdArgs)
{
  top.flagOutput = tail(top.flagInput);
  top.flagModified = ast(top.flagOriginal);
}

@{--
 - In the terminology I've just made up, an 'option' is a cmd line option
 - with one, single parameter.
 -
 - @param ast  Production for handling this commandline option being given
 -}
abstract production option
top::Flag ::= ast::(CmdArgs ::= String CmdArgs)
{
  top.flagOutput = if null(tail(top.flagInput)) then [] else tail(tail(top.flagInput));
  top.flagModified = if null(tail(top.flagInput))
                     then errorCmdArgs(head(top.flagInput) ++ " is missing its parameter.")
                     else ast(head(tail(top.flagInput)), top.flagOriginal);
}

@{--
 - In the terminology I've just made up, 'nOptions' is a cmd line option
 - with n parameters.
 -
 - @param n  The number of arguments expected by the flag
 - @param ast  Production for handling this commandline option being given
 -}
abstract production nOptions
top::Flag ::= n::Integer  ast::(CmdArgs ::= [String] CmdArgs)
{
  top.flagOutput =
      if length(top.flagInput) <= n + 1
      then []
      else drop(n + 1, top.flagInput);
  top.flagModified =
      if length(top.flagInput) <= n + 1
      then errorCmdArgs(head(top.flagInput) ++ " only has " ++
                        toString(length(top.flagInput)) ++
                        " parameters, when it should have " ++
                        toString(n - 1))
      else ast(take(n, drop(1, top.flagInput)), top.flagOriginal);
}

