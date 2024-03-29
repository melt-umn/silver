package edu.umn.cs.melt.lsp4jutil;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.lsp4j.Diagnostic;
import org.eclipse.lsp4j.DiagnosticSeverity;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.Range;

import common.ConsCell;
import common.DecoratedNode;
import common.SilverCopperParser;
import common.javainterop.ConsCellCollection;
import silver.core.NLocation;
import silver.langutil.NMessage;

/**
 * Generic utilities for LSP server implementations for languages implemented in Silver.
 *
 * @author krame505
 */
public class Util {
    public static String locationToFile(final NLocation loc) {
        return loc.synthesized(silver.core.Init.silver_core_filename__ON__silver_core_Location).toString();
    }

    public static Range locationToRange(final NLocation loc) {
        return new Range(
            new Position(
                (int)loc.synthesized(silver.core.Init.silver_core_line__ON__silver_core_Location) - 1,
                loc.synthesized(silver.core.Init.silver_core_column__ON__silver_core_Location)),
            new Position(
                (int)loc.synthesized(silver.core.Init.silver_core_endLine__ON__silver_core_Location) - 1,
                loc.synthesized(silver.core.Init.silver_core_endColumn__ON__silver_core_Location))
        );
    }

    public static Diagnostic messageToDiagnostic(final NMessage m, final String uri) {
        Diagnostic d = new Diagnostic();

        DecoratedNode decM = m.decorate();
        NLocation loc = decM.synthesized(silver.langutil.Init.silver_langutil_where__ON__silver_langutil_Message);
        d.setMessage(decM.synthesized(silver.langutil.Init.silver_langutil_message__ON__silver_langutil_Message).toString());

        if (uri.endsWith(locationToFile(loc))) {
            d.setRange(locationToRange(loc));
        } else {
            // The error isn't in the file for which we are generating diagnostics.
            // Produce a dummy diagnostic at the beginning of the file containing the actual location in the message.
            d.setMessage(decM.synthesized(silver.langutil.Init.silver_langutil_output__ON__silver_langutil_Message).toString());
            d.setRange(new Range(new Position(0, 0), new Position(0, 0)));
        }

        switch ((int)decM.synthesized(silver.langutil.Init.silver_langutil_severity__ON__silver_langutil_Message)) {
        case 0:
            d.setSeverity(DiagnosticSeverity.Information);
            break;
        case 1:
            d.setSeverity(DiagnosticSeverity.Warning);
            break;
        case 2:
            d.setSeverity(DiagnosticSeverity.Error);
            break;
        }

        return d;
    }

    public static List<Diagnostic> messagesToDiagnostics(final ConsCell ms, final String uri) {
        return new ConsCellCollection<NMessage>(ms).stream()
            .map((m) -> messageToDiagnostic(m, uri))
            .collect(Collectors.toList());
    }

    /**
     * Copy a resource file or directory from a jar to a path.
     *
     * @param clazz A class from the jar containing the resource
     * @param source The resource path within the jar
     * @param target The path to which the resource should be copied
     * @throws URISyntaxException
     * @throws IOException
     */
    public static void copyFromJar(Class<?> clazz, String source, final Path target) throws URISyntaxException, IOException {
        URI resource = clazz.getResource("").toURI();
        FileSystem fileSystem = FileSystems.newFileSystem(resource, Map.of());
        final Path jarPath = fileSystem.getPath(source);
        Files.walkFileTree(jarPath, new SimpleFileVisitor<Path>() {
            private Path currentTarget;

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                currentTarget = target.resolve(jarPath.relativize(dir).toString());
                Files.createDirectories(currentTarget);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.copy(file, target.resolve(jarPath.relativize(file).toString()), StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * Convert a grammar name to a Java package name
     * @param grammar The grammar name
     * @return The package name
     */
    public static String grammarToPackage(final String grammar) {
        return grammar.replace(':', '.');
    }

    /**
     * Reflectively initialize a Silver grammar, using the default ClassLoader.
     * This transitively initialize all dependencies.
     * 
     * @param grammar The name of the grammar to initialize
     */
    public static void initGrammar(final String grammar)
        throws SecurityException, ReflectiveOperationException {
        initGrammar(grammar, ClassLoader.getSystemClassLoader());
    }
    /**
     * Reflectively initialize a Silver grammar, using the specified ClassLoader.
     * This transitively initialize all dependencies.
     * 
     * @param grammar The name of the grammar to initialize
     * @param loader The ClassLoader from which to load the grammar's classes
     */
    public static void initGrammar(final String grammar, final ClassLoader loader)
        throws SecurityException, ReflectiveOperationException {
        Class<?> initClass = Class.forName(grammarToPackage(grammar) + ".Init", true, loader);
        initClass.getMethod("initAllStatics").invoke(null);
        initClass.getMethod("init").invoke(null);
        initClass.getMethod("postInit").invoke(null);
    }

    /**
     * Initialize a ClassLoader from a jar path.
     * 
     * @param jarPath The path to the jar
     * @return A ClassLoader for the jar
     */
    public static URLClassLoader getJarClassLoader(final Path jarPath) {
        try {
            return new URLClassLoader(new URL[] {jarPath.toUri().toURL()}, Util.class.getClassLoader());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Dynamically load a Copper parser from a compiled Silver grammar.
     * 
     * @param <ROOT> The start nonterminal type of the parser
     * @param loader The ClassLoader to use to load the parser
     * @param name The name of the declared parser, prefixed by its grammar
     * @param rootClass The class of the start nonterminal for the parser
     * @return A factory object for instantiating the parser
     * @throws SecurityException
     * @throws ReflectiveOperationException
     */
    @SuppressWarnings("unchecked")
    public static <ROOT> Supplier<SilverCopperParser<ROOT>> loadCopperParserFactory(
        final ClassLoader loader, final String name, final Class<ROOT> rootClass)
        throws SecurityException, ReflectiveOperationException {
        // Load the parser class
        String grammar = name.substring(0, Math.max(name.lastIndexOf(":"), 0));
        String pkg = grammarToPackage(grammar);
        String parserClassName = pkg + ".Parser_" + String.join("_", name.split(":"));
        Class<?> parserClass = Class.forName(parserClassName, true, loader);

        // Sanity check: make sure it's actually a Silver-declared Copper parser
        if (!SilverCopperParser.class.isAssignableFrom(parserClass)) {
            throw new ReflectiveOperationException("Loaded class is not a Silver-generated Copper parser");
        }

        // Hacky way of determining the actual start nonterminal type of the parser, from its superclass generic type args.
        Type[] genericParams = ((ParameterizedType)parserClass.getGenericSuperclass()).getActualTypeArguments();
        if (genericParams.length != 2) {
            // This should only fail if there is a change to the superclass of Copper-generated parsers.
            throw new ReflectiveOperationException("Could not determine parser root type: Parser class should extend SingleDFAEngine<ROOT, PARSE_EXCEPTION>");
        }
        Class<ROOT> actualRootClass = (Class<ROOT>)genericParams[0];

        // Check that the start nonterminal is correct
        if (!rootClass.isAssignableFrom(actualRootClass)) {
            throw new ReflectiveOperationException(
                "Loaded parser has the wrong start nonterminal: expected " + rootClass.getName() + ", got " + actualRootClass.getName());
        }

        // Initialize the grammar containing the parser.
        initGrammar(grammar, loader);

        // Set up the parser factory
        Constructor<?> constructor = parserClass.getConstructor();
        return () -> {
            try {
                // Invoke the constructor.
                // This unchecked cast should be safe due to the above sanity checks.
                return (SilverCopperParser<ROOT>)constructor.newInstance();
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                throw new RuntimeException("Error instantiating Copper parser", e);
            }
        };
    }
}
