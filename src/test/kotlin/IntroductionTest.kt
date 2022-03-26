import kotlin.test.Test
import kotlin.test.assertEquals

internal class IntroductionTest {
    private val introduction: Introduction = Introduction()

    @Test
    fun testNamedArguments() {
        // Return list in JSON format:
        // Given:
        val list: Collection<String> = listOf("a", "b", "c")
        // When:
        val joined: String = introduction.joinOptions(list);
        // Then:
        val expectedVal = "[a, b, c]";

        assertEquals(expectedVal, joined);
    }
}