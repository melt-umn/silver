package edu.umn.cs.melt.ide.copper.coloring;

import java.util.HashMap;
import java.util.Map;

import edu.umn.cs.melt.ide.copper.IToken;

import org.eclipse.jface.text.TextAttribute;

public abstract class CopperTextAttributeDecider {

//	protected ICopperTokenClassifier classifier;
//	
//	public CopperTextAttributeDecider(ICopperTokenClassifier classifier){
//		this.classifier = classifier;
//	}
	
	private Map<Integer, TextAttribute> attrMap = new HashMap<Integer, TextAttribute>();
	
	protected void addTextAttribute(int kind, TextAttribute attr){
		attrMap.put(kind, attr);
	}
	
	public TextAttribute getAddTextAttribute(IToken token){
		//if(token.getKind()!=0) {
			//System.out.println("kind: " + token.getKind() + " from " + token.getStartOffset() + " to " + token.getEndOffset());			
		//}
		return attrMap.get(token.getKind());
	}
	
}
