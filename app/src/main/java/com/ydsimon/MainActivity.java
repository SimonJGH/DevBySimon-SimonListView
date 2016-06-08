package com.ydsimon;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class MainActivity extends Activity implements SimonListView.SimonListViewListener {
    private SimonListView mListView;
    private ArrayAdapter<String> adapter;
    private Handler mHandler;
    private ArrayList<String> mList = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (SimonListView) findViewById(R.id.techan_xListView);
        getData();

        mListView.setPullRefreshEnable(true);
        mListView.setPullLoadEnable(true);
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, mList);
        mListView.setAdapter(adapter);
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                openItem.setBackground(new ColorDrawable(Color.RED));
                openItem.setWidth(dp2px(90));
                openItem.setTitle("删除");
                openItem.setTitleSize(18);
                openItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(openItem);

//                SwipeMenuItem deleteItem = new SwipeMenuItem(
//                        getApplicationContext());
//                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
//                        0x3F, 0x25)));
//                deleteItem.setWidth(dp2px(90));
//                deleteItem.setIcon(R.drawable.ic_delete);
//                menu.addMenuItem(deleteItem);
            }
        };
        mListView.setMenuCreator(creator);
        mListView.setOnMenuItemClickListener(new SimonListView.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // delete
                        Toast.makeText(getApplicationContext(), "DELE" + position, Toast.LENGTH_SHORT).show();
                        mList.remove(position);
                        adapter.notifyDataSetChanged();
                        mListView.setAdapter(adapter);

                        break;
                    case 1:
                        // open
                        Toast.makeText(getApplicationContext(), "OPEN" + position, Toast.LENGTH_SHORT).show();
                        mList.remove(position);
                        adapter.notifyDataSetChanged();
                        mListView.setAdapter(adapter);
                        break;
                }
            }
        });

        mListView.setOnSwipeListener(new SimonListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {

            }

            @Override
            public void onSwipeEnd(int position) {

            }
        });

        // 其它
		mListView.setCloseInterpolator(new BounceInterpolator());

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                Toast.makeText(getApplicationContext(), position + " long click", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        mListView.setXListViewListener(this);
        mHandler = new Handler();
        mListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(), "您点击了" + position, Toast.LENGTH_LONG).show();
            }
        });
    }


    private void getData() {
        for (int i = 0; i < 10; i++) {
            mList.add("第" + i + "条数据");
        }
    }

    /**
     * 停止刷新
     */
    private void onLoad() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.setRefreshTime("刚刚");
    }

    /**
     * 刷新数据
     */
    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
                mListView.setAdapter(adapter);
                onLoad();
            }
        }, 2000);
    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                getData();
                adapter.notifyDataSetChanged();
                onLoad();
            }
        }, 2000);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            this.finish();
        }
        return false;
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}