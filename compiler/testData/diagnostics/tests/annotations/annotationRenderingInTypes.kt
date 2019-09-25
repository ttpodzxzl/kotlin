// !RENDER_DIAGNOSTICS_MESSAGES
// !DIAGNOSTICS: -UNUSED_PARAMETER

fun foo(x: String) {}
fun bar(f: () -> Unit) {}
fun test() = bar(<!TYPE_MISMATCH("() -> Unit", "KFunction1<String, Unit>")!>::foo<!>)

