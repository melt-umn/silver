
package silver.extension.bidirtransform;

// top::AGDcl ::= qns::QNameList 
public final class PoriginAttributes extends silver.definition.core.NAGDcl {

	public static final int i_qns = 0;


	public static final Class<?> childTypes[] = {silver.extension.bidirtransform.NQNameList.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_originAttributes;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_qns] = new common.Lazy[silver.extension.bidirtransform.NQNameList.num_inh_attrs];

	}

	public PoriginAttributes(final Object c_qns, final Object a_core_location) {
		super(a_core_location);
		this.child_qns = c_qns;

	}

	private Object child_qns;
	public final silver.extension.bidirtransform.NQNameList getChild_qns() {
		return (silver.extension.bidirtransform.NQNameList) (child_qns = common.Util.demand(child_qns));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_qns: return getChild_qns();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_qns: return child_qns;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return (((Boolean)core.Pnull.invoke(context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PoriginAttributes.i_qns, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_qList__ON__silver_extension_bidirtransform_QNameList))) ? ((silver.definition.core.NAGDcl)new silver.definition.core.PemptyAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })) : ((silver.definition.core.NAGDcl)new silver.definition.core.PappendAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.extension.bidirtransform.PoriginAttribute(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQName)core.Phead.invoke(context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PoriginAttributes.i_qns, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_qList__ON__silver_extension_bidirtransform_QNameList))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.extension.bidirtransform.PoriginAttributes(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.bidirtransform.Init.qnsTail__ON__silver_extension_bidirtransform_originAttributes)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })));
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
		return "silver:extension:bidirtransform:originAttributes";
	}

	static void initProductionAttributeDefinitions() {
		// qnsTail = case qns of qNameListCons(_, _, tl) -> tl | _ -> qNameListEmpty() end
		silver.extension.bidirtransform.PoriginAttributes.localAttributes[silver.extension.bidirtransform.Init.qnsTail__ON__silver_extension_bidirtransform_originAttributes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NQNameList)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12913___fail_12914 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NQNameList)new silver.extension.bidirtransform.PqNameListEmpty(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } };
return new common.PatternLazy<common.DecoratedNode, silver.extension.bidirtransform.NQNameList>() { public final silver.extension.bidirtransform.NQNameList eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.extension.bidirtransform.PqNameListCons) { final common.Thunk<Object> __SV_LOCAL___pv12919___sv_tmp_pv_12920 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv12921___sv_tmp_pv_12922 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv12923___sv_pv_12918_tl = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
 return (silver.extension.bidirtransform.NQNameList)((silver.extension.bidirtransform.NQNameList)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12924_tl = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv12923___sv_pv_12918_tl.eval())); } };
return ((silver.extension.bidirtransform.NQNameList)((common.DecoratedNode)__SV_LOCAL_12924_tl.eval()).undecorate()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.extension.bidirtransform.NQNameList)(__SV_LOCAL_12913___fail_12914.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PoriginAttributes.i_qns)); } }).eval()); } };


	}

	public static final common.NodeFactory<PoriginAttributes> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PoriginAttributes> {

		@Override
		public PoriginAttributes invoke(final Object[] children, final Object[] annotations) {
			return new PoriginAttributes(children[0], annotations[0]);
		}
	};

}
