package edu.umn.cs.melt.silver.langserver;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
import java.util.stream.Collectors;

import org.eclipse.lsp4j.Diagnostic;
import org.eclipse.lsp4j.DiagnosticSeverity;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.Range;

import common.ConsCell;
import common.DecoratedNode;
import common.javainterop.ConsCellCollection;
import silver.core.NLocation;
import silver.langutil.NMessage;

public class Util {
    public static Diagnostic messageToDiagnostic(final NMessage m, final String uri) {
        Diagnostic d = new Diagnostic();

        DecoratedNode decM = m.decorate();
        NLocation loc = decM.synthesized(silver.langutil.Init.silver_langutil_where__ON__silver_langutil_Message);
        d.setMessage(decM.synthesized(silver.langutil.Init.silver_langutil_message__ON__silver_langutil_Message).toString());

        DecoratedNode decLoc = loc.decorate();
        String locFile = decLoc.synthesized(silver.core.Init.silver_core_filename__ON__silver_core_Location).toString();
        if (uri.endsWith(locFile)) {
            d.setRange(new Range(
                new Position(
                    (int)decLoc.synthesized(silver.core.Init.silver_core_line__ON__silver_core_Location) - 1,
                    decLoc.synthesized(silver.core.Init.silver_core_column__ON__silver_core_Location)),
                new Position(
                    (int)decLoc.synthesized(silver.core.Init.silver_core_endLine__ON__silver_core_Location) - 1,
                    decLoc.synthesized(silver.core.Init.silver_core_endColumn__ON__silver_core_Location))
            ));
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
}
