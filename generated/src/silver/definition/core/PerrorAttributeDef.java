
package silver.definition.core;

// top::ProductionStmt ::= msg::[Message] dl::Decorated DefLHS attr::Decorated QNameAttrOccur e::Expr 
public final class PerrorAttributeDef extends silver.definition.core.NProductionStmt {

	public static final int i_msg = 0;
	public static final int i_dl = 1;
	public static final int i_attr = 2;
	public static final int i_e = 3;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class,silver.definition.core.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_errorAttributeDef;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PerrorAttributeDef(final Object c_msg, final Object c_dl, final Object c_attr, final Object c_e, final Object a_core_location) {
		super(a_core_location);
		this.child_msg = c_msg;
		this.child_dl = c_dl;
		this.child_attr = c_attr;
		this.child_e = c_e;

	}

	private Object child_msg;
	public final common.ConsCell getChild_msg() {
		return (common.ConsCell) (child_msg = common.Util.demand(child_msg));
	}

	private Object child_dl;
	public final common.DecoratedNode getChild_dl() {
		return (common.DecoratedNode) (child_dl = common.Util.demand(child_dl));
	}

	private Object child_attr;
	public final common.DecoratedNode getChild_attr() {
		return (common.DecoratedNode) (child_attr = common.Util.demand(child_attr));
	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_msg: return getChild_msg();
			case i_dl: return getChild_dl();
			case i_attr: return getChild_attr();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_msg: return child_msg;
			case i_dl: return child_dl;
			case i_attr: return child_attr;
			case i_e: return child_e;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return ((silver.definition.core.NProductionStmt)new silver.definition.core.PerrorProductionStmt(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childAsIsLazy(silver.definition.core.PerrorAttributeDef.i_msg), context.childDecoratedSynthesizedLazy(silver.definition.core.PerrorAttributeDef.i_e, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionStmt)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:definition:core:errorAttributeDef";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "\t" ++ dl.pp ++ "." ++ attr.pp ++ " = " ++ e.pp ++ ";"
		silver.definition.core.PerrorAttributeDef.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("\t")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PerrorAttributeDef.i_dl)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_DefLHS)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(".")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PerrorAttributeDef.i_attr)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QNameAttrOccur)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" = ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PerrorAttributeDef.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)(new common.StringCatter(";")))))))); } };

	}

	public static final common.NodeFactory<PerrorAttributeDef> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PerrorAttributeDef> {

		@Override
		public PerrorAttributeDef invoke(final Object[] children, final Object[] annotations) {
			return new PerrorAttributeDef(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
