package io.indrian16.sembako.ui.scanner.view

import android.os.Bundle
import android.view.*
import android.widget.Toast
import com.google.zxing.Result
import io.indrian16.sembako.R
import io.indrian16.sembako.ui.base.view.BaseFragment
import io.indrian16.sembako.ui.scanner.presenter.ScannerPresenter

import me.dm7.barcodescanner.zxing.ZXingScannerView
import timber.log.Timber
import javax.inject.Inject

class ScannerFragment : BaseFragment(), ScannerView, ZXingScannerView.ResultHandler {

    companion object {

        const val TAG = "ScannerFragment"

        const val FLASH_STATE = "FLASH_STATE"
        const val AUTO_FOCUS_STATE = "AUTO_FOCUS_STATE"
        const val CAMERA_ID = "CAMERA_ID"

        fun newInstance(): ScannerFragment {

            return ScannerFragment()
        }
    }

    private var mFlash: Boolean = false
    private var mAutoFocus: Boolean = false
    private var mCameraId = -1

    private lateinit var mScannerView: ZXingScannerView

    @Inject lateinit var presenter: ScannerPresenter

    override fun onResume() {
        super.onResume()
        mScannerView.setResultHandler(this)
        mScannerView.startCamera(mCameraId)
        mScannerView.setAutoFocus(mAutoFocus)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              state: Bundle?): View? {

        mScannerView = ZXingScannerView(activity)

        if (state != null) {

            mFlash = state.getBoolean(FLASH_STATE, false)
            mAutoFocus = state.getBoolean(AUTO_FOCUS_STATE, true)
            mCameraId = state.getInt(CAMERA_ID, -1)
        } else {

            mFlash = false
            mAutoFocus = true
            mCameraId = -1
        }

        return mScannerView
    }

    override fun handleResult(result: Result?) {

        Toast.makeText(context, result.toString(), Toast.LENGTH_LONG).show()
        Timber.tag(TAG).d(result.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {

        inflater?.inflate(R.menu.scanner_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {

            R.id.scanner_menu_flash -> {

                mFlash = !mFlash

                if (mFlash) {

                    presenter.onFlashLight()
                    item.setTitle(R.string.flash_light_on)

                } else {

                    presenter.offFlashLight()
                    item.setTitle(R.string.flash_light_off)
                }

                return true
            }

            R.id.scanner_menu_auto_focus -> {

                mAutoFocus = !mAutoFocus

                if (mAutoFocus) {

                    presenter.onAutoFocus()
                    item.setTitle(R.string.auto_focus_on)

                } else {

                    presenter.offAutoFocus()
                    item.setTitle(R.string.auto_focus_off)
                }

                return true
            }

            else -> return super.onOptionsItemSelected(item)

        }

    }

    override fun setOnFlashLight(on: Boolean) {

        mFlash = on
        mScannerView.flash = mFlash
    }

    override fun setOffFlashLight(off: Boolean) {

        mFlash = off
        mScannerView.flash = mFlash
    }

    override fun setOnAutoFocus(on: Boolean) {

        mAutoFocus = on
        mScannerView.setAutoFocus(mAutoFocus)
    }

    override fun setOffAutoFocus(off: Boolean) {

        mAutoFocus = off
        mScannerView.setAutoFocus(mAutoFocus)
    }

    override fun onPause() {
        super.onPause()
        mScannerView.stopCamera()
    }
}
