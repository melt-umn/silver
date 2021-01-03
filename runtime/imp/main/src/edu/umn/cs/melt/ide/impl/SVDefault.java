package edu.umn.cs.melt.ide.impl;

import java.io.IOException;
import java.io.Reader;

import org.eclipse.core.resources.IProject;

import common.ConsCell;
import common.IOToken;
import common.Node;
import common.StringCatter;

import silver.core.NIOVal;
import silver.core.Pioval;
import edu.umn.cs.melt.copper.runtime.logging.CopperParserException;
import edu.umn.cs.melt.ide.eclipse.property.IPropertyPageTab;
import edu.umn.cs.melt.ide.imp.services.IdeParseResult;
import edu.umn.cs.melt.ide.silver.property.ui.IPropertyControlsProvider;

/**
 * This class exists to provide default behaviors for all methods of the interface.
 * 
 * The implementor class should only need to provide those methods it actually uses. The others
 * should either be never accessed (thus throwing exceptions below) or have completely valid
 * behavior if absent (seen below).
 */
public abstract class SVDefault implements SVInterface {

	// Required methods:
	@Override
	public abstract String name();
	@Override
	public abstract String pluginId();
	@Override
	public abstract String markerErrorName();
	@Override
	public abstract String getNatureId();
	@Override
	public abstract String fileExtension();
	@Override
	public abstract IPropertyControlsProvider getProjectProperties();
	@Override
	public abstract String getInitialProjectProperties();
	@Override
	public abstract IPropertyPageTab[] getPropertyTabs();
	
	@Override
	public IPropertyControlsProvider getNewFileProperties() {
		// Should be provided if the wizard ends up in plugin.xml
		throw new UnsupportedOperationException("new file properties requested by not provided by plugin");
	}
	@Override
	public StringCatter fileStub(ConsCell properties) {
		// Should be provided if the wizard ends up in plugin.xml
		throw new UnsupportedOperationException("new file stub generator requested by not provided by plugin");
	}
}
