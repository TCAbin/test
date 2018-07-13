package LiteNAdemo_https.src.com.huawei.service.dataCollection;


import LiteNAdemo_https.src.com.huawei.utils.Constant;
import LiteNAdemo_https.src.com.huawei.utils.HttpsUtil;
import LiteNAdemo_https.src.com.huawei.utils.JsonUtil;
import LiteNAdemo_https.src.com.huawei.utils.StreamClosedHttpResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Query Device Data :
 * This interface is used by NAs to query information for specify device.
 */
public class QueryDeviceData {

    public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication，get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;

        //please replace the deviceId, when you use the demo.
        String deviceId = "30c4c925-30e2-49cc-8f8d-fac02b73523f";
        String urlQueryDeviceData = Constant.QUERY_DEVICE_DATA + "/" + deviceId;

        Map<String, String> paramQueryDeviceData = new HashMap<>();
        paramQueryDeviceData.put("appId", appId);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse bodyQueryDeviceData = httpsUtil.doGetWithParasGetStatusLine(urlQueryDeviceData,
                paramQueryDeviceData, header);

        Map<String, String> data = new HashMap<>();
        data = JsonUtil.jsonString2SimpleObj(bodyQueryDeviceData.getContent(), data.getClass());
        JSONObject object = JSONArray.fromObject(data.get("services")).getJSONObject(0);
        JSONObject object2 = JSONArray.fromObject(data.get("deviceInfo")).getJSONObject(0);


        System.out.println("QueryDeviceData, response content:");
        System.out.print(bodyQueryDeviceData.getStatusLine());
        System.out.println(bodyQueryDeviceData.getContent());
        System.out.println();
    }

    /**
     * Authentication，get token
     * */
    @SuppressWarnings("unchecked")
    public static String login(HttpsUtil httpsUtil) throws Exception {

        String appId = Constant.APPID;
        String secret = Constant.SECRET;
        String urlLogin = Constant.APP_AUTH;

        Map<String, String> paramLogin = new HashMap<>();
        paramLogin.put("appId", appId);
        paramLogin.put("secret", secret);

        StreamClosedHttpResponse responseLogin = httpsUtil.doPostFormUrlEncodedGetStatusLine(urlLogin, paramLogin);

        System.out.println("app auth success,return accessToken:");
        System.out.print(responseLogin.getStatusLine());
        System.out.println(responseLogin.getContent());
        System.out.println();

        Map<String, String> data = new HashMap<>();
        data = JsonUtil.jsonString2SimpleObj(responseLogin.getContent(), data.getClass());
        return data.get("accessToken");
    }

}
