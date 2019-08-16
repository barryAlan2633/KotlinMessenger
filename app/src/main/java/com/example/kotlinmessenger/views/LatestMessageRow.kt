package com.example.kotlinmessenger.views

import com.example.kotlinmessenger.R
import com.example.kotlinmessenger.models.ChatMessage
import com.example.kotlinmessenger.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.latest_message_row.view.*

class LatestMessageRow(val chatMessage: ChatMessage): Item<ViewHolder>(){
    var charPartnerUser: User? = null

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.latestMessage_tv_latest_message.text = chatMessage.text

        val chatPartnerId: String
        if(chatMessage.fromId == FirebaseAuth.getInstance().uid){
            chatPartnerId = chatMessage.toId
        }else{
            chatPartnerId = chatMessage.fromId
        }
        val ref = FirebaseDatabase.getInstance().getReference("/users/$chatPartnerId")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                charPartnerUser = p0.getValue(User::class.java)
                viewHolder.itemView.username_tv_latest_message.text =  charPartnerUser?.username

                val targetImageView = viewHolder.itemView.iv_latest_message
                Picasso.get().load(charPartnerUser?.profileImageUrl).into(targetImageView)
            }

            override fun onCancelled(p0: DatabaseError) {
            }
        })


    }
    override fun getLayout(): Int {
        return R.layout.latest_message_row
    }
}