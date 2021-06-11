package com.example.primeraapp;

import android.content.Context;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ActivityEjercicio extends AppCompatActivity {
    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;
   @Override
    protected void onCreate(Bundle saveInstanceState){
       super.onCreate(saveInstanceState);
       setContentView(R.layout.activity_ejercicio);
       mDatabase = FirebaseDatabase.getInstance().getReference().child("global");
       mDatabase.keepSynced(true);

       mBlogList=(RecyclerView)findViewById(R.id.myrecyclerview);
       mBlogList.setHasFixedSize(true);
       mBlogList.setLayoutManager(new LinearLayoutManager(this));
   }
   @Override
    protected void onStart(){
       super.onStart();
       FirebaseRecyclerAdapter<Blog,BlogViewHolder>firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<Blog, BlogViewHolder>
               (Blog.class, R.layout.blof_raw,BlogViewHolder.class,mDatabase) {
           @Override
           protected void populateViewHolder(BlogViewHolder blogViewHolder, Blog model, int position) {
               blogViewHolder.setColor(getApplicationContext(),model.getColor());
               blogViewHolder.setDesc(model.getDescripcion());
               blogViewHolder.setEjercicio(model.getEjercicio());
               blogViewHolder.setUrl(model.getUrl());

           }
       };
       mBlogList.setAdapter(firebaseRecyclerAdapter);
   }
   public static class BlogViewHolder extends RecyclerView.ViewHolder{
       View view;
       public BlogViewHolder(View itemView)
       {
           super(itemView);
           view=itemView;
       }
       public void setColor(Context ctx, String color){
           ImageView post_Image=(ImageView)view.findViewById(R.id.post_image);
           Picasso.get().load(color).into(post_Image);
       }
       public void setEjercicio(String ejercicio)
       {
           TextView post_title=(TextView)view.findViewById(R.id.post_title);
           post_title.setText(ejercicio);
       }
       public void setDesc(String descripcion)
       {
           TextView post_descripcion=(TextView)view.findViewById(R.id.post_descripcion);
           post_descripcion.setText(descripcion);
       }
       public void setUrl(String url)
       {
           TextView post_Url=(TextView)view.findViewById(R.id.post_url);
           post_Url.setText(url);
       }

   }
}
