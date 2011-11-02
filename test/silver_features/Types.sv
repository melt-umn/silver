

terminal ATerminalType 'doesnotmatter';

wrongCode "ATerminalType has 0 type variables" {
 global t :: ATerminalType<String> = error("");
}

nonterminal NTZero;

wrongCode "NTZero has 0 type variables, but there are 1 supplied here" {
 global t :: NTZero<String> = error("");
}

nonterminal NTOne<a>;

wrongCode "NTOne has 1 type variables, but there are 0 supplied here" {
 global t :: NTOne = error("");
}
wrongCode "NTOne has 1 type variables, but there are 2 supplied here" {
 global t :: NTOne<String String> = error("");
}

wrongCode "NTZero' is already bound" {
 nonterminal NTZero;
}


--nonterminal IO; -- parse error



