package edu.umn.cs.melt.ide.copper;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.imp.model.ICompilationUnit;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.parser.ISourcePositionLocator;

import common.Node;


public class SourcePositionLocator
<NODE extends Node, TOKEN extends IToken>
implements ISourcePositionLocator {

    private final IParseController fParseController;

    public SourcePositionLocator(IParseController parseController) {
        fParseController= parseController;
    }
    
	@Override
	public Object findNode(Object astRoot, int offset) {
		return findNode(astRoot, offset, offset);
	}

	@Override
	public Object findNode(Object astRoot, int startOffset, int endOffset) {
        
		//System.out.println("\nRequested findNode. Not implemented.\n");
		// TODO: only possible solution to this problem is to wrap the ast with parameters,
		// to be consumed by another service.
		// e.g. imp asks "what's ast at start, end?" and we say
		// AstPosition(ast, start, end) as the result.
		// then when imp says, "what's the definition site of this ast node?"
		// we get an AstPosition, unwrap to ast, then ask ast about definition site
		// at start, end.
		
        return null;
	}
	
	@Override
	public int getStartOffset(Object entity) {
		
		if (entity instanceof IToken) {
			return ((IToken) entity).getStartOffset();
		} else {
			System.out.println("Got asked start for " + entity.getClass().getName());
		}
		
		return 0;
	}

	@Override
	public int getEndOffset(Object entity) {
		
		if (entity instanceof IToken) {
			// Silver uses a position range that is inclusive at the start, and 
			// exclusive at the end.
			// IMP / Eclipse want a range that is includive at BOTH ends.
			// So subtract one.
			return ((IToken) entity).getEndOffset() - 1;
		} else {
			System.out.println("Got asked end for " + entity.getClass().getName());
		}
		
		return 0;
	}

	@Override
	public int getLength(Object entity) {
		return getEndOffset(entity) - getStartOffset(entity);
	}

	@Override
	public IPath getPath(Object entity) {
		
		System.out.println("GetPath requested of source locator");
		
		// ???
		
        if(fParseController instanceof IParseController){
        	return fParseController.getPath();
        }
        
        if (entity instanceof ICompilationUnit) {
            ICompilationUnit cu= (ICompilationUnit) entity;
            return cu.getPath();
        }
        
        return new Path("");
	}


}
