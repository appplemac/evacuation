package com.company.test;


import com.company.Headquarter;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


/**
 * Created by alexey on 15/10/14.
 */
public class HeadquarterTest {
    TestHelpers helpers = new TestHelpers();

    @Test
    public void basicGetters() {
        Headquarter hq = helpers.hqFactory(1);
        assertThat("id",
                hq.getIdent(), equalTo(0));
    }
}
