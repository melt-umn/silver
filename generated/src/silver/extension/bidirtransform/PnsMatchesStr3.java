
package silver.extension.bidirtransform;

public final class PnsMatchesStr3 extends common.FunctionNode {

	public static final int i_ty = 0;
	public static final int i_fnnt = 1;


	public static final Class<?> childTypes[] = { silver.definition.type.NType.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_nsMatchesStr3;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_ty] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	}

	public PnsMatchesStr3(final Object c_ty, final Object c_fnnt) {
		this.child_ty = c_ty;
		this.child_fnnt = c_fnnt;

	}

	private Object child_ty;
	public final silver.definition.type.NType getChild_ty() {
		return (silver.definition.type.NType) (child_ty = common.Util.demand(child_ty));
	}

	private Object child_fnnt;
	public final common.StringCatter getChild_fnnt() {
		return (common.StringCatter) (child_fnnt = common.Util.demand(child_fnnt));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ty: return getChild_ty();
			case i_fnnt: return getChild_fnnt();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ty: return child_ty;
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
		return "silver:extension:bidirtransform:nsMatchesStr3";
	}

	public static Boolean invoke(final Object c_ty, final Object c_fnnt) {
		try {
		final common.DecoratedNode context = new PnsMatchesStr3(c_ty, c_fnnt).decorate();
		//case ty of nonterminalType(s, _) -> unFull(s) == fnnt | _ -> false end
		return (Boolean)(((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13720___fail_13721 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return false; } };
return new common.PatternLazy<common.DecoratedNode, Boolean>() { public final Boolean eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.type.PnonterminalType) { final common.Thunk<Object> __SV_LOCAL___pv13725___sv_pv_13724_s = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv13726___sv_tmp_pv_13727 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(1); } };
 return (Boolean)((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13728_s = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv13725___sv_pv_13724_s.eval())); } };
return ((common.StringCatter)silver.extension.bidirtransform.PunFull.invoke(__SV_LOCAL_13728_s)).equals(((common.StringCatter)context.childAsIs(silver.extension.bidirtransform.PnsMatchesStr3.i_fnnt))); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Boolean)(__SV_LOCAL_13720___fail_13721.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PnsMatchesStr3.i_ty)); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:nsMatchesStr3", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PnsMatchesStr3.invoke(children[0], children[1]);
		}
	};
}