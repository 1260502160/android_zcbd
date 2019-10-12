package com.sunshine.first.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by wr on 2018/2/27.
 */

public class GlideUtils {

    /**
     * Glide特点
     * 使用简单
     * 可配置度高，自适应程度高
     * 支持常见图片格式 Jpg png gif webp
     * 支持多种数据源  网络、本地、资源、Assets 等
     * 高效缓存策略    支持Memory和Disk图片缓存 默认Bitmap格式采用RGB_565内存使用至少减少一半
     * 生命周期集成   根据Activity/Fragment生命周期自动管理请求
     * 高效处理Bitmap  使用Bitmap Pool使Bitmap复用，主动调用recycle回收需要回收的Bitmap，减小系统回收压力
     * 这里默认支持Context，Glide支持Context,Activity,Fragment，FragmentActivity
     */
        /*
         参数	说明
         .load(String string)	string可以为一个文件路径、uri或者url
         .load(Uri uri)	uri类型
         .load(File file)	文件
         .load(Integer resourceId)	资源Id,R.drawable.xxx或者R.mipmap.xxx
         .load(byte[] model)	byte[]类型
         .load(T model)	自定义类型

         作者：倾城_之泪
         链接：https://www.jianshu.com/p/31c82862ef19
         來源：简书
         著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    */
    //默认加载 path

    public static String imgUrl = "https://acdors.oss-cn-beijing.aliyuncs.com/";

    //    private static String imgUrl = "http://39.106.100.235/";
    public static void loadImageView(Context mContext, String path, final ImageView mImageView) {
//        if (TextUtils.isEmpty(path) || null == mImageView) {
//            return;
//        }
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        RequestBuilder<Drawable> requestBuilder = Glide.with(mContext)
                .load(imgUrl + path);
        requestBuilder/*.thumbnail(0.1f)*/ // 加载模糊图
                .apply(options)
                .into(mImageView);
    }

    public static void loadLoacalImageView(Context mContext, String path, final ImageView mImageView) {
//        if (TextUtils.isEmpty(path) || null == mImageView) {
//            return;
//        }
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        RequestBuilder<Drawable> requestBuilder = Glide.with(mContext)
                .load(path);
        requestBuilder/*.thumbnail(0.1f)*/ // 加载模糊图
                .apply(options)
                .into(mImageView);
    }


    public static void loadImageView(Context mContext, String path, int placeHolder, final ImageView mImageView) {
        if (TextUtils.isEmpty(path) || null == mImageView) {
            return;
        }
        RequestOptions options = new RequestOptions()
                .placeholder(placeHolder)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        RequestBuilder<Drawable> requestBuilder = Glide.with(mContext)
                .load(path);
        requestBuilder/*.thumbnail(0.1f)*/ // 加载模糊图
                .apply(options)
                .into(mImageView);
    }

    //默认加载 URI
    public static void loadImageView(Context mContext, Uri path, ImageView mImageView) {
        Glide.with(mContext).load(path).into(mImageView);
    }


    //默认加载 resID
    public static void loadImageView(Context mContext, int resId, ImageView mImageView) {
        //  Glide.with(mContext).load(resId).into(mImageView);
        if (resId == -1 || null == mImageView) {
            return;
        }
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        RequestBuilder<Drawable> requestBuilder = Glide.with(mContext)
                .load(resId);
        requestBuilder
                .apply(options)
                .into(mImageView);
    }

    //加载指定大小
    public static void loadImageViewSize(Context mContext, String path, int width, int height, ImageView mImageView) {
        if (TextUtils.isEmpty(path) || null == mImageView) {
            return;
        }
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .override(width, height)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        RequestBuilder<Drawable> requestBuilder = Glide.with(mContext)
                .load(imgUrl + path);
        requestBuilder.thumbnail(0.1f)
                .apply(options)
                .into(mImageView);
        //  Glide.with(mContext).load(path).override(width, height).into(mImageView);
    }

    //加载指定大小 uri
    public static void loadImageViewSize(Context mContext, Uri path, int width, int height, ImageView mImageView) {
        if (TextUtils.isEmpty(path.getPath()) || null == mImageView) {
            return;
        }
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .override(width, height)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        RequestBuilder<Drawable> requestBuilder = Glide.with(mContext)
                .load(imgUrl + path);
        requestBuilder.thumbnail(0.1f)
                .apply(options)
                .into(mImageView);
        //  Glide.with(mContext).load(path).override(width, height).into(mImageView);
    }

    //加载指定大小 资源文件
    public static void loadImageViewSize(Context mContext, int path, int width, int height, ImageView mImageView) {

        RequestOptions options = new RequestOptions()
                .fitCenter()
                .override(width, height)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        RequestBuilder<Drawable> requestBuilder = Glide.with(mContext)
                .load(imgUrl + path);
        requestBuilder.thumbnail(0.1f)
                .apply(options)
                .into(mImageView);
        //  Glide.with(mContext).load(path).override(width, height).into(mImageView);
    }

    /**
     * 加载网络图片，切成圆形
     *
     * @param mContext
     * @param url
     * @param iv
     */
    public static void loadRoundImg(final Context mContext, String url, final ImageView iv) {

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .circleCrop();
        RequestBuilder<Drawable> requestBuilder = Glide.with(mContext)
                .load(url);
        requestBuilder
                .apply(options)
                .into(iv);
    }


    /**
     * 设置圆角图片
     *
     * @param mContext
     * @param url
     * @param iv
     * @param radius
     * @param width
     * @param height
     */
    public static void loadRoundedCornerImg(final Context mContext, String url, int placeholder, final ImageView iv, int radius, int width, int height) {
        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(radius);
//通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(width, height).placeholder(placeholder);

        Glide.with(mContext).load(imgUrl + url).apply(options).into(iv);

    }

    public static void loadRoundImg(final Context mContext, String url, int placeHolderId, final ImageView iv) {

        RequestOptions options = new RequestOptions()
                .placeholder(placeHolderId)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .circleCrop();
        RequestBuilder<Drawable> requestBuilder = Glide.with(mContext)
                .load(url);
        requestBuilder
                .apply(options)
                .into(iv);
    }

    //加载指定大小 uri和加载失败图片
    public static void loadImageViewSize(Context mContext, Uri path, int width, int height, int errorImageView, ImageView mImageView) {
        if (TextUtils.isEmpty(path.getPath()) || null == mImageView) {
            return;
        }
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .override(width, height)
                .error(errorImageView)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        RequestBuilder<Drawable> requestBuilder = Glide.with(mContext)
                .load(imgUrl + path);
        requestBuilder.thumbnail(0.1f)
                .apply(options)
                .into(mImageView);
        // Glide.with(mContext).load(path).override(width, height).error(errorImageView).into(mImageView);
    }


    //加载指定大小  byte[] bytes和加载失败图片
    public static void loadImageViewSize(Context mContext, byte[] bytes, int width, int height, int errorImageView, ImageView mImageView) {
        if (bytes == null || null == mImageView) {
            return;
        }
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .override(width, height)
                .error(errorImageView)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        RequestBuilder<Drawable> requestBuilder = Glide.with(mContext)
                .load(bytes);
        requestBuilder.thumbnail(0.1f)
                .apply(options)
                .into(mImageView);
        // Glide.with(mContext).load(path).override(width, height).error(errorImageView).into(mImageView);
    }

    //设置加载中以及加载失败图片
    public static void loadImageViewLoding(Context mContext, String path, ImageView mImageView, int lodingImage, int errorImageView) {
        if (TextUtils.isEmpty(path) || null == mImageView) {
            return;
        }
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .placeholder(lodingImage)
                .error(errorImageView)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        RequestBuilder<Drawable> requestBuilder = Glide.with(mContext)
                .load(imgUrl + path);
        requestBuilder.thumbnail(0.1f)
                .apply(options)
                .into(mImageView);
        //Glide.with(mContext).load(path).placeholder(lodingImage).error(errorImageView).into(mImageView);
    }

    //设置加载失败图片
    public static void loadImageViewLoding(Context mContext, String path, ImageView mImageView, int errorImageView) {
        if (TextUtils.isEmpty(path) || null == mImageView) {
            return;
        }
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .error(errorImageView)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        RequestBuilder<Drawable> requestBuilder = Glide.with(mContext)
                .load(imgUrl + path);
        requestBuilder.thumbnail(0.1f)
                .apply(options)
                .into(mImageView);
        //  Glide.with(mContext).load(path).error(errorImageView).into(mImageView);
    }

    //设置加载中以及加载失败图片并且指定大小
    public static void loadImageViewLodingSize(Context mContext, String path, int width, int height, ImageView mImageView, int lodingImage, int errorImageView) {


        // Glide.with(mContext).load(path).override(width, height).placeholder(lodingImage).error(errorImageView).into(mImageView);
    }

    //设置跳过内存缓存
    public static void loadImageViewCache(Context mContext, String path, ImageView mImageView) {
        //  Glide.with(mContext).load(path).skipMemoryCache(true).into(mImageView);
    }

    //设置下载优先级
    public static void loadImageViewPriority(Context mContext, String path, ImageView mImageView) {
        //  Glide.with(mContext).load(path).priority(Priority.NORMAL).into(mImageView);
    }

    /**
     * 策略解说：
     * <p>
     * all:缓存源资源和转换后的资源
     * <p>
     * none:不作任何磁盘缓存
     * <p>
     * source:缓存源资源
     * <p>
     * result：缓存转换后的资源
     */

    //设置缓存策略
    public static void loadImageViewDiskCache(Context mContext, String path, ImageView mImageView) {
        //   Glide.with(mContext).load(path).diskCacheStrategy(DiskCacheStrategy.ALL).into(mImageView);
    }

    /**
     * api也提供了几个常用的动画：比如crossFade()
     */

    //设置加载动画
    public static void loadImageViewAnim(Context mContext, String path, int anim, ImageView mImageView) {
        //   Glide.with(mContext).load(path).animate(anim).into(mImageView);
    }

    /**
     * 会先加载缩略图
     */

    //设置缩略图支持
    public static void loadImageViewThumbnail(Context mContext, String path, ImageView mImageView) {
        //   Glide.with(mContext).load(path).thumbnail(0.1f).into(mImageView);
    }

    /**
     * api提供了比如：centerCrop()、fitCenter()等
     */

    //设置动态转换
    public static void loadImageViewCrop(Context mContext, String path, ImageView mImageView) {
        //   Glide.with(mContext).load(path).centerCrop().into(mImageView);
    }

    //设置动态GIF加载方式
    public static void loadImageViewDynamicGif(Context mContext, String path, ImageView mImageView) {
        //  Glide.with(mContext).load(path).asGif().into(mImageView);
    }

    //设置静态GIF加载方式
    public static void loadImageViewStaticGif(Context mContext, String path, ImageView mImageView) {
        //   Glide.with(mContext).load(path).asBitmap().into(mImageView);
    }

    //设置监听的用处 可以用于监控请求发生错误来源，以及图片来源 是内存还是磁盘

  /*  //设置监听请求接口
    public static void loadImageViewListener(Context mContext, String path, ImageView mImageView, RequestListener<String, GlideDrawable> requstlistener) {
      //  Glide.with(mContext).load(path).listener(requstlistener).into(mImageView);
    }*/

    //项目中有很多需要先下载图片然后再做一些合成的功能，比如项目中出现的图文混排

    //设置要加载的内容
   /* public static void loadImageViewContent(Context mContext, String path, SimpleTarget<GlideDrawable> simpleTarget) {
     //   Glide.with(mContext).load(path).centerCrop().into(simpleTarget);
    }*/

    //清理磁盘缓存
    public static void GuideClearDiskCache(Context mContext) {
        //理磁盘缓存 需要在子线程中执行
        Glide.get(mContext).clearDiskCache();
    }

    //清理内存缓存
    public static void GuideClearMemory(Context mContext) {
        //清理内存缓存  可以在UI主线程中进行
        Glide.get(mContext).clearMemory();

    }

}
