grammar copper_features:token_pushing;

imports silver:testing ;
imports lib:extcore ;
imports copper_features;

nonterminal PushTokenRoot;
nonterminal Contents;

terminal A 'A';
terminal B 'B' 
action{ 
   pushToken(C, "C") if (1==1);
};
terminal C 'C';

terminal X 'X';
terminal Y 'Y' 
action{ 
   pushToken(Z, "Z");
};
terminal Z 'Z';

ignore terminal Whitespace /[\ \t\r\n]+/;


concrete production pushTokenRoot
top::PushTokenRoot ::= bottom::Contents
{}

concrete productions top::Contents 
| 'A' 'B' 'C' {}
| 'X' 'Y' 'Z' {}


parser parse :: PushTokenRoot {
  copper_features:token_pushing;
}


equalityTest ( parse("A B", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( parse("X Y", "").parseSuccess, true, Boolean, copper_tests );
