
package silver.definition.env;

public final class PdefsFromPADcls extends common.FunctionNode {

	public static final int i_valueDclInfos = 0;
	public static final int i_s = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,silver.definition.env.NNamedSignature.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_defsFromPADcls;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_s] = new common.Lazy[silver.definition.env.NNamedSignature.num_inh_attrs];

	}

	public PdefsFromPADcls(final Object c_valueDclInfos, final Object c_s) {
		this.child_valueDclInfos = c_valueDclInfos;
		this.child_s = c_s;

	}

	private Object child_valueDclInfos;
	public final common.ConsCell getChild_valueDclInfos() {
		return (common.ConsCell) (child_valueDclInfos = common.Util.demand(child_valueDclInfos));
	}

	private Object child_s;
	public final silver.definition.env.NNamedSignature getChild_s() {
		return (silver.definition.env.NNamedSignature) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_valueDclInfos: return getChild_valueDclInfos();
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_valueDclInfos: return child_valueDclInfos;
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
		return "silver:definition:env:defsFromPADcls";
	}

	public static common.ConsCell invoke(final Object c_valueDclInfos, final Object c_s) {
		try {
		final common.DecoratedNode context = new PdefsFromPADcls(c_valueDclInfos, c_s).decorate();
		//if null(valueDclInfos) then [] else if subst.failure then defsFromPADcls(tail(valueDclInfos), s) else map(performSubstitutionDef(_, subst), head(valueDclInfos).prodDefs) ++ defsFromPADcls(tail(valueDclInfos), s)
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.env.PdefsFromPADcls.i_valueDclInfos))) ? ((common.ConsCell)core.Pnil.invoke()) : (((Boolean)context.localDecorated(silver.definition.env.Init.subst__ON__silver_definition_env_defsFromPADcls).synthesized(silver.definition.type.Init.silver_definition_type_failure__ON__silver_definition_type_Substitution)) ? ((common.ConsCell)silver.definition.env.PdefsFromPADcls.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.env.PdefsFromPADcls.i_valueDclInfos))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.env.PdefsFromPADcls.i_s)))) : ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.env.PperformSubstitutionDef.factory.invokePartial(new int[]{1}, new Object[]{common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.env.Init.subst__ON__silver_definition_env_defsFromPADcls))}); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((silver.definition.env.NDclInfo)core.Phead.invoke(context.childAsIsLazy(silver.definition.env.PdefsFromPADcls.i_valueDclInfos))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_prodDefs__ON__silver_definition_env_DclInfo)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PdefsFromPADcls.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.env.PdefsFromPADcls.i_valueDclInfos))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.env.PdefsFromPADcls.i_s)))); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:defsFromPADcls", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PdefsFromPADcls.invoke(children[0], children[1]);
		}
	};
}