
package silver.extension.patternmatching;

public final class PensureDecoratedExpr extends common.FunctionNode {

	public static final int i_e = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_patternmatching_ensureDecoratedExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PensureDecoratedExpr(final Object c_e) {
		this.child_e = c_e;

	}

	private Object child_e;
	public final common.DecoratedNode getChild_e() {
		return (common.DecoratedNode) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e: return child_e;

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
		return "silver:extension:patternmatching:ensureDecoratedExpr";
	}

	public static silver.definition.core.NExpr invoke(final Object c_e) {
		try {
		final common.DecoratedNode context = new PensureDecoratedExpr(c_e).decorate();
		//if et.isDecorable then decorateExprWithEmpty('decorate', exprRef(e,location=e.location), 'with', '{', '}',location=e.location) else exprRef(e,location=e.location)
		return (silver.definition.core.NExpr)((((Boolean)context.localDecorated(silver.extension.patternmatching.Init.et__ON__silver_extension_patternmatching_ensureDecoratedExpr).synthesized(silver.definition.type.Init.silver_definition_type_isDecorable__ON__silver_definition_type_Type)) ? ((silver.definition.core.NExpr)new silver.definition.core.PdecorateExprWithEmpty(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TDecorate_kwd((new common.StringCatter("decorate")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.PexprRef(context.childAsIsLazy(silver.extension.patternmatching.PensureDecoratedExpr.i_e), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)((common.DecoratedNode)context.childAsIs(silver.extension.patternmatching.PensureDecoratedExpr.i_e)).undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TWith_kwd((new common.StringCatter("with")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TLCurly_t((new common.StringCatter("{")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TRCurly_t((new common.StringCatter("}")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)((common.DecoratedNode)context.childAsIs(silver.extension.patternmatching.PensureDecoratedExpr.i_e)).undecorate()).getAnno_core_location()); } })) : ((silver.definition.core.NExpr)new silver.definition.core.PexprRef(context.childAsIsLazy(silver.extension.patternmatching.PensureDecoratedExpr.i_e), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)((common.DecoratedNode)context.childAsIs(silver.extension.patternmatching.PensureDecoratedExpr.i_e)).undecorate()).getAnno_core_location()); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:patternmatching:ensureDecoratedExpr", t);
		}
	}

	public static final common.NodeFactory<silver.definition.core.NExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.core.NExpr> {
		@Override
		public silver.definition.core.NExpr invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PensureDecoratedExpr.invoke(children[0]);
		}
	};
}