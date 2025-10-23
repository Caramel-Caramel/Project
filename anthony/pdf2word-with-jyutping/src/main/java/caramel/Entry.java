package caramel;

import caramel.impl.docx.DocxFileCreate;
import caramel.impl.pdf.PdfAnalysisPronounce;
import caramel.impl.xml.XmlFileCreate;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Entry {

    public static final String BASE_PATH = "E:\\102Project\\anthony\\pdf2word-with-jyutping\\src\\main\\resources";

    public static void main(String[] args) throws IOException {
        Set<String> hadTxtFile = hadFile(BASE_PATH + "\\txt");
        Set<String> hadXmlFile = hadFile(BASE_PATH + "\\xml");
        Set<String> hadDocxFile = hadFile(BASE_PATH + "\\docx");
        for (File pdfFile : new File(BASE_PATH + "\\pdf").listFiles()) {
            String txtFileName = pdfFile.getName().replace("pdf", "txt");
            String xmlFileName = pdfFile.getName().replace("pdf", "xml");
            String docxFileName = pdfFile.getName().replace("pdf", "docx");
            if (!hadTxtFile.contains(txtFileName)) {
                PdfAnalysisPronounce.process(txtFileName, pdfFile.getName());
            }
            if (!hadXmlFile.contains(xmlFileName)) {
                XmlFileCreate.create(xmlFileName, txtFileName);
            }
            if (!hadDocxFile.contains(docxFileName)) {
                DocxFileCreate.create(docxFileName, xmlFileName);
            }
        }
    }

    private static Set<String> hadFile(String filePath) {
        File path = new File(filePath);
        File[] files = path.listFiles();
        Set<String> set = new HashSet<>();
        if (files == null) {
            return set;
        }
        for (File file : files) {
            set.add(file.getName());
        }
        return set;
    }
}
