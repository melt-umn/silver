package common;

import java.io.StringWriter;
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
 * <p>NOTE: string appends are done strictly, not lazily. We don't support building
 * infinite strings in Silver.
 * 
 * @author tedinski
 * @see Util#writeFile
 * @see Util#appendFile
 */
public final class StringCatter {
	
	private Object fst; // Either String or StringCatter
	private StringCatter snd; // If non-null, then we're an append!
	
	/**
	 * Just wrap the string up as a StringCatter.
	 * 
	 * @param str The string to represent
	 */
	public StringCatter(final String str) {
		fst = str;
		snd = null;
	}
	
	/**
	 * Append two StringCatters together.
	 * 
	 * @param sc1 The LHS
	 * @param sc2 The RHS
	 */
	public StringCatter(final StringCatter sc1, final StringCatter sc2) {
		fst = sc1;
		snd = sc2;
	}
	
	@Override
	public String toString() {
		if(snd == null) {
			return (String)fst;
		}
		
		// Allocate enough space right off the bat, by computing how much space is enough
		StringWriter sr = new StringWriter(length() + 1);
		// I don't know enough about Java internals to know if the +1 is necessary
		// or not to prevent a doubling in the buffer size from ever happening.
		
		// Build it
		try {
			((StringCatter)fst).write(sr);
			snd.write(sr);
		} catch (IOException e) {
			// This SHOULD be impossible.
			throw new RuntimeException(e);
		}
		
		// Mutate ourselves, referentially transparently
		fst = sr.toString();
		snd = null;
		return (String)fst;
	}
	
	@Override
	public boolean equals(final Object obj) {
		return toString().equals(obj.toString());
	}
	
	/**
	 * Determine the length of the represented string, without collapsing the append trees.
	 * 
	 * @return The string length
	 */
	public int length() {
		if(snd == null) {
			return ((String)fst).length();
		}
		return ((StringCatter)fst).length() + snd.length();
	}
	
	/**
	 * Writes out the string, without collapsing the append trees.
	 *  
	 * @param fout Where to write the string
	 * @throws IOException
	 */
	public void write(final Writer fout) throws IOException {
		if(snd == null) {
			fout.write((String)fst);
			return;
		}
		((StringCatter)fst).write(fout);
		snd.write(fout);
	}
	
}
