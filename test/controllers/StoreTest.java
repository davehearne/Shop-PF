package controllers;

import models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

    private Product productBelow, productExact, productAbove, productZero;
    private Store storeWithProducts = new Store();
    private Store storeEmpty = new Store();

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

        storeWithProducts.add(productBelow);
        storeWithProducts.add(productExact);
        storeWithProducts.add(productAbove);
    }

    @AfterEach
    void tearDown() {
        productBelow = productExact = productAbove = productZero = null;
        storeEmpty = storeWithProducts = null;
    }

    @Nested
    class ArrayListCRUD{
        @Test
        void addingToAnArrayListThatHasProductsIsSuccessful() {
            assertEquals(3, storeWithProducts.numberOfProducts());
            assertTrue(storeWithProducts.add(productZero));
            assertEquals(4, storeWithProducts.numberOfProducts());
            assertEquals(productZero, storeWithProducts.findProduct(3));
        }

        @Test
        void addingToAnArrayListThatHasNoProductsIsSuccessful() {
            assertEquals(0, storeEmpty.numberOfProducts());
            assertTrue(storeEmpty.add(productZero));
            assertEquals(1, storeEmpty.numberOfProducts());
            assertEquals(productZero, storeEmpty.findProduct(0));
        }

        @Test
        void listProductsReturnsNoProductsStoredWhenArrayListIsEmpty() {
            assertEquals(0, storeEmpty.numberOfProducts());
            assertTrue(storeEmpty.listProducts().toLowerCase().contains("no products"));
        }

        @Test
        void listProductsReturnsProductsWhenArrayListHasProductsStored() {
            assertEquals(3, storeWithProducts.numberOfProducts());
            String productsString = storeWithProducts.listProducts();
            assertTrue(productsString.contains("Television 42Inches"));
            assertTrue(productsString.contains("Television 50 Inches"));
            assertTrue(productsString.contains("Television 60 Inches"));
        }


        @Test
        void updatingAProductThatExistsReturnsTrueAndUpdates(){
            //check product index 2 exists and check the contents
            assertEquals(productAbove, storeWithProducts.findProduct(2));
            assertEquals("Television 60 Inches", storeWithProducts.findProduct(2).getProductName());
            assertEquals(10000, storeWithProducts.findProduct(2).getProductCode());
            assertEquals(1000, storeWithProducts.findProduct(2).getUnitCost());
            assertFalse(storeWithProducts.findProduct(2).isInCurrentProductLine());

            //update product 2 with new information and ensure contents updated successfully
            assertTrue(storeWithProducts.updateProduct(2, "Updating Product", 2000, 19.99, true));
            assertEquals("Updating Product", storeWithProducts.findProduct(2).getProductName());
            assertEquals(2000, storeWithProducts.findProduct(2).getProductCode());
            assertEquals(19.99, storeWithProducts.findProduct(2).getUnitCost());
            assertTrue(storeWithProducts.findProduct(2).isInCurrentProductLine());
        }

        @Test
        void updatingAProductThatDoesNotExistReturnsFalse(){
            assertFalse(storeWithProducts.updateProduct(3, "Updating Product", 2, 19.99, false));
            assertFalse(storeWithProducts.updateProduct(-1, "Updating Product", 1002, 14.49, true));
            assertFalse(storeEmpty.updateProduct(0, "Updating Product", 1003, 199.99, false));
        }

        @Test
        void deletingAProductThatDoesNotExistReturnsNull(){
            assertNull(storeEmpty.deleteProduct(0));
            assertNull(storeWithProducts.deleteProduct(-1));
            assertNull(storeWithProducts.deleteProduct(3));
        }

        @Test
        void deletingAProductThatExistsDeletesAndReturnsDeletedObject(){
            assertEquals(3, storeWithProducts.numberOfProducts());
            assertEquals(productAbove, storeWithProducts.deleteProduct(2));
            assertEquals(2, storeWithProducts.numberOfProducts());
        }

    }

    @Nested
    class FindAndSearch {
        @Test
        void findProductReturnsProductWhenIndexIsValid() {
            assertEquals(3, storeWithProducts.numberOfProducts());
            assertEquals(productBelow, storeWithProducts.findProduct(0));
            assertEquals(productAbove, storeWithProducts.findProduct(2));
        }

        @Test
        void findProductReturnsNullWhenIndexIsInValid() {
            assertEquals(0, storeEmpty.numberOfProducts());
            assertNull(storeEmpty.findProduct(0));
            assertEquals(3, storeWithProducts.numberOfProducts());
            assertNull(storeWithProducts.findProduct(-1));
            assertNull(storeWithProducts.findProduct(3));
        }
    }
}