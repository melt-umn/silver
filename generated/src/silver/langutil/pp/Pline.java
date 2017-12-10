
package silver.langutil.pp;

// top::Document ::= 
public final class Pline extends silver.langutil.pp.NDocument {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_langutil_pp_line;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.langutil.pp.NDocument.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.langutil.pp.NDocument.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public Pline() {
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
		throw new common.exceptions.SilverInternalError("Production silver:langutil:pp:line erroneously claimed to forward");
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
		return "silver:langutil:pp:line";
	}

	static void initProductionAttributeDefinitions() {
		// pr = prune(top.outPosition, top.inDq)
		silver.langutil.pp.Pline.localAttributes[silver.langutil.pp.Init.pr__ON__silver_langutil_pp_line] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NPair)silver.langutil.pp.Pprune.invoke(context.contextSynthesizedLazy(silver.langutil.pp.Init.silver_langutil_pp_outPosition__ON__silver_langutil_pp_Document), context.contextInheritedLazy(silver.langutil.pp.Init.silver_langutil_pp_inDq__ON__silver_langutil_pp_Document))); } };
		// horizontal = head(top.inCHorizontals)
		silver.langutil.pp.Pline.localAttributes[silver.langutil.pp.Init.horizontal__ON__silver_langutil_pp_line] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)core.Phead.invoke(context.contextInheritedLazy(silver.langutil.pp.Init.silver_langutil_pp_inCHorizontals__ON__silver_langutil_pp_Document))); } };
		// top.outPosition = top.inPosition + 1
		silver.langutil.pp.Pline.synthesizedAttributes[silver.langutil.pp.Init.silver_langutil_pp_outPosition__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.inherited(silver.langutil.pp.Init.silver_langutil_pp_inPosition__ON__silver_langutil_pp_Document)) + Integer.valueOf((int)1)); } };
		// top.outDq = pr.fst
		silver.langutil.pp.Pline.synthesizedAttributes[silver.langutil.pp.Init.silver_langutil_pp_outDq__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)context.localDecorated(silver.langutil.pp.Init.pr__ON__silver_langutil_pp_line).synthesized(core.Init.core_fst__ON__core_Pair)); } };
		// top.outCHorizontals = top.inCHorizontals
		silver.langutil.pp.Pline.synthesizedAttributes[silver.langutil.pp.Init.silver_langutil_pp_outCHorizontals__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.inherited(silver.langutil.pp.Init.silver_langutil_pp_inCHorizontals__ON__silver_langutil_pp_Document)); } };
		// top.outRemaining = if horizontal then top.inRemaining - 1 else top.width - top.indent
		silver.langutil.pp.Pline.synthesizedAttributes[silver.langutil.pp.Init.silver_langutil_pp_outRemaining__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localAsIs(silver.langutil.pp.Init.horizontal__ON__silver_langutil_pp_line)) ? Integer.valueOf(((Integer)context.inherited(silver.langutil.pp.Init.silver_langutil_pp_inRemaining__ON__silver_langutil_pp_Document)) - Integer.valueOf((int)1)) : Integer.valueOf(((Integer)context.inherited(silver.langutil.pp.Init.silver_langutil_pp_width__ON__silver_langutil_pp_Document)) - ((Integer)context.inherited(silver.langutil.pp.Init.silver_langutil_pp_indent__ON__silver_langutil_pp_Document)))); } };
		// top.result = if horizontal then " " else "\n" ++ replicate(top.indent, " ")
		silver.langutil.pp.Pline.synthesizedAttributes[silver.langutil.pp.Init.silver_langutil_pp_result__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localAsIs(silver.langutil.pp.Init.horizontal__ON__silver_langutil_pp_line)) ? (new common.StringCatter(" ")) : new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)((common.StringCatter)core.Preplicate.invoke(context.contextInheritedLazy(silver.langutil.pp.Init.silver_langutil_pp_indent__ON__silver_langutil_pp_Document), (new common.StringCatter(" ")))))); } };
		// top.horizontals = pr.snd
		silver.langutil.pp.Pline.synthesizedAttributes[silver.langutil.pp.Init.silver_langutil_pp_horizontals__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.langutil.pp.Init.pr__ON__silver_langutil_pp_line).synthesized(core.Init.core_snd__ON__core_Pair)); } };

	}

	public static final common.NodeFactory<Pline> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pline> {

		@Override
		public Pline invoke(final Object[] children, final Object[] annotations) {
			return new Pline();
		}
	};

}
