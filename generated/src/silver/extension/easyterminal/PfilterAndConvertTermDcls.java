
package silver.extension.easyterminal;

public final class PfilterAndConvertTermDcls extends common.FunctionNode {

	public static final int i_ei = 0;
	public static final int i_sofar = 1;


	public static final Class<?> childTypes[] = { silver.definition.env.NEnvItem.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_easyterminal_filterAndConvertTermDcls;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_ei] = new common.Lazy[silver.definition.env.NEnvItem.num_inh_attrs];

	}

	public PfilterAndConvertTermDcls(final Object c_ei, final Object c_sofar) {
		this.child_ei = c_ei;
		this.child_sofar = c_sofar;

	}

	private Object child_ei;
	public final silver.definition.env.NEnvItem getChild_ei() {
		return (silver.definition.env.NEnvItem) (child_ei = common.Util.demand(child_ei));
	}

	private Object child_sofar;
	public final common.ConsCell getChild_sofar() {
		return (common.ConsCell) (child_sofar = common.Util.demand(child_sofar));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ei: return getChild_ei();
			case i_sofar: return getChild_sofar();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ei: return child_ei;
			case i_sofar: return child_sofar;

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
		return "silver:extension:easyterminal:filterAndConvertTermDcls";
	}

	public static common.ConsCell invoke(final Object c_ei, final Object c_sofar) {
		try {
		final common.DecoratedNode context = new PfilterAndConvertTermDcls(c_ei, c_sofar).decorate();
		//case ei.dcl of termDcl(_, _, fn, regex) -> (pair(regex.regString, ei.dcl) :: sofar) | _ -> sofar end
		return (common.ConsCell)(((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_375___fail_376 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.childAsIs(silver.extension.easyterminal.PfilterAndConvertTermDcls.i_sofar)); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.env.PtermDcl) { final common.Thunk<Object> __SV_LOCAL___pv380___sv_tmp_pv_381 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv382___sv_tmp_pv_383 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
final common.Thunk<Object> __SV_LOCAL___pv384___sv_pv_385_fn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(2); } };
final common.Thunk<Object> __SV_LOCAL___pv386___sv_pv_379_regex = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(3); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_387_regex = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv386___sv_pv_379_regex.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_388_fn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv384___sv_pv_385_fn.eval())); } };
return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)(__SV_LOCAL_387_regex.eval())).synthesized(silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_Regex)); } }, context.childDecoratedSynthesizedLazy(silver.extension.easyterminal.PfilterAndConvertTermDcls.i_ei, silver.definition.env.Init.silver_definition_env_dcl__ON__silver_definition_env_EnvItem))); } }, context.childAsIsLazy(silver.extension.easyterminal.PfilterAndConvertTermDcls.i_sofar))); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_375___fail_376.eval()));}}.eval(context, (common.DecoratedNode)((silver.definition.env.NDclInfo)context.childDecorated(silver.extension.easyterminal.PfilterAndConvertTermDcls.i_ei).synthesized(silver.definition.env.Init.silver_definition_env_dcl__ON__silver_definition_env_EnvItem)).decorate(context, (common.Lazy[])null)); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:easyterminal:filterAndConvertTermDcls", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfilterAndConvertTermDcls.invoke(children[0], children[1]);
		}
	};
}