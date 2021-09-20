package ca.trackthevaxxed.vaxcheck.ui.onboarding


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import ca.trackthevaxxed.vaxcheck.R
import ca.trackthevaxxed.vaxcheck.databinding.FragmentOnboardingBinding
import ca.trackthevaxxed.vaxcheck.utils.setSpannableLink
import ca.trackthevaxxed.vaxcheck.utils.toast
import ca.trackthevaxxed.vaxcheck.utils.viewBindings
import ca.trackthevaxxed.vaxcheck.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * [OnBoardingFragment]
 *
 * @author Amit Metri
 */
@AndroidEntryPoint
class OnBoardingFragment : Fragment(R.layout.fragment_onboarding) {

    private val binding by viewBindings(FragmentOnboardingBinding::bind)

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAllowCameraPermission.setOnClickListener {
            sharedViewModel.setOnBoardingShown(true)
        }

        viewLifecycleOwner.lifecycleScope.launch {

            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                sharedViewModel.isOnBoardingShown.collect { shown ->
                    when (shown) {
                        true -> {
                            val navOptions = NavOptions.Builder()
                                .setPopUpTo(R.id.onBoardingFragment, true)
                                .build()
                            findNavController().navigate(
                                R.id.barcodeScannerFragment,
                                null,
                                navOptions
                            )
                        }
                    }
                }

            }

        }

        binding.txtPrivacyPolicy.setSpannableLink {
            showPrivacyPolicy()
        }
    }

    private fun showPrivacyPolicy() {
        val webpage: Uri = Uri.parse(getString(R.string.url_privacy_policy))
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        try {
            startActivity(intent)
        } catch (e: Exception) {
            context?.toast(getString(R.string.no_app_found))
        }
    }
}