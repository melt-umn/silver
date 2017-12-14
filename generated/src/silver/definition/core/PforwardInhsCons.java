
package silver.definition.core;

// top::ForwardInhs ::= lhs::ForwardInh rhs::ForwardInhs 
public final class PforwardInhsCons extends silver.definition.core.NForwardInhs {

	public static final int i_lhs = 0;
	public static final int i_rhs = 1;


	public static final Class<?> childTypes[] = {silver.definition.core.NForwardInh.class,silver.definition.core.NForwardInhs.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_forwardInhsCons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NForwardInhs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NForwardInhs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_lhs] = new common.Lazy[silver.definition.core.NForwardInh.num_inh_attrs];
	childInheritedAttributes[i_rhs] = new common.Lazy[silver.definition.core.NForwardInhs.num_inh_attrs];

	}

	public PforwardInhsCons(final Object c_lhs, final Object c_rhs, final Object a_core_location) {
		super(a_core_location);
		this.child_lhs = c_lhs;
		this.child_rhs = c_rhs;

	}

	private Object child_lhs;
	public final silver.definition.core.NForwardInh getChild_lhs() {
		return (silver.definition.core.NForwardInh) (child_lhs = common.Util.demand(child_lhs));
	}

	private Object child_rhs;
	public final silver.definition.core.NForwardInhs getChild_rhs() {
		return (silver.definition.core.NForwardInhs) (child_rhs = common.Util.demand(child_rhs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_lhs: return getChild_lhs();
			case i_rhs: return getChild_rhs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_lhs: return child_lhs;
			case i_rhs: return child_rhs;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:forwardInhsCons erroneously claimed to forward");
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
		return "silver:definition:core:forwardInhsCons";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = lhs.pp ++ " " ++ rhs.pp
		silver.definition.core.PforwardInhsCons.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ForwardInhs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PforwardInhsCons.i_lhs).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ForwardInh)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PforwardInhsCons.i_rhs).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ForwardInhs)))); } };
		// top.errors := lhs.errors ++ rhs.errors
		if(silver.definition.core.PforwardInhsCons.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ForwardInhs] == null)
			silver.definition.core.PforwardInhsCons.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ForwardInhs] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ForwardInhs);
		((common.CollectionAttribute)silver.definition.core.PforwardInhsCons.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ForwardInhs]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PforwardInhsCons.i_lhs, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ForwardInh), context.childDecoratedSynthesizedLazy(silver.definition.core.PforwardInhsCons.i_rhs, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ForwardInhs))); } });

	}

	public static final common.NodeFactory<PforwardInhsCons> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PforwardInhsCons> {

		@Override
		public PforwardInhsCons invoke(final Object[] children, final Object[] annotations) {
			return new PforwardInhsCons(children[0], children[1], annotations[0]);
		}
	};

}
