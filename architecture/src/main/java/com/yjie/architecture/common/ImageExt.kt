package com.yjie.architecture.common

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.yjie.architecture.R


/**
 * <pre>
 *     author : yjie.liang
 *     time   : 2021/06/23
 *     desc   : 图片加载方法
 *     version: 1.0
 * </pre>
 */

fun ImageView.loadUrl(context: Context, url:String){
    Glide.with(context)
        .load(url)
        .transition(withCrossFade())
        .into(this)
}

fun ImageView.loadUri(context: Context, uri:Uri) {
    Glide.with(context)
        .load(uri)
        .transition(withCrossFade())
        .into(this)
}

/**
 * 高斯模糊加渐入渐出
 */
fun ImageView.loadBlurTrans(context: Context, uri:Uri, radius:Int){
    Glide.with(context)
        .load(uri)
        .thumbnail(0.1f).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .apply(RequestOptions.bitmapTransform(CenterBlurTransformation(radius, 8, context)))
        .transition(withCrossFade(400))
        .into(this)
}

/**
 * 圆形图片
 */
fun ImageView.loadCircle(context: Context, uri: Uri) {
    Glide.with(context)
        .load(uri)
        .apply(RequestOptions.bitmapTransform(CircleCrop()))
        .into(this)
}

fun ImageView.loadCircle(context: Context, url:String, radius: Int = 20){
    Glide.with(context)
        .load(url)
        .centerCrop()
        .error(R.drawable.ic_launcher)
        .transition(withCrossFade())
        .transform(GlideRoundTransform(context, radius))
        .into(this)
}
