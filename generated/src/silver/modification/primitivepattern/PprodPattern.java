
package silver.modification.primitivepattern;

// top::PrimPattern ::= qn::QName '(' ns::VarBinders ')' '->' e::Expr 
public final class PprodPattern extends silver.modification.primitivepattern.NPrimPattern {

	public static final int i_qn = 0;
	public static final int i__G_4 = 1;
	public static final int i_ns = 2;
	public static final int i__G_2 = 3;
	public static final int i__G_1 = 4;
	public static final int i_e = 5;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class,silver.definition.core.TLParen_t.class,silver.modification.primitivepattern.NVarBinders.class,silver.definition.core.TRParen_t.class,silver.extension.patternmatching.TArrow_kwd.class,silver.definition.core.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_primitivepattern_prodPattern;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.primitivepattern.NPrimPattern.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.primitivepattern.NPrimPattern.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_qn] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_ns] = new common.Lazy[silver.modification.primitivepattern.NVarBinders.num_inh_attrs];
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PprodPattern(final Object c_qn, final Object c__G_4, final Object c_ns, final Object c__G_2, final Object c__G_1, final Object c_e, final Object a_core_location) {
		super(a_core_location);
		this.child_qn = c_qn;
		this.child__G_4 = c__G_4;
		this.child_ns = c_ns;
		this.child__G_2 = c__G_2;
		this.child__G_1 = c__G_1;
		this.child_e = c_e;

	}

	private Object child_qn;
	public final silver.definition.core.NQName getChild_qn() {
		return (silver.definition.core.NQName) (child_qn = common.Util.demand(child_qn));
	}

	private Object child__G_4;
	public final silver.definition.core.TLParen_t getChild__G_4() {
		return (silver.definition.core.TLParen_t) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_ns;
	public final silver.modification.primitivepattern.NVarBinders getChild_ns() {
		return (silver.modification.primitivepattern.NVarBinders) (child_ns = common.Util.demand(child_ns));
	}

	private Object child__G_2;
	public final silver.definition.core.TRParen_t getChild__G_2() {
		return (silver.definition.core.TRParen_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child__G_1;
	public final silver.extension.patternmatching.TArrow_kwd getChild__G_1() {
		return (silver.extension.patternmatching.TArrow_kwd) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_qn: return getChild_qn();
			case i__G_4: return getChild__G_4();
			case i_ns: return getChild_ns();
			case i__G_2: return getChild__G_2();
			case i__G_1: return getChild__G_1();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_qn: return child_qn;
			case i__G_4: return child__G_4;
			case i_ns: return child_ns;
			case i__G_2: return child__G_2;
			case i__G_1: return child__G_1;
			case i_e: return child_e;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 6;
	}

	@Override
	public common.Lazy getSynthesized(final int index) {
		return synthesizedAttributes[index];
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
	public boolean hasForward() {
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return (((Boolean)context.localAsIs(silver.modification.primitivepattern.Init.isGadt__ON__silver_modification_primitivepattern_prodPattern)) ? ((silver.modification.primitivepattern.NPrimPattern)new silver.modification.primitivepattern.PprodPatternGadt(context.childDecoratedLazy(silver.modification.primitivepattern.PprodPattern.i_qn), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.primitivepattern.PprodPattern.i_ns)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.primitivepattern.PprodPattern.i_e)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.primitivepattern.NPrimPattern)context.undecorate()).getAnno_core_location()); } })) : ((silver.modification.primitivepattern.NPrimPattern)new silver.modification.primitivepattern.PprodPatternNormal(context.childDecoratedLazy(silver.modification.primitivepattern.PprodPattern.i_qn), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.primitivepattern.PprodPattern.i_ns)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.primitivepattern.PprodPattern.i_e)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.primitivepattern.NPrimPattern)context.undecorate()).getAnno_core_location()); } })));
	}

	@Override
	public common.Lazy getForwardInheritedAttributes(final int index) {
		return forwardInheritedAttributes[index];
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
		return "silver:modification:primitivepattern:prodPattern";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = qn.pp ++ "(" ++ ns.pp ++ ") -> " ++ e.pp
		silver.modification.primitivepattern.PprodPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_env_pp__ON__silver_modification_primitivepattern_PrimPattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PprodPattern.i_qn).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PprodPattern.i_ns).synthesized(silver.modification.primitivepattern.Init.silver_definition_env_pp__ON__silver_modification_primitivepattern_VarBinders)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(") -> ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PprodPattern.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)))))); } };
		// isGadt = case qn.lookupValue.typerep.outputType of nonterminalType(_, tvs) -> ! isOnlyTyVars(tvs) || length(tvs) != length(setUnionTyVarsAll(map((.freeVariables), tvs))) | _ -> false end
		silver.modification.primitivepattern.PprodPattern.localAttributes[silver.modification.primitivepattern.Init.isGadt__ON__silver_modification_primitivepattern_prodPattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7644___fail_7645 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return false; } };
return new common.PatternLazy<common.DecoratedNode, Boolean>() { public final Boolean eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.type.PnonterminalType) { final common.Thunk<Object> __SV_LOCAL___pv7649___sv_tmp_pv_7650 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv7651___sv_pv_7648_tvs = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(1); } };
 return (Boolean)((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7652_tvs = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv7651___sv_pv_7648_tvs.eval())); } };
return ((!((Boolean)silver.modification.primitivepattern.PisOnlyTyVars.invoke(__SV_LOCAL_7652_tvs))) || !((Integer)core.PlistLength.invoke(__SV_LOCAL_7652_tvs)).equals(((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.type.PsetUnionTyVarsAll.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.definition.type.Init.silver_definition_type_freeVariables__ON__silver_definition_type_Type, context), __SV_LOCAL_7652_tvs)); } })); } })))); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Boolean)(__SV_LOCAL_7644___fail_7645.eval()));}}.eval(context, (common.DecoratedNode)((silver.definition.type.NType)((silver.definition.type.NType)((common.DecoratedNode)context.childDecorated(silver.modification.primitivepattern.PprodPattern.i_qn).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_outputType__ON__silver_definition_type_Type)).decorate(context, (common.Lazy[])null)); } }).eval()); } };

	}

	public static final common.NodeFactory<PprodPattern> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PprodPattern> {

		@Override
		public PprodPattern invoke(final Object[] children, final Object[] annotations) {
			return new PprodPattern(children[0], children[1], children[2], children[3], children[4], children[5], annotations[0]);
		}
	};

}
