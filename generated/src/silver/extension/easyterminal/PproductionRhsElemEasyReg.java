
package silver.extension.easyterminal;

// top::ProductionRHSElem ::= id::Name '::' reg::EasyTerminalRef 
public final class PproductionRhsElemEasyReg extends silver.definition.core.NProductionRHSElem {

	public static final int i_id = 0;
	public static final int i__G_1 = 1;
	public static final int i_reg = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.NName.class,silver.definition.core.TColonColon_t.class,silver.extension.easyterminal.NEasyTerminalRef.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_easyterminal_productionRhsElemEasyReg;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NProductionRHSElem.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NProductionRHSElem.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_reg] = new common.Lazy[silver.extension.easyterminal.NEasyTerminalRef.num_inh_attrs];

	}

	public PproductionRhsElemEasyReg(final Object c_id, final Object c__G_1, final Object c_reg, final Object a_core_location) {
		super(a_core_location);
		this.child_id = c_id;
		this.child__G_1 = c__G_1;
		this.child_reg = c_reg;

	}

	private Object child_id;
	public final silver.definition.core.NName getChild_id() {
		return (silver.definition.core.NName) (child_id = common.Util.demand(child_id));
	}

	private Object child__G_1;
	public final silver.definition.core.TColonColon_t getChild__G_1() {
		return (silver.definition.core.TColonColon_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_reg;
	public final silver.extension.easyterminal.NEasyTerminalRef getChild_reg() {
		return (silver.extension.easyterminal.NEasyTerminalRef) (child_reg = common.Util.demand(child_reg));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_id: return getChild_id();
			case i__G_1: return getChild__G_1();
			case i_reg: return getChild_reg();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_id: return child_id;
			case i__G_1: return child__G_1;
			case i_reg: return child_reg;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return ((silver.definition.core.NProductionRHSElem)new silver.definition.core.PproductionRHSElem(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.easyterminal.PproductionRhsElemEasyReg.i_id)), context.childAsIsLazy(silver.extension.easyterminal.PproductionRhsElemEasyReg.i__G_1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.syntax.NTypeExpr)new silver.definition.type.syntax.PtyperepTypeExpr(context.childDecoratedSynthesizedLazy(silver.extension.easyterminal.PproductionRhsElemEasyReg.i_reg, silver.extension.easyterminal.Init.silver_definition_env_typerep__ON__silver_extension_easyterminal_EasyTerminalRef), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.easyterminal.NEasyTerminalRef)context.childDecorated(silver.extension.easyterminal.PproductionRhsElemEasyReg.i_reg).undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionRHSElem)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:easyterminal:productionRhsElemEasyReg";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = id.pp ++ "::" ++ reg.pp
		silver.extension.easyterminal.PproductionRhsElemEasyReg.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionRHSElem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.easyterminal.PproductionRhsElemEasyReg.i_id).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Name)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("::")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.easyterminal.PproductionRhsElemEasyReg.i_reg).synthesized(silver.extension.easyterminal.Init.silver_definition_env_pp__ON__silver_extension_easyterminal_EasyTerminalRef)))); } };
		// top.errors <- reg.errors
		if(silver.extension.easyterminal.PproductionRhsElemEasyReg.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionRHSElem] == null)
			silver.extension.easyterminal.PproductionRhsElemEasyReg.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionRHSElem] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionRHSElem);
		((common.CollectionAttribute)silver.extension.easyterminal.PproductionRhsElemEasyReg.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionRHSElem]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.extension.easyterminal.PproductionRhsElemEasyReg.i_reg).synthesized(silver.extension.easyterminal.Init.silver_definition_core_errors__ON__silver_extension_easyterminal_EasyTerminalRef)); } });

	}

	public static final common.NodeFactory<PproductionRhsElemEasyReg> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PproductionRhsElemEasyReg> {

		@Override
		public PproductionRhsElemEasyReg invoke(final Object[] children, final Object[] annotations) {
			return new PproductionRhsElemEasyReg(children[0], children[1], children[2], annotations[0]);
		}
	};

}
