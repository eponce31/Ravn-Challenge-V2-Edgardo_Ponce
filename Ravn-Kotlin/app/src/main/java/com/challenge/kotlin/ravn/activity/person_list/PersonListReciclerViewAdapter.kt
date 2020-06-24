package com.challenge.kotlin.ravn.activity.person_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.challenge.kotlin.ravn.GetAllPersonsQuery.AllPerson
import com.challenge.kotlin.ravn.GraphQLUtil
import com.challenge.kotlin.ravn.MainApplication
import com.challenge.kotlin.ravn.R
import com.challenge.kotlin.ravn.activity.util.Navigator
import com.challenge.kotlin.ravn.activity.person_list.PersonListReciclerViewAdapter.PersonItemViewHolder

class PersonListReciclerViewAdapter internal constructor(private val navigator: Navigator) :
    RecyclerView.Adapter<PersonItemViewHolder>() {
    private var list: List<AllPerson> = emptyList()
    private val context: Context? = null
    fun setList(_list: List<AllPerson>) {
        list = _list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView =
            layoutInflater.inflate(R.layout.item_person, parent, false)

        return PersonItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PersonItemViewHolder, position: Int) {
        val item = list[position]
        holder.setItem(item, navigator)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class PersonItemViewHolder(itemView: View) :
        ViewHolder(itemView) {
        private val tvName: TextView
        private val tvDetail: TextView
        private val llContainer: View
        fun setItem(
            item: AllPerson,
            navigator: Navigator
        ) {
            tvName.text = item.name()

            tvDetail.text =
                GraphQLUtil.getPersonDescription(
                    item, " from "
                );

            llContainer.setOnClickListener { navigator.startActivity(item.id()) }
        }

        init {
            tvName = itemView.findViewById<View>(R.id.tv_name) as TextView
            tvDetail = itemView.findViewById<View>(R.id.tv_detail) as TextView
            llContainer = itemView.findViewById(R.id.ll_container)
        }
    }

}