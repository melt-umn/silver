package common.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import common.exceptions.SilverError;
import common.exceptions.SilverInternalError;
import common.exceptions.TraceException;

public final class Util {

	private static DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	private static DocumentBuilder parser;
	
	/**
	 * @param fn The filename of an XML file to convert to a Silver AST
	 * @return The Silver AST of the XML file, wrapped in a ParseResult structure.
	 */
	public static final core.NParseResult parseXMLFile(final common.StringCatter fn) {
		
		// Create the parser, if we haven't already. Why do they create these things so complicated?
		if(parser == null) {
			try {
				parser = dbFactory.newDocumentBuilder();
			} catch(ParserConfigurationException pce) {
				throw new SilverInternalError("Unexpected error initializing XML parser", pce);
			}
		}
		
		final String filename = fn.toString();

		Document d;
		try {
			d = parser.parse(new File(filename));
		} catch (SAXException e) {
			
			// Return the failure data structure, with the parse error.
			return new core.PparseFailed(new common.StringCatter(e.toString()));
			
		} catch (Throwable e) {
			throw new TraceException("While parsing XML file " + filename, e);
		}
		
		final DocumentType dt = d.getDoctype();
		final NodeList nl = d.getChildNodes();
		
		lib.xml.ast.NXMLDocumentType sdt;
		
		if(dt != null) {
			sdt = new lib.xml.ast.PxmlDocumentType(
					new common.StringCatter(dt.getName()), 
					parseXMLNodes(dt.getChildNodes())); 
		} else {
			sdt = new lib.xml.ast.PxmlNoDocumentType();
		}
		
		return new core.PparseSucceeded(
				new lib.xml.ast.PxmlDocument(
						sdt, 
						parseXMLNodes(nl)));
		
	}
	
	private static final lib.xml.ast.NXMLNodeList parseXMLNodes(final NodeList nl) {
		
		common.ConsCell l = common.ConsCell.nil; // < lib.xml.NXMLNode >
		
		for(int i = 0; i < nl.getLength(); i ++ ) {
			Node n = nl.item(i);
			
			switch(n.getNodeType()) {
			case Node.TEXT_NODE:
			case Node.CDATA_SECTION_NODE: // treating the same as text for now!
				// Convert the text data to Silver type
				common.StringCatter val = new common.StringCatter(n.getNodeValue());
				
				// Add this element to the list
				l = new common.ConsCell( new lib.xml.ast.PxmlTextBuilder(val), l);
				break;
			case Node.ELEMENT_NODE:
				// Convert the name
				common.StringCatter name = new common.StringCatter(n.getNodeName());
				// Convert the attributes
				lib.xml.ast.NXMLAttributeList attrs = parseElementAttributes(n.getAttributes());
				// Convert the children
				lib.xml.ast.NXMLNodeList children = parseXMLNodes(n.getChildNodes());
				
				// Create the Element
				lib.xml.ast.NXMLElement elem = new lib.xml.ast.PxmlElement(name, attrs, children);
				
				// Create the Node
				l = new common.ConsCell( new lib.xml.ast.PxmlNodeElement(elem), l);
				break;
			case Node.COMMENT_NODE:
				break; // ignoring comments!
			default:
				throw new SilverError("Unexpected type of XML node! " + n.getNodeName() + n.toString());
			}
			
		}
		return new lib.xml.ast.PxmlNodeListBuilder(l);
	}
	
	private static final lib.xml.ast.NXMLAttributeList parseElementAttributes(final NamedNodeMap al) {
		
		common.ConsCell l = common.ConsCell.nil; // < lib.xml.NXMLAttribute >
		
		for(int i = 0; i < al.getLength(); i++ ) {
			Node n = al.item(i);
			common.StringCatter key = new common.StringCatter(n.getNodeName());
			common.StringCatter value = new common.StringCatter(n.getNodeValue());
			l = new common.ConsCell( new lib.xml.ast.PxmlAttribute(key, value), l);
		}
		
		return new lib.xml.ast.PxmlAttributeListBuilder(l);
	}
}
