package edu.umn.cs.melt.ide.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import common.Node;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;

import core.NIOVal;
import core.NMaybe;
import core.Pioval;
import core.Pjust;
import core.Pnothing;

/**
 * Helper to call a Silver function from Java code.
 *
 * Intention is for this object to be instantiated from `setInitializationData`
 * where CoreException is reasonable. We get as far as we can in the constructor
 * and then jam and error from the later call into RuntimeException.
 */
public final class ReflectedCall<T> {
	private Method method;
	
	public ReflectedCall(String silver_function, int arity) throws CoreException {
		int i = silver_function.lastIndexOf(":");
		String before = silver_function.substring(0, i);
		String after = silver_function.substring(i + 1, silver_function.length());
		
		// 'a:b:c' to 'a.b.Pc'
		String class_name = before.replace(":", ".") + ".P" + after;
		
		try {
			Class<Node> cls = (Class<Node>)Class.forName(class_name);
			Class[] arg_types = new Class[arity];
			Arrays.fill(arg_types, Object.class);
		
			method = cls.getMethod("invoke", arg_types);
		} catch (ClassNotFoundException e) {
			// TODO: figure out correct pluginId?
			throw new CoreException(
				new Status(Status.ERROR, "edu.umn.cs.melt.eclipse", "Cannot find " + silver_function, e));
		} catch (NoSuchMethodException e) {
			// TODO: figure out correct pluginId?
			throw new CoreException(
				new Status(Status.ERROR, "edu.umn.cs.melt.eclipse", "Cannot find valid invocation for " + silver_function, e));
		}
	}
	
	public T invoke(Object[] args) {
		try {
			return (T) method.invoke(null, args);
		} catch (InvocationTargetException | IllegalAccessException e) {
			// We don't do CoreException here because we might not be called in a context where
			// that's acceptable to throw (it's a checked exception).
			throw new RuntimeException("Reflected call failed", e);
		}
	}
}
