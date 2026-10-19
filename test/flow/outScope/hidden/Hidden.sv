grammar flow:outScope:hidden;

imports flow;

-- Productions and dispatch signatures that are not in scope at flow:outScope: flow:outScope:mid
-- imports this grammar without exporting it.  The walk enters them by their production graphs (see
-- findDecSitePaths).

production osHidden
top::OB ::= w::OB
{
  w.obK = top.obM;
  forwards to obLeaf();
}
production osHiddenNone
top::OB ::= w::OB
{
  forwards to obLeaf();
}
production osHid
top::OB ::= w::OB  flag::Boolean
{
  forwards to if flag then obH(@w) else obJ(@w);
}

-- A dispatch signature like flow's GOpN.  If its implementation left gNeedsOther to its forward,
-- the implicit copy would bring gOther into what implementations assume, and an override supplying
-- gEnv from gOther would pass.
dispatch OsN = GExpr ::= a::GExpr;
production osImplN implements OsN
top::GExpr ::= a::GExpr
{
  a.gEnv = top.gNeedsOther;
  a.gCtx = top.gCtx;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  forwards to gLit();
}

-- An implementation that passes its child on with '@' to an application of flow's GOpN.
dispatch OsOuter = GExpr ::= a::GExpr;
production osOuterImpl implements OsOuter
top::GExpr ::= qa::GExpr
{
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  local prod::GOpN = gImplN;
  forwards to prod(@qa);
}

-- The same, through an inner dispatch signature, OsInner, that is not in scope at flow:outScope
-- either.
dispatch OsInner = GExpr ::= a::GExpr;
production osInnerImpl implements OsInner
top::GExpr ::= a::GExpr
{
  a.gEnv = top.gNeedsOther;
  a.gCtx = top.gCtx;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  forwards to gLit();
}
dispatch OsOuterN = GExpr ::= a::GExpr;
production osOuterNImpl implements OsOuterN
top::GExpr ::= qa::GExpr
{
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  local prod::OsInner = osInnerImpl;
  forwards to prod(@qa);
}

-- A host implementation with no equation for its child: only the implementations not known here may
-- supply it, as OsBare's graph says (see unknownImplSets).
dispatch OsBare = GExpr ::= a::GExpr;
production osBareImpl implements OsBare
top::GExpr ::= a::GExpr
{
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  forwards to gLit();
}

-- A dispatch signature without host implementations, told from a production by its graph (see
-- dispatchInputs).
dispatch OsNoHost = GExpr ::= a::GExpr;

-- A production that shares a translation attribute of its child: its route is listed from the flow
-- environment, as flow:outScope's environment has no declaration of this production (see
-- sharedTransAttrsOf).
production osTrHidden
top::OB ::= w::OBT
{
  local q::OB = obH(@w.obTr);
  q.obK = top.obK;
  forwards to obLeaf();
}
