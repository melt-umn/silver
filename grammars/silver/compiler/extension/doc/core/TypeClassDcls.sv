grammar silver:compiler:extension:doc:core;

attribute docUnparse occurs on ClassBodyItem, InstanceBodyItem;
attribute docForName occurs on ClassBodyItem, InstanceBodyItem;

@{- What to prefix 'child' declaration names with in docs and for hyperlinking. -}
inherited attribute scopeName :: String occurs on ClassBody, ClassBodyItem, InstanceBody, InstanceBodyItem;
propagate scopeName on ClassBody, ClassBodyItem, InstanceBody, InstanceBodyItem;

aspect production typeClassDcl
top::AGDcl ::= 'class' cl::ConstraintList '=>' id::QNameType var::TypeExpr '{' body::ClassBody '}'
{
  top.docForName = "class " ++ id.name;
  top.docUnparse = s"`class ${id.unparse}`";
  top.docDcls := [(id.name, docDclInfo(top.docForName, sourceLocation=id.nameLoc, sourceGrammar=top.grammarName))] ++ body.docDcls;
  top.docs := [mkUndocumentedItem(top.docForName, top)] ++ body.docs;
  body.scopeName = top.docForName;
  body.downDocConfig = top.downDocConfig;
  top.upDocConfig := body.upDocConfig;
}

aspect production instanceDcl
top::AGDcl ::= 'instance' cl::ConstraintList '=>' id::QNameType ty::TypeExpr '{' body::InstanceBody '}'
{
  top.docForName = "instance "++id.name++" "++ty.unparse;
  top.docUnparse = s"`instance ${id.unparse} ${ty.unparse}`";
  top.docDcls := [(id.name, docDclInfo(top.docForName, sourceLocation=id.nameLoc, sourceGrammar=top.grammarName))] ++ body.docDcls;
  top.docs := [mkUndocumentedItem(top.docForName, top)] ++ body.docs;
  body.scopeName = top.docForName;
  body.downDocConfig = top.downDocConfig;
  top.upDocConfig := body.upDocConfig;
}

aspect production nilClassBody
top::ClassBody ::= 
{
  top.docs := [];
  top.upDocConfig := [];
  top.docDcls := [];
}

aspect production consClassBody
top::ClassBody ::= h::ClassBodyItem t::ClassBody
{
  top.docs := h.docs ++ t.docs;
  top.upDocConfig := h.upDocConfig ++ t.upDocConfig;
  top.docDcls := h.docDcls ++ t.docDcls;
  h.downDocConfig = top.downDocConfig;
  t.downDocConfig = top.downDocConfig;
}

aspect default production
top::ClassBodyItem ::=
{
  top.docForName = "<undefined docForName for "++genericShow(top)++">";
  top.upDocConfig := [];
  top.docDcls := [];
  top.docs := [undocumentedItem(top.scopeName ++ "." ++ top.docForName, "`" ++ top.scopeName ++ "`." ++ top.docUnparse, top.grammarName)];
  top.docUnparse = "<undefined docUnparse for "++genericShow(top)++">";
}

concrete production documentedClassBodyItem
top::ClassBodyItem ::= comment::DocComment_t item::ClassBodyItem
{
  local parsed::DclComment = parseComment(top.config, comment);

  parsed.paramNames = nothing();
  parsed.isForWhat = "classBodyItem";
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
                        dclCommentItem(top.scopeName ++ "." ++ forward.docForName, forward.docUnparse, forward.grammarName, parsed)
                      end];
  top.docErrors <-
    if isDoubleComment
    then [wrnFromOrigin(parsed, "Doc comment not immediately preceding ClassBodyItem, so association is ambiguous. Treating as standalone comment. Mark with @@{- instead of @{- to silence this warning.")]
    else [];

  forwards to item;
}

aspect production constraintClassBodyItem
top::ClassBodyItem ::= id::Name '::' cl::ConstraintList '=>' ty::TypeExpr ';'
{
  top.docForName = id.name;
  top.docUnparse = "`" ++ id.unparse ++ " :: " ++ cl.unparse ++ " => " ++ ty.unparse ++ "`";
  top.docs := [undocumentedItem(top.scopeName ++ "." ++ top.docForName, "`" ++ top.scopeName ++ "`." ++ top.docUnparse, top.grammarName)];
}

aspect production defaultConstraintClassBodyItem
top::ClassBodyItem ::= id::Name '::' cl::ConstraintList '=>' ty::TypeExpr '=' e::Expr ';'
{
  top.docForName = id.name;
  top.docUnparse = "`" ++ id.unparse ++ " :: " ++ cl.unparse ++ " => " ++ ty.unparse ++ "` (has default)";
  top.docs := [undocumentedItem(top.scopeName ++ "." ++ top.docForName, "`" ++ top.scopeName ++ "`." ++ top.docUnparse, top.grammarName)];
}






aspect production nilInstanceBody
top::InstanceBody ::= 
{
  top.docs := [];
  top.upDocConfig := [];
  top.docDcls := [];
}

aspect production consInstanceBody
top::InstanceBody ::= h::InstanceBodyItem t::InstanceBody
{
  top.docs := h.docs ++ t.docs;
  top.upDocConfig := h.upDocConfig ++ t.upDocConfig;
  top.docDcls := h.docDcls ++ t.docDcls;
  h.downDocConfig = top.downDocConfig;
  t.downDocConfig = top.downDocConfig;
}

aspect default production
top::InstanceBodyItem ::=
{
  top.docForName = "<undefined docForName for "++genericShow(top)++">";
  top.upDocConfig := [];
  top.docDcls := [];
  top.docs := [undocumentedItem(top.scopeName ++ "." ++ top.docForName, "`" ++ top.scopeName ++ "`." ++ top.docUnparse, top.grammarName)];
  top.docUnparse = "<undefined docUnparse for "++genericShow(top)++">";
}

concrete production documentedInstanceBodyItem
top::InstanceBodyItem ::= comment::DocComment_t item::InstanceBodyItem
{
  local parsed::DclComment = parseComment(top.config, comment);

  parsed.paramNames = nothing();
  parsed.isForWhat = "instanceBodyItem";
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
                        dclCommentItem(top.scopeName ++ "." ++ forward.docForName, forward.docUnparse, forward.grammarName, parsed)
                      end];
  top.docErrors <-
    if isDoubleComment
    then [wrnFromOrigin(parsed, "Doc comment not immediately preceding InstanceBodyItem, so association is ambiguous. Treating as standalone comment. Mark with @@{- instead of @{- to silence this warning.")]
    else [];

  forwards to item;
}

aspect production instanceBodyItem
top::InstanceBodyItem ::= id::QName '=' e::Expr ';'
{
  top.docForName = id.name;
  top.docUnparse = "`" ++ id.unparse ++ "`";
  top.docs := [undocumentedItem(top.scopeName ++ "." ++ top.docForName, "`" ++ top.scopeName ++ "`." ++ top.docUnparse, top.grammarName)];
}
