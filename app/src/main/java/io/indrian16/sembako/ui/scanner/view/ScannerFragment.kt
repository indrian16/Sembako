package io.indrian16.sembako.ui.scanner.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.Toast
import com.google.zxing.Result
import io.indrian16.sembako.R

import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScannerFragment : Fragment(), ScannerView, ZXingScannerView.ResultHandler {

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

    lateinit var scannerView: ZXingScannerView

    override fun onResume() {
        super.onResume()
        scannerView.setResultHandler(this)
        scannerView.startCamera(mCameraId)
        scannerView.setAutoFocus(mAutoFocus)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              state: Bundle?): View? {

        scannerView = ZXingScannerView(activity)

        if (state != null) {

            mFlash = state.getBoolean(FLASH_STATE, false)
            mAutoFocus = state.getBoolean(AUTO_FOCUS_STATE, true)
            mCameraId = state.getInt(CAMERA_ID, -1)
        } else {

            mFlash = false
            mAutoFocus = true
            mCameraId = -1
        }

        return scannerView
    }

    override fun handleResult(result: Result?) {

        Toast.makeText(context, result.toString(), Toast.LENGTH_LONG).show()
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

                    setOnFlashLight(true)
                } else {

                    setOffFlashLight(false)
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun setOnFlashLight(on: Boolean) {

        mFlash = on
        scannerView.flash = mFlash
    }

    override fun setOffFlashLight(off: Boolean) {

        mFlash = off
        scannerView.flash = mFlash
    }

    override fun setOnAutoFocus(on: Boolean) {

        mAutoFocus = on
        scannerView.setAutoFocus(mAutoFocus)
    }

    override fun setOffAutoFocus(off: Boolean) {

        mAutoFocus = off
        scannerView.setAutoFocus(mAutoFocus)
    }

    override fun onPause() {
        super.onPause()
        scannerView.stopCamera()
    }
}
