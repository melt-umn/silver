package common;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.*;


/**
 * Many places in Silver's translation are bits of code that need factoring out, somehow.
 * 
 * <p>The most common justification is the need to do several statements, while being in the middle
 * of an expression.
 * 
 * @author tedinski, bodin
 */
public class Util {
	/**
	 * Used for function/production references.  e.g. things of type "Function(Bool ::= Integer)"
	 * 
	 * <p>This is a function here due to our wrapping of the exception in a RuntimeException object.
	 * 
	 * @param c a child of {@link Node}, with a constructor of type Object[]
	 * @return the constructor obtained through reflection
	 * @see #construct
	 */
	public static Constructor<?> getConstruct(Class<?> c) {
		try {
			return c.getConstructor(Object[].class);
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}
	}
	
	/**
	 * For using function/production references. e.g. things of type "Function(Bool ::= Integer)"
	 * 
	 * <p>This is a function here due to our wrapping of the exception in a RuntimeException object.
	 * 
	 * @param c obtained using {@link #getConstruct}
	 * @param o the children list
	 * @return the constructed Node object
	 * @see #getConstruct
	 */
	public static Object construct(Constructor<?> c, Object[] o) {
		try {
			return c.newInstance(new Object[] {o});
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Turns a list of names and values into a map.
	 * 
	 * <p>Used by the 'decorate ... with { THIS PART }' syntax.
	 */
	public static <K, V> Map<K, V> populateMap(K[] names, V[] values) {
		Map<K, V> result = new TreeMap<K, V>();
		for (int i = 0; i < names.length; i++) {
			result.put(names[i], values[i]);
		}
		return result;
	}

	/**
	 * Used by the horrible hack that is let expressions in Silver.
	 * 
	 * @return the result of evaluating expr in context, with new local attributes jammed into it.
	 */
	public static Object let(common.DecoratedNode context, String[] names, Lazy[] values, Lazy expr) {
		
		if(context.extraLocalAttributes == null)
			context.extraLocalAttributes = new TreeMap<String, Lazy>();
		
		for (int i = 0; i < names.length; i++)
			context.extraLocalAttributes.put(names[i], values[i]);

		// It's not possible to remove these after the eval! (Lazys may still be around
		// with references to these let attributes!)
		
		return expr.eval(context);
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
		System.exit(status);
		return null;
	}
	
	/**
	 * Used by the 'error("wat")' syntax in Silver.
	 * 
	 * @param o the "wat"
	 * @return Does not return.
	 */
	public static Object error(Object o) {
		System.err.print(o);
		throw new RuntimeException(o.toString());
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

	public static boolean isFile(String sb) {
		return new File(sb).isFile();
	}

	public static boolean isDirectory(String sb) {
		return new File(sb).isDirectory();
	}
	
	public static boolean mkdir(String sb) {
		return new File(sb).mkdirs();
	}

	public static StringCatter readFile(String sb) {
		try {
			FileInputStream file = new FileInputStream(sb);
			DataInputStream in = new DataInputStream(file);
			byte[] b = new byte[in.available()];
			in.readFully(b);
			in.close();
			file.close();
			// TODO: is there a better way of handling \r\n?
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
			Writer fout = new FileWriter(file);
			if(content instanceof StringCatter)
				((StringCatter)content).write(fout = new BufferedWriter(fout));
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
	public static Object appendFile(String file, Object content) {
		try {
			Writer fout = new FileWriter(file, true);
			if(content instanceof StringCatter)
				((StringCatter)content).write(fout = new BufferedWriter(fout));
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
		// TODO: oh god, get rid of this insanity with Pempty and such!
		try {
			File f = new File(sb);
			String[] files = f.list();

			ConsCell result = ConsCell.nil;
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
		System.err.println("An error occured.  Silver stack trace follows. (To see full traces including java elements, SILVERTRACE=1)\n");
		
		if(! "1".equals(System.getenv("SILVERTRACE"))) {
			Throwable t = e;
			while(t != null) {
				StackTraceElement st[] = t.getStackTrace();
				
				if(st[0].getClassName().startsWith("common."))
					// This will give error messages like (DN.146) corresponding to DecoratedNode.java:146
					System.err.println("(" + st[0].getFileName().replaceAll("[a-z]", "") + st[0].getLineNumber() + "): " + t.getLocalizedMessage());
				else
					// Be more explicit about where when it's not one of ours
					System.err.println("(" + st[0].getClassName() + " in " + st[0].getFileName() + ":" + st[0].getLineNumber() + "): " + t.getLocalizedMessage());
				
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
			throw new RuntimeException("SILVERTRACE=1, letting Java handle the exception...", e);
		}
	}
	
	// These are written un-ideally so that they're all confined in one place.
	public static StringCatter hackyhackyUnparse(Object o) {
		if(o instanceof DecoratedNode) {
			o = ((DecoratedNode)o).undecorate();
		}
		Node n = (Node) o;
		
		StringBuilder sb = new StringBuilder();
		
		hackyhackyUnparseNode(n, sb);
		
		return new StringCatter(sb.toString());
	}
	
	private static void hackyhackyUnparseNode(Node n, StringBuilder sb) {
		sb.append(n.getName() + "(");
		for(int i = 0; i < n.getNumberOfChildren(); i++) {
			if(i != 0) {
				sb.append(", ");
			}
			Object c = n.getChild(i);
			if(c instanceof Node) {
				hackyhackyUnparseNode((Node)c, sb);
			} else if(c instanceof TerminalRecord) {
				TerminalRecord t = (TerminalRecord) c;
				sb.append("'" + t.lexeme + "'");
			} else {
				sb.append("<OBJ>");
			}
		}
		sb.append(")");
	}
}
