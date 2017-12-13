
package silver.extension.bidirtransform;

public final class PfilterSigs extends common.FunctionNode {

	public static final int i_nm = 0;
	public static final int i_toFilter = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_filterSigs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PfilterSigs(final Object c_nm, final Object c_toFilter) {
		this.child_nm = c_nm;
		this.child_toFilter = c_toFilter;

	}

	private Object child_nm;
	public final common.StringCatter getChild_nm() {
		return (common.StringCatter) (child_nm = common.Util.demand(child_nm));
	}

	private Object child_toFilter;
	public final common.ConsCell getChild_toFilter() {
		return (common.ConsCell) (child_toFilter = common.Util.demand(child_toFilter));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_nm: return getChild_nm();
			case i_toFilter: return getChild_toFilter();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_nm: return child_nm;
			case i_toFilter: return child_toFilter;

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
		return "silver:extension:bidirtransform:filterSigs";
	}

	public static common.ConsCell invoke(final Object c_nm, final Object c_toFilter) {
		try {
		final common.DecoratedNode context = new PfilterSigs(c_nm, c_toFilter).decorate();
		//if null(toFilter) then [] else if unFull(hd.outputElement.typerep.typeName) == nm then [ hd ] ++ filterSigs(nm, tail(toFilter)) else filterSigs(nm, tail(toFilter))
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PfilterSigs.i_toFilter))) ? ((common.ConsCell)core.Pnil.invoke()) : (((common.StringCatter)silver.extension.bidirtransform.PunFull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)((silver.definition.env.NNamedSignatureElement)((common.DecoratedNode)context.localAsIs(silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_filterSigs)).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } })).equals(((common.StringCatter)context.childAsIs(silver.extension.bidirtransform.PfilterSigs.i_nm))) ? ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.localAsIsLazy(silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_filterSigs), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.bidirtransform.PfilterSigs.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PfilterSigs.i_nm), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PfilterSigs.i_toFilter))); } })); } })) : ((common.ConsCell)silver.extension.bidirtransform.PfilterSigs.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PfilterSigs.i_nm), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PfilterSigs.i_toFilter))); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:filterSigs", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfilterSigs.invoke(children[0], children[1]);
		}
	};
}