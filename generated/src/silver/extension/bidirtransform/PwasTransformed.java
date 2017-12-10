
package silver.extension.bidirtransform;

public final class PwasTransformed extends common.FunctionNode {

	public static final int i_node = 0;
	public static final int i_redex = 1;


	public static final Class<?> childTypes[] = { silver.extension.bidirtransform.NOrigin.class,core.NMaybe.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_wasTransformed;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_node] = new common.Lazy[silver.extension.bidirtransform.NOrigin.num_inh_attrs];
	childInheritedAttributes[i_redex] = new common.Lazy[core.NMaybe.num_inh_attrs];

	}

	public PwasTransformed(final Object c_node, final Object c_redex) {
		this.child_node = c_node;
		this.child_redex = c_redex;

	}

	private Object child_node;
	public final silver.extension.bidirtransform.NOrigin getChild_node() {
		return (silver.extension.bidirtransform.NOrigin) (child_node = common.Util.demand(child_node));
	}

	private Object child_redex;
	public final core.NMaybe getChild_redex() {
		return (core.NMaybe) (child_redex = common.Util.demand(child_redex));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_node: return getChild_node();
			case i_redex: return getChild_redex();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_node: return child_node;
			case i_redex: return child_redex;

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
		return "silver:extension:bidirtransform:wasTransformed";
	}

	public static Boolean invoke(final Object c_node, final Object c_redex) {
		try {
		final common.DecoratedNode context = new PwasTransformed(c_node, c_redex).decorate();
		//if redex.isJust then true else node.wasTransformed
		return (Boolean)((((Boolean)context.childDecorated(silver.extension.bidirtransform.PwasTransformed.i_redex).synthesized(core.Init.core_isJust__ON__core_Maybe)) ? true : ((Boolean)context.childDecorated(silver.extension.bidirtransform.PwasTransformed.i_node).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_wasTransformed__ON__silver_extension_bidirtransform_Origin))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:wasTransformed", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PwasTransformed.invoke(children[0], children[1]);
		}
	};
}