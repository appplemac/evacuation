package com.company.test;

/**
 * Created by alexey on 15/10/14.
 */
public class TestRunner {
    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main(
                  "com.company.test.InitialSolutionTest"
                , "com.company.test.GroupTest"
                , "com.company.test.HelicopterTest"
                , "com.company.test.HeadquarterTest"
        );
    }
}
