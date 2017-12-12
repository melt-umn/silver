
package silver.extension.convenience;

// top::QNameWithTL ::= qn::QName tl::BracketedOptTypeExprs 
public final class PqNameWithTL extends silver.extension.convenience.NQNameWithTL {

	public static final int i_qn = 0;
	public static final int i_tl = 1;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class,silver.definition.type.syntax.NBracketedOptTypeExprs.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_convenience_qNameWithTL;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.convenience.NQNameWithTL.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.convenience.NQNameWithTL.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_qn] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_tl] = new common.Lazy[silver.definition.type.syntax.NBracketedOptTypeExprs.num_inh_attrs];

	}

	public PqNameWithTL(final Object c_qn, final Object c_tl) {
		super();
		this.child_qn = c_qn;
		this.child_tl = c_tl;

	}

	private Object child_qn;
	public final silver.definition.core.NQName getChild_qn() {
		return (silver.definition.core.NQName) (child_qn = common.Util.demand(child_qn));
	}

	private Object child_tl;
	public final silver.definition.type.syntax.NBracketedOptTypeExprs getChild_tl() {
		return (silver.definition.type.syntax.NBracketedOptTypeExprs) (child_tl = common.Util.demand(child_tl));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_qn: return getChild_qn();
			case i_tl: return getChild_tl();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_qn: return child_qn;
			case i_tl: return child_tl;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:convenience:qNameWithTL erroneously claimed to forward");
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
		return "silver:extension:convenience:qNameWithTL";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = qn.pp ++ tl.pp
		silver.extension.convenience.PqNameWithTL.synthesizedAttributes[silver.extension.convenience.Init.silver_definition_env_pp__ON__silver_extension_convenience_QNameWithTL] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.convenience.PqNameWithTL.i_qn).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.convenience.PqNameWithTL.i_tl).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_BracketedOptTypeExprs))); } };
		// top.qnwtQN = qn
		silver.extension.convenience.PqNameWithTL.synthesizedAttributes[silver.extension.convenience.Init.silver_extension_convenience_qnwtQN__ON__silver_extension_convenience_QNameWithTL] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.extension.convenience.PqNameWithTL.i_qn).undecorate(); } };
		// top.qnwtTL = tl
		silver.extension.convenience.PqNameWithTL.synthesizedAttributes[silver.extension.convenience.Init.silver_extension_convenience_qnwtTL__ON__silver_extension_convenience_QNameWithTL] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.extension.convenience.PqNameWithTL.i_tl).undecorate(); } };

	}

	public static final common.NodeFactory<PqNameWithTL> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PqNameWithTL> {

		@Override
		public PqNameWithTL invoke(final Object[] children, final Object[] annotations) {
			return new PqNameWithTL(children[0], children[1]);
		}
	};

}
