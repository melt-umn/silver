
package silver.extension.bidirtransform;

public final class PgetConcreteOrigin extends common.FunctionNode {

	public static final int i_node = 0;
	public static final int i_root = 1;


	public static final Class<?> childTypes[] = { silver.extension.bidirtransform.NOrigin.class,silver.extension.bidirtransform.NOrigin.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_getConcreteOrigin;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_node] = new common.Lazy[silver.extension.bidirtransform.NOrigin.num_inh_attrs];
	childInheritedAttributes[i_root] = new common.Lazy[silver.extension.bidirtransform.NOrigin.num_inh_attrs];

	}

	public PgetConcreteOrigin(final Object c_node, final Object c_root) {
		this.child_node = c_node;
		this.child_root = c_root;

	}

	private Object child_node;
	public final silver.extension.bidirtransform.NOrigin getChild_node() {
		return (silver.extension.bidirtransform.NOrigin) (child_node = common.Util.demand(child_node));
	}

	private Object child_root;
	public final silver.extension.bidirtransform.NOrigin getChild_root() {
		return (silver.extension.bidirtransform.NOrigin) (child_root = common.Util.demand(child_root));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_node: return getChild_node();
			case i_root: return getChild_root();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_node: return child_node;
			case i_root: return child_root;

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
		return "silver:extension:bidirtransform:getConcreteOrigin";
	}

	public static silver.extension.bidirtransform.NOrigin invoke(final Object c_node, final Object c_root) {
		try {
		final common.DecoratedNode context = new PgetConcreteOrigin(c_node, c_root).decorate();
		//if node.isBottomOrigin then root else node.concreteOrigin
		return (silver.extension.bidirtransform.NOrigin)((((Boolean)context.childDecorated(silver.extension.bidirtransform.PgetConcreteOrigin.i_node).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_isBottomOrigin__ON__silver_extension_bidirtransform_Origin)) ? context.childDecorated(silver.extension.bidirtransform.PgetConcreteOrigin.i_root).undecorate() : ((silver.extension.bidirtransform.NOrigin)context.childDecorated(silver.extension.bidirtransform.PgetConcreteOrigin.i_node).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_concreteOrigin__ON__silver_extension_bidirtransform_Origin))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:getConcreteOrigin", t);
		}
	}

	public static final common.NodeFactory<silver.extension.bidirtransform.NOrigin> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.extension.bidirtransform.NOrigin> {
		@Override
		public silver.extension.bidirtransform.NOrigin invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgetConcreteOrigin.invoke(children[0], children[1]);
		}
	};
}