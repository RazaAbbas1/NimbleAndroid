package com.android.sampleTest.base

import android.annotation.TargetApi
import android.app.Activity
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.android.sampleTest.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren


abstract class BaseActivity<T : BaseViewModel, DB: ViewDataBinding>(private val viewModelClass: Class<T>) : AppCompatActivity() {

    val job = Job()
    val coroutineScope = CoroutineScope(Dispatchers.Main + job)


    val viewModel by lazy {
        ViewModelProvider(this)[viewModelClass]
    }

    val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutResId()) as DB
    }

    abstract fun setupViews()

    @LayoutRes
    abstract fun getLayoutResId(): Int

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.coroutineContext.cancelChildren()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        AndroidInjection.inject(this)
        setContentView(getLayoutResId())
        setupViews()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun setStatusBarGradiant(activity: Activity, background : Drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = activity.resources.getColor(android.R.color.transparent)
            window.navigationBarColor = activity.resources.getColor(android.R.color.transparent)
            window.setBackgroundDrawable(background)
        }
    }

    private fun takeToPlaystore(packageName: String?) {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
        } catch (e: ActivityNotFoundException) {
            val url = "https://play.google.com/store/apps/details?id=$packageName"
            openUrl(url)
        }
    }

    private fun openUrl(url: String) {
        try {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "No related activity found", Toast.LENGTH_SHORT).show()
        }
    }

    fun showAlert(message : String, buttonTitle : String = "Okay", isCancellable : Boolean = true,
                   showCancel : Boolean = true, successBlock : () -> Unit) {

        try {
            val builder1 = AlertDialog.Builder(this)
            builder1.setMessage(message)
            builder1.setTitle(getString(R.string.app_name))
            builder1.setCancelable(isCancellable)
            builder1.setPositiveButton(buttonTitle) { dialog, id ->
                successBlock()
            }
            if (showCancel) {
                builder1.setNegativeButton(
                        "Cancel"
                ) { dialog, id ->
                    dialog.cancel()
                }
            }
            val alert11 = builder1.create()
            alert11.show()
        } catch (e: Exception) {

        }
    }


}