
package silver.extension.bidirtransform;

public final class PcncProdsFromDefs extends common.FunctionNode {

	public static final int i_defs = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_cncProdsFromDefs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PcncProdsFromDefs(final Object c_defs) {
		this.child_defs = c_defs;

	}

	private Object child_defs;
	public final common.ConsCell getChild_defs() {
		return (common.ConsCell) (child_defs = common.Util.demand(child_defs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_defs: return getChild_defs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_defs: return child_defs;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return "silver:extension:bidirtransform:cncProdsFromDefs";
	}

	public static common.ConsCell invoke(final Object c_defs) {
		try {
		final common.DecoratedNode context = new PcncProdsFromDefs(c_defs).decorate();
		//if length(defs) == 0 then [] else cncProdFromDef(head(defs)) ++ cncProdsFromDefs(tail(defs))
		return (common.ConsCell)((((Integer)core.PlistLength.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PcncProdsFromDefs.i_defs))).equals(Integer.valueOf((int)0)) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.bidirtransform.PcncProdFromDef.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PcncProdsFromDefs.i_defs))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.bidirtransform.PcncProdsFromDefs.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PcncProdsFromDefs.i_defs))); } })); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:cncProdsFromDefs", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcncProdsFromDefs.invoke(children[0]);
		}
	};
}