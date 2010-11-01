package common;

import java.io.Writer;
import java.io.IOException;

/**
 * This class is an optimization for string appending, which is an extremely common operation
 * in Silver.  We build up a lazy tree of the appends, and only ever create the String object
 * when it's demanded.
 * 
 * <p>Further, if we're demanding it to write to a file, then we still don't even build the
 * String object.
 * 
 * @author tedinski
 * @see Util#writeFile
 * @see Util#appendFile
 */
public class StringCatter {
	// TODO: We could probably eliminate that interface, and just give this class
	// two fields.  I'm not sure it's saving us anything.

	private StrCattable str;
	
	public StringCatter(String str) {
		this.str = new StrLiteral(str);
	}
	public StringCatter(StringCatter s) {
		this.str = s.str;
		// Could probably eliminate this case but eh.
	}
	
	public StringCatter append(StringCatter sc) {
		str = new StrAppend(str,sc.str);
		return this;
	}
	@Override
	public String toString() {
		if(str instanceof StrLiteral) {
			return ((StrLiteral)str).str;
		}
		// Allocate enough space right off the bat
		StringBuilder sb = new StringBuilder(str.length());
		// Build it
		str.build(sb);
		String result = sb.toString();
		// Cache the result and return
		str = new StrLiteral(result);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		return toString().equals(obj.toString());
	}
	
	public int length() {
		return str.length();
	}
	// Things like substring and indexOf are done via toString() first
	// There doesn't seem to be a point to trying to do that here
	// also, compareTo and equals are also via toString.
	
	// We can do file output without constructing the final, appended string :)
	public void write(Writer fout) throws IOException {
		str.write(fout);
	}
	
	private static interface StrCattable {
		public int length();
		public void build(StringBuilder sb);
		public void write(Writer fout) throws IOException;
	}
	
	private static class StrLiteral implements StrCattable {
		private final String str;
		StrLiteral(String s) {
			str = s;
		}
		@Override
		public void build(StringBuilder sb) {
			sb.append(str);
		}
		@Override
		public int length() {
			return str.length();
		}
		@Override
		public void write(Writer fout) throws IOException {
			fout.write(str);
		}
	}
	
	private static class StrAppend implements StrCattable {
		private StrCattable left, right;
		StrAppend(StrCattable l, StrCattable r) {
			left = l; right = r;
		}
		@Override
		public void build(StringBuilder sb) {
			left.build(sb);
			right.build(sb);
		}
		@Override
		public int length() {
			return left.length() + right.length();
		}
		@Override
		public void write(Writer fout) throws IOException {
			left.write(fout);
			right.write(fout);
		}
	}
}
