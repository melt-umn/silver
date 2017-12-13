
package silver.modification.impide.spec;

// top::IdeFunction ::= fName::String 
public final class PfolderFunction extends silver.modification.impide.spec.NIdeFunction {

	public static final int i_fName = 0;


	public static final Class<?> childTypes[] = {common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_spec_folderFunction;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.impide.spec.NIdeFunction.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.impide.spec.NIdeFunction.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PfolderFunction(final Object c_fName) {
		super();
		this.child_fName = c_fName;

	}

	private Object child_fName;
	public final common.StringCatter getChild_fName() {
		return (common.StringCatter) (child_fName = common.Util.demand(child_fName));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_fName: return getChild_fName();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_fName: return child_fName;

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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:spec:folderFunction erroneously claimed to forward");
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
		return "silver:modification:impide:spec:folderFunction";
	}

	static void initProductionAttributeDefinitions() {
		// top.svIdeInterface = "\n\t@Override\n\tpublic ConsCell getFolds(Node root) {\n\t\treturn (ConsCell)" ++ makeClassName(fName) ++ ".invoke(root);\n\t}\n"
		silver.modification.impide.spec.PfolderFunction.synthesizedAttributes[silver.modification.impide.spec.Init.silver_modification_impide_spec_svIdeInterface__ON__silver_modification_impide_spec_IdeFunction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\t@Override\n\tpublic ConsCell getFolds(Node root) {\n\t\treturn (ConsCell)")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.translation.java.core.PmakeClassName.invoke(context.childAsIsLazy(silver.modification.impide.spec.PfolderFunction.i_fName))), (common.StringCatter)(new common.StringCatter(".invoke(root);\n\t}\n")))); } };
		// top.pluginXml = "\n<extension point=\"org.eclipse.imp.runtime.foldingUpdater\">\n  <foldingUpdater\n      class=\"edu.umn.cs.melt.ide.imp.services.FoldingProvider\"\n      language=\"" ++ top.implang ++ "\">\n  </foldingUpdater>\n</extension>\n"
		silver.modification.impide.spec.PfolderFunction.synthesizedAttributes[silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginXml__ON__silver_modification_impide_spec_IdeFunction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("\n<extension point=\"org.eclipse.imp.runtime.foldingUpdater\">\n  <foldingUpdater\n      class=\"edu.umn.cs.melt.ide.imp.services.FoldingProvider\"\n      language=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.modification.impide.spec.Init.silver_modification_impide_spec_implang__ON__silver_modification_impide_spec_IdeFunction)), (common.StringCatter)(new common.StringCatter("\">\n  </foldingUpdater>\n</extension>\n")))); } };

	}

	public static final common.NodeFactory<PfolderFunction> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PfolderFunction> {

		@Override
		public PfolderFunction invoke(final Object[] children, final Object[] annotations) {
			return new PfolderFunction(children[0]);
		}
	};

}
