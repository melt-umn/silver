
package silver.extension.bidirtransform;

public final class PgetProdFromSig extends common.FunctionNode {

	public static final int i_s = 0;
	public static final int i_prds = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_getProdFromSig;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PgetProdFromSig(final Object c_s, final Object c_prds) {
		this.child_s = c_s;
		this.child_prds = c_prds;

	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}

	private Object child_prds;
	public final common.ConsCell getChild_prds() {
		return (common.ConsCell) (child_prds = common.Util.demand(child_prds));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();
			case i_prds: return getChild_prds();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;
			case i_prds: return child_prds;

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
		return "silver:extension:bidirtransform:getProdFromSig";
	}

	public static common.ConsCell invoke(final Object c_s, final Object c_prds) {
		try {
		final common.DecoratedNode context = new PgetProdFromSig(c_s, c_prds).decorate();
		//if length(prds) == 0 then [] else if unFull(head(prds).fullName) == s then [ head(prds) ] else getProdFromSig(s, tail(prds))
		return (common.ConsCell)((((Integer)core.PlistLength.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PgetProdFromSig.i_prds))).equals(Integer.valueOf((int)0)) ? ((common.ConsCell)core.Pnil.invoke()) : (((common.StringCatter)silver.extension.bidirtransform.PunFull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PgetProdFromSig.i_prds))).synthesized(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature)); } })).equals(((common.StringCatter)context.childAsIs(silver.extension.bidirtransform.PgetProdFromSig.i_s))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PgetProdFromSig.i_prds))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)silver.extension.bidirtransform.PgetProdFromSig.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PgetProdFromSig.i_s), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PgetProdFromSig.i_prds))); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:getProdFromSig", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgetProdFromSig.invoke(children[0], children[1]);
		}
	};
}