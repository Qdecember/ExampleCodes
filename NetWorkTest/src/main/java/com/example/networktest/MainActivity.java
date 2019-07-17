package com.example.networktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendRequest = (Button)findViewById(R.id.send_request);
        responseText = (TextView)findViewById(R.id.response_text);
        sendRequest.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send_request) {
            sendRequestWithOkHttp();
        }
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://baidu.com")
                            .build();
                    Response response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



    //原始HttpURLConnection方法 向服务器发起请求
//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.send_request){
//             sendRequestWithHttpURLConnection();
//        }
//    }
//
//    private void  sendRequestWithHttpURLConnection() {
//        //开启线程发起网络请求
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                HttpURLConnection connection = null;
//                BufferedReader reader = null;
//                try {
//                    //发出一条Http请求 请求的目标地市是百度
//                    URL url = new URL("http://www.baidu.com");
//                    connection = (HttpURLConnection)url.openConnection();
//                    connection.setRequestMethod("GET");
//                    connection.setConnectTimeout(8000);
//                    connection.setReadTimeout(8000);
//
//                    //向服务器提交请求方法
//                    //每条数据都要以键值对的形式存在，数据与数据之间用&隔开
////                    connection.setRequestMethod("POST");
////                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
////                    out.writeBytes("username=admin&password=123456");  向服务器提交用户名和密码
//
//
//                    //对服务器的返回流进行读取
//                    InputStream inputStream = connection.getInputStream();
//                    reader = new BufferedReader(new InputStreamReader(inputStream));
//                    StringBuilder response = new StringBuilder();
//                    String line;
//                    while ((line = reader.readLine())!= null) {
//                        response.append(line);
//                    }
//                   showResponse(response.toString());
//
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }finally {
//                    if (reader!=null) {
//                        try {
//                            reader.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (connection != null) {
//                        connection.disconnect();
//                    }
//                }
//            }
//        }).start();
//    }
//
//    private void showResponse(final String response) {
//        //在这里进行UI操作，将结果显示到界面上
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                responseText.setText(response);
//            }
//        });
//    }
}
