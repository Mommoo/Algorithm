package whitesheep.stack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Java6Assertions.assertThat;

class StackTest {

    @DisplayName("괄호의 짝이 맞는지 검증하라.")
    @ParameterizedTest
    @CsvSource({"(()),true", "()()()(),true", "(()))(),false", "(()))(,false"})
    void test1(String expression, Boolean actualResult) {
        Boolean expectedResult = null;

        assertThat(expectedResult).isEqualTo(actualResult);
    }

    @DisplayName("다음 수식의 괄호의 짝이 맞는지 확인하는 코드를 작성하라")
    @ParameterizedTest
    @CsvSource({"[{1 + 2 * (2 + 2)} - (1 - 3)],true", "[{1 + 2 * (2 + 2)} - [1 - 3)],false"})
    void test2(String expression, Boolean actualResult) {
        Boolean expectedResult = null;

        assertThat(expectedResult).isEqualTo(actualResult);
    }

    @DisplayName("다음 수식에서 여는 괄호의 위치가 어디에서 닫히는지 인덱스를 찾아라.")
    @ParameterizedTest
    @CsvSource({"[{1 + 2 * (2 + 2)} - (1 - 3)],1,16", "[{1 + 2 * (2 + 2)} - (1 - 3)],10,16"})
    void test3(String expression, Integer beginIndex, Integer actualIndex) {
        Integer expectedIndex = null;

        assertThat(expectedIndex).isEqualTo(actualIndex);
    }

    @DisplayName("괄호 몇개를 뒤집어야 정상적인 수식을 만들 수 있는지 개수를 찾아라. 만약 만들 수 없다면 -1 을 리턴한다.")
    @ParameterizedTest
    @CsvSource({"{{{}},-1", "{{{{}},1", "}}}}{}}},3", "{{{{,2"})
    void test4(String expression, Integer actualCount) {
        Integer expectedCount = null;

        assertThat(expectedCount).isEqualTo(actualCount);
    }

    @DisplayName("주어진 수식에 불필요한 괄호가 있는지 판단하라.")
    @ParameterizedTest
    @CsvSource({"1+(2*3),false", "(1+2)+3,true", "(1*2)+3,false", "1+(1*2)+3,false", "((1+2))+5,true", "(1)+3*4,true"})
    void test5(String expression, Boolean actualResult) {
        Boolean expectedResult = null;

        assertThat(expectedResult).isEqualTo(actualResult);
    }

    /**
     * Note.
     * 우리가 흔히 표현하는 산술 연산식은 인픽스(infix)라 부른다. 컴퓨터는 산술 연산 우선순위 문제 때문에 프리픽스(prefix) 또는 포스트픽스(postfix)로 변환하여 계산한다.
     * */
    @DisplayName("산술 연산식 인픽스가 주어질 때, 프리픽스로 변환하여라.")
    @ParameterizedTest
    @CsvSource({"1+2*3,+*231", "1*5+2*7,+**1527"})
    void test6(String expression, String actualPrefix) {
        String expectedPrefix = null;

        assertThat(expectedPrefix).isEqualTo(actualPrefix);
    }

    /**
     * Note.
     * 1+2*3 예시 (string | stack)
     * 1|  : 숫자를 만났으므로, 그냥 문자열로 출력한다.
     * 1|+ : 연산자를 만났을 때, 스택에 들어 있는 연산자 우선순위를 계산한다. 스택은 비어있으니 + 를 삽입한다.
     * 12|+ : 숫자를 만났으므로, 문자열을 출력한다.
     * 12|+* : 연산자를 만났는데, 스택에 들어 있는 '+' 연산자보다 우선순위가 높으므로 스택에 '*'를 삽입한다.
     * 123|+* : 숫자를 만났으므르, 문자열을 출력한다.
     * 123*+| : 탐색이 종료됬으므로, 스택에 있던 연산자를 pop으로 꺼내와 붙여준다.
     *
     * 1*2+3 예시 (string | stack)
     * 1|
     * 1|*
     * 12|*
     * 12*|+ : '+' 연산자는 스택에 들어있는 '*' 연산자보다 우선순위가 낮으므로, 스택에 있는것을 문자열에 출력해준다.
     * 12*3|+
     * 12*3+
     *
     * 5*3*2+3
     * 5|
     * 5|*
     * 53|*
     * 53|**
     * 532|**
     * 532**|+
     * 532**3|+
     * 532**3+
     */
    @DisplayName("산술 연산식 인픽스가 주어질 때, 포스트픽스로 변환하여라.")
    @ParameterizedTest
    @CsvSource({"1+2*3,123*+", "1*5+2*7,15*27*+", "(1+2)*3,12+3*"})
    void test7(String expression, String actualPostfix) {
        String expectedPostfix = null;

        assertThat(expectedPostfix).isEqualTo(actualPostfix);
    }

    @DisplayName("포스트 픽스 계산하기. 사칙연산은 네가지만 사용합니다. (+,-,*,/)")
    @ParameterizedTest
    @CsvSource({"12+,3", "123+-5*,-20"})
    void test8(String expression, Integer actualResult) {
        Integer expectedResult = null;

        assertThat(expectedResult).isEqualTo(actualResult);
    }

    @DisplayName("스팬 찾기. (당일의 주가 보다 낮거나 같았던 연속적인 일 수")
    @ParameterizedTest
    @CsvSource(value = {"5,3,2,4,7,1_1,1,1,3,5,1", "2,3,4,5,6,7_1,2,3,4,5,6"}, delimiter = '_')
    void test9(String expression, String actualResult) {
        String expectedResult = null;

        assertThat(expectedResult).isEqualTo(actualResult);
    }

    @DisplayName("주어진 배열의 오른쪽에 처음으로 등장하는 현재 숫자보다 큰 수를 담고 있는 배열을 만드는 코드를 작성하라. 오른쪽에 현재 수보다 큰수가 없는 경우에는 -1.")
    @ParameterizedTest
    @CsvSource(value = {"1,1,2,3_2,2,3,-1", "10,4,2,30_30,30,30,-1", "82,7,15_-1,15,-1"}, delimiter = '_')
    void test10(String expression, String actualResult) {
        String expectedResult = null;

        assertThat(expectedResult).isEqualTo(actualResult);
    }
}