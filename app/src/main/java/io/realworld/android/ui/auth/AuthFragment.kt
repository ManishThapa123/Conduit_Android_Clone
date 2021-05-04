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



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_auth, container, false)
        val tabLayout = root.findViewById<TabLayout>(R.id.auth_tabs)


//        activity.let {
//            navController = Navigation.findNavController(requireActivity(), R.id.navigation_auth)}

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
            when(tab?.position){
                0 -> {
                    requireView().findNavController().navigate(R.id.gotoLoginFragment)

                }
                1 -> {
                    requireView().findNavController().navigate(R.id.gotoSignUpFragment)
                }

            }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}