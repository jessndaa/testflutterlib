import 'dart:async';

import 'package:flutter/services.dart';

class Ussd {
  static const MethodChannel _channel =
      const MethodChannel('ussd');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
  static Future<void> startPermission() async {
    await _channel.invokeMethod('testPermitionRequest');
  }
}
