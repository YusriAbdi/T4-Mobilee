package com.abdi.studentcenter

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.abdi.studentcenter.database.StudentEntity
import com.abdi.studentcenter.database.StudentViewModel

class FormActivity : AppCompatActivity() {

    private lateinit var viewModel: StudentViewModel
    private var studentId: Int = 0
    private var isEditMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        viewModel = ViewModelProvider(this)[StudentViewModel::class.java]

        // 1. Inisialisasi SEMUA View terlebih dahulu
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val etNama = findViewById<EditText>(R.id.etNama)
        val etNim = findViewById<EditText>(R.id.etNim)
        val etProdi = findViewById<EditText>(R.id.etProdi)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etSemester = findViewById<EditText>(R.id.etSemester)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)

        // 2. Cek apakah ini mode Edit atau Tambah Baru
        if (intent.hasExtra("EXTRA_ID")) {
            isEditMode = true
            studentId = intent.getIntExtra("EXTRA_ID", 0)

            // Isi data ke form
            etNama.setText(intent.getStringExtra("EXTRA_NAME"))
            etNim.setText(intent.getStringExtra("EXTRA_NIM"))
            etProdi.setText(intent.getStringExtra("EXTRA_PRODI"))
            etEmail.setText(intent.getStringExtra("EXTRA_EMAIL"))
            etSemester.setText(intent.getStringExtra("EXTRA_SEMESTER"))

            // Ubah teks UI
            tvTitle?.text = "Edit Data Mahasiswa"
            btnSimpan.text = "Update Data"
        } else {
            isEditMode = false
            tvTitle?.text = "Tambah Data Mahasiswa"
            btnSimpan.text = "Simpan"
        }

        // 3. Set Klik Listener
        btnSimpan.setOnClickListener {
            val nama = etNama.text.toString().trim()
            val nim = etNim.text.toString().trim()
            val prodi = etProdi.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val semester = etSemester.text.toString().trim()

            if (nama.isEmpty() || nim.isEmpty() || prodi.isEmpty() || email.isEmpty() || semester.isEmpty()) {
                Toast.makeText(this, "Harap isi semua kolom!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val student = StudentEntity(
                id = if (isEditMode) studentId else 0,
                name = nama,
                nim = nim,
                prodi = prodi,
                email = email,
                semester = semester
            )

            if (isEditMode) {
                viewModel.update(student)
                Toast.makeText(this, "Data diperbarui", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.insert(student)
                Toast.makeText(this, "Data ditambahkan", Toast.LENGTH_SHORT).show()
            }

            finish() // Kembali ke MainActivity
        }
    }
}