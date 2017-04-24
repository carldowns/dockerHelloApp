package com.pwllc.util;

import com.google.common.collect.Multimap;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public class FileUtils {


    public static String getResource (String resource) throws URISyntaxException, IOException {

        FileSystem jarFileSystem = null;
        InputStream is = null;
        Path path = null;
        String result = "";

        URI uri = FileUtils.class.getResource(resource).toURI();
        try {

            // discern between running in a jar vs non-jar (IDE) environment
            if (uri.getScheme().equals("jar")) {
                jarFileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap());
                path = jarFileSystem.getPath(resource);
            }
            else {
                path = Paths.get(uri);
            }

            Stream<Path> walk = Files.walk(path, 4);
            for (Iterator<Path> it = walk.iterator(); it.hasNext(); ) {
                Path currentPath = it.next();

                if (Files.isDirectory(currentPath)) {
                    continue;
                }

                //logger.debug("loading resource: " + currentPath);

                is = jarFileSystem != null ?
                        FileUtils.class.getResourceAsStream(currentPath.toString()) :
                        new FileInputStream(currentPath.toFile());

                result = new BufferedReader(new InputStreamReader(is))
                        .lines().collect(Collectors.joining("\n"));

            }
        }
        finally {
            if (jarFileSystem != null) {
                jarFileSystem.close();
            }
            if (is != null) {
                is.close();
            }
        }

        return result;
    }
}
