package com.yjie.architecture.common

import android.content.ContentUris
import android.net.Uri

/**
 * <pre>
 *     author : yjie.liang
 *     time   : 2021/06/23
 *     desc   : 播放相关扩展方法
 *     version: 1.0
 * </pre>
 */

/**
 * 通过id获取专辑图片uri
 */
fun albumById(albumId:Long): Uri {
    return ContentUris.withAppendedId(
        Uri.parse("content://media/external/audio/albumart"),
        albumId
    )
}