
package silver.extension.bidirtransform;

public final class PprodFromDef extends common.FunctionNode {

	public static final int i_def = 0;


	public static final Class<?> childTypes[] = { silver.definition.env.NDef.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_prodFromDef;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_def] = new common.Lazy[silver.definition.env.NDef.num_inh_attrs];

	}

	public PprodFromDef(final Object c_def) {
		this.child_def = c_def;

	}

	private Object child_def;
	public final silver.definition.env.NDef getChild_def() {
		return (silver.definition.env.NDef) (child_def = common.Util.demand(child_def));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_def: return getChild_def();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_def: return child_def;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return "silver:extension:bidirtransform:prodFromDef";
	}

	public static common.ConsCell invoke(final Object c_def) {
		try {
		final common.DecoratedNode context = new PprodFromDef(c_def).decorate();
		//if null(def.prodNamedSig) then [] else [ decorate head(def.prodNamedSig) with {} ]
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PprodFromDef.i_def, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodNamedSig__ON__silver_definition_env_Def))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignature)core.Phead.invoke(context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PprodFromDef.i_def, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodNamedSig__ON__silver_definition_env_Def))).decorate(context, (common.Lazy[])null); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:prodFromDef", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PprodFromDef.invoke(children[0]);
		}
	};
}