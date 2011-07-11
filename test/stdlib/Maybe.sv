

--fromMaybe
equalityTest ( fromMaybe(true, just(false)), false,
               Boolean, core_tests ) ;
equalityTest ( fromMaybe(true, nothing()), true,
               Boolean, core_tests ) ;

--orElse
equalityTest ( orElse(just(false), just(true)).fromJust, false,
               Boolean, core_tests ) ;
equalityTest ( orElse(nothing(), just(true)).fromJust, true,
               Boolean, core_tests ) ;
equalityTest ( orElse(just(false), nothing()).fromJust, false,
               Boolean, core_tests ) ;
equalityTest ( orElse(nothing(), nothing()).isJust, false,
               Boolean, core_tests ) ;

