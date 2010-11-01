package core;

import java.util.*;

public abstract class NAnyTypeList extends common.Node {

	public static final HashSet<String> occurs = new HashSet<String>();
	public static final LinkedList<common.Decorator> decorators = new LinkedList<common.Decorator>();

	protected NAnyTypeList(int children) {
		super(children);
	}
}
