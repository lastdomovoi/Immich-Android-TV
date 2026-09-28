package nl.giejay.android.tv.immich.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.leanback.app.GuidedStepSupportFragment
import androidx.leanback.widget.GuidanceStylist
import androidx.leanback.widget.GuidedAction
import androidx.navigation.fragment.findNavController
import nl.giejay.android.tv.immich.BuildConfig
import nl.giejay.android.tv.immich.R
import nl.giejay.android.tv.immich.shared.guidedstep.GuidedStepUtil.addAction
import nl.giejay.android.tv.immich.shared.guidedstep.GuidedStepUtil.addCheckedAction



class AuthFragmentStep1 : GuidedStepSupportFragment() {
    private val ACTION_SIGN_IN = 0L
    private val ACTION_CONTINUE = 3L

    override fun onCreateGuidance(savedInstanceState: Bundle?): GuidanceStylist.Guidance {
        val icon: Drawable =
            requireContext().getDrawable(R.drawable.icon)!!
        return GuidanceStylist.Guidance(
            getString(R.string.app_name) + " (${BuildConfig.VERSION_NAME})",
            getString(R.string.login_immich_description),
            "",
            icon
        )
    }

    override fun onCreateActions(actions: MutableList<GuidedAction>, savedInstanceState: Bundle?) {
        addCheckedAction(
            actions,
            ACTION_SIGN_IN,
            getString(R.string.auth_sign_in_by_api_key),
            getString(R.string.auth_sign_in_by_api_key_desc),
            true,
            1
        )
    }

    override fun onCreateButtonActions(
        actions: MutableList<GuidedAction>,
        savedInstanceState: Bundle?
    ) {
        super.onCreateButtonActions(actions, savedInstanceState)
        addAction(actions, ACTION_CONTINUE, getString(R.string.continue_text), "")
    }

    override fun onGuidedActionClicked(action: GuidedAction) {
        super.onGuidedActionClicked(action)
        if (action.id == ACTION_CONTINUE) {
            val navController = findNavController()
            if (navController.currentDestination?.id == R.id.authFragment) {
                navController.navigate(AuthFragmentStep1Directions.actionAuthToAuth2())
            }
        }
    }
}
