package com.example.c24raheel.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.c24raheel.R
import com.example.c24raheel.base.BaseFragment
import com.example.c24raheel.databinding.FragmentFirstBinding
import com.example.c24raheel.misc.LoadingViewDialog
import com.example.c24raheel.utils.StateData
import com.example.c24raheel.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment : BaseFragment() {

    private var _binding: FragmentFirstBinding? = null
    private val mViewModel: HomeViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        (activity as MainActivity).showLoading()
        mViewModel.getItemsList()
        mViewModel.listItemsResponseSLD.observe(viewLifecycleOwner) {
            when(it.status){
                 StateData.DataStatus.CREATED->{
                     Log.d("","");
                 }
                StateData.DataStatus.LOADING->{
                    Log.d("","");

                }
                StateData.DataStatus.SUCCESS->{
                    Log.d("","");

                }
                StateData.DataStatus.ERROR->{
                    Log.d("","");

                }
            }
        }
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}