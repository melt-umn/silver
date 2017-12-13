
package silver.definition.env;

public final class PperformSubstitutionDef extends common.FunctionNode {

	public static final int i_d = 0;
	public static final int i_s = 1;


	public static final Class<?> childTypes[] = { silver.definition.env.NDef.class,silver.definition.type.NSubstitution.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_performSubstitutionDef;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_d] = new common.Lazy[silver.definition.env.NDef.num_inh_attrs];
	childInheritedAttributes[i_s] = new common.Lazy[silver.definition.type.NSubstitution.num_inh_attrs];

	}

	public PperformSubstitutionDef(final Object c_d, final Object c_s) {
		this.child_d = c_d;
		this.child_s = c_s;

	}

	private Object child_d;
	public final silver.definition.env.NDef getChild_d() {
		return (silver.definition.env.NDef) (child_d = common.Util.demand(child_d));
	}

	private Object child_s;
	public final silver.definition.type.NSubstitution getChild_s() {
		return (silver.definition.type.NSubstitution) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_d: return getChild_d();
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_d: return child_d;
			case i_s: return child_s;

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
		return "silver:definition:env:performSubstitutionDef";
	}

	public static silver.definition.env.NDef invoke(final Object c_d, final Object c_s) {
		try {
		final common.DecoratedNode context = new PperformSubstitutionDef(c_d, c_s).decorate();
		//valueDef(defaultEnvItem(performSubstitutionDclInfo(d.dcl, s)))
		return (silver.definition.env.NDef)(((silver.definition.env.NDef)new silver.definition.env.PvalueDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NEnvItem)silver.definition.env.PdefaultEnvItem.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDclInfo)silver.definition.env.PperformSubstitutionDclInfo.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.PperformSubstitutionDef.i_d, silver.definition.env.Init.silver_definition_env_dcl__ON__silver_definition_env_Def), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.env.PperformSubstitutionDef.i_s)))); } })); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:performSubstitutionDef", t);
		}
	}

	public static final common.NodeFactory<silver.definition.env.NDef> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.env.NDef> {
		@Override
		public silver.definition.env.NDef invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PperformSubstitutionDef.invoke(children[0], children[1]);
		}
	};
}