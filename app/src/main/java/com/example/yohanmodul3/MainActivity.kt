package com.example.yohanmodul3

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.yohanmodul3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Mendefinisikan konstanta untuk request code permission lokasi
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    // Mendefinisikan variabel binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout activity_main.xml dan menginisialisasi binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Set content view dengan root view dari binding
        setContentView(binding.root)

        binding.btnExplicit.setOnClickListener {
            val intent = Intent(this, Bahanbakaryohan::class.java)
            startActivity(intent)
        }


        // Set event handling ketika btnImplicit diklik
        binding.btnImplicit.setOnClickListener {

            // Memeriksa apakah GPS sudah diaktifkan pada perangkat
            val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }

            // Memeriksa apakah izin lokasi sudah diberikan
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Jika belum diberikan, minta izin lokasi dari pengguna
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE
                )
            } else {
                // Jika sudah diberikan, buka aplikasi Google Maps dengan koordinat restoran terdekat
                openGoogleMaps()
            }
        }
    }

    // Override onRequestPermissionsResult untuk menangani respons dari permintaan izin lokasi
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            // Memeriksa apakah izin diberikan atau tidak
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Izin diberikan, buka aplikasi Google Maps dengan koordinat wisata terdekat
                openGoogleMaps()
            } else {
                // Izin tidak diberikan, tampilkan pesan atau tindakan lain yang sesuai
                // Contoh: Menampilkan pesan Toast yang memberitahu bahwa izin lokasi tidak diberikan
                Toast.makeText(this, "Izin lokasi tidak diberikan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Fungsi untuk membuka aplikasi Google Maps dengan koordinat restoran terdekat
    private fun openGoogleMaps() {
        // Membuat URI yang berisi koordinat dari suatu lokasi (dalam hal ini "makananenak")
        val uri = Uri.parse("geo:0,0?q=wisata+terdekat")

        // Membuat intent untuk membuka aplikasi Google Maps dan menambahkan URI sebagai data yang akan ditampilkan
        val mapIntent = Intent(Intent.ACTION_VIEW, uri)

        // Menetapkan package name untuk aplikasi Google Maps agar intent hanya membuka aplikasi tersebut
        mapIntent.setPackage("com.google.android.apps.maps")

        // Memeriksa apakah aplikasi Google Maps sudah terinstal pada perangkat
        // Jika sudah terinstal, maka menjalankan intent untuk membuka aplikasi tersebut
        // Jika belum terinstal, maka menampilkan pesan Toast yang memberitahu bahwa aplikasi Google Maps tidak terinstal
        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent)
        } else {
            Toast.makeText(this, "Aplikasi Google Maps tidak terinstall", Toast.LENGTH_SHORT).show()
        }
    }
}