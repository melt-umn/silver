
package silver.extension.bidirtransform;

// o::Origin ::= 
public final class PbottomOrigin extends silver.extension.bidirtransform.NOrigin {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_bottomOrigin;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.bidirtransform.NOrigin.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.bidirtransform.NOrigin.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PbottomOrigin() {
		super();

	}



	@Override
	public Object getChild(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 0;
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
		throw new common.exceptions.SilverInternalError("Production silver:extension:bidirtransform:bottomOrigin erroneously claimed to forward");
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
		return "silver:extension:bidirtransform:bottomOrigin";
	}

	static void initProductionAttributeDefinitions() {
		// o.wasTransformed = false
		silver.extension.bidirtransform.PbottomOrigin.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_wasTransformed__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// o.concreteOrigin = o
		silver.extension.bidirtransform.PbottomOrigin.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_concreteOrigin__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.undecorate(); } };
		// o.isBottomOrigin = true
		silver.extension.bidirtransform.PbottomOrigin.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_isBottomOrigin__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };

	}

	public static final common.NodeFactory<PbottomOrigin> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PbottomOrigin> {

		@Override
		public PbottomOrigin invoke(final Object[] children, final Object[] annotations) {
			return new PbottomOrigin();
		}
	};

}
