package com.mommoo.programmers.level1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class 완주하지_못한_선수 {
    public static void main(String[] args) {
        String[] participant = {"mislav", "stanko", "mislav", "ana"};
        String[] completion = {"stanko", "ana", "mislav"};

        System.out.println(new 완주하지_못한_선수().solution(participant, completion));
    }

    public String solution(String[] participant, String[] completion) {
        ParticipantFinder participantFinder = new ParticipantFinder(participant);

        Arrays.stream(completion)
              .forEach(participantFinder::removeParticipant);

        return participantFinder.findRemainParticipant();
    }

    private static class ParticipantFinder {
        private Map<String, Integer> participantCounter = new HashMap<>();

        private ParticipantFinder(String[] participants) {
            for (String participant : participants) {
                int savedParticipantCount = participantCounter.getOrDefault(participant, 0);
                participantCounter.put(participant, savedParticipantCount + 1);
            }
        }

        private void removeParticipant(String completion) {
            participantCounter.computeIfPresent(completion, (name, count) -> count - 1);
            Optional.ofNullable(participantCounter.get(completion))
                    .filter(count -> count == 0)
                    .ifPresent(count -> participantCounter.remove(completion));
        }

        private String findRemainParticipant() {
            return participantCounter.keySet().iterator().next();
        }
    }
}
