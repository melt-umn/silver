
package edu.umn.cs.melt.ableC.abstractsyntax.host;

public final class PcompatibleTypeList extends common.FunctionNode {

	public static final int i_a = 0;
	public static final int i_b = 1;
	public static final int i_allowSubtypes = 2;
	public static final int i_dropOuterQual = 3;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class,Boolean.class,Boolean.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_compatibleTypeList;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PcompatibleTypeList(final Object c_a, final Object c_b, final Object c_allowSubtypes, final Object c_dropOuterQual) {
		this.child_a = c_a;
		this.child_b = c_b;
		this.child_allowSubtypes = c_allowSubtypes;
		this.child_dropOuterQual = c_dropOuterQual;

	}

	private Object child_a;
	public final common.ConsCell getChild_a() {
		return (common.ConsCell) (child_a = common.Util.demand(child_a));
	}

	private Object child_b;
	public final common.ConsCell getChild_b() {
		return (common.ConsCell) (child_b = common.Util.demand(child_b));
	}

	private Object child_allowSubtypes;
	public final Boolean getChild_allowSubtypes() {
		return (Boolean) (child_allowSubtypes = common.Util.demand(child_allowSubtypes));
	}

	private Object child_dropOuterQual;
	public final Boolean getChild_dropOuterQual() {
		return (Boolean) (child_dropOuterQual = common.Util.demand(child_dropOuterQual));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_a: return getChild_a();
			case i_b: return getChild_b();
			case i_allowSubtypes: return getChild_allowSubtypes();
			case i_dropOuterQual: return getChild_dropOuterQual();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_a: return child_a;
			case i_b: return child_b;
			case i_allowSubtypes: return child_allowSubtypes;
			case i_dropOuterQual: return child_dropOuterQual;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:compatibleTypeList";
	}

	public static Boolean invoke(final Object c_a, final Object c_b, final Object c_allowSubtypes, final Object c_dropOuterQual) {
		try {
		final common.DecoratedNode context = new PcompatibleTypeList(c_a, c_b, c_allowSubtypes, c_dropOuterQual).decorate();
		//if null(a,) && null(b,) then true else if null(a,) || null(b,) then false else compatibleTypes(head(a,), head(b,), allowSubtypes, dropOuterQual,) && compatibleTypeList(tail(a,), tail(b,), allowSubtypes, dropOuterQual,)
		return (Boolean)(((((Boolean)core.Pnull.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PcompatibleTypeList.i_a))) && ((Boolean)core.Pnull.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PcompatibleTypeList.i_b)))) ? true : ((((Boolean)core.Pnull.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PcompatibleTypeList.i_a))) || ((Boolean)core.Pnull.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PcompatibleTypeList.i_b)))) ? false : (((Boolean)edu.umn.cs.melt.ableC.abstractsyntax.host.PcompatibleTypes.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)core.Phead.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PcompatibleTypeList.i_a))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)core.Phead.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PcompatibleTypeList.i_b))); } }, context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PcompatibleTypeList.i_allowSubtypes), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PcompatibleTypeList.i_dropOuterQual))) && ((Boolean)edu.umn.cs.melt.ableC.abstractsyntax.host.PcompatibleTypeList.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PcompatibleTypeList.i_a))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PcompatibleTypeList.i_b))); } }, context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PcompatibleTypeList.i_allowSubtypes), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PcompatibleTypeList.i_dropOuterQual)))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:host:compatibleTypeList", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcompatibleTypeList.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}