package caramel.impl.xml;

import caramel.Entry;

import java.io.*;

/**
 * 生成 XML 文件
 *
 * @author 焦糖
 */
public class XmlFileCreate {

    public static void main(String[] args) {
        create("", "");
    }

    public static void create(String xmlFileName, String txtFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Entry.BASE_PATH + "\\xml\\" + xmlFileName))) {
            writer.write(XmlCst.XML_START);
            writer.newLine();
            try (BufferedReader reader = new BufferedReader(new FileReader(Entry.BASE_PATH + "\\txt\\" + txtFileName))) {
                String string = reader.readLine();
                while (string != null) {
                    writer.write(XmlCst.LINE_START);
                    // 处理每一行的数据
                    processLine(writer, string);
                    writer.write(XmlCst.LINE_END);
                    string = reader.readLine();
                }
            }
            writer.write(XmlCst.XML_END);
        } catch (Exception e) {
            System.out.println("XML 文件生成异常：" + xmlFileName);
            throw new RuntimeException();
        }
    }

    private static void processLine(BufferedWriter writer, String string) throws IOException {
        boolean isJyutping = false;
        StringBuilder jyutping = new StringBuilder();
        StringBuilder text = new StringBuilder();
        for (char c : string.toCharArray()) {
            if (c == ']') {
                String word = XmlCst.WORD_WITH_JYUTPING.replace("${jyutping}", jyutping.toString()).replace("${text}", text.toString());
                writer.write(word);
                jyutping = new StringBuilder();
                text = new StringBuilder();
                isJyutping = false;
            } else if (c == '[') {
                isJyutping = true;
            } else {
                if (isJyutping) {
                    jyutping.append(c);
                } else {
                    if (text.length() > 0) {
                        writer.write(XmlCst.ONLY_WORD.replace("${符号}", text.toString()));
                        text = new StringBuilder();
                    }
                    text.append(c);
                }
            }
        }
    }
}
