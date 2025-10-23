package caramel.impl.pronounce;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GetPronounce {

    private static final okhttp3.OkHttpClient HTTP_CLIENT = new okhttp3.OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(10, 1, TimeUnit.MINUTES))
            .build();

    public static void main(String[] args) throws IOException {
        http("");
    }

    public static String http(String text) throws IOException {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        // 添加表单字段
        builder.addFormDataPart("input", text);
        builder.addFormDataPart("kpc_selectpingyam", "py_jyutping");
        builder.addFormDataPart("kpc_dicConv", "off");
        builder.addFormDataPart("kpc_dicConv","on");
        builder.addFormDataPart("kpc_forceConv","off");
        builder.addFormDataPart("kpc_forceConvPingyam","py_yale_16");
        builder.addFormDataPart("kpc_display_mode","kpc_display_mode_yoko_1_on_1");
        builder.addFormDataPart("kpc_us_yoko_1_on_1_no_kakko","off");
        builder.addFormDataPart("kpc_us_yoko_1_on_1_change_kakko","off");
        builder.addFormDataPart("kpc_us_yoko_1_on_1_change_kakko_open","(");
        builder.addFormDataPart("kpc_us_yoko_1_on_1_change_kakko_close",")");
        builder.addFormDataPart("kpc_us_yoko_1_on_1_add_space","off");
        builder.addFormDataPart("kpc_us_only_pingyam_no_space","off");
        builder.addFormDataPart("kpc_us_only_pingyam_no_display_cannot_kanji","off");
        builder.addFormDataPart("kpc_us_only_pingyam_no_conv_hankaku","off");
        builder.addFormDataPart("kpc_NoConvArabicNum","off");
        builder.addFormDataPart("kpc_notShowKanjiDescChar","off");
        builder.addFormDataPart("kpc_notShowKanjiDescCharList","\n 　,.!?。、，！？");
        builder.addFormDataPart("kpc_showErrorLog", "off");

        // 构建请求体
        RequestBody requestBody = builder.build();

        // 创建请求对象
        Request request = new Request.Builder()
                .url("https://hongkongvision.com/tool/cc_py_conv_zh")
                .post(requestBody)
                .build();

        try (Response response = HTTP_CLIENT.newCall(request).execute()) {
            // 处理响应
            return response.body().string();
        }
    }
}
