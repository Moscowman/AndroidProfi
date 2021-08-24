package ru.varasoft.androidprofi.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import org.koin.android.viewmodel.ext.android.viewModel
import ru.varasoft.androidprofi.R
import ru.varasoft.androidprofi.databinding.ActivityMainBinding
import ru.varasoft.androidprofi.model.data.AppState
import ru.varasoft.androidprofi.model.data.DataModel
import ru.varasoft.androidprofi.view.base.BaseActivity
import ru.varasoft.androidprofi.view.main.adapter.MainAdapter
import ru.varasoft.androidprofi.viewmodel.MainViewModel

class MainActivity : BaseActivity<AppState, MainInteractor, ActivityMainBinding>() {

    lateinit var viewModel: MainViewModel

    private var adapter: MainAdapter? = null

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding = ActivityMainBinding::inflate

    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        val _viewModel: MainViewModel by viewModel()
        viewModel = _viewModel

        viewModel.subscribe().observe(this@MainActivity, Observer<AppState> { renderData(it) })

        binding.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    viewModel.getData(searchWord, true)
                }
            })
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter?.setData(data)
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            viewModel.getData("hi", true)
        }
    }

    private fun showViewSuccess() {
        binding.successLinearLayout.visibility = VISIBLE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewLoading() {
        binding.successLinearLayout.visibility = GONE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewError() {
        binding.successLinearLayout.visibility = GONE
        binding.errorLinearLayout.visibility = VISIBLE
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf4fgt0-092395"
    }
}