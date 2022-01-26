grammar silver:core;

-- WARNING: Many of the nonterminals and productions in this file are runtime- and/or compiler-blessed.
--  Don't change their names, grammar locations, or parameters unless you know what your doing
--  (and have made the appropriate runtime and compiler changes!)

nonterminal OriginInfo;
nonterminal OriginInfoType;
nonterminal OriginNote;

synthesized attribute notepp :: String occurs on OriginNote;

synthesized attribute isNewlyConstructed :: Boolean occurs on OriginInfo;
synthesized attribute originNotes :: [OriginNote] occurs on OriginInfo;
synthesized attribute originType :: OriginInfoType occurs on OriginInfo;

synthesized attribute isBogus :: Boolean occurs on OriginInfoType;

@@{- ## Origin info types
   -
   - Single instances of the following are constructed once in OriginsUtil.java in the runtime and used 
   -  to indicate when the origin information was computed.
   -}

@{- Information was computed at the site of invoking a constructor (this is "normal") -}
abstract production setAtConstructionOIT
top::OriginInfoType ::=
{
  top.isBogus = false;
}

@{- Result of calling new(x) on a tracked nonterminal (including children of x that were also new-ed) -}
abstract production setAtNewOIT
top::OriginInfoType ::=
{
  top.isBogus = false;
}

@{-
  - Result of forwarding to a nonterminal. This is a little weird because there's an extra indirection.
  - The attached origin info has an origin pointing to the value that was computed for the production
  - to forward to. At forwarding time (in evalForward) it's copied and has an origin attached of this
  - type. This is so that it's possible to tell something was forwarded to.
  -}
abstract production setAtForwardingOIT
top::OriginInfoType ::=
{
  top.isBogus = false;
}

@{- Result of doing foo.bar (this is "normal") -}
abstract production setAtAccessOIT
top::OriginInfoType ::=
{
  top.isBogus = false;
}

@{- The origin was set when constructing a concrete production in the parser (will be a parsedOriginInfo) -}
abstract production setFromParserOIT
top::OriginInfoType ::=
{
  top.isBogus = false;
}

@{- The origin was set in something constructed in a parser action block -}
abstract production setFromParserActionOIT
top::OriginInfoType ::=
{
  top.isBogus = true;
}

@{-
  - This is a catchall for stuff constructed in java (really only used in the SilverComparator and in the XML lib)
  - where the java library dosen't keep track of origins info meaningfully
  -}
abstract production setFromFFIOIT
top::OriginInfoType ::=
{
  top.isBogus = true;
}

@{- This originates from something via a call to `reflect` -}
abstract production setFromReflectionOIT
top::OriginInfoType ::=
{
  top.isBogus = true;
}

@{- This originates from it's reflective representation via a call to `reify` -}
abstract production setFromReificationOIT
top::OriginInfoType ::=
{
  top.isBogus = true;
}

@{-
  - This was constructed in `main` or in a function called from `main` without
  - passing through a context with a meaningful nonterminal to use instead
  -}
abstract production setFromEntryOIT
top::OriginInfoType ::=
{
  top.isBogus = false;
}

@{- This is a global -}
abstract production setInGlobalOIT
top::OriginInfoType ::=
{
  top.isBogus = false;
}


@@{- ## OriginInfo represent the origin information contained in nodes/values -}

@{- 'catchall' for origins that don't encode other info -}
abstract production otherOriginInfo
top::OriginInfo ::= typ::OriginInfoType source::String notes::[OriginNote]
{
  top.isNewlyConstructed = true;
  top.originNotes = notes;
  top.originType = typ;
}

@{- The production originated from a sequence of tokens at `source` in Copper -}
abstract production parsedOriginInfo
top::OriginInfo ::= typ::OriginInfoType source::Location notes::[OriginNote]
{
  top.isNewlyConstructed = true;
  top.originNotes = notes;
  top.originType = typ;
}

@@{- The following two are the same modulo if a redex is set or not
   -  `origin` is the node that this node originated from, `originNotes` are
   -  notes set on the control-flow path to where the origin was set.
   -  `redex` is the node that catalyzed the movement of this node to where it
   -  is now (i.e. where a `foo.bar` happaned that 'moved' the `bar` in the new
   -  tree. `redexNotes` are similarly the notes set on the control-flow path to
   -  where the tree motion that set the redex occurred.
   -  `newlyConstructed` is `er` from the paper, and represents if the node
   -  is not the result of a basically no-op transformation.
   -}

@{- See above -}
abstract production originOriginInfo
top::OriginInfo ::= typ::OriginInfoType 
                    origin :: a
                    originNotes :: [OriginNote]
                    newlyConstructed :: Boolean
{
  top.isNewlyConstructed = newlyConstructed;
  top.originNotes = originNotes;
  top.originType = typ;
}

@{- See above -}
abstract production originAndRedexOriginInfo
top::OriginInfo ::= typ::OriginInfoType 
                    origin :: a
                    originNotes :: [OriginNote]
                    redex :: b
                    redexNotes :: [OriginNote]
                    newlyConstructed :: Boolean
{
  top.isNewlyConstructed = newlyConstructed;
  top.originNotes = originNotes;
  top.originType = typ;
}



-- These are some simple builtin node types, and may be generated automatically
--  inside the compiler.

aspect default production
top::OriginNote ::=
{
  top.notepp = "<" ++ hackUnparse(top) ++ ">";
}

abstract production traceNote
top::OriginNote ::= loc::String
{
  
}

abstract production originDbgNote
top::OriginNote ::= string::String
{
  
}

abstract production dbgNote
top::OriginNote ::= string::String
{
  
}

abstract production logicalLocationNote
top::OriginNote ::= loc::Location
{
  
}

@{-
  - Can be attached automatically by the compiler to show the control-flow path leading to where an origin
  - was set. Actually pretty useful for debugging client code too.
  -}
abstract production ruleLocNote
top::OriginNote ::= attributeName::String sourceGrammar::String prod::String nt::String sourceLocation::Location
{
  
}


@{-
  - Compute the 'chain' of origins leading back to whatever the first thing without an origin (really without
  - an origin that has an `origin` field.) -}
function getOriginInfoChain
[OriginInfo] ::= l::a
{
  return case getOriginInfo(l) of
         | just(info) -> 
             case info of
             | originOriginInfo(_, o, _, _) -> info :: getOriginInfoChain(o)
             | originAndRedexOriginInfo(_, o, _, _, _, _) -> info :: getOriginInfoChain(o)
             | _ -> [info]
             end
         | _ -> []
        end;
}

@{- Low level accessor for getting OriginInfo (maybe) from a node. -}
function getOriginInfo
Maybe<OriginInfo> ::= arg::a
{
  return javaGetOrigin(arg);
}

@{- Walk back to the first thing with an origin in the history of `a`. -}
function getUrOrigin
Maybe<OriginInfo> ::= arg::a
{
  return case getOriginInfoChain(arg) of
         | [] -> nothing()
         | l -> just(last(l))
         end;
}

@{- Try to walk back to a parsedOriginInfo and extract the location the node came from in the source -}
function getParsedOriginLocation
Maybe<Location> ::= arg::a
{
  return getParsedOriginLocation_helper(getOriginInfoChain(arg));
}

@{- @hide -}
function getParsedOriginLocation_helper
Maybe<Location> ::= chain::[OriginInfo]
{
  return case chain of
         | [] -> nothing()
         | link::rest -> 
             case link of
             | parsedOriginInfo(_, l, _) -> just(l)
             | other -> case getParsedOriginLocation_findLogicalLocationNote(other.originNotes) of
                        | nothing() -> getParsedOriginLocation_helper(rest)
                        | x -> x
                        end
             end
         end;
}

@{- @hide -}
function getParsedOriginLocation_findLogicalLocationNote
Maybe<Location> ::= notes::[OriginNote]
{
  return case notes of
         | [] -> nothing()
         | logicalLocationNote(l)::_ -> just(l)
         | x::r -> getParsedOriginLocation_findLogicalLocationNote(r)
         end;
}

@{-
  - Try to walk back to a parsedOriginInfo and extract the location the node came from in the source,
  - giving diagnostic garbage if failed.
  -}
function getParsedOriginLocationOrFallback
Location ::= arg::a
{
  return case getParsedOriginLocation(arg) of
         | just(l) -> l
         | _ -> txtLoc("<getParsedOriginLocationOrFallback failed: arg: " ++ hackUnparse(arg) ++ 
                       " ochain: " ++ hackUnparse(getOriginInfoChain(arg)) ++ ">")
         end;
}

@{-
  - Dump out two objects in a format for svdraw2 to consume and draw their
  - structure and the origins links that connect them (and any intermediate
  - objects. The only difference between `start` and `stop` is that they will
  - be specially colored in the visualization diagram.)
  -}
function printObjectPairForOriginsViz
IOToken ::= start::a stop::b io::IOToken
{
  return printT(
    "\n\n\n---SVDRAW2 START---" ++
    "\n" ++ sexprify(start) ++
    "\n" ++ sexprify(stop) ++
    "\n" ++ "---SVDRAW2 END---\n\n\n", io);
}

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

closed tracked nonterminal AmbientOriginNT;

@{- Useful for accessing the "ambient" origin, i.e., what origin does a created node get? Create one and find out! -}
abstract production ambientOrigin
top::AmbientOriginNT ::= 
{
  
}

@{- Call fn in a context where notes have been added to the origins context -}
function callWithListOfNotes
a ::= notes::[OriginNote] fn::(a::=)
{
  return case notes of
         | [] -> fn()
         | x::xs -> attachNote x on callWithListOfNotes(xs, fn) end
         end;
}
