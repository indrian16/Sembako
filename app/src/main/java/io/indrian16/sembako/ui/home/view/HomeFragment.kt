package io.indrian16.sembako.ui.home.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import io.indrian16.sembako.R

class HomeFragment : Fragment() {

    companion object {

        val TAG = "HomeFragment"

        fun newInstance(): HomeFragment {

            return HomeFragment()
        }
    }

    private lateinit var mRecyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        with(view) {

            mRecyclerView = findViewById(R.id.home_recycler_view)
        }

        return view
    }

}
