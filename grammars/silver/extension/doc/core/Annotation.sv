grammar silver:extension:doc:core;

aspect production annotationDcl
top::AGDcl ::= 'annotation' a::QName tl::BracketedOptTypeList '::' te::Type ';'
{
  top.docs := [ bodilessDclCommentItem("annotation", a.name ++ tl.pp, te.pp, 
                                       a.location.filename) ];
}

concrete production docAnnotationDcl
top::AGDcl ::= comment::DclComment 'annotation' a::QName tl::BracketedOptTypeList '::' te::Type ';'
{
  top.docs := [ dclCommentItem("annotation ", a.name ++ tl.pp, te.pp, 
                               a.location.filename, comment) ];

  forwards to annotationDcl('annotation', a, tl, '::', te, ';', location=top.location);
}

concrete production noDocAnnotationDcl
top::AGDcl ::= noDoc::NoDclComment_t 'annotation' a::QName tl::BracketedOptTypeList '::' te::Type ';'
{
  top.docs := [];

  forwards to annotationDcl('annotation', a, tl, '::', te, ';', location=top.location);
}

