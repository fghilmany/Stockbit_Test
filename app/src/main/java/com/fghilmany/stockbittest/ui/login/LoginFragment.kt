package com.fghilmany.stockbittest.ui.login

import android.app.KeyguardManager
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.findNavController
import com.fghilmany.stockbittest.R
import com.fghilmany.stockbittest.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private var cancellationSignal : CancellationSignal? = null
    private val authenticationCallback: BiometricPrompt.AuthenticationCallback
        get() =
            @RequiresApi(Build.VERSION_CODES.P)
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    findNavController().navigate(R.id.action_loginFragment_to_watchListFragment)
                }

            }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.P)
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

            checkBiometricSupport()
            btFingerprintLoginp.setOnClickListener {
                val biometricPrompt = BiometricPrompt.Builder(requireContext())
                        .setTitle("Absen masuk")
                        .setDescription("silahkan scan sidik jari anda")
                        .setNegativeButton("Cancel", requireActivity().mainExecutor, { _, _ ->
                            Toast.makeText(context, "Authentification cancelled", Toast.LENGTH_SHORT).show()
                        }).build()


                biometricPrompt.authenticate(getCancellationSignal(), requireActivity().mainExecutor, authenticationCallback)
            }
        }

    }

    private fun checkBiometricSupport(): Boolean {

        val keyGuardManager = requireActivity().getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

        if (!keyGuardManager.isKeyguardLocked){
            Toast.makeText(context, "Fingerprint suthentication has not been enabled in settings", Toast.LENGTH_SHORT).show()
            binding.btFingerprintLoginp.visibility = View.GONE
            return false
        }

        if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(context, "fingerprint permission is nor rnabled", Toast.LENGTH_SHORT).show()
            return false
        }

        return if (requireActivity().packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)){
            true
        } else true
    }

    private fun getCancellationSignal(): CancellationSignal {
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener {
            Toast.makeText(context, "Authetification was cancelled by the user", Toast.LENGTH_SHORT).show()
        }

        return cancellationSignal as CancellationSignal
    }

    /*private fun initBiometric(): Boolean{
        var state = false
        val executor = ContextCompat.getMainExecutor(this.context)
        val biometricManager = BiometricManager.from(this.requireContext())

        when (biometricManager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS ->{
                authUser(executor)
                state = authUser(executor)
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                Toast.makeText(
                    requireContext(),
                    getString(R.string.error_msg_no_biometric_hardware),
                    Toast.LENGTH_LONG
                ).show()
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                Toast.makeText(
                    requireContext(),
                    getString(R.string.error_msg_biometric_hw_unavailable),
                    Toast.LENGTH_LONG
                ).show()
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                Toast.makeText(
                    requireContext(),
                    getString(R.string.error_msg_biometric_not_setup),
                    Toast.LENGTH_LONG
                ).show()
        }
        return state
    }

    private fun authUser(executor: Executor): Boolean {
        var state = false
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.auth_title))
            .setSubtitle(getString(R.string.auth_subtitle))
            .setDeviceCredentialAllowed(true)
            .build()

        val biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(
                        requireContext(),
                        "Login Success",
                        Toast.LENGTH_SHORT
                    ).show()
                    Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_watchListFragment)
                    state = true
                }
                override fun onAuthenticationError(
                    errorCode: Int, errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.error_msg_auth_error) + errString,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                // 4
                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(requireContext(),
                        getString(R.string.error_msg_auth_failed),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        biometricPrompt.authenticate(promptInfo)
        return state
    }*/

}