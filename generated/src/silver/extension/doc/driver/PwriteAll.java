
package silver.extension.doc.driver;

public final class PwriteAll extends common.FunctionNode {

	public static final int i_i = 0;
	public static final int i_a = 1;
	public static final int i_l = 2;
	public static final int i_outputLoc = 3;


	public static final Class<?> childTypes[] = { Object.class,common.DecoratedNode.class,common.DecoratedNode.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_doc_driver_writeAll;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PwriteAll(final Object c_i, final Object c_a, final Object c_l, final Object c_outputLoc) {
		this.child_i = c_i;
		this.child_a = c_a;
		this.child_l = c_l;
		this.child_outputLoc = c_outputLoc;

	}

	private Object child_i;
	public final Object getChild_i() {
		return (Object) (child_i = common.Util.demand(child_i));
	}

	private Object child_a;
	public final common.DecoratedNode getChild_a() {
		return (common.DecoratedNode) (child_a = common.Util.demand(child_a));
	}

	private Object child_l;
	public final common.ConsCell getChild_l() {
		return (common.ConsCell) (child_l = common.Util.demand(child_l));
	}

	private Object child_outputLoc;
	public final common.StringCatter getChild_outputLoc() {
		return (common.StringCatter) (child_outputLoc = common.Util.demand(child_outputLoc));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_i: return getChild_i();
			case i_a: return getChild_a();
			case i_l: return getChild_l();
			case i_outputLoc: return getChild_outputLoc();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_i: return child_i;
			case i_a: return child_a;
			case i_l: return child_l;
			case i_outputLoc: return child_outputLoc;

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
		return "silver:extension:doc:driver:writeAll";
	}

	public static Object invoke(final Object c_i, final Object c_a, final Object c_l, final Object c_outputLoc) {
		try {
		final common.DecoratedNode context = new PwriteAll(c_i, c_a, c_l, c_outputLoc).decorate();
		//if null(l) then i else recurse
		return (Object)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.extension.doc.driver.PwriteAll.i_l))) ? ((Object)context.childAsIs(silver.extension.doc.driver.PwriteAll.i_i)) : ((Object)context.localAsIs(silver.extension.doc.driver.Init.recurse__ON__silver_extension_doc_driver_writeAll))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:doc:driver:writeAll", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PwriteAll.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}