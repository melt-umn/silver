
package silver.modification.impide.cstast;

public final class PdumbExtractFont extends common.FunctionNode {

	public static final int i_d = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_cstast_dumbExtractFont;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PdumbExtractFont(final Object c_d) {
		this.child_d = c_d;

	}

	private Object child_d;
	public final common.ConsCell getChild_d() {
		return (common.ConsCell) (child_d = common.Util.demand(child_d));
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
		return "silver:modification:impide:cstast:dumbExtractFont";
	}

	public static common.StringCatter invoke(final Object c_d) {
		try {
		final common.DecoratedNode context = new PdumbExtractFont(c_d).decorate();
		//if null(d) then "" else case d of syntaxLexerClass(_, mods)::rest -> if mods.fontAttr != "" then mods.fontAttr else dumbExtractFont(rest) | _ -> error("We should only have lexer classes here") end
		return (common.StringCatter)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.modification.impide.cstast.PdumbExtractFont.i_d))) ? (new common.StringCatter("")) : ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10731___fail_10732 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("We should only have lexer classes here")))); } };
return new common.PatternLazy<common.ConsCell, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_10734___sv_tmp_pv_10733 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_10735___sv_pv_10736_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return new common.PatternLazy<common.DecoratedNode, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.concrete_syntax.ast.PsyntaxLexerClass) { final common.Thunk<Object> __SV_LOCAL___pv10741___sv_tmp_pv_10742 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv10743___sv_pv_10744_mods = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (common.StringCatter)((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10745_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_10735___sv_pv_10736_rest.eval())); } };
return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10746_mods = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv10743___sv_pv_10744_mods.eval())); } };
return (!((common.StringCatter)((common.DecoratedNode)(__SV_LOCAL_10746_mods.eval())).synthesized(silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttr__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers)).equals((new common.StringCatter(""))) ? ((common.StringCatter)((common.DecoratedNode)(__SV_LOCAL_10746_mods.eval())).synthesized(silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttr__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers)) : ((common.StringCatter)silver.modification.impide.cstast.PdumbExtractFont.invoke(__SV_LOCAL_10745_rest))); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.StringCatter)(__SV_LOCAL_10731___fail_10732.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL_10734___sv_tmp_pv_10733.eval()))); }return ((common.StringCatter)(__SV_LOCAL_10731___fail_10732.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)context.childAsIs(silver.modification.impide.cstast.PdumbExtractFont.i_d))); } }).eval())));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:impide:cstast:dumbExtractFont", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PdumbExtractFont.invoke(children[0]);
		}
	};
}