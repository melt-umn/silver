
package silver.definition.type;

public final class PmapNamedSubst extends common.FunctionNode {

	public static final int i_np = 0;
	public static final int i_s = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,silver.definition.type.NSubstitution.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_mapNamedSubst;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_s] = new common.Lazy[silver.definition.type.NSubstitution.num_inh_attrs];

	}

	public PmapNamedSubst(final Object c_np, final Object c_s) {
		this.child_np = c_np;
		this.child_s = c_s;

	}

	private Object child_np;
	public final common.ConsCell getChild_np() {
		return (common.ConsCell) (child_np = common.Util.demand(child_np));
	}

	private Object child_s;
	public final silver.definition.type.NSubstitution getChild_s() {
		return (silver.definition.type.NSubstitution) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_np: return getChild_np();
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_np: return child_np;
			case i_s: return child_s;

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
		return "silver:definition:type:mapNamedSubst";
	}

	public static common.ConsCell invoke(final Object c_np, final Object c_s) {
		try {
		final common.DecoratedNode context = new PmapNamedSubst(c_np, c_s).decorate();
		//case np of [] -> [] | namedArgType(n, t)::rest -> (namedArgType(n, performSubstitution(t, s)) :: mapNamedSubst(rest, s)) end
		return (common.ConsCell)(new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_5794___sv_tmp_pv_5793 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_5795___sv_pv_5796_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.type.PnamedArgType) { final common.Thunk<Object> __SV_LOCAL___pv5798___sv_pv_5799_n = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv5800___sv_pv_5801_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5802_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_5795___sv_pv_5796_rest.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5803_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv5800___sv_pv_5801_t.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5804_n = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv5798___sv_pv_5799_n.eval())); } };
return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NNamedArgType)new silver.definition.type.PnamedArgType(__SV_LOCAL_5804_n, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_5803_t), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.type.PmapNamedSubst.i_s)))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.type.PmapNamedSubst.invoke(__SV_LOCAL_5802_rest, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.type.PmapNamedSubst.i_s)))); } })); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:definition:type 'Substitutions.sv', 205, 9, 209, 12, 5725, 5891\n"))));}}.eval(context, (common.DecoratedNode)((silver.definition.type.NNamedArgType)(__SV_LOCAL_5794___sv_tmp_pv_5793.eval())).decorate(context, (common.Lazy[])null)); }
else if(scrutinee.nil()) { return (common.ConsCell)((common.ConsCell)core.Pnil.invoke()); }return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:definition:type 'Substitutions.sv', 205, 9, 209, 12, 5725, 5891\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)context.childAsIs(silver.definition.type.PmapNamedSubst.i_np))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:type:mapNamedSubst", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmapNamedSubst.invoke(children[0], children[1]);
		}
	};
}