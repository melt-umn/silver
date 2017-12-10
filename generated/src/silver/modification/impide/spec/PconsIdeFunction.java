
package silver.modification.impide.spec;

// top::IdeFunctions ::= h::IdeFunction t::IdeFunctions 
public final class PconsIdeFunction extends silver.modification.impide.spec.NIdeFunctions {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver.modification.impide.spec.NIdeFunction.class,silver.modification.impide.spec.NIdeFunctions.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_spec_consIdeFunction;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.impide.spec.NIdeFunctions.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.impide.spec.NIdeFunctions.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.modification.impide.spec.NIdeFunction.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.modification.impide.spec.NIdeFunctions.num_inh_attrs];

	}

	public PconsIdeFunction(final Object c_h, final Object c_t) {
		super();
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final silver.modification.impide.spec.NIdeFunction getChild_h() {
		return (silver.modification.impide.spec.NIdeFunction) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final silver.modification.impide.spec.NIdeFunctions getChild_t() {
		return (silver.modification.impide.spec.NIdeFunctions) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;
			case i_t: return child_t;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:spec:consIdeFunction erroneously claimed to forward");
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
		return "silver:modification:impide:spec:consIdeFunction";
	}

	static void initProductionAttributeDefinitions() {
		// top.svIdeInterface = h.svIdeInterface ++ t.svIdeInterface
		silver.modification.impide.spec.PconsIdeFunction.synthesizedAttributes[silver.modification.impide.spec.Init.silver_modification_impide_spec_svIdeInterface__ON__silver_modification_impide_spec_IdeFunctions] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.impide.spec.PconsIdeFunction.i_h).synthesized(silver.modification.impide.spec.Init.silver_modification_impide_spec_svIdeInterface__ON__silver_modification_impide_spec_IdeFunction)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.impide.spec.PconsIdeFunction.i_t).synthesized(silver.modification.impide.spec.Init.silver_modification_impide_spec_svIdeInterface__ON__silver_modification_impide_spec_IdeFunctions))); } };
		// top.pluginXml = h.pluginXml ++ t.pluginXml
		silver.modification.impide.spec.PconsIdeFunction.synthesizedAttributes[silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginXml__ON__silver_modification_impide_spec_IdeFunctions] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.impide.spec.PconsIdeFunction.i_h).synthesized(silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginXml__ON__silver_modification_impide_spec_IdeFunction)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.impide.spec.PconsIdeFunction.i_t).synthesized(silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginXml__ON__silver_modification_impide_spec_IdeFunctions))); } };
		// top.pluginXmlActions = h.pluginXmlActions ++ t.pluginXmlActions
		silver.modification.impide.spec.PconsIdeFunction.synthesizedAttributes[silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginXmlActions__ON__silver_modification_impide_spec_IdeFunctions] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.impide.spec.PconsIdeFunction.i_h).synthesized(silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginXmlActions__ON__silver_modification_impide_spec_IdeFunction)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.impide.spec.PconsIdeFunction.i_t).synthesized(silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginXmlActions__ON__silver_modification_impide_spec_IdeFunctions))); } };

	}

	public static final common.NodeFactory<PconsIdeFunction> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsIdeFunction> {

		@Override
		public PconsIdeFunction invoke(final Object[] children, final Object[] annotations) {
			return new PconsIdeFunction(children[0], children[1]);
		}
	};

}
