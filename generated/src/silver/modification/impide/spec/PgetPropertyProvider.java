
package silver.modification.impide.spec;

public final class PgetPropertyProvider extends common.FunctionNode {

	public static final int i_pkgName = 0;
	public static final int i_propDcls = 1;
	public static final int i_pkgPart = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_spec_getPropertyProvider;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PgetPropertyProvider(final Object c_pkgName, final Object c_propDcls, final Object c_pkgPart) {
		this.child_pkgName = c_pkgName;
		this.child_propDcls = c_propDcls;
		this.child_pkgPart = c_pkgPart;

	}

	private Object child_pkgName;
	public final common.StringCatter getChild_pkgName() {
		return (common.StringCatter) (child_pkgName = common.Util.demand(child_pkgName));
	}

	private Object child_propDcls;
	public final common.ConsCell getChild_propDcls() {
		return (common.ConsCell) (child_propDcls = common.Util.demand(child_propDcls));
	}

	private Object child_pkgPart;
	public final common.StringCatter getChild_pkgPart() {
		return (common.StringCatter) (child_pkgPart = common.Util.demand(child_pkgPart));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_pkgName: return getChild_pkgName();
			case i_propDcls: return getChild_propDcls();
			case i_pkgPart: return getChild_pkgPart();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_pkgName: return child_pkgName;
			case i_propDcls: return child_propDcls;
			case i_pkgPart: return child_pkgPart;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "silver:modification:impide:spec:getPropertyProvider";
	}

	public static common.StringCatter invoke(final Object c_pkgName, final Object c_propDcls, final Object c_pkgPart) {
		try {
		final common.DecoratedNode context = new PgetPropertyProvider(c_pkgName, c_propDcls, c_pkgPart).decorate();
		//"\npackage " ++ pkgName ++ ".eclipse." ++ pkgPart ++ ";\n\nimport java.util.ArrayList;\nimport java.util.List;\n\nimport org.eclipse.swt.widgets.Composite;\n\nimport edu.umn.cs.melt.ide.silver.property.ui.*;\n\npublic class PropertyControlsProvider implements IPropertyControlsProvider {\n\n  private List<PropertyControl> controls;\n\n  @Override\n  public List<PropertyControl> getPropertyControls(Composite panel) {\n    if(controls == null) {\n      controls = new ArrayList<PropertyControl>();\n\n" ++ sflatMap((.controlJavaTranslation), propDcls) ++ "\n    }\n\n    return controls;\n  }\n\n  @Override\n  public boolean validateAll() {\n    boolean valid = true;\n\n    if(controls != null) {\n      for(PropertyControl control : controls) {\n        if(!control.validate()) {\n          valid = false;\n        }\n      }\n    }\n\n    return valid;\n  }\n}\n"
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)(new common.StringCatter("\npackage ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.modification.impide.spec.PgetPropertyProvider.i_pkgName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(".eclipse.")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.modification.impide.spec.PgetPropertyProvider.i_pkgPart)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(";\n\nimport java.util.ArrayList;\nimport java.util.List;\n\nimport org.eclipse.swt.widgets.Composite;\n\nimport edu.umn.cs.melt.ide.silver.property.ui.*;\n\npublic class PropertyControlsProvider implements IPropertyControlsProvider {\n\n  private List<PropertyControl> controls;\n\n  @Override\n  public List<PropertyControl> getPropertyControls(Composite panel) {\n    if(controls == null) {\n      controls = new ArrayList<PropertyControl>();\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.PsflatMap.invoke(new common.AttributeSection.Undecorated(silver.modification.impide.spec.Init.silver_modification_impide_spec_controlJavaTranslation__ON__silver_modification_impide_spec_IdeProperty, context), context.childAsIsLazy(silver.modification.impide.spec.PgetPropertyProvider.i_propDcls))), (common.StringCatter)(new common.StringCatter("\n    }\n\n    return controls;\n  }\n\n  @Override\n  public boolean validateAll() {\n    boolean valid = true;\n\n    if(controls != null) {\n      for(PropertyControl control : controls) {\n        if(!control.validate()) {\n          valid = false;\n        }\n      }\n    }\n\n    return valid;\n  }\n}\n")))))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:impide:spec:getPropertyProvider", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgetPropertyProvider.invoke(children[0], children[1], children[2]);
		}
	};
}