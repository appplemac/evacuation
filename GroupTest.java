package com.company;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by alexey on 15/10/14.
 */
public class GroupTest {
    @Test
    public void basicAttributesGetters() {
        Group group = new Group(11, 2, 10, 10);
        assertThat("people count", group.getNumPeople(), equalTo(11));
        assertThat("priority id", group.getPriority(), equalTo(2));
    }

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("com.company.GroupTest");
    }
}
