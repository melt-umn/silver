
package silver.extension.functorattrib;

public final class PmakeAnnoArgs extends common.FunctionNode {

	public static final int i_loc = 0;
	public static final int i_baseName = 1;
	public static final int i_inputs = 2;


	public static final Class<?> childTypes[] = { core.NLocation.class,silver.definition.core.NQName.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_functorattrib_makeAnnoArgs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_loc] = new common.Lazy[core.NLocation.num_inh_attrs];
	childInheritedAttributes[i_baseName] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PmakeAnnoArgs(final Object c_loc, final Object c_baseName, final Object c_inputs) {
		this.child_loc = c_loc;
		this.child_baseName = c_baseName;
		this.child_inputs = c_inputs;

	}

	private Object child_loc;
	public final core.NLocation getChild_loc() {
		return (core.NLocation) (child_loc = common.Util.demand(child_loc));
	}

	private Object child_baseName;
	public final silver.definition.core.NQName getChild_baseName() {
		return (silver.definition.core.NQName) (child_baseName = common.Util.demand(child_baseName));
	}

	private Object child_inputs;
	public final common.ConsCell getChild_inputs() {
		return (common.ConsCell) (child_inputs = common.Util.demand(child_inputs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_loc: return getChild_loc();
			case i_baseName: return getChild_baseName();
			case i_inputs: return getChild_inputs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_loc: return child_loc;
			case i_baseName: return child_baseName;
			case i_inputs: return child_inputs;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "silver:extension:functorattrib:makeAnnoArgs";
	}

	public static common.ConsCell invoke(final Object c_loc, final Object c_baseName, final Object c_inputs) {
		try {
		final common.DecoratedNode context = new PmakeAnnoArgs(c_loc, c_baseName, c_inputs).decorate();
		//case inputs of [] -> [] | h::rest -> (annoExpr(annoName, '=', presentAppExpr(access(baseExpr(baseName,location=loc), '.', qNameAttrOccur(annoName,location=loc),location=loc),location=loc),location=loc) :: makeAnnoArgs(loc, baseName, rest)) end
		return (common.ConsCell)(new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_10294___sv_pv_10295_h = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_10296___sv_pv_10293_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10297_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_10296___sv_pv_10293_rest.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10298_h = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignatureElement)(__SV_LOCAL_10294___sv_pv_10295_h.eval())); } };
return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAnnoExpr)new silver.definition.core.PannoExpr(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.functorattrib.Init.annoName__ON__silver_extension_functorattrib_makeAnnoArgs)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TEqual_t((new common.StringCatter("=")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAppExpr)new silver.definition.core.PpresentAppExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.Paccess(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.PbaseExpr(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.functorattrib.PmakeAnnoArgs.i_baseName)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.functorattrib.PmakeAnnoArgs.i_loc)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TDot_t((new common.StringCatter(".")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQNameAttrOccur)new silver.definition.core.PqNameAttrOccur(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.functorattrib.Init.annoName__ON__silver_extension_functorattrib_makeAnnoArgs)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.functorattrib.PmakeAnnoArgs.i_loc)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.functorattrib.PmakeAnnoArgs.i_loc)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.functorattrib.PmakeAnnoArgs.i_loc)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.functorattrib.PmakeAnnoArgs.i_loc)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.functorattrib.PmakeAnnoArgs.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.functorattrib.PmakeAnnoArgs.i_loc)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.functorattrib.PmakeAnnoArgs.i_baseName)), __SV_LOCAL_10297_rest)); } })); } }).eval()); } }).eval()); }
else if(scrutinee.nil()) { return (common.ConsCell)((common.ConsCell)core.Pnil.invoke()); }return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:functorattrib 'ProductionBody.sv', 81, 4, 94, 7, 2790, 3158\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)context.childAsIs(silver.extension.functorattrib.PmakeAnnoArgs.i_inputs))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:functorattrib:makeAnnoArgs", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmakeAnnoArgs.invoke(children[0], children[1], children[2]);
		}
	};
}