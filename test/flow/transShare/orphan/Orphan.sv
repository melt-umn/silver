grammar flow:transShare:orphan;

imports flow;
imports flow:transShare;

-- A grammar that declares neither the production nor an occurrence on the chain of a translation attribute
-- of a translation attribute cannot share it: another such grammar could share it as well.

warnCode "Orphaned sharing of translation attribute flow:ntsA of translation attribute flow:ntsB of child x in production flow:ntsOrphanHost" {
aspect production ntsOrphanHost
top::NtsP ::= x::NtsX
{
  local orphanSite::NtsW = ntsW(@x.ntsB.ntsA);
}
}

warnCode "Orphaned sharing of translation attribute flow:ntsA of translation attribute flow:transShare:ntsExtB of child x in production flow:ntsOrphanExtHost" {
aspect production ntsOrphanExtHost
top::NtsP ::= x::NtsX
{
  local orphanSite::NtsW = ntsW(@x.ntsExtB.ntsA);
}
}
