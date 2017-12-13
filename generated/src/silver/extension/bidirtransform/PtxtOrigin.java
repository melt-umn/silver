
package silver.extension.bidirtransform;

// o::Origin ::= s::String 
public final class PtxtOrigin extends silver.extension.bidirtransform.NOrigin {

	public static final int i_s = 0;


	public static final Class<?> childTypes[] = {common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_txtOrigin;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.bidirtransform.NOrigin.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.bidirtransform.NOrigin.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PtxtOrigin(final Object c_s) {
		super();
		this.child_s = c_s;

	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
	}

	@Override
	public common.Lazy getSynthesized(final int index) {
		return synthesizedAttributes[index];
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
	public boolean hasForward() {
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:extension:bidirtransform:txtOrigin erroneously claimed to forward");
	}

	@Override
	public common.Lazy getForwardInheritedAttributes(final int index) {
		return forwardInheritedAttributes[index];
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
		return "silver:extension:bidirtransform:txtOrigin";
	}

	static void initProductionAttributeDefinitions() {
		// o.wasTransformed = false
		silver.extension.bidirtransform.PtxtOrigin.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_wasTransformed__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// o.concreteOrigin = o
		silver.extension.bidirtransform.PtxtOrigin.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_concreteOrigin__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.undecorate(); } };
		// o.isBottomOrigin = true
		silver.extension.bidirtransform.PtxtOrigin.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_isBottomOrigin__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };

	}

	public static final common.NodeFactory<PtxtOrigin> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtxtOrigin> {

		@Override
		public PtxtOrigin invoke(final Object[] children, final Object[] annotations) {
			return new PtxtOrigin(children[0]);
		}
	};

}
