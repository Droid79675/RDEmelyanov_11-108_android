package com.example.task4_recycler_view.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.task4_recycler_view.R
import com.example.task4_recycler_view.data.CarRepository
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.android.synthetic.main.fragment_car_info.view.*
import kotlin.math.abs

class CarInfoFragment : Fragment(R.layout.fragment_car_info) {
    private var textViewName: TextView? = null
    private var textViewWeight: TextView? = null
    private var textViewEngine: TextView? = null
    private var textViewGasTank: TextView? = null
    private var imageViewCar: ImageView? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id: Int = arguments?.getString(ARG_TEXT).orEmpty().toInt()
        val car = CarRepository.cars[id]
        val name = car.name
        val weight = car.weight
        val engine = car.engine
        val gasTank = car.gasTank
        val url: String = car.url

        textViewName = view.findViewById(R.id.tv_name)
        textViewName?.text = name
        textViewWeight = view.findViewById(R.id.tv_weight)
        textViewWeight?.text = "Weight: $weight кг"
        textViewEngine = view.findViewById(R.id.tv_engine)
        textViewEngine?.text = "Engine: $engine л.с."
        textViewGasTank = view.findViewById(R.id.tv_gasTank)
        textViewGasTank?.text = "GasTank: $gasTank л"
        imageViewCar = view.findViewById(R.id.iv_car_info)
        val appBarInfo: AppBarLayout = view.findViewById(R.id.app_bar_layout_info)
        val collapsingToolbarLayout: CollapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar)
        when {
            imageViewCar != null -> {
                Glide.with(this)
                    .load(url)
                    .into(imageViewCar!!)
            }
        }
        when{
            appBarInfo != null && collapsingToolbarLayout != null -> {
                appBarInfo.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset ->
                    when {
                        // Expanded
                        verticalOffset == 0 -> {
                            collapsingToolbarLayout.isTitleEnabled = false
                        }
                        // Collapsed
                        abs(verticalOffset) == appBarLayout.totalScrollRange -> {
                            collapsingToolbarLayout.isTitleEnabled = true
                        }
                    }
                })
            }
        }


        }


    companion object{
        private const val ARG_TEXT =""
        fun createBundle(id:String): Bundle {
            val bundle = Bundle()
            bundle.putString(
                ARG_TEXT,
                id
            )
            return bundle
        }

    }
}