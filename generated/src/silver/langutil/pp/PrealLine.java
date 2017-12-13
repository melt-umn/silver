
package silver.langutil.pp;

// top::Document ::= 
public final class PrealLine extends silver.langutil.pp.NDocument {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_langutil_pp_realLine;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.langutil.pp.NDocument.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.langutil.pp.NDocument.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PrealLine() {
		super();

	}



	@Override
	public Object getChild(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 0;
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
		throw new common.exceptions.SilverInternalError("Production silver:langutil:pp:realLine erroneously claimed to forward");
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
		return "silver:langutil:pp:realLine";
	}

	static void initProductionAttributeDefinitions() {
		// top.outPosition = top.inPosition + (top.inRemaining - top.outRemaining)
		silver.langutil.pp.PrealLine.synthesizedAttributes[silver.langutil.pp.Init.silver_langutil_pp_outPosition__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.inherited(silver.langutil.pp.Init.silver_langutil_pp_inPosition__ON__silver_langutil_pp_Document)) + Integer.valueOf(((Integer)context.inherited(silver.langutil.pp.Init.silver_langutil_pp_inRemaining__ON__silver_langutil_pp_Document)) - ((Integer)context.synthesized(silver.langutil.pp.Init.silver_langutil_pp_outRemaining__ON__silver_langutil_pp_Document)))); } };
		// top.outDq = top.inDq
		silver.langutil.pp.PrealLine.synthesizedAttributes[silver.langutil.pp.Init.silver_langutil_pp_outDq__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)context.inherited(silver.langutil.pp.Init.silver_langutil_pp_inDq__ON__silver_langutil_pp_Document)); } };
		// top.outCHorizontals = top.inCHorizontals
		silver.langutil.pp.PrealLine.synthesizedAttributes[silver.langutil.pp.Init.silver_langutil_pp_outCHorizontals__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.inherited(silver.langutil.pp.Init.silver_langutil_pp_inCHorizontals__ON__silver_langutil_pp_Document)); } };
		// top.outRemaining = top.width - top.indent
		silver.langutil.pp.PrealLine.synthesizedAttributes[silver.langutil.pp.Init.silver_langutil_pp_outRemaining__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.inherited(silver.langutil.pp.Init.silver_langutil_pp_width__ON__silver_langutil_pp_Document)) - ((Integer)context.inherited(silver.langutil.pp.Init.silver_langutil_pp_indent__ON__silver_langutil_pp_Document))); } };
		// top.result = "\n" ++ replicate(top.indent, " ")
		silver.langutil.pp.PrealLine.synthesizedAttributes[silver.langutil.pp.Init.silver_langutil_pp_result__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)((common.StringCatter)core.Preplicate.invoke(context.contextInheritedLazy(silver.langutil.pp.Init.silver_langutil_pp_indent__ON__silver_langutil_pp_Document), (new common.StringCatter(" "))))); } };
		// top.horizontals = []
		silver.langutil.pp.PrealLine.synthesizedAttributes[silver.langutil.pp.Init.silver_langutil_pp_horizontals__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };

	}

	public static final common.NodeFactory<PrealLine> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PrealLine> {

		@Override
		public PrealLine invoke(final Object[] children, final Object[] annotations) {
			return new PrealLine();
		}
	};

}
