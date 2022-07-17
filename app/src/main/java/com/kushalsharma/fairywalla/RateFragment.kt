package com.kushalsharma.fairywalla

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.kushalsharma.fairywallaapp.databinding.FragmentRateBinding

class RateFragment : Fragment() {

    private var _binding: FragmentRateBinding? = null
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRateBinding.inflate(inflater, container, false)
        val root: View = binding!!.root

        binding!!.ratingBar.setOnRatingChangeListener { ratingBar, rating, fromUser ->
            binding!!.userRating.text = rating.toString()
        }


        binding!!.rateBtn.setOnClickListener{
            Toast.makeText(this.requireContext(), "Thank You!", Toast.LENGTH_SHORT).show()
//            Log.d("RatingValue","${finalRating}")

            val s : String = binding!!.rateTv.getText().toString()
            var total : Float = s.toFloat()

            val sT : String = binding!!.userRating.text.toString()
            var userRating : Float = sT.toFloat()

            val totalUser : Float = 4.0f

            total = (userRating + total)/totalUser
            val finalVal : String = total.toString()
            binding!!.rateTv.setText(finalVal).toString()

        }

        binding!!.backBtnRate.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_rateFragment_to_navigation_explore)
        }
                Glide.with(binding!!.rateProfileImage.context)
            .load(R.drawable.img_mu)
            .placeholder(R.drawable.ic_loading_placeholder)
            .transform(CenterCrop(), RoundedCorners(40))
            .into(binding!!.rateProfileImage)

        return root
    }

}