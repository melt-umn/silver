
package silver.extension.templating.syntax;

// top::TemplateStringBodyItem ::= w::Water nw::NonWater 
public final class PitemWaterEscape extends silver.extension.templating.syntax.NTemplateStringBodyItem {

	public static final int i_w = 0;
	public static final int i_nw = 1;


	public static final Class<?> childTypes[] = {silver.extension.templating.syntax.NWater.class,silver.extension.templating.syntax.NNonWater.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_templating_syntax_itemWaterEscape;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.templating.syntax.NTemplateStringBodyItem.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.templating.syntax.NTemplateStringBodyItem.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_w] = new common.Lazy[silver.extension.templating.syntax.NWater.num_inh_attrs];
	childInheritedAttributes[i_nw] = new common.Lazy[silver.extension.templating.syntax.NNonWater.num_inh_attrs];

	}

	public PitemWaterEscape(final Object c_w, final Object c_nw, final Object a_core_location) {
		super(a_core_location);
		this.child_w = c_w;
		this.child_nw = c_nw;

	}

	private Object child_w;
	public final silver.extension.templating.syntax.NWater getChild_w() {
		return (silver.extension.templating.syntax.NWater) (child_w = common.Util.demand(child_w));
	}

	private Object child_nw;
	public final silver.extension.templating.syntax.NNonWater getChild_nw() {
		return (silver.extension.templating.syntax.NNonWater) (child_nw = common.Util.demand(child_nw));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_w: return getChild_w();
			case i_nw: return getChild_nw();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_w: return child_w;
			case i_nw: return child_nw;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		throw new common.exceptions.SilverInternalError("Production silver:extension:templating:syntax:itemWaterEscape erroneously claimed to forward");
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
		return "silver:extension:templating:syntax:itemWaterEscape";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PitemWaterEscape> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PitemWaterEscape> {

		@Override
		public PitemWaterEscape invoke(final Object[] children, final Object[] annotations) {
			return new PitemWaterEscape(children[0], children[1], annotations[0]);
		}
	};

}
