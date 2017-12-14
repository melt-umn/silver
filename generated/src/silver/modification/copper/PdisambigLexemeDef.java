
package silver.modification.copper;

public final class PdisambigLexemeDef extends common.FunctionNode {

	public static final int i_sg = 0;
	public static final int i_sl = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,core.NLocation.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_disambigLexemeDef;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_sl] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PdisambigLexemeDef(final Object c_sg, final Object c_sl) {
		this.child_sg = c_sg;
		this.child_sl = c_sl;

	}

	private Object child_sg;
	public final common.StringCatter getChild_sg() {
		return (common.StringCatter) (child_sg = common.Util.demand(child_sg));
	}

	private Object child_sl;
	public final core.NLocation getChild_sl() {
		return (core.NLocation) (child_sl = common.Util.demand(child_sl));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_sg: return getChild_sg();
			case i_sl: return getChild_sl();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_sg: return child_sg;
			case i_sl: return child_sl;

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
		return "silver:modification:copper:disambigLexemeDef";
	}

	public static silver.definition.env.NDef invoke(final Object c_sg, final Object c_sl) {
		try {
		final common.DecoratedNode context = new PdisambigLexemeDef(c_sg, c_sl).decorate();
		//valueDef(defaultEnvItem(disambigLexemeDcl(sg, sl)))
		return (silver.definition.env.NDef)(((silver.definition.env.NDef)new silver.definition.env.PvalueDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NEnvItem)silver.definition.env.PdefaultEnvItem.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDclInfo)new silver.modification.copper.PdisambigLexemeDcl(context.childAsIsLazy(silver.modification.copper.PdisambigLexemeDef.i_sg), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.copper.PdisambigLexemeDef.i_sl)))); } })); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:copper:disambigLexemeDef", t);
		}
	}

	public static final common.NodeFactory<silver.definition.env.NDef> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.env.NDef> {
		@Override
		public silver.definition.env.NDef invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PdisambigLexemeDef.invoke(children[0], children[1]);
		}
	};
}