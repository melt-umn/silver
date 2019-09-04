grammar silver:extension:doc:core;

aspect production annotationDcl
top::AGDcl ::= 'annotation' a::QName tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.docs := [ bodilessDclCommentItem("annotation", a.name ++ tl.unparse, te.unparse, 
                                       a.location.filename) ];
}

concrete production docAnnotationDcl
top::AGDcl ::= comment::DclComment 'annotation' a::QName tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.docs := [ dclCommentItem("annotation ", a.name ++ tl.unparse, te.unparse, 
                               a.location.filename, comment) ];

  forwards to annotationDcl('annotation', a, tl, '::', te, ';', location=top.location);
}

concrete production noDocAnnotationDcl
top::AGDcl ::= noDoc::NoDclComment_t 'annotation' a::QName tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.docs := [];

  forwards to annotationDcl('annotation', a, tl, '::', te, ';', location=top.location);
}

