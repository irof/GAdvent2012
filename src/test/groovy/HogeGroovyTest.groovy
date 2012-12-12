import org.junit.Rule
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.RunWith

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
}
