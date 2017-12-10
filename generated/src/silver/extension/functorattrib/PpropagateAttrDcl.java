
package silver.extension.functorattrib;

// top::ProductionStmt ::= 'propagate' ns::NameList ';' 
public final class PpropagateAttrDcl extends silver.definition.core.NProductionStmt {

	public static final int i__G_2 = 0;
	public static final int i_ns = 1;
	public static final int i__G_0 = 2;


	public static final Class<?> childTypes[] = {silver.extension.functorattrib.TPropagate_kwd.class,silver.definition.core.NNameList.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_functorattrib_propagateAttrDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_ns] = new common.Lazy[silver.definition.core.NNameList.num_inh_attrs];

	}

	public PpropagateAttrDcl(final Object c__G_2, final Object c_ns, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_2 = c__G_2;
		this.child_ns = c_ns;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_2;
	public final silver.extension.functorattrib.TPropagate_kwd getChild__G_2() {
		return (silver.extension.functorattrib.TPropagate_kwd) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_ns;
	public final silver.definition.core.NNameList getChild_ns() {
		return (silver.definition.core.NNameList) (child_ns = common.Util.demand(child_ns));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i_ns: return getChild_ns();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i_ns: return child_ns;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return new common.PatternLazy<common.DecoratedNode, silver.definition.core.NProductionStmt>() { public final silver.definition.core.NProductionStmt eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.PnameListCons) { final common.Thunk<Object> __SV_LOCAL___pv10228___sv_pv_10229_n = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv10230___sv_tmp_pv_10231 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv10232___sv_pv_10227_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
 return (silver.definition.core.NProductionStmt)((silver.definition.core.NProductionStmt)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10233_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv10232___sv_pv_10227_rest.eval())); } };
return ((silver.definition.core.NProductionStmt)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10234_n = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv10228___sv_pv_10229_n.eval())); } };
return ((silver.definition.core.NProductionStmt)new silver.definition.core.PproductionStmtAppend(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionStmt)new silver.extension.functorattrib.PpropagateOne(common.Thunk.transformUndecorate(__SV_LOCAL_10234_n), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionStmt)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionStmt)new silver.extension.functorattrib.PpropagateAttrDcl(context.childAsIsLazy(silver.extension.functorattrib.PpropagateAttrDcl.i__G_2), common.Thunk.transformUndecorate(__SV_LOCAL_10233_rest), context.childAsIsLazy(silver.extension.functorattrib.PpropagateAttrDcl.i__G_0), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionStmt)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionStmt)context.undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.core.PnameListOne) { final common.Thunk<Object> __SV_LOCAL___pv10246___sv_pv_10245_n = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (silver.definition.core.NProductionStmt)((silver.definition.core.NProductionStmt)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10247_n = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv10246___sv_pv_10245_n.eval())); } };
return ((silver.definition.core.NProductionStmt)new silver.extension.functorattrib.PpropagateOne(common.Thunk.transformUndecorate(__SV_LOCAL_10247_n), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionStmt)context.undecorate()).getAnno_core_location()); } })); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.core.NProductionStmt)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:functorattrib 'ProductionBody.sv', 14, 4, 22, 7, 404, 706\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.functorattrib.PpropagateAttrDcl.i_ns));
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
		return "silver:extension:functorattrib:propagateAttrDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "propagate " ++ ns.pp ++ ";"
		silver.extension.functorattrib.PpropagateAttrDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("propagate ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.functorattrib.PpropagateAttrDcl.i_ns).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_NameList)), (common.StringCatter)(new common.StringCatter(";")))); } };

	}

	public static final common.NodeFactory<PpropagateAttrDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PpropagateAttrDcl> {

		@Override
		public PpropagateAttrDcl invoke(final Object[] children, final Object[] annotations) {
			return new PpropagateAttrDcl(children[0], children[1], children[2], annotations[0]);
		}
	};

}
