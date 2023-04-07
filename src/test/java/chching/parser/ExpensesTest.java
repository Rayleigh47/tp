package chching.parser;

import chching.record.Expense;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ExpensesTest {
    public static final String CATEGORY_VALUE = "transport";
    public static final String DESCRIPTION_VALUE = "public transport";
    public static final String DATE_STRING = "02-04-2023";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    public static final LocalDate DATE_VALUE = LocalDate.parse(DATE_STRING, FORMATTER);
    public static final String VALUE_STRING = "3.50";
    public static final double VALUE_VALUE = 3.50;
    public static final String CATEGORY_FIELD = "c";
    public static final String DESCRIPTION_FIELD = "de";
    public static final String DATE_FIELD = "da";
    public static final String VALUE_FIELD = "v";
    public static final String INDEX_FIELD = "in";
    public static final String INDEX_STRING = "1";
    public static final int INDEX_VALUE = 1;
    
    /**
     * JUnit test for parseDate method.
     * Tests if parseDate returns the correct LocalDate date if a normal date is input.
     */
    @Test
    public void parseDate_normalScenario_success() {
        try {
            assertEquals(DATE_VALUE, Expenses.parseDate(DATE_STRING));
        } catch (Exception e) {
            fail(); // test should not reach this line
        }
    }
    
    /**
     * JUnit test for parseDate method.
     * Tests if parseDate throws an exception if an invalid date is input.
     */
    @Test
    public void parseDate_invalidDate_exceptionThrown() {
        try {
            Expenses.parseDate("30-02-2023");
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("Date must be valid and have format: \"DD-MM-YYYY\"", e.getMessage());
        }
    }
    
    /**
     * JUnit test for parseDate method.
     * Tests if parseDate throws an exception if an invalid date format is input.
     */
    @Test
    public void parseDate_invalidDateFormat_exceptionThrown() {
        try {
            Expenses.parseDate("2023-02-30");
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("Date must be valid and have format: \"DD-MM-YYYY\"", e.getMessage());
        }
    }
    
    /**
     * JUnit test for parseDate method.
     * Tests if parseDate throws an exception if a future date is used.
     */
    @Test
    public void parseDate_futureDate_exceptionThrown() {
        try {
            Expenses.parseDate("02-04-2030");
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("Date cannot be in the future", e.getMessage());
        }
    }
    
    /**
     * JUnit test for parseExpense method.
     * Tests if parseExpense returns the correct Expense object if a normal input is used.
     */
    @Test
    public void parseExpense_normalScenario_success() {
        HashMap<String, String> input = new HashMap<>();
        input.put(CATEGORY_FIELD, CATEGORY_VALUE);
        input.put(DESCRIPTION_FIELD, DESCRIPTION_VALUE);
        input.put(DATE_FIELD, DATE_STRING);
        input.put(VALUE_FIELD, VALUE_STRING);
        
        try {
            Expense output = Expenses.parseExpense(input);
            assertEquals(CATEGORY_VALUE, output.getCategory());
            assertEquals(DESCRIPTION_VALUE, output.getDescription());
            assertEquals(DATE_VALUE, output.getDate());
            assertEquals(VALUE_VALUE, output.getValue());
        } catch (Exception e) {
            fail(); // test should not reach this line
        }
    }
    
    /**
     * JUnit test for parseExpense method.
     * Tests if parseExpense throws an exception if a category is not input.
     */
    @Test
    public void parseExpense_missingCategory_exceptionThrown() {
        HashMap<String, String> input = new HashMap<>();
        input.put(DESCRIPTION_FIELD, DESCRIPTION_VALUE);
        input.put(DATE_FIELD, DATE_STRING);
        input.put(VALUE_FIELD, VALUE_STRING);
        
        try {
            Expenses.parseExpense(input);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("Missing fields detected", e.getMessage());
        }
    }
    
    /**
     * JUnit test for parseExpense method.
     * Tests if parseExpense throws an exception if expense value >= 1,000,000.
     */
    @Test
    public void parseExpense_valueTooLarge_exceptionThrown() {
        HashMap<String, String> input = new HashMap<>();
        input.put(CATEGORY_FIELD, CATEGORY_VALUE);
        input.put(DESCRIPTION_FIELD, DESCRIPTION_VALUE);
        input.put(DATE_FIELD, DATE_STRING);
        input.put(VALUE_FIELD, "1000000.00");
        try {
            Expenses.parseExpense(input);
            fail(); // test should not reach this line
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertEquals("Expense value must be less than 1000000", e.getMessage());
        }
    }
    
    /**
     * JUnit test for parseExpense method.
     * Tests if parseExpense throws an exception if expense value <= 0.
     */
    @Test
    public void parseExpense_valueTooSmall_exceptionThrown() {
        HashMap<String, String> input = new HashMap<>();
        input.put(CATEGORY_FIELD, CATEGORY_VALUE);
        input.put(DESCRIPTION_FIELD, DESCRIPTION_VALUE);
        input.put(DATE_FIELD, DATE_STRING);
        input.put(VALUE_FIELD, "0");
        
        try {
            Expenses.parseExpense(input);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("Expense value must be greater than 0", e.getMessage());
        }
    }
    
    /**
     * JUnit test for parseExpense method.
     * Tests if parseExpense throws an exception if expense value is not a number.
     */
    @Test
    public void getIndex_normalScenario_success() {
        HashMap<String, String> input = new HashMap<>();
        input.put(INDEX_FIELD, INDEX_STRING);
        try {
            assertEquals(INDEX_VALUE, Expenses.getIndex(input));
        } catch (Exception e) {
            fail(); // test should not reach this line
        }
    }
    
    /**
     * JUnit test for parseExpense method.
     * Tests if parseExpense throws an exception if no index in input.
     */
    @Test
    public void getIndex_missingIndex_exceptionThrown() {
        HashMap<String, String> input = new HashMap<>();
        input.put(CATEGORY_FIELD, CATEGORY_VALUE);
        try {
            Expenses.getIndex(input);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("Index field not found", e.getMessage());
        }
    }
    
    /**
     * JUnit test for getIndex method.
     * Tests if getIndex throws an exception if index is not a number.
     */
    @Test
    public void getIndex_invalidIndex_exceptionThrown() {
        HashMap<String, String> input = new HashMap<>();
        input.put(INDEX_FIELD, "2 a");
        try {
            Expenses.getIndex(input);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("Index field not found", e.getMessage());
        }
    }
}