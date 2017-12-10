
package edu.umn.cs.melt.ableC.drivers.compile;

public final class PxcArgDef extends common.FunctionNode {

	public static final int i_arg = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_drivers_compile_xcArgDef;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PxcArgDef(final Object c_arg) {
		this.child_arg = c_arg;

	}

	private Object child_arg;
	public final common.StringCatter getChild_arg() {
		return (common.StringCatter) (child_arg = common.Util.demand(child_arg));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_arg: return getChild_arg();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_arg: return child_arg;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return "edu:umn:cs:melt:ableC:drivers:compile:xcArgDef";
	}

	public static edu.umn.cs.melt.ableC.abstractsyntax.env.NDef invoke(final Object c_arg) {
		try {
		final common.DecoratedNode context = new PxcArgDef(c_arg).decorate();
		//miscDef(arg, emptyMiscItem(,),)
		return (edu.umn.cs.melt.ableC.abstractsyntax.env.NDef)(((edu.umn.cs.melt.ableC.abstractsyntax.env.NDef)new edu.umn.cs.melt.ableC.abstractsyntax.env.PmiscDef(context.childAsIsLazy(edu.umn.cs.melt.ableC.drivers.compile.PxcArgDef.i_arg), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.env.NMiscItem)new edu.umn.cs.melt.ableC.abstractsyntax.env.PemptyMiscItem()); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:drivers:compile:xcArgDef", t);
		}
	}

	public static final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.env.NDef> factory = new Factory();

	public static final class Factory extends common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.env.NDef> {
		@Override
		public edu.umn.cs.melt.ableC.abstractsyntax.env.NDef invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PxcArgDef.invoke(children[0]);
		}
	};
}