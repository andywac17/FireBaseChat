package com.example.wearecoders.firebasechat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText editMessage;
    private DatabaseReference mDatabase;
    private RecyclerView mMessageList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editMessage=findViewById(R.id.edit_messageE);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Messages");
        mMessageList=findViewById(R.id.messageRec);
        mMessageList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        mMessageList.setLayoutManager(linearLayoutManager);

    }

    public void sendButtonClicked(View view) {

        final String messageValue=editMessage.getText().toString().trim();
        if(!TextUtils.isEmpty(messageValue)){
            final DatabaseReference newPost=mDatabase.push();
            newPost.child("content").setValue(messageValue);

        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<MessageBean,MessageViewHolder> FBRA=new FirebaseRecyclerAdapter<MessageBean, MessageViewHolder>(
                MessageBean.class,
                R.layout.single_row_message,
                MessageViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(MessageViewHolder viewHolder, MessageBean model, int position) {
                  viewHolder.setContent(model.getContent());
            }
        };
        mMessageList.setAdapter(FBRA);
    }

public static class MessageViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public MessageViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }

     public void setContent(String content){
      TextView message_content=(TextView)mView.findViewById(R.id.message_text);
     message_content.setText(content);
     }
}

}
