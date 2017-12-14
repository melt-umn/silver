
package silver.modification.impide;

// top::TypeName ::= 'url' 
public final class PpropType_URL extends silver.modification.impide.NTypeName {

	public static final int i__G_0 = 0;


	public static final Class<?> childTypes[] = {silver.modification.impide.TImpIde_PropType_url_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_propType_URL;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.impide.NTypeName.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.impide.NTypeName.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PpropType_URL(final Object c__G_0) {
		super();
		this.child__G_0 = c__G_0;

	}

	private Object child__G_0;
	public final silver.modification.impide.TImpIde_PropType_url_t getChild__G_0() {
		return (silver.modification.impide.TImpIde_PropType_url_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:propType_URL erroneously claimed to forward");
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
		return "silver:modification:impide:propType_URL";
	}

	static void initProductionAttributeDefinitions() {
		// top.propType = urlPropType()
		silver.modification.impide.PpropType_URL.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_propType__ON__silver_modification_impide_TypeName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.modification.impide.spec.NPropType)new silver.modification.impide.spec.PurlPropType()); } };

	}

	public static final common.NodeFactory<PpropType_URL> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PpropType_URL> {

		@Override
		public PpropType_URL invoke(final Object[] children, final Object[] annotations) {
			return new PpropType_URL(children[0]);
		}
	};

}
