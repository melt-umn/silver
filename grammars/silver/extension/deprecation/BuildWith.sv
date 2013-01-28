grammar silver:extension:deprecation;

terminal Build_kwd		'build';

concrete production buildsStmt
top::ModuleStmt ::= 'build' m::QName 'with' c::QName ';'
{
  top.errors <- [wrn(forward.location, "Conditional export using old-style 'build ... with' rather than 'exports ... with'")];

  forwards to exportsWithStmt(terminal(Exports_kwd, "exports", $1), m, $3, c, $5);
}

