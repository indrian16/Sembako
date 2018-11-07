package io.indrian16.sembako.ui.main.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.indrian16.sembako.R
import io.indrian16.sembako.ui.base.view.BaseActivity
import io.indrian16.sembako.ui.createitem.view.CreateItemActivity
import io.indrian16.sembako.ui.home.view.HomeFragment
import io.indrian16.sembako.ui.main.presenter.MainPresenter
import io.indrian16.sembako.ui.scanner.view.ScannerFragment
import io.indrian16.sembako.ui.setting.view.SettingActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.appbar_navigation.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView, NavigationView.OnNavigationItemSelectedListener, HasSupportFragmentInjector {

    @Inject lateinit var presenter: MainPresenter
    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpToolbar()
        setUpDrawerMenu()
        setUpListener()
        presenter.navHome()
    }

    private fun setUpToolbar() {

        setSupportActionBar(toolbar)

        supportActionBar?.title = resources.getString(R.string.app_name)
    }

    private fun setUpListener() {

        fab_scanner.setOnClickListener {

            startActivity(Intent(baseContext, CreateItemActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {

            R.id.main_menu_search -> presenter.clickSearch()

            R.id.main_menu_refresh -> presenter.clickRefresh()

        }

        return super.onOptionsItemSelected(item)
    }

    private fun setUpDrawerMenu() {

        val toggle = ActionBarDrawerToggle(

                this, drawer_layout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {

        if (drawer_layout.isDrawerOpen(Gravity.START)) {

            drawer_layout.closeDrawer(Gravity.START)
        }

        super.onBackPressed()
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {

        when (menuItem.itemId) {

            R.id.nav_home -> {

                presenter.navHome()
                menuItem.isChecked = true
            }


            R.id.nav_scanner -> {

                presenter.navScanner()
                menuItem.isEnabled = true
            }

            R.id.nav_cashier -> presenter.navCashier()

            R.id.nav_setting -> presenter.navSetting()
        }

        drawer_layout.closeDrawers()
        return true
    }

    @SuppressLint("PrivateResource")
    private fun switchFragment(fragment: Fragment):FragmentTransaction {

        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.abc_tooltip_enter, R.anim.abc_tooltip_exit)
        transaction.replace(R.id.cl_root_view, fragment)
        return transaction
    }

    override fun goScanner() {

        switchFragment(ScannerFragment.newInstance()).commit()
    }

    override fun goHome() {

        switchFragment(HomeFragment.newInstance()).commit()
    }

    override fun goCashier() {

        Toast.makeText(baseContext, "Cashier", Toast.LENGTH_LONG).show()
    }

    override fun goSetting() {

        startActivity(Intent(baseContext, SettingActivity::class.java))
    }

    override fun goSearchView() {

        Toast.makeText(baseContext, "Search", Toast.LENGTH_LONG).show()
    }

    override fun refreshItem() {

        Toast.makeText(baseContext, "Refresh", Toast.LENGTH_LONG).show()
    }
}
