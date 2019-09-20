// MODULE: m3
// FILE: my/Some.java
package my;
public class Some {
    private int foo = 42;
    public int getFoo() { return foo; }
}
// MODULE: m2(m3)
// FILE: my/Base.java
package my;
public class Base {
    private Some some;
}
// MODULE: m1(m2)
// FILE: my/first.kt
package my
class First : Base()
