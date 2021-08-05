package ru.varasoft.androidprofi.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import ru.varasoft.androidprofi.model.data.AppState
import ru.varasoft.androidprofi.viewmodel.BaseViewModel

abstract class BaseActivity<T : AppState> : AppCompatActivity(), View {

    abstract val viewModel: BaseViewModel<T>

    abstract override fun renderData(appState: AppState)

}
