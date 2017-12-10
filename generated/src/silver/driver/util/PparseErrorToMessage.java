
package silver.driver.util;

public final class PparseErrorToMessage extends common.FunctionNode {

	public static final int i_grammarSource = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,core.NParseError.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_util_parseErrorToMessage;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_e] = new common.Lazy[core.NParseError.num_inh_attrs];

	}

	public PparseErrorToMessage(final Object c_grammarSource, final Object c_e) {
		this.child_grammarSource = c_grammarSource;
		this.child_e = c_e;

	}

	private Object child_grammarSource;
	public final common.StringCatter getChild_grammarSource() {
		return (common.StringCatter) (child_grammarSource = common.Util.demand(child_grammarSource));
	}

	private Object child_e;
	public final core.NParseError getChild_e() {
		return (core.NParseError) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_grammarSource: return getChild_grammarSource();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_grammarSource: return child_grammarSource;
			case i_e: return child_e;

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
		return "silver:driver:util:parseErrorToMessage";
	}

	public static core.NPair invoke(final Object c_grammarSource, final Object c_e) {
		try {
		final common.DecoratedNode context = new PparseErrorToMessage(c_grammarSource, c_e).decorate();
		//case e of syntaxError(str, locat, _, _) -> pair(locat.filename, [ err(locat, "Syntax error:\n" ++ str) ]) | unknownParseError(str, file) -> pair(file, [ err(loc(grammarSource ++ file, -1, -1, -1, -1, -1, -1), "Unknown error while parsing:\n" ++ str) ]) end
		return (core.NPair)(new common.PatternLazy<common.DecoratedNode, core.NPair>() { public final core.NPair eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.PsyntaxError) { final common.Thunk<Object> __SV_LOCAL___pv4251___sv_pv_4252_str = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv4253___sv_pv_4250_locat = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
final common.Thunk<Object> __SV_LOCAL___pv4254___sv_tmp_pv_4255 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(2); } };
final common.Thunk<Object> __SV_LOCAL___pv4256___sv_tmp_pv_4257 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(3); } };
 return (core.NPair)((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4258_locat = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv4253___sv_pv_4250_locat.eval())); } };
return ((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4259_str = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv4251___sv_pv_4252_str.eval())); } };
return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)(__SV_LOCAL_4258_locat.eval())).synthesized(core.Init.core_filename__ON__core_Location)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(common.Thunk.transformUndecorate(__SV_LOCAL_4258_locat), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Syntax error:\n")), (common.StringCatter)((common.StringCatter)(__SV_LOCAL_4259_str.eval()))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof core.PunknownParseError) { final common.Thunk<Object> __SV_LOCAL___pv4266___sv_pv_4267_str = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv4268___sv_pv_4265_file = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
 return (core.NPair)((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4269_file = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv4268___sv_pv_4265_file.eval())); } };
return ((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4270_str = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv4266___sv_pv_4267_str.eval())); } };
return ((core.NPair)new core.Ppair(__SV_LOCAL_4269_file, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)new core.Ploc(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.driver.util.PparseErrorToMessage.i_grammarSource)), (common.StringCatter)((common.StringCatter)(__SV_LOCAL_4269_file.eval()))); } }, Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Unknown error while parsing:\n")), (common.StringCatter)((common.StringCatter)(__SV_LOCAL_4270_str.eval()))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NPair)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:driver:util 'RootSpec.sv', 131, 9, 140, 5, 4675, 4984\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.driver.util.PparseErrorToMessage.i_e)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:util:parseErrorToMessage", t);
		}
	}

	public static final common.NodeFactory<core.NPair> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NPair> {
		@Override
		public core.NPair invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PparseErrorToMessage.invoke(children[0], children[1]);
		}
	};
}