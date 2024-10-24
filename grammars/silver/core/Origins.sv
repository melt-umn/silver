grammar silver:core;

-- WARNING: Many of the nonterminals and productions in this file are runtime- and/or compiler-blessed.
--  Don't change their names, grammar locations, or parameters unless you know what your doing
--  (and have made the appropriate runtime and compiler changes!)

@{- Origin info types
   -
   - Single instances of the following are constructed once in OriginsUtil.java in the runtime and used 
   -  to indicate when the origin information was computed.
   -}
data OriginInfoType =
@{- Information was computed at the site of invoking a constructor (this is "normal") -}
  setAtConstructionOIT
@{- Result of calling ^x on a tracked nonterminal (including children of x that were also new-ed) -}
| setAtNewOIT
@{-
  - Result of forwarding to a nonterminal. This is a little weird because there's an extra indirection.
  - The attached origin info has an origin pointing to the value that was computed for the production
  - to forward to. At forwarding time (in evalForward) it's copied and has an origin attached of this
  - type. This is so that it's possible to tell something was forwarded to.
  -}
| setAtForwardingOIT
@{- Result of doing foo.bar (this is "normal") -}
| setAtAccessOIT
@{- The origin was set when constructing a concrete production in the parser (will be a parsedOriginInfo) -}
| setFromParserOIT
@{- The origin was set in something constructed in a parser action block -}
| setFromParserActionOIT
@{-
  - This is a catchall for stuff constructed in java (really only used in the SilverComparator and in the XML lib)
  - where the java library dosen't keep track of origins info meaningfully
  -}
| setFromFFIOIT
@{- This originates from something via a call to `reflect` -}
| setFromReflectionOIT
@{- This originates from it's reflective representation via a call to `reify` -}
| setFromReificationOIT
@{-
  - This was constructed in `main` or in a function called from `main` without
  - passing through a context with a meaningful nonterminal to use instead
  -}
| setFromEntryOIT
@{- This is a global -}
| setInGlobalOIT
;

synthesized attribute isBogus :: Boolean occurs on OriginInfoType;
aspect isBogus on OriginInfoType of
| setFromParserActionOIT() -> true
| setFromFFIOIT() -> true
| setFromReflectionOIT() -> true
| _ -> false
end;


@{- OriginInfo represent the origin information contained in nodes/values.
  - originOriginInfo and originAndRedexOriginInfo are the same modulo if a redex is set or not
  -  `origin` is the node that this node originated from, `originNotes` are
  -  notes set on the control-flow path to where the origin was set.
  -  `redex` is the node that catalyzed the movement of this node to where it
  -  is now (i.e. where a `foo.bar` happaned that 'moved' the `bar` in the new
  -  tree. `redexNotes` are similarly the notes set on the control-flow path to
  -  where the tree motion that set the redex occurred.
  -  `newlyConstructed` is `er` from the paper, and represents if the node
  -  is not the result of a basically no-op transformation.
  -}
data OriginInfo =
@{- 'catchall' for origins that don't encode other info -}
  otherOriginInfo source::String
@{- The production originated from a sequence of tokens at `source` in Copper -}
| parsedOriginInfo source::Location

@{- See above -}
| originOriginInfo origin::a newlyConstructed::Boolean
@{- See above -}
| originAndRedexOriginInfo origin::a redex::b redexNotes::[OriginNote] newlyConstructed::Boolean
  with originNotes, originType;

annotation originNotes :: [OriginNote];
annotation originType :: OriginInfoType;

synthesized attribute isNewlyConstructed :: Boolean occurs on OriginInfo;
aspect isNewlyConstructed on OriginInfo of
| otherOriginInfo(_) -> true
| parsedOriginInfo(_) -> true
| originOriginInfo(_, nc) -> nc
| originAndRedexOriginInfo(_, _, _, nc) -> nc
end;

@{-
 - OriginNotes are various pieces of information associated with the origin of a term.
 - A notepp can optionally be supplied, if the note should be displayed in stack traces
 - and other diagnostic messages.
 -}
closed data nonterminal OriginNote with notepp;

synthesized attribute notepp :: Maybe<String>;

aspect default production
top::OriginNote ::=
{
  top.notepp = nothing();
}


-- These are some simple builtin node types, and may be generated automatically
--  inside the compiler.

abstract production traceNote
top::OriginNote ::= loc::String
{
  top.notepp = just(s"at ${loc}");
}

abstract production originDbgNote
top::OriginNote ::= string::String
{
  top.notepp = nothing();
}

abstract production dbgNote
top::OriginNote ::= string::String
{
  top.notepp = just(s"debug ${string}");
}

abstract production logicalLocationNote
top::OriginNote ::= loc::Location
{
  top.notepp = just(s"logical ${loc.filename}:${toString(loc.line)}:${toString(loc.column)}");
}

@{-
  - Can be attached automatically by the compiler to show the control-flow path leading to where an origin
  - was set. Actually pretty useful for debugging client code too.
  -}
abstract production ruleLocNote
top::OriginNote ::= attributeName::String sourceGrammar::String prod::String nt::String sourceLocation::Location
{}


@{-
  - Compute the 'chain' of origins leading back to whatever the first thing without an origin (really without
  - an origin that has an `origin` field.) -}
fun getOriginInfoChain [OriginInfo] ::= l::a =
  case getOriginInfo(l) of
  | just(info) -> 
      case info of
      | originOriginInfo(o, _) -> info :: getOriginInfoChain(o)
      | originAndRedexOriginInfo(o, _, _, _) -> info :: getOriginInfoChain(o)
      | _ -> [info]
      end
  | _ -> []
  end;

@{- Low level accessor for getting OriginInfo (maybe) from a node. -}
fun getOriginInfo Maybe<OriginInfo> ::= arg::a = javaGetOrigin(arg);

@{- Walk back to the first thing with an origin in the history of `a`. -}
fun getUrOrigin Maybe<OriginInfo> ::= arg::a =
  case getOriginInfoChain(arg) of
  | [] -> nothing()
  | l -> just(last(l))
  end;

@{- Try to walk back to a parsedOriginInfo and extract the location the node came from in the source -}
fun getParsedOriginLocation Maybe<Location> ::= arg::a =
  getParsedOriginLocationFromChain(getOriginInfoChain(arg));

fun getParsedOriginLocationFromChain Maybe<Location> ::= chain::[OriginInfo] =
  case chain of
  | [] -> nothing()
  | link::rest -> 
      case link of
      | parsedOriginInfo(l) -> just(l)
      | other -> case getParsedOriginLocation_findLogicalLocationNote(other.originNotes) of
                 | nothing() -> getParsedOriginLocationFromChain(rest)
                 | x -> x
                 end
      end
   end;
@{- @hide -}
fun getParsedOriginLocation_findLogicalLocationNote Maybe<Location> ::= notes::[OriginNote] =
  case notes of
  | [] -> nothing()
  | logicalLocationNote(l)::_ -> just(l)
  | x::r -> getParsedOriginLocation_findLogicalLocationNote(r)
  end;

fun originNotesToString String ::= ns::[OriginNote] = implode(", ", filterMap((.notepp), ns));

fun getOriginNotesString String ::= arg::a =
  case getOriginInfo(arg) of
  | just(oi) -> originNotesToString(oi.originNotes)
  | nothing() -> ""
  end;

@{-
  - Dump out two objects in a format for svdraw2 to consume and draw their
  - structure and the origins links that connect them (and any intermediate
  - objects. The only difference between `start` and `stop` is that they will
  - be specially colored in the visualization diagram.)
  -}
fun printObjectPairForOriginsViz IOToken ::= start::a stop::b io::IOToken =
  printT(
    "\n\n\n---SVDRAW2 START---" ++
    "\n" ++ sexprify(start) ++
    "\n" ++ sexprify(stop) ++
    "\n" ++ "---SVDRAW2 END---\n\n\n", io);

@{- @hide -}
function sexprify
String ::= nt::a
{
  return error("Not impl");
} foreign {
  "java" : return "(common.OriginsUtil.sexprify(%nt%))";
}

@{- @hide -}
function javaGetOrigin
Maybe<OriginInfo> ::= arg::a
{
  return error("Not impl");
} foreign {
  "java" : return "common.OriginsUtil.polyGetOrigin(%arg%)";
}

@{- @hide -}
function javaGetOriginLink
Maybe<a> ::= arg::OriginInfo
{
  return error("Not impl");
} foreign {
  "java" : return "common.OriginsUtil.getOriginLink(%arg%)";
}

closed tracked data nonterminal AmbientOriginNT;

@{- Useful for accessing the "ambient" origin, i.e., what origin does a created node get? Create one and find out! -}
abstract production ambientOrigin
top::AmbientOriginNT ::= 
{
  
}

@{- Call fn in a context where notes have been added to the origins context -}
fun callWithListOfNotes a ::= notes::[OriginNote] fn::(a::=) =
  case notes of
  | [] -> fn()
  | x::xs -> attachNote x on callWithListOfNotes(xs, fn) end
  end;
