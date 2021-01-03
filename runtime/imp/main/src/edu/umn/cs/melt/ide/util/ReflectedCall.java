package edu.umn.cs.melt.ide.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import common.Node;
import common.OriginContext;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;

import silver.core.NIOVal;
import silver.core.NMaybe;
import silver.core.Pioval;
import silver.core.Pjust;
import silver.core.Pnothing;

/**
 * Helper to call a Silver function from Java code.
 *
 * Intention is for this object to be instantiated from `setInitializationData`
 * where CoreException is reasonable. We get as far as we can in the constructor
 * and then jam and error from the later call into RuntimeException.
 */
public final class ReflectedCall<T> {
	private Method method;
	
	private static String to_sv_class(String silver_function) {
		int i = silver_function.lastIndexOf(":");
		String before = silver_function.substring(0, i);
		String after = silver_function.substring(i + 1, silver_function.length());
		return before.replace(":", ".") + ".P" + after;
	}
	private static Class[] sv_args(int arity) {
		Class[] arg_types = new Class[arity + 1];
		Arrays.fill(arg_types, Object.class);
                arg_types[0] = OriginContext.class;
		return arg_types;
	}
	
	// "silver:core:map", 2
	public ReflectedCall(String silver_function, int arity) throws CoreException {
		this(to_sv_class(silver_function), "invoke", sv_args(arity));
	}
	// "silver.core.Pmap", "invoke", {Object, Object}
	public ReflectedCall(String class_name, String method_name, Class[] arg_types) throws CoreException {
		try {
			Class<Node> cls = (Class<Node>)Class.forName(class_name);
			this.method = cls.getMethod(method_name, arg_types);
		} catch (ClassNotFoundException e) {
			// TODO: figure out correct pluginId?
			throw new CoreException(
				new Status(Status.ERROR, "edu.umn.cs.melt.eclipse", "Cannot find " + class_name, e));
		} catch (NoSuchMethodException e) {
			// TODO: figure out correct pluginId?
			throw new CoreException(
				new Status(Status.ERROR, "edu.umn.cs.melt.eclipse", "Cannot find valid invocation for " + method_name, e));
		}
	}
	
	// call this static method
	public T invoke(Object[] args) {
		try {
			return (T) method.invoke(null, args);
		} catch (InvocationTargetException | IllegalAccessException e) {
			// We don't do CoreException here because we might not be called in a context where
			// that's acceptable to throw (it's a checked exception).
			throw new RuntimeException("Reflected call failed", e);
		}
	}
	// call this NONstatic method
	public T invokeOn(Object obj, Object[] args) {
		try {
			return (T) method.invoke(obj, args);
		} catch (InvocationTargetException | IllegalAccessException e) {
			// We don't do CoreException here because we might not be called in a context where
			// that's acceptable to throw (it's a checked exception).
			throw new RuntimeException("Reflected call failed", e);
		}
	}
	
	// Instantiate using zero-arg constructor an object of `class_name`
	public static Object newInstance(String class_name) throws CoreException {
		try {
			return Class.forName(class_name).getConstructor().newInstance();
		} catch (ClassNotFoundException e) {
			// TODO: figure out correct pluginId?
			throw new CoreException(
				new Status(Status.ERROR, "edu.umn.cs.melt.eclipse", "Cannot find " + class_name, e));
		} catch (NoSuchMethodException e) {
			// TODO: figure out correct pluginId?
			throw new CoreException(
				new Status(Status.ERROR, "edu.umn.cs.melt.eclipse", "Cannot find valid invocation for 0-arg constructor", e));
		} catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
			// TODO: figure out correct pluginId?
			throw new CoreException(
				new Status(Status.ERROR, "edu.umn.cs.melt.eclipse", "Cannot invoke constructor", e));
		}
	}
}
