
package edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax;

public final class PappendStructItemList extends common.FunctionNode {

	public static final int i_d1 = 0;
	public static final int i_d2 = 1;


	public static final Class<?> childTypes[] = { edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_appendStructItemList;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_d1] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList.num_inh_attrs];
	childInheritedAttributes[i_d2] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList.num_inh_attrs];

	}

	public PappendStructItemList(final Object c_d1, final Object c_d2) {
		this.child_d1 = c_d1;
		this.child_d2 = c_d2;

	}

	private Object child_d1;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList getChild_d1() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList) (child_d1 = common.Util.demand(child_d1));
	}

	private Object child_d2;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList getChild_d2() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList) (child_d2 = common.Util.demand(child_d2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_d1: return getChild_d1();
			case i_d2: return getChild_d2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_d1: return child_d1;
			case i_d2: return child_d2;

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
		return "edu:umn:cs:melt:exts:ableC:algebraicDataTypes:datatype:abstractsyntax:appendStructItemList";
	}

	public static edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList invoke(final Object c_d1, final Object c_d2) {
		try {
		final common.DecoratedNode context = new PappendStructItemList(c_d1, c_d2).decorate();
		//case d1 of nilStructItem() -> d2 | consStructItem(d, rest) -> consStructItem(d, appendStructItemList(rest, d2,),) end
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList)(new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PconsStructItem) { final common.Thunk<Object> __SV_LOCAL___pv10093___sv_pv_10094_d = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv10095___sv_pv_10092_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList)((edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10096_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv10095___sv_pv_10092_rest.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10097_d = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv10093___sv_pv_10094_d.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList)new edu.umn.cs.melt.ableC.abstractsyntax.host.PconsStructItem(common.Thunk.transformUndecorate(__SV_LOCAL_10097_d), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList)edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PappendStructItemList.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_10096_rest), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PappendStructItemList.i_d2)))); } })); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PnilStructItem) {  return (edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList)context.childDecorated(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PappendStructItemList.i_d2).undecorate(); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:exts:ableC:algebraicDataTypes:datatype:abstractsyntax 'DataType.sv', 254, 9, 257, 12, 8676, 8826\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PappendStructItemList.i_d1)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:exts:ableC:algebraicDataTypes:datatype:abstractsyntax:appendStructItemList", t);
		}
	}

	public static final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList> factory = new Factory();

	public static final class Factory extends common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList> {
		@Override
		public edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PappendStructItemList.invoke(children[0], children[1]);
		}
	};
}