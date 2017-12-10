
package silver.extension.templating.syntax;

// top::SingleLineTemplateStringBodyItem ::= nw::NonWater 
public final class PsingleLineItemEscape extends silver.extension.templating.syntax.NSingleLineTemplateStringBodyItem {

	public static final int i_nw = 0;


	public static final Class<?> childTypes[] = {silver.extension.templating.syntax.NNonWater.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_templating_syntax_singleLineItemEscape;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.templating.syntax.NSingleLineTemplateStringBodyItem.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.templating.syntax.NSingleLineTemplateStringBodyItem.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_nw] = new common.Lazy[silver.extension.templating.syntax.NNonWater.num_inh_attrs];

	}

	public PsingleLineItemEscape(final Object c_nw, final Object a_core_location) {
		super(a_core_location);
		this.child_nw = c_nw;

	}

	private Object child_nw;
	public final silver.extension.templating.syntax.NNonWater getChild_nw() {
		return (silver.extension.templating.syntax.NNonWater) (child_nw = common.Util.demand(child_nw));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_nw: return getChild_nw();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_nw: return child_nw;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:templating:syntax:singleLineItemEscape erroneously claimed to forward");
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
		return "silver:extension:templating:syntax:singleLineItemEscape";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PsingleLineItemEscape> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PsingleLineItemEscape> {

		@Override
		public PsingleLineItemEscape invoke(final Object[] children, final Object[] annotations) {
			return new PsingleLineItemEscape(children[0], annotations[0]);
		}
	};

}
