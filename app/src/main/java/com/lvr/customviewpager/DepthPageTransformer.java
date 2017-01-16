package com.lvr.customviewpager;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by lvr on 2017/1/16.
 */

public class DepthPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;
    @Override
    public void transformPage(View page, float position) {
        int pageWidth = page.getWidth();
        if (position < -1) { // [-Infinity,-1)
            // 页面远离左侧页面
            page.setAlpha(0);
        } else if (position <= 0) { // [-1,0]
            // 页面在由中间页滑动到左侧页面 或者 由左侧页面滑动到中间页
            page.setAlpha(1);
            page.setTranslationX(0);
            page.setScaleX(1);
            page.setScaleY(1);
        } else if (position <= 1) { // (0,1]
            //页面在由中间页滑动到右侧页面 或者 由右侧页面滑动到中间页
            // 淡出效果
            page.setAlpha(1 - position);
            // 反方向移动
            page.setTranslationX(pageWidth * -position);
            // 0.75-1比例之间缩放
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
        } else { // (1,+Infinity]
            // 页面远离右侧页面
            page.setAlpha(0);
        }

    }
}
