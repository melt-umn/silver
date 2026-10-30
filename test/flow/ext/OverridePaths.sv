grammar flow:ext;

-- The implementations' own equations for their child are assumed as the dispatch signature allows,
-- whether or not the applying production shows the override: it stitches the signature's tile flow
-- graph (see implLevel).
noWarnCode "hidden transitive" {
production obCapExt
top::OB ::= c::OB
{
  c.obK = top.obM;
  local prod::OBC = obcK;
  forwards to prod(@c);
}
}

-- An override in the production's own grammar is recorded at the tree's decoration sites, although
-- the attribute's grammar does not export that grammar (see defLHSRecorded), so the implementation
-- applied by name is capped as for obByNameOwn.
noWarnCode "hidden transitive" {
production obExtByName
top::OB ::= c::OB
{
  c.obK = top.obF;
  forwards to obeOwn(@c);
}
}
