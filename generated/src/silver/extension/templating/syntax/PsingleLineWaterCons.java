
package silver.extension.templating.syntax;

// top::SingleLineWater ::= h::SingleLineWater t::SingleLineWaterItem 
public final class PsingleLineWaterCons extends silver.extension.templating.syntax.NSingleLineWater {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver.extension.templating.syntax.NSingleLineWater.class,silver.extension.templating.syntax.NSingleLineWaterItem.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_templating_syntax_singleLineWaterCons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.templating.syntax.NSingleLineWater.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.templating.syntax.NSingleLineWater.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.extension.templating.syntax.NSingleLineWater.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.extension.templating.syntax.NSingleLineWaterItem.num_inh_attrs];

	}

	public PsingleLineWaterCons(final Object c_h, final Object c_t, final Object a_core_location) {
		super(a_core_location);
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final silver.extension.templating.syntax.NSingleLineWater getChild_h() {
		return (silver.extension.templating.syntax.NSingleLineWater) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final silver.extension.templating.syntax.NSingleLineWaterItem getChild_t() {
		return (silver.extension.templating.syntax.NSingleLineWaterItem) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;
			case i_t: return child_t;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:templating:syntax:singleLineWaterCons erroneously claimed to forward");
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
		return "silver:extension:templating:syntax:singleLineWaterCons";
	}

	static void initProductionAttributeDefinitions() {
		// top.waterString = h.waterString ++ t.waterString
		silver.extension.templating.syntax.PsingleLineWaterCons.synthesizedAttributes[silver.extension.templating.syntax.Init.silver_extension_templating_syntax_waterString__ON__silver_extension_templating_syntax_SingleLineWater] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.templating.syntax.PsingleLineWaterCons.i_h).synthesized(silver.extension.templating.syntax.Init.silver_extension_templating_syntax_waterString__ON__silver_extension_templating_syntax_SingleLineWater)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.templating.syntax.PsingleLineWaterCons.i_t).synthesized(silver.extension.templating.syntax.Init.silver_extension_templating_syntax_waterString__ON__silver_extension_templating_syntax_SingleLineWaterItem))); } };

	}

	public static final common.NodeFactory<PsingleLineWaterCons> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PsingleLineWaterCons> {

		@Override
		public PsingleLineWaterCons invoke(final Object[] children, final Object[] annotations) {
			return new PsingleLineWaterCons(children[0], children[1], annotations[0]);
		}
	};

}
