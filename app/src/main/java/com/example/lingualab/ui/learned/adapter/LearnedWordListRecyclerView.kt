package com.example.lingualab.ui.learned.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lingualab.R
import com.example.lingualab.data.model.Word

class LearnedWordListRecyclerView(private val wordList: List<Word>,
                                  val onClickVHolder: (Word) -> Unit) :
    RecyclerView.Adapter<LearnedWordListRecyclerView.WordViewHolder>() {

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val trMeanTextView: TextView = itemView.findViewById(R.id.tr_mean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_word_item, parent, false)
        return WordViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = wordList[position]
        holder.trMeanTextView.text = word.tr

        holder.itemView.setOnClickListener {
            onClickVHolder(word)
        }
    }


    override fun getItemCount(): Int {
        return wordList.size
    }



}