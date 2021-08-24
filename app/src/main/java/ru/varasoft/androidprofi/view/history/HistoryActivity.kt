package ru.varasoft.androidprofi.view.history

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import org.koin.android.viewmodel.ext.android.viewModel
import ru.varasoft.androidprofi.R
import ru.varasoft.androidprofi.databinding.ActivityHistoryBinding
import ru.varasoft.androidprofi.model.data.AppState
import ru.varasoft.androidprofi.model.data.DataModel
import ru.varasoft.androidprofi.view.base.BaseActivity
import ru.varasoft.androidprofi.view.history.adapter.HistoryAdapter
import ru.varasoft.androidprofi.viewmodel.HistoryViewModel

class HistoryActivity : BaseActivity<AppState, HistoryInteractor, ActivityHistoryBinding>() {

    lateinit var model: HistoryViewModel

    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override val bindingInflater: (LayoutInflater) -> ActivityHistoryBinding = ActivityHistoryBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        iniViewModel()
        initViews()
    }
    // Сразу запрашиваем данные из локального репозитория
    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }
    // Вызовется из базовой Activity, когда данные будут готовы
    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun iniViewModel() {
        if (binding.historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: HistoryViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@HistoryActivity, Observer<AppState> { renderData(it) })
    }
    // Инициализируем адаптер и передаем его в RecyclerView
    private fun initViews() {
        binding.historyActivityRecyclerview.adapter = adapter
    }
}