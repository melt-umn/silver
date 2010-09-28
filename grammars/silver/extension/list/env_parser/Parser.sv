grammar silver:extension:list:env_parser;

import silver:extension:list hiding LSqr_t, RSqr_t;
import silver:definition:env;
import silver:definition:env:parser;


concrete production aTypeRepList
top::aTypeRep ::= l::lb tr::aTypeRep r::rb {
  top.typerep = listTypeExp(tr.typerep);
}

