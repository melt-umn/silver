package edu.umn.cs.melt.silver.langserver;

import java.util.concurrent.CompletableFuture;

import org.eclipse.lsp4j.CreateFilesParams;
import org.eclipse.lsp4j.DeleteFilesParams;
import org.eclipse.lsp4j.DidChangeConfigurationParams;
import org.eclipse.lsp4j.DidChangeWatchedFilesParams;
import org.eclipse.lsp4j.ExecuteCommandParams;
import org.eclipse.lsp4j.RenameFilesParams;
import org.eclipse.lsp4j.services.WorkspaceService;

public class SilverWorkspaceService implements WorkspaceService {
    private SilverLanguageServer server;

    public SilverWorkspaceService(SilverLanguageServer server) {
        this.server = server;
    }

    @Override
    public CompletableFuture<Object> executeCommand(ExecuteCommandParams params) {
        switch (params.getCommand()) {
        case "silver.clean":
            server.cleanBuild();
            return null;
        }
        throw new UnsupportedOperationException("Unsupported command " + params.getCommand());
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
