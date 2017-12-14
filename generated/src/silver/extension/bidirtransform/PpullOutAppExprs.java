
package silver.extension.bidirtransform;

public final class PpullOutAppExprs extends common.FunctionNode {

	public static final int i_aexprs = 0;
	public static final int i_env = 1;


	public static final Class<?> childTypes[] = { silver.definition.core.NAppExprs.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_pullOutAppExprs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_aexprs] = new common.Lazy[silver.definition.core.NAppExprs.num_inh_attrs];

	}

	public PpullOutAppExprs(final Object c_aexprs, final Object c_env) {
		this.child_aexprs = c_aexprs;
		this.child_env = c_env;

	}

	private Object child_aexprs;
	public final silver.definition.core.NAppExprs getChild_aexprs() {
		return (silver.definition.core.NAppExprs) (child_aexprs = common.Util.demand(child_aexprs));
	}

	private Object child_env;
	public final common.DecoratedNode getChild_env() {
		return (common.DecoratedNode) (child_env = common.Util.demand(child_env));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_aexprs: return getChild_aexprs();
			case i_env: return getChild_env();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_aexprs: return child_aexprs;
			case i_env: return child_env;

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
		return "silver:extension:bidirtransform:pullOutAppExprs";
	}

	public static common.ConsCell invoke(final Object c_aexprs, final Object c_env) {
		try {
		final common.DecoratedNode context = new PpullOutAppExprs(c_aexprs, c_env).decorate();
		//case aexprs of snocAppExprs(es, _, e) -> pullOutAppExprs(es, env) ++ case e of presentAppExpr(e2) -> [ decorate e2 with {env = env;} ] end | oneAppExprs(e) -> case e of presentAppExpr(e2) -> [ decorate e2 with {env = env;} ] end | _ -> [] end
		return (common.ConsCell)(((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12322___fail_12323 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.PoneAppExprs) { final common.Thunk<Object> __SV_LOCAL___pv12328___sv_pv_12327_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12329_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12328___sv_pv_12327_e.eval())); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.PpresentAppExpr) { final common.Thunk<Object> __SV_LOCAL___pv12334___sv_pv_12333_e2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12335_e2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12334___sv_pv_12333_e2.eval())); } };
return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)((common.DecoratedNode)__SV_LOCAL_12335_e2.eval()).undecorate()).decorate(context, common.Util.populateInh(silver.definition.core.NExpr.num_inh_attrs, new int[]{silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_Expr}, new common.Lazy[]{new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.childAsIs(silver.extension.bidirtransform.PpullOutAppExprs.i_env)); } }})); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:bidirtransform 'HelpFills.sv', 208, 12, 208, 79, 8617, 8684\n"))));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL_12329_e.eval()))); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PsnocAppExprs) { final common.Thunk<Object> __SV_LOCAL___pv12340___sv_pv_12341_es = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv12342___sv_tmp_pv_12343 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv12344___sv_pv_12339_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12345_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12344___sv_pv_12339_e.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12346_es = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12340___sv_pv_12341_es.eval())); } };
return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.bidirtransform.PpullOutAppExprs.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_12346_es), context.childAsIsLazy(silver.extension.bidirtransform.PpullOutAppExprs.i_env))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.PpresentAppExpr) { final common.Thunk<Object> __SV_LOCAL___pv12358___sv_pv_12357_e2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12359_e2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12358___sv_pv_12357_e2.eval())); } };
return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)((common.DecoratedNode)__SV_LOCAL_12359_e2.eval()).undecorate()).decorate(context, common.Util.populateInh(silver.definition.core.NExpr.num_inh_attrs, new int[]{silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_Expr}, new common.Lazy[]{new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.childAsIs(silver.extension.bidirtransform.PpullOutAppExprs.i_env)); } }})); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:bidirtransform 'HelpFills.sv', 206, 40, 206, 107, 8508, 8575\n"))));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL_12345_e.eval()))); } })); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_12322___fail_12323.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PpullOutAppExprs.i_aexprs)); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:pullOutAppExprs", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PpullOutAppExprs.invoke(children[0], children[1]);
		}
	};
}