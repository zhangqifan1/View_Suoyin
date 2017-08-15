package com.example.administrator.view_suoyin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 1.绘制A-Z的字母列表(自绘式自定义控件)
 * 快速索引栏
 * 1.继承View,覆写构造方法,初始化画笔
 * 2.在OnDrawer方法里绘制字符
 * 3.在OnMesure方法里测量高度
 * 4.在OnTouchEvent事件知道用户具体按住了哪个字母
 * 5.定义抽象方法,实现监听回调
 * Created by 张祺钒
 * on2017/8/14.
 */

public class MyQuickIndex extends View {

    private Paint paint;
    private int measuredHeight;
    private float spaceHeight;
    private int measuredWidth;


    public MyQuickIndex(Context context) {
        this(context, null);
    }

    public MyQuickIndex(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyQuickIndex(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化画笔
        initPainter();
    }

    private void initPainter() {
        paint = new Paint();
        //设置风格
        paint.setStyle(Paint.Style.STROKE);
        //设置抗锯齿
        paint.setAntiAlias(true);
        //设置画笔加粗
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        //设置画笔颜色
        paint.setColor(Color.WHITE);
        paint.setTextSize(30);

    }

    //准备的数据
    //A.要绘制的内容
    private static final String[] LETTERS = new String[]{
            "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X",
            "Y", "Z"
    };

    @Override
    protected void onDraw(Canvas canvas) {

        //遍历字母数组，计算坐标，进行绘制
        for (int i = 0; i < LETTERS.length; i++) {
            String letter = LETTERS[i];
            //计算X轴坐标
            float x = measuredWidth * 0.5f - paint.measureText(letter) * 0.5f;
            float y = spaceHeight * 0.5f + paint.measureText(letter) * 0.5f+i*spaceHeight;
            canvas.drawText(letter, x,y, paint);
        }

    }
    //获取控件的宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //得到控件宽度  高度
        measuredWidth = getMeasuredWidth();
        measuredHeight = getMeasuredHeight();//既是控件宽度也是单元格宽度
        //单元格的高度
        spaceHeight = measuredHeight *1.0f / LETTERS.length;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if(mOntouchListener!=null){
            mOntouchListener.onTouchEvent(this,event,spaceHeight,LETTERS);
        }
        return true;
    }
    private OntouchListener mOntouchListener;
    public interface OntouchListener{
        void onTouchEvent(MyQuickIndex myQuickIndex,MotionEvent event,float spaceHeight,final String[] LETTERS);
    }

    public void setmOntouchListener(OntouchListener mOntouchListener) {
        this.mOntouchListener = mOntouchListener;
    }
}
