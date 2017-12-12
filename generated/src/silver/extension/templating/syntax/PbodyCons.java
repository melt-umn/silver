
package silver.extension.templating.syntax;

// top::TemplateStringBody ::= h::TemplateStringBodyItem t::TemplateStringBody 
public final class PbodyCons extends silver.extension.templating.syntax.NTemplateStringBody {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver.extension.templating.syntax.NTemplateStringBodyItem.class,silver.extension.templating.syntax.NTemplateStringBody.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_templating_syntax_bodyCons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.templating.syntax.NTemplateStringBody.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.templating.syntax.NTemplateStringBody.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.extension.templating.syntax.NTemplateStringBodyItem.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.extension.templating.syntax.NTemplateStringBody.num_inh_attrs];

	}

	public PbodyCons(final Object c_h, final Object c_t, final Object a_core_location) {
		super(a_core_location);
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final silver.extension.templating.syntax.NTemplateStringBodyItem getChild_h() {
		return (silver.extension.templating.syntax.NTemplateStringBodyItem) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final silver.extension.templating.syntax.NTemplateStringBody getChild_t() {
		return (silver.extension.templating.syntax.NTemplateStringBody) (child_t = common.Util.demand(child_t));
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
		throw new common.exceptions.SilverInternalError("Production silver:extension:templating:syntax:bodyCons erroneously claimed to forward");
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
		return "silver:extension:templating:syntax:bodyCons";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PbodyCons> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PbodyCons> {

		@Override
		public PbodyCons invoke(final Object[] children, final Object[] annotations) {
			return new PbodyCons(children[0], children[1], annotations[0]);
		}
	};

}
