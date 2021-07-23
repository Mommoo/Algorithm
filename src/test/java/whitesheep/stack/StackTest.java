package whitesheep.stack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Java6Assertions.assertThat;

class StackTest {
    private static final Set<Character> OPENS = new HashSet<>(Arrays.asList('(', '[', '{'));
    private static final Set<Character> CLOSES = new HashSet<>(Arrays.asList(')', ']', '}'));
    private static final Map<Character, Character> MATCHED = new HashMap<>();

    private static final Set<Character> OPERATIONS = new HashSet<>(Arrays.asList('+', '-', '/', '*', '('));
    private static final Map<Character, Integer> OPERATION_SCORES = new HashMap<>();

    static {
        MATCHED.put('(', ')');
        MATCHED.put('[', ']');
        MATCHED.put('{', '}');

        OPERATION_SCORES.put('+', 0);
        OPERATION_SCORES.put('-', 0);
        OPERATION_SCORES.put('*', 1);
        OPERATION_SCORES.put('/', 1);
        OPERATION_SCORES.put('(', -1);
        OPERATION_SCORES.put(')', -1);
    }


    @DisplayName("괄호의 짝이 맞는지 검증하라.")
    @ParameterizedTest
    @CsvSource({"(()),true", "()()()(),true", "(()))(),false", "(()))(,false"})
    void test1(String expression, Boolean actualResult) {
        Boolean expectedResult = Boolean.TRUE;

        int openCount = 0;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(') {
                openCount++;
            } else {
                openCount--;
                if (openCount < 0) {
                    expectedResult = Boolean.FALSE;
                    break;
                }
            }
        }
        assertThat(expectedResult).isEqualTo(actualResult);
    }

    @DisplayName("다음 수식의 괄호의 짝이 맞는지 확인하는 코드를 작성하라")
    @ParameterizedTest
    @CsvSource({"[{1 + 2 * (2 + 2)} - (1 - 3)],true", "[{1 + 2 * (2 + 2)} - [1 - 3)],false"})
    void test2(String expression, Boolean actualResult) {
        Boolean expectedResult = Boolean.TRUE;

        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (OPENS.contains(c)) {
                stack.push(c);
            } else if (CLOSES.contains(c)) {
                boolean isValid = !stack.isEmpty() && Objects.equals(MATCHED.get(stack.pop()), c);
                if (!isValid) {
                    expectedResult = Boolean.FALSE;
                    break;
                }
            }
        }


        assertThat(expectedResult).isEqualTo(actualResult);
    }

    @DisplayName("다음 수식에서 여는 괄호의 위치가 어디에서 닫히는지 인덱스를 찾아라.")
    @ParameterizedTest
    @CsvSource({"[{1 + 2 * (2 + 2)} - (1 - 3)],1,17", "[{1 + 2 * (2 + 2)} - (1 - 3)],10,16"})
    void test3(String expression, Integer beginIndex, Integer actualIndex) {
        Integer expectedIndex = null;

        boolean beginTracking = false;
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < expression.length(); i++) {
            if (i == beginIndex) {
                beginTracking = true;
            }
            char c = expression.charAt(i);
            if (OPENS.contains(c)) {
                stack.push(c);
            } else if (CLOSES.contains(c)) {
                char open = stack.pop();

                if (beginTracking && open == expression.charAt(beginIndex) && Objects.equals(MATCHED.get(open), c)) {
                    expectedIndex = i;
                    break;
                }
            }
        }

        assertThat(expectedIndex).isEqualTo(actualIndex);
    }

    @DisplayName("괄호 몇개를 뒤집어야 정상적인 수식을 만들 수 있는지 개수를 찾아라. 만약 만들 수 없다면 -1 을 리턴한다.")
    @ParameterizedTest
    @CsvSource({"{{{}},-1", "{{{{}},1", "}}}}{}}},3", "{{{{,2", "{{}}}},1"})
    void test4(String expression, Integer actualCount) {
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (OPENS.contains(c)) {
                stack.push(c);
            } else if (CLOSES.contains(c)) {
                if (!stack.isEmpty() && Objects.equals(MATCHED.get(stack.peek()), c)) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }

        Integer expectedCount = stack.size() % 2 != 0 ? -1 : stack.size() / 2;

        assertThat(expectedCount).isEqualTo(actualCount);
    }

    /**
     * Note.
     * 우리가 흔히 표현하는 산술 연산식은 인픽스(infix)라 부른다. 컴퓨터는 산술 연산 우선순위 문제 때문에 프리픽스(prefix) 또는 포스트픽스(postfix)로 변환하여 계산한다.
     */
    @DisplayName("산술 연산식 인픽스가 주어질 때, 프리픽스로 변환하여라.")
    @ParameterizedTest
    @CsvSource({"1+2*3,+1*23", "1*5+2*7,+*15*27"})
    void test6(String expression, String actualPrefix) {
        StringBuilder builder = new StringBuilder();

        Deque<Character> stack = new LinkedList<>();
        for (int i = expression.length() - 1; i >= 0; i--) {
            char c = expression.charAt(i);
            if (OPERATIONS.contains(c)) {
                int currentOperationScore = OPERATION_SCORES.get(c);
                while (!stack.isEmpty() && currentOperationScore < OPERATION_SCORES.get(stack.peek())) {
                    builder.append(stack.pop());
                }

                stack.push(c);
                continue;
            }

            builder.append(c);
        }

        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }

        String expectedPrefix = builder.reverse().toString();

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
     * <p>
     * 1*2+3 예시 (string | stack)
     * 1|
     * 1|*
     * 12|*
     * 12*|+ : '+' 연산자는 스택에 들어있는 '*' 연산자보다 우선순위가 낮으므로, 스택에 있는것을 문자열에 출력해준다.
     * 12*3|+
     * 12*3+
     * <p>
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
    @CsvSource({"1+2*3,123*+", "1*5+2*7,15*27*+", "(1+2)*3,12+3*", "(1+2*5+7)*3,125*7++3*"})
    void test7(String expression, String actualPostfix) {
        StringBuilder builder = new StringBuilder();

        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == '(') {
                stack.push('(');
                continue;
            }

            if (c == ')') {
                while (!stack.isEmpty()) {
                    char pop = stack.pop();
                    if (pop == '(') {
                        break;
                    }
                    builder.append(pop);
                }
                continue;
            }


            if (OPERATIONS.contains(c)) {
                int curOperationScore = OPERATION_SCORES.get(c);
                while (!stack.isEmpty() && OPERATION_SCORES.get(stack.peek()) > curOperationScore) {
                    builder.append(stack.pop());
                }

                stack.push(c);
                continue;
            }

            builder.append(c);
        }

        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }
        String expectedPostfix = builder.toString();

        assertThat(expectedPostfix).isEqualTo(actualPostfix);
    }

    @DisplayName("포스트 픽스 계산하기. 사칙연산은 네가지만 사용합니다. (+,-,*,/)")
    @ParameterizedTest
    @CsvSource({"12+,3", "123+-5*,-20"})
    void test8(String expression, Integer actualResult) {
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (OPERATIONS.contains(c)) {
                int right = stack.pop();
                int left = stack.pop();
                stack.push(compute(left, right, c));
                continue;
            }

            stack.push(c - '0');
        }
        Integer expectedResult = stack.pop();

        assertThat(expectedResult).isEqualTo(actualResult);
    }

    private int compute(int num1, int num2, char operation) {
        switch (operation) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
        }

        return -1;
    }

    @DisplayName("스팬 찾기. (당일의 주가 보다 낮거나 같았던 연속적인 일 수")
    @ParameterizedTest
    @CsvSource(value = {"5,3,2,4,7,1_1,1,1,3,5,1", "2,3,4,5,6,7_1,2,3,4,5,6"}, delimiter = '_')
    void test9(String expression, String actualResult) {
        List<Integer> costs = Arrays.stream(expression.split(","))
                                    .map(Integer::parseInt)
                                    .collect(Collectors.toList());

        List<Integer> values = new ArrayList<>();
        Deque<Integer> stack = new LinkedList<>();

        for (int index = 0 ; index < costs.size(); index++) {
            if (stack.isEmpty()) {
                stack.push(index);
                values.add(index + 1);
                continue;
            }

            while (!stack.isEmpty() && costs.get(stack.peek()) <= costs.get(index)) {
                stack.pop();
            }

            int value = stack.isEmpty() ? index + 1 : index - stack.peek();
            values.add(value);

            stack.push(index);
        }

        String expectedResult = values.stream().map(String::valueOf).collect(Collectors.joining(","));
        assertThat(expectedResult).isEqualTo(actualResult);
    }

    @DisplayName("주어진 배열의 오른쪽에 처음으로 등장하는 현재 숫자보다 큰 수를 담고 있는 배열을 만드는 코드를 작성하라. 오른쪽에 현재 수보다 큰수가 없는 경우에는 -1.")
    @ParameterizedTest
    @CsvSource(value = {"1,1,2,3_2,2,3,-1", "10,4,2,30_30,30,30,-1", "82,7,15_-1,15,-1"}, delimiter = '_')
    void test10(String expression, String actualResult) {
        Integer[] elements = Arrays.stream(expression.split(",")).map(Integer::parseInt).toArray(Integer[]::new);
        Integer[] values = new Integer[elements.length];
        Deque<Integer> indexes = new LinkedList<>();

        for (int i = 0 ; i < elements.length; i++) {
            while (!indexes.isEmpty() && elements[indexes.peek()] < elements[i]) {
                values[indexes.pop()] = elements[i];
            }

            indexes.push(i);
        }

        while (!indexes.isEmpty()) {
            values[indexes.pop()] = -1;
        }

        String expectedResult = Arrays.stream(values).map(String::valueOf).collect(Collectors.joining(","));

        assertThat(expectedResult).isEqualTo(actualResult);
    }
}