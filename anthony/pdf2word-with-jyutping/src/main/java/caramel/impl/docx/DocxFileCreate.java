package caramel.impl.docx;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;

import caramel.Entry;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.nio.charset.StandardCharsets;

public class DocxFileCreate {

    public static void create(String docxFileName, String xmlFileName) throws IOException, TemplateException {
        // 1、创建配置对象
        Configuration config = new Configuration(Configuration.VERSION_2_3_30);
        config.setDefaultEncoding("utf-8");
        config.setDirectoryForTemplateLoading(new File(Entry.BASE_PATH + "\\xml"));
        // 2、获取模板文件
        Template template = config.getTemplate(xmlFileName);
        // 3、创建生成的文件对象
        File file = new File(Entry.BASE_PATH + "\\docx\\" + docxFileName);
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(file.toPath()), StandardCharsets.UTF_8));) {
            // 4、渲染模板文件
            template.process(new HashMap<>(), writer);
        }
    }
}
