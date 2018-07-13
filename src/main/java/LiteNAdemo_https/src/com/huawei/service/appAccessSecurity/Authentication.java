package LiteNAdemo_https.src.com.huawei.service.appAccessSecurity;


import LiteNAdemo_https.src.com.huawei.utils.Constant;
import LiteNAdemo_https.src.com.huawei.utils.HttpsUtil;
import LiteNAdemo_https.src.com.huawei.utils.JsonUtil;
import LiteNAdemo_https.src.com.huawei.utils.StreamClosedHttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 *  Auth:
 *  This interface is used to authenticate third-party systems before third-party systems access open APIs.
 */
public class Authentication {
    @SuppressWarnings({"src/resource", "unchecked" })
    public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        String appId = Constant.APPID;
        String secret = Constant.SECRET;
        String urlLogin = Constant.APP_AUTH;

        Map<String, String> param = new HashMap<>();
        param.put("appId", appId);
        param.put("secret", secret);

        StreamClosedHttpResponse responseLogin = httpsUtil.doPostFormUrlEncodedGetStatusLine(urlLogin, param);

        System.out.println("app auth success,return accessToken:");
        System.out.print(responseLogin.getStatusLine());
        System.out.println(responseLogin.getContent());
        System.out.println();

        //resolve the value of accessToken from responseLogin.
        Map<String, String> data = new HashMap<>();
        data = JsonUtil.jsonString2SimpleObj(responseLogin.getContent(), data.getClass());
        String accessToken = data.get("accessToken");
        System.out.println("accessToken:" + accessToken);

    }
}