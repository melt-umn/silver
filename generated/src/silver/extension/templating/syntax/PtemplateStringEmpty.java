
package silver.extension.templating.syntax;

// top::TemplateString ::= TripleQuote 
public final class PtemplateStringEmpty extends silver.extension.templating.syntax.NTemplateString {

	public static final int i__G_0 = 0;


	public static final Class<?> childTypes[] = {silver.extension.templating.syntax.TTripleQuote.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_templating_syntax_templateStringEmpty;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.templating.syntax.NTemplateString.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.templating.syntax.NTemplateString.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PtemplateStringEmpty(final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_0 = c__G_0;

	}

	private Object child__G_0;
	public final silver.extension.templating.syntax.TTripleQuote getChild__G_0() {
		return (silver.extension.templating.syntax.TTripleQuote) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:templating:syntax:templateStringEmpty erroneously claimed to forward");
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
		return "silver:extension:templating:syntax:templateStringEmpty";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PtemplateStringEmpty> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtemplateStringEmpty> {

		@Override
		public PtemplateStringEmpty invoke(final Object[] children, final Object[] annotations) {
			return new PtemplateStringEmpty(children[0], annotations[0]);
		}
	};

}
