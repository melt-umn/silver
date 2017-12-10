
package silver.definition.core;

public final class Pdclinfo2possibility extends common.FunctionNode {

	public static final int i_dcl = 0;


	public static final Class<?> childTypes[] = { silver.definition.env.NDclInfo.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_dclinfo2possibility;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_dcl] = new common.Lazy[silver.definition.env.NDclInfo.num_inh_attrs];

	}

	public Pdclinfo2possibility(final Object c_dcl) {
		this.child_dcl = c_dcl;

	}

	private Object child_dcl;
	public final silver.definition.env.NDclInfo getChild_dcl() {
		return (silver.definition.env.NDclInfo) (child_dcl = common.Util.demand(child_dcl));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_dcl: return getChild_dcl();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_dcl: return child_dcl;

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
		return "silver:definition:core:dclinfo2possibility";
	}

	public static common.StringCatter invoke(final Object c_dcl) {
		try {
		final common.DecoratedNode context = new Pdclinfo2possibility(c_dcl).decorate();
		//"\t" ++ dcl.fullName ++ " (" ++ dcl.sourceLocation.filename ++ ":" ++ toString(dcl.sourceLocation.line) ++ ")"
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)(new common.StringCatter("\t")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.Pdclinfo2possibility.i_dcl).synthesized(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_DclInfo)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" (")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((core.NLocation)context.childDecorated(silver.definition.core.Pdclinfo2possibility.i_dcl).synthesized(silver.definition.env.Init.silver_definition_env_sourceLocation__ON__silver_definition_env_DclInfo)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_filename__ON__core_Location)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)((core.NLocation)context.childDecorated(silver.definition.core.Pdclinfo2possibility.i_dcl).synthesized(silver.definition.env.Init.silver_definition_env_sourceLocation__ON__silver_definition_env_DclInfo)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_line__ON__core_Location)))), (common.StringCatter)(new common.StringCatter(")")))))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:core:dclinfo2possibility", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pdclinfo2possibility.invoke(children[0]);
		}
	};
}