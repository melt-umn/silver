
package edu.umn.cs.melt.ableC.drivers.compile;

public final class PpartitionArg extends common.FunctionNode {

	public static final int i_arg = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_drivers_compile_partitionArg;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PpartitionArg(final Object c_arg) {
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
		return "edu:umn:cs:melt:ableC:drivers:compile:partitionArg";
	}

	public static Boolean invoke(final Object c_arg) {
		try {
		final common.DecoratedNode context = new PpartitionArg(c_arg).decorate();
		//arg == "--show-ast" || arg == "--show-host-ast" || arg == "--show-lifted-ast" || arg == "--show-pp" || arg == "--show-host-pp" || arg == "--show-lifted-pp" || arg == "--show-cpp" || arg == "--force-trans" || startsWith("--xc-", arg,)
		return (Boolean)(((((((((((common.StringCatter)context.childAsIs(edu.umn.cs.melt.ableC.drivers.compile.PpartitionArg.i_arg)).equals((new common.StringCatter("--show-ast"))) || ((common.StringCatter)context.childAsIs(edu.umn.cs.melt.ableC.drivers.compile.PpartitionArg.i_arg)).equals((new common.StringCatter("--show-host-ast")))) || ((common.StringCatter)context.childAsIs(edu.umn.cs.melt.ableC.drivers.compile.PpartitionArg.i_arg)).equals((new common.StringCatter("--show-lifted-ast")))) || ((common.StringCatter)context.childAsIs(edu.umn.cs.melt.ableC.drivers.compile.PpartitionArg.i_arg)).equals((new common.StringCatter("--show-pp")))) || ((common.StringCatter)context.childAsIs(edu.umn.cs.melt.ableC.drivers.compile.PpartitionArg.i_arg)).equals((new common.StringCatter("--show-host-pp")))) || ((common.StringCatter)context.childAsIs(edu.umn.cs.melt.ableC.drivers.compile.PpartitionArg.i_arg)).equals((new common.StringCatter("--show-lifted-pp")))) || ((common.StringCatter)context.childAsIs(edu.umn.cs.melt.ableC.drivers.compile.PpartitionArg.i_arg)).equals((new common.StringCatter("--show-cpp")))) || ((common.StringCatter)context.childAsIs(edu.umn.cs.melt.ableC.drivers.compile.PpartitionArg.i_arg)).equals((new common.StringCatter("--force-trans")))) || ((Boolean)core.PstartsWith.invoke((new common.StringCatter("--xc-")), context.childAsIsLazy(edu.umn.cs.melt.ableC.drivers.compile.PpartitionArg.i_arg)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:drivers:compile:partitionArg", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PpartitionArg.invoke(children[0]);
		}
	};
}