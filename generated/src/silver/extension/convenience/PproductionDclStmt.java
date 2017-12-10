
package silver.extension.convenience;

// top::ProductionDclStmt ::= optn::OptionalName v::ProdVBar rhs::ProductionRHS mods::ProductionModifiers body::ProductionBody opta::OptionalAction 
public final class PproductionDclStmt extends silver.extension.convenience.NProductionDclStmt {

	public static final int i_optn = 0;
	public static final int i_v = 1;
	public static final int i_rhs = 2;
	public static final int i_mods = 3;
	public static final int i_body = 4;
	public static final int i_opta = 5;


	public static final Class<?> childTypes[] = {silver.extension.convenience.NOptionalName.class,silver.extension.convenience.TProdVBar.class,silver.definition.core.NProductionRHS.class,silver.definition.concrete_syntax.NProductionModifiers.class,silver.definition.core.NProductionBody.class,silver.extension.convenience.NOptionalAction.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_convenience_productionDclStmt;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.convenience.NProductionDclStmt.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.convenience.NProductionDclStmt.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_optn] = new common.Lazy[silver.extension.convenience.NOptionalName.num_inh_attrs];
	childInheritedAttributes[i_rhs] = new common.Lazy[silver.definition.core.NProductionRHS.num_inh_attrs];
	childInheritedAttributes[i_mods] = new common.Lazy[silver.definition.concrete_syntax.NProductionModifiers.num_inh_attrs];
	childInheritedAttributes[i_body] = new common.Lazy[silver.definition.core.NProductionBody.num_inh_attrs];
	childInheritedAttributes[i_opta] = new common.Lazy[silver.extension.convenience.NOptionalAction.num_inh_attrs];

	}

	public PproductionDclStmt(final Object c_optn, final Object c_v, final Object c_rhs, final Object c_mods, final Object c_body, final Object c_opta, final Object a_core_location) {
		super(a_core_location);
		this.child_optn = c_optn;
		this.child_v = c_v;
		this.child_rhs = c_rhs;
		this.child_mods = c_mods;
		this.child_body = c_body;
		this.child_opta = c_opta;

	}

	private Object child_optn;
	public final silver.extension.convenience.NOptionalName getChild_optn() {
		return (silver.extension.convenience.NOptionalName) (child_optn = common.Util.demand(child_optn));
	}

	private Object child_v;
	public final silver.extension.convenience.TProdVBar getChild_v() {
		return (silver.extension.convenience.TProdVBar) (child_v = common.Util.demand(child_v));
	}

	private Object child_rhs;
	public final silver.definition.core.NProductionRHS getChild_rhs() {
		return (silver.definition.core.NProductionRHS) (child_rhs = common.Util.demand(child_rhs));
	}

	private Object child_mods;
	public final silver.definition.concrete_syntax.NProductionModifiers getChild_mods() {
		return (silver.definition.concrete_syntax.NProductionModifiers) (child_mods = common.Util.demand(child_mods));
	}

	private Object child_body;
	public final silver.definition.core.NProductionBody getChild_body() {
		return (silver.definition.core.NProductionBody) (child_body = common.Util.demand(child_body));
	}

	private Object child_opta;
	public final silver.extension.convenience.NOptionalAction getChild_opta() {
		return (silver.extension.convenience.NOptionalAction) (child_opta = common.Util.demand(child_opta));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_optn: return getChild_optn();
			case i_v: return getChild_v();
			case i_rhs: return getChild_rhs();
			case i_mods: return getChild_mods();
			case i_body: return getChild_body();
			case i_opta: return getChild_opta();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_optn: return child_optn;
			case i_v: return child_v;
			case i_rhs: return child_rhs;
			case i_mods: return child_mods;
			case i_body: return child_body;
			case i_opta: return child_opta;

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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:extension:convenience:productionDclStmt erroneously claimed to forward");
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
		return "silver:extension:convenience:productionDclStmt";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "| " ++ rhs.pp ++ mods.pp ++ body.pp
		silver.extension.convenience.PproductionDclStmt.synthesizedAttributes[silver.extension.convenience.Init.silver_definition_env_pp__ON__silver_extension_convenience_ProductionDclStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("| ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.convenience.PproductionDclStmt.i_rhs).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionRHS)), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.convenience.PproductionDclStmt.i_mods).synthesized(silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_ProductionModifiers)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.convenience.PproductionDclStmt.i_body).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionBody))))); } };
		// nme = case optn of noOptionalName() -> name("p_" ++ substitute(":", "_", top.grammarName) ++ "_" ++ substitute(".", "_", top.location.filename) ++ "_" ++ toString(v.line) ++ "_" ++ toString(v.column), v.location) | anOptionalName(_, n, _) -> n end
		silver.extension.convenience.PproductionDclStmt.localAttributes[silver.extension.convenience.Init.nme__ON__silver_extension_convenience_productionDclStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.PatternLazy<common.DecoratedNode, silver.definition.core.NName>() { public final silver.definition.core.NName eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.extension.convenience.PanOptionalName) { final common.Thunk<Object> __SV_LOCAL___pv9383___sv_tmp_pv_9384 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv9385___sv_pv_9382_n = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
final common.Thunk<Object> __SV_LOCAL___pv9386___sv_tmp_pv_9387 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(2); } };
 return (silver.definition.core.NName)((silver.definition.core.NName)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_9388_n = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv9385___sv_pv_9382_n.eval())); } };
return ((silver.definition.core.NName)((common.DecoratedNode)__SV_LOCAL_9388_n.eval()).undecorate()); } }).eval()); }
else if(scrutineeNode instanceof silver.extension.convenience.PnoOptionalName) {  return (silver.definition.core.NName)((silver.definition.core.NName)silver.definition.core.Pname.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("p_")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Psubstitute.invoke((new common.StringCatter(":")), (new common.StringCatter("_")), context.contextInheritedLazy(silver.extension.convenience.Init.silver_definition_core_grammarName__ON__silver_extension_convenience_ProductionDclStmt))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("_")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Psubstitute.invoke((new common.StringCatter(".")), (new common.StringCatter("_")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((core.NLocation)((silver.extension.convenience.NProductionDclStmt)context.undecorate()).getAnno_core_location()).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_filename__ON__core_Location)); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("_")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)((silver.extension.convenience.TProdVBar)context.childAsIs(silver.extension.convenience.PproductionDclStmt.i_v)).getLine()))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("_")), (common.StringCatter)new common.StringCatter(String.valueOf(((Integer)((silver.extension.convenience.TProdVBar)context.childAsIs(silver.extension.convenience.PproductionDclStmt.i_v)).getColumn())))))))))); } }, ((core.NLocation)((silver.extension.convenience.TProdVBar)context.childAsIs(silver.extension.convenience.PproductionDclStmt.i_v)).location))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.core.NName)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:convenience 'Productions.sv', 48, 4, 57, 7, 1502, 1820\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.convenience.PproductionDclStmt.i_optn)); } };
		// newSig = productionSignature(top.lhsdcl, '::=', rhs,location=rhs.location)
		silver.extension.convenience.PproductionDclStmt.localAttributes[silver.extension.convenience.Init.newSig__ON__silver_extension_convenience_productionDclStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionSignature)new silver.definition.core.PproductionSignature(context.contextInheritedLazy(silver.extension.convenience.Init.silver_extension_convenience_lhsdcl__ON__silver_extension_convenience_ProductionDclStmt), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TCCEQ_t((new common.StringCatter("::=")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.convenience.PproductionDclStmt.i_rhs)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionRHS)context.childDecorated(silver.extension.convenience.PproductionDclStmt.i_rhs).undecorate()).getAnno_core_location()); } })); } };
		// top.proddcls = case opta of noOptionalAction() -> concreteProductionDcl('concrete', 'production', nme, newSig, mods, body,location=top.location) | anOptionalAction(a, c) -> concreteProductionDclAction('concrete', 'production', nme, newSig, mods, body, a, c,location=top.location) end
		silver.extension.convenience.PproductionDclStmt.synthesizedAttributes[silver.extension.convenience.Init.silver_extension_convenience_proddcls__ON__silver_extension_convenience_ProductionDclStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.PatternLazy<common.DecoratedNode, silver.definition.core.NAGDcl>() { public final silver.definition.core.NAGDcl eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.extension.convenience.PanOptionalAction) { final common.Thunk<Object> __SV_LOCAL___pv9401___sv_pv_9402_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.modification.copper.TAction_kwd)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv9403___sv_pv_9400_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (silver.definition.core.NAGDcl)((silver.definition.core.NAGDcl)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_9404_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv9403___sv_pv_9400_c.eval())); } };
return ((silver.definition.core.NAGDcl)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_9405_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.modification.copper.TAction_kwd)(__SV_LOCAL___pv9401___sv_pv_9402_a.eval())); } };
return ((silver.definition.core.NAGDcl)new silver.modification.copper.PconcreteProductionDclAction(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TConcrete_kwd((new common.StringCatter("concrete")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TProduction_kwd((new common.StringCatter("production")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.convenience.Init.nme__ON__silver_extension_convenience_productionDclStmt)), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.convenience.Init.newSig__ON__silver_extension_convenience_productionDclStmt)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.convenience.PproductionDclStmt.i_mods)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.convenience.PproductionDclStmt.i_body)), __SV_LOCAL_9405_a, common.Thunk.transformUndecorate(__SV_LOCAL_9404_c), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.convenience.NProductionDclStmt)context.undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof silver.extension.convenience.PnoOptionalAction) {  return (silver.definition.core.NAGDcl)((silver.definition.core.NAGDcl)new silver.definition.concrete_syntax.PconcreteProductionDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TConcrete_kwd((new common.StringCatter("concrete")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TProduction_kwd((new common.StringCatter("production")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.convenience.Init.nme__ON__silver_extension_convenience_productionDclStmt)), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.convenience.Init.newSig__ON__silver_extension_convenience_productionDclStmt)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.convenience.PproductionDclStmt.i_mods)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.convenience.PproductionDclStmt.i_body)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.convenience.NProductionDclStmt)context.undecorate()).getAnno_core_location()); } })); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.core.NAGDcl)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:convenience 'Productions.sv', 63, 4, 68, 7, 1958, 2258\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.convenience.PproductionDclStmt.i_opta)); } };

	}

	public static final common.NodeFactory<PproductionDclStmt> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PproductionDclStmt> {

		@Override
		public PproductionDclStmt invoke(final Object[] children, final Object[] annotations) {
			return new PproductionDclStmt(children[0], children[1], children[2], children[3], children[4], children[5], annotations[0]);
		}
	};

}
