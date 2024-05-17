grammar silver:compiler:extension:doc:core;

imports silver:compiler:extension:data;

attribute docUnparse, docForName occurs on DataConstructor;

propagate upDocConfig, downDocConfig, docDcls, docs on DataConstructors;

aspect production dataDcl
top::AGDcl ::= 'data' id::Name tl::BracketedOptTypeExprs '=' ctors::DataConstructors ';'
{
  propagate grammarName, config, docEnv;
  top.docForName = id.name;
  top.docUnparse = s"`data nonterminal ${id.unparse}`";
  top.docDcls := (id.name, docDclInfo(id.name, sourceLocation=id.nameLoc, sourceGrammar=top.grammarName)) :: ctors.docDcls;
  top.docs := mkUndocumentedItem(top.docForName, top) :: ctors.docs;
  ctors.downDocConfig = top.downDocConfig;
}

aspect production dataDclWith
top::AGDcl ::= 'data' id::Name tl::BracketedOptTypeExprs '=' ctors::DataConstructors  'with' attrs::QNames ';'
{
  propagate grammarName, config, docEnv;
  top.docForName = id.name;
  top.docUnparse = s"`data nonterminal ${id.unparse}`";
  top.docDcls := (id.name, docDclInfo(id.name, sourceLocation=id.nameLoc, sourceGrammar=top.grammarName)) :: ctors.docDcls;
  top.docs := mkUndocumentedItem(top.docForName, top) :: ctors.docs;
  ctors.downDocConfig = top.downDocConfig;
}

concrete production documentedConsDataConstructor
top::DataConstructors ::= h::DataConstructor comment::DocComment_t '|' t::DataConstructors
{
  forwards to
    case t of
    | consDataConstructor(h1, _, t1) ->
      consDataConstructor(
        @h, '|',
        consDataConstructor(documentedConstructor(comment, new(h1)), '|', new(t1)))
    | oneDataConstructor(h1) ->
      consDataConstructor(
        @h, '|',
        oneDataConstructor(documentedConstructor(comment, new(h1))))
    | nilDataConstructor() -> consDataConstructor(@h, '|', @t)
    end;
}

concrete production documentedConstructor
top::DataConstructor ::= comment::DocComment_t item::DataConstructor
{
  local parsed::DclComment = parseComment(top.config, comment);

  parsed.paramNames = nothing();
  parsed.isForWhat = "abstract production";
  parsed.downDocConfig = top.downDocConfig;
  parsed.docEnv = top.docEnv;
  parsed.offsetLocation = comment.location;
  parsed.indentBy = ">";

  top.upDocConfig <- parsed.upDocConfig;
  top.docErrors <- parsed.errors;

  local realDclDocs::[CommentItem] = filter((\x::CommentItem->!x.stub), forward.docs);
  local isDoubleComment::Boolean = length(realDclDocs) != 0;
  top.docs := if isDoubleComment
                then [standaloneDclCommentItem(parsed)] ++ realDclDocs
                else [attachNote logicalLocationFromOrigin(item) on
                        dclCommentItem(top.docForName, forward.docUnparse, forward.grammarName, parsed)
                      end];
  top.docErrors <-
    if isDoubleComment
    then [wrnFromOrigin(parsed, "Doc comment not immediately preceding constructor, so association is ambiguous. Treating as standalone comment. Mark with @@{- instead of @{- to silence this warning.")]
    else [];

  forwards to @item;
}

aspect production dataConstructor
top::DataConstructor ::= id::Name rhs::ProductionRHS
{
  top.docForName = id.name;
  top.docUnparse = "`abstract production " ++ id.name ++ "` &nbsp; (`" ++ top.ntName ++ top.ntTypeArgs.unparse ++ " ::= " ++ rhs.unparse ++ "`)";
  top.docDcls := [];
  top.docs := [undocumentedItem(top.docForName, top.docUnparse, top.grammarName)];
  top.upDocConfig := [];
}