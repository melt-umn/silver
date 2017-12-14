
package silver.extension.bidirtransform;

// o::Origin ::= l::Location 
public final class PlocOrigin extends silver.extension.bidirtransform.NOrigin {

	public static final int i_l = 0;


	public static final Class<?> childTypes[] = {core.NLocation.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_locOrigin;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.bidirtransform.NOrigin.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.bidirtransform.NOrigin.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PlocOrigin(final Object c_l) {
		super();
		this.child_l = c_l;

	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:bidirtransform:locOrigin erroneously claimed to forward");
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
		return "silver:extension:bidirtransform:locOrigin";
	}

	static void initProductionAttributeDefinitions() {
		// o.wasTransformed = false
		silver.extension.bidirtransform.PlocOrigin.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_wasTransformed__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// o.concreteOrigin = o
		silver.extension.bidirtransform.PlocOrigin.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_concreteOrigin__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.undecorate(); } };
		// o.isBottomOrigin = true
		silver.extension.bidirtransform.PlocOrigin.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_isBottomOrigin__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };

	}

	public static final common.NodeFactory<PlocOrigin> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PlocOrigin> {

		@Override
		public PlocOrigin invoke(final Object[] children, final Object[] annotations) {
			return new PlocOrigin(children[0]);
		}
	};

}
