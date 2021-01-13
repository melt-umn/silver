grammar silver:compiler:extension:doc:core;

aspect production annotationDcl
top::AGDcl ::= 'annotation' a::QName tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.docs := [ bodilessDclCommentItem("annotation", top.grammarName, a.name ++ tl.unparse, te.unparse, 
                                       a.location) ];
}

concrete production docAnnotationDcl
top::AGDcl ::= comment::DocComment_t 'annotation' a::QName tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.docs := [ dclCommentItem("annotation", top.grammarName, a.name ++ tl.unparse, te.unparse, 
                               a.location, comment) ];

  forwards to annotationDcl('annotation', a, tl, '::', te, ';', location=top.location);
}

