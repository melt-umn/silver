
package silver.modification.copper;

public final class Phacklocaldeclarations extends common.FunctionNode {

	public static final int i_d = 0;


	public static final Class<?> childTypes[] = { silver.definition.env.NDef.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_hacklocaldeclarations;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_d] = new common.Lazy[silver.definition.env.NDef.num_inh_attrs];

	}

	public Phacklocaldeclarations(final Object c_d) {
		this.child_d = c_d;

	}

	private Object child_d;
	public final silver.definition.env.NDef getChild_d() {
		return (silver.definition.env.NDef) (child_d = common.Util.demand(child_d));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_d: return getChild_d();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_d: return child_d;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return "silver:modification:copper:hacklocaldeclarations";
	}

	public static common.StringCatter invoke(final Object c_d) {
		try {
		final common.DecoratedNode context = new Phacklocaldeclarations(c_d).decorate();
		//d.dcl.typerep.transType ++ " " ++ makeCopperName(d.dcl.fullName) ++ ";\n"
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.type.NType)((silver.definition.env.NDclInfo)context.childDecorated(silver.modification.copper.Phacklocaldeclarations.i_d).synthesized(silver.definition.env.Init.silver_definition_env_dcl__ON__silver_definition_env_Def)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_DclInfo)).decorate(context, (common.Lazy[])null).synthesized(silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.concrete_syntax.ast.PmakeCopperName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NDclInfo)context.childDecorated(silver.modification.copper.Phacklocaldeclarations.i_d).synthesized(silver.definition.env.Init.silver_definition_env_dcl__ON__silver_definition_env_Def)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_DclInfo)); } })), (common.StringCatter)(new common.StringCatter(";\n"))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:copper:hacklocaldeclarations", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Phacklocaldeclarations.invoke(children[0]);
		}
	};
}