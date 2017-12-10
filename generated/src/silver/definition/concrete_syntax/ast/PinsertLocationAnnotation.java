
package silver.definition.concrete_syntax.ast;

public final class PinsertLocationAnnotation extends common.FunctionNode {

	public static final int i_ns = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_insertLocationAnnotation;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PinsertLocationAnnotation(final Object c_ns) {
		this.child_ns = c_ns;

	}

	private Object child_ns;
	public final common.DecoratedNode getChild_ns() {
		return (common.DecoratedNode) (child_ns = common.Util.demand(child_ns));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ns: return getChild_ns();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ns: return child_ns;

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
		return "silver:definition:concrete_syntax:ast:insertLocationAnnotation";
	}

	public static common.StringCatter invoke(final Object c_ns) {
		try {
		final common.DecoratedNode context = new PinsertLocationAnnotation(c_ns).decorate();
		//if null(ns.namedInputElements) then "" else if length(ns.namedInputElements) > 1 then pfx ++ "multiple_annotation_problem" else if head(ns.namedInputElements).elementName != "core:location" then pfx ++ "unknown_annotation_type_problem" else pfx ++ "common.Terminal.createSpan(_children, virtualLocation, (int)_pos.getPos())"
		return (common.StringCatter)((((Boolean)core.Pnull.invoke(context.childAsIsSynthesizedLazy(silver.definition.concrete_syntax.ast.PinsertLocationAnnotation.i_ns, silver.definition.env.Init.silver_definition_env_namedInputElements__ON__silver_definition_env_NamedSignature))) ? (new common.StringCatter("")) : ((((Integer)core.PlistLength.invoke(context.childAsIsSynthesizedLazy(silver.definition.concrete_syntax.ast.PinsertLocationAnnotation.i_ns, silver.definition.env.Init.silver_definition_env_namedInputElements__ON__silver_definition_env_NamedSignature))) > Integer.valueOf((int)1)) ? new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.definition.concrete_syntax.ast.Init.pfx__ON__silver_definition_concrete_syntax_ast_insertLocationAnnotation)), (common.StringCatter)(new common.StringCatter("multiple_annotation_problem"))) : (!((common.StringCatter)((silver.definition.env.NNamedSignatureElement)core.Phead.invoke(context.childAsIsSynthesizedLazy(silver.definition.concrete_syntax.ast.PinsertLocationAnnotation.i_ns, silver.definition.env.Init.silver_definition_env_namedInputElements__ON__silver_definition_env_NamedSignature))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement)).equals((new common.StringCatter("core:location"))) ? new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.definition.concrete_syntax.ast.Init.pfx__ON__silver_definition_concrete_syntax_ast_insertLocationAnnotation)), (common.StringCatter)(new common.StringCatter("unknown_annotation_type_problem"))) : new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.definition.concrete_syntax.ast.Init.pfx__ON__silver_definition_concrete_syntax_ast_insertLocationAnnotation)), (common.StringCatter)(new common.StringCatter("common.Terminal.createSpan(_children, virtualLocation, (int)_pos.getPos())")))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:concrete_syntax:ast:insertLocationAnnotation", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PinsertLocationAnnotation.invoke(children[0]);
		}
	};
}