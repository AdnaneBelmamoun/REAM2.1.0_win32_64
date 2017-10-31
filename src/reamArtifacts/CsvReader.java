/**
 * 
 */
/**
 * @author Adnane Belmamoun
 * @version REAM 2.1
 */

package reamArtifacts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.List;

class CsvReader {
	 
    private static final String SEPARATOR = ";";
 
    private final Reader source;
 
    CsvReader(Reader source) {
        this.source = source;
    }
    List<String> readHeader() {
        try (BufferedReader reader = new BufferedReader(source)) {
            return (List<String>) reader.lines().findFirst().map(line -> Arrays.asList(line.split(SEPARATOR))).get();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }    
}