grammar silver:compiler:extension:deprecation;

terminal Build_kwd 'build' lexer classes {MODSTMT};

concrete production buildsStmt
top::ModuleStmt ::= 'build' m::QName 'with' c::QName ';'
{
  top.errors <- [wrnFromOrigin(top, "Conditional export using old-style 'build ... with' rather than 'exports ... with'")];

  forwards to exportsWithStmt('exports', m, $3, c, $5);
}

