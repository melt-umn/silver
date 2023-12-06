grammar silver:compiler:extension:doc:core;

import silver:util:cmdargs only CmdArgs;
import silver:compiler:extension:doc:driver;
import silver:compiler:definition:type;

@{-
 - Parse the doc-comment mini language in a DocComment_t, returning a DclComment.
 -
 - @param conf Global compiler config, used to see if any of the doc-related options 
               are set. If none are, comment is not parsed
 - @param body The Doc-comment token.
 - @return If we are parsing docs, the actual doc-comment parsed into a DclComment
 -         (which could be an errorDclComment) or if we aren't then theEmptyDclComment.
 -}
function parseComment
DclComment ::= conf::Decorated CmdArgs body::DocComment_t
{
    local docCommentContent::String = body.lexeme;
    local parsed::ParseResult<DclComment> = parseDocComment(docCommentContent, body.location.filename);
    local comment::DclComment = if parsed.parseSuccess then parsed.parseTree else errorDclComment(docCommentContent, parsed.parseError);
    return if conf.parseDocs then comment else theEmptyDclComment;
}

function getFreeTypeNames
[String] ::= l::[TyVar]
{
    return case l of
           | tyVarNamed(s)::xs -> s :: getFreeTypeNames(xs)
           | _::xs -> getFreeTypeNames(xs)
           | [] -> []
           end;
}

function getFirstAGDcl
Decorated AGDcl ::= a::Decorated AGDcl
{
    return case a of
           | appendAGDcl(x, _) -> getFirstAGDcl(x)
           | x -> x
           end;
}

@{-
 - This wraps an AGDcl to allow it to be prefixed with a doc comment. AGDcls will by default
 - emit an doc item that notes that it is undocumented (via mkUndocumentedItem.) This does not
 - pass those up, since they are documented here.
 -
 - @forward Forwards to the wrapped AGDcl.
 -}
concrete production documentedAGDcl
top::AGDcl ::= comment::DocComment_t dcl::AGDcl
{
    local parsed::DclComment = parseComment(top.config, comment);

    local paramNamesAndForWhat::Pair<Maybe<[String]> String> = case getFirstAGDcl(forward) of
        | functionDcl(_, _, ns, _) -> (just(ns.argNames), "function")
        | aspectFunctionDcl(_, _, _, ns, _) -> (just(ns.argNames), "function")
        | productionDcl(_, _, _, ns, _) -> (just(ns.argNames), "production")
        | aspectProductionDcl(_, _, _, ns, _) -> (just(ns.argNames), "production")
        | nonterminalDcl(_, _, _, tl, _, _) -> (just(getFreeTypeNames(tl.freeVariables)), "nonterminal")
        | attributeDclInh(_, _, _, tl, _, _, _) -> (just(getFreeTypeNames(tl.freeVariables)), "attribute")
        | attributeDclSyn(_, _, _, tl, _, _, _) -> (just(getFreeTypeNames(tl.freeVariables)), "attribute")
        -- another case for precise functions
        | _ -> (just([]), if isDoubleComment then "standalone" else "other")
        end;

    parsed.paramNames = paramNamesAndForWhat.fst;
    parsed.isForWhat = paramNamesAndForWhat.snd;
    parsed.downDocConfig = top.downDocConfig;
    parsed.docEnv = top.docEnv;
    parsed.offsetLocation = comment.location;
    parsed.indentBy = "";
    
    top.upDocConfig <- parsed.upDocConfig;
    top.docErrors <- parsed.errors;

    local isDoubleComment::Boolean = case forward of documentedAGDcl(_, _) -> true | _ -> false end;
    top.docs := if isDoubleComment
                  then [standaloneDclCommentItem(parsed)] ++ forward.docs
                  else [attachNote logicalLocationFromOrigin(dcl) on
                          dclCommentItem(forward.docForName, forward.docUnparse, forward.grammarName, parsed)
                        end]
                       ++ if length(forward.docs) > 1 then tail(forward.docs) else [];
    top.errors <- if isDoubleComment
                    then [wrnFromOrigin(parsed, "Doc comment not immediately preceding AGDcl, so association is ambiguous. Treating as standalone comment. Mark with @@{- instead of @{- to silence this warning.")]
                    else [];

    forwards to dcl;
}

@{-
 - Doc comment without associated AGDcl.
 - 
 - @forward emptyAGDcl.
 -}
concrete production standaloneCommentAGDcl
top::AGDcl ::= '@' comment::DocComment_t
layout {}
{
    local parsed::DclComment = parseComment(top.config, comment);

    parsed.paramNames = nothing();
    parsed.isForWhat = "standalone";
    parsed.downDocConfig = top.downDocConfig;
    parsed.docEnv = top.docEnv;
    parsed.offsetLocation = comment.location;
    parsed.indentBy = "";
    
    top.upDocConfig <- parsed.upDocConfig;
    top.docErrors <- parsed.errors;

    top.docs := [standaloneDclCommentItem(parsed)];
    top.unparse = "";
    forwards to emptyAGDcl();
}
