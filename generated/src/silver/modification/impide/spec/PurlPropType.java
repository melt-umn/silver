
package silver.modification.impide.spec;

// top::PropType ::= 
public final class PurlPropType extends silver.modification.impide.spec.NPropType {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_spec_urlPropType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.impide.spec.NPropType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.impide.spec.NPropType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PurlPropType() {
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:spec:urlPropType erroneously claimed to forward");
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
		return "silver:modification:impide:spec:urlPropType";
	}

	static void initProductionAttributeDefinitions() {
		// top.strPropType = "url"
		silver.modification.impide.spec.PurlPropType.synthesizedAttributes[silver.modification.impide.spec.Init.silver_modification_impide_spec_strPropType__ON__silver_modification_impide_spec_PropType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("url")); } };
		// top.propControl = "URLPropertyControl"
		silver.modification.impide.spec.PurlPropType.synthesizedAttributes[silver.modification.impide.spec.Init.silver_modification_impide_spec_propControl__ON__silver_modification_impide_spec_PropType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("URLPropertyControl")); } };

	}

	public static final common.NodeFactory<PurlPropType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PurlPropType> {

		@Override
		public PurlPropType invoke(final Object[] children, final Object[] annotations) {
			return new PurlPropType();
		}
	};

}
