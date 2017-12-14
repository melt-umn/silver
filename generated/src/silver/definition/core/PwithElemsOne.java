
package silver.definition.core;

// top::WithElems ::= we::WithElem 
public final class PwithElemsOne extends silver.definition.core.NWithElems {

	public static final int i_we = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.NWithElem.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_withElemsOne;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NWithElems.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NWithElems.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_we] = new common.Lazy[silver.definition.core.NWithElem.num_inh_attrs];

	}

	public PwithElemsOne(final Object c_we, final Object a_core_location) {
		super(a_core_location);
		this.child_we = c_we;

	}

	private Object child_we;
	public final silver.definition.core.NWithElem getChild_we() {
		return (silver.definition.core.NWithElem) (child_we = common.Util.demand(child_we));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_we: return getChild_we();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_we: return child_we;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:withElemsOne erroneously claimed to forward");
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
		return "silver:definition:core:withElemsOne";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = we.pp
		silver.definition.core.PwithElemsOne.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_WithElems] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.core.PwithElemsOne.i_we).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_WithElem)); } };
		// top.envMaps = we.envMaps
		silver.definition.core.PwithElemsOne.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_envMaps__ON__silver_definition_core_WithElems] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PwithElemsOne.i_we).synthesized(silver.definition.core.Init.silver_definition_core_envMaps__ON__silver_definition_core_WithElem)); } };

	}

	public static final common.NodeFactory<PwithElemsOne> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PwithElemsOne> {

		@Override
		public PwithElemsOne invoke(final Object[] children, final Object[] annotations) {
			return new PwithElemsOne(children[0], annotations[0]);
		}
	};

}
