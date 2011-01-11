package common;

import java.util.*;

import common.exceptions.MissingDefinitionException;
import common.exceptions.TraceException;

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
	protected final Object[] inheritedValues;
	/**
	 * The cache of the synthesized attributes on this node.
	 * 
	 * @see #synthesized(String)
	 * @see #local(String)
	 */
	protected final Object[] synthesizedValues;

	/**
	 * The inherited attributes supplied to this DecoratedNode, to be evaluated with context 'parent'.
	 * 
	 * @see #inherited(String)
	 * @see #inheritedValues
	 */
	protected final Lazy[] inheritedAttributes;

	/**
	 * An Ugly, horrifying hack for dealing with 'let's in Silver (needed due to pattern matching)
	 * 
	 * <p> TODO: Remove this stain!
	 * @see #local(String)
	 */
	protected Map<String, Lazy> extraLocalAttributes;
	
	/**
	 * A cache of the values of local attributes on this node. (incl. locals, lets and prod)
	 */
	protected final Map<String, Object> localValues;

	
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
	protected DecoratedNode(final Node self, final DecoratedNode parent,
			final Lazy[] inhs, final DecoratedNode forwardParent) {
		this.self = self;
		this.parent = parent;
		this.inheritedAttributes = inhs;
		this.forwardParent = forwardParent;
		
		// create caches
		if(self != null && self.getNumberOfChildren() > 0)
			this.childrenValues = new Object[self.getNumberOfChildren()];
		else
			this.childrenValues = null;
		if(self != null && self.getNumberOfInhAttrs() > 0)
			this.inheritedValues = new Object[self.getNumberOfInhAttrs()];
		else
			this.inheritedValues = null;
		if(self != null)
			this.synthesizedValues = new Object[self.getNumberOfSynAttrs()];
		else
			this.synthesizedValues = null;
		this.localValues = new TreeMap<String, Object>();
		
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
	public DecoratedNode(final Node self, final DecoratedNode parent, final Lazy[] inhs) {
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
	public DecoratedNode(final Node self, final DecoratedNode parent, final DecoratedNode forwardParent) {
		this(self,parent,null,forwardParent);
	}

	/**
	 * @return The {@link Node} this decorates.
	 */
	public final Node undecorate(){
		return self;
	}

	
	// TODO: The AsIs/Decorated distinction could go away and we could go for storing
	// information in Node on whether it's decorable.
	
	
	/**
	 * Returns the child of this DecoratedNode, without potentially decorating it.
	 * 
	 * <p>Warning: While it is technically safe to mix calls to {@link #childAsIs} and {@link #childDecorated}
	 * this behavior should not be relied upon, as it may change later.
	 * 
	 * @param child The number of the child to obtain.
	 * @return The unmodified value of the child.
	 */
	public Object childAsIs(final int child){
		return self.getChild(child);
	}
	
	/**
	 * Returns the child of this DecoratedNode, decorating it with whatever inherited attributes
	 * this production has for it.
	 * 
	 * <p>Warning: While it is technically safe to mix calls to {@link #childAsIs} and {@link #childDecorated}
	 * this behavior should not be relied upon, as it may change later.
	 * 
	 * @param child The number of the child to obtain.
	 * @return The decorated value of the child.
	 */
	public DecoratedNode childDecorated(final int child){
		Object o = this.childrenValues[child]; 
		if(o == null) {
			o = self.getChild(child);
			// Thunk evaluation is now handled in Node, rather than here.
			if (o instanceof Node) {
				o = ((Node)o).decorate(this, self.getDefinedInheritedAttributes(child));
			}
			
			// CACHE : probably should not comment out child caching?
			this.childrenValues[child] = o;
		}
		return (DecoratedNode)o;
	}
	
	/**
	 * Get the value of a local, caching it for re-use.
	 * 
	 * First, we look for "real" locals, and if that fails, we try the hacky {@link extraLocalAttributes}
	 * field.
	 * 
	 * <p>Warning: do not mix {@link #localAsIs} and {@link #localDecorated} on the same local attribute!
	 * 
	 * @param attribute The full name of the local to obtain.
	 * @return The value of the local.
	 */
	public Object localAsIs(final String attribute) {
		Object o = this.localValues.get(attribute);
		if(o == null) {
			Lazy l = self.getLocal(attribute);
			if(l == null && this.extraLocalAttributes != null) {
				l = this.extraLocalAttributes.get(attribute);
			}
			if(l == null) {
				throw new MissingDefinitionException("Local attribute '" + attribute + "' is not defined in production '" + self.getName() + "'");
			}
			try {
				o = l.eval(this);
			} catch(Throwable t){
				throw new TraceException("Error while evaluating local attribute '" + attribute + "' in production '" + self.getName() + "'", t);
			}
			
			// CACHE : comment out to disable caching for local attributes
			// not recommended due to IO objects (IOString, etc)
			this.localValues.put(attribute, o);
		}
		return o;
	}

	/**
	 * Get the value of a local, caching it for re-use.
	 * 
	 * First, we look for "real" locals, and if that fails, we try the hacky {@link extraLocalAttributes}
	 * field.
	 * 
	 * <p>Warning: do not mix {@link #localAsIs} and {@link #localDecorated} on the same local attribute!
	 * 
	 * @param attribute The full name of the local to obtain.
	 * @return The value of the local.
	 */
	public DecoratedNode localDecorated(final String attribute) {
		Object o = this.localValues.get(attribute);
		if(o == null) {
			Lazy l = self.getLocal(attribute);
			if(l == null && this.extraLocalAttributes != null) {
				l = this.extraLocalAttributes.get(attribute);
			}
			if(l == null) {
				throw new MissingDefinitionException("Local attribute '" + attribute + "' is not defined in production '" + self.getName() + "'");
			}
			try {
				o = l.eval(this);
				o = ((Node)o).decorate(this, self.getDefinedInheritedAttributes(attribute));
			} catch(Throwable t){
				throw new TraceException("Error while evaluating local attribute '" + attribute + "' in production '" + self.getName() + "'", t);
			}
			
			// CACHE : comment out to disable caching for local attributes
			// not recommended due to IO objects (IOString, etc)
			this.localValues.put(attribute, o);
		}
		return (DecoratedNode)o;
	}

	/**
	 * Obtain a synthesized attribute from this DecoratedNode. First, look for definitions on this node,
	 * and if that fails, request it from whatever we forward to, if anything.
	 * 
	 * @param attribute The full name of the attribute.
	 * @return The value of the attribute.
	 */
	public Object synthesized(final int attribute) {
		//System.err.println("TRACE: " + name + " demanding syn attribute: " + attribute);
		
		Object o = this.synthesizedValues[attribute];
		if(o == null) {
			Lazy l = self.getSynthesized(attribute);
			if(l != null) {
				try {
					o = l.eval(this);
				} catch(Throwable t) {
					throw new TraceException("Error while evaluating synthesized attribute '" + self.getNameOfSynAttr(attribute) + "' in production '" + self.getName() + "'", t);
				}
			} else if(self.getForward() != null) {
				// TODO: tell forward not to cache the value since we will?
				try {
					o = forward().synthesized(attribute);
				} catch(Throwable t) {
					throw new TraceException("Error attempting to fetch from forward in production " + self.getName() + ".", t);
				}
			} else {
				throw new MissingDefinitionException("Synthesized attribute '" + self.getNameOfSynAttr(attribute) + "' is not defined in production '" + self.getName() + "'");
			}
			
			// CACHE : comment out to disable caching for synthesized attributes
			this.synthesizedValues[attribute] = o;
		}
		return o;
	}
	
	/**
	 * Get the forwarded-to DecoratedNode.  Cached.  There is no need for an "AsIs" vs "Decorated"
	 * variant of this function, since we always know it's a Node.
	 * 
	 * @return The DecoratedNode this one forwards to, or null if this node does not forward.
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
				throw new TraceException("Error evaluating forward node in production '" + self.getName() + "'", t);
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
	public Object inherited(final int attribute) {
		//System.err.println("TRACE: " + name + " demanding inh attribute: " + attribute);
		
		Object o = this.inheritedValues[attribute];
		if(o == null) {
			Lazy l;
			// Note: inheritedAttributes is validly null here!  (forward nodes have no inherited attributes)
			if(this.inheritedAttributes == null || (l = this.inheritedAttributes[attribute]) == null) {
				if(forwardParent != null) {
					try {
						o = forwardParent.inheritedForwarded(attribute);
					} catch(Throwable t) {
						throw new TraceException("Inherited attribute '" + self.getNameOfInhAttr(attribute) + "' demanded by forward production'" + self.getName() +"'.", t);
					}
				} else {
					throw new MissingDefinitionException("Inherited attribute '" + self.getNameOfInhAttr(attribute) + "' not provided to '" + self.getName() + "' by '" + parent.self.getName() + "'" + (forwardParent==null?"":" (forwardParent: '" + forwardParent.self.getName() + "')"));
				}
			} else {
				try {
					o = l.eval(this.parent);
				} catch(Throwable t) {
					throw new TraceException("Error evaluating inherited attribute '" + self.getNameOfInhAttr(attribute) + "' in production '" + self.getName() + "'", t);
				}
			}
			
			// CACHE : comment out to disable caching of inherited attributes
			// not recommended because it leads to a combinatoric explosion for environments
			// due to thunks keeping around references to previously computed environments that
			// we're recomputing
			this.inheritedValues[attribute] = o;
		}
		return o;
	}
	
	/**
	 * Only called by {@link #inherited(String)}, when it doesn't have an inherited attribute,
	 * and it wants to request that value from this DecorateNode (its {@link #forwardParent}).
	 * 
	 * @param attribute The full name of the attribute.
	 * @return The value of the attribute.
	 */
	protected Object inheritedForwarded(final int attribute) {
		//System.err.println("TRACE: " + name + " demanding FORWARDED inh attribute: " + attribute);
		
		// No cache look up here. There is only one forward production. It will call this method
		// a maximum of once for each attribute, since it will cache the result.
		
		Lazy l = self.getForwardInh(attribute);
		if(l == null) {
			return inherited(attribute);
		}
		try {
			// No need for caching here, it'll be cached by the inherited() that called us
			return l.eval(this);
		} catch(Throwable t) {
			throw new TraceException("Error evaluating inherited attribute '" + self.getNameOfInhAttr(attribute) + "' for forward production in production '" + self.getName() + "'", t);
		}
	}

}
