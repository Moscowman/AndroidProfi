package ru.varasoft.androidprofi.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import geekbrains.ru.translator.utils.network.isOnline
import ru.varasoft.androidprofi.R
import ru.varasoft.androidprofi.databinding.LoadingLayoutBinding
import ru.varasoft.androidprofi.model.data.AppState
import ru.varasoft.androidprofi.model.data.DataModel
import ru.varasoft.androidprofi.utils.ui.AlertDialogFragment
import ru.varasoft.androidprofi.viewmodel.Interactor

private const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c77522"

abstract class BaseActivity<T : AppState, I : Interactor<T>, VB : ViewBinding> :
    AppCompatActivity() {

    private var _binding: VB? = null
    abstract val bindingInflater: (LayoutInflater) -> VB
    private var _loadingLayoutBinding: LoadingLayoutBinding? = null


    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    protected val loadingLayoutBinding: LoadingLayoutBinding
        get() = _loadingLayoutBinding as LoadingLayoutBinding

    protected var isNetworkAvailable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        _loadingLayoutBinding =
            LoadingLayoutBinding.inflate(LayoutInflater.from(applicationContext))
        isNetworkAvailable = isOnline(applicationContext)
    }

    override fun onResume() {
        super.onResume()
        isNetworkAvailable = isOnline(applicationContext)
        if (!isNetworkAvailable && isDialogNull()) {
            showNoInternetConnectionDialog()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    protected fun renderData(appState: T) {
        when (appState) {
            is AppState.Success -> {
                showViewWorking()
                appState.data?.let {
                    if (it.isEmpty()) {
                        showAlertDialog(
                            getString(R.string.dialog_tittle_sorry),
                            getString(R.string.empty_server_response_on_success)
                        )
                    } else {
                        setDataToAdapter(it)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    loadingLayoutBinding.progressBarHorizontal.visibility = View.VISIBLE
                    loadingLayoutBinding.progressBarRound.visibility = View.GONE
                    loadingLayoutBinding.progressBarHorizontal.progress = appState.progress
                } else {
                    loadingLayoutBinding.progressBarHorizontal.visibility = View.GONE
                    loadingLayoutBinding.progressBarRound.visibility = View.VISIBLE
                }
            }
            is AppState.Error -> {
                showViewWorking()
                showAlertDialog(getString(R.string.error_stub), appState.error.message)
            }
        }
    }

    protected fun showNoInternetConnectionDialog() {
        showAlertDialog(
            getString(R.string.dialog_title_device_is_offline),
            getString(R.string.dialog_message_device_is_offline)
        )
    }

    protected fun showAlertDialog(title: String?, message: String?) {
        AlertDialogFragment.newInstance(title, message)
            .show(supportFragmentManager, DIALOG_FRAGMENT_TAG)
    }

    private fun showViewWorking() {
        loadingLayoutBinding.loadingFrameLayout.visibility = View.GONE
    }

    private fun showViewLoading() {
        loadingLayoutBinding.loadingFrameLayout.visibility = View.VISIBLE
    }

    private fun isDialogNull(): Boolean {
        return supportFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
    }

    abstract fun setDataToAdapter(data: List<DataModel>)
}
