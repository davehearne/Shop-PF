package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import utils.Utilities;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product productBelow, productExact, productAbove, productZero;

    @BeforeEach
    void setUp() {
        //name, 19 chars, code 999, unitCost 1, inCurrentProductLine true.
        productBelow = new Product("Television 42Inches", 999, 1, true);
        //name, 20 chars, code 1000, unitCost 999, inCurrentProductLine true.
        productExact = new Product("Television 50 Inches", 1000, 999, true);
        //name, 21 chars, code 10000, unitCost 1000, inCurrentProductLine false.
        productAbove = new Product("Television 60 Inches.", 10000, 1000, false);
        //name, 0 chars, code 9999, unitCost 0, inCurrentProductLine false.
        productZero = new Product("", 9999, 0, false);
    }

    @AfterEach
    void tearDown() {
        productBelow = productExact = productAbove = productZero = null;
    }

    @Nested
    class Getters {
        @Test
        void getProductName() {
            assertEquals("Television 42Inches", productBelow.getProductName());
            assertEquals("Television 50 Inches", productExact.getProductName());
            assertEquals("Television 60 Inches", productAbove.getProductName());
            assertEquals("", productZero.getProductName());
        }

        @Test
        void getUnitCost() {
            assertEquals(1, productBelow.getUnitCost());
            assertEquals(999, productExact.getUnitCost());
            assertEquals(1000, productAbove.getUnitCost());
            assertEquals(0, productZero.getUnitCost());
        }

        @Test
        void getProductCode() {
            assertEquals(999, productBelow.getProductCode());
            assertEquals(1000, productExact.getProductCode());
            assertEquals(10000, productAbove.getProductCode());
            assertEquals(9999, productZero.getProductCode());
        }

        @Test
        void isInCurrentProductLine() {
            assertTrue(productBelow.isInCurrentProductLine());
            assertTrue(productExact.isInCurrentProductLine());
            assertFalse(productAbove.isInCurrentProductLine());
            assertFalse(productZero.isInCurrentProductLine());
        }
    }

    @Nested
    class Setters {
        @Test
        void setProductName() {
            assertEquals("Television 42Inches", productBelow.getProductName());

            productBelow.setProductName("iPhone 13 Charcoal.");   //19 chars - update performed
            assertEquals("iPhone 13 Charcoal.", productBelow.getProductName());

            productBelow.setProductName("iPhone 12 - Charcoal");   //20 chars - update performed
            assertEquals("iPhone 12 - Charcoal", productBelow.getProductName());

            productBelow.setProductName("iPhone 11: - Charcoal");   //21 chars - update ignored
            assertEquals("iPhone 12 - Charcoal", productBelow.getProductName());
        }

        @Test
        void setUnitCost() {
            assertEquals(999, productExact.getUnitCost());
            productExact.setUnitCost(99.99);  //no validation performed
            assertEquals(99.99, productExact.getUnitCost());
        }

        @Test
        void setProductCode() {

            productAbove.setProductCode(999);  //update ignored
            assertEquals(999, productAbove.getProductCode());

            productAbove.setProductCode(1000); //update performed
            assertEquals(1000, productAbove.getProductCode());

            productAbove.setProductCode(9999);  //update performed
            assertEquals(9999, productAbove.getProductCode());

            productAbove.setProductCode(10000);  //update ignored
            assertEquals(10000, productAbove.getProductCode());
        }

        @Test
        void setInCurrentProductLine() {
            assertTrue(productExact.isInCurrentProductLine());
            productExact.setInCurrentProductLine(false); //no validation performed
            assertFalse(productExact.isInCurrentProductLine());

            assertFalse(productAbove.isInCurrentProductLine());
            productAbove.setInCurrentProductLine(true); //no validation performed
            assertTrue(productAbove.isInCurrentProductLine());
        }

    }

    @Test
    void testToString() {
        String toStringContents = productExact.toString();
        assertTrue(toStringContents.contains("Product description: " + productExact.getProductName()));
        assertTrue(toStringContents.contains("product code: " + productExact.getProductCode()));
        assertTrue(toStringContents.contains("unit cost: " + productExact.getUnitCost()));
        assertTrue(toStringContents.contains(" currently in product line: true"));
    }
}