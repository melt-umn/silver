
package silver.definition.core;

// top::ExprInhs ::= lhs::ExprInh inh::ExprInhs 
public final class PexprInhsCons extends silver.definition.core.NExprInhs {

	public static final int i_lhs = 0;
	public static final int i_inh = 1;


	public static final Class<?> childTypes[] = {silver.definition.core.NExprInh.class,silver.definition.core.NExprInhs.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_exprInhsCons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExprInhs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExprInhs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_lhs] = new common.Lazy[silver.definition.core.NExprInh.num_inh_attrs];
	childInheritedAttributes[i_inh] = new common.Lazy[silver.definition.core.NExprInhs.num_inh_attrs];

	}

	public PexprInhsCons(final Object c_lhs, final Object c_inh, final Object a_core_location) {
		super(a_core_location);
		this.child_lhs = c_lhs;
		this.child_inh = c_inh;

	}

	private Object child_lhs;
	public final silver.definition.core.NExprInh getChild_lhs() {
		return (silver.definition.core.NExprInh) (child_lhs = common.Util.demand(child_lhs));
	}

	private Object child_inh;
	public final silver.definition.core.NExprInhs getChild_inh() {
		return (silver.definition.core.NExprInhs) (child_inh = common.Util.demand(child_inh));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_lhs: return getChild_lhs();
			case i_inh: return getChild_inh();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_lhs: return child_lhs;
			case i_inh: return child_inh;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:exprInhsCons erroneously claimed to forward");
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
		return "silver:definition:core:exprInhsCons";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = lhs.pp ++ " " ++ inh.pp
		silver.definition.core.PexprInhsCons.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ExprInhs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PexprInhsCons.i_lhs).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ExprInh)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PexprInhsCons.i_inh).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ExprInhs)))); } };
		// top.errors := lhs.errors ++ inh.errors
		if(silver.definition.core.PexprInhsCons.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ExprInhs] == null)
			silver.definition.core.PexprInhsCons.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ExprInhs] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ExprInhs);
		((common.CollectionAttribute)silver.definition.core.PexprInhsCons.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ExprInhs]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PexprInhsCons.i_lhs, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ExprInh), context.childDecoratedSynthesizedLazy(silver.definition.core.PexprInhsCons.i_inh, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ExprInhs))); } });
		// top.suppliedInhs = lhs.suppliedInhs ++ inh.suppliedInhs
		silver.definition.core.PexprInhsCons.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_suppliedInhs__ON__silver_definition_core_ExprInhs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PexprInhsCons.i_lhs, silver.definition.core.Init.silver_definition_core_suppliedInhs__ON__silver_definition_core_ExprInh), context.childDecoratedSynthesizedLazy(silver.definition.core.PexprInhsCons.i_inh, silver.definition.core.Init.silver_definition_core_suppliedInhs__ON__silver_definition_core_ExprInhs))); } };

	}

	public static final common.NodeFactory<PexprInhsCons> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PexprInhsCons> {

		@Override
		public PexprInhsCons invoke(final Object[] children, final Object[] annotations) {
			return new PexprInhsCons(children[0], children[1], annotations[0]);
		}
	};

}
