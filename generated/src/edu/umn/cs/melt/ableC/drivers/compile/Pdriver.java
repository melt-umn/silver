
package edu.umn.cs.melt.ableC.drivers.compile;

public final class Pdriver extends common.FunctionNode {

	public static final int i_args = 0;
	public static final int i_ioIn = 1;
	public static final int i_theParser = 2;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,Object.class,common.NodeFactory.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_drivers_compile_driver;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Pdriver(final Object c_args, final Object c_ioIn, final Object c_theParser) {
		this.child_args = c_args;
		this.child_ioIn = c_ioIn;
		this.child_theParser = c_theParser;

	}

	private Object child_args;
	public final common.ConsCell getChild_args() {
		return (common.ConsCell) (child_args = common.Util.demand(child_args));
	}

	private Object child_ioIn;
	public final Object getChild_ioIn() {
		return (Object) (child_ioIn = common.Util.demand(child_ioIn));
	}

	private Object child_theParser;
	public final common.NodeFactory<core.NParseResult> getChild_theParser() {
		return (common.NodeFactory<core.NParseResult>) (child_theParser = common.Util.demand(child_theParser));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_args: return getChild_args();
			case i_ioIn: return getChild_ioIn();
			case i_theParser: return getChild_theParser();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_args: return child_args;
			case i_ioIn: return child_ioIn;
			case i_theParser: return child_theParser;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "edu:umn:cs:melt:ableC:drivers:compile:driver";
	}

	public static core.NIOVal invoke(final Object c_args, final Object c_ioIn, final Object c_theParser) {
		try {
		final common.DecoratedNode context = new Pdriver(c_args, c_ioIn, c_theParser).decorate();
		//evalIO(result, ioIn,)
		return (core.NIOVal)(((core.NIOVal)core.monad.PevalIO.invoke(common.Thunk.transformUndecorate(context.localDecoratedLazy(edu.umn.cs.melt.ableC.drivers.compile.Init.result__ON__edu_umn_cs_melt_ableC_drivers_compile_driver)), context.childAsIsLazy(edu.umn.cs.melt.ableC.drivers.compile.Pdriver.i_ioIn))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:drivers:compile:driver", t);
		}
	}

	public static final common.NodeFactory<core.NIOVal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NIOVal> {
		@Override
		public core.NIOVal invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pdriver.invoke(children[0], children[1], children[2]);
		}
	};
}