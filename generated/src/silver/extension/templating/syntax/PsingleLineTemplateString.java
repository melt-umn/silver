
package silver.extension.templating.syntax;

// top::SingleLineTemplateString ::= b::SingleLineTemplateStringBody LiteralQuote 
public final class PsingleLineTemplateString extends silver.extension.templating.syntax.NSingleLineTemplateString {

	public static final int i_b = 0;
	public static final int i__G_0 = 1;


	public static final Class<?> childTypes[] = {silver.extension.templating.syntax.NSingleLineTemplateStringBody.class,silver.extension.templating.syntax.TLiteralQuote.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_templating_syntax_singleLineTemplateString;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.templating.syntax.NSingleLineTemplateString.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.templating.syntax.NSingleLineTemplateString.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_b] = new common.Lazy[silver.extension.templating.syntax.NSingleLineTemplateStringBody.num_inh_attrs];

	}

	public PsingleLineTemplateString(final Object c_b, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child_b = c_b;
		this.child__G_0 = c__G_0;

	}

	private Object child_b;
	public final silver.extension.templating.syntax.NSingleLineTemplateStringBody getChild_b() {
		return (silver.extension.templating.syntax.NSingleLineTemplateStringBody) (child_b = common.Util.demand(child_b));
	}

	private Object child__G_0;
	public final silver.extension.templating.syntax.TLiteralQuote getChild__G_0() {
		return (silver.extension.templating.syntax.TLiteralQuote) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_b: return getChild_b();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_b: return child_b;
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:templating:syntax:singleLineTemplateString erroneously claimed to forward");
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
		return "silver:extension:templating:syntax:singleLineTemplateString";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PsingleLineTemplateString> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PsingleLineTemplateString> {

		@Override
		public PsingleLineTemplateString invoke(final Object[] children, final Object[] annotations) {
			return new PsingleLineTemplateString(children[0], children[1], annotations[0]);
		}
	};

}
