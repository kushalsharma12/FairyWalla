package com.kushalsharma.fairywallaapp.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.kushalsharma.fairywallaapp.R
import com.kushalsharma.fairywallaapp.databinding.FragmentExploreBinding
import com.kushalsharma.fairywallaapp.modals.FairyWalla


class ExploreFragment : Fragment(), iPostAdapter {


    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    private val db = FirebaseFirestore.getInstance()
    private lateinit var adapter: ExploreAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val query = db.collection("Fairywalla")
            .orderBy("displayName").limit(100)

        val options = FirestoreRecyclerOptions.Builder<FairyWalla>()
            .setQuery(query, FairyWalla::class.java)
            .setLifecycleOwner(this).build()

        adapter = ExploreAdapter(options, this)
        binding.recyclerViewExplore.adapter = adapter
        binding.recyclerViewExplore.layoutManager = LinearLayoutManager(this.requireContext())

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

    override fun onPostClicked(v: View, fairywallaUid: String) {
//        val action = ExploreFragmentDirections.actionNavigationExploreToFairyWallaProductList(fairywallaUid)
//        v.findNavController().navigate(action)
        val bundle = bundleOf("FairyWallaUid" to fairywallaUid)
        v.findNavController()
            .navigate(R.id.action_navigation_explore_to_fairyWallaProductList, bundle)
    }

    override fun iPictureClicked(v: View) {

        v.findNavController().navigate(R.id.action_navigation_explore_to_rateFragment)
    }
}