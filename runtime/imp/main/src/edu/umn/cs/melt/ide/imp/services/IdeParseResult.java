package edu.umn.cs.melt.ide.imp.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.text.IRegion;

import common.Node;
import edu.umn.cs.melt.ide.copper.IToken;

public class IdeParseResult<NODE extends Node, TOKEN extends IToken> {
	private NODE value;
	private List<TOKEN> tokens;
	
	public IdeParseResult(NODE tree, List<TOKEN> terminals) {
		value = tree;
		tokens = terminals;
	}
	
	public NODE getTree() {
		return value;
	}

	public Iterator<TOKEN> getTokenIterator(IRegion region) {
		int startOffset = region.getOffset();
		int endOffset = startOffset + region.getLength();
		
		List<TOKEN> list = new ArrayList<TOKEN>();
		Iterator<TOKEN> iter = tokens.iterator();
		while(iter.hasNext()){
			TOKEN next = iter.next();
			if(next.getStartOffset() < startOffset || next.getEndOffset() > endOffset){
				continue;
			} else {
				list.add(next);
			}
		}
		
		return list.iterator();
	}
}
