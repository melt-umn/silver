package edu.umn.cs.melt.silver.langserver;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.lsp4j.FileOperationFilter;
import org.eclipse.lsp4j.FileOperationOptions;
import org.eclipse.lsp4j.FileOperationPattern;
import org.eclipse.lsp4j.FileOperationsServerCapabilities;
import org.eclipse.lsp4j.InitializeParams;
import org.eclipse.lsp4j.InitializeResult;
import org.eclipse.lsp4j.InitializedParams;
import org.eclipse.lsp4j.SemanticTokensLegend;
import org.eclipse.lsp4j.SemanticTokensServerFull;
import org.eclipse.lsp4j.SemanticTokensWithRegistrationOptions;
import org.eclipse.lsp4j.ServerCapabilities;
import org.eclipse.lsp4j.TextDocumentSyncKind;
import org.eclipse.lsp4j.WorkspaceFolder;
import org.eclipse.lsp4j.WorkspaceFoldersOptions;
import org.eclipse.lsp4j.WorkspaceServerCapabilities;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageClientAware;
import org.eclipse.lsp4j.services.LanguageServer;
import org.eclipse.lsp4j.services.TextDocumentService;
import org.eclipse.lsp4j.services.WorkspaceService;

public class SilverLanguageServer implements LanguageServer, LanguageClientAware {
    private SilverTextDocumentService textDocumentService;
    private SilverWorkspaceService workspaceService;
    private int errorCode = 1;
    private List<WorkspaceFolder> folders;

    public SilverLanguageServer() {
        this.textDocumentService = new SilverTextDocumentService();
        this.workspaceService = new SilverWorkspaceService(this);
    }

    @Override
    public CompletableFuture<InitializeResult> initialize(InitializeParams initializeParams) {
		common.Util.init();
		silver.compiler.composed.Default.Init.initAllStatics();
		silver.compiler.composed.Default.Init.init();
		silver.compiler.composed.Default.Init.postInit();

        System.err.println("Initializing Silver language server");

        this.folders = initializeParams.getWorkspaceFolders();
        refreshWorkspace();

        // Set the capabilities of the LS to inform the client.
        ServerCapabilities capabilities = new ServerCapabilities();
        capabilities.setTextDocumentSync(TextDocumentSyncKind.Full);
        capabilities.setSemanticTokensProvider(
            new SemanticTokensWithRegistrationOptions(
                new SemanticTokensLegend(
                    SilverTextDocumentService.tokenTypes, SilverTextDocumentService.tokenModifiers),
                new SemanticTokensServerFull(false), false));

        FileOperationOptions fileOperationOptions = new FileOperationOptions(
            List.of(new FileOperationFilter(new FileOperationPattern("**/*.{sv,ag,sv.md,ag.md}")))
        );
        FileOperationsServerCapabilities fileOperations = new FileOperationsServerCapabilities();
        fileOperations.setDidCreate(fileOperationOptions);
        fileOperations.setDidDelete(fileOperationOptions);
        fileOperations.setDidRename(fileOperationOptions);
        WorkspaceServerCapabilities workspaceServer = new WorkspaceServerCapabilities();
        workspaceServer.setFileOperations(fileOperations);
        capabilities.setWorkspace(workspaceServer);

        final InitializeResult initializeResult = new InitializeResult(capabilities);
        return CompletableFuture.supplyAsync(()->initializeResult);
    }

    @Override
    public CompletableFuture<Object> shutdown() {
        // If shutdown request comes from client, set the error code to 0.
        errorCode = 0;
        return null;
    }

    @Override
    public void exit() {
        // Kill the LS on exit request from client.
        System.exit(errorCode);
    }

    @Override
    public TextDocumentService getTextDocumentService() {
        // Return the endpoint for language features.
        return this.textDocumentService;
    }

    @Override
    public WorkspaceService getWorkspaceService() {
        // Return the endpoint for workspace functionality.
        return this.workspaceService;
    }

    @Override
    public void connect(LanguageClient languageClient) {
        textDocumentService.setClient(languageClient);
    }

    public void refreshWorkspace() {
        textDocumentService.setWorkspaceFolders(folders);
    }
}
