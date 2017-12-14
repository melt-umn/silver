
package silver.extension.bidirtransform;

// top::AppExprs ::= inputNames::[String] 
public final class PstrAppExprs extends silver.definition.core.NAppExprs {

	public static final int i_inputNames = 0;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_strAppExprs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAppExprs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAppExprs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PstrAppExprs(final Object c_inputNames, final Object a_core_location) {
		super(a_core_location);
		this.child_inputNames = c_inputNames;

	}

	private Object child_inputNames;
	public final common.ConsCell getChild_inputNames() {
		return (common.ConsCell) (child_inputNames = common.Util.demand(child_inputNames));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_inputNames: return getChild_inputNames();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_inputNames: return child_inputNames;

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
		return (((Integer)core.PlistLength.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PstrAppExprs.i_inputNames))).equals(Integer.valueOf((int)1)) ? ((silver.definition.core.NAppExprs)new silver.definition.core.PoneAppExprs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAppExpr)new silver.extension.bidirtransform.PpresentName(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PstrAppExprs.i_inputNames))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAppExprs)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAppExprs)context.undecorate()).getAnno_core_location()); } })) : ((silver.definition.core.NAppExprs)new silver.definition.core.PsnocAppExprs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAppExprs)new silver.extension.bidirtransform.PstrAppExprs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PstrAppExprs.i_inputNames))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAppExprs)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TComma_t((new common.StringCatter(",")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAppExpr)new silver.extension.bidirtransform.PpresentName(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PstrAppExprs.i_inputNames))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAppExprs)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAppExprs)context.undecorate()).getAnno_core_location()); } })));
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
		return "silver:extension:bidirtransform:strAppExprs";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PstrAppExprs> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PstrAppExprs> {

		@Override
		public PstrAppExprs invoke(final Object[] children, final Object[] annotations) {
			return new PstrAppExprs(children[0], annotations[0]);
		}
	};

}
