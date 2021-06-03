package com.fghilmany.stockbittest.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.fghilmany.stockbittest.R
import com.fghilmany.stockbittest.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            btLogin.setOnClickListener {
                if (etEmail.text.isNullOrEmpty()){
                    etEmail.error = "Wajib diisi"
                    etEmail.requestFocus()
                    return@setOnClickListener
                }

                if (etPassword.text.isNullOrEmpty()){
                    etPassword.error = "Wajib diisi"
                    etPassword.requestFocus()
                    return@setOnClickListener
                }

                findNavController().navigate(R.id.action_loginFragment_to_watchListFragment)
            }
        }
    }

}