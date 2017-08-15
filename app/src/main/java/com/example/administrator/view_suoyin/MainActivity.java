package com.example.administrator.view_suoyin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1.绘制A-Z的字母列表(自绘式自定义控件)
 * 快速索引栏
 * 1.继承View,覆写构造方法,初始化画笔
 * 2.在OnDrawer方法里绘制字符
 * 3.在OnMesure方法里测量高度
 * 4.在OnTouchEvent事件知道用户具体按住了哪个字母
 * 5.定义抽象方法,实现监听回调
 * 2.响应触摸事件
 * 3.提供监听回调
 * 4.获取汉字的拼音,首字母  (pinyin4j  通过汉字得到他的拼音只能一个字符一个字符的去转换成拼音)
 * 5.根据拼音排序
 * 6.根据首字母分组
 * 7.把监听回调和ListView结合起来
 */
public class MainActivity extends AppCompatActivity implements MyQuickIndex.OntouchListener, View.OnClickListener {
    private MyQuickIndex mQuickIndex;
    private int lastIndex = -1;
    public static final String[] NAMES = new String[]{"宋江", "卢俊义", "吴用",
            "公孙胜", "关胜", "林冲", "秦明", "呼延灼", "花荣", "柴进", "李应", "朱仝", "鲁智深",
            "武松", "董平", "张清", "杨志", "徐宁", "索超", "戴宗", "刘唐", "李逵", "史进", "穆弘",
            "雷横", "李俊", "阮小二", "张横", "阮小五", " 张顺", "阮小七", "杨雄", "石秀", "解珍",
            " 解宝", "燕青", "朱武", "黄信", "孙立", "宣赞", "郝思文", "韩滔", "彭玘", "单廷珪",
            "魏定国", "萧让", "裴宣", "欧鹏", "邓飞", " 燕顺", "杨林", "凌振", "蒋敬", "吕方",
            "郭 盛", "安道全", "皇甫端", "王英", "扈三娘", "鲍旭", "樊瑞", "孔明", "孔亮", "项充",
            "李衮", "金大坚", "马麟", "童威", "童猛", "孟康", "侯健", "陈达", "杨春", "郑天寿",
            "陶宗旺", "宋清", "乐和", "龚旺", "丁得孙", "穆春", "曹正", "宋万", "杜迁", "薛永", "施恩",
            "周通", "李忠", "杜兴", "汤隆", "邹渊", "邹润", "朱富", "朱贵", "蔡福", "蔡庆", "李立",
            "李云", "焦挺", "石勇", "孙新", "顾大嫂", "张青", "孙二娘", " 王定六", "郁保四", "白胜",
            "时迁", "段景柱", "小凡哥"};
    private List<Bean> persons;
    private ListView mLv;
    private RelativeLayout mActivityMain;

    /**
     * View decorView = getWindow().getDecorView();
     * int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
     * decorView.setSystemUiVisibility(option);
     * ActionBar actionBar = getSupportActionBar();
     * actionBar.hide();
     * 隐藏状态栏
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        mQuickIndex.setmOntouchListener(this);
        //准备数据
        persons = new ArrayList<>();
        //排序
        findAndSortData(persons);
        //适配数据
        MyAdapter myAdapter = new MyAdapter(persons, this);
        mLv.setAdapter(myAdapter);
    }

    private void findAndSortData(List<Bean> persons) {
        for (int i = 0; i < NAMES.length; i++) {
            String name = NAMES[i];
            Bean bean = new Bean(name);
            persons.add(bean);
        }
        Collections.sort(persons);

    }

    private void initView() {
        mQuickIndex = (MyQuickIndex) findViewById(R.id.quickIndex);
        mLv = (ListView) findViewById(R.id.lv);
        mQuickIndex.setOnClickListener(this);
        mActivityMain = (RelativeLayout) findViewById(R.id.activity_main);
    }

    @Override
    public void onTouchEvent(MyQuickIndex myQuickIndex, MotionEvent event, float spaceHeight, String[] LETTERS) {
        switch (event.getAction()) {
            //计算用户按到哪个 字母
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //得到当前的Y
                float y = event.getY();
                //得到是第几个indxt
                int currentIndex = (int) (y / spaceHeight);
                //判断不是点击同一个字母
                if (currentIndex != lastIndex) {
                    if (currentIndex >= 0 && currentIndex < LETTERS.length) {
                        String letter = LETTERS[currentIndex];
                        Util_Toast.showToast(MainActivity.this, letter);

                        for (int i = 0; i < persons.size(); i++) {
                            String s = persons.get(i).getPinyin().charAt(0) + "";
                            if(s.equals(letter)){
                                mLv.setSelection(i);
                                break;
                            }
                        }



                        //重新赋值
                        lastIndex = currentIndex;

                    }
                }
                break;
            default:
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.quickIndex:
                break;
        }
    }
}
