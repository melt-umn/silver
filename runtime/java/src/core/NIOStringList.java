package core;

import java.util.*;

public abstract class NIOStringList extends common.Node {

	public static final HashSet<String> occurs = new HashSet<String>();
	public static final LinkedList<common.Decorator> decorators = new LinkedList<common.Decorator>();

	protected NIOStringList(int children) {
		super(children);
	}
}
