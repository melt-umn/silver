package common;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

import common.exceptions.*;

import core.reflect.*;

/**
 * Implementation of the Silver reflection library
 * 
 * @author krame505
 */
public final class Reflection {
	/**
	 * Implementation of the reflect operation for an arbitrary type.
	 * 
	 * @param o The object to reflect.
	 * @return The reflected AST.
	 */
	public static NAST reflect(final Object o) {
		if(o instanceof Node) {
			Node n = (Node)o;
			NASTs children = new PnilAST();
			for (int i = n.getNumberOfChildren() - 1; i >= 0; i--) {
				Object value = reflect(n.getChild(i));
				children = new PconsAST(value, children);
			}
			String[] annotationNames = n.getAnnoNames();
			NNamedASTs annotations = new PnilNamedAST();
			for (int i = annotationNames.length - 1; i >= 0; i--) {
				String name = annotationNames[i];
				Object value = reflect(n.getAnno(name));
				annotations = new PconsNamedAST(new PnamedAST(new StringCatter(name), value), annotations);
			}
			return new PnonterminalAST(new StringCatter(n.getName()), children, annotations);
		} else if(o instanceof ConsCell) {
			return new PlistAST(reflectList((ConsCell)o));
		} else if(o instanceof StringCatter) {
			return new PstringAST((StringCatter)o);
		} else if(o instanceof Integer) {
			return new PintegerAST((Integer)o);
		} else if(o instanceof Float) {
			return new PfloatAST((Float)o);
		} else if(o instanceof Boolean) {
			return new PbooleanAST((Boolean)o);
		} else {
			return new PforeignAST(o);
		}
	}
	private static NASTs reflectList(final ConsCell l) {
		if (!l.nil()) {
			return new PconsAST(reflect(l.head()), reflectList(l.tail()));
		} else {
			return new PnilAST();
		}
	}
	
	/**
	 * Implementation of the reify operation for an arbitrary type.
	 * 
	 * @param resultType The type of tree to be constructed.
	 * @param ast The AST to reify.
	 * @return The constructed object.
	 */
	public static Object reify(final TypeRep resultType, final NAST ast) {
		if (ast.getName().equals("core:reflect:nonterminalAST")) {
			final String prodName = ((StringCatter)ast.getChild(0)).toString();
			final List<NAST> childASTs = new ArrayList<>(5);
			for (NASTs current = (NASTs)ast.getChild(1); !current.getName().equals("core:reflect:nilAST"); current = (NASTs)current.getChild(1)) {
				childASTs.add((NAST)current.getChild(0));
			}
			final Map<String, NAST> annotationASTs = new TreeMap<>();
			for (NNamedASTs current = (NNamedASTs)ast.getChild(2); !current.getName().equals("core:reflect:nilNamedAST"); current = (NNamedASTs)current.getChild(1)) {
				NNamedAST item = (NNamedAST)current.getChild(0);
				annotationASTs.put(item.getChild(0).toString(), (NAST)item.getChild(1));
			}

			// Invoke the reify function for the appropriate nonterminal class
			final String[] path = prodName.split(":");
			path[path.length - 1] = "P" + path[path.length - 1];
			final String className = String.join(".", path);
			try {
				return Class.forName(className).getMethod("reify", TypeRep.class, List.class, Map.class).invoke(null, resultType, childASTs, annotationASTs);
			} catch (ClassNotFoundException e) {
				throw new SilverError("Undefined production " + prodName);
			} catch (NoSuchMethodException | IllegalAccessException e) {
				throw new SilverInternalError("Error invoking reify for class " + className, e);
			} catch (InvocationTargetException e) {
				if (e.getTargetException() instanceof SilverException) {
					throw (SilverException)e.getTargetException();
				} else {
					throw new SilverInternalError("Error invoking reify for class " + className, e.getTargetException());
				}
			}
		} else if (ast.getName().equals("core:reflect:listAST")) {
			final TypeRep paramType = new VarTypeRep();
			if (!resultType.unify(new ListTypeRep(paramType), true)) {
				throw new SilverError("reify is constructing " + resultType.toString() + ", but found list AST.");
			}
			return reifyList(paramType, (NASTs)ast.getChild(0));
		} else {
			// Construct the TypeRep correpsonding to the given object
			TypeRep givenType;
			if (ast.getName().equals("core:reflect:stringAST")) {
				givenType = new BaseTypeRep("String");
			} else if (ast.getName().equals("core:reflect:integerAST")) {
				givenType = new BaseTypeRep("Integer");
			} else if (ast.getName().equals("core:reflect:floatAST")) {
				givenType = new BaseTypeRep("Float");
			} else if (ast.getName().equals("core:reflect:booleanAST")) {
				givenType = new BaseTypeRep("Boolean");
			} else if (ast.getName().equals("core:reflect:foreignAST")) {
				givenType = null; // TODO
				throw new UnsupportedOperationException("Not yet implemented");
			} else {
				throw new SilverInternalError("Unexpected AST production " + ast.getName());
			}
			// Perform unification with type vars as skolems
			if (!resultType.unify(givenType, false)) {
				throw new SilverError("reify is constructing " + resultType.toString() + ", but found " + givenType.toString() + " AST.");
			}
			return ast.getChild(0);
		}
	}
	// Recursive helper to walk the ASTs tree and build a list
	private static ConsCell reifyList(final TypeRep resultParamType, final NASTs asts) {
		if (asts.getName().equals("core:reflect:consAST")) {
			return new ConsCell(reify(resultParamType, (NAST)asts.getChild(0)), reifyList(resultParamType, (NASTs)asts.getChild(1)));
		} else if (asts.getName().equals("core:reflect:nilAST")) {
			return ConsCell.nil;
		} else {
			throw new SilverInternalError("Unexpected ASTs production" + asts.getName());
		}
	}
}
