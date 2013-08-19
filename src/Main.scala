package test.io.utils


/**
 * Created with IntelliJ IDEA.
 * User: tobysuggate
 * Date: 17/08/13
 * Time: 1:37 PM
 */

object Main extends App {

//  FindTypes.run()

  var s = new Statement("template <class traits>\nclass CefStructBase : public traits::struct_type {\n public:\n  typedef typename traits::struct_type struct_type;\n\n  CefStructBase() : attached_to_(NULL) {\n    Init();\n  }\n  virtual ~CefStructBase() {\n    // Only clear this object's data if it isn't currently attached to a\n    // structure.\n    if (!attached_to_)\n      Clear(this);\n  }\n\n  CefStructBase(const CefStructBase& r) {\n    Init();\n    *this = r;\n  }\n  CefStructBase(const struct_type& r) {  // NOLINT(runtime/explicit)\n    Init();\n    *this = r;\n  }\n\n  ///\n  // Clear this object's values.\n  ///\n  void Reset() {\n    Clear(this);\n    Init();\n  }")


}

