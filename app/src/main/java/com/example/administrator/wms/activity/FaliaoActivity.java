package com.example.administrator.wms.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.wms.R;
import com.example.administrator.wms.adapter.FaliaoApater;
import com.example.administrator.wms.entity.Faliao;

import java.util.ArrayList;
import java.util.List;

public class FaliaoActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tv_gongji;
    private ListView lv_fl;
    private FaliaoApater adapter;
    private List<Faliao> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faliao);
        setTool();
        setViews();
        setListeners();
    }

    protected void setTool(){
        toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        toolbar.setTitle(R.string.flkd);

        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(android.R.drawable.ic_menu_revert);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_sm:

                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);//加载menu文件到布局
        return true;
    }


    protected void setViews(){
        tv_gongji = (TextView) findViewById(R.id.tv_gongji);
        lv_fl = (ListView) findViewById(R.id.lv_fl);
        Faliao fl = new Faliao();
        fl.setFfaliaono("qweyqgdsdgajksgdj");
        fl.setFgongdanno("ajsdhasjkdhjkasdh");
        fl.setFnote("空间哈尽快的哈数据库的哈");
        list.add(fl);
        adapter = new FaliaoApater(this,list);
        lv_fl.setAdapter(adapter);
    }

    protected void setListeners(){
        lv_fl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent());
            }
        });
    }
}
