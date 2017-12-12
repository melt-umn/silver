
package silver.definition.concrete_syntax.ast;

public final class PxmlEscapeChar extends common.FunctionNode {

	public static final int i_ch = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_xmlEscapeChar;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PxmlEscapeChar(final Object c_ch) {
		this.child_ch = c_ch;

	}

	private Object child_ch;
	public final common.StringCatter getChild_ch() {
		return (common.StringCatter) (child_ch = common.Util.demand(child_ch));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ch: return getChild_ch();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ch: return child_ch;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return "silver:definition:concrete_syntax:ast:xmlEscapeChar";
	}

	public static common.StringCatter invoke(final Object c_ch) {
		try {
		final common.DecoratedNode context = new PxmlEscapeChar(c_ch).decorate();
		//if ch == ">" then "&gt;" else if ch == "<" then "&lt;" else if ch == "&" then "&amp;" else if ch == "\"" then "&quot;" else ch
		return (common.StringCatter)((((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.ast.PxmlEscapeChar.i_ch)).equals((new common.StringCatter(">"))) ? (new common.StringCatter("&gt;")) : (((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.ast.PxmlEscapeChar.i_ch)).equals((new common.StringCatter("<"))) ? (new common.StringCatter("&lt;")) : (((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.ast.PxmlEscapeChar.i_ch)).equals((new common.StringCatter("&"))) ? (new common.StringCatter("&amp;")) : (((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.ast.PxmlEscapeChar.i_ch)).equals((new common.StringCatter("\""))) ? (new common.StringCatter("&quot;")) : ((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.ast.PxmlEscapeChar.i_ch)))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:concrete_syntax:ast:xmlEscapeChar", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PxmlEscapeChar.invoke(children[0]);
		}
	};
}