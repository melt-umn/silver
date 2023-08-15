grammar silver:compiler:extension:doc:core;

imports silver:compiler:extension:data;

attribute docUnparse, docForName occurs on DataConstructor;

propagate upDocConfig, downDocConfig, docDcls, docs on DataConstructors;

aspect production dataDcl
top::AGDcl ::= 'data' id::Name tl::BracketedOptTypeExprs '=' ctors::DataConstructors ';'
{
  propagate grammarName, config, docEnv;
  top.docForName = id.name;
  top.docUnparse = s"`nonterminal ${id.unparse}`";
  top.docDcls := (id.name, docDclInfo(id.name, sourceLocation=top.location, sourceGrammar=top.grammarName)) :: ctors.docDcls;
  top.docs := mkUndocumentedItem(top.docForName, top) :: ctors.docs;
  ctors.downDocConfig = top.downDocConfig;
}

aspect production dataDclWith
top::AGDcl ::= 'data' id::Name tl::BracketedOptTypeExprs '=' ctors::DataConstructors  'with' attrs::QNames ';'
{
  propagate grammarName, config, docEnv;
  top.docForName = id.name;
  top.docUnparse = s"`nonterminal ${id.unparse}`";
  top.docDcls := (id.name, docDclInfo(id.name, sourceLocation=top.location, sourceGrammar=top.grammarName)) :: ctors.docDcls;
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
        h, '|',
        consDataConstructor(documentedConstructor(comment, h1, location=h1.location), '|', t1, location=t.location),
        location=top.location)
    | oneDataConstructor(h1) ->
      consDataConstructor(
        h, '|',
        oneDataConstructor(documentedConstructor(comment, h1, location=h1.location), location=t.location),
        location=top.location)
    | nilDataConstructor() -> consDataConstructor(h, '|', t, location=top.location)
    end;
}

concrete production documentedConstructor
top::DataConstructor ::= comment::DocComment_t item::DataConstructor
{
  local parsed::DclComment = parseComment(top.config, comment);

  parsed.paramNames = nothing();
  parsed.isForWhat = "production";
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
                else [dclCommentItem(top.docForName, forward.docUnparse, forward.grammarName, item.location, parsed)];
  top.docErrors <-
    if isDoubleComment
    then [wrn(parsed.location, "Doc comment not immediately preceding constructor, so association is ambiguous. Treating as standalone comment. Mark with @@{- instead of @{- to silence this warning.")]
    else [];

  forwards to item;
}

aspect production dataConstructor
top::DataConstructor ::= id::Name rhs::ProductionRHS
{
  top.docForName = id.name;
  top.docUnparse = "`" ++ id.unparse ++ " " ++ rhs.unparse ++ "`";
  top.docDcls := [];
  top.docs := [undocumentedItem(top.docForName, top.docUnparse, top.grammarName, top.location)];
  top.upDocConfig := [];
}