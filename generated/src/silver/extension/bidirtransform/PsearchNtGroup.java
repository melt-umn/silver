
package silver.extension.bidirtransform;

public final class PsearchNtGroup extends common.FunctionNode {

	public static final int i_fnnt = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_searchNtGroup;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PsearchNtGroup(final Object c_fnnt, final Object c_e) {
		this.child_fnnt = c_fnnt;
		this.child_e = c_e;

	}

	private Object child_fnnt;
	public final common.StringCatter getChild_fnnt() {
		return (common.StringCatter) (child_fnnt = common.Util.demand(child_fnnt));
	}

	private Object child_e;
	public final common.DecoratedNode getChild_e() {
		return (common.DecoratedNode) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_fnnt: return getChild_fnnt();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_fnnt: return child_fnnt;
			case i_e: return child_e;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return "silver:extension:bidirtransform:searchNtGroup";
	}

	public static common.ConsCell invoke(final Object c_fnnt, final Object c_e) {
		try {
		final common.DecoratedNode context = new PsearchNtGroup(c_fnnt, c_e).decorate();
		//case e of i_emptyEnv() -> [] | i_appendEnv(e2, e3) -> searchNtGroup(fnnt, e2) ++ searchNtGroup(fnnt, e3) | i_newScopeEnv(dfs, e2) -> searchNtGroup(fnnt, e2) ++ defsNtGroup(fnnt, dfs) end
		return (common.ConsCell)(new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.env.Pi_appendEnv) { final common.Thunk<Object> __SV_LOCAL___pv13586___sv_pv_13587_e2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13588___sv_pv_13585_e3 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childAsIs(1); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13589_e3 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13588___sv_pv_13585_e3.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13590_e2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13586___sv_pv_13587_e2.eval())); } };
return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.bidirtransform.PsearchNtGroup.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PsearchNtGroup.i_fnnt), __SV_LOCAL_13590_e2)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.bidirtransform.PsearchNtGroup.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PsearchNtGroup.i_fnnt), __SV_LOCAL_13589_e3)); } })); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.env.Pi_emptyEnv) {  return (common.ConsCell)((common.ConsCell)core.Pnil.invoke()); }
else if(scrutineeNode instanceof silver.definition.env.Pi_newScopeEnv) { final common.Thunk<Object> __SV_LOCAL___pv13592___sv_pv_13593_dfs = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13594___sv_pv_13591_e2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childAsIs(1); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13595_e2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13594___sv_pv_13591_e2.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13596_dfs = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13592___sv_pv_13593_dfs.eval())); } };
return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.bidirtransform.PsearchNtGroup.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PsearchNtGroup.i_fnnt), __SV_LOCAL_13595_e2)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.bidirtransform.PdefsNtGroup.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PsearchNtGroup.i_fnnt), common.Thunk.transformUndecorate(__SV_LOCAL_13596_dfs))); } })); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:bidirtransform 'HelpLookup.sv', 23, 11, 27, 7, 892, 1109\n"))));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.extension.bidirtransform.PsearchNtGroup.i_e))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:searchNtGroup", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PsearchNtGroup.invoke(children[0], children[1]);
		}
	};
}