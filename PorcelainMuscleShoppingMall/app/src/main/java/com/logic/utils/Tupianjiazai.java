package com.logic.utils;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class Tupianjiazai {
    private String uri;
    private ImageView imageView;
    private byte[] picByte;
    Bitmap bitmap=null;
    public void getPicture(String uri,ImageView imageView){
        this.uri = uri;
        this.imageView = imageView;
        new Thread(runnable).start();
    }
    @SuppressLint("HandlerLeak")
    Handler handle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (picByte != null) {
                     bitmap = BitmapFactory.decodeByteArray(picByte, 0, picByte.length);
                     imageView.setImageBitmap(bitmap);
                }
            }
        }
    };

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                URL url = new URL(uri);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("GET");
                conn.setReadTimeout(10000);

                if (conn.getResponseCode() == 200) {
                    InputStream fis =  conn.getInputStream();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] bytes = new byte[1024];
                    int length = -1;
                    while ((length = fis.read(bytes)) != -1) {
                        bos.write(bytes, 0, length);
                    }
                    picByte = bos.toByteArray();
                    bos.close();
                    fis.close();

                    Message message = new Message();
                    message.what = 1;
                    handle.sendMessage(message);
                }


            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
   /* public void setImageloading(final String URL, ImageView imageView){
          new Thread(){
              @Override
              public void run() {
                  try {
                      URL url = new URL(URL);
                      HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                      conn.setRequestMethod("GET");
                      conn.setReadTimeout(10000);

                      if (conn.getResponseCode() == 200) {
                          InputStream fis =  conn.getInputStream();
                          ByteArrayOutputStream bos = new ByteArrayOutputStream();
                          byte[] bytes = new byte[1024];
                          int length = -1;
                          while ((length = fis.read(bytes)) != -1) {
                              bos.write(bytes, 0, length);
                          }
                          picByte = bos.toByteArray();
                          bos.close();
                          fis.close();

                          Message message = new Message();
                          message.what = 2;
                          handle.sendMessage(message);
                      }


                  }catch (IOException e) {
                      e.printStackTrace();
                  }
              }
          }.start();
    }*/
}