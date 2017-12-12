
package silver.modification.impide;

// top::DriverAction ::= grams::EnvTree<Decorated RootSpec> ide::IdeSpec ideGenPath::String pkgName::String 
public final class PgenerateNCS extends silver.driver.util.NDriverAction {

	public static final int i_grams = 0;
	public static final int i_ide = 1;
	public static final int i_ideGenPath = 2;
	public static final int i_pkgName = 3;


	public static final Class<?> childTypes[] = {Object.class,silver.modification.impide.spec.NIdeSpec.class,common.StringCatter.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_generateNCS;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.driver.util.NDriverAction.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.driver.util.NDriverAction.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_ide] = new common.Lazy[silver.modification.impide.spec.NIdeSpec.num_inh_attrs];

	}

	public PgenerateNCS(final Object c_grams, final Object c_ide, final Object c_ideGenPath, final Object c_pkgName) {
		super();
		this.child_grams = c_grams;
		this.child_ide = c_ide;
		this.child_ideGenPath = c_ideGenPath;
		this.child_pkgName = c_pkgName;

	}

	private Object child_grams;
	public final Object getChild_grams() {
		return (Object) (child_grams = common.Util.demand(child_grams));
	}

	private Object child_ide;
	public final silver.modification.impide.spec.NIdeSpec getChild_ide() {
		return (silver.modification.impide.spec.NIdeSpec) (child_ide = common.Util.demand(child_ide));
	}

	private Object child_ideGenPath;
	public final common.StringCatter getChild_ideGenPath() {
		return (common.StringCatter) (child_ideGenPath = common.Util.demand(child_ideGenPath));
	}

	private Object child_pkgName;
	public final common.StringCatter getChild_pkgName() {
		return (common.StringCatter) (child_pkgName = common.Util.demand(child_pkgName));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_grams: return getChild_grams();
			case i_ide: return getChild_ide();
			case i_ideGenPath: return getChild_ideGenPath();
			case i_pkgName: return getChild_pkgName();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_grams: return child_grams;
			case i_ide: return child_ide;
			case i_ideGenPath: return child_ideGenPath;
			case i_pkgName: return child_pkgName;

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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:generateNCS erroneously claimed to forward");
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
		return "silver:modification:impide:generateNCS";
	}

	static void initProductionAttributeDefinitions() {
		// ide.compiledGrammars = grams
		silver.modification.impide.PgenerateNCS.childInheritedAttributes[silver.modification.impide.PgenerateNCS.i_ide][silver.modification.impide.spec.Init.silver_definition_env_compiledGrammars__ON__silver_modification_impide_spec_IdeSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childAsIs(silver.modification.impide.PgenerateNCS.i_grams)); } };
		// io0 = print("[IDE plugin] Generating IDE plugin.\n", top.ioIn)
		silver.modification.impide.PgenerateNCS.localAttributes[silver.modification.impide.Init.io0__ON__silver_modification_impide_generateNCS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.Pprint.invoke((new common.StringCatter("[IDE plugin] Generating IDE plugin.\n")), context.contextInheritedLazy(silver.driver.util.Init.silver_driver_util_ioIn__ON__silver_driver_util_DriverAction))); } };
		// io1 = deleteTree(ideGenPath, io0)
		silver.modification.impide.PgenerateNCS.localAttributes[silver.modification.impide.Init.io1__ON__silver_modification_impide_generateNCS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.PdeleteTree.invoke(context.childAsIsLazy(silver.modification.impide.PgenerateNCS.i_ideGenPath), context.localAsIsLazy(silver.modification.impide.Init.io0__ON__silver_modification_impide_generateNCS))); } };
		// io2 = mkdirs(ideGenPath ++ "/plugin/src/" ++ pkgToPath(pkgName) ++ "/", [ "imp/coloring", "eclipse/property", "eclipse/wizard/newproject", "eclipse/wizard/newfile" ], io1)
		silver.modification.impide.PgenerateNCS.localAttributes[silver.modification.impide.Init.io2__ON__silver_modification_impide_generateNCS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)silver.modification.impide.Pmkdirs.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.modification.impide.PgenerateNCS.i_ideGenPath)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("/plugin/src/")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.modification.impide.PpkgToPath.invoke(context.childAsIsLazy(silver.modification.impide.PgenerateNCS.i_pkgName))), (common.StringCatter)(new common.StringCatter("/"))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("imp/coloring")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("eclipse/property")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("eclipse/wizard/newproject")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("eclipse/wizard/newfile")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } }, context.localAsIsLazy(silver.modification.impide.Init.io1__ON__silver_modification_impide_generateNCS))); } };
		// io3 = writeFiles(ideGenPath ++ "/plugin/", ide.pluginFiles, io2)
		silver.modification.impide.PgenerateNCS.localAttributes[silver.modification.impide.Init.io3__ON__silver_modification_impide_generateNCS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)silver.translation.java.driver.PwriteFiles.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.modification.impide.PgenerateNCS.i_ideGenPath)), (common.StringCatter)(new common.StringCatter("/plugin/"))); } }, context.childDecoratedSynthesizedLazy(silver.modification.impide.PgenerateNCS.i_ide, silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginFiles__ON__silver_modification_impide_spec_IdeSpec), context.localAsIsLazy(silver.modification.impide.Init.io2__ON__silver_modification_impide_generateNCS))); } };
		// top.io = io3
		silver.modification.impide.PgenerateNCS.synthesizedAttributes[silver.driver.util.Init.core_io__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.localAsIs(silver.modification.impide.Init.io3__ON__silver_modification_impide_generateNCS)); } };
		// top.code = 0
		silver.modification.impide.PgenerateNCS.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_code__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)0); } };
		// top.order = 7
		silver.modification.impide.PgenerateNCS.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_order__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)7); } };

	}

	public static final common.NodeFactory<PgenerateNCS> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgenerateNCS> {

		@Override
		public PgenerateNCS invoke(final Object[] children, final Object[] annotations) {
			return new PgenerateNCS(children[0], children[1], children[2], children[3]);
		}
	};

}
