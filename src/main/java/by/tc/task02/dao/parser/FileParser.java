package by.tc.task02.dao.parser;

import java.net.URL;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileParser {
    private static final String NOT_SYMBOL_REGEX = ">[\\s]+<";
    private static final String START_OF_XML_FILE = "<\\?.*\\?>";
    private static final String COMMENT_LINE = "<!--.*-->";
    private String path;

    public FileParser(URL fileURL) {
        path = fileURL.getPath();
    }

    public String fileToString(){
        BufferedReader reader;
        try {
            path = path.replaceAll("%5b", "[");
            path = path.replaceAll("%5d", "]");
            reader = new BufferedReader(new FileReader(path));

            StringBuilder stringBuilder;
            stringBuilder = new StringBuilder();

            int c;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
            String string;
            string = stringBuilder.toString();
            reader.close();
            return getStringAfterConversion(string);
        } catch (IOException e) {
            System.exit(-1);
            return null;
        }
    }

    private String getStringAfterConversion(String string) {
        string = string.replaceAll(START_OF_XML_FILE,"");
        string = string.replaceAll(COMMENT_LINE, "");
        string = string.replaceAll(NOT_SYMBOL_REGEX,"><");
        string = string.trim();

        return string;
    }
}