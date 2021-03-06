package introduction

import introduction.Introduction.Mailer
import java.lang.IllegalArgumentException
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

        assertEquals(expectedVal, joined)
    }

    @Test
    fun testDefaultArguments() {
        // Should compile when you call "useFoo"
        val foos: List<String> = introduction.useFoo("bar")

        assertEquals(
            listOf("bar42", "bar1", "BAR42", "BAR2"),
            foos
        )
    }

    @Test
    fun tripleQuotedStrings() {
        val result: String = introduction.doTripleQuotedString()
        val expectedResult = """question = "life, the universe, and everything"
answer = 42""".trimMargin()

        assertEquals(expectedResult, result)
    }

    @Test
    fun stringTemplatesTest() {
        val date: String = "12 JUN 1991"
        val dateTwo: String = "June 12, 1991"
        val dateThree: String = "( 12, 1991"

        val expectedTrue: Boolean = introduction.doStringTemplates(date);
        val expectedFalse: Boolean = introduction.doStringTemplates(dateTwo);
        val expectedFalseTwo: Boolean = introduction.doStringTemplates(dateThree);

        assertEquals(true, expectedTrue);
        assertEquals(false, expectedFalse);
        assertEquals(false, expectedFalseTwo);
    }

    @Test
    fun testNullableTypes() {
        val personalInfo: Introduction.PersonalInfo = Introduction.PersonalInfo("Cool email!")
        val client: Introduction.Client = Introduction.Client(personalInfo)
        val myMailer: Mailer = Introduction.MyMailer();

        // Should not throw errors if no client nor message:
        introduction.sendMessageToClient(null, null, myMailer);

        // Should not throw errors if no client:
        introduction.sendMessageToClient(null, "Dude look like a lady!", myMailer);

        // Should not throw errors if no message:
        introduction.sendMessageToClient(client, null, myMailer);

        // Should not throw errors if all things exist:
        introduction.sendMessageToClient(client, "Howdy doodie!", myMailer);
    }

    @Test
    fun testNothingTypeNull() {
        // Should throw error if age is null
        try {
            introduction.checkAge(null);
            assertEquals(true, false);
        } catch (error: IllegalArgumentException) {
            val isRightError: Boolean = error is IllegalArgumentException;

            assertEquals(isRightError, true);
        }
    }

    @Test
    fun testNothingTypeLarge() {
        // Should throw error if age is not between 0 & 150, inclusive
        try {
            introduction.checkAge(151);
            assertEquals(true, false);
        } catch (error: IllegalArgumentException) {
            val isRightError: Boolean = error is IllegalArgumentException;

            assertEquals(isRightError, true);
        }
    }

    @Test
    fun testNothingTypeCorrectAge() {
        // Should not throw error if age is between 0 & 150, inclusive
        try {
            introduction.checkAge(150);
        } catch (error: IllegalArgumentException) {
            assertEquals(true, false)
        }
    }

    @Test
    fun testContainsEven() {
        val oneEven: Collection<Int> = listOf(2, 3, 5, 5, 9);
        val noEvens: Collection<Int> = listOf(3, 5, 9, 7);

        val actualOne: Boolean = introduction.containsEven(oneEven);
        val actualTwo: Boolean = introduction.containsEven(noEvens);

        assertEquals(true, actualOne);
        assertEquals(false, actualTwo);
    }
}