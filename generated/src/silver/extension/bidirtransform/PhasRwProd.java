
package silver.extension.bidirtransform;

public final class PhasRwProd extends common.FunctionNode {

	public static final int i_rwrs = 0;
	public static final int i_outType = 1;
	public static final int i_ns = 2;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_hasRwProd;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PhasRwProd(final Object c_rwrs, final Object c_outType, final Object c_ns) {
		this.child_rwrs = c_rwrs;
		this.child_outType = c_outType;
		this.child_ns = c_ns;

	}

	private Object child_rwrs;
	public final common.ConsCell getChild_rwrs() {
		return (common.ConsCell) (child_rwrs = common.Util.demand(child_rwrs));
	}

	private Object child_outType;
	public final common.StringCatter getChild_outType() {
		return (common.StringCatter) (child_outType = common.Util.demand(child_outType));
	}

	private Object child_ns;
	public final common.DecoratedNode getChild_ns() {
		return (common.DecoratedNode) (child_ns = common.Util.demand(child_ns));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_rwrs: return getChild_rwrs();
			case i_outType: return getChild_outType();
			case i_ns: return getChild_ns();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_rwrs: return child_rwrs;
			case i_outType: return child_outType;
			case i_ns: return child_ns;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "silver:extension:bidirtransform:hasRwProd";
	}

	public static Boolean invoke(final Object c_rwrs, final Object c_outType, final Object c_ns) {
		try {
		final common.DecoratedNode context = new PhasRwProd(c_rwrs, c_outType, c_ns).decorate();
		//if null(rwrs) then false else if hd.hasProduction && unFull(hd.inputProduction.name) == unFull(ns.fullName) && unFull(hd.typerep.typeName) == unFull(outType) then true else hasRwProd(tail(rwrs), outType, ns)
		return (Boolean)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PhasRwProd.i_rwrs))) ? false : (((((Boolean)((common.DecoratedNode)context.localAsIs(silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_hasRwProd)).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_hasProduction__ON__silver_extension_bidirtransform_RewriteRule)) && ((common.StringCatter)silver.extension.bidirtransform.PunFull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.extension.bidirtransform.NRewriteProduction)((common.DecoratedNode)context.localAsIs(silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_hasRwProd)).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_inputProduction__ON__silver_extension_bidirtransform_RewriteRule)).decorate(context, (common.Lazy[])null).synthesized(silver.extension.bidirtransform.Init.silver_definition_core_name__ON__silver_extension_bidirtransform_RewriteProduction)); } })).equals(((common.StringCatter)silver.extension.bidirtransform.PunFull.invoke(context.childAsIsSynthesizedLazy(silver.extension.bidirtransform.PhasRwProd.i_ns, silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature))))) && ((common.StringCatter)silver.extension.bidirtransform.PunFull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)((common.DecoratedNode)context.localAsIs(silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_hasRwProd)).synthesized(silver.extension.bidirtransform.Init.silver_definition_env_typerep__ON__silver_extension_bidirtransform_RewriteRule)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } })).equals(((common.StringCatter)silver.extension.bidirtransform.PunFull.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PhasRwProd.i_outType))))) ? true : ((Boolean)silver.extension.bidirtransform.PhasRwProd.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PhasRwProd.i_rwrs))); } }, context.childAsIsLazy(silver.extension.bidirtransform.PhasRwProd.i_outType), context.childAsIsLazy(silver.extension.bidirtransform.PhasRwProd.i_ns))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:hasRwProd", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PhasRwProd.invoke(children[0], children[1], children[2]);
		}
	};
}