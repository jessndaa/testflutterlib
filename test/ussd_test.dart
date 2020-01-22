import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:ussd/ussd.dart';

void main() {
  const MethodChannel channel = MethodChannel('ussd');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await Ussd.platformVersion, '42');
  });
}
