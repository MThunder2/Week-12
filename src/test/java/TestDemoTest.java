import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.junit.jupiter.params.provider.Arguments.arguments;


import org.junit.jupiter.params.provider.MethodSource;

class TestDemoTest {

	private TestDemo testDemo;

	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource("TestDemoTest#argumentsForAddPositive")
	void assertTheTwoPositiveNUmversAreAddedCorrectly(int a, int b, int expected, Boolean expectException) {

		if (!expectException) {
			assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
		} else {

			assertThatThrownBy(() -> 
				testDemo.addPositive(a, b))
					.isInstanceOf(IllegalArgumentException.class);
		}
		
		}

	static Stream<Arguments> argumentsForAddPositive() {
			//@Formatter:off
		return Stream.of(
				
				arguments(-6, -4, -2, true),
				arguments(-8, 2, 4, true),
				arguments(-10, 0, 5, true),
				arguments(0, 0, 0, true),
				arguments(2, 4, 6, false)
				);
		
			//@Formatter:on
		}
	
	
	@Test
	void assertThatNumberSquaredIsCorrect() {
		TestDemo mockDemo = spy(testDemo);
		doReturn(5).when(mockDemo).getRandomInt();
		
		int fiveSquared = mockDemo.randomNumberSquared();
		
		assertThat(fiveSquared).isEqualTo(25);
	}
}
