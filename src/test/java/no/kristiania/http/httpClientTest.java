package no.kristiania.http;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class httpClientTest {

    @Test
    void shouldDoSomething(){
        assertEquals("rune", httpClientMain.testMethod());

    }

}
