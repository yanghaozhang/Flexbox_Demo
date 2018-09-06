package org.flexbox.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import org.flexbox.R;
import org.flexbox.adapter.DragAdapter;
import org.flexbox.helper.ItemTouchHelperCallBack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemTouchHelperCallBack.OnItemDragListener {

    private RecyclerView rv;
    private List<String> list;
    private DragAdapter adapter;
    private Button btnLog;
    private TextView tvLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
        initData();
        initAdapter();
    }

    private void initAdapter() {
        //屏蔽notifyItemChanged造成的动画
//        rv.setItemAnimator(null);

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        layoutManager.setFlexWrap(FlexWrap.WRAP);

        rv.setLayoutManager(layoutManager);

        adapter = new DragAdapter(this, list);
        rv.setAdapter(adapter);

        ItemTouchHelperCallBack itemDragHelperCallBack = new ItemTouchHelperCallBack();
        itemDragHelperCallBack.setOnItemDragListeber(this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragHelperCallBack);
        //关联RecyclerView
        itemTouchHelper.attachToRecyclerView(rv);
    }

    private void initData() {
        list = new ArrayList<>();
        list.add("1.Java");
        list.add("2.C++");
        list.add("3.Python");
        list.add("4.Visual Basic .NET");
        list.add("5.C#");
        list.add("6.JavaScript");
        list.add("7.Objective-C");
        list.add("8.Assembly language");
        list.add("9.Kotlin");
        list.add("10.Groovy");
    }

    private void initListener() {
        btnLog.setOnClickListener(v -> {
            StringBuilder builder = new StringBuilder();
            for (String s : list) {
                builder.append(s);
                builder.append("\n");
            }
            tvLog.setText(builder.toString());
        });

        tvLog.setOnClickListener(v -> tvLog.setText(""));
    }

    private void initView() {
        rv = findViewById(R.id.rv);
        btnLog = findViewById(R.id.btn_log);
        tvLog = findViewById(R.id.tv_log);
    }

    @Override
    public void onItemMove(int startPos, int endPos) {
        if (startPos < endPos) {
            for (int i = startPos; i < endPos; i++) {
                Collections.swap(list, i, i + 1);
            }
        } else {
            for (int i = startPos; i > endPos; i--) {
                Collections.swap(list, i, i - 1);
            }
        }
        adapter.notifyItemMoved(startPos, endPos);
    }
}
