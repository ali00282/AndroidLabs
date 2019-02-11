package com.example.androidlabs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.androidlab.R;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomActivity extends AppCompatActivity {

    ListView lv;
    EditText et;
    List<Message> msgList = new ArrayList<>();
    Button btnSend;
    Button btnReceive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

     lv = (ListView)findViewById(R.id.lstView);
     et = (EditText)findViewById(R.id.chatEditText);
     btnSend = (Button)findViewById(R.id.SendBtn);
     btnReceive = (Button)findViewById(R.id.ReceiveBtn);

     btnSend.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             String sendMessage = et.getText().toString();
             Message msgStorage = new Message(sendMessage);
             msgList.add(msgStorage);
             ArrayAdapter<List> ad = new ArrayAdapter<List>(this,R.layout.activity_chat_room,msgList);
             lv.setAdapter(ad);
         }
     });


    }
}
