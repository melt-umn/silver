grammar silver:extension:doc:core;

aspect production annotationDcl
top::AGDcl ::= 'annotation' a::QName tl::BracketedOptTypeList '::' te::Type ';'
{
  top.docs := [commentDocItem(bodilessCommentItem("annotation", a.name ++ tl.pp, te.pp, a.location.filename))];
}

concrete production docAnnotationDcl
top::AGDcl ::= comment::DocComment 'annotation' a::QName tl::BracketedOptTypeList '::' te::Type ';'
{
  top.docs := [commentDocItem(commentItem("annotation ", a.name ++ tl.pp, te.pp, a.location.filename, comment))];

  forwards to annotationDcl('annotation', a, tl, '::', te, ';', location=top.location);
}

concrete production noDocAnnotationDcl
top::AGDcl ::= noDoc::NoDocComment_t 'annotation' a::QName tl::BracketedOptTypeList '::' te::Type ';'
{
  top.docs := [];

  forwards to annotationDcl('annotation', a, tl, '::', te, ';', location=top.location);
}

