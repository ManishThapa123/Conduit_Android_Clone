package io.realworld.android.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.*
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import io.realworld.android.R

class AuthFragment : Fragment() {

    private var navController: NavController? = null
    private var root: View? = null
    private var tabLayout: TabLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_auth, container, false)
         tabLayout = root?.findViewById(R.id.auth_tabs)
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = root?.let { Navigation.findNavController(it.findViewById(R.id.authFragmentNavHost)) }
        tabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> {
                        navController?.navigate(R.id.gotoLoginFragment)

                    }
                    1 -> {
                        navController?.navigate(R.id.gotoSignUpFragment)
                    }

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

    }
}