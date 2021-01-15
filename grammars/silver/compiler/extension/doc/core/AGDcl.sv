grammar silver:compiler:extension:doc:core;

synthesized attribute docUnparse :: String occurs on AGDcl;

function parseComment
Pair<[Message] DclComment> ::= body::DocComment_t
{
    local docCommentContent::String = body.lexeme;
    local parsed::ParseResult<DclComment> = parseDocComment(docCommentContent, body.location.filename);

    local errorMessage::Message =
        case parsed.parseError of
        | syntaxError(_, location, expected, matched) ->
            let printLoc::Location = childParserLoc(body.location, location, 0, 0, 0, 0)
            in wrn(printLoc,
                s"Doc Comment Parse Error at ${printLoc.filename} line ${toString(printLoc.line)} column ${toString(printLoc.column)}"
                ++ s"\n\tExpected a token of one of the following types: [${implode(", ", expected)}]"
                ++ s"\n\tInput currently matches: [${implode(", ", matched)}]") end
        | unknownParseError(s, f) -> wrn(body.location, s"Doc comment unknown parse error: unknownParseError(${s}, ${f})")
        end;
    local comment::DclComment = if parsed.parseSuccess then parsed.parseTree else errorDclComment(docCommentContent, errorMessage.output, location=body.location);
    local errs::[Message] = if parsed.parseSuccess then [] else [errorMessage];
    return pair(errs, comment);
}

concrete production documentedAGDcl
top::AGDcl ::= comment::DocComment_t dcl::AGDcl
{
    local res::Pair<[Message] DclComment> = parseComment(comment);
    top.errors <- res.fst;

    local isDoubleComment::Boolean = length(dcl.docs) != 0;
    top.docs := if isDoubleComment
                  then [standaloneDclCommentItem(res.snd)] ++ dcl.docs
                  else [dclCommentItem(dcl, res.snd)];
    top.errors <- if isDoubleComment
                    then [wrn(comment.location, "Doc comment not immediately preceding AGDcl, so association is ambiguous. Treating as standalone comment. Mark with @@{- instead of @{- to silence this warning.")]
                    else [];

    forwards to dcl;
}

concrete production standaloneCommentAGDcl
top::AGDcl ::= '@' comment::DocComment_t
{
    local res::Pair<[Message] DclComment> = parseComment(comment);
    top.errors <- res.fst;

    top.docs := [standaloneDclCommentItem(res.snd)];
    top.unparse = "";
    forwards to emptyAGDcl(location=top.location);
}