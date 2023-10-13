package com.example.colormusic

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider

class SettingsFragment : Fragment(), MenuProvider {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.host, MainFragment.newInstance())
                        .commit()

                }
            })

        requireActivity().addMenuProvider(this, viewLifecycleOwner)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.settings_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.next -> {
                Toast.makeText(requireContext(), "Next", Toast.LENGTH_SHORT).show()
            }
            R.id.before -> {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.host, MainFragment.newInstance())
                    .commit()
            }
        }
        return true
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}