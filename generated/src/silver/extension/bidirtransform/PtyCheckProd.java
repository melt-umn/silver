
package silver.extension.bidirtransform;

public final class PtyCheckProd extends common.FunctionNode {

	public static final int i_loc = 0;
	public static final int i_args = 1;
	public static final int i_nsElems = 2;


	public static final Class<?> childTypes[] = { core.NLocation.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_tyCheckProd;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_loc] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PtyCheckProd(final Object c_loc, final Object c_args, final Object c_nsElems) {
		this.child_loc = c_loc;
		this.child_args = c_args;
		this.child_nsElems = c_nsElems;

	}

	private Object child_loc;
	public final core.NLocation getChild_loc() {
		return (core.NLocation) (child_loc = common.Util.demand(child_loc));
	}

	private Object child_args;
	public final common.ConsCell getChild_args() {
		return (common.ConsCell) (child_args = common.Util.demand(child_args));
	}

	private Object child_nsElems;
	public final common.ConsCell getChild_nsElems() {
		return (common.ConsCell) (child_nsElems = common.Util.demand(child_nsElems));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_loc: return getChild_loc();
			case i_args: return getChild_args();
			case i_nsElems: return getChild_nsElems();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_loc: return child_loc;
			case i_args: return child_args;
			case i_nsElems: return child_nsElems;

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
		return "silver:extension:bidirtransform:tyCheckProd";
	}

	public static common.ConsCell invoke(final Object c_loc, final Object c_args, final Object c_nsElems) {
		try {
		final common.DecoratedNode context = new PtyCheckProd(c_loc, c_args, c_nsElems).decorate();
		//[]
		return (common.ConsCell)(((common.ConsCell)core.Pnil.invoke()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:tyCheckProd", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtyCheckProd.invoke(children[0], children[1], children[2]);
		}
	};
}