// FILE: Base.kt

interface Base {
    fun foo(): Int
}

// FILE: Derived.java

public class Derived implements Base {
    public int foo() {
        return 42;
    }
}

// FILE: Test.kt

fun test() {
    val b: Base = Derived()
    b.foo()
}


