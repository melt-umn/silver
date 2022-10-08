package edu.umn.cs.melt.silver.langserver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.lsp4j.ExecuteCommandOptions;
import org.eclipse.lsp4j.FileOperationFilter;
import org.eclipse.lsp4j.FileOperationOptions;
import org.eclipse.lsp4j.FileOperationPattern;
import org.eclipse.lsp4j.FileOperationsServerCapabilities;
import org.eclipse.lsp4j.InitializeParams;
import org.eclipse.lsp4j.InitializeResult;
import org.eclipse.lsp4j.SemanticTokensLegend;
import org.eclipse.lsp4j.SemanticTokensServerFull;
import org.eclipse.lsp4j.SemanticTokensWithRegistrationOptions;
import org.eclipse.lsp4j.ServerCapabilities;
import org.eclipse.lsp4j.TextDocumentSyncKind;
import org.eclipse.lsp4j.WorkspaceServerCapabilities;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageClientAware;
import org.eclipse.lsp4j.services.LanguageServer;
import org.eclipse.lsp4j.services.TextDocumentService;
import org.eclipse.lsp4j.services.WorkspaceService;

import edu.umn.cs.melt.lsp4jutil.Util;

public class SilverLanguageServer implements LanguageServer, LanguageClientAware {
    private SilverLanguageService service;
    private int errorCode = 1;

    public SilverLanguageServer() {
        this.service = new SilverLanguageService();
    }

    @Override
    public CompletableFuture<InitializeResult> initialize(InitializeParams initializeParams) {
		common.Util.init();
		silver.compiler.composed.Default.Init.initAllStatics();
		silver.compiler.composed.Default.Init.init();
		silver.compiler.composed.Default.Init.postInit();

        System.err.println("Initializing Silver language server");

        Path silverGrammars;
        try {
            silverGrammars = Files.createTempDirectory("silver_grammars");
            Util.copyFromJar(getClass(), "grammars/", silverGrammars);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        service.setSilverGrammarsPath(silverGrammars);

        service.setWorkspaceFolders(initializeParams.getWorkspaceFolders());

        // Set the capabilities of the LS to inform the client.
        ServerCapabilities capabilities = new ServerCapabilities();
        capabilities.setTextDocumentSync(TextDocumentSyncKind.Full);
        capabilities.setSemanticTokensProvider(
            new SemanticTokensWithRegistrationOptions(
                new SemanticTokensLegend(
                    SilverLanguageService.tokenTypes, SilverLanguageService.tokenModifiers),
                new SemanticTokensServerFull(false), false));
        capabilities.setExecuteCommandProvider(new ExecuteCommandOptions(List.of(
            "silver.clean"
        )));
        capabilities.setDeclarationProvider(true);

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
        return this.service;
    }

    @Override
    public WorkspaceService getWorkspaceService() {
        // Return the endpoint for workspace functionality.
        return this.service;
    }

    @Override
    public void connect(LanguageClient languageClient) {
        service.setClient(languageClient);
    }
}
