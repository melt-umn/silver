grammar silver:langutil:origins;

imports silver:langutil:pp;

synthesized attribute notepp :: String occurs on OriginNote;

aspect default production
top::OriginNote ::=
{
  top.notepp = "<" ++ hackUnparse(top) ++ ">";
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