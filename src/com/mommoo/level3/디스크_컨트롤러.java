package com.mommoo.level3;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class 디스크_컨트롤러 {
    public static void main(String[] args) {
        System.out.println(new 디스크_컨트롤러().solution(new int[][]{
                new int[]{0, 3},
                new int[]{1, 9},
                new int[]{2, 6}
        }));

        System.out.println(new 디스크_컨트롤러().solution(new int[][]{
                new int[]{0, 3},
                new int[]{5, 9},
                new int[]{6, 6}
        }));
    }

    public int solution(int[][] jobs) {
        JobQueue jobQueue = new JobQueue();
        jobQueue.addJobs(Arrays.stream(jobs)
                               .map(Job::from)
                               .collect(Collectors.toList()));

        int totalTakenTime = 0;
        int currentTime = 0;
        while (jobQueue.hasMore()) {
            Job nextJob = jobQueue.pop(currentTime);
            totalTakenTime += nextJob.computeTakenTime(currentTime);
            currentTime = nextJob.computeEndTime(currentTime);
        }

        int size = jobs.length;
        return totalTakenTime / size;
    }

    private static class JobQueue {
        private LinkedList<Job> jobs = new LinkedList<>();

        public void addJobs(List<Job> jobs) {
            this.jobs.addAll(jobs);
            Collections.sort(this.jobs);
        }

        public Job pop(int beginTime) {
            int nextJobIndex = findNextJobIndex(beginTime);
            return jobs.remove(nextJobIndex);
        }

        public boolean hasMore() {
            return !this.jobs.isEmpty();
        }

        private int findNextJobIndex(int beginTime) {
            int nextJobIdx = 0;
            Job nextJob = this.jobs.getFirst();

            int curIdx = -1;
            for (Job job : jobs) {
                curIdx++;
                if (!job.isNeedToWait(beginTime)) {
                    break;
                }

                if (job.isHighPriorityThan(nextJob)) {
                    nextJob = job;
                    nextJobIdx = curIdx;
                }
            }

            return nextJobIdx;
        }
    }

    private static class Job implements Comparable<Job> {
        private final int enterTime;
        private final int progressTime;

        private Job(int enterTime, int progressTime) {
            this.enterTime = enterTime;
            this.progressTime = progressTime;
        }

        private static Job from(int[] jobData) {
            return new Job(jobData[0], jobData[1]);
        }

        public boolean isNeedToWait(int beginTime) {
            return this.enterTime < beginTime;
        }

        public int computeTakenTime(int beginTime) {
            int waitTime = Math.max(0, beginTime - enterTime);
            return waitTime + progressTime;
        }

        public int computeEndTime(int beginTime) {
            if (beginTime > enterTime) {
                return beginTime + progressTime;
            } else {
                return enterTime + progressTime;
            }
        }

        public boolean isHighPriorityThan(Job job) {
            if (this.progressTime == job.progressTime) {
                return this.enterTime < job.enterTime;
            } else {
                return this.progressTime < job.progressTime;
            }
        }

        @Override
        public int compareTo(Job o) {
            if (this.enterTime == o.enterTime) {
                return this.progressTime - o.progressTime;
            }

            return this.enterTime - o.enterTime;
        }
    }
}
