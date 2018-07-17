package com.example.yueguo.myhw9;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import java.net.*;
import java.io.*;

/**
 * Created by YueGuo on 16/11/25.
 */

public class ImageLoader1 {

    private ImageView mImageView;
    private String mUrl;
    /*private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mImageView.setImageBitmap((Bitmap) msg.obj);
        }
    };
    public void showImageByThread(ImageView imageView, final String url){
        mImageView = imageView;

        new Thread(){
            public void run(){
                super.run();
                Bitmap bitmap = getBitemapFromURL(url);
                Message message = Message.obtain();
                message.obj = bitmap;
                mHandler.sendMessage(message);

            }
        }.start();

    }*/


    public Bitmap getDBitemapFromURL(String urlString){
        Bitmap bitmap;
        InputStream is = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            is = new BufferedInputStream(connection.getInputStream());
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            //bitmap = BitmapFactory.decodeStream(is);
            bitmap = BitmapFactory.decodeStream(is,null,options);  //内存超出问题
            //bitmap = BitmapFactory.decodeFile(urlString,options);
            connection.disconnect();
            return bitmap;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }
    public void showdetailImageByAsyncTask(ImageView imageView, String url){
        new DNewsAsyncTask(imageView,url).execute(url);


    }

    private class DNewsAsyncTask extends AsyncTask<String,Void,Bitmap>{
        private ImageView mImageView;
        private String mUrl;
        public DNewsAsyncTask(ImageView imageView, String url){
            mImageView = imageView;
            mUrl = url;

        }
        @Override
        protected Bitmap doInBackground(String... strings) {
            return getDBitemapFromURL(strings[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            mImageView.setImageBitmap(bitmap);
        }
    }
}
