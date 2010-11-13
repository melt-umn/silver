grammar tutorials:simple:host ;

exports tutorials:simple:terminals ;
exports tutorials:simple:concretesyntax ;
exports tutorials:simple:abstractsyntax ;
exports tutorials:simple:host:driver ;

import tutorials:simple:concretesyntax only Root_c ;

parser parse :: Root_c { tutorials:simple:concretesyntax;
                         tutorials:simple:terminals; } 
