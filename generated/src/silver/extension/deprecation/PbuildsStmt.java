
package silver.extension.deprecation;

// top::ModuleStmt ::= 'build' m::QName 'with' c::QName ';' 
public final class PbuildsStmt extends silver.definition.core.NModuleStmt {

	public static final int i__G_4 = 0;
	public static final int i_m = 1;
	public static final int i__G_2 = 2;
	public static final int i_c = 3;
	public static final int i__G_0 = 4;


	public static final Class<?> childTypes[] = {silver.extension.deprecation.TBuild_kwd.class,silver.definition.core.NQName.class,silver.definition.core.TWith_kwd.class,silver.definition.core.NQName.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_deprecation_buildsStmt;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NModuleStmt.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NModuleStmt.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_m] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_c] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PbuildsStmt(final Object c__G_4, final Object c_m, final Object c__G_2, final Object c_c, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_4 = c__G_4;
		this.child_m = c_m;
		this.child__G_2 = c__G_2;
		this.child_c = c_c;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_4;
	public final silver.extension.deprecation.TBuild_kwd getChild__G_4() {
		return (silver.extension.deprecation.TBuild_kwd) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_m;
	public final silver.definition.core.NQName getChild_m() {
		return (silver.definition.core.NQName) (child_m = common.Util.demand(child_m));
	}

	private Object child__G_2;
	public final silver.definition.core.TWith_kwd getChild__G_2() {
		return (silver.definition.core.TWith_kwd) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_c;
	public final silver.definition.core.NQName getChild_c() {
		return (silver.definition.core.NQName) (child_c = common.Util.demand(child_c));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_4: return getChild__G_4();
			case i_m: return getChild_m();
			case i__G_2: return getChild__G_2();
			case i_c: return getChild_c();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_4: return child__G_4;
			case i_m: return child_m;
			case i__G_2: return child__G_2;
			case i_c: return child_c;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
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
		return ((silver.definition.core.NModuleStmt)new silver.definition.core.PexportsWithStmt(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TExports_kwd((new common.StringCatter("exports")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.deprecation.PbuildsStmt.i_m)), context.childAsIsLazy(silver.extension.deprecation.PbuildsStmt.i__G_2), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.deprecation.PbuildsStmt.i_c)), context.childAsIsLazy(silver.extension.deprecation.PbuildsStmt.i__G_0), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NModuleStmt)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:deprecation:buildsStmt";
	}

	static void initProductionAttributeDefinitions() {
		// top.errors <- [ wrn(top.location, "Conditional export using old-style 'build ... with' rather than 'exports ... with'") ]
		if(silver.extension.deprecation.PbuildsStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleStmt] == null)
			silver.extension.deprecation.PbuildsStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleStmt] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleStmt);
		((common.CollectionAttribute)silver.extension.deprecation.PbuildsStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleStmt]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Pwrn(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NModuleStmt)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("Conditional export using old-style 'build ... with' rather than 'exports ... with'")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });

	}

	public static final common.NodeFactory<PbuildsStmt> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PbuildsStmt> {

		@Override
		public PbuildsStmt invoke(final Object[] children, final Object[] annotations) {
			return new PbuildsStmt(children[0], children[1], children[2], children[3], children[4], annotations[0]);
		}
	};

}
