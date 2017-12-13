
package silver.modification.copper;

public final class PaddTerminalAttrDefs extends common.FunctionNode {

	public static final int i_moredefs = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_addTerminalAttrDefs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PaddTerminalAttrDefs(final Object c_moredefs) {
		this.child_moredefs = c_moredefs;

	}

	private Object child_moredefs;
	public final common.ConsCell getChild_moredefs() {
		return (common.ConsCell) (child_moredefs = common.Util.demand(child_moredefs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_moredefs: return getChild_moredefs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_moredefs: return child_moredefs;

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
		return "silver:modification:copper:addTerminalAttrDefs";
	}

	public static common.ConsCell invoke(final Object c_moredefs) {
		try {
		final common.DecoratedNode context = new PaddTerminalAttrDefs(c_moredefs).decorate();
		//[ termAttrValueDef("DBGtav", bogusLocation(), "lexeme", stringType()), termAttrValueDef("DBGtav", bogusLocation(), "filename", stringType()), termAttrValueDef("DBGtav", bogusLocation(), "line", intType()), termAttrValueDef("DBGtav", bogusLocation(), "column", intType()) ] ++ moredefs
		return (common.ConsCell)(((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.modification.copper.PtermAttrValueDef.invoke((new common.StringCatter("DBGtav")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)silver.definition.env.PbogusLocation.invoke()); } }, (new common.StringCatter("lexeme")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PstringType()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.modification.copper.PtermAttrValueDef.invoke((new common.StringCatter("DBGtav")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)silver.definition.env.PbogusLocation.invoke()); } }, (new common.StringCatter("filename")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PstringType()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.modification.copper.PtermAttrValueDef.invoke((new common.StringCatter("DBGtav")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)silver.definition.env.PbogusLocation.invoke()); } }, (new common.StringCatter("line")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PintType()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.modification.copper.PtermAttrValueDef.invoke((new common.StringCatter("DBGtav")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)silver.definition.env.PbogusLocation.invoke()); } }, (new common.StringCatter("column")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PintType()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } }, context.childAsIsLazy(silver.modification.copper.PaddTerminalAttrDefs.i_moredefs))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:copper:addTerminalAttrDefs", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PaddTerminalAttrDefs.invoke(children[0]);
		}
	};
}