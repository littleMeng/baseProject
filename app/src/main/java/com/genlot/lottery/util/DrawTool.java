package com.genlot.lottery.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

/**
 * Date 2018/4/24
 * Author littlemeng
 * Email yikai.shao@genlot.com
 * Describe 画图工具
 */

public class DrawTool {
    /**
     * @param canvas
     * @param text
     * @param startX
     * @param startY
     * @param width
     * @param height
     * @param paint
     */
    public static void drawTextAlignCenter(Canvas canvas, String text, float startX, float startY, float width, float height, Paint paint) {
        paint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        float textHeight = fontMetrics.bottom + fontMetrics.top;
        float baseline = (startY + height + startY - textHeight) / 2;
        canvas.drawText(text, startX + width / 2, baseline, paint);
    }

    /**
     * 设置适配边框的文字尺寸
     *
     * @param paint
     * @param text       要显示的text
     * @param width      text显示区域的width
     * @param height     text显示区域的height
     * @param sizeReduce 减小字号以满足需求
     * @return
     */
    public static int setTextSizeFitChart(Paint paint, String text, float width, float height, int sizeReduce) {
        if (text == null) {
            text = "彩";
        }
        int textSize = 1;
        float textWidth = 0;
        for (int i = 1; i < 100; i++) {
            paint.setTextSize(i);
            textWidth = paint.measureText(text);
            if (height - i <= 0 || width - textWidth <= 0) {
                textSize = i;
                break;
            }
        }
        if (sizeReduce > 0 && textSize > sizeReduce) {
            textSize -= sizeReduce;
            paint.setTextSize(textSize);
        }
        return textSize;
    }

    public static void setTextSize(float offsetX, float offsetY, Paint paint, String str) {
        if (str.length() <= 1) {
            DrawTool.setTextSizeFitChart(paint, "10", offsetX, offsetY, 0);
        }
        if (str.length() <= 2) {
            DrawTool.setTextSizeFitChart(paint, "100", offsetX, offsetY, 0);
        }
        if (str.length() > 2) {
            DrawTool.setTextSizeFitChart(paint, "1000", offsetX, offsetY, 0);
        }
        if (str.length() > 3) {
            DrawTool.setTextSizeFitChart(paint, "1000", offsetX, offsetY, 0);
        }
        if (str.length() > 3) {
            DrawTool.setTextSizeFitChart(paint, "10000", offsetX, offsetY, 0);
        }
        if (str.length() > 4) {
            DrawTool.setTextSizeFitChart(paint, "100000", offsetX, offsetY, 0);
        }
    }

    public static Bitmap readBitmap(Context context, int id) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.ARGB_8888;
        opt.inPurgeable = true;

        InputStream is = context.getResources().openRawResource(id);
        Bitmap bitmap = BitmapFactory.decodeStream(is, null, opt);
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    public static int drawTextAlignCenter2(Canvas canvas, String text, int startX, int startY, int width, int height, Paint paint)
    {
        paint.setTextAlign(Paint.Align.LEFT);
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        float textHeight = fontMetrics.bottom + fontMetrics.top;
        float baseline = (startY + height + startY - textHeight) / 2;
        float textWidth = paint.measureText(text);
        canvas.drawText(text, startX + (width - textWidth) / 2, baseline, paint);
        return (int) (startX + (width - textWidth) / 2 + textWidth);
    }
    public static Bitmap readBitmap2(Context context, int id) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.ARGB_8888;
        opt.inPurgeable = true;
        opt.inJustDecodeBounds = true;
        InputStream is = context.getResources().openRawResource(id);
        Bitmap bitmap = BitmapFactory.decodeStream(is, null, opt);
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    /**
     * 计算出来的位置，y方向就在anchorView的上面和下面对齐显示，x方向就是与屏幕右边对齐显示
     * 如果anchorView的位置有变化，就可以适当自己额外加入偏移来修正
     *
     * @param anchorView  呼出window的view
     * @param contentView window的内容布局
     * @return window显示的左上角的xOff, yOff坐标
     */
    public static int[] calculatePopWindowPos(final View anchorView, final View contentView) {
        final int windowPos[] = new int[2];
        final int anchorLoc[] = new int[2];
        // 获取锚点View在屏幕上的左上角坐标位置
        anchorView.getLocationOnScreen(anchorLoc);
        final int anchorHeight = anchorView.getHeight();
        // 获取屏幕的高宽
        final int screenHeight = getScreenHeight(anchorView.getContext());
        final int screenWidth = getScreenWidth(anchorView.getContext());
        // 测量contentView
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        // 计算contentView的高宽
        final int windowHeight = contentView.getMeasuredHeight();
        final int windowWidth = contentView.getMeasuredWidth();
        // 判断需要向上弹出还是向下弹出显示
        final boolean isNeedShowUp = (screenHeight - anchorLoc[1] - anchorHeight < windowHeight);
        if (isNeedShowUp) {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] - windowHeight;
        } else {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] + anchorHeight;
        }
        return windowPos;
    }

    /**
     * 获取屏幕高度(px)
     */
    private static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕宽度(px)
     */
    private static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
