grammar silver:compiler:extension:doc:core:doclang;
imports silver:compiler:extension:doc:core;
imports silver:langutil;
imports silver:util:treemap as tm;

-- Comment is sequence of blocks
-- Blocks start with a newline or a @param/@return/@prodattr/@forward/...
-- Initial block is a 'normal' block even if no newline (but is other type if has @tag)

synthesized attribute doEmit::Boolean occurs on DclComment;

inherited attribute paramNames::[String] occurs on DclComment;
inherited attribute isForWhat::String occurs on DclComment;

synthesized attribute paramBlocks::[Pair<String String>];
synthesized attribute otherBlocks::[Pair<String String>];
synthesized attribute configArgs::[Pair<String ConfigValue>];

nonterminal DclComment layout {} with docEnv, body, errors, location, downDocConfig, upDocConfig;

nonterminal DclCommentBlocks layout {} with paramBlocks, otherBlocks, configArgs, location, docEnv, errors;
nonterminal DclCommentStrictBlocks layout {} with paramBlocks, otherBlocks, configArgs, location, docEnv, errors;
nonterminal DclCommentBlock layout {} with paramBlocks, otherBlocks, configArgs, body, location, docEnv, errors;

nonterminal ConfigValue layout {} with location;

nonterminal DclCommentLines layout {} with body, location, docEnv, errors;

nonterminal DclCommentParts layout {} with body, location, docEnv, errors;
nonterminal DclCommentPart layout {} with body, location, docEnv, errors;

propagate errors on DclCommentBlocks, DclCommentStrictBlocks, DclCommentBlock,
    DclCommentLines, DclCommentParts, DclCommentPart;

autocopy attribute offsetLocation::Location occurs on 
    DclComment, DclCommentBlocks, DclCommentStrictBlocks, DclCommentBlock,
    DclCommentLines, DclCommentParts, DclCommentPart;

parser parseDocComment::DclComment {
    silver:compiler:extension:doc:core:doclang;
}

concrete production emptyDclComment
top::DclComment ::= EmptyDclComment_t
{
    top.errors := [];
    top.body = "";
    top.upDocConfig := [];
    top.doEmit = !doesExcludeFile(top.downDocConfig);
}

concrete production normalDclComment
top::DclComment ::= InitialIgnore_t blocks::DclCommentBlocks FinalIgnore_t
{
    local warningBlocks::[String] = getBlocksNamed(blocks.otherBlocks, "warning");
    local prodAttrBlocks::[String] = getBlocksNamed(blocks.otherBlocks, "prodAttr");
    local returnBlocks::[String] = getBlocksNamed(blocks.otherBlocks, "return");
    local forwardBlocks::[String] = getBlocksNamed(blocks.otherBlocks, "forward");
    local normalBlocks::[String] = getBlocksNamed(blocks.otherBlocks, "normal");

    local paramBlocks::[String] = 
        map(snd, sortBy(
            (\x::Pair<String String> y::Pair<String String> ->
                positionOf(x.fst, top.paramNames) < positionOf(y.fst, top.paramNames)),
            blocks.paramBlocks));

    local errs::[String] =
        (if (length(paramBlocks) != length(top.paramNames)) && (length(paramBlocks) != 0)
        then ["Arity doesn't match in doc-comment"]
        else checkParams(top.paramNames, map(fst, blocks.paramBlocks))) ++
        (if length(forwardBlocks) > 1 then ["More than one forward block in doc-comment"] else []) ++
        (if length(returnBlocks) > 1 then ["More than one return block in doc-comment"] else []) ++
        (if length(returnBlocks) > 0 && top.isForWhat!="function" then ["@return in non-function doc-comment"] else []) ++
        (if length(forwardBlocks) > 0 && top.isForWhat!="production" then ["@forward in non-production doc-comment"] else []) ++
        (if length(prodAttrBlocks) > 0 && !(top.isForWhat=="function" || top.isForWhat == "production") then ["@prodattr in non function-or-production doc comment"] else []) ++
        (if length(paramBlocks) > 0 && !(top.isForWhat=="function" || top.isForWhat == "production") then ["@param in non function-or-production doc comment"] else []) ++
        confResult.fst;

    top.errors := map((\x::String -> wrn(top.offsetLocation, x)), errs);
    top.errors <- blocks.errors;

    top.body =
        implode("\n\n",
            warningBlocks ++
            paramBlocks ++
            prodAttrBlocks ++
            returnBlocks ++
            forwardBlocks ++
            normalBlocks);

    local confResult::Pair<[String] [DocConfigSetting]> = processConfigOptions([], blocks.configArgs, []);
    top.upDocConfig := confResult.snd;

    top.doEmit =
        ((length(blocks.otherBlocks) + length(paramBlocks)) != 0) &&
        (!doesExcludeFile(top.downDocConfig)) &&
        (!fromMaybe(false, fromMaybe(kwdValue(terminal(ConfigValueKeyword_t, "off"),
            location=top.location), lookup("hide", blocks.configArgs)).asBool));
}

function getBlocksNamed
[String] ::= l::[Pair<String String>] f::String
{
    return flatMap((\x::Pair<String String> -> if x.fst==f then [x.snd] else []), l);
}

function processConfigOptions
Pair<[String] [DocConfigSetting]> ::= alreadyErrs::[String] args::[Pair<String ConfigValue>] conf::[DocConfigSetting]
{
    local arg::Pair<String ConfigValue> =
        case args of
        | a::_ -> a
        end;

    local err::[String] =
        case arg of
        | pair("split", v) -> if !v.asBool.isJust then ["@config split takes a boolean value (or just @config split)"] else []
        | pair("fileSplit", v) -> if !v.asBool.isJust then ["@config fileSplit takes a boolean value (or just @config fileSplit)"] else []
        | pair("noToc", v) -> if !v.asBool.isJust then ["@config noToc takes a boolean value (or just @config noToc)"] else []
        | pair("weight", v) -> if !v.asInteger.isJust then ["@config weight takes an integer"] else []
        | pair("grammarWeight", v) -> if !v.asInteger.isJust then ["@config grammarWeight takes an integer"] else []
        | pair("title", v) -> if !v.asString.isJust then ["@config title takes a string in quotes"] else []
        | pair("grammarTitle", v) -> if !v.asString.isJust then ["@config grammarTitle takes a string in quotes"] else []
        | pair("excludeFile", v) -> if !v.asBool.isJust then ["@config excludeFile takes a boolean value (or just @config excludeFile)"] else []
        | pair("excludeGrammar", v) -> if !v.asBool.isJust then ["@config excludeGrammar takes a boolean value (or just @config excludeGrammar)"] else []
        | pair("hide", v) -> if !v.asBool.isJust then ["@config hide takes a boolean value (or just @config hide or @hide)"] else []
        | pair(k, _) -> ["Unknown @config directive '"++k++"'"]
        end;

    local boundConf::[DocConfigSetting] =
        case arg of
        | pair("split", v) -> [splitConfig(v.asBool.fromJust)]
        | pair("fileSplit", v) -> [fileSplitConfig(v.asBool.fromJust)]
        | pair("noToc", v) -> [tocConfig(!v.asBool.fromJust)]
        | pair("weight", v) -> [weightConfig(v.asInteger.fromJust)]
        | pair("grammarWeight", v) -> [grammarWeightConfig(v.asInteger.fromJust)]
        | pair("title", v) -> [titleConfig(v.asString.fromJust)]
        | pair("grammarTitle", v) -> [grammarTitleConfig(v.asString.fromJust)]
        | pair("excludeFile", v) -> [fileNoDocsConfig(v.asBool.fromJust)]
        | pair("excludeGrammar", v) -> [grammarNoDocsConfig(v.asBool.fromJust)]
        | pair("hide", _) -> []
        end;

    return case args of
           | [] -> pair(alreadyErrs, conf)
           | _::r when length(err)!=0 -> processConfigOptions(err++alreadyErrs, r, conf)
           | _::r -> processConfigOptions(alreadyErrs, r, boundConf ++ conf)
           end;
}

function checkParams
[String] ::= p::[String] b::[String]
{
    return case p, b of
           | pn::p_, bn::b_ when pn==bn -> checkParams(p_, b_)
           | pn::p_, bn::b_ -> s"Param '${pn}' in wrong order in doc-comment" :: checkParams(p_, b_)
           | _, _ -> []
           end;
}

abstract production errorDclComment
top::DclComment ::= content::String error::ParseError
{
    top.body = s"""(Comment parse error, raw content)
```
${content}
```
""";

    local errorMessage::Message =
        case error of
        | syntaxError(_, location, expected, matched) ->
            let printLoc::Location = childParserLoc(top.offsetLocation, location, 0, 0, 0, 0)
            in wrn(printLoc,
                s"Doc Comment Parse Error at ${printLoc.filename} line ${toString(printLoc.line)} column ${toString(printLoc.column)}"
                ++ s"\n\tExpected a token of one of the following types: [${implode(", ", expected)}]"
                ++ s"\n\tInput currently matches: [${implode(", ", matched)}]") end
        | unknownParseError(s, f) -> wrn(top.location, s"Doc comment unknown parse error: unknownParseError(${s}, ${f})")
        end;

    top.errors := [errorMessage];
    top.doEmit = true;

    top.upDocConfig := [];
}




concrete production initialCommentBlocks
top::DclCommentBlocks ::= block::DclCommentLines blocks::DclCommentStrictBlocks
{
    top.otherBlocks = 
        pair("normal", block.body) :: blocks.otherBlocks;
    top.paramBlocks = blocks.paramBlocks;
    top.configArgs = blocks.configArgs;
}

concrete production passThruCommentBlocks
top::DclCommentBlocks ::= blocks::DclCommentStrictBlocks
{
    top.otherBlocks = blocks.otherBlocks;
    top.paramBlocks = blocks.paramBlocks;
    top.configArgs = blocks.configArgs;
}



concrete production nilCommentBlocks
top::DclCommentStrictBlocks ::=
{
    top.otherBlocks = [];
    top.paramBlocks = [];
    top.configArgs = [];
}

concrete production consCommentBlocks
top::DclCommentStrictBlocks ::= block::DclCommentBlock rest::DclCommentStrictBlocks  
{
    top.paramBlocks = block.paramBlocks ++ rest.paramBlocks;
    top.otherBlocks = block.otherBlocks ++ rest.otherBlocks;
    top.configArgs = block.configArgs ++ rest.configArgs;
}




concrete production commentBlock
top::DclCommentBlock ::= EmptyLines_t content::DclCommentLines
{
    top.body = content.body;
    top.otherBlocks = [pair("normal", top.body)];
    top.paramBlocks = [];
    top.configArgs = [];
}

concrete production paramBlock
top::DclCommentBlock ::= Param_t Whitespace_t id::Id_t Whitespace_t content::DclCommentLines
{
    top.body = "{{< hint info >}}\n**Parameter `"++id.lexeme++"`**\\\n " ++ content.body ++ "\n{{< /hint >}}";
    top.otherBlocks = [];
    top.paramBlocks = [pair(id.lexeme, top.body)];
    top.configArgs = [];
}

concrete production prodattrBlock
top::DclCommentBlock ::= Prodattr_t Whitespace_t id::Id_t Whitespace_t content::DclCommentLines
{
    top.body = "{{< hint warning >}}\n**Production Attribute `"++id.lexeme++"`**\\\n " ++ content.body ++ "\n{{< /hint >}}";
    top.otherBlocks = [pair("prodAttr", top.body)];
    top.paramBlocks = [];
    top.configArgs = [];
}

concrete production returnBlock
top::DclCommentBlock ::= Return_t Whitespace_t content::DclCommentLines
{
    top.body = "{{< hint info >}}\n**Return**\\\n " ++ content.body ++ "\n{{< /hint >}}";
    top.otherBlocks = [pair("return", top.body)];
    top.paramBlocks = [];
    top.configArgs = [];
}

concrete production forwardBlock
top::DclCommentBlock ::= Forward_t Whitespace_t content::DclCommentLines
{
    top.body = "{{< hint warning >}}\n**Forward**\\\n " ++ content.body ++ "\n{{< /hint >}}";
    top.otherBlocks = [pair("forward", top.body)];
    top.paramBlocks = [];
    top.configArgs = [];
}

concrete production warningBlock
top::DclCommentBlock ::= Warning_t Whitespace_t content::DclCommentLines
{
    top.body = "{{< hint danger >}}\n**WARNING!**\\\n " ++ content.body ++ "\n{{< /hint >}}";
    top.otherBlocks = [pair("warning", top.body)];
    top.paramBlocks = [];
    top.configArgs = [];
}

concrete production hideBlock
top::DclCommentBlock ::= Hide_t
{
    forwards to configBlockImplicitTrue(
        terminal(Config_t, "@config"), terminal(Whitespace_t, ""),
        terminal(Id_t, "hide"), terminal(Whitespace_t, ""), location=top.location);
}

concrete production configBlock
top::DclCommentBlock ::= Config_t Whitespace_t param::Id_t Whitespace_t Equals_t Whitespace_t value::ConfigValue
{
    top.body = "@config " ++ param.lexeme ++ " = " ++ hackUnparse(value); --not emitted
    top.otherBlocks = [];
    top.paramBlocks = [];
    top.configArgs = [pair(param.lexeme, value)];
}

concrete production configBlockImplicitTrue
top::DclCommentBlock ::= Config_t Whitespace_t param::Id_t Whitespace_t
{
    forwards to configBlock($1, $2, $3, $4, terminal(Equals_t, ""), terminal(Whitespace_t, ""),
        kwdValue(terminal(ConfigValueKeyword_t, "true"), location=top.location), location=top.location);
}

synthesized attribute asBool::Maybe<Boolean> occurs on ConfigValue;
synthesized attribute asString::Maybe<String> occurs on ConfigValue;
synthesized attribute asInteger::Maybe<Integer> occurs on ConfigValue;

concrete production kwdValue
top::ConfigValue ::= v::ConfigValueKeyword_t
{
    top.asBool = just(v.lexeme=="on" || v.lexeme=="true" || v.lexeme=="yes");
    top.asString = nothing();
    top.asInteger = nothing();
}

concrete production stringValue
top::ConfigValue ::= v::ConfigValueString_t
{
    top.asBool = nothing();
    top.asString = just(substring(1, length(v.lexeme)-1, v.lexeme));
    top.asInteger = nothing();
}

concrete production integerValue
top::ConfigValue ::= v::ConfigValueInt_t
{
    top.asBool = nothing();
    top.asString = nothing();
    top.asInteger = just(toInteger(v.lexeme));
}

concrete production lastCommentLines
top::DclCommentLines ::= body::DclCommentParts
{
    top.body = body.body;
}

concrete production consCommentLines
top::DclCommentLines ::= body::DclCommentParts Newline_t rest::DclCommentLines
{
    top.body = body.body ++ "\n" ++ rest.body;
}





concrete production firstCommentParts
top::DclCommentParts ::= part::DclCommentPart
{
    top.body = part.body;
}

concrete production snocCommentParts
top::DclCommentParts ::= rest::DclCommentParts part::DclCommentPart
{
    top.body = rest.body ++ part.body;
}


concrete production textCommentPart
top::DclCommentPart ::= part::CommentContent_t
{
    top.body = part.lexeme;
}

concrete production linkCommentPart
top::DclCommentPart ::= '@link' '[' id::Id_t ']'
{
    local res::[DocDclInfo] = tm:lookup(id.lexeme, top.docEnv);
    top.body = case res of
               | [docDclInfo(_, location, grammarName)] -> id.lexeme ++ " at " ++ grammarName ++ "/" ++ location.filename ++ "#" ++ toString(location.line)
               | _ -> s"${id.lexeme} (**BROKEN LINK**)"
               end;
    top.errors <- case res of
                  | [_] -> []
                  | _ -> [wrn(childParserLoc(top.offsetLocation, top.location, 0, 0, 0, 0),
                            "Broken doc link to `"++id.lexeme++"`")]
                  end;
}
concrete production fileLinkCommentPart
top::DclCommentPart ::= '@file' '[' path::Path_t ']'
{
    top.body = s"[${path.lexeme}](github -> ${path.lexeme})";
}

concrete production escapedAtPart
top::DclCommentPart ::= '@@'
{
    top.body = "@";
}


terminal InitialIgnore_t /@+\{\-[ \t]*\-*[ \t]*([ \t]*\-*[ \t]*\r?\n)*[ \t]*\-*[ \t]*/;
terminal FinalIgnore_t /[\- \r\n]*\-\}/ dominates {CommentContent_t};
terminal EmptyDclComment_t /@+{\-[ \-]*\-}/;

terminal EmptyLines_t /\r?\n([ \t]*\-*[ \t]*\r?\n)+[ \t]*\-*[ \t]*/;
terminal Newline_t /\r?\n[ \t]*\-*[ \t]*/;

terminal CommentContent_t /([^@\r\n\-]|\-[^\r\n}])+/;

terminal EscapedAt_t '@@';

terminal Param_t /([ \t]*\-*[ \t]*\r?\n)*[ \t]*\-*[ \t]*@(param|child)/ lexer classes {BLOCK_KWD};
terminal Return_t /([ \t]*\-*[ \t]*\r?\n)*[ \t]*\-*[ \t]*@return/ lexer classes {BLOCK_KWD};
terminal Forward_t /([ \t]*\-*[ \t]*\r?\n)*[ \t]*\-*[ \t]*@forward/ lexer classes {BLOCK_KWD};
terminal Prodattr_t /([ \t]*\-*[ \t]*\r?\n)*[ \t]*\-*[ \t]*@prodattr/ lexer classes {BLOCK_KWD};
terminal Warning_t /([ \t]*\-*[ \t]*\r?\n)*[ \t]*\-*[ \t]*@warning/ lexer classes {BLOCK_KWD};
terminal Config_t /([ \t]*\-*[ \t]*\r?\n)*[ \t]*\-*[ \t]*@config/ lexer classes {BLOCK_KWD};
terminal Hide_t /([ \t]*\-*[ \t]*\r?\n)*[ \t]*\-*[ \t]*@hide/ lexer classes {BLOCK_KWD};

terminal ConfigValueKeyword_t /(on|off|true|false|yes|no)/;
terminal ConfigValueString_t /[\"]([^\r\n\"\\]|[\\][\"]|[\\][\\]|[\\]b|[\\]n|[\\]r|[\\]f|[\\]t)*[\"]/;
terminal ConfigValueInt_t /\-?[0-9]+/;

terminal Whitespace_t /[\t ]*/;
terminal Equals_t /=?/;

terminal Link_t '@link';
terminal FileLink_t '@file';
terminal OpenBracket_t '[';
terminal CloseBracket_t ']';
terminal Id_t /[a-zA-Z][a-zA-Z0-9_:]*/;
terminal Path_t /[a-zA-Z0-9_\-\/\.]+/;

lexer class BLOCK_KWD dominates CommentContent_t;