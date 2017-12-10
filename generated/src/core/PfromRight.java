
package core;

public final class PfromRight extends common.FunctionNode {

	public static final int i_e = 0;
	public static final int i_o = 1;


	public static final Class<?> childTypes[] = { core.NEither.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__core_fromRight;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_e] = new common.Lazy[core.NEither.num_inh_attrs];

	}

	public PfromRight(final Object c_e, final Object c_o) {
		this.child_e = c_e;
		this.child_o = c_o;

	}

	private Object child_e;
	public final core.NEither getChild_e() {
		return (core.NEither) (child_e = common.Util.demand(child_e));
	}

	private Object child_o;
	public final Object getChild_o() {
		return (Object) (child_o = common.Util.demand(child_o));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e: return getChild_e();
			case i_o: return getChild_o();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e: return child_e;
			case i_o: return child_o;

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
		return "core:fromRight";
	}

	public static Object invoke(final Object c_e, final Object c_o) {
		try {
		final common.DecoratedNode context = new PfromRight(c_e, c_o).decorate();
		//case e of left(_) -> o | right(b) -> b end
		return (Object)(new common.PatternLazy<common.DecoratedNode, Object>() { public final Object eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pleft) { final common.Thunk<Object> __SV_LOCAL___pv4486___sv_tmp_pv_4487 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Object)scrutinee.childAsIs(0); } };
 return (Object)((Object)context.childAsIs(core.PfromRight.i_o)); }
else if(scrutineeNode instanceof core.Pright) { final common.Thunk<Object> __SV_LOCAL___pv4489___sv_pv_4488_b = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Object)scrutinee.childAsIs(0); } };
 return (Object)((Object)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4490_b = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)(__SV_LOCAL___pv4489___sv_pv_4488_b.eval())); } };
return ((Object)(__SV_LOCAL_4490_b.eval())); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Object)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at core 'Either.sv', 59, 9, 62, 5, 1142, 1192\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(core.PfromRight.i_e)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:fromRight", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfromRight.invoke(children[0], children[1]);
		}
	};
}