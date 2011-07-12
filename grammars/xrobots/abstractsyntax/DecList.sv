grammar xrobots:abstractsyntax;
import xrobots:terminals;

nonterminal DecList
                with pp, 
                     bindingHTrans,
                     decHTrans,
                     frameIn,
                     frameSyn,
                     errors;


abstract production declist_empty
dl::DecList  ::= 
{
  dl.pp            = "Empty DecList\n";
  dl.decHTrans     = "[]";
  dl.bindingHTrans = "[]";
  dl.errors        = [];
  dl.frameSyn      = emptyFrame();
}

abstract production declist_cons
dl::DecList  ::= d::Dec  dlIn::DecList
{
 dl.pp        =  d.pp ++ "\n" ++ dlIn.pp;
 dl.decHTrans =  "[" ++ d.decHTrans ++ "] ++ (" ++ dlIn.decHTrans ++")";
 dl.bindingHTrans   
              = "[" ++ d.bindingHTrans 
                 ++ "] ++ (" ++ dlIn.bindingHTrans ++ ")";
 dl.errors    = d.errors ++ dlIn.errors;
 dl.frameSyn  = addBinding(dlIn.frameSyn, d.name, d.type);
 
 d.frameIn    = dl.frameIn;
 dlIn.frameIn = dl.frameIn;
}
