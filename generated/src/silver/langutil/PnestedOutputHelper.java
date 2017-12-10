
package silver.langutil;

public final class PnestedOutputHelper extends common.FunctionNode {

	public static final int i_header = 0;
	public static final int i_msg = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,silver.langutil.NMessage.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_langutil_nestedOutputHelper;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_msg] = new common.Lazy[silver.langutil.NMessage.num_inh_attrs];

	}

	public PnestedOutputHelper(final Object c_header, final Object c_msg) {
		this.child_header = c_header;
		this.child_msg = c_msg;

	}

	private Object child_header;
	public final common.StringCatter getChild_header() {
		return (common.StringCatter) (child_header = common.Util.demand(child_header));
	}

	private Object child_msg;
	public final silver.langutil.NMessage getChild_msg() {
		return (silver.langutil.NMessage) (child_msg = common.Util.demand(child_msg));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_header: return getChild_header();
			case i_msg: return getChild_msg();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_header: return child_header;
			case i_msg: return child_msg;

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
		return "silver:langutil:nestedOutputHelper";
	}

	public static common.StringCatter invoke(final Object c_header, final Object c_msg) {
		try {
		final common.DecoratedNode context = new PnestedOutputHelper(c_header, c_msg).decorate();
		//case msg of nested(_, _, _) -> header ++ "\n" | _ -> "" end ++ msg.output
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2450___fail_2449 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
return new common.PatternLazy<common.DecoratedNode, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.langutil.Pnested) { final common.Thunk<Object> __SV_LOCAL___pv2451___sv_tmp_pv_2452 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv2453___sv_tmp_pv_2454 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv2455___sv_tmp_pv_2456 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(2); } };
 return (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.langutil.PnestedOutputHelper.i_header)), (common.StringCatter)(new common.StringCatter("\n"))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.StringCatter)(__SV_LOCAL_2450___fail_2449.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.langutil.PnestedOutputHelper.i_msg)); } }).eval()), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.langutil.PnestedOutputHelper.i_msg).synthesized(silver.langutil.Init.silver_langutil_output__ON__silver_langutil_Message))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:langutil:nestedOutputHelper", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PnestedOutputHelper.invoke(children[0], children[1]);
		}
	};
}