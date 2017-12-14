
package silver.extension.bidirtransform;

public final class PabsProdFromDcl extends common.FunctionNode {

	public static final int i_dcl = 0;


	public static final Class<?> childTypes[] = { silver.definition.env.NDclInfo.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_absProdFromDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_dcl] = new common.Lazy[silver.definition.env.NDclInfo.num_inh_attrs];

	}

	public PabsProdFromDcl(final Object c_dcl) {
		this.child_dcl = c_dcl;

	}

	private Object child_dcl;
	public final silver.definition.env.NDclInfo getChild_dcl() {
		return (silver.definition.env.NDclInfo) (child_dcl = common.Util.demand(child_dcl));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_dcl: return getChild_dcl();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_dcl: return child_dcl;

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
		return "silver:extension:bidirtransform:absProdFromDcl";
	}

	public static common.ConsCell invoke(final Object c_dcl) {
		try {
		final common.DecoratedNode context = new PabsProdFromDcl(c_dcl).decorate();
		//case dcl of prodDcl(_, _, ns, x) -> if x then [ ns ] else [] | _ -> [] end
		return (common.ConsCell)(((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12793___fail_12794 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.env.PprodDcl) { final common.Thunk<Object> __SV_LOCAL___pv12799___sv_tmp_pv_12800 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv12801___sv_tmp_pv_12802 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
final common.Thunk<Object> __SV_LOCAL___pv12803___sv_pv_12804_ns = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv12805___sv_pv_12798_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Boolean)scrutinee.childAsIs(3); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12806_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Boolean)(__SV_LOCAL___pv12805___sv_pv_12798_x.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12807_ns = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12803___sv_pv_12804_ns.eval())); } };
return (((Boolean)(__SV_LOCAL_12806_x.eval())) ? ((common.ConsCell)core.Pcons.invoke(__SV_LOCAL_12807_ns, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_12793___fail_12794.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PabsProdFromDcl.i_dcl)); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:absProdFromDcl", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PabsProdFromDcl.invoke(children[0]);
		}
	};
}