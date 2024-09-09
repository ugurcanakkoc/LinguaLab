package com.example.lingualab.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lingualab.R
import com.example.lingualab.data.model.Word

class WordListRecyclerViewAdapter(private val wordList: List<Word>) :
    RecyclerView.Adapter<WordListRecyclerViewAdapter.WordViewHolder>() {

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val trMeanTextView: TextView = itemView.findViewById(R.id.tr_mean)
        val engMeanTextView: TextView = itemView.findViewById(R.id.eng_mean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_word_item, parent, false)
        return WordViewHolder(view)
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = wordList[position]
        holder.trMeanTextView.text = word.tr
        holder.engMeanTextView.text = word.en
    }
}
