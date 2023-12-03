package com.example.yohanmodul3


import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.yohanmodul3.databinding.ActivityBahanbakaryohanBinding

class Bahanbakaryohan : AppCompatActivity() {


    private lateinit var distanceEditText: EditText
    private lateinit var fuelPriceEditText: EditText
    private lateinit var timeEditText: EditText
    private lateinit var fuelEfficiencyEditText: EditText
    private lateinit var calculateButton: Button
    private lateinit var resultTextView: TextView

    private lateinit var binding: ActivityBahanbakaryohanBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBahanbakaryohanBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // Inisialisasi komponen UI
        distanceEditText = findViewById(R.id.distanceEditText)
        fuelPriceEditText = findViewById(R.id.fuelPriceEditText)
        timeEditText = findViewById(R.id.timeEditText)
        fuelEfficiencyEditText = findViewById(R.id.fuelEfficiencyEditText)
        calculateButton = findViewById(R.id.calculateButton)
        resultTextView = findViewById(R.id.resultTextView)

        // Penanganan klik tombol "Hitung"
        calculateButton.setOnClickListener {
            // Mendapatkan nilai input dari EditText
            val distance = distanceEditText.text.toString().toDoubleOrNull()
            val fuelPrice = fuelPriceEditText.text.toString().toDoubleOrNull()
            val time = timeEditText.text.toString().toDoubleOrNull()
            val fuelEfficiency = fuelEfficiencyEditText.text.toString().toDoubleOrNull()

            // Mengecek apakah nilai input valid atau tidak
            if (distance == null || fuelPrice == null || time == null || fuelEfficiency == null) {
                resultTextView.text = "Masukkan input yang valid"
            } else {
                // Melakukan perhitungan dan menampilkan hasil di TextView
                val kebutuhanbb = distance / fuelEfficiency
                val totalCost = kebutuhanbb * fuelPrice
                val averageSpeed = distance / time

                resultTextView.text = "Bahan Bakar yang Dibutuhkan: %.2f L\n".format(kebutuhanbb) +
                        "Total Biaya Bahan Bakar: Rp %,.2f\n".format(totalCost) +
                        "Kecepatan Rata-rata: %.2f km/jam".format(averageSpeed)
            }
        }
    }
}
