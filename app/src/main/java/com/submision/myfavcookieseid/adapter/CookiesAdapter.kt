package com.submision.myfavcookieseid.adapter

import android.graphics.text.LineBreaker
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.submision.myfavcookieseid.R
import com.submision.myfavcookieseid.data.Cookies

class CookiesAdapter(
    private val listCookies:ArrayList<Cookies>
):RecyclerView.Adapter<CookiesAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback:OnItemClickCallback

    inner class ListViewHolder(view: View):RecyclerView.ViewHolder(view){
        val img:ImageView = view.findViewById(R.id.img_item_photo)
        val title:TextView = view.findViewById(R.id.tv_item_name)
        val desc:TextView = view.findViewById(R.id.tv_item_desc)
        val score:RatingBar = view.findViewById(R.id.rating_cake)
        val txScore:TextView = view.findViewById(R.id.tv_score)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CookiesAdapter.ListViewHolder {
        val view:View = LayoutInflater
            .from(parent.context).inflate(R.layout.items,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CookiesAdapter.ListViewHolder, position: Int) {
        val hero = listCookies[position]

        Glide.with(holder.itemView.context)
            .load(hero.img)
            .into(holder.img)

        holder.title.text = hero.title
        holder.desc.text = "${hero.desc!!.take(50)} ..."
        holder.txScore.text = "Score Taste :"
        holder.score.rating = hero.score!!
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            holder.desc.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
        }

        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(listCookies[holder.adapterPosition])
        }

    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun getItemCount(): Int {
        return listCookies.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Cookies)
    }

}