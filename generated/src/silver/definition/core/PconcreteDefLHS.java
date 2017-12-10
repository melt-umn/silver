
package silver.definition.core;

// top::DefLHS ::= q::QName 
public final class PconcreteDefLHS extends silver.definition.core.NDefLHS {

	public static final int i_q = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_concreteDefLHS;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NDefLHS.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NDefLHS.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_q] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PconcreteDefLHS(final Object c_q, final Object a_core_location) {
		super(a_core_location);
		this.child_q = c_q;

	}

	private Object child_q;
	public final silver.definition.core.NQName getChild_q() {
		return (silver.definition.core.NQName) (child_q = common.Util.demand(child_q));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_q: return getChild_q();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_q: return child_q;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
	}

	@Override
	public common.Lazy getSynthesized(final int index) {
		return synthesizedAttributes[index];
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
	public boolean hasForward() {
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.core.PconcreteDefLHS.i_q).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_dcls__ON__silver_definition_core_QNameLookup)); } })) ? ((silver.definition.core.NDefLHS)new silver.definition.core.PerrorDefLHS(context.childDecoratedLazy(silver.definition.core.PconcreteDefLHS.i_q), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NDefLHS)context.undecorate()).getAnno_core_location()); } })) : ((silver.definition.core.NDefLHS)((common.NodeFactory<silver.definition.core.NDefLHS>)((silver.definition.env.NDclInfo)((common.DecoratedNode)context.childDecorated(silver.definition.core.PconcreteDefLHS.i_q).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_dcl__ON__silver_definition_core_QNameLookup)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.core.Init.silver_definition_core_defLHSDispatcher__ON__silver_definition_env_DclInfo)).invoke(new Object[]{context.childDecoratedLazy(silver.definition.core.PconcreteDefLHS.i_q), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NDefLHS)context.undecorate()).getAnno_core_location()); } }}, null)));
	}

	@Override
	public common.Lazy getForwardInheritedAttributes(final int index) {
		return forwardInheritedAttributes[index];
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
		return "silver:definition:core:concreteDefLHS";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = q.pp
		silver.definition.core.PconcreteDefLHS.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_DefLHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.core.PconcreteDefLHS.i_q).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)); } };
		// top.errors := q.lookupValue.errors ++ forward.errors
		if(silver.definition.core.PconcreteDefLHS.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_DefLHS] == null)
			silver.definition.core.PconcreteDefLHS.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_DefLHS] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_DefLHS);
		((common.CollectionAttribute)silver.definition.core.PconcreteDefLHS.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_DefLHS]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.core.PconcreteDefLHS.i_q).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.forward().synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_DefLHS)); } })); } });

	}

	public static final common.NodeFactory<PconcreteDefLHS> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconcreteDefLHS> {

		@Override
		public PconcreteDefLHS invoke(final Object[] children, final Object[] annotations) {
			return new PconcreteDefLHS(children[0], annotations[0]);
		}
	};

}
