
package silver.extension.functorattrib;

public final class PmakeArgs extends common.FunctionNode {

	public static final int i_loc = 0;
	public static final int i_env = 1;
	public static final int i_attrName = 2;
	public static final int i_inputs = 3;


	public static final Class<?> childTypes[] = { core.NLocation.class,common.DecoratedNode.class,silver.definition.core.NQName.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_functorattrib_makeArgs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_loc] = new common.Lazy[core.NLocation.num_inh_attrs];
	childInheritedAttributes[i_attrName] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PmakeArgs(final Object c_loc, final Object c_env, final Object c_attrName, final Object c_inputs) {
		this.child_loc = c_loc;
		this.child_env = c_env;
		this.child_attrName = c_attrName;
		this.child_inputs = c_inputs;

	}

	private Object child_loc;
	public final core.NLocation getChild_loc() {
		return (core.NLocation) (child_loc = common.Util.demand(child_loc));
	}

	private Object child_env;
	public final common.DecoratedNode getChild_env() {
		return (common.DecoratedNode) (child_env = common.Util.demand(child_env));
	}

	private Object child_attrName;
	public final silver.definition.core.NQName getChild_attrName() {
		return (silver.definition.core.NQName) (child_attrName = common.Util.demand(child_attrName));
	}

	private Object child_inputs;
	public final common.ConsCell getChild_inputs() {
		return (common.ConsCell) (child_inputs = common.Util.demand(child_inputs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_loc: return getChild_loc();
			case i_env: return getChild_env();
			case i_attrName: return getChild_attrName();
			case i_inputs: return getChild_inputs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_loc: return child_loc;
			case i_env: return child_env;
			case i_attrName: return child_attrName;
			case i_inputs: return child_inputs;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return "silver:extension:functorattrib:makeArgs";
	}

	public static common.ConsCell invoke(final Object c_loc, final Object c_env, final Object c_attrName, final Object c_inputs) {
		try {
		final common.DecoratedNode context = new PmakeArgs(c_loc, c_env, c_attrName, c_inputs).decorate();
		//case inputs of [] -> [] | h::rest -> ((if validTypeHead && attrOccursOnHead then presentAppExpr(access(baseExpr(at,location=loc), '.', qNameAttrOccur(attrName,location=loc),location=loc),location=loc) else presentAppExpr(baseExpr(at,location=loc),location=loc)) :: makeArgs(loc, env, attrName, rest)) end
		return (common.ConsCell)(new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_10249___sv_pv_10250_h = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_10251___sv_pv_10248_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10252_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_10251___sv_pv_10248_rest.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10253_h = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignatureElement)(__SV_LOCAL_10249___sv_pv_10250_h.eval())); } };
return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((((Boolean)context.localAsIs(silver.extension.functorattrib.Init.validTypeHead__ON__silver_extension_functorattrib_makeArgs)) && ((Boolean)context.localAsIs(silver.extension.functorattrib.Init.attrOccursOnHead__ON__silver_extension_functorattrib_makeArgs))) ? ((silver.definition.core.NAppExpr)new silver.definition.core.PpresentAppExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.Paccess(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.PbaseExpr(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.functorattrib.Init.at__ON__silver_extension_functorattrib_makeArgs)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.functorattrib.PmakeArgs.i_loc)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TDot_t((new common.StringCatter(".")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQNameAttrOccur)new silver.definition.core.PqNameAttrOccur(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.functorattrib.PmakeArgs.i_attrName)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.functorattrib.PmakeArgs.i_loc)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.functorattrib.PmakeArgs.i_loc)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.functorattrib.PmakeArgs.i_loc)))) : ((silver.definition.core.NAppExpr)new silver.definition.core.PpresentAppExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.PbaseExpr(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.functorattrib.Init.at__ON__silver_extension_functorattrib_makeArgs)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.functorattrib.PmakeArgs.i_loc)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.functorattrib.PmakeArgs.i_loc))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.functorattrib.PmakeArgs.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.functorattrib.PmakeArgs.i_loc)), context.childAsIsLazy(silver.extension.functorattrib.PmakeArgs.i_env), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.functorattrib.PmakeArgs.i_attrName)), __SV_LOCAL_10252_rest)); } })); } }).eval()); } }).eval()); }
else if(scrutinee.nil()) { return (common.ConsCell)((common.ConsCell)core.Pnil.invoke()); }return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:functorattrib 'ProductionBody.sv', 51, 4, 64, 7, 1702, 2174\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)context.childAsIs(silver.extension.functorattrib.PmakeArgs.i_inputs))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:functorattrib:makeArgs", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmakeArgs.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}