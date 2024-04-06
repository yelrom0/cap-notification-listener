import Foundation

@objc public class CapNotify: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
