package com.lequ.server.bootstrap.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//import org.json.JSONObject;
//
//import com.baidu.aip.speech.AipSpeech;
//import com.baidu.aip.util.Util;

public class testai {
//	public static final String APP_ID = "你的 App ID";
//    public static final String API_KEY = "你的 Api Key";
//    public static final String SECRET_KEY = "你的 Secret Key";
//
//	/**
//	 * @param args
//	 */
	public static void main(String[] args) {
		
		
		
		
//		CopyOfRoundRobinLoadBalance 
//		for (int i=0;i<30;i++)
//		{
//			 System.out.println((int)(1+Math.random()*10));
//		}
		

		//int rand = (int)(1+Math.random()*10) ;
//	int [] arr = {1,2,3,4};
//	//产生0-(arr.length-1)的整数值,也是数组的索引
//	int index=(int)(Math.random()*arr.length);
//	int rand = arr[index];

//		AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
//
//        // 可选：设置网络连接参数
//        client.setConnectionTimeoutInMillis(2000);
//        client.setSocketTimeoutInMillis(60000);
//
//        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
////        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
////        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理
//    	// 对本地语音文件进行识别
//	    String path = "D:\\code\\java-sdk\\speech_sdk\\src\\test\\resources\\16k_test.pcm";
//        // 调用接口
//        JSONObject res = client.asr(path, "pcm", 16000, null);
//        System.out.println(res.toString(2));
//
	}
//	public void synthesis(AipSpeech client)
//	{
//		// 对本地语音文件进行识别
//	    String path = "D:\\code\\java-sdk\\speech_sdk\\src\\test\\resources\\16k_test.pcm";
//	    JSONObject asrRes = client.asr(path, "pcm", 16000, null);
//	    System.out.println(asrRes);
//
//		// 对语音二进制数据进行识别
//		byte[] data = null;
//		try {
//			data = Util.readFileByBytes(path);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}     //readFileByBytes仅为获取二进制数据示例
//		JSONObject asrRes2 = client.asr(data, "pcm", 16000, null);
//	    System.out.println(asrRes);
//
//		// 对网络上音频进行识别
//		String url = "http://somehost/res/16k_test.pcm";
//	    String callback = "http://callbackhost/aip/dump";
//	    JSONObject res = client.asr(url, callback, "pcm", 16000, null);
//	    System.out.println(res);
//	}
	
	public String doSelect(){
		String result = "";
		List<String> list = new ArrayList<String>();
		
		list.add("1");
		list.add("2");
		
		return result;
		
	}
}
