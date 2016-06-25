package com.logic.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/6/11.
 */
public class LoadImagesUtils {
         private ImageView imageView;
         private byte[] picByte;
         public LoadImagesUtils(ImageView imageView) {
        this.imageView = imageView;
    }

    public void LoadImaegs(String url){
             new AsyncTask<String,Void,String>(){
                 @Override
                 protected void onPreExecute() {
                     super.onPreExecute();
                 }

                 @Override
                 protected void onPostExecute(String s) {
                     super.onPostExecute(s);
                     Bitmap  bitmap = BitmapFactory.decodeByteArray(picByte, 0, picByte.length);
                     imageView.setImageBitmap(bitmap);
                 }

                 @Override
                 protected void onProgressUpdate(Void... values) {
                     super.onProgressUpdate(values);
                 }

                 @Override
                 protected void onCancelled(String s) {
                     super.onCancelled(s);
                 }

                 @Override
                 protected void onCancelled() {
                     super.onCancelled();
                 }

                 @Override
                 protected String doInBackground(String... params) {
                      picByte = new byte[1024];
                   try {
                       URL url = new URL(params[0]);
                       HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                       conn.setRequestMethod("GET");
                       conn.setReadTimeout(10000);
                       if (conn.getResponseCode() == 200){
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

                       }
                   }catch (Exception e){
                       e.printStackTrace();
                   }
                     return null;
                 }
             }.execute(url);
         }
}
