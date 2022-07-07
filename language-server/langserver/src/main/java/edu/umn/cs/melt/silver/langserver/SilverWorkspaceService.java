package edu.umn.cs.melt.silver.langserver;

import org.eclipse.lsp4j.CreateFilesParams;
import org.eclipse.lsp4j.DeleteFilesParams;
import org.eclipse.lsp4j.DidChangeConfigurationParams;
import org.eclipse.lsp4j.DidChangeWatchedFilesParams;
import org.eclipse.lsp4j.RenameFilesParams;
import org.eclipse.lsp4j.services.WorkspaceService;

public class SilverWorkspaceService implements WorkspaceService {
    private SilverLanguageServer server;

    public SilverWorkspaceService(SilverLanguageServer server) {
        this.server = server;
    }

    @Override
    public void didChangeConfiguration(DidChangeConfigurationParams params) {

    }

    @Override
    public void didChangeWatchedFiles(DidChangeWatchedFilesParams params) {

    }

    @Override
    public void didCreateFiles(CreateFilesParams params) {
		server.refreshWorkspace();
	}

    @Override
    public void didDeleteFiles(DeleteFilesParams params) {
		server.refreshWorkspace();
	}

    @Override
    public void didRenameFiles(RenameFilesParams params) {
		server.refreshWorkspace();
	}
}
