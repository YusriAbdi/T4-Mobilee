package com.abdi.studentcenter.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abdi.studentcenter.R
import com.abdi.studentcenter.database.StudentEntity

class StudentAdapter(
    private val onEditClick: (StudentEntity) -> Unit,
    private val onDeleteClick: (StudentEntity) -> Unit
) : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    private var list = emptyList<StudentEntity>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNama: TextView = view.findViewById(R.id.tvNama)
        val tvNim: TextView = view.findViewById(R.id.tvNim)
        val btnEdit: Button = view.findViewById(R.id.btnEdit)
        val btnDelete: Button = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mahasiswa, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.tvNama.text = data.name
        holder.tvNim.text = data.nim

        holder.btnEdit.setOnClickListener { onEditClick(data) }
        holder.btnDelete.setOnClickListener { onDeleteClick(data) }
    }

    fun setData(newList: List<StudentEntity>) {
        this.list = newList
        notifyDataSetChanged()
    }
}