package id.idn.fahru.covid19info.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.idn.fahru.covid19info.databinding.ListCountryBinding
import id.idn.fahru.covid19info.pojo.CountriesItem

/**
 * Created by Imam Fahrur Rofi on 29/07/2020.
 */
class ListCountryAdapter : RecyclerView.Adapter<ListCountryVH>() {
    // buat variabel list/arraylist untuk menyimpan data di dalam adapter
    private val dataCountry = mutableListOf<CountriesItem>()

    // buat fungsi addData agar kelas lain bisa mengisi data kedalam recyclerview adapter
    fun addData(listCountry: List<CountriesItem>) {
        // bersihkan data lama jika ada menggunakan clear()
        dataCountry.clear()
        // tambahkan data set baru menggunakan addAll
        dataCountry.addAll(listCountry)
        // beritahu RecyclerView Adapter karena ada perubahan Data Set
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCountryVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListCountryBinding.inflate(inflater, parent, false)
        return ListCountryVH(binding)
    }

    override fun getItemCount(): Int {
        // ukuran dari dataCountry
        return dataCountry.size
    }

    override fun onBindViewHolder(holder: ListCountryVH, position: Int) {
        // memilih data sesuai posisi item recyclerview
        val data = dataCountry[position]
        // data tersebut ditempelkan ke dalam view menggunakan holder / ViewHolder
        holder.bind(data)
    }
}
