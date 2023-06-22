package com.example.designpattern.creational_patterns.prototype._02_after;

public class App {
    public static void main(String[] args) throws CloneNotSupportedException {
        GithubRepository repository = new GithubRepository();
        repository.setUser("whiteship");
        repository.setName("live-study");

        GithubIssue githubIssue = new GithubIssue(repository);
        githubIssue.setId(1);
        githubIssue.setTitle("1주차 과제: JVM은 무엇이며 자바 코드는 어떻게 실행하는 것인가.");

        GithubIssue clone = (GithubIssue) githubIssue.clone();

        System.out.println(githubIssue != clone);
        System.out.println(githubIssue.equals(clone));
        System.out.println(githubIssue.getRepository() == repository);
        System.out.println(githubIssue.getRepository().equals(repository));

        clone.setId(2);
        clone.setTitle("2주차 과제");

        String url = githubIssue.getUrl();
        System.out.println(url);
    }
}
