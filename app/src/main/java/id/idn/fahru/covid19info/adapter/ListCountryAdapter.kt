package id.idn.fahru.covid19info.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import id.idn.fahru.covid19info.databinding.ListCountryBinding
import id.idn.fahru.covid19info.pojo.CountriesItem

/**
 * Created by Imam Fahrur Rofi on 29/07/2020.
 */
class ListCountryAdapter : RecyclerView.Adapter<ListCountryVH>(), Filterable {
    // buat variabel list/arraylist untuk menyimpan data di dalam adapter
    private val dataCountry = mutableListOf<CountriesItem>()
    private var dataFiltered = mutableListOf<CountriesItem>()

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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults? {
                val filterResult = FilterResults()
                // mengisi dataFiltered dengan semua data yang ada di dataCountry jika tidak ada keyword
                if (constraint.isNullOrEmpty()) {
                    // jadikan dataCountry sebagai isi dari dataFiltered
                    filterResult.values = dataCountry
                } else {  // Jika ada keyword, maka lakukan perulangan untuk menyaring data berdasarkan keyword
                    // pertama buat variabel list kosong sementara
                    val resultFilter = mutableListOf<CountriesItem>()
                    // lakukan perulangan data pada dataCountry untuk mencari data berisi keyword
                    dataCountry.forEach { data ->
                        val countryName = data.country
                        // val keyword = constraint
                        countryName?.let { country ->  // jika countryName tidak null maka berinama country
                            // jika nama negara berisi keyword (setel true agar huruf kecil besar tidak berpengaruh)
                            if (country.contains(constraint, true)) {
                                // tambahkan data ke dalam resultFilter
                                resultFilter.add(data)
                            }
                        }
                    }
                    // jadikan resultFilter sebagai isi dari dataFiltered
                    filterResult.values = resultFilter
                }
                return filterResult
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                results?.let { data ->          // jika variabel results tidak null, maka buat variabel baru "data" berisi results
                    if (data is List<*>) { // jika data tersebut berbentuk list
                        dataFiltered.clear() // hapus dataFiltered yang ada saat ini
                        data.forEach { country ->   // lakukan perulangan, tiap item list data jadi variabel country
                            country?.let {      // Jika country tidak null
                                // maka tambahkan data tersebut ke dalam data Filtered
                                if (country is CountriesItem) dataFiltered.add(it as CountriesItem)
                            }
                        }
                        // beritahu adapter recyclerview jika data set telah berubah
                        notifyDataSetChanged()
                    }
                }
            }
        }
    }
}
