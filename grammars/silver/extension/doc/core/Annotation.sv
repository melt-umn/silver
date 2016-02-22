grammar silver:extension:doc:core;

aspect production annotationDcl
top::AGDcl ::= 'annotation' a::QName tl::BracketedOptTypeList '::' te::Type ';'
{
  top.docs := [toNoCommentMarkdown("annotation", a.name ++ tl.pp, te.pp)];
}

concrete production docAnnotationDcl
top::AGDcl ::= comment::DocComment 'annotation' a::QName tl::BracketedOptTypeList '::' te::Type ';'
{
  top.docs := [toMarkdown("annotation ", a.name ++ tl.pp, te.pp, comment)];

  forwards to annotationDcl('annotation', a, tl, '::', te, ';', location=top.location);
}

