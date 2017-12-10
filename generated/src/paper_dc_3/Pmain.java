
package paper_dc_3;

public final class Pmain extends common.FunctionNode {

	public static final int i_largs = 0;
	public static final int i_ioin = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__paper_dc_3_main;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Pmain(final Object c_largs, final Object c_ioin) {
		this.child_largs = c_largs;
		this.child_ioin = c_ioin;

	}

	private Object child_largs;
	public final common.ConsCell getChild_largs() {
		return (common.ConsCell) (child_largs = common.Util.demand(child_largs));
	}

	private Object child_ioin;
	public final Object getChild_ioin() {
		return (Object) (child_ioin = common.Util.demand(child_ioin));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_largs: return getChild_largs();
			case i_ioin: return getChild_ioin();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_largs: return child_largs;
			case i_ioin: return child_ioin;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return "paper_dc_3:main";
	}

	public static core.NIOVal invoke(final Object c_largs, final Object c_ioin) {
		try {
		final common.DecoratedNode context = new Pmain(c_largs, c_ioin).decorate();
		//ioval(if result.parseSuccess then print_success else print_failure, 0)
		return (core.NIOVal)(((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((Boolean)context.localDecorated(paper_dc_3.Init.result__ON__paper_dc_3_main).synthesized(core.Init.core_parseSuccess__ON__core_ParseResult)) ? ((Object)context.localAsIs(paper_dc_3.Init.print_success__ON__paper_dc_3_main)) : ((Object)context.localAsIs(paper_dc_3.Init.print_failure__ON__paper_dc_3_main))); } }, Integer.valueOf((int)0))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function paper_dc_3:main", t);
		}
	}

	public static final common.NodeFactory<core.NIOVal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NIOVal> {
		@Override
		public core.NIOVal invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pmain.invoke(children[0], children[1]);
		}
	};
}