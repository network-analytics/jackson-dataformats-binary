package com.fasterxml.jackson.dataformat.ion.failing;

import java.net.URL;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.ion.IonObjectMapper;

import static org.junit.Assert.*;

public class UncaughtException303Test
{
    private final IonObjectMapper MAPPER = IonObjectMapper.builder().build();

    // [dataformats-binary#303]
    @Test
    public void testUncaughtException303() throws Exception
    {
        URL poc = getClass().getResource("/data/issue-303.ion");
        try {
            MAPPER.readTree(poc);
            fail("Should not pass with invalid content");
        } catch (JsonProcessingException e) {
            // !!! TODO: change to match what we actually expect
            verifyException(e, "MATCH MESSAGE");
        }
    }

    void verifyException(Throwable e, String match)
    {
        String msg = e.getMessage();
        String lmsg = (msg == null) ? "" : msg.toLowerCase();
        if (!lmsg.contains(match.toLowerCase())) {
            fail("Expected an exception with a substrings ("+match+"): got one with message \""+msg+"\"");
        }
    }
}
