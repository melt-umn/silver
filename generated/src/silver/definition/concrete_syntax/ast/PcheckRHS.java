
package silver.definition.concrete_syntax.ast;

public final class PcheckRHS extends common.FunctionNode {

	public static final int i_pn = 0;
	public static final int i_rhs = 1;
	public static final int i_refs = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_checkRHS;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PcheckRHS(final Object c_pn, final Object c_rhs, final Object c_refs) {
		this.child_pn = c_pn;
		this.child_rhs = c_rhs;
		this.child_refs = c_refs;

	}

	private Object child_pn;
	public final common.StringCatter getChild_pn() {
		return (common.StringCatter) (child_pn = common.Util.demand(child_pn));
	}

	private Object child_rhs;
	public final common.ConsCell getChild_rhs() {
		return (common.ConsCell) (child_rhs = common.Util.demand(child_rhs));
	}

	private Object child_refs;
	public final common.ConsCell getChild_refs() {
		return (common.ConsCell) (child_refs = common.Util.demand(child_refs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_pn: return getChild_pn();
			case i_rhs: return getChild_rhs();
			case i_refs: return getChild_refs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_pn: return child_pn;
			case i_rhs: return child_rhs;
			case i_refs: return child_refs;

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
		return "silver:definition:concrete_syntax:ast:checkRHS";
	}

	public static common.ConsCell invoke(final Object c_pn, final Object c_rhs, final Object c_refs) {
		try {
		final common.DecoratedNode context = new PcheckRHS(c_pn, c_rhs, c_refs).decorate();
		//if null(rhs) then [] else (if length(head(refs)) == 1 then case head(head(refs)) of syntaxNonterminal(_, _) -> [] | syntaxTerminal(_, _, _) -> [] | _ -> [ "parameter " ++ head(rhs).typeName ++ " of production " ++ pn ++ " is not syntax." ] end else [ "Terminal " ++ head(rhs).typeName ++ " was referenced but " ++ "this grammar was not included in this parser. (Referenced from RHS of " ++ pn ++ ")" ]) ++ checkRHS(pn, tail(rhs), tail(refs))
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PcheckRHS.i_rhs))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Phead.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PcheckRHS.i_refs))); } })).equals(Integer.valueOf((int)1)) ? ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2010___fail_2009 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("parameter ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.type.NType)core.Phead.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PcheckRHS.i_rhs))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" of production ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.ast.PcheckRHS.i_pn)), (common.StringCatter)(new common.StringCatter(" is not syntax.")))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.concrete_syntax.ast.PsyntaxNonterminal) { final common.Thunk<Object> __SV_LOCAL___pv2017___sv_tmp_pv_2018 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv2019___sv_tmp_pv_2020 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (common.ConsCell)((common.ConsCell)core.Pnil.invoke()); }
else if(scrutineeNode instanceof silver.definition.concrete_syntax.ast.PsyntaxTerminal) { final common.Thunk<Object> __SV_LOCAL___pv2011___sv_tmp_pv_2012 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv2013___sv_tmp_pv_2014 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
final common.Thunk<Object> __SV_LOCAL___pv2015___sv_tmp_pv_2016 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
 return (common.ConsCell)((common.ConsCell)core.Pnil.invoke()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_2010___fail_2009.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Phead.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PcheckRHS.i_refs))); } }))); } }).eval()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Terminal ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.type.NType)core.Phead.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PcheckRHS.i_rhs))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" was referenced but ")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("this grammar was not included in this parser. (Referenced from RHS of ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.ast.PcheckRHS.i_pn)), (common.StringCatter)(new common.StringCatter(")"))))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.concrete_syntax.ast.PcheckRHS.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PcheckRHS.i_pn), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PcheckRHS.i_rhs))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PcheckRHS.i_refs))); } })); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:concrete_syntax:ast:checkRHS", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcheckRHS.invoke(children[0], children[1], children[2]);
		}
	};
}