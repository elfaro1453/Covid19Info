package id.idn.fahru.covid19info.adapter

import androidx.recyclerview.widget.RecyclerView
import id.idn.fahru.covid19info.databinding.ListCountryBinding
import id.idn.fahru.covid19info.pojo.CountriesItem

/**
 * Created by Imam Fahrur Rofi on 29/07/2020.
 */
// ListCountryVH digunakan untuk menghubungkan antara list_country.xml dengan RecyclerView Adapter
class ListCountryVH(private val binding: ListCountryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    // fungsi bind digunakan untuk mendapatkan data dari recyclerview Adapter
    fun bind(data: CountriesItem) {
        binding.txtCountryName.text = data.country
        binding.txtTotalCase.text = data.totalConfirmed.toString()
        binding.txtTotalRecovered.text = data.totalRecovered.toString()
        binding.txtTotalDeaths.text = data.totalDeaths.toString()
    }
}