
package silver.extension.bidirtransform;

public final class PnsMatchesStr2 extends common.FunctionNode {

	public static final int i_oe = 0;
	public static final int i_fnnt = 1;


	public static final Class<?> childTypes[] = { silver.definition.env.NNamedSignatureElement.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_nsMatchesStr2;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_oe] = new common.Lazy[silver.definition.env.NNamedSignatureElement.num_inh_attrs];

	}

	public PnsMatchesStr2(final Object c_oe, final Object c_fnnt) {
		this.child_oe = c_oe;
		this.child_fnnt = c_fnnt;

	}

	private Object child_oe;
	public final silver.definition.env.NNamedSignatureElement getChild_oe() {
		return (silver.definition.env.NNamedSignatureElement) (child_oe = common.Util.demand(child_oe));
	}

	private Object child_fnnt;
	public final common.StringCatter getChild_fnnt() {
		return (common.StringCatter) (child_fnnt = common.Util.demand(child_fnnt));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_oe: return getChild_oe();
			case i_fnnt: return getChild_fnnt();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_oe: return child_oe;
			case i_fnnt: return child_fnnt;

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
		return "silver:extension:bidirtransform:nsMatchesStr2";
	}

	public static Boolean invoke(final Object c_oe, final Object c_fnnt) {
		try {
		final common.DecoratedNode context = new PnsMatchesStr2(c_oe, c_fnnt).decorate();
		//case oe of namedSignatureElement(_, ty) -> nsMatchesStr3(ty, fnnt) | _ -> false end
		return (Boolean)(((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13954___fail_13955 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return false; } };
return new common.PatternLazy<common.DecoratedNode, Boolean>() { public final Boolean eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.env.PnamedSignatureElement) { final common.Thunk<Object> __SV_LOCAL___pv13959___sv_tmp_pv_13960 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13961___sv_pv_13958_ty = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (Boolean)((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13962_ty = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv13961___sv_pv_13958_ty.eval())); } };
return ((Boolean)silver.extension.bidirtransform.PnsMatchesStr3.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_13962_ty), context.childAsIsLazy(silver.extension.bidirtransform.PnsMatchesStr2.i_fnnt))); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Boolean)(__SV_LOCAL_13954___fail_13955.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PnsMatchesStr2.i_oe)); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:nsMatchesStr2", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PnsMatchesStr2.invoke(children[0], children[1]);
		}
	};
}