
package silver.definition.type;

public final class PignoreFailure extends common.FunctionNode {

	public static final int i_s = 0;


	public static final Class<?> childTypes[] = { silver.definition.type.NSubstitution.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_ignoreFailure;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_s] = new common.Lazy[silver.definition.type.NSubstitution.num_inh_attrs];

	}

	public PignoreFailure(final Object c_s) {
		this.child_s = c_s;

	}

	private Object child_s;
	public final silver.definition.type.NSubstitution getChild_s() {
		return (silver.definition.type.NSubstitution) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;

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
		return "silver:definition:type:ignoreFailure";
	}

	public static silver.definition.type.NSubstitution invoke(final Object c_s) {
		try {
		final common.DecoratedNode context = new PignoreFailure(c_s).decorate();
		//case s of goodSubst(_) -> s | badSubst(sl, _) -> goodSubst(sl) end
		return (silver.definition.type.NSubstitution)(new common.PatternLazy<common.DecoratedNode, silver.definition.type.NSubstitution>() { public final silver.definition.type.NSubstitution eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.type.PbadSubst) { final common.Thunk<Object> __SV_LOCAL___pv5757___sv_pv_5756_sl = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv5758___sv_tmp_pv_5759 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(1); } };
 return (silver.definition.type.NSubstitution)((silver.definition.type.NSubstitution)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5760_sl = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv5757___sv_pv_5756_sl.eval())); } };
return ((silver.definition.type.NSubstitution)new silver.definition.type.PgoodSubst(__SV_LOCAL_5760_sl)); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.type.PgoodSubst) { final common.Thunk<Object> __SV_LOCAL___pv5761___sv_tmp_pv_5762 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(0); } };
 return (silver.definition.type.NSubstitution)context.childDecorated(silver.definition.type.PignoreFailure.i_s).undecorate(); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.type.NSubstitution)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:definition:type 'Substitutions.sv', 66, 9, 69, 5, 1894, 1967\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.definition.type.PignoreFailure.i_s)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:type:ignoreFailure", t);
		}
	}

	public static final common.NodeFactory<silver.definition.type.NSubstitution> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.type.NSubstitution> {
		@Override
		public silver.definition.type.NSubstitution invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PignoreFailure.invoke(children[0]);
		}
	};
}