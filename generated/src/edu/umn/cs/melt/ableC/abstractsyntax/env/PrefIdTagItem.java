
package edu.umn.cs.melt.ableC.abstractsyntax.env;

// top::TagItem ::= tag::StructOrEnumOrUnion refId::String 
public final class PrefIdTagItem extends edu.umn.cs.melt.ableC.abstractsyntax.env.NTagItem {

	public static final int i_tag = 0;
	public static final int i_refId = 1;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.abstractsyntax.host.NStructOrEnumOrUnion.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_refIdTagItem;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.env.NTagItem.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.env.NTagItem.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_tag] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NStructOrEnumOrUnion.num_inh_attrs];

	}

	public PrefIdTagItem(final Object c_tag, final Object c_refId) {
		super();
		this.child_tag = c_tag;
		this.child_refId = c_refId;

	}

	private Object child_tag;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NStructOrEnumOrUnion getChild_tag() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NStructOrEnumOrUnion) (child_tag = common.Util.demand(child_tag));
	}

	private Object child_refId;
	public final common.StringCatter getChild_refId() {
		return (common.StringCatter) (child_refId = common.Util.demand(child_refId));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_tag: return getChild_tag();
			case i_refId: return getChild_refId();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_tag: return child_tag;
			case i_refId: return child_refId;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:env:refIdTagItem erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:env:refIdTagItem";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PrefIdTagItem> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PrefIdTagItem> {

		@Override
		public PrefIdTagItem invoke(final Object[] children, final Object[] annotations) {
			return new PrefIdTagItem(children[0], children[1]);
		}
	};

}
