#import "UssdPlugin.h"
#if __has_include(<ussd/ussd-Swift.h>)
#import <ussd/ussd-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "ussd-Swift.h"
#endif

@implementation UssdPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftUssdPlugin registerWithRegistrar:registrar];
}
@end
