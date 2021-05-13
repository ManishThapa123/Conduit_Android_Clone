package io.realworld.android.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import io.realworld.android.AuthViewModel
import io.realworld.android.R

class SettingsFragment: Fragment() {

    private val authViewModel by activityViewModels<AuthViewModel>()
    private var image: EditText? = null
    private var username: EditText? = null
    private var bio: EditText? = null
    private var email: EditText? = null
    private var password: EditText? = null
    private var buttonUpdate: Button? = null

    private var root: View? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_settings,container,false)
        image = root?.findViewById(R.id.imageEditText)
        username = root?.findViewById(R.id.usernameEditText)
        bio = root?.findViewById(R.id.bioEditText)
        email = root?.findViewById(R.id.emailEditText)
        password = root?.findViewById(R.id.passwordEditText)
        buttonUpdate = root?.findViewById(R.id.btn_update)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.user.observe({lifecycle}){
            root?.apply {
                image?.setText(it?.image ?: "")
                username?.setText(it?.username ?: "")
                email?.setText(it?.email ?: "")
                bio?.setText(it?.bio ?: "")
            }

        }
    }

}