
package silver.definition.concrete_syntax.ast;

// top::SyntaxDcl ::= n::String ty::Type acode::String 
public final class PsyntaxParserAttribute extends silver.definition.concrete_syntax.ast.NSyntaxDcl {

	public static final int i_n = 0;
	public static final int i_ty = 1;
	public static final int i_acode = 2;


	public static final Class<?> childTypes[] = {common.StringCatter.class,silver.definition.type.NType.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_syntaxParserAttribute;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_ty] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	}

	public PsyntaxParserAttribute(final Object c_n, final Object c_ty, final Object c_acode) {
		super();
		this.child_n = c_n;
		this.child_ty = c_ty;
		this.child_acode = c_acode;

	}

	private Object child_n;
	public final common.StringCatter getChild_n() {
		return (common.StringCatter) (child_n = common.Util.demand(child_n));
	}

	private Object child_ty;
	public final silver.definition.type.NType getChild_ty() {
		return (silver.definition.type.NType) (child_ty = common.Util.demand(child_ty));
	}

	private Object child_acode;
	public final common.StringCatter getChild_acode() {
		return (common.StringCatter) (child_acode = common.Util.demand(child_acode));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();
			case i_ty: return getChild_ty();
			case i_acode: return getChild_acode();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;
			case i_ty: return child_ty;
			case i_acode: return child_acode;

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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:syntaxParserAttribute erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:syntaxParserAttribute";
	}

	static void initProductionAttributeDefinitions() {
		// top.sortKey = "BBB" ++ n
		silver.definition.concrete_syntax.ast.PsyntaxParserAttribute.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_sortKey__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("BBB")), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.ast.PsyntaxParserAttribute.i_n))); } };
		// top.cstDcls = [ pair(n, top) ]
		silver.definition.concrete_syntax.ast.PsyntaxParserAttribute.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstDcls__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PsyntaxParserAttribute.i_n), context)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.cstErrors := if length(searchEnvTree(n, top.cstEnv)) == 1 then [] else [ "Name conflict with parser attribute " ++ n ]
		if(silver.definition.concrete_syntax.ast.PsyntaxParserAttribute.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] == null)
			silver.definition.concrete_syntax.ast.PsyntaxParserAttribute.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new silver.definition.concrete_syntax.ast.CAcstErrors(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl);
		((common.CollectionAttribute)silver.definition.concrete_syntax.ast.PsyntaxParserAttribute.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PsearchEnvTree.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PsyntaxParserAttribute.i_n), context.contextInheritedLazy(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_SyntaxDcl))); } })).equals(Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Name conflict with parser attribute ")), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.ast.PsyntaxParserAttribute.i_n))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// top.cstNormalize = [ top ]
		silver.definition.concrete_syntax.ast.PsyntaxParserAttribute.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstNormalize__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.undecorate(), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.xmlCopper = "  <ParserAttribute id=\"" ++ makeCopperName(n) ++ "\">\n" ++ "    <Type><![CDATA[" ++ ty.transType ++ "]]></Type>\n" ++ "    <Code><![CDATA[\n" ++ acode ++ "]]></Code>\n" ++ "  </ParserAttribute>\n"
		silver.definition.concrete_syntax.ast.PsyntaxParserAttribute.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("  <ParserAttribute id=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.concrete_syntax.ast.PmakeCopperName.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PsyntaxParserAttribute.i_n))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\">\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <Type><![CDATA[")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxParserAttribute.i_ty).synthesized(silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("]]></Type>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <Code><![CDATA[\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.ast.PsyntaxParserAttribute.i_acode)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("]]></Code>\n")), (common.StringCatter)(new common.StringCatter("  </ParserAttribute>\n"))))))))))); } };
		// ty.boundVariables = []
		silver.definition.concrete_syntax.ast.PsyntaxParserAttribute.childInheritedAttributes[silver.definition.concrete_syntax.ast.PsyntaxParserAttribute.i_ty][silver.definition.type.Init.silver_definition_type_boundVariables__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.unparses = [ "pattr('" ++ n ++ "', " ++ ty.unparse ++ ",\"" ++ escapeString(acode) ++ "\")" ]
		silver.definition.concrete_syntax.ast.PsyntaxParserAttribute.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("pattr('")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.ast.PsyntaxParserAttribute.i_n)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("', ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxParserAttribute.i_ty).synthesized(silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_type_Type)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(",\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PescapeString.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PsyntaxParserAttribute.i_acode))), (common.StringCatter)(new common.StringCatter("\")")))))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PsyntaxParserAttribute> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PsyntaxParserAttribute> {

		@Override
		public PsyntaxParserAttribute invoke(final Object[] children, final Object[] annotations) {
			return new PsyntaxParserAttribute(children[0], children[1], children[2]);
		}
	};

}
