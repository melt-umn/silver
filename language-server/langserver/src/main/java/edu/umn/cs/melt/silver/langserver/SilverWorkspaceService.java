package edu.umn.cs.melt.silver.langserver;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.lsp4j.DidChangeConfigurationParams;
import org.eclipse.lsp4j.DidChangeWatchedFilesParams;
import org.eclipse.lsp4j.DidChangeWorkspaceFoldersParams;
import org.eclipse.lsp4j.WorkspaceFolder;
import org.eclipse.lsp4j.services.WorkspaceService;

public class SilverWorkspaceService implements WorkspaceService {
    private SilverLanguageServer languageServer;
    private List<WorkspaceFolder> workspaceFolders = new ArrayList<>();

    public SilverWorkspaceService(SilverLanguageServer languageServer) {
        this.languageServer = languageServer;
    }

    public void setWorkspaceFolders(List<WorkspaceFolder> folders) {
        workspaceFolders = folders;
    }

    @Override
    public void didChangeConfiguration(DidChangeConfigurationParams params) {

    }

    @Override
    public void didChangeWatchedFiles(DidChangeWatchedFilesParams params) {

    }

    @Override
    public void didChangeWorkspaceFolders(DidChangeWorkspaceFoldersParams params) {
        System.err.println(params);
        workspaceFolders.addAll(params.getEvent().getAdded());
        workspaceFolders.removeAll(params.getEvent().getRemoved());
        languageServer.setWorkspaceFolders(workspaceFolders);
    }
}
