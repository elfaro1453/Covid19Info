package id.idn.fahru.covid19info

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import id.idn.fahru.covid19info.adapter.ListCountryAdapter
import id.idn.fahru.covid19info.databinding.ActivityMainBinding
import id.idn.fahru.covid19info.pojo.CountriesItem
import id.idn.fahru.covid19info.retrofit.CovidInterface
import id.idn.fahru.covid19info.retrofit.RetrofitService
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // inflater dan inflate binding mesti ada
        val inflater = layoutInflater
        binding = ActivityMainBinding.inflate(inflater)

        // ganti setContentView dengan binding.root
        setContentView(binding.root)

        // definisikan recyclerview adapter
        val rvAdapter = ListCountryAdapter()

        // setting recyclerview
        binding.rvCountry.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            // gunakan recyclerview adapter yang telah didefinisikan sebelumnya
            adapter = rvAdapter
        }

        // buat lifecyclescope untuk mengakses retrofit
        lifecycleScope.launch {
            // definisikan retrofit service berdasarkan interface yang dituju
            val retrofit = RetrofitService.buildService(CovidInterface::class.java)
            // definisikan variabel summary (sesuaikan aja namanya)
            val summary = retrofit.getSummary()
            if (summary.isSuccessful) { // jika berhasil
                // buat variabel dataCountry yang berisi list countries dari API
                val dataCountry = summary.body()?.countries as List<CountriesItem>
                // tambahkan ke dalam rvAdapter
                rvAdapter.addData(dataCountry)
            } else {
                Log.e("RetrofitFailed", summary.errorBody().toString())
            }
        }
    }
}