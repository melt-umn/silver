
package silver.extension.templating.syntax;

// top::SingleLineWaterItem ::= w::SingleLineQuoteWater 
public final class PsingleLineWater extends silver.extension.templating.syntax.NSingleLineWaterItem {

	public static final int i_w = 0;


	public static final Class<?> childTypes[] = {silver.extension.templating.syntax.TSingleLineQuoteWater.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_templating_syntax_singleLineWater;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.templating.syntax.NSingleLineWaterItem.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.templating.syntax.NSingleLineWaterItem.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PsingleLineWater(final Object c_w, final Object a_core_location) {
		super(a_core_location);
		this.child_w = c_w;

	}

	private Object child_w;
	public final silver.extension.templating.syntax.TSingleLineQuoteWater getChild_w() {
		return (silver.extension.templating.syntax.TSingleLineQuoteWater) (child_w = common.Util.demand(child_w));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_w: return getChild_w();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_w: return child_w;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:templating:syntax:singleLineWater erroneously claimed to forward");
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
		return "silver:extension:templating:syntax:singleLineWater";
	}

	static void initProductionAttributeDefinitions() {
		// top.waterString = w.lexeme
		silver.extension.templating.syntax.PsingleLineWater.synthesizedAttributes[silver.extension.templating.syntax.Init.silver_extension_templating_syntax_waterString__ON__silver_extension_templating_syntax_SingleLineWaterItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.extension.templating.syntax.TSingleLineQuoteWater)context.childAsIs(silver.extension.templating.syntax.PsingleLineWater.i_w)).lexeme); } };

	}

	public static final common.NodeFactory<PsingleLineWater> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PsingleLineWater> {

		@Override
		public PsingleLineWater invoke(final Object[] children, final Object[] annotations) {
			return new PsingleLineWater(children[0], annotations[0]);
		}
	};

}
