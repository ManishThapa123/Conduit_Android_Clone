package io.realworld.android.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import io.realworld.android.AuthViewModel
import io.realworld.android.R

class SignupFragment : Fragment() {
    val authViewModel: AuthViewModel by activityViewModels()
    private var submitButton : Button? = null
    private var email: EditText? = null
    private var password: EditText? = null
    private var username: EditText? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_login_signup, container, false)

        submitButton = root.findViewById(R.id.loginButton)
        email = root.findViewById(R.id.emailEditText)
        password = root.findViewById(R.id.passwordEditText)
        username = root.findViewById(R.id.usernameEditText)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submitButton?.setOnClickListener {
            authViewModel.signup(
                username?.text.toString(),
                email?.text.toString(),
                password?.text.toString()
            )
        }

    }
}