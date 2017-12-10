
package silver.definition.type;

public final class PcomposeSubst extends common.FunctionNode {

	public static final int i_s1 = 0;
	public static final int i_s2 = 1;


	public static final Class<?> childTypes[] = { silver.definition.type.NSubstitution.class,silver.definition.type.NSubstitution.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_composeSubst;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_s1] = new common.Lazy[silver.definition.type.NSubstitution.num_inh_attrs];
	childInheritedAttributes[i_s2] = new common.Lazy[silver.definition.type.NSubstitution.num_inh_attrs];

	}

	public PcomposeSubst(final Object c_s1, final Object c_s2) {
		this.child_s1 = c_s1;
		this.child_s2 = c_s2;

	}

	private Object child_s1;
	public final silver.definition.type.NSubstitution getChild_s1() {
		return (silver.definition.type.NSubstitution) (child_s1 = common.Util.demand(child_s1));
	}

	private Object child_s2;
	public final silver.definition.type.NSubstitution getChild_s2() {
		return (silver.definition.type.NSubstitution) (child_s2 = common.Util.demand(child_s2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s1: return getChild_s1();
			case i_s2: return getChild_s2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s1: return child_s1;
			case i_s2: return child_s2;

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
		return "silver:definition:type:composeSubst";
	}

	public static silver.definition.type.NSubstitution invoke(final Object c_s1, final Object c_s2) {
		try {
		final common.DecoratedNode context = new PcomposeSubst(c_s1, c_s2).decorate();
		//case s1, s2 of goodSubst(s1l), goodSubst(s2l) -> goodSubst(s1l ++ s2l) | goodSubst(s1l), badSubst(s2l, s2e) -> badSubst(s1l ++ s2l, s2e) | badSubst(s1l, s1e), goodSubst(s2l) -> badSubst(s1l ++ s2l, s1e) | badSubst(s1l, s1e), badSubst(s2l, s2e) -> badSubst(s1l ++ s2l, s1e ++ s2e) end
		return (silver.definition.type.NSubstitution)(new common.PatternLazy<common.DecoratedNode, silver.definition.type.NSubstitution>() { public final silver.definition.type.NSubstitution eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.type.PbadSubst) { final common.Thunk<Object> __SV_LOCAL___pv5715___sv_pv_5716_s1l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv5717___sv_pv_5718_s1e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(1); } };
 return (silver.definition.type.NSubstitution)new common.PatternLazy<common.DecoratedNode, silver.definition.type.NSubstitution>() { public final silver.definition.type.NSubstitution eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.type.PbadSubst) { final common.Thunk<Object> __SV_LOCAL___pv5722___sv_pv_5723_s2l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv5724___sv_pv_5721_s2e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(1); } };
 return (silver.definition.type.NSubstitution)((silver.definition.type.NSubstitution)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5725_s2e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv5724___sv_pv_5721_s2e.eval())); } };
return ((silver.definition.type.NSubstitution)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5726_s2l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv5722___sv_pv_5723_s2l.eval())); } };
return ((silver.definition.type.NSubstitution)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5727_s1e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv5717___sv_pv_5718_s1e.eval())); } };
return ((silver.definition.type.NSubstitution)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5728_s1l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv5715___sv_pv_5716_s1l.eval())); } };
return ((silver.definition.type.NSubstitution)new silver.definition.type.PbadSubst(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(__SV_LOCAL_5728_s1l, __SV_LOCAL_5726_s2l)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(__SV_LOCAL_5727_s1e, __SV_LOCAL_5725_s2e)); } })); } }).eval()); } }).eval()); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.type.PgoodSubst) { final common.Thunk<Object> __SV_LOCAL___pv5734___sv_pv_5733_s2l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(0); } };
 return (silver.definition.type.NSubstitution)((silver.definition.type.NSubstitution)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5735_s2l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv5734___sv_pv_5733_s2l.eval())); } };
return ((silver.definition.type.NSubstitution)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5736_s1e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv5717___sv_pv_5718_s1e.eval())); } };
return ((silver.definition.type.NSubstitution)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5737_s1l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv5715___sv_pv_5716_s1l.eval())); } };
return ((silver.definition.type.NSubstitution)new silver.definition.type.PbadSubst(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(__SV_LOCAL_5737_s1l, __SV_LOCAL_5735_s2l)); } }, __SV_LOCAL_5736_s1e)); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.type.NSubstitution)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:definition:type 'Substitutions.sv', 55, 9, 60, 5, 1523, 1822\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.definition.type.PcomposeSubst.i_s2)); }
else if(scrutineeNode instanceof silver.definition.type.PgoodSubst) { final common.Thunk<Object> __SV_LOCAL___pv5742___sv_pv_5743_s1l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(0); } };
 return (silver.definition.type.NSubstitution)new common.PatternLazy<common.DecoratedNode, silver.definition.type.NSubstitution>() { public final silver.definition.type.NSubstitution eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.type.PbadSubst) { final common.Thunk<Object> __SV_LOCAL___pv5751___sv_pv_5752_s2l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv5753___sv_pv_5754_s2e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(1); } };
 return (silver.definition.type.NSubstitution)((silver.definition.type.NSubstitution)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5750_s2e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv5753___sv_pv_5754_s2e.eval())); } };
return ((silver.definition.type.NSubstitution)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5749_s2l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv5751___sv_pv_5752_s2l.eval())); } };
return ((silver.definition.type.NSubstitution)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5748_s1l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv5742___sv_pv_5743_s1l.eval())); } };
return ((silver.definition.type.NSubstitution)new silver.definition.type.PbadSubst(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(__SV_LOCAL_5748_s1l, __SV_LOCAL_5749_s2l)); } }, __SV_LOCAL_5750_s2e)); } }).eval()); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.type.PgoodSubst) { final common.Thunk<Object> __SV_LOCAL___pv5746___sv_pv_5747_s2l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(0); } };
 return (silver.definition.type.NSubstitution)((silver.definition.type.NSubstitution)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5745_s2l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv5746___sv_pv_5747_s2l.eval())); } };
return ((silver.definition.type.NSubstitution)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5744_s1l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv5742___sv_pv_5743_s1l.eval())); } };
return ((silver.definition.type.NSubstitution)new silver.definition.type.PgoodSubst(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(__SV_LOCAL_5744_s1l, __SV_LOCAL_5745_s2l)); } })); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.type.NSubstitution)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:definition:type 'Substitutions.sv', 55, 9, 60, 5, 1523, 1822\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.definition.type.PcomposeSubst.i_s2)); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.type.NSubstitution)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:definition:type 'Substitutions.sv', 55, 9, 60, 5, 1523, 1822\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.definition.type.PcomposeSubst.i_s1)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:type:composeSubst", t);
		}
	}

	public static final common.NodeFactory<silver.definition.type.NSubstitution> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.type.NSubstitution> {
		@Override
		public silver.definition.type.NSubstitution invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcomposeSubst.invoke(children[0], children[1]);
		}
	};
}