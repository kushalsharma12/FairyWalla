package com.kushalsharma.fairywallaapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kushalsharma.fairywallaapp.R
import com.kushalsharma.fairywallaapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        auth = Firebase.auth
        val currentUser = auth.currentUser

        Glide.with(binding.profileImage)
            .load(currentUser!!.photoUrl)
            .placeholder(R.drawable.ic_add_img_placeholder)
            .transform(CenterCrop(), RoundedCorners(32))
            .into(binding.profileImage)

        binding.profileName.text = currentUser.displayName.toString()
        binding.profileEmail.text = currentUser.email.toString()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}