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
				dbFactory.setCoalescing(true); // Eliminate CDATA into text nodes...
				dbFactory.setIgnoringComments(true); // Eliminate comment nodes...
				//dbFactory.setIgnoringElementContentWhitespace(true); //REQUIRES VALIDATION MODE
				dbFactory.setValidating(false); // Make sure we're not validating...
				
				// I.. what...
				// Whoever is responsible for this being necessary SHOULD BE SHOT IN THE FACE
				// Apparently the w3c gets GBPS eaten up by this buggy crap!
				dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
				// No, no I don't want my language tools to stop, and go download a dtd
				// from the damn internet you fools. Especially when I ALREADY TOLD YOU
				// NOT TO VALIDATE!
				
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
		
		lib.xml.ast.NXMLNodeList l = new lib.xml.ast.PxmlNodeListNil();
		
		for(int i = nl.getLength() - 1; i >= 0; i -- ) {
			final Node n = nl.item(i);
			
			switch(n.getNodeType()) {
			case Node.TEXT_NODE:
			case Node.CDATA_SECTION_NODE: // treating the same as text for now!
				// Convert the text data to Silver type
				common.StringCatter val = new common.StringCatter(n.getNodeValue());
				
				// Add this element to the list
				l = new lib.xml.ast.PxmlNodeListCons( new lib.xml.ast.PxmlNodeText(val), l);
				break;
			case Node.ELEMENT_NODE:
			case Node.ENTITY_NODE: // treating the same as an element for now!
				// Convert the name
				common.StringCatter name = new common.StringCatter(n.getNodeName());
				// Convert the attributes
				common.ConsCell/*lib.xml.ast.NXMLAttribute*/ attrs = parseElementAttributes(n.getAttributes());
				// Convert the children
				lib.xml.ast.NXMLNodeList children = parseXMLNodes(n.getChildNodes());
				
				// Create the Node
				l = new lib.xml.ast.PxmlNodeListCons( new lib.xml.ast.PxmlNodeElement(name, attrs, children), l);
				break;
			case Node.COMMENT_NODE:
			case Node.DOCUMENT_TYPE_NODE: // for now: wtf?
				break; // ignoring comments!
			default:
				throw new SilverError("Unexpected type of XML node! " + n.getNodeType() + n.getNodeName() + n.toString());
			}
			
		}
		return l;
	}
	
	private static final common.ConsCell/*lib.xml.ast.NXMLAttribute*/ parseElementAttributes(final NamedNodeMap al) {
		
		common.ConsCell/*lib.xml.ast.NXMLAttribute*/ l = common.ConsCell.nil;
		
		for(int i = 0; i < al.getLength(); i++ ) {
			final Node n = al.item(i);
			final common.StringCatter key = new common.StringCatter(n.getNodeName());
			final common.StringCatter value = new common.StringCatter(n.getNodeValue());
			l = new common.ConsCell( new lib.xml.ast.PxmlAttribute(key, value), l);
		}
		
		return l;
	}
}
