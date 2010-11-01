package common;

import java.util.*;

/**
 * This is the <strike>Stack</strike>Heap Frame and primary data structure of Silver.
 * 
 * <p>Here be dragons, unfortunately. Don't modify at all without running it past me.
 * 
 * @author tedinski
 * @see Node
 */
public class DecoratedNode {

	/**
	 * The "undecorated" form of this DecoratedNode. (Never null)
	 * 
	 * @see #undecorate()
	 */
	protected final Node self;
	/**
	 * The node that forwards to this one. (May be null)
	 * 
	 * @see #inheritedForwarded(String)
	 */
	protected final DecoratedNode forwardParent;
	/**
	 * The DecoratedNode to use as a context for evaluating inherited attributes. (Never null except for TopNode)
	 * 
	 * <p> May be actual parent. Or production this is a local in. Or just a production that
	 * called 'decorate' to create this one.
	 * 
	 * @see TopNode
	 */
	protected final DecoratedNode parent;
	
	/**
	 * The cache of children (e.g. the DecoratedNodes)
	 * 
	 * @see #child(int)
	 */
	protected final Object[] childrenValues;
	/**
	 * The cache of the forward node.
	 * 
	 * @see #forward()
	 */
	protected DecoratedNode forwardValue;
	/**
	 * The cache of the inherited attribute supplied to this node.
	 * 
	 * @see #inherited(String)
	 * @see #inheritedAttributes
	 */
	protected final Map<String, Object> inheritedValues;
	/**
	 * The cache of the synthesized and local attributes on this node.
	 * 
	 * @see #synthesized(String)
	 * @see #local(String)
	 */
	protected final Map<String, Object> synthesizedValues;

	/**
	 * The inherited attributes supplied to this DecoratedNode, to be evaluated with context 'parent'.
	 * 
	 * @see #inherited(String)
	 * @see #inheritedValues
	 */
	protected final Map<String, Lazy> inheritedAttributes;

	/**
	 * An Ugly, horrifying hack for dealing with 'let's in Silver (needed due to pattern matching)
	 * 
	 * <p> TODO: Remove this stain!
	 * @see #local(String)
	 */
	protected Map<String, Lazy> extraLocalAttributes;

	
	/**
	 * Construct a decorated form of a Node.
	 * 
	 * <p> 'self' and 'parent' are not null (except for TopNode)
	 * 
	 * <p> Only one or none of 'inhs' and 'forwardParent' should be supplied.
	 * 
	 * @param self The Node to decorate.
	 * @param parent The DecoratedNode creating this one, to evaluate inhs in.
	 * @param inhs The inherited attributes to decorate this node with.
	 * @param forwardParent The node to request inherited attributes from instead of using 'inhs'.
	 */
	protected DecoratedNode(Node self, DecoratedNode parent,
			Map<String, Lazy> inhs, DecoratedNode forwardParent) {
		this.self = self;
		this.parent = parent;
		this.inheritedAttributes = inhs;
		this.forwardParent = forwardParent;
		
		// create caches
		if(self != null && self.getNumberOfChildren() > 0)
			this.childrenValues = new Object[self.getNumberOfChildren()];
		else
			this.childrenValues = null;
		if(self instanceof FunctionNode)
			this.inheritedValues = null;
		else
			this.inheritedValues = new TreeMap<String, Object>();
		this.synthesizedValues = new TreeMap<String, Object>();
		
		
		// STATS: Uncomment to enable statistics
		//Statistics.dnSpawn(self!=null?self.getClass():TopNode.class);
	}
	
	// STATS: Uncomment to enable statistics
	//@Override
	//protected void finalize() throws Throwable {
	//	Statistics.dnFinal(self!=null?self.getClass():TopNode.class);
	//}

	/**
	 * The standard way of creating a DecoratedNode.
	 * 
	 * @param self The Node being decorated. (Not null)
	 * @param parent The Node supplying the inherited attributes. (Not null)
	 * @param inhs The inherited attributes being supplied to this node. (may be null)
	 */
	public DecoratedNode(Node self, DecoratedNode parent, Map<String, Lazy> inhs) {
		this(self,parent,inhs,null);
	}

	/**
	 * The way of creating forwarded-to DecoratedNodes.
	 * 
	 * @param self The Node being decorated. (Not null)
	 * @param parent The Node that is our actual parent. (Not null)
	 * @param forwardParent The Node to request inherited attributes from.
	 * 
	 * @see #inheritedForwarded(String)
	 */
	public DecoratedNode(Node self, DecoratedNode parent, DecoratedNode forwardParent) {
		this(self,parent,null,forwardParent);
	}

	/**
	 * @return The {@link Node} this decorates.
	 */
	public Node undecorate(){
		return self;
	}

	/**
	 * Get the value of a child. If this is a Node, supply it with the attributes we have for it. Caches values for re-use.
	 * 
	 * <p>Note: to avoid auto-decoration, call <code>.undecorate().getChild(child)</code> instead.
	 * 
	 * @param child The number of the child to obtain.
	 * @return The value of the child.
	 */
	public Object child(int child){
		//System.err.println("TRACE: " + name + " demanding child: " + child);
		
		Object o = this.childrenValues[child]; 
		if(o == null) {
			o = self.getChild(child);
			// Thunk evaluation is now handled in Node, rather than here.
			if (o instanceof Node) {
				o = ((Node)o).decorate(this, self.getDefinedInheritedAttributes(child));
			}
			
			/*// "run time type checking" lots of false positives.
			if(o!= null)
			try {
				// This should never throw, since all Node children should have a static childTypes
				Class<?> cts[] = (Class<?>[])self.getClass().getField("childTypes").get(null);
				if(!cts[child].isInstance(o instanceof DecoratedNode ? ((DecoratedNode)o).self : o)) {
					
					// Functions take DecoratedNodes...
					if(cts[child] != common.DecoratedNode.class && cts[child] != common.Terminal.class) {
						System.out.println("RTTC: Node " + name + " getting child " + child +
										" failed.  Expected type: " + cts[child].getName() + 
										". Got type: " + o.getClass().getName());
						if(o instanceof DecoratedNode) {
							System.out.println("\t\t DC is of type: " + ((DecoratedNode)o).name);
						}
					}
				}
			} catch (Throwable t) {
				System.out.println("RTTC: Got exception? " + t);
			}*/
			
			// CACHE : probably should not comment out child caching?
			this.childrenValues[child] = o;
		}
		return o;
	}

	/**
	 * Get the value of a local. If this is a Node, supply it with the attributes we have for it. Caches values for re-use.
	 * 
	 * <p>First, we look for "real" locals, and if that fails, we try the hacky {@link extraLocalAttributes} field.
	 * 
	 * <p>Note: it's not currently possibly to avoid auto-decoration with this. But you can undo it afterward.
	 * 
	 * @param attribute The full name of the local to obtain.
	 * @return The value of the local.
	 */
	public Object local(String attribute) {
		//System.err.println("TRACE: " + name + " demanding local: " + attribute);
		
		Object o = this.synthesizedValues.get(attribute);
		if(o == null) {
			Lazy l = self.getLocal(attribute);
			if(l == null && this.extraLocalAttributes != null) {
				l = this.extraLocalAttributes.get(attribute);
			}
			if(l == null) {
				throw new RuntimeException("Local attribute '" + attribute + "' is not defined in production '" + self.getName() + "'");
			}
			try {
				o = l.eval(this);
				if (o instanceof Node) {
					o = ((Node)o).decorate(this, self.getDefinedInheritedAttributes(attribute));
				}
			} catch(Throwable t){
				throw new RuntimeException("Error evaluating local attribute '" + attribute + "' in production '" + self.getName() + "'", t);
			}
			
			// CACHE : comment out to disable caching for local attributes
			// not recommended due to IO objects (IOString, etc)
			this.synthesizedValues.put(attribute, o);
		}
		return o;
	}

	/**
	 * Obtain a synthesized attribute from this DecoratedNode. First, look for definitions on this node,
	 * and if that fails, request it from whatever we forward to, if anything.
	 * 
	 * @param attribute The full name of the attribute.
	 * @return The value of the attribute.
	 */
	public Object synthesized(String attribute) {
		//System.err.println("TRACE: " + name + " demanding syn attribute: " + attribute);
		
		Object o = this.synthesizedValues.get(attribute);
		if(o == null) {
			Lazy l = self.getSynthesized(attribute);
			if(l != null) {
				try {
					o = l.eval(this);
				} catch(Throwable t) {
					throw new RuntimeException("Error evaluating synthesized attribute '" + attribute + "' in production '" + self.getName() + "'", t);
				}
			} else if(self.getForward() != null) {
				// TODO: tell forward not to cache the value since we will?
				try {
					o = forward().synthesized(attribute);
				} catch(Throwable t) {
					throw new RuntimeException("Error attempting to fetch from forward in production " + self.getName() + ".", t);
				}
			} else {
				throw new RuntimeException("Synthesized attribute '" + attribute + "' is not defined in production '" + self.getName() + "'");
			}
			
			// CACHE : comment out to disable caching for synthesized attributes
			this.synthesizedValues.put(attribute, o);
		}
		return o;
	}
	
	/**
	 * Get the forwarded-to DecoratedNode.  Cached.
	 * 
	 * @return The DecoratedNode this one forwards to.
	 */
	public DecoratedNode forward() {
		Lazy l = self.getForward();
		if(l == null)
			return null;
		if(this.forwardValue == null) {
			try {
				// CACHE : should not comment out forward caching !
				this.forwardValue = ((Node)l.eval(this)).decorate(parent, this);
			} catch(Throwable t) {
				throw new RuntimeException("Error evaluating forward node in production '" + self.getName() + "'", t);
			}
		}
		return this.forwardValue;
	}

	/**
	 * Get the value of an inherited attribute on this DN.  First, try the inherited attributes we were given.
	 * If that fails, ask our {@link #forwardParent}, if any, for whatever they may give us.
	 * 
	 * @param attribute The full name of the attribute.
	 * @return The value of the attribute.
	 * 
	 * @see #inheritedForwarded(String)
	 */
	public Object inherited(String attribute) {
		//System.err.println("TRACE: " + name + " demanding inh attribute: " + attribute);
		
		Object o = this.inheritedValues.get(attribute);
		if(o == null) {
			Lazy l;
			// Note: inheritedAttributes is validly null here!  (forward nodes have no inherited attributes)
			if(this.inheritedAttributes == null || (l = this.inheritedAttributes.get(attribute)) == null) {
				if(forwardParent != null) {
					try {
						o = forwardParent.inheritedForwarded(attribute);
					} catch(Throwable t) {
						throw new RuntimeException("Inherited attribute '" + attribute + "' demanded by forward-to production'" + self.getName() +"'.", t);
					}
				} else {
					throw new RuntimeException("Inherited attribute '" + attribute + "' not provided to '" + self.getName() + "' by '" + parent.self.getName() + "'" + (forwardParent==null?"":" (forwardParent: '" + forwardParent.self.getName() + "')"));
				}
			} else {
				try {
					o = l.eval(this.parent);
				} catch(Throwable t) {
					throw new RuntimeException("Error evaluating inherited attribute '" + attribute + "' in production '" + self.getName() + "'", t);
				}
			}
			
			// CACHE : comment out to disable caching of inherited attributes
			// not recommended because it leads to a combinatoric explosion for environments
			// due to thunks keeping around references to previously computed environments that
			// we're recomputing
			this.inheritedValues.put(attribute, o);
		}
		return o;
	}
	
	/**
	 * Only called by {@link #inherited(String)}, when it doesn't have an inherited attribute,
	 * and it wants to request that value from us (its {@link #forwardParent}).
	 * 
	 * @param attribute The full name of the attribute.
	 * @return The value of the attribute.
	 */
	protected Object inheritedForwarded(String attribute) {
		//System.err.println("TRACE: " + name + " demanding FORWARDED inh attribute: " + attribute);
		
		Lazy l = self.getForwardInh(attribute);
		if(l == null) {
			return inherited(attribute);
		}
		try {
			// No need for caching here, it'll be cached by the inherited() that called us
			return l.eval(this);
		} catch(Throwable t) {
			throw new RuntimeException("Error evaluating inherited attribute '" + attribute + "' for forward production in production '" + self.getName() + "'", t);
		}
	}

}
