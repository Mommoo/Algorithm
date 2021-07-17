package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BOJ3054 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FrameBoard frameBoard = FrameBoard.from(reader.readLine());
        System.out.println(frameBoard.render());
    }

    public static class FrameBoard {
        private final List<FrameBuilder> frameBuilders;

        private FrameBoard(List<FrameBuilder> frameBuilders) {
            this.frameBuilders = frameBuilders;
        }

        public static FrameBoard from(String string) {
            return new FrameBoard(IntStream.rangeClosed(1, string.length())
                                           .mapToObj(order -> new FrameBuilder(order % 3 != 0, string.charAt(order - 1)))
                                           .collect(Collectors.toList()));
        }

        public String render() {
            StringBuilder stringBuilder = new StringBuilder();
            StringBuilder frameLine = new StringBuilder();
            for (int raw = 0 ; raw < 5; raw++) {
                frameLine.append(frameBuilders.get(0).getLine(raw));

                for (int col = 1 ; col < frameBuilders.size(); col++) {
                    if (frameLine.charAt(frameLine.length() - 1) == '#') {
                        frameLine.setLength(frameLine.length() - 1);
                        frameLine.append(frameBuilders.get(col).getLine(raw));
                    }else {
                        frameLine.append(frameBuilders.get(col).getLine(raw).substring(1));
                    }
                }

                stringBuilder
                        .append(frameLine)
                        .append(System.lineSeparator());

                frameLine.setLength(0);
            }

            return stringBuilder.toString();
        }
    }

    public static class FrameBuilder {
        private final List<String> lines;

        public FrameBuilder(boolean isPeterPanMode, char letter) {
            this.lines = createFrameLines(isPeterPanMode ? "#" : "*", letter);
        }

        private static List<String> createFrameLines(String decorationLetter, char letter) {
            List<String> lines = new ArrayList<>();
            lines.add(String.format("..%s..", decorationLetter));
            lines.add(String.format(".%s.%s.", decorationLetter, decorationLetter));
            lines.add(String.format("%s.%s.%s", decorationLetter, letter, decorationLetter));
            lines.add(String.format(".%s.%s.", decorationLetter, decorationLetter));
            lines.add(String.format("..%s..", decorationLetter));
            return Collections.unmodifiableList(lines);
        }

        public String getLine(int lineNumber) {
            return lines.get(lineNumber);
        }
    }
}
