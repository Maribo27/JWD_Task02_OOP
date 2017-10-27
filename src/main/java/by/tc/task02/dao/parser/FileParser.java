package by.tc.task02.dao.parser;

import java.net.URL;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileParser {
    private static final String NOT_SYMBOL_REGEX = ">[\\s]+<";
    private static final String START_OF_XML_FILE = "<\\?.*\\?>";
    private static final String COMMENT_LINE = "<!.*>";
    private static final String OPEN_SQUARE = "%5b";
    private static final String CLOSE_SQUARE = "%5d";
    private String path;

    public FileParser(URL fileURL) {
        path = fileURL.getPath();
    }

    public String fileToString() throws IOException{
        BufferedReader reader;
        path = path.replaceAll(OPEN_SQUARE, "[");
        path = path.replaceAll(CLOSE_SQUARE, "]");
        reader = new BufferedReader(new FileReader(path));

        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder();

        int currentChar;
        while ((currentChar = reader.read()) != -1) {
            stringBuilder.append((char) currentChar);
        }
        String string;
        string = stringBuilder.toString();
        reader.close();
        return getStringAfterConversion(string);
    }

    private String getStringAfterConversion(String string) {
        string = string.replaceAll(START_OF_XML_FILE,"");
        string = string.replaceAll(COMMENT_LINE, "");
        string = string.replaceAll(NOT_SYMBOL_REGEX,"><");
        string = string.trim();
        return string;
    }
}