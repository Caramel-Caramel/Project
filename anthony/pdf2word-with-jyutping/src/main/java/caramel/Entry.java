package caramel;

import caramel.impl.docx.DocxFileCreate;
import caramel.impl.pdf.PdfAnalysisPronounce;
import caramel.impl.xml.XmlFileCreate;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Entry {

    public static final String BASE_PATH = "E:\\102Project\\anthony\\pdf2word-with-jyutping\\src\\main\\resources";

    public static void main(String[] args) throws IOException, TemplateException {
        Set<String> hadTxtFile = hadFile(BASE_PATH + "\\txt");
        Set<String> hadXmlFile = hadFile(BASE_PATH + "\\xml");
        Set<String> hadDocxFile = hadFile(BASE_PATH + "\\docx");
        for (File pdfFile : new File(BASE_PATH + "\\pdf").listFiles()) {
            System.out.println("开始处理：" + pdfFile.getName());
            String txtFileName = pdfFile.getName().replace("pdf", "txt");
            String xmlFileName = pdfFile.getName().replace("pdf", "xml");
            String docxFileName = pdfFile.getName().replace("pdf", "docx");
            if (hadTxtFile.contains(txtFileName)) {
                System.out.println("无需生成txt文件");
            } else {
                PdfAnalysisPronounce.process(txtFileName, pdfFile.getName());
                System.out.println("生成txt文件：" + txtFileName);
            }
            if (hadXmlFile.contains(xmlFileName)) {
                System.out.println("无需生成xml文件");
            } else {
                XmlFileCreate.create(xmlFileName, txtFileName);
                System.out.println("生成xml文件：" + xmlFileName);
            }
            if (hadDocxFile.contains(docxFileName)) {
                System.out.println("无需生成docx文件");
            } else {
                DocxFileCreate.create(docxFileName, xmlFileName);
                System.out.println("生成docx文件：" + docxFileName);
            }
            System.out.println("结束处理：" + pdfFile.getName());
            System.out.println("---------------------------------------------");
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
