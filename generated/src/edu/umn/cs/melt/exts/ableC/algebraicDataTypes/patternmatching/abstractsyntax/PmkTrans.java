
package edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax;

public final class PmkTrans extends common.FunctionNode {

	public static final int i_pts = 0;
	public static final int i_ptypes = 1;
	public static final int i_tag = 2;
	public static final int i_pos = 3;
	public static final int i_locations = 4;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class,common.StringCatter.class,Integer.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_mkTrans;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PmkTrans(final Object c_pts, final Object c_ptypes, final Object c_tag, final Object c_pos, final Object c_locations) {
		this.child_pts = c_pts;
		this.child_ptypes = c_ptypes;
		this.child_tag = c_tag;
		this.child_pos = c_pos;
		this.child_locations = c_locations;

	}

	private Object child_pts;
	public final common.ConsCell getChild_pts() {
		return (common.ConsCell) (child_pts = common.Util.demand(child_pts));
	}

	private Object child_ptypes;
	public final common.ConsCell getChild_ptypes() {
		return (common.ConsCell) (child_ptypes = common.Util.demand(child_ptypes));
	}

	private Object child_tag;
	public final common.StringCatter getChild_tag() {
		return (common.StringCatter) (child_tag = common.Util.demand(child_tag));
	}

	private Object child_pos;
	public final Integer getChild_pos() {
		return (Integer) (child_pos = common.Util.demand(child_pos));
	}

	private Object child_locations;
	public final common.ConsCell getChild_locations() {
		return (common.ConsCell) (child_locations = common.Util.demand(child_locations));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_pts: return getChild_pts();
			case i_ptypes: return getChild_ptypes();
			case i_tag: return getChild_tag();
			case i_pos: return getChild_pos();
			case i_locations: return getChild_locations();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_pts: return child_pts;
			case i_ptypes: return child_ptypes;
			case i_tag: return child_tag;
			case i_pos: return child_pos;
			case i_locations: return child_locations;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
	}

	@Override
	public common.Lazy[] getLocalInheritedAttributes(final int key) {
		return localInheritedAttributes[key];
	}

	@Override
	public common.Lazy[] getChildInheritedAttributes(final int key) {
		return childInheritedAttributes[key];
	}

	@Override
	public common.Lazy getLocal(final int key) {
		return localAttributes[key];
	}

	@Override
	public final int getNumberOfLocalAttrs() {
		return num_local_attrs;
	}

	@Override
	public final String getNameOfLocalAttr(final int index) {
		return occurs_local[index];
	}

	@Override
	public String getName() {
		return "edu:umn:cs:melt:exts:ableC:algebraicDataTypes:patternmatching:abstractsyntax:mkTrans";
	}

	public static edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt invoke(final Object c_pts, final Object c_ptypes, final Object c_tag, final Object c_pos, final Object c_locations) {
		try {
		final common.DecoratedNode context = new PmkTrans(c_pts, c_ptypes, c_tag, c_pos, c_locations).decorate();
		//if null(pts,) then nullStmt(,) else seqStmt(mkTran(head(pts,), head(ptypes,), tag, pos, head(locations,),), mkTrans(tail(pts,), tail(ptypes,), tag, pos + 1, tail(locations,),),)
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PmkTrans.i_pts))) ? ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnullStmt()) : ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)new edu.umn.cs.melt.ableC.abstractsyntax.host.PseqStmt(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PmkTran.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)core.Phead.invoke(context.childAsIsLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PmkTrans.i_pts))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)core.Phead.invoke(context.childAsIsLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PmkTrans.i_ptypes))); } }, context.childAsIsLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PmkTrans.i_tag), context.childAsIsLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PmkTrans.i_pos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)core.Phead.invoke(context.childAsIsLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PmkTrans.i_locations))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PmkTrans.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PmkTrans.i_pts))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PmkTrans.i_ptypes))); } }, context.childAsIsLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PmkTrans.i_tag), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childAsIs(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PmkTrans.i_pos)) + Integer.valueOf((int)1)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PmkTrans.i_locations))); } })); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:exts:ableC:algebraicDataTypes:patternmatching:abstractsyntax:mkTrans", t);
		}
	}

	public static final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt> factory = new Factory();

	public static final class Factory extends common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt> {
		@Override
		public edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmkTrans.invoke(children[0], children[1], children[2], children[3], children[4]);
		}
	};
}