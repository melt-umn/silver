
package silver.definition.concrete_syntax.ast;

public final class PxmlCopperRef extends common.FunctionNode {

	public static final int i_d = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_xmlCopperRef;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PxmlCopperRef(final Object c_d) {
		this.child_d = c_d;

	}

	private Object child_d;
	public final common.DecoratedNode getChild_d() {
		return (common.DecoratedNode) (child_d = common.Util.demand(child_d));
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
		return "silver:definition:concrete_syntax:ast:xmlCopperRef";
	}

	public static common.StringCatter invoke(final Object c_d) {
		try {
		final common.DecoratedNode context = new PxmlCopperRef(c_d).decorate();
		//case d of syntaxLexerClass(n, _) -> "<TerminalClassRef id=\"" ++ makeCopperName(n) ++ "\" grammar=\"" ++ d.containingGrammar ++ "\" />" | syntaxTerminal(n, _, _) -> "<TerminalRef id=\"" ++ makeCopperName(n) ++ "\" grammar=\"" ++ d.containingGrammar ++ "\" />" | syntaxNonterminal(n, _) -> "<NonterminalRef id=\"" ++ makeCopperName(n.typeName) ++ "\" grammar=\"" ++ d.containingGrammar ++ "\" />" | syntaxProduction(ns, _) -> "<ProductionRef id=\"" ++ makeCopperName(ns.fullName) ++ "\" grammar=\"" ++ d.containingGrammar ++ "\" />" end
		return (common.StringCatter)(new common.PatternLazy<common.DecoratedNode, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.concrete_syntax.ast.PsyntaxLexerClass) { final common.Thunk<Object> __SV_LOCAL___pv2058___sv_pv_2057_n = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv2059___sv_tmp_pv_2060 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (common.StringCatter)((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2061_n = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv2058___sv_pv_2057_n.eval())); } };
return new common.StringCatter((common.StringCatter)(new common.StringCatter("<TerminalClassRef id=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.concrete_syntax.ast.PmakeCopperName.invoke(__SV_LOCAL_2061_n)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\" grammar=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.concrete_syntax.ast.PxmlCopperRef.i_d)).inherited(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_containingGrammar__ON__silver_definition_concrete_syntax_ast_SyntaxDcl)), (common.StringCatter)(new common.StringCatter("\" />")))))); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.concrete_syntax.ast.PsyntaxNonterminal) { final common.Thunk<Object> __SV_LOCAL___pv2063___sv_pv_2062_n = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv2064___sv_tmp_pv_2065 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (common.StringCatter)((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2066_n = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv2063___sv_pv_2062_n.eval())); } };
return new common.StringCatter((common.StringCatter)(new common.StringCatter("<NonterminalRef id=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.concrete_syntax.ast.PmakeCopperName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)(__SV_LOCAL_2066_n.eval())).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\" grammar=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.concrete_syntax.ast.PxmlCopperRef.i_d)).inherited(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_containingGrammar__ON__silver_definition_concrete_syntax_ast_SyntaxDcl)), (common.StringCatter)(new common.StringCatter("\" />")))))); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.concrete_syntax.ast.PsyntaxProduction) { final common.Thunk<Object> __SV_LOCAL___pv2073___sv_pv_2072_ns = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv2074___sv_tmp_pv_2075 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (common.StringCatter)((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2076_ns = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv2073___sv_pv_2072_ns.eval())); } };
return new common.StringCatter((common.StringCatter)(new common.StringCatter("<ProductionRef id=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.concrete_syntax.ast.PmakeCopperName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)(__SV_LOCAL_2076_ns.eval())).synthesized(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature)); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\" grammar=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.concrete_syntax.ast.PxmlCopperRef.i_d)).inherited(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_containingGrammar__ON__silver_definition_concrete_syntax_ast_SyntaxDcl)), (common.StringCatter)(new common.StringCatter("\" />")))))); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.concrete_syntax.ast.PsyntaxTerminal) { final common.Thunk<Object> __SV_LOCAL___pv2081___sv_pv_2080_n = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv2082___sv_tmp_pv_2083 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
final common.Thunk<Object> __SV_LOCAL___pv2084___sv_tmp_pv_2085 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
 return (common.StringCatter)((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2086_n = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv2081___sv_pv_2080_n.eval())); } };
return new common.StringCatter((common.StringCatter)(new common.StringCatter("<TerminalRef id=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.concrete_syntax.ast.PmakeCopperName.invoke(__SV_LOCAL_2086_n)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\" grammar=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.concrete_syntax.ast.PxmlCopperRef.i_d)).inherited(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_containingGrammar__ON__silver_definition_concrete_syntax_ast_SyntaxDcl)), (common.StringCatter)(new common.StringCatter("\" />")))))); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:definition:concrete_syntax:ast 'Syntax.sv', 362, 9, 367, 5, 14115, 14662\n"))));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.definition.concrete_syntax.ast.PxmlCopperRef.i_d))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:concrete_syntax:ast:xmlCopperRef", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PxmlCopperRef.invoke(children[0]);
		}
	};
}