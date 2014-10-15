package com.company.test;


import com.company.Headquarter;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


/**
 * Created by alexey on 15/10/14.
 */
public class HeadquarterTest {
    public Headquarter genericHQ() {
        return new Headquarter(1, 0, 0);
    }

    @Test
    public void basicGetters() {
        Headquarter hq = genericHQ();
        assertThat("id",
                hq.getIdent(), equalTo(1));
    }
}
