package com.lequ.common.util;

import java.io.IOException;

import org.json.JSONObject;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.util.Util;

public class LocalSpeech {
	public void synthesis(AipSpeech client)
	{
	    // 对本地语音文件进行识别
	    String path = "D:\\code\\java-sdk\\speech_sdk\\src\\test\\resources\\16k_test.pcm";
	    JSONObject asrRes = client.asr(path, "pcm", 16000, null);
	    System.out.println(asrRes);

	    // 对语音二进制数据进行识别
	    byte[] data;
		try {
			data = Util.readFileByBytes(path);
			 JSONObject asrRes2 = client.asr(data, "pcm", 16000, null);
			    System.out.println(asrRes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     //readFileByBytes仅为获取二进制数据示例
	   

	    // 对网络上音频进行识别
	    String url = "http://somehost/res/16k_test.pcm";
	    String callback = "http://callbackhost/aip/dump";
	    JSONObject res = client.asr(url, callback, "pcm", 16000, null);
	    System.out.println(res);
	}
}
