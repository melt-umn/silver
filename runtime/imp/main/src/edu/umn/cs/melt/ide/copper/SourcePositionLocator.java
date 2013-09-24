package edu.umn.cs.melt.ide.copper;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.imp.model.ICompilationUnit;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.parser.ISourcePositionLocator;


public class SourcePositionLocator
<NODE extends IEnhancedParseTreeNode, TOKEN extends IToken> // PCONTROL extends IEnhancedParseController
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
        if (!(astRoot instanceof IEnhancedParseTreeNode))
            return astRoot;

        NODE astNode= (NODE) astRoot;
        
        if (astNode instanceof IEnhancedParseTreeInnerNode) {
        	IEnhancedParseTreeNode[] children = ((IEnhancedParseTreeInnerNode)astNode).getChildren();
            for(int i= 0; i < children.length; i++) {
            	NODE maybe= (NODE) findNode(children[i], startOffset, endOffset);
                if (maybe != null){
                    return maybe;
                }
            }
        }
        
        if (startOffset >= astNode.getStart() && endOffset <= astNode.getEnd()){
        	return astNode;
        }

        return null;
	}
	
	@Override
	public int getStartOffset(Object entity) {
		if (entity instanceof IEnhancedParseTreeNode) {
			return ((IEnhancedParseTreeNode) entity).getStart();
		} else if (entity instanceof IToken) {
			return ((IToken) entity).getStartOffset();
		} 
		return 0;
	}

	@Override
	public int getEndOffset(Object entity) {
		if (entity instanceof IEnhancedParseTreeNode) {
			return ((IEnhancedParseTreeNode) entity).getEnd();
		} else if (entity instanceof IToken) {
			return ((IToken) entity).getEndOffset();
		} 
		return 0;
	}

	@Override
	public int getLength(Object entity) {
		return getEndOffset(entity) - getStartOffset(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IPath getPath(Object entity) {
		//TEMPLATE
        if(fParseController instanceof IParseController){
        	return fParseController.getPath();
//        	return fParseController.getProject().getRawProject().getFile(
//        		((PCONTROL)fParseController).getFileName()).getFullPath();
        }
        
        if (entity instanceof ICompilationUnit) {
            ICompilationUnit cu= (ICompilationUnit) entity;
            return cu.getPath();
        }
        
        return new Path("");
	}


}
