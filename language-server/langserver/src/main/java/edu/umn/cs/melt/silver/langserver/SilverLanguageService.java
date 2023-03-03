package edu.umn.cs.melt.silver.langserver;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.lsp4j.ApplyWorkspaceEditParams;
import org.eclipse.lsp4j.ConfigurationItem;
import org.eclipse.lsp4j.ConfigurationParams;
import org.eclipse.lsp4j.CreateFilesParams;
import org.eclipse.lsp4j.DeclarationParams;
import org.eclipse.lsp4j.DeleteFilesParams;
import org.eclipse.lsp4j.DidChangeConfigurationParams;
import org.eclipse.lsp4j.DidChangeTextDocumentParams;
import org.eclipse.lsp4j.DidChangeWatchedFilesParams;
import org.eclipse.lsp4j.DidCloseTextDocumentParams;
import org.eclipse.lsp4j.DidOpenTextDocumentParams;
import org.eclipse.lsp4j.DidSaveTextDocumentParams;
import org.eclipse.lsp4j.ExecuteCommandParams;
import org.eclipse.lsp4j.FileCreate;
import org.eclipse.lsp4j.Location;
import org.eclipse.lsp4j.LocationLink;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.PublishDiagnosticsParams;
import org.eclipse.lsp4j.Range;
import org.eclipse.lsp4j.RenameFilesParams;
import org.eclipse.lsp4j.SemanticTokens;
import org.eclipse.lsp4j.SemanticTokensParams;
import org.eclipse.lsp4j.TextDocumentContentChangeEvent;
import org.eclipse.lsp4j.TextEdit;
import org.eclipse.lsp4j.WorkspaceEdit;
import org.eclipse.lsp4j.WorkspaceFolder;
import org.eclipse.lsp4j.jsonrpc.CompletableFutures;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.TextDocumentService;
import org.eclipse.lsp4j.services.WorkspaceService;

import com.google.gson.JsonPrimitive;
import com.google.gson.JsonElement;

import common.ConsCell;
import common.DecoratedNode;
import common.OriginContext;
import common.SilverCopperParser;
import common.StringCatter;
import common.javainterop.ConsCellCollection;
import edu.umn.cs.melt.lsp4jutil.CopperParserNodeFactory;
import edu.umn.cs.melt.lsp4jutil.CopperSemanticTokenEncoder;
import edu.umn.cs.melt.lsp4jutil.Util;
import silver.compiler.definition.core.NRoot;
import silver.compiler.driver.PbuildRun;
import silver.compiler.driver.PparseArgsOrError;
import silver.compiler.driver.util.NBuildEnv;
import silver.compiler.driver.util.PbuildEnv;
import silver.compiler.driver.util.PwriteInterface;
import silver.compiler.langserver.PfindDeclLocation;
import silver.core.NLocation;
import silver.core.NPair;
import silver.core.PunsafeEvalIO;

/**
 * Implementation of LSP text document and workspace services for Silver.
 * 
 * @author krame505
 */
public class SilverLanguageService implements TextDocumentService, WorkspaceService {
    private LanguageClient client;
    private List<WorkspaceFolder> folders;
    private Map<String, String> fileContents = new HashMap<>();
    private Map<String, Integer> fileVersions = new HashMap<>();
    private Map<String, Integer> savedVersions = new HashMap<>();
    private boolean buildInProgress = false, buildTriggered = false;
    private boolean cleanBuild = false;
    private boolean enableMWDA = false;
    private String silverGen;
    private String silverStdlibGrammars = null;
    private DecoratedNode comp = null;
    private Set<String> grammarDirs = new HashSet<>();
    private Set<String> buildGrammars = new HashSet<>();

    public SilverLanguageService() {
        try {
            silverGen = Files.createTempDirectory("silver_generated").toString() + "/";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setClient(LanguageClient client) {
        this.client = client;
    }

    public void setSilverGrammarsPath(Path path) {
        this.silverStdlibGrammars = path.toString() + "/";
    }

    public void setWorkspaceFolders(List<WorkspaceFolder> folders) {
        this.folders = folders;
        refreshWorkspace();
    }

    @Override
    public void didOpen(DidOpenTextDocumentParams params) {
        // System.err.println("Opened " + params);
        String uri = params.getTextDocument().getUri();
        fileContents.put(uri, params.getTextDocument().getText());
        fileVersions.put(uri, params.getTextDocument().getVersion());
        savedVersions.put(uri, params.getTextDocument().getVersion());
        triggerBuild(false);
    }

    @Override
    public void didChange(DidChangeTextDocumentParams params) {
        // System.err.println("Changed " + params);
        String uri = params.getTextDocument().getUri();
        for (TextDocumentContentChangeEvent change : params.getContentChanges()) {
            fileContents.put(uri, change.getText());
            fileVersions.put(uri, params.getTextDocument().getVersion());
        }
    }

    @Override
    public void didClose(DidCloseTextDocumentParams params) {
        // System.err.println("Closed " + params);
        String uri = params.getTextDocument().getUri();
        fileContents.remove(uri);
        fileVersions.remove(uri);
        savedVersions.remove(uri);
    }

    @Override
    public void didSave(DidSaveTextDocumentParams params) {
        // System.err.println("Saved " + params);
        String uri = params.getTextDocument().getUri();
        if (!fileVersions.containsKey(uri)) {
            throw new IllegalStateException("File saved before it was changed");
        }
        savedVersions.put(uri, fileVersions.get(uri));
        triggerBuild(false);
    }

    @Override
    public CompletableFuture<Object> executeCommand(ExecuteCommandParams params) {
        switch (params.getCommand()) {
        case "silver.clean":
            triggerBuild(true);
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
        Map<String, List<TextEdit>> edits = new HashMap<>();
        for (FileCreate f : params.getFiles()) {
            if (!f.getUri().endsWith(".md")) {
                SilverUtil.uriToGrammar(f.getUri()).ifPresent(grammar -> {
                    String grammarDecl = "grammar " + grammar + ";\n\n";
                    TextEdit edit = new TextEdit(new Range(new Position(0, 0), new Position(0, 0)), grammarDecl);
                    edits.put(f.getUri(), List.of(edit));
                });
            }
        }
        client.applyEdit(new ApplyWorkspaceEditParams(new WorkspaceEdit(edits)));
        refreshWorkspace();
    }

    @Override
    public void didDeleteFiles(DeleteFilesParams params) {
        refreshWorkspace();
    }

    @Override
    public void didRenameFiles(RenameFilesParams params) {
        refreshWorkspace();
    }

    @Override
    public CompletableFuture<Either<List<? extends Location>, List<? extends LocationLink>>> declaration(DeclarationParams params) {
        return CompletableFutures.computeAsync((cancelChecker) -> {
            if (comp == null) {
                return Either.forLeft(List.of());
            }

            String fileName = "";
            try {
                fileName = new URI(params.getTextDocument().getUri()).getPath();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return Either.forLeft(new ConsCellCollection<NLocation>(
                PfindDeclLocation.invoke(OriginContext.FFI_CONTEXT,
                    new StringCatter(fileName), params.getPosition().getLine() + 1, params.getPosition().getCharacter(), comp))
                .stream()
                .map((loc) -> new Location("file://" + Util.locationToFile(loc), Util.locationToRange(loc)))
                .collect(Collectors.toList()));
        });
    }

    public static final List<String> tokenTypes = Arrays.asList(new String[] {
        "namespace", "type", "interface", "class", "typeParameter", "parameter", "variable", "function",
        "keyword", "modifier", "comment", "string", "number", "regexp", "operator", "macro"
    });
    public static final List<String> tokenModifiers = Arrays.asList(new String[] {
        "declaration", "definition", "documentation", "defaultLibrary", "modification"
    });

    private CopperSemanticTokenEncoder semanticTokenEncoder = null;
    private CopperParserNodeFactory parserFn = null;
    public void setParserFactory(Supplier<SilverCopperParser<NRoot>> parserFactory) {
        semanticTokenEncoder = new CopperSemanticTokenEncoder(parserFactory, tokenTypes, tokenModifiers);
        parserFn = new CopperParserNodeFactory(parserFactory);
    }

    @Override
    public CompletableFuture<SemanticTokens> semanticTokensFull(SemanticTokensParams params) {
        if (semanticTokenEncoder == null) {
            throw new IllegalStateException("Semantic tokens requested when parser has not been initialized");
        }

        String uri = params.getTextDocument().getUri();
        return CompletableFutures.computeAsync((cancelChecker) -> {
            List<Integer> tokens;
            int requestVersion;
            // Recompute tokens if the file is changed while tokens are being computed
            do {
                requestVersion = fileVersions.get(uri);
                if (fileContents.containsKey(uri)) {
                    tokens = semanticTokenEncoder.parseTokens(fileContents.get(uri));
                } else {
                    tokens = new ArrayList<Integer>();
                }
            } while (requestVersion != fileVersions.get(uri));
            return new SemanticTokens(tokens);
        });
    }

    private void refreshWorkspace() {
        grammarDirs.clear();
        buildGrammars.clear();

        for (WorkspaceFolder folder : folders) {
            URI uri;
            try {
                uri = new URI(folder.getUri());
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException("Invalid URI", e);
            }
            findGrammars(new File(uri));
        }

        triggerBuild(false);
    }

    private void findGrammars(File root) {
        // Workaround to avoid copied resource grammars in the maven build
        // directory in this LSP plugin under the Silver repo.
        // We might want to add a more intelligent way of specifying grammars to
        // exclude in case duplicates are found.
        if (root.getPath().contains("/target/classes/")) return;

        for (File file : root.listFiles()) {
            if (file.isDirectory()) {
                findGrammars(file);
            } else if (SilverUtil.isValidSilverFileName(file.getName())) {
                Optional<String> fileGrammar = SilverUtil.uriToGrammar("file://" + file.getAbsolutePath());
                fileGrammar.ifPresent(buildGrammars::add);
                fileGrammar.ifPresent(grammar -> grammarDirs.add(
                    root.getAbsolutePath().replace("/", ":").replace(".", ":")
                    .split(grammar, 2)[0].replace(":", "/")));
            }
        }
    }

    public synchronized void triggerBuild(boolean cleanBuild) {
        this.cleanBuild = cleanBuild;

        buildTriggered = true;
        if (!buildInProgress && parserFn != null) {
            buildInProgress = true;
            new Thread(() -> {
                while(true) {
                    Map<String, Integer> buildVersions;
                    synchronized (this) {
                        buildTriggered = false;
                        buildVersions = new HashMap<>(savedVersions);
                    }
                    doBuild(buildVersions);
                    synchronized (this) {
                        if (!buildTriggered) {
                            buildInProgress = false;
                            break;
                        }
                    }
                };
            }).start();
        }
    }

    private void doBuild(Map<String, Integer> buildVersions) {
        System.err.println("Building");
        if (parserFn == null) {
            throw new IllegalStateException("Build requested when parser has not been loaded");
        }

        String silverHome = "";  // Not needed since we aren't doing translation
        List<String> args = new ArrayList<>();
        List<String> grammarPath = new ArrayList<>(grammarDirs);
        List<String> silverHostGen = List.of(silverGen);

        // Check the config for whether the MWDA is enabled
        ConfigurationItem enableMWDAConfigItem = new ConfigurationItem();
        enableMWDAConfigItem.setSection("silver.enableMWDA");
        ConfigurationParams configParams = new ConfigurationParams(List.of(enableMWDAConfigItem));
        boolean newEnableMWDA = enableMWDA;
        try {
            Object configMWDAGet = client.configuration(configParams).get().get(0);
            if (configMWDAGet != null && !((JsonElement)configMWDAGet).isJsonNull()) {
                newEnableMWDA = ((JsonPrimitive)configMWDAGet).getAsBoolean();
            }
        } catch (InterruptedException | ExecutionException e) {
            // Ignore, getting the settings sometimes fails when a build is triggered during initialization
        }

        if (newEnableMWDA && !enableMWDA) {
            // Do a clean build when the MWDA is initially enabled
            cleanBuild = true;
        }
        enableMWDA = newEnableMWDA;

        if (enableMWDA) {
            System.err.println("MWDA enabled");
            args.add("--warn-all");
        }

        // Clean build if requested
        if (cleanBuild) {
            System.err.println("Clean build");
            args.add("--clean");
        }

        if (silverStdlibGrammars == null) {
            throw new IllegalStateException("Silver host grammars path not set");
        } else {
            // Add the silver resource grammars to the end of the grammar path,
            // so if silver is in the workspace we will find it there first.
            grammarPath.add(silverStdlibGrammars);
        }

        // Set up the build environment
        NBuildEnv benv = new PbuildEnv(
            new StringCatter(silverHome),
            new StringCatter(silverGen),
            ConsCellCollection.fromStringList(grammarPath),
            ConsCellCollection.fromStringList(silverHostGen)
        );
        DecoratedNode a = PparseArgsOrError.invoke(OriginContext.FFI_CONTEXT, ConsCellCollection.fromStringList(args));

        // Build!
        DecoratedNode comp = (DecoratedNode)PunsafeEvalIO.invoke(OriginContext.FFI_CONTEXT,
            PbuildRun.invoke(OriginContext.FFI_CONTEXT,
                parserFn, a, benv,
                ConsCellCollection.fromIterator(buildGrammars.stream().<StringCatter>map(StringCatter::new).iterator())));

        // Note that we must demand allGrammars from comp before demanding
        // recompiledGrammars to ensure that IO happens properly,
        // due to the circularity in the driver involving unsafeInterleaveIO.
        Collection<DecoratedNode> allGrammars = new ConsCellCollection<>(
            comp.synthesized(silver.compiler.driver.util.Init.silver_compiler_driver_util_allGrammars__ON__silver_compiler_driver_util_Compilation));
        Collection<DecoratedNode> recompiledGrammars = new ConsCellCollection<>(
            comp.synthesized(silver.compiler.driver.util.Init.silver_compiler_driver_util_recompiledGrammars__ON__silver_compiler_driver_util_Compilation));

        // Check that we built all triggered grammars.
        Set<String> builtGrammars = allGrammars.stream()
            .map(r -> r.synthesized(silver.compiler.driver.util.Init.silver_compiler_definition_env_declaredName__ON__silver_compiler_driver_util_RootSpec).toString())
            .collect(Collectors.toSet());
        for (String grammar : buildGrammars) {
            if (!builtGrammars.contains(grammar)) {
                System.err.println("Failed to find triggered grammar " + grammar);
            }
        }

        // Report diagnostics
        System.err.println("Reporting diagnostics");
        for (DecoratedNode r : allGrammars) {
            String grammarSource =
                r.synthesized(silver.compiler.driver.util.Init.silver_compiler_definition_env_grammarSource__ON__silver_compiler_driver_util_RootSpec).toString();

            Collection<NPair> allFileErrors = new ConsCellCollection<>(
                r.synthesized(silver.compiler.driver.util.Init.silver_compiler_definition_core_allFileErrors__ON__silver_compiler_driver_util_RootSpec));
            for (NPair fileErrors : allFileErrors) {
                DecoratedNode decFileErrors = fileErrors.decorate();
                String fileName = decFileErrors.synthesized(silver.core.Init.silver_core_fst__ON__silver_core_Pair).toString();
                ConsCell messages = decFileErrors.synthesized(silver.core.Init.silver_core_snd__ON__silver_core_Pair);
                String uri = "file://" + grammarSource + fileName;

                // System.err.println("Reporting diagnostics for " + grammarSource + fileName);
                client.publishDiagnostics(new PublishDiagnosticsParams(uri, Util.messagesToDiagnostics(messages, uri), buildVersions.get(uri)));
            }
        }

        // Write updated interface files
        System.err.println("Writing interface files");
        for (DecoratedNode r : recompiledGrammars) {
            PunsafeEvalIO.invoke(OriginContext.FFI_CONTEXT,
                PwriteInterface.invoke(OriginContext.FFI_CONTEXT, new StringCatter(silverGen), r));
        }

        // Only update the compilation result once we are done reporting errors, to avoid race conditions from other attributes being demanded
        this.comp = comp;

        // Reset option flags
        this.cleanBuild = false;
    }
}
