package com.lukaflex.ussd

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry
import io.flutter.plugin.common.PluginRegistry.Registrar
import kotlin.properties.Delegates

/** UssdPlugin */
@Suppress("DEPRECATION")
public class UssdPlugin(): FlutterPlugin,
        MethodCallHandler,
        ActivityAware,
        PluginRegistry.ActivityResultListener,
        PluginRegistry.RequestPermissionsResultListener{

    interface  ValueChangeListner{
        fun onValueChanged(newValue: Activity)
    }

  private val PHONE_REQUEST_CODE = 0x005478F
  private val TAG = "APP_ENGINE"
    var observed = false
    var activity: Activity by Delegates.observable(Activity()) { property, oldValue, newValue ->
        observed = true
    }
  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    val channel = MethodChannel(flutterPluginBinding.flutterEngine.dartExecutor, "ussd")
    channel.setMethodCallHandler(UssdPlugin())
  }

  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      val channel = MethodChannel(registrar.messenger(), "ussd")
      channel.setMethodCallHandler(UssdPlugin())
    }
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    when(call.method){
      "getPlatformVersion"-> result.success("Android ${android.os.Build.VERSION.RELEASE}")
      "testPermitionRequest"-> {
        makeRequest()
        result.success("")
      }
    }
  }

  private fun makeRequest() {
    Log.i(TAG, "is observed:${observed}")
      if (activity == null){
          Log.i(TAG, "it not initialized again")
      }

  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
    return true
  }

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>?, grantResults: IntArray?): Boolean {
    return true
  }

  override fun onDetachedFromActivity() {}

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {

  }

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {

  }

  override fun onDetachedFromActivityForConfigChanges() {

  }
}
