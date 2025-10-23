package caramel.impl.pdf;

import caramel.Entry;
import caramel.impl.pronounce.GetPronounce;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PdfAnalysisPronounce {

    public static void process(String pdfFileName, String txtFileName) throws IOException {
        String[] textArray = TextGet.getText(pdfFileName);
        StringBuilder sb = new StringBuilder();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Entry.BASE_PATH + "\\txt\\" + txtFileName))) {
            for (String text : textArray) {
                sb.append(text.replace('[', ' ').replace(']', ' ')).append("\r\n");
                if (sb.length() > 1000) {
                    String result = GetPronounce.getTextWithPronounce(sb.toString());
                    writer.write(result);
                    writer.newLine();
                    sb = new StringBuilder();
                }
            }
        }
    }
}
