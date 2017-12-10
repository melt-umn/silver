
package silver.modification.primitivepattern;

public final class PproduceRefinement extends common.FunctionNode {

	public static final int i_scrutineeType = 0;
	public static final int i_constructorType = 1;


	public static final Class<?> childTypes[] = { silver.definition.type.NType.class,silver.definition.type.NType.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_primitivepattern_produceRefinement;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_scrutineeType] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
	childInheritedAttributes[i_constructorType] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	}

	public PproduceRefinement(final Object c_scrutineeType, final Object c_constructorType) {
		this.child_scrutineeType = c_scrutineeType;
		this.child_constructorType = c_constructorType;

	}

	private Object child_scrutineeType;
	public final silver.definition.type.NType getChild_scrutineeType() {
		return (silver.definition.type.NType) (child_scrutineeType = common.Util.demand(child_scrutineeType));
	}

	private Object child_constructorType;
	public final silver.definition.type.NType getChild_constructorType() {
		return (silver.definition.type.NType) (child_constructorType = common.Util.demand(child_constructorType));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_scrutineeType: return getChild_scrutineeType();
			case i_constructorType: return getChild_constructorType();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_scrutineeType: return child_scrutineeType;
			case i_constructorType: return child_constructorType;

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
		return "silver:modification:primitivepattern:produceRefinement";
	}

	public static silver.definition.type.NSubstitution invoke(final Object c_scrutineeType, final Object c_constructorType) {
		try {
		final common.DecoratedNode context = new PproduceRefinement(c_scrutineeType, c_constructorType).decorate();
		//case scrutineeType, constructorType of decoratedType(nonterminalType(n1, p1)), decoratedType(nonterminalType(n2, p2)) -> if n1 == n2 then refineAll(p1, p2) else emptySubst() | _, _ -> emptySubst() end
		return (silver.definition.type.NSubstitution)(((silver.definition.type.NSubstitution)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7549___fail_7550 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)silver.definition.type.PemptySubst.invoke()); } };
return new common.PatternLazy<common.DecoratedNode, silver.definition.type.NSubstitution>() { public final silver.definition.type.NSubstitution eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.type.PdecoratedType) { final common.Thunk<Object> __SV_LOCAL___pv7554___sv_tmp_pv_7553 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (silver.definition.type.NSubstitution)new common.PatternLazy<common.DecoratedNode, silver.definition.type.NSubstitution>() { public final silver.definition.type.NSubstitution eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.type.PnonterminalType) { final common.Thunk<Object> __SV_LOCAL___pv7556___sv_pv_7557_n1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv7558___sv_pv_7559_p1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(1); } };
 return (silver.definition.type.NSubstitution)new common.PatternLazy<common.DecoratedNode, silver.definition.type.NSubstitution>() { public final silver.definition.type.NSubstitution eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.type.PdecoratedType) { final common.Thunk<Object> __SV_LOCAL___pv7562___sv_tmp_pv_7561 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (silver.definition.type.NSubstitution)new common.PatternLazy<common.DecoratedNode, silver.definition.type.NSubstitution>() { public final silver.definition.type.NSubstitution eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.type.PnonterminalType) { final common.Thunk<Object> __SV_LOCAL___pv7565___sv_pv_7566_n2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv7567___sv_pv_7564_p2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(1); } };
 return (silver.definition.type.NSubstitution)((silver.definition.type.NSubstitution)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7568_p2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv7567___sv_pv_7564_p2.eval())); } };
return ((silver.definition.type.NSubstitution)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7569_n2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv7565___sv_pv_7566_n2.eval())); } };
return ((silver.definition.type.NSubstitution)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7570_p1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv7558___sv_pv_7559_p1.eval())); } };
return ((silver.definition.type.NSubstitution)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7571_n1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv7556___sv_pv_7557_n1.eval())); } };
return (((common.StringCatter)(__SV_LOCAL_7571_n1.eval())).equals(((common.StringCatter)(__SV_LOCAL_7569_n2.eval()))) ? ((silver.definition.type.NSubstitution)silver.modification.primitivepattern.PrefineAll.invoke(__SV_LOCAL_7570_p1, __SV_LOCAL_7568_p2)) : ((silver.definition.type.NSubstitution)silver.definition.type.PemptySubst.invoke())); } }).eval()); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.type.NSubstitution)(__SV_LOCAL_7549___fail_7550.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL___pv7562___sv_tmp_pv_7561.eval()))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.type.NSubstitution)(__SV_LOCAL_7549___fail_7550.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.modification.primitivepattern.PproduceRefinement.i_constructorType)); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.type.NSubstitution)(__SV_LOCAL_7549___fail_7550.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL___pv7554___sv_tmp_pv_7553.eval()))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.type.NSubstitution)(__SV_LOCAL_7549___fail_7550.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.modification.primitivepattern.PproduceRefinement.i_scrutineeType)); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:primitivepattern:produceRefinement", t);
		}
	}

	public static final common.NodeFactory<silver.definition.type.NSubstitution> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.type.NSubstitution> {
		@Override
		public silver.definition.type.NSubstitution invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PproduceRefinement.invoke(children[0], children[1]);
		}
	};
}