
package silver.extension.bidirtransform;

public final class PgetProdsFromConsDefs2 extends common.FunctionNode {

	public static final int i_fnnt = 0;
	public static final int i_d = 1;
	public static final int i_dfs = 2;
	public static final int i_skipGrammar = 3;


	public static final Class<?> childTypes[] = { common.StringCatter.class,silver.definition.env.NDef.class,silver.definition.env.NDefs.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_getProdsFromConsDefs2;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_d] = new common.Lazy[silver.definition.env.NDef.num_inh_attrs];
	childInheritedAttributes[i_dfs] = new common.Lazy[silver.definition.env.NDefs.num_inh_attrs];

	}

	public PgetProdsFromConsDefs2(final Object c_fnnt, final Object c_d, final Object c_dfs, final Object c_skipGrammar) {
		this.child_fnnt = c_fnnt;
		this.child_d = c_d;
		this.child_dfs = c_dfs;
		this.child_skipGrammar = c_skipGrammar;

	}

	private Object child_fnnt;
	public final common.StringCatter getChild_fnnt() {
		return (common.StringCatter) (child_fnnt = common.Util.demand(child_fnnt));
	}

	private Object child_d;
	public final silver.definition.env.NDef getChild_d() {
		return (silver.definition.env.NDef) (child_d = common.Util.demand(child_d));
	}

	private Object child_dfs;
	public final silver.definition.env.NDefs getChild_dfs() {
		return (silver.definition.env.NDefs) (child_dfs = common.Util.demand(child_dfs));
	}

	private Object child_skipGrammar;
	public final common.StringCatter getChild_skipGrammar() {
		return (common.StringCatter) (child_skipGrammar = common.Util.demand(child_skipGrammar));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_fnnt: return getChild_fnnt();
			case i_d: return getChild_d();
			case i_dfs: return getChild_dfs();
			case i_skipGrammar: return getChild_skipGrammar();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_fnnt: return child_fnnt;
			case i_d: return child_d;
			case i_dfs: return child_dfs;
			case i_skipGrammar: return child_skipGrammar;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return "silver:extension:bidirtransform:getProdsFromConsDefs2";
	}

	public static common.ConsCell invoke(final Object c_fnnt, final Object c_d, final Object c_dfs, final Object c_skipGrammar) {
		try {
		final common.DecoratedNode context = new PgetProdsFromConsDefs2(c_fnnt, c_d, c_dfs, c_skipGrammar).decorate();
		//case d of prodDclDef(ei) -> getProdsFromConsDefs3(fnnt, d, dfs, skipGrammar) | _ -> getProdsFromDefs2(fnnt, dfs, skipGrammar) end
		return (common.ConsCell)(((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13427___fail_13428 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.bidirtransform.PgetProdsFromDefs2.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PgetProdsFromConsDefs2.i_fnnt), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PgetProdsFromConsDefs2.i_dfs)), context.childAsIsLazy(silver.extension.bidirtransform.PgetProdsFromConsDefs2.i_skipGrammar))); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.env.PprodDclDef) { final common.Thunk<Object> __SV_LOCAL___pv13433___sv_pv_13432_ei = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13434_ei = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13433___sv_pv_13432_ei.eval())); } };
return ((common.ConsCell)silver.extension.bidirtransform.PgetProdsFromConsDefs3.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PgetProdsFromConsDefs2.i_fnnt), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PgetProdsFromConsDefs2.i_d)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PgetProdsFromConsDefs2.i_dfs)), context.childAsIsLazy(silver.extension.bidirtransform.PgetProdsFromConsDefs2.i_skipGrammar))); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_13427___fail_13428.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PgetProdsFromConsDefs2.i_d)); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:getProdsFromConsDefs2", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgetProdsFromConsDefs2.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}