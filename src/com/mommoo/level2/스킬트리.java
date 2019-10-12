package com.mommoo.level2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class 스킬트리 {
    public static void main(String[] args) {
        System.out.println(new 스킬트리().solution("CBD", new String[]{"BACDE", "CBADF", "AECB", "BDA"}));
    }

    private static boolean isValidSkillTree(String skill, String skillTree) {
        SkillValidator skillValidator = new SkillValidator(skill);
        for (int index = 0; index < skillTree.length(); index++) {
            char cSkill = skillTree.charAt(index);
            if (skillValidator.isInValid(cSkill)) {
                return false;
            }
        }

        return true;
    }

    public int solution(String skill, String[] skill_trees) {
        return (int) Arrays.stream(skill_trees)
                           .filter(skillTree -> isValidSkillTree(skill, skillTree))
                           .count();
    }

    private static class SkillValidator {
        private Queue<Character> skillQueue = new LinkedList<>();
        private Set<Character> skillFinder = new HashSet<>();

        public SkillValidator(String skill) {
            for (int index = 0; index < skill.length(); index++) {
                Character cSkill = skill.charAt(index);
                skillQueue.offer(cSkill);
                skillFinder.add(cSkill);
            }
        }

        public boolean isInValid(Character cSkill) {
            if (!skillFinder.contains(cSkill)) {
                return false;
            }

            Character requiredSkill = skillQueue.poll();
            return !requiredSkill.equals(cSkill);
        }
    }
}
