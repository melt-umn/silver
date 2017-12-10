
package silver_features;

public final class PthunkUndecGoWrong extends common.FunctionNode {

	public static final int i_ac = 0;


	public static final Class<?> childTypes[] = { silver_features.NADecoratedValue.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_features_thunkUndecGoWrong;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_ac] = new common.Lazy[silver_features.NADecoratedValue.num_inh_attrs];

	}

	public PthunkUndecGoWrong(final Object c_ac) {
		this.child_ac = c_ac;

	}

	private Object child_ac;
	public final silver_features.NADecoratedValue getChild_ac() {
		return (silver_features.NADecoratedValue) (child_ac = common.Util.demand(child_ac));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ac: return getChild_ac();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ac: return child_ac;

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
		return "silver_features:thunkUndecGoWrong";
	}

	public static silver_features.NADecoratedValue invoke(final Object c_ac) {
		try {
		final common.DecoratedNode context = new PthunkUndecGoWrong(c_ac).decorate();
		//let dec :: Decorated ADecoratedValue = ac in case dec of bouncerDecProd(_) -> error("nope",) | _ -> bouncerDecProd(dec,) end end
		return (silver_features.NADecoratedValue)(((silver_features.NADecoratedValue)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_171_dec = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return context.childDecorated(silver_features.PthunkUndecGoWrong.i_ac); } };
return ((silver_features.NADecoratedValue)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_172___fail_173 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.NADecoratedValue)new silver_features.PbouncerDecProd(common.Thunk.transformUndecorate(__SV_LOCAL_171_dec))); } };
return new common.PatternLazy<common.DecoratedNode, silver_features.NADecoratedValue>() { public final silver_features.NADecoratedValue eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver_features.PbouncerDecProd) { final common.Thunk<Object> __SV_LOCAL___pv178___sv_tmp_pv_179 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (silver_features.NADecoratedValue)((silver_features.NADecoratedValue)core.Perror.invoke((new common.StringCatter("nope")))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver_features.NADecoratedValue)(__SV_LOCAL_172___fail_173.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL_171_dec.eval()))); } }).eval()); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver_features:thunkUndecGoWrong", t);
		}
	}

	public static final common.NodeFactory<silver_features.NADecoratedValue> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver_features.NADecoratedValue> {
		@Override
		public silver_features.NADecoratedValue invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PthunkUndecGoWrong.invoke(children[0]);
		}
	};
}