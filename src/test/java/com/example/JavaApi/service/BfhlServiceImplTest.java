package com.example.JavaApi.service;

import com.example.JavaApi.dto.BfhlRequest;
import com.example.JavaApi.dto.BfhlResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BfhlServiceImplTest {

    private BfhlServiceImpl bfhlService;

    @BeforeEach
    void setUp() {
        bfhlService = new BfhlServiceImpl();
    }

    @Test
    @DisplayName("Test Example A: Mixed data with special characters")
    void testExampleA() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("a", "1", "334", "4", "R", "$"));
        BfhlResponse response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertNotNull(response.getUserId());
        assertNotNull(response.getEmail());
        assertNotNull(response.getRollNumber());
        
        assertEquals(Arrays.asList("1"), response.getOddNumbers());
        assertTrue(response.getEvenNumbers().containsAll(Arrays.asList("334", "4")));
        assertTrue(response.getAlphabets().containsAll(Arrays.asList("A", "R")));
        assertEquals(Arrays.asList("$"), response.getSpecialCharacters());
        assertEquals("339", response.getSum());
        assertEquals("Ra", response.getConcatString());
    }

    @Test
    @DisplayName("Test Example B: Multiple special characters and numbers")
    void testExampleB() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("2", "a", "y", "4", "&", "-", "*", "5", "92", "b"));
        BfhlResponse response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(Arrays.asList("5"), response.getOddNumbers());
        assertTrue(response.getEvenNumbers().containsAll(Arrays.asList("2", "4", "92")));
        assertTrue(response.getAlphabets().containsAll(Arrays.asList("A", "Y", "B")));
        assertTrue(response.getSpecialCharacters().containsAll(Arrays.asList("&", "-", "*")));
        assertEquals("103", response.getSum());
        assertEquals("ByA", response.getConcatString());
    }

    @Test
    @DisplayName("Test Example C: Only alphabetic strings")
    void testExampleC() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("A", "ABCD", "DOE"));
        BfhlResponse response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().containsAll(Arrays.asList("A", "ABCD", "DOE")));
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("EoDdCbAa", response.getConcatString());
    }

    @Test
    @DisplayName("Test empty data array")
    void testEmptyData() {
        BfhlRequest request = new BfhlRequest(Arrays.asList());
        BfhlResponse response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    @DisplayName("Test null data handling")
    void testNullData() {
        BfhlRequest request = new BfhlRequest(null);
        BfhlResponse response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertNotNull(response.getOddNumbers());
        assertNotNull(response.getEvenNumbers());
        assertNotNull(response.getAlphabets());
        assertNotNull(response.getSpecialCharacters());
    }

    @Test
    @DisplayName("Test large numbers")
    void testLargeNumbers() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("1000", "2001", "3000"));
        BfhlResponse response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(Arrays.asList("2001"), response.getOddNumbers());
        assertTrue(response.getEvenNumbers().containsAll(Arrays.asList("1000", "3000")));
        assertEquals("6001", response.getSum());
    }

    @Test
    @DisplayName("Test only special characters")
    void testOnlySpecialCharacters() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("@", "#", "%", "^"));
        BfhlResponse response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getSpecialCharacters().containsAll(Arrays.asList("@", "#", "%", "^")));
        assertTrue(response.getAlphabets().isEmpty());
        assertEquals("0", response.getSum());
    }

    @Test
    @DisplayName("Test alternating caps in reverse concatenation")
    void testConcatenationLogic() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("a", "b", "c"));
        BfhlResponse response = bfhlService.processData(request);

        // Reverse: c, b, a -> alternating: C, b, A -> "CbA"
        assertEquals("CbA", response.getConcatString());
    }

    @Test
    @DisplayName("Test mixed case alphabets")
    void testMixedCaseAlphabets() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("Hello", "WoRlD"));
        BfhlResponse response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getAlphabets().contains("HELLO"));
        assertTrue(response.getAlphabets().contains("WORLD"));
    }
}
