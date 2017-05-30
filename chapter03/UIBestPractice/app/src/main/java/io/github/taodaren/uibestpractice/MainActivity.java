package io.github.taodaren.uibestpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Msg> msgList = new ArrayList<>();
    private RecyclerView rvMsg;
    private MsgAdapter adapter;
    private EditText editInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化消息数据
        initDataMsg();
        initView();
        setClickListener();
    }

    private void setClickListener() {
        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editInput.getText().toString();
                if (!"".equals(input)) {
                    Msg msg = new Msg(input, Msg.TYPE_SENT);
                    msgList.add(msg);
                    //当有新消息时，刷新 RecyclerView 中的显示
                    adapter.notifyItemChanged(msgList.size() - 1);
                    //将 RecyclerView 定位到最后一行
                    rvMsg.scrollToPosition(msgList.size() - 1);
                    //清除输入框中的内容
                    editInput.setText("");
                }
            }
        });
    }

    private void initView() {
        editInput = (EditText) findViewById(R.id.edit_input);
        rvMsg = (RecyclerView) findViewById(R.id.msg_recycler_view);
        adapter = new MsgAdapter(msgList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvMsg.setLayoutManager(layoutManager);
        rvMsg.setAdapter(adapter);
    }

    private void initDataMsg() {
        Msg msg1 = new Msg("Hello guy.", Msg.TYPE_RECEIVED);
        Msg msg2 = new Msg("Hello Who is that?", Msg.TYPE_SENT);
        Msg msg3 = new Msg("This is Tom. Nice talking to you.", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        msgList.add(msg2);
        msgList.add(msg3);
    }
}
