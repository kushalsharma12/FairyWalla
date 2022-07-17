package com.kushalsharma.fairywallaapp.ui.FairyWallaProductList

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.kushalsharma.fairywallaapp.R
import com.kushalsharma.fairywallaapp.databinding.FragmentFairyWallaProductListBinding
import com.kushalsharma.fairywallaapp.modals.Fproducts


class FairyWallaProductList : android.app.Fragment() {

    private var _binding: FragmentFairyWallaProductListBinding? = null
    private val binding get() = _binding!!

    private val db = FirebaseFirestore.getInstance()
    private lateinit var adapter: ProductlistAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFairyWallaProductListBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val id = arguments?.getString("FairyWallaUid")

        val query = db.collection("Posts")
//            .whereEqualTo("userId", id)
            .orderBy("title").limit(100)

        val options = FirestoreRecyclerOptions.Builder<Fproducts>()
            .setQuery(query, Fproducts::class.java)
            .setLifecycleOwner(this).build()

        adapter = ProductlistAdapter(options)
        binding.recyclerViewProductList.adapter = adapter

        binding.backBtnFpl.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_fairyWallaProductList_to_navigation_explore)
        }

        binding.cTlocBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_fairyWallaProductList_to_navigation_map)

        }

        return root
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}