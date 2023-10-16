package com.example.sqlite1.Adapter;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import  androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite1.DisplayContact;
import com.example.sqlite1.MainActivity;
import com.example.sqlite1.Model.Contact;
import com.example.sqlite1.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private Context context;
    private List<Contact> contactList;

    public RecyclerViewAdapter(Context context,List<Contact> contactList)
    {
        this.contactList=contactList;this.context=context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row1,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact=contactList.get(position);
        holder.contactName.setText(contact.getName());
        holder.contactNos.setText(contact.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView contactName;
        public TextView contactNos;
        public ImageView contactpic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            contactName=itemView.findViewById(R.id.Name);
            contactNos=itemView.findViewById(R.id.Phonenos);
            contactpic=itemView.findViewById(R.id.imageView);
            contactpic.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int position=this.getBindingAdapterPosition();
            Contact contact=contactList.get(position);
            String name=contact.getName();
            String number=contact.getPhoneNumber();
            Toast.makeText(context,"The Position is"+String.valueOf(position)+
                    "Name is: "+name+" Phone Number: "+number,Toast.LENGTH_SHORT).show();

            Intent intent=new Intent(context, DisplayContact.class);
            intent.putExtra("Rname",name);
            intent.putExtra("Rphone",number);
            context.startActivity(intent);
        }
    }

}
