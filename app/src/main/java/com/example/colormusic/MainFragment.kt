package com.example.colormusic

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import kotlin.system.exitProcess

class MainFragment : Fragment(), MenuProvider {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() = exitProcess(0)
            })

        requireActivity().addMenuProvider(this, viewLifecycleOwner)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.main_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.next -> {
                Toast.makeText(requireContext(), "Next", Toast.LENGTH_SHORT).show()
            }
            R.id.settings -> {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.host, SettingsFragment.newInstance())
                    .commit()
            }
        }
        return true
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}