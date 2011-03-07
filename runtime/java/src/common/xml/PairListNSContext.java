package common.xml;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

import core.NPair;

public class PairListNSContext implements NamespaceContext {

	private final Map<String, String> resolver;
	
	/**
	 * Creates a namespace context resolver from a [(String, String)] Silver type.
	 * 
	 * @param current a key-value pair of prefixes to namespace URIs
	 */
	public PairListNSContext(common.ConsCell current) {
		
		resolver = new TreeMap<String, String>();
		while(!current.nil()) {
			core.NPair elem = (NPair) current.head();
			
			String fst = elem.getChild(0).toString();
			String snd = elem.getChild(1).toString();
			
			resolver.put(fst, snd);
			
			current = current.tail();
		}
		
	}
	
	@Override
	public String getNamespaceURI(String prefix) {
		
		if(prefix == null)
			throw new IllegalArgumentException();
		
		String result = resolver.get(prefix);
		if(result != null)
			return result;
		
		if(prefix.equals(XMLConstants.XML_NS_PREFIX))
			return XMLConstants.XML_NS_URI;
		
		if(prefix.equals(XMLConstants.XMLNS_ATTRIBUTE))
			return XMLConstants.XMLNS_ATTRIBUTE_NS_URI;
		
		return XMLConstants.NULL_NS_URI;
	}

	@Override
	public String getPrefix(String namespaceURI) {
		throw new UnsupportedOperationException();

	}

	@Override
	public Iterator<?> getPrefixes(String namespaceURI) {
		throw new UnsupportedOperationException();
	}

}
