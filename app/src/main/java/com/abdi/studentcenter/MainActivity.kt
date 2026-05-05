package com.abdi.studentcenter

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abdi.studentcenter.database.StudentViewModel
import com.abdi.studentcenter.utils.PrefManager
import com.abdi.studentcenter.utils.StudentAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var prefManager: PrefManager
    private lateinit var viewModel: StudentViewModel
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefManager = PrefManager(this)
        viewModel = ViewModelProvider(this)[StudentViewModel::class.java]

        setupRecyclerView()

        viewModel.allStudents.observe(this) { list ->
            adapter.setData(list)
        }

        // Tampilkan nama user yang sedang login
        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        tvWelcome.text = "Selamat datang, ${prefManager.getUsername()}!"

        // Tombol logout — hapus session lalu kembali ke LoginActivity
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener {
            prefManager.logout()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = StudentAdapter(
            onEditClick = { student ->
                val intent = Intent(this, FormActivity::class.java)
                intent.putExtra("EXTRA_ID", student.id)
                intent.putExtra("EXTRA_NAME", student.name)
                intent.putExtra("EXTRA_NIM", student.nim)
                intent.putExtra("EXTRA_PRODI", student.prodi)
                intent.putExtra("EXTRA_EMAIL", student.email)
                intent.putExtra("EXTRA_SEMESTER", student.semester)
                startActivity(intent)
            },
            onDeleteClick = { student ->
                AlertDialog.Builder(this)
                    .setTitle("Hapus Data")
                    .setMessage("Yakin ingin menghapus ${student.name}?")
                    .setPositiveButton("Ya") { _, _ ->
                        viewModel.delete(student)
                    }
                    .setNegativeButton("Tidak", null)
                    .show()
            }
        )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}