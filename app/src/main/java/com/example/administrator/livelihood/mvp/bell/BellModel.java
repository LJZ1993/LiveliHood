package com.example.administrator.livelihood.mvp.bell;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.livelihood.bean.BellBean;
import com.example.administrator.livelihood.util.OnResultListener;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by Administrator on 2016/11/11.
 */
public class BellModel implements BellContract.Model {

    @Override
    public void obtainData(Context context, OnResultListener listener) {
        try {
            ContentResolver contentResolver = context.getContentResolver();
            Cursor cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
            ArrayList<BellBean> mBellList = new ArrayList<>();
            BellBean bellBean = new BellBean();
            while (cursor.moveToNext()) {
                long id = cursor.getLong(cursor
                        .getColumnIndex(MediaStore.Audio.Media._ID));    //音乐id
                String title = cursor.getString((cursor
                        .getColumnIndex(MediaStore.Audio.Media.TITLE)));//音乐标题
                Log.w("title", "title" + title);
                String artist = cursor.getString(cursor
                        .getColumnIndex(MediaStore.Audio.Media.ARTIST));//艺术家

                long duration = cursor.getLong(cursor
                        .getColumnIndex(MediaStore.Audio.Media.DURATION));//时长
                //下面是不需要的数据

                long size = cursor.getLong(cursor
                        .getColumnIndex(MediaStore.Audio.Media.SIZE));    //文件大小

                String url = cursor.getString(cursor
                        .getColumnIndex(MediaStore.Audio.Media.DATA));    //文件路径

                String album = cursor.getString(cursor
                        .getColumnIndex(MediaStore.Audio.Media.ALBUM)); //唱片图片

                long album_id = cursor.getLong(cursor
                        .getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)); //唱片图片ID

                int isMusic = cursor.getInt(cursor
                        .getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));//是否为音乐
                bellBean.setId(id);
                bellBean.setTime(duration);
                bellBean.setArtist(artist);
                bellBean.setTitle(title);
                mBellList.add(bellBean);
            }
            if (mBellList.size() > 0) {
                listener.onSuccess(mBellList);
            } else {
                listener.onFailded("手机中没有audio文件");
            }
        } catch (Exception e) {
            Log.w("Exception",""+e.toString());
            Toast.makeText(context, "没有读取音乐的权限", Toast.LENGTH_SHORT).show();

        }

    }
}
