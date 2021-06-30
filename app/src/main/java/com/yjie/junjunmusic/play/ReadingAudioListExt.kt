package com.yjie.junjunmusic.play

import android.content.Context
import android.database.Cursor
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import com.yjie.junjunmusic.play.bean.AudioBean

/**
 * 通过ContentProvider读取本地音频文件
 */
@RequiresApi(Build.VERSION_CODES.R)
fun readingLocalList(context: Context): MutableList<AudioBean> {
    val audioList = mutableListOf<AudioBean>()

    val cursor: Cursor? = context.contentResolver.query(
        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
        null,
        null,
        null,
        MediaStore.Audio.Media.DEFAULT_SORT_ORDER
    )
    if (cursor != null) {
        while (cursor.moveToNext()) {
            val audioBean = AudioBean()
            audioBean.id =
                cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID))
            audioBean.name =
                cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
            audioBean.singer =
                cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
            audioBean.path =
                cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
            audioBean.duration =
                cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION))
            audioBean.size =
                cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE))
            audioBean.albumId =
                cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID))
            audioBean.playListType = PlayList.Type.LOCAL_LIST
            //筛选音频时长大于一分钟
            if (audioBean.duration > 60000) {
                audioList.add(audioBean)
            }
        }
        cursor.close()
    }
    return audioList
}

