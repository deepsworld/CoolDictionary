package edu.towson.cosc431.Patel.CoolDictionary

import android.support.v7.widget.RecyclerView
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.word_view.view.*


class WordAdapter(val wordList: List<WordDataClass>) :  RecyclerView.Adapter<WordViewHolder>(){

    lateinit var filterList: List<Word>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        //1. Inflate a view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.word_view, parent, false)

        //2. Create a ViewHolder
        val viewHolder =  WordViewHolder(view)
        return viewHolder
    }


    override fun getItemCount(): Int {
        return wordList.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        //1. get the task at the position
        val word_string = wordList.get(position)
        holder.itemView.word_card.text = word_string.word
    }


}

class WordViewHolder(view: View) : RecyclerView.ViewHolder(view)