
package silver.extension.bidirtransform;

public final class PhasRwOut extends common.FunctionNode {

	public static final int i_rwrs = 0;
	public static final int i_outType = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_hasRwOut;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PhasRwOut(final Object c_rwrs, final Object c_outType) {
		this.child_rwrs = c_rwrs;
		this.child_outType = c_outType;

	}

	private Object child_rwrs;
	public final common.ConsCell getChild_rwrs() {
		return (common.ConsCell) (child_rwrs = common.Util.demand(child_rwrs));
	}

	private Object child_outType;
	public final common.StringCatter getChild_outType() {
		return (common.StringCatter) (child_outType = common.Util.demand(child_outType));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_rwrs: return getChild_rwrs();
			case i_outType: return getChild_outType();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_rwrs: return child_rwrs;
			case i_outType: return child_outType;

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
		return "silver:extension:bidirtransform:hasRwOut";
	}

	public static Boolean invoke(final Object c_rwrs, final Object c_outType) {
		try {
		final common.DecoratedNode context = new PhasRwOut(c_rwrs, c_outType).decorate();
		//if null(rwrs) then false else if ! hd.hasProduction && unFull(hd.typerep.typeName) == unFull(outType) then true else hasRwOut(tail(rwrs), outType)
		return (Boolean)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PhasRwOut.i_rwrs))) ? false : (((!((Boolean)((common.DecoratedNode)context.localAsIs(silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_hasRwOut)).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_hasProduction__ON__silver_extension_bidirtransform_RewriteRule))) && ((common.StringCatter)silver.extension.bidirtransform.PunFull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)((common.DecoratedNode)context.localAsIs(silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_hasRwOut)).synthesized(silver.extension.bidirtransform.Init.silver_definition_env_typerep__ON__silver_extension_bidirtransform_RewriteRule)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } })).equals(((common.StringCatter)silver.extension.bidirtransform.PunFull.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PhasRwOut.i_outType))))) ? true : ((Boolean)silver.extension.bidirtransform.PhasRwOut.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PhasRwOut.i_rwrs))); } }, context.childAsIsLazy(silver.extension.bidirtransform.PhasRwOut.i_outType))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:hasRwOut", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PhasRwOut.invoke(children[0], children[1]);
		}
	};
}