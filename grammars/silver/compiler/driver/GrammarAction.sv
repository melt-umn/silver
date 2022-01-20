grammar silver:compiler:driver;

{-
 - A short guide to error codes:
 - Negative = configuration/installation error
 - 1-19 = command line/start up error
 - 20+ = Normal use error (errors in spec)
 - 127 = "abnormal" success (e.g. printed version string, quit now)
 - 0 = success of course
 -}

{- Orders:
 - 0: parsing errors (also flow graph emitting)
 - 1: binding errors
 - 3: interfaces and classes
 - 4: doc
 - 5: copper_mda
 - 6: buildxml
 - 7: impide
 -}

aspect production compilation
top::Compilation ::= g::Grammars  r::Grammars  buildGrammar::String  benv::BuildEnv
{
  top.postOps <- [printAllParsingErrors(g.grammarList ++ r.grammarList)];
  top.postOps <- if top.config.noBindingChecking then [] else
    [printAllBindingErrors(g.grammarList ++ r.grammarList)]; 
  top.postOps <- [touchIfaces(r.grammarList, benv.silverGen)];
}

abstract production touchIfaces
top::DriverAction ::= r::[Decorated RootSpec] genPath::String
{
  top.io = touchFiles(map(sviPath(_, genPath), r), top.ioIn);
  top.code = 0;
  top.order = 3;
}
function sviPath
String ::= r::Decorated RootSpec genPath::String
{
  return genPath ++ "src/" ++ grammarToPath(r.declaredName) ++ "Silver.svi";
}

abstract production printAllBindingErrors
top::DriverAction ::= specs::[Decorated RootSpec]
{
  -- Force printing of status before doing error checks
  top.code = unsafeTrace(forward.code, forward.ioIn);
  -- For anyone encountering this hack for the first time,
  -- IO-token passing can force linearity of actions, but
  -- interleaves pure computation in annoying ways.
  -- Without the above, all the error checking work gets done
  -- (to compute return code) before something tries to do IO,
  -- so we wouldn't print first.

  forwards to printAllBindingErrorsHelp(specs)
  with {
    ioIn = printT("Checking For Errors.\n", top.ioIn);
  };
}

abstract production printAllBindingErrorsHelp
top::DriverAction ::= specs::[Decorated RootSpec]
{
  local errs :: [Pair<String [Message]>] = head(specs).grammarErrors;

  local i :: IOToken =
    if null(errs)
    then top.ioIn
    else printT("Errors for " ++ head(specs).declaredName ++ "\n" ++ flatMap(renderMessages(head(specs).grammarSource, _), errs) ++ "\n", top.ioIn);

  local recurse :: DriverAction = printAllBindingErrorsHelp(tail(specs));
  recurse.ioIn = i;

  top.io = if null(specs) then top.ioIn else recurse.io;

  top.code = 
    if null(specs) || (!grammarContainsErrors(errs, head(specs).config.warnError) && recurse.code == 0)
    then 0
    else 20;

  top.order = 1;
}

abstract production printAllParsingErrors
top::DriverAction ::= specs::[Decorated RootSpec]
{
  local errs :: [Pair<String [Message]>] = head(specs).parsingErrors;

  local i :: IOToken =
    if null(errs)
    then top.ioIn
    else printT("Errors for " ++ head(specs).declaredName ++ "\n" ++ flatMap(renderMessages(head(specs).grammarSource, _), errs) ++ "\n", top.ioIn);

  local recurse :: DriverAction = printAllParsingErrors(tail(specs));
  recurse.ioIn = i;

  top.io = if null(specs) then top.ioIn else recurse.io;

  top.code = 
    if null(specs) || (null(errs) && recurse.code == 0)
    then 0
    else 21;

  top.order = 0;
}

function renderMessages
String ::= grammarSource::String  msg::Pair<String [Message]>
{
  return " [" ++ grammarSource ++ msg.fst ++ "]\n" ++ messagesToString(msg.snd) ++ "\n";
}

function grammarContainsErrors
Boolean ::= es::[Pair<String [Message]>]  werr::Boolean
{
  return if null(es) then false
  else containsErrors(head(es).snd, werr) || grammarContainsErrors(tail(es), werr);
}

