grammar flow:ext;

-- saExt defines the host attribute saS from saI, an equation that is suspect here, and supplies its
-- child's saK from saS (see SuspectAdmission.sv in flow).
production saExt
top::SAE ::= c::SAE
{
  top.saS = toString(top.saI);
  c.saK = top.saS;
  forwards to saLit();
}
