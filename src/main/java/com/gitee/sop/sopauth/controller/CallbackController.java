package com.gitee.sop.sopauth.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gitee.sop.sdk.client.OpenClient;
import com.gitee.sop.sdk.model.OpenAuthTokenAppModel;
import com.gitee.sop.sdk.request.OpenAuthTokenAppRequest;
import com.gitee.sop.sdk.response.OpenAuthTokenAppResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liumf
 */
@Controller
@Slf4j
public class CallbackController {
    String url = "http://localhost:8087/oauth2/open.auth.token.app/";
    String appId = "2019032617262200001";
    // 平台提供的私钥
    String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCXJv1pQFqWNA/++OYEV7WYXwexZK/J8LY1OWlP9X0T6wHFOvxNKRvMkJ5544SbgsJpVcvRDPrcxmhPbi/sAhdO4x2PiPKIz9Yni2OtYCCeaiE056B+e1O2jXoLeXbfi9fPivJZkxH/tb4xfLkH3bA8ZAQnQsoXA0SguykMRZntF0TndUfvDrLqwhlR8r5iRdZLB6F8o8qXH6UPDfNEnf/K8wX5T4EB1b8x8QJ7Ua4GcIUqeUxGHdQpzNbJdaQvoi06lgccmL+PHzminkFYON7alj1CjDN833j7QMHdPtS9l7B67fOU/p2LAAkPMtoVBfxQt9aFj7B8rEhGCz02iJIBAgMBAAECggEARqOuIpY0v6WtJBfmR3lGIOOokLrhfJrGTLF8CiZMQha+SRJ7/wOLPlsH9SbjPlopyViTXCuYwbzn2tdABigkBHYXxpDV6CJZjzmRZ+FY3S/0POlTFElGojYUJ3CooWiVfyUMhdg5vSuOq0oCny53woFrf32zPHYGiKdvU5Djku1onbDU0Lw8w+5tguuEZ76kZ/lUcccGy5978FFmYpzY/65RHCpvLiLqYyWTtaNT1aQ/9pw4jX9HO9NfdJ9gYFK8r/2f36ZE4hxluAfeOXQfRC/WhPmiw/ReUhxPznG/WgKaa/OaRtAx3inbQ+JuCND7uuKeRe4osP2jLPHPP6AUwQKBgQDUNu3BkLoKaimjGOjCTAwtp71g1oo+k5/uEInAo7lyEwpV0EuUMwLA/HCqUgR4K9pyYV+Oyb8d6f0+Hz0BMD92I2pqlXrD7xV2WzDvyXM3s63NvorRooKcyfd9i6ccMjAyTR2qfLkxv0hlbBbsPHz4BbU63xhTJp3Ghi0/ey/1HQKBgQC2VsgqC6ykfSidZUNLmQZe3J0p/Qf9VLkfrQ+xaHapOs6AzDU2H2osuysqXTLJHsGfrwVaTs00ER2z8ljTJPBUtNtOLrwNRlvgdnzyVAKHfOgDBGwJgiwpeE9voB1oAV/mXqSaUWNnuwlOIhvQEBwekqNyWvhLqC7nCAIhj3yvNQKBgQCqYbeec56LAhWP903Zwcj9VvG7sESqXUhIkUqoOkuIBTWFFIm54QLTA1tJxDQGb98heoCIWf5x/A3xNI98RsqNBX5JON6qNWjb7/dobitti3t99v/ptDp9u8JTMC7penoryLKK0Ty3bkan95Kn9SC42YxaSghzqkt+uvfVQgiNGQKBgGxU6P2aDAt6VNwWosHSe+d2WWXt8IZBhO9d6dn0f7ORvcjmCqNKTNGgrkewMZEuVcliueJquR47IROdY8qmwqcBAN7Vg2K7r7CPlTKAWTRYMJxCT1Hi5gwJb+CZF3+IeYqsJk2NF2s0w5WJTE70k1BSvQsfIzAIDz2yE1oPHvwVAoGAA6e+xQkVH4fMEph55RJIZ5goI4Y76BSvt2N5OKZKd4HtaV+eIhM3SDsVYRLIm9ZquJHMiZQGyUGnsvrKL6AAVNK7eQZCRDk9KQz+0GKOGqku0nOZjUbAu6A2/vtXAaAuFSFx1rUQVVjFulLexkXR3KcztL1Qu2k5pB6Si0K/uwQ=";

    OpenClient openClient = new OpenClient(url, appId, privateKey);

    /**
     * 模拟开发者回调，这里需要开发者自己实现.通过code换取token
     * @return
     */
    @GetMapping("oauth2callback")
    @ResponseBody
    public OpenAuthTokenAppResponse callback(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        String app_id = servletRequest.getParameter("app_id");
        String code = servletRequest.getParameter("code");
        log.info("app_id:{}, code:{}", app_id, code);

        OpenAuthTokenAppRequest request = new OpenAuthTokenAppRequest();
        OpenAuthTokenAppModel model = new OpenAuthTokenAppModel();
        model.setCode(code);
        model.setGrant_type("authorization_code");
        request.setBizModel(model);

        // 根据code获取token
//        OpenAuthTokenAppResponse response = openClient.execute(request);
        
        Map<String, String> map = new HashMap<String,String>();
        map.put("biz_content", "{\"code\":\""+code+"\",\"grant_type\":\"authorization_code\"}");
        map.put("app_id",app_id);
//        System.out.println(doPost(url, map, "utf-8"));
        /**
        if (response.isSuccess()) {
            // 成功拿到token，开发者在这里保存token信息
            // 后续使用token进行接口访问
            log.info("授权成功，body:{}", response.getBody());
        }
        return response;
        **/
        return null;
    }
    
//    @SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
//	public static String doPost(String url, Map<String, String> params,
//			String charset) {
//		String result = "";
//
//		try {
//			// 创建httpClient
//			DefaultHttpClient httpClient = new DefaultHttpClient();
//			// 创建httpPost，设置url
//			HttpPost httpPost = new HttpPost(url);
//
//			// 设置参数 ,将传入的参数，放在集合中
//			List<NameValuePair> list = new ArrayList<NameValuePair>();
//			Iterator iterator = params.entrySet().iterator();
//			while (iterator.hasNext()) {
//				Entry<String, String> elem = (Entry<String, String>) iterator
//						.next();
//				list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
//			}
//			if (list.size() > 0) {
//				// 对参数进行编码转换
//				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,
//						charset);
//				// 参数放入httpPost
//				httpPost.setEntity(entity);
//			}
//			// 发送请求，返回response
//			int responseCount=1;
//			while(responseCount<=3){
//				HttpResponse response = httpClient.execute(httpPost);
//				if (response != null) {
//					// 获取返回的信息
//					HttpEntity resEntity = response.getEntity();
//					if (resEntity != null) {
//						// 将获取的返回信息 进行编码转换
//						result = EntityUtils.toString(resEntity, charset);
//						JSONObject resultFormat = JSONObject.parseObject(result);
//						String resultCode = (String)resultFormat.get("status");
//						if("200".equals(resultCode)){
//							break;
//						}
//					}
//				}
//				responseCount++;
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return result;
//	}
}
