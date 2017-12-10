
package patt;

public final class PdoesThisGenerateSilverErrors extends common.FunctionNode {

	public static final int i_d = 0;


	public static final Class<?> childTypes[] = { patt.NOrdinaryNonterminal.class };

	public static final int num_local_attrs = Init.count_local__ON__patt_doesThisGenerateSilverErrors;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_d] = new common.Lazy[patt.NOrdinaryNonterminal.num_inh_attrs];

	}

	public PdoesThisGenerateSilverErrors(final Object c_d) {
		this.child_d = c_d;

	}

	private Object child_d;
	public final patt.NOrdinaryNonterminal getChild_d() {
		return (patt.NOrdinaryNonterminal) (child_d = common.Util.demand(child_d));
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
		return "patt:doesThisGenerateSilverErrors";
	}

	public static common.StringCatter invoke(final Object c_d) {
		try {
		final common.DecoratedNode context = new PdoesThisGenerateSilverErrors(c_d).decorate();
		//case d of ordinaryProduction(p) -> ordinaryFunction(p, p,) end
		return (common.StringCatter)(new common.PatternLazy<common.DecoratedNode, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof patt.PordinaryProduction) { final common.Thunk<Object> __SV_LOCAL___pv486___sv_pv_485_p = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (common.StringCatter)((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_487_p = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv486___sv_pv_485_p.eval())); } };
return ((common.StringCatter)patt.PordinaryFunction.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_487_p), __SV_LOCAL_487_p)); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at patt 'Normal.sv', 209, 9, 211, 12, 4962, 5067\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(patt.PdoesThisGenerateSilverErrors.i_d)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function patt:doesThisGenerateSilverErrors", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PdoesThisGenerateSilverErrors.invoke(children[0]);
		}
	};
}