package edu.umn.cs.melt.ide.imp.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.text.IRegion;

import common.Node;
import common.Terminal;

public class IdeParseResult<NODE extends Node> {
	private NODE value;
	private List<Terminal> tokens;
	
	public IdeParseResult(NODE tree, List<Terminal> terminals) {
		value = tree;
		tokens = terminals;
	}
	
	public NODE getTree() {
		return value;
	}

	public Iterator<Terminal> getTokenIterator(IRegion region) {
		int startOffset = region.getOffset();
		int endOffset = startOffset + region.getLength();
		
		List<Terminal> list = new ArrayList<Terminal>();
		Iterator<Terminal> iter = tokens.iterator();
		while(iter.hasNext()){
			Terminal next = iter.next();
			if(next.getStartOffset() < startOffset || next.getEndOffset() > endOffset){
				continue;
			} else {
				list.add(next);
			}
		}
		
		return list.iterator();
	}
}
