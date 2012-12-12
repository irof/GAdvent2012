import org.junit.Rule
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.experimental.theories.DataPoints
import org.junit.experimental.theories.Theories
import org.junit.experimental.theories.Theory
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.RunWith
import spock.lang.Specification
import spock.lang.Unroll

import static org.hamcrest.CoreMatchers.is
import static org.junit.Assert.assertThat

@RunWith(Enclosed)
class HogeGroovyTest {

    static class JUnitTest {

        @Rule
        public TestRule watcher = [starting: { println it.methodName }] as TestWatcher

        HogeGroovy sut = new HogeGroovy()

        @Test
        public void 三ならFizz() {
            assertThat sut.fizzBuzz(3), is("Fizz")
        }

        @Test
        public void 三の倍数もFizz() {
            assertThat sut.fizzBuzz(12), is("Fizz")
        }

        @Test
        public void 五ならFizz() {
            assertThat sut.fizzBuzz(5), is("Buzz")
        }

        @Test
        public void 五の倍数もFizz() {
            assertThat sut.fizzBuzz(10), is("Buzz")
        }

        @Test
        public void 十五ならFizzBuzz() {
            assertThat sut.fizzBuzz(15), is("FizzBuzz")
        }

        @Test
        public void 十五の倍数もFizzBuzz() {
            assertThat(sut.fizzBuzz(45), is("FizzBuzz"))
        }
    }

    @RunWith(Theories)
    static class TheoriesTest {
        HogeGroovy sut = new HogeGroovy()

        @DataPoints
        static Object[] params() {
            def map = [:]
            map << [3: "Fizz", 12: "Fizz"]
            map << [5: "Buzz", 10: "Buzz"]
            map << [15: "FizzBuzz", 30: "FizzBuzz"]
        }

        @Theory
        public void test(def param) {
            assert sut.fizzBuzz(param.key) == param.value
        }
    }

    static class HogeSpock extends Specification {
        def "Enclosedの中でSpockが使えるよー"() {
            expect: true
        }

        @Unroll
        def "fizzBuzzへの入力値が #num の時は #res が返される"() {
            def sut = new HogeJava()
            expect: sut.fizzBuzz(num) == res
            where:
            num | res
            1   | "1"
            2   | "2"
            3   | "Fizz"
            4   | "4"
            5   | "Buzz"
            6   | "Fizz"
            7   | "7"
            8   | "8"
            9   | "Fizz"
            10  | "Buzz"
            11  | "11"
            12  | "Fizz"
            13  | "13"
            14  | "14"
            15  | "FizzBuzz"
        }
    }
}
