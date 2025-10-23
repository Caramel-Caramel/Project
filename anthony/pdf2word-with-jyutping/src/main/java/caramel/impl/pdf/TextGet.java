package caramel.impl.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TextGet {

    public static void main(String[] args) throws IOException {
        String[] text = getText("E:\\102Project\\anthony\\pdf2word-with-jyutping\\src\\main\\resources\\pdf\\CHI1001_2.4_主題與形象分析.pdf");
        System.out.println(text);
    }

    public static String[] getText(String pdfFilePathAndName) throws IOException {
        try (PDDocument document = PDDocument.load(new File(pdfFilePathAndName))) {
            PDFTextStripper pdfStripper = new PDFTextStripper() {
                // 可以重写writeString方法来自定义文本输出格式或处理逻辑
                @Override
                protected void writeString(String string, List<TextPosition> textPositions) throws IOException {
                    super.writeString(string, textPositions);
                }
            };
            // 获取文本内容
            String text = pdfStripper.getText(document);
            return text.split("\r\n");
        }
    }
}
