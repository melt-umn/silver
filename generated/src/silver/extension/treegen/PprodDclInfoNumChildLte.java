
package silver.extension.treegen;

public final class PprodDclInfoNumChildLte extends common.FunctionNode {

	public static final int i_l = 0;
	public static final int i_r = 1;


	public static final Class<?> childTypes[] = { silver.definition.env.NDclInfo.class,silver.definition.env.NDclInfo.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_treegen_prodDclInfoNumChildLte;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l] = new common.Lazy[silver.definition.env.NDclInfo.num_inh_attrs];
	childInheritedAttributes[i_r] = new common.Lazy[silver.definition.env.NDclInfo.num_inh_attrs];

	}

	public PprodDclInfoNumChildLte(final Object c_l, final Object c_r) {
		this.child_l = c_l;
		this.child_r = c_r;

	}

	private Object child_l;
	public final silver.definition.env.NDclInfo getChild_l() {
		return (silver.definition.env.NDclInfo) (child_l = common.Util.demand(child_l));
	}

	private Object child_r;
	public final silver.definition.env.NDclInfo getChild_r() {
		return (silver.definition.env.NDclInfo) (child_r = common.Util.demand(child_r));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_r: return getChild_r();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_r: return child_r;

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
		return "silver:extension:treegen:prodDclInfoNumChildLte";
	}

	public static Boolean invoke(final Object c_l, final Object c_r) {
		try {
		final common.DecoratedNode context = new PprodDclInfoNumChildLte(c_l, c_r).decorate();
		//length(l.typerep.inputTypes) <= length(r.typerep.inputTypes)
		return (Boolean)((((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((silver.definition.type.NType)context.childDecorated(silver.extension.treegen.PprodDclInfoNumChildLte.i_l).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_DclInfo)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_inputTypes__ON__silver_definition_type_Type)); } })) <= ((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((silver.definition.type.NType)context.childDecorated(silver.extension.treegen.PprodDclInfoNumChildLte.i_r).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_DclInfo)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_inputTypes__ON__silver_definition_type_Type)); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:treegen:prodDclInfoNumChildLte", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PprodDclInfoNumChildLte.invoke(children[0], children[1]);
		}
	};
}