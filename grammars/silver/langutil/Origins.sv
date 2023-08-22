grammar silver:langutil;

import silver:langutil:pp;
import silver:reflect;

attribute pp occurs on OriginInfoType;

aspect production setAtConstructionOIT
top::OriginInfoType ::=
{
  top.pp = pp"construction";
}

aspect production setAtNewOIT
top::OriginInfoType ::=
{
  top.pp = pp"new";
}

aspect production setAtForwardingOIT
top::OriginInfoType ::=
{
  top.pp = pp"forwarding";
}

aspect production setAtAccessOIT
top::OriginInfoType ::=
{
  top.pp = pp"access";
}

aspect production setFromParserOIT
top::OriginInfoType ::=
{
  top.pp = pp"parser";
}

aspect production setFromParserActionOIT
top::OriginInfoType ::=
{
  top.pp = pp"parse action";
}

aspect production setFromFFIOIT
top::OriginInfoType ::=
{
  top.pp = pp"foreign function";
}

aspect production setFromReflectionOIT
top::OriginInfoType ::=
{
  top.pp = pp"reflect";
}

aspect production setFromReificationOIT
top::OriginInfoType ::=
{
  top.pp = pp"reify";
}

aspect production setFromEntryOIT
top::OriginInfoType ::=
{
  top.pp = pp"main function";
}

aspect production setInGlobalOIT
top::OriginInfoType ::=
{
  top.pp = pp"global";
}

attribute pp occurs on OriginInfo;

aspect production otherOriginInfo
top::OriginInfo ::= source::String
{
  top.pp = text(source) ++ originNotesPP(top.originNotes);
}

aspect production parsedOriginInfo
top::OriginInfo ::= source::Location
{
  top.pp = pp"Parsed from ${source}${originNotesPP(top.originNotes)}";
}

aspect production originOriginInfo
top::OriginInfo ::= origin :: a
                    newlyConstructed :: Boolean
{
  local constructionNote::Document =
    case top.originType of
    | setAtConstructionOIT() ->
      if newlyConstructed then pp"" else pp" (preserved)"
    | oit -> pp" (from ${oit})"
    end;
  top.pp = pp"${genericPP(origin)}${constructionNote}${originNotesPP(top.originNotes)}";
}

aspect production originAndRedexOriginInfo
top::OriginInfo ::= origin :: a
                    redex :: b
                    redexNotes :: [OriginNote]
                    newlyConstructed :: Boolean
{
  local constructionNote::Document =
    case top.originType of
    | setAtConstructionOIT() ->
      if newlyConstructed then pp"" else pp" (preserved)"
    | oit -> pp" (from ${oit})"
    end;
  top.pp = pp"${genericPP(origin)}${constructionNote}${originNotesPP(top.originNotes)}, redex ${genericPP(redex)}${originNotesPP(redexNotes)}";
}

function originNotesPP
Document ::= ns::[OriginNote]
{
  return if null(ns) then pp"" else pp": ${text(originNotesToString(ns))}";
}

@{-
 - This note can be attached to indicate that code is generated by an extension,
 - and should not contain errors. If an error is encountered in extension-generated code,
 - a diagnostic message is printed by showMessage.
 -}
production extensionGenerated
top::OriginNote ::= extName::String
{
  top.notepp = just(s"generated by ${extName}");
}

@{-
  - Try to walk back to a parsedOriginInfo and extract the location the node came from in the source,
  - giving diagnostic garbage if failed.
  -}
function getParsedOriginLocationOrFallback
Location ::= arg::a
{
  return
    case getParsedOriginLocationFromChain(getOriginInfoChain(arg)) of
    | just(l) -> l
    | _ -> txtLoc("<getParsedOriginLocationOrFallback failed: " ++ showOriginInfoChain(arg) ++ ">")
    end;
}

@{-
 - Render the origin chain for a term as a human-readable string.
 -}
function showOriginInfoChain
String ::= arg::a
{
  return show(80, ppImplode(pp"${line()} -> ", genericPP(arg) :: map((.pp), getOriginInfoChain(arg))));
}

@{-
 - Shorthand for note specifying logical location as some object's origin
 -}
function logicalLocationFromOrigin
OriginNote ::= arg::a
{
  return logicalLocationNote(getParsedOriginLocationOrFallback(arg));
}

@{-
 - Walk back an orgin chain to determine if an object was generated by an extension.
 -}
function originatesInExt
Maybe<String> ::= chain::[OriginInfo]
{
  return case chain of
         | [] -> nothing()
         | link::rest -> 
             orElse(findExtensionGeneratedNote(link.originNotes),
              originatesInExt(rest))
         end;
}

function findExtensionGeneratedNote
Maybe<String> ::= notes::[OriginNote]
{
  return case notes of
         | [] -> nothing()
         | extensionGenerated(l)::_ -> just(l)
         | x::r -> findExtensionGeneratedNote(r)
         end;
}
