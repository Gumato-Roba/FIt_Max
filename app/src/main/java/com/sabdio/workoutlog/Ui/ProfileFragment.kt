package com.sabdio.workoutlog.Ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sabdio.workoutlog.R
import com.sabdio.workoutlog.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_profile, container, false)
//        val tvLogout=view?.findViewById<ImageView>(R.id.ivLogout)
//        ivLogout?.setOnClickListener{
//            logout()
//        }
//        return inflater.inflate(R.layout.fragment_profile, container, false)


    }


}