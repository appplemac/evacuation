package com.company;

/**
 * Created by alexey on 15/10/14.
 */
public class TestRunner {
    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main(
                  "com.company.InitialSolutionTest"
                , "com.company.GroupTest"
                , "com.company.HelicopterTest"
                , "com.company.HeadquarterTest"
        );
    }
}
