package com.example.androidlabs;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidlab.R;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomActivity extends AppCompatActivity {

    private static final String TAG = ChatRoomActivity.class.getSimpleName();
    ListView lv;
    EditText et;
    List<Message> msgList = new ArrayList<>();
    Button btnSend;
    Button btnReceive;

    // DATABASE CLASS REFERENCE OBJECT
    DatabaseClass dc = new DatabaseClass(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lv = (ListView)findViewById(R.id.lstView);
        et = (EditText)findViewById(R.id.chatEditText);
        btnSend = (Button)findViewById(R.id.SendBtn);
        btnReceive = (Button)findViewById(R.id.ReceiveBtn);

        dc.viewData();
        dc.printCursor();



        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    String msg = et.getText().toString();
                    if (!msg.equals("")) {
                        Message model = new Message(msg,true, false);
                        dc.insertSendMessage(msg);
                        msgList.add(model);
                        ChatAdapter adt = new ChatAdapter(msgList, getApplicationContext());
                        lv.setAdapter(adt);
                        et.setText("");

                    }

                }

        });

        btnReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String msg = et.getText().toString();
                if ( !msg.equals("")){
                    Message model = new Message(msg,false,true);
                    dc.insertReceiveMessage(msg);
                    msgList.add(model);

                    ChatAdapter adt = new ChatAdapter(msgList, getApplicationContext());
                    lv.setAdapter(adt);
                    et.setText("");


                }


            }
        });




    }



}

class ChatAdapter extends BaseAdapter{

    private List<Message> messageModels;
    private Context context;
    private LayoutInflater inflater;

    public ChatAdapter(List<Message> messageModels, Context context) {
        this.messageModels = messageModels;
        this.context = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return messageModels.size();
    }

    @Override
    public Object getItem(int position) {
        return messageModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null){
            if (messageModels.get(position).getisSent()){
                view = inflater.inflate(R.layout.activity_send, null);

            }else {
                view = inflater.inflate(R.layout.activity_receive, null);
            }
            TextView messageText = (TextView)view.findViewById(R.id.messageText);
            messageText.setText(messageModels.get(position).getMsg());
        }
        return view;
    }
}
