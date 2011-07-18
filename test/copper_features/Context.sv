
nonterminal Context;

wrongCode "Forwarding is not permitted in this context" {
 concrete production context
 t::Context ::=
 {
 } action {
   forwards to context();
 }
}

wrongCode "Return is not valid in this context." {
 concrete production context
 t::Context ::=
 {
 } action {
   return context();
 }
}

wrongCode "'pluck' allowed only in disambiguation-group parser actions" {
 concrete production context
 t::Context ::=
 {
 } action {
   pluck context();
 }
}

wrongCode "Production attributes are not valid in this context." {
 concrete production context
 t::Context ::=
 {
 } action {
   production attribute fo::String;
 }
}

wrongCode "'print' statement allowed only in parser action blocks." {
 concrete production context
 t::Context ::=
 {
   print "Copper command";
 }
}

