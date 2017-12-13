
package silver.extension.bidirtransform;

// top::AppExpr ::= name::String accessOn::String 
public final class PnamedAccess extends silver.definition.core.NAppExpr {

	public static final int i_name = 0;
	public static final int i_accessOn = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_namedAccess;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAppExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAppExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PnamedAccess(final Object c_name, final Object c_accessOn, final Object a_core_location) {
		super(a_core_location);
		this.child_name = c_name;
		this.child_accessOn = c_accessOn;

	}

	private Object child_name;
	public final common.StringCatter getChild_name() {
		return (common.StringCatter) (child_name = common.Util.demand(child_name));
	}

	private Object child_accessOn;
	public final common.StringCatter getChild_accessOn() {
		return (common.StringCatter) (child_accessOn = common.Util.demand(child_accessOn));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_name: return getChild_name();
			case i_accessOn: return getChild_accessOn();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_name: return child_name;
			case i_accessOn: return child_accessOn;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.core.NAppExpr)new silver.definition.core.PpresentAppExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PexprAccess(context.childAsIsLazy(silver.extension.bidirtransform.PnamedAccess.i_name), context.childAsIsLazy(silver.extension.bidirtransform.PnamedAccess.i_accessOn), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAppExpr)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAppExpr)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:bidirtransform:namedAccess";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PnamedAccess> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnamedAccess> {

		@Override
		public PnamedAccess invoke(final Object[] children, final Object[] annotations) {
			return new PnamedAccess(children[0], children[1], annotations[0]);
		}
	};

}
