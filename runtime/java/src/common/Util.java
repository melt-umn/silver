package common;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.*;

import common.exceptions.*;


/**
 * Many places in Silver's translation are bits of code that need factoring out, somehow.
 * 
 * <p>The most common justification is the need to do several statements, while being in the middle
 * of an expression.
 * 
 * @author tedinski, bodin
 */
public final class Util {
	/**
	 * Ensures that a (potential) closure is evaluated.
	 *
	 * Use by writing  (v = Util.demand(v)), never just invoke it!
	 *
	 * @param c  Either a value, or a Closure
	 * @return The value, either directly, or evaluating the Closure
	 */
	public static Object demand(Object c) {
		if(c instanceof Thunk)
			return ((Thunk<?>)c).eval();
		return c;
	}

	/**
	 * Turns a list of names and values into a map.
	 * 
	 * <p>Used by the 'decorate ... with { THIS PART }' syntax.
	 */
	public static Lazy[] populateInh(final int size, final int[] idx, final Lazy[] val) {
		final Lazy[] result = new Lazy[size];
		for(int i = 0; i < idx.length; i++) {
			result[idx[i]] = val[i];
		}
		return result;
	}

	/**
	 * Exit, of course!
	 * 
	 * <p>This is here because it has to return Object to be used in expressions.
	 * 
	 * @param status the exit status code
	 * @return Does not return.
	 */
	public static Object exit(int status) {
		throw new SilverExit(status);
	}
	
	/**
	 * Used by the 'error("wat")' syntax in Silver.
	 * 
	 * @param o the "wat"
	 * @return Does not return.
	 */
	public static Object error(Object o) {
		System.err.print(o);
		throw new SilverError(o.toString());
	}
	
	public static core.NMaybe safetoInt(String s) {
		try {
			return new core.Pjust( Integer.valueOf(s) );
		} catch(NumberFormatException e) {
			return new core.Pnothing();
		}
	}

	public static boolean isAlpha(String sb) {
		boolean result = true;
		for (int i = 0; result && i < sb.length(); i++) {
			result = Character.isLetter(sb.charAt(i));
		}
		return result;
	}

	public static boolean isDigit(String sb) {
		boolean result = true;
		for (int i = 0; result && i < sb.length(); i++) {
			result = Character.isDigit(sb.charAt(i));
		}
		return result;
	}

	public static boolean isSpace(String sb) {
		boolean result = true;
		for (int i = 0; result && i < sb.length(); i++) {
			result = Character.isWhitespace(sb.charAt(i));
		}
		return result;
	}

	public static boolean isUpper(String sb) {
		boolean result = true;
		for (int i = 0; result && i < sb.length(); i++) {
			result = Character.isUpperCase(sb.charAt(i));
		}
		return result;
	}

	public static boolean isLower(String sb) {
		boolean result = true;
		for (int i = 0; result && i < sb.length(); i++) {
			result = Character.isLowerCase(sb.charAt(i));
		}
		return result;
	}

	public static int fileTime(String sb) {
		return (int) ((new File(sb).lastModified()) / 1000);
	}
	
	public static Object touchFile(String sb) {
		return setFileTime(sb, currentTime());
	}
	
	public static Object setFileTime(String sb, int time) {
		new File(sb).setLastModified(((long)time) * 1000);
		return null;
	}
	
	public static int currentTime() {
		return (int)(System.currentTimeMillis() / 1000);
	}

	public static boolean isFile(String sb) {
		return new File(sb).isFile();
	}

	public static boolean isDirectory(String sb) {
		return new File(sb).isDirectory();
	}
	
	public static boolean mkdir(String sb) {
		return new File(sb).mkdirs();
	}

	public static boolean deleteFile(String sb) {
		return new File(sb).delete();
	}
	
    public static StringCatter getStr ( ) {
	try {
	    InputStreamReader isr = new InputStreamReader(System.in);
	    BufferedReader br = new BufferedReader(isr);
	    String s = br.readLine();
	    return new StringCatter (s) ;
	}
	catch (IOException e) {
	    String s = "IO EXCEPTION" ;
	    return new StringCatter (s) ;
	}

    }

	public static Object copyFile(String from, String to) {
		// TODO: When we're good to depend on Java 7, there's Files.copy. This method should work for 4:
	    FileChannel source = null;
	    FileChannel destination = null;

	    try {
			File toFile = new File(to);
			File fromFile = new File(from);
		    if(!toFile.exists()) {
		        toFile.createNewFile();
		    }

	        source = new FileInputStream(fromFile).getChannel();
	        destination = new FileOutputStream(toFile).getChannel();
	        destination.transferFrom(source, 0, source.size());
	        
	        source.close();
	        destination.close();
	        return null;
	    } catch(Exception io) {
	    	throw new RuntimeException(io);
	    }
	}
	
	/**
	 * Slurps the contents of a file into a string.  May cause IO exceptions.
	 * 
	 * @param sb  The filename
	 * @return  The file contents.
	 */
	public static StringCatter readFile(String filename) {
		try {
			// TODO: Java 7 : Files.readAllBytes(...)
			File file = new File(filename);
			FileInputStream fis = new FileInputStream(file);
			DataInputStream in = new DataInputStream(fis);
			byte[] b = new byte[(int)file.length()];
			in.readFully(b); // The only reason we use DataInputStream is this method.
			in.close();
			fis.close();
			// TODO: is there a better way of handling \r\n? (Maybe implicitly in copper specs?)
			// (We should also probably be more discriminating about charsets)
			return new StringCatter(new String(b).replace("\r\n","\n"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static StringCatter cwd() {
		return new StringCatter(System.getProperty("user.dir"));
	}
	
	/**
	 * We have a (public) copy of the environment because RunSilver may need to modify it.
	 * 
	 * Sadly, there does not appear to be any way to do with without making our own copy
	 * of the entire environment.
	 */
	public static Map<String, String> environment = new TreeMap<String, String>(System.getenv());

	public static StringCatter env(String sb) {
		String result = environment.get(sb);
		if (result == null)
			return new StringCatter(""); // Is this the right reply?
		else
			return new StringCatter(result);
	}

	public static int system(String sb) {
		try {
			String cmdstr[] = {"bash", "-c", sb}; // TODO: platform dependency!
			Process p = Runtime.getRuntime().exec(cmdstr);
			p.waitFor();
			return p.exitValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Write to a file, truncating anything there already. Used by 'writeFile' in silver.
	 * 
	 * <p>Avoids demanding a StringCatter.
	 * 
	 * @param file The filename
	 * @param content Either a String or {@link StringCatter} object.
	 * @return null, the IO object.
	 */
	public static Object writeFile(String file, Object content) {
		try {
			// TODO: Java 7 : Files.write
			Writer fout = new FileWriter(file); // already buffered
			if(content instanceof StringCatter)
				((StringCatter)content).write(fout);
			else
				fout.write(content.toString());
			fout.flush();
			fout.close();
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Write to a file, appending onto the end of anything there already. Used by 'appendFile' in silver.
	 * 
	 * <p>Avoids demanding a StringCatter.
	 * 
	 * @param file The filename
	 * @param content Either a String or {@link StringCatter} object.
	 * @return null, the IO object.
	 */
	public static Object appendFile(String file, Object content) { // TODO: merge with above!
		try {
			Writer fout = new FileWriter(file, true); // already buffered
			if(content instanceof StringCatter)
				((StringCatter)content).write(fout);
			else
				fout.write(content.toString());
			fout.flush();
			fout.close();
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Object print(String sb) {
		// TODO: should we avoid demanding stringcatter objects?
		System.out.print(sb);
		return null;
	}

	/**
	 * Lists the contents of a directory.
	 * 
	 * @param sb The directory to list the contents of.
	 * @return A list of Strings
	 */
	public static ConsCell listContents(String sb) {
		try {
			File f = new File(sb);
			String[] files = f.list();

			ConsCell result = ConsCell.nil;
			
			if(files == null)
				return result;
			
			for (String file : files) {
				result = new ConsCell(new StringCatter(file), result);
			}
			
			return result;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * This exists only because some things like {@link Util#writeFile} don't take an IO object, so
	 * we use this to demand the io object, then call writeFile.
	 * 
	 * <p>In other cases, we're actually returning something, like system, and the call to the IOInteger
	 * nonterminal constructor takes care of demanding the old IO object.
	 * 
	 * @param i First thing to do
	 * @param o Second thing to do
	 * @return null, the IO object.
	 */
	public static Object io(Object i, Object o) {
		return null;
	}

	private static int i = 0;
	public static int genInt() {
		return i++;
	}
	
	public static void printStackCauses(Throwable e) {
		System.err.println("\nAn error occured.  Silver stack trace follows. (To see full traces including java elements, SILVERTRACE=1)\n");
		
		if(! "1".equals(System.getenv("SILVERTRACE"))) {
			Throwable t = e;
			while(t != null) {
				StackTraceElement st[] = t.getStackTrace();
				
				String msg = t.getLocalizedMessage();
				if(msg == null) // Some exceptions have no message... apparently.
					msg = t.toString();
				
				if(st.length == 0) // Some exceptions don't seem to occur anywhere... somehow.
					System.err.println("(??): " + msg);
				else if(st[0].getClassName().startsWith("common."))
					// This will give error messages like (DN.146) corresponding to DecoratedNode.java:146
					System.err.println("(" + st[0].getFileName().replaceAll("[a-z]", "") + st[0].getLineNumber() + "): " + msg);
				else
					// Be more explicit about where when it's not one of ours
					System.err.println("(" + st[0].getClassName() + " in " + st[0].getFileName() + ":" + st[0].getLineNumber() + "): " + msg);
				
				String lastCause = t.getLocalizedMessage();
				int repeats = 0;
				t = t.getCause();
				while(t != null && lastCause.equals(t.getLocalizedMessage())) {
					repeats++;
					t = t.getCause();
				}
				if(repeats > 0) {
					System.err.println("\t(last line repeats " + repeats + " more times)");
				}
			}
			
			System.exit(-2);		
		} else {
			// Displaying it by rethrowing it.
			throw new RuntimeException(e);
		}
	}
	
	// These are written un-ideally so that they're all confined in one place.
	public static StringCatter hackyhackyUnparse(Object o) {
		StringBuilder sb = new StringBuilder();
		
		hackyhackyUnparseObject(o, sb);
		
		return new StringCatter(sb.toString());
	}
	
	private static void hackyhackyUnparseObject(Object o, StringBuilder sb) {
		if(o instanceof Node) {
			hackyhackyUnparseNode((Node)o, sb);
		} else if(o instanceof DecoratedNode) {
			// For the time being, just undecorate it
			hackyhackyUnparseNode(((DecoratedNode)o).undecorate(), sb);
		} else if(o instanceof TerminalRecord) {
			TerminalRecord t = (TerminalRecord) o;
			sb.append("'" + t.lexeme + "'");
		} else if(o instanceof StringCatter) {
			sb.append("\"" + o.toString() + "\"");
		} else if(o instanceof Integer ||
				  o instanceof Float ||
    			  o instanceof Boolean) {
			sb.append(o.toString());
		} else if(o instanceof ConsCell) {
			hackyhackyUnparseList((ConsCell)o, sb);
		} else {
			sb.append("<OBJ>");
		}
	}
	private static void hackyhackyUnparseNode(Node n, StringBuilder sb) {
		sb.append(n.getName() + "(");
		for(int i = 0; i < n.getNumberOfChildren(); i++) {
			if(i != 0) {
				sb.append(", ");
			}
			hackyhackyUnparseObject(n.getChild(i), sb);
			//System.out.println(sb.toString());
		}
		sb.append(")");
	}
	private static void hackyhackyUnparseList(ConsCell c, StringBuilder sb) {
		sb.append("[");
		ConsCell i = c;
		while(!i.nil()) {
			if(i != c) {
				sb.append(", ");
			}
			hackyhackyUnparseObject(i.head(), sb);
			i = i.tail();
		}
		sb.append("]");
	}
}
