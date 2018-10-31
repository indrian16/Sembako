package io.indrian16.sembako.ui.home.view

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import io.indrian16.sembako.R
import io.indrian16.sembako.database.repository.sembako.Sembako
import io.indrian16.sembako.ui.base.view.BaseFragment
import io.indrian16.sembako.ui.home.presenter.HomePresenter
import timber.log.Timber
import javax.inject.Inject

class HomeFragment : BaseFragment(), HomeView {

    companion object {

        const val TAG = "HomeFragment"

        fun newInstance(): HomeFragment {

            return HomeFragment()
        }
    }

    @Inject lateinit var presenter: HomePresenter

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mTvNoItem: TextView

    override fun onResume() {
        super.onResume()
        presenter.loadSembakoItem()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        with(view) {

            mRecyclerView = findViewById(R.id.rv_home)
            mTvNoItem = findViewById(R.id.tv_home_no_item)
        }

        return view
    }

    override fun addSembakoItem(sembakoList: List<Sembako>) {

        mTvNoItem.visibility = View.INVISIBLE
        mRecyclerView.visibility = View.VISIBLE
    }

    override fun sembakoNoItem() {

        mTvNoItem.visibility = View.VISIBLE
        mRecyclerView.visibility = View.INVISIBLE
    }

    override fun showError(error: Throwable) {

        Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
        Timber.tag(TAG).e(error)
    }

    override fun onPause() {
        super.onPause()
        presenter.unSubscribe()
    }
}
