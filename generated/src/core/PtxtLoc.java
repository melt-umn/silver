
package core;

// top::Location ::= text::String 
public final class PtxtLoc extends core.NLocation {

	public static final int i_text = 0;


	public static final Class<?> childTypes[] = {common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__core_txtLoc;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[core.NLocation.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[core.NLocation.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PtxtLoc(final Object c_text) {
		super();
		this.child_text = c_text;

	}

	private Object child_text;
	public final common.StringCatter getChild_text() {
		return (common.StringCatter) (child_text = common.Util.demand(child_text));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_text: return getChild_text();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_text: return child_text;

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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production core:txtLoc erroneously claimed to forward");
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
		return "core:txtLoc";
	}

	static void initProductionAttributeDefinitions() {
		// top.filename = "N/A"
		core.PtxtLoc.synthesizedAttributes[core.Init.core_filename__ON__core_Location] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("N/A")); } };
		// top.line = -1
		core.PtxtLoc.synthesizedAttributes[core.Init.core_line__ON__core_Location] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)-1); } };
		// top.column = -1
		core.PtxtLoc.synthesizedAttributes[core.Init.core_column__ON__core_Location] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)-1); } };
		// top.endLine = -1
		core.PtxtLoc.synthesizedAttributes[core.Init.core_endLine__ON__core_Location] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)-1); } };
		// top.endColumn = -1
		core.PtxtLoc.synthesizedAttributes[core.Init.core_endColumn__ON__core_Location] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)-1); } };
		// top.index = -1
		core.PtxtLoc.synthesizedAttributes[core.Init.core_index__ON__core_Location] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)-1); } };
		// top.endIndex = -1
		core.PtxtLoc.synthesizedAttributes[core.Init.core_endIndex__ON__core_Location] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)-1); } };

	}

	public static final common.NodeFactory<PtxtLoc> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtxtLoc> {

		@Override
		public PtxtLoc invoke(final Object[] children, final Object[] annotations) {
			return new PtxtLoc(children[0]);
		}
	};

}
