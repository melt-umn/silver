grammar silver:compiler:extension:doc:core;

function parseComment
DclComment ::= body::DocComment_t
{
    local docCommentContent::String = body.lexeme;
    local parsed::ParseResult<DclComment> = parseDocComment(docCommentContent, body.location.filename);
    local comment::DclComment = if parsed.parseSuccess then parsed.parseTree else errorDclComment(docCommentContent, parsed.parseError, location=body.location);
    return comment;
}

concrete production documentedAGDcl
top::AGDcl ::= comment::DocComment_t dcl::AGDcl
{
    local parsed::DclComment = parseComment(comment);

    local paramNamesAndForWhat::Pair<[String] String> = case dcl of
        | functionDcl(_, _, ns, _) -> pair(ns.argNames, "function")
        | productionDcl(_, _, _, ns, _) -> pair(ns.argNames, "production")
        | _ -> pair([], if isDoubleComment then "standalone" else "other")
        end;

    parsed.paramNames = paramNamesAndForWhat.fst;
    parsed.isForWhat = paramNamesAndForWhat.snd;
    parsed.downDocConfig = top.downDocConfig;
    parsed.docEnv = top.docEnv;
    parsed.offsetLocation = comment.location;
    parsed.indentBy = "";

    dcl.downDocConfig = top.downDocConfig;
    
    top.upDocConfig <- parsed.upDocConfig ++ dcl.upDocConfig;
    top.errors <- parsed.errors;

    local isDoubleComment::Boolean = case dcl of documentedAGDcl(_, _) -> true | _ -> false end;
    top.docs := if isDoubleComment
                  then [standaloneDclCommentItem(parsed)] ++ dcl.docs
                  else [dclCommentItem(dcl.docForName, dcl.docUnparse, dcl.grammarName, dcl.location, parsed)]
                       ++ if length(dcl.docs) > 1 then tail(dcl.docs) else [];
    top.errors <- if isDoubleComment
                    then [wrn(parsed.location, "Doc comment not immediately preceding AGDcl, so association is ambiguous. Treating as standalone comment. Mark with @@{- instead of @{- to silence this warning.")]
                    else [];

    forwards to dcl;
}

concrete production standaloneCommentAGDcl
top::AGDcl ::= '@' comment::DocComment_t
{
    local parsed::DclComment = parseComment(comment);

    parsed.paramNames = [];
    parsed.isForWhat = "standalone";
    parsed.downDocConfig = top.downDocConfig;
    parsed.docEnv = top.docEnv;
    parsed.offsetLocation = comment.location;
    parsed.indentBy = "";
    
    top.upDocConfig <- parsed.upDocConfig;
    top.errors <- parsed.errors;

    top.docs := [standaloneDclCommentItem(parsed)];
    top.unparse = "";
    forwards to emptyAGDcl(location=top.location);
}