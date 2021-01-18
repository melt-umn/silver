grammar silver:compiler:extension:convenienceaspects;
import silver:compiler:modification:collection;
import silver:compiler:extension:constructparser;


concrete productions top::AGDcl
| 'aspect' attr::QNameAttrOccur 'on' ty::TypeExpr 'using' '=' 'of' Opt_Vbar_t ml::MRuleList 'end' ';'
{
  forwards to convenienceAspects(attr,ty,ml, attributeDef(_,'.',_,'=',_,';',location=_), location=top.location);

}
| 'aspect' attr::QNameAttrOccur 'on' ty::TypeExpr 'using' ':=' 'of' Opt_Vbar_t ml::MRuleList 'end' ';'
{
  forwards to convenienceAspects(attr,ty,ml, attrContainsBase(_,'.',_,':=',_,';',location=_), location=top.location);
}
| 'aspect' attr::QNameAttrOccur 'on' ty::TypeExpr 'using' '<-' 'of' Opt_Vbar_t ml::MRuleList 'end' ';'
{
  forwards to convenienceAspects(attr,ty,ml, attrContainsAppend(_,'.',_,'<-',_,';', location=_), location=top.location);
}
| 'aspect' attr::QNameAttrOccur 'on' ty::TypeExpr 'of' Opt_Vbar_t ml::MRuleList 'end' ';'
{
  forwards to convenienceAspects(attr,ty,ml, attributeDef(_,'.',_,'=',_,';',location=_), location=top.location);
}
