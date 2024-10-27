package com.inditex.app.constanst;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ErrorMessagesTest {

    @Test
    void testErrorMessagesConstants() {
        assertEquals("Error: No price found for the given parameters", ErrorMessages.PRICE_NOT_FOUND);
        assertEquals("Error interno del servidor: ", ErrorMessages.INTERNAL_SERVER_ERROR);
    }
}
