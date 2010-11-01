package common;

import java.util.Map;

/**
 * This is the old style of representing terminals that is no longer used.
 * 
 * Remove as soon as we no longer have stale code floating around.
 *
 * @author tedinski
 * @deprecated
 * @see TerminalRecord
 */
public class Terminal extends DecoratedNode {

	public Terminal(final String lexeme, final Integer line, final Integer column) {
		// TODO: Eventually, we need a 'name' here, not just the lexeme
		super(new TerminalNode(lexeme, lexeme, line, column, ""), null, (DecoratedNode)null);
	}
	public Terminal(final String lexeme, final Integer line, final Integer column, final String file) {
		// TODO: here too
		super(new TerminalNode(lexeme, lexeme, line, column, file), null, (DecoratedNode)null);
	}
	public Terminal(final String lexeme, final DecoratedNode from) {
		// TODO: here too
		super(new TerminalNode(lexeme, lexeme,
				        (Integer)from.synthesized("line"),
				        (Integer)from.synthesized("column"),
				        from.synthesized("filename").toString()), null, (DecoratedNode)null);
	}

	static private class TerminalNode extends Node {
		private final String name;
		private final String lexeme;
		private final String filename;
		private final Integer line;
		private final Integer column;

		public TerminalNode(String name, String lexeme, Integer line, Integer column, String file) {
			super(null);
			this.name = name;
			this.lexeme = lexeme;
			this.filename = file;
			this.line = line;
			this.column = column;
		}

		@Override
		public Lazy getSynthesized(String name) {
			if(name.equals("lexeme")) {
				return new Lazy() {
					public Object eval(DecoratedNode context) {
						return new StringCatter(lexeme);
					};
				};
			} else if(name.equals("line")) {
				return new Lazy() {
					public Object eval(DecoratedNode context) {
						return new Integer(line);
					};
				};
			} else if(name.equals("column")) {
				return new Lazy() {
					public Object eval(DecoratedNode context) {
						return new Integer(column);
					};
				};
			} else if(name.equals("filename")) {
				return new Lazy() {
					public Object eval(DecoratedNode context) {
						return new StringCatter(filename);
					};
				};
			} else {
				return null;
			}
		}

		@Override
		public Object getChild(int child) {
			throw new RuntimeException("Terminals have no children.");
		}

		@Override
		public Map<String, Lazy> getDefinedInheritedAttributes(Object key) {
			throw new RuntimeException("Terminals have no children.");
		}

		@Override
		public Lazy getForward() {
			throw new RuntimeException("Terminals do not forward.");
		}

		@Override
		public Lazy getForwardInh(String name) {
			throw new RuntimeException("Terminals do not forward.");
		}

		@Override
		public Lazy getLocal(String name) {
			throw new RuntimeException("Terminals have no locals.");
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public int getNumberOfChildren() {
			return 0;
		}
	}
}
