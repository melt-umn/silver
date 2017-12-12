
package silver.driver;

// top::RunError ::= c::Integer m::String 
public final class PrunError extends silver.driver.NRunError {

	public static final int i_c = 0;
	public static final int i_m = 1;


	public static final Class<?> childTypes[] = {Integer.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_runError;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.driver.NRunError.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.driver.NRunError.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PrunError(final Object c_c, final Object c_m) {
		super();
		this.child_c = c_c;
		this.child_m = c_m;

	}

	private Object child_c;
	public final Integer getChild_c() {
		return (Integer) (child_c = common.Util.demand(child_c));
	}

	private Object child_m;
	public final common.StringCatter getChild_m() {
		return (common.StringCatter) (child_m = common.Util.demand(child_m));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_c: return getChild_c();
			case i_m: return getChild_m();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_c: return child_c;
			case i_m: return child_m;

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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:driver:runError erroneously claimed to forward");
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
		return "silver:driver:runError";
	}

	static void initProductionAttributeDefinitions() {
		// top.code = c
		silver.driver.PrunError.synthesizedAttributes[silver.driver.Init.silver_driver_util_code__ON__silver_driver_RunError] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childAsIs(silver.driver.PrunError.i_c)); } };
		// top.message = m
		silver.driver.PrunError.synthesizedAttributes[silver.driver.Init.silver_langutil_message__ON__silver_driver_RunError] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.driver.PrunError.i_m)); } };

	}

	public static final common.NodeFactory<PrunError> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PrunError> {

		@Override
		public PrunError invoke(final Object[] children, final Object[] annotations) {
			return new PrunError(children[0], children[1]);
		}
	};

}
