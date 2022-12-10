package main;

import controllers.Store;
import models.Product;
import utils.LanguageUtility;
import utils.ScannerInput;
import utils.Utilities;

import java.util.Scanner;

public class Driver{
    private Scanner input = new Scanner(System.in);
    private Store store;

    /**
     * The main method is the starting point for the program.
     * When started, the main method calls the addProduct() method
     * followed by the printProduct() method.
     *
     * @param args  Any arguments for the main method
     */
    public static void main(String[] args) {
        new Driver();
	}
    public Driver() {
        store = new Store();
        runMenu();
    }

    //gather the product data from the user and create a new product object.
    private void addProduct(){
        input.nextLine();  //dummy read of String to clear the buffer - bug in Scanner class.

        System.out.print("Enter the Product Name:  ");
        String productName = input.nextLine();
        System.out.print("Enter the Product Code:  ");
        int productCode = input.nextInt();
        System.out.print("Enter the Unit Cost:  ");
        double unitCost = input.nextDouble();

        //Ask the user to type in either a Y or an N.  This is then
        //converted to either a True or a False (i.e. a boolean value).
        System.out.print("Is this product in your current line (y/n): ");
        char currentProduct = input.next().charAt(0);
        boolean inCurrentProductLine = Utilities.YNtoBoolean(currentProduct);
        boolean isAdded = store.add(new Product(productName, productCode, unitCost, inCurrentProductLine));
        if (isAdded){
            System.out.println("Product Added Successfully");
        }
        else{
            System.out.println("No Product Added");
        }
    }
    
    //print the product (the toString method is automatically called).
    private void printProducts(){
        System.out.println(store.listProducts());
    }

    /**
     * create menu options
     * @return int option
     */
    private int mainMenu() {
        return ScannerInput.readNextInt("""
                ------------------------------------------------------------------
                |                            Shop Menu                           |
                ------------------------------------------------------------------
                | PRODUCT MENU                                                   | 
                |   1) Add a product                                             |
                |   2) List the Products                                         |
                |   3) Update a product                                          | 
                |   4) Delete a product                                          | 
                ------------------------------------------------------------------
                | PRODUCT DESCRIPTION MENU                                       | 
                |   5) Add a product description                                 |
                |   6) List product description(s)                               |
                |   7) Update a product description                              | 
                |   8) Delete a product description                              | 
                ------------------------------------------------------------------
                | REPORT MENU                                                    | 
                |   10) List the current products                                |
                |   11) Display average product unit cost                        |
                |   12) Display cheapest product                                 |
                |   13) List products that are more expensive than a given price |
                ------------------------------------------------------------------
                | SEARCH AND SORT                                                |
                |   15) Search By Name                                           |
                |   16) Sort Alphabetically                                      |
                ------------------------------------------------------------------
                |   20)  Save products to products.xml                           |  
                |   21) Load products from products.xml                          |  
                |   0)  Exit                                                     |  
                ------------------------------------------------------------------
                ==>>  """);
    }

    /**
     * run Menu
     */
    private void runMenu(){
        int option = mainMenu();

        while (option != 0){

            switch (option){
                case 1 -> addProduct();
                case 2 -> printProducts();
                case 3 -> updateProduct();
                case 4 -> deleteProduct();
                case 5 -> addDescriptionToProduct();
                case 6 -> listProductDescriptions();
                case 7 -> updateDescriptionInProduct();
                case 8 -> deleteDescriptionFromProduct();
                case 10 -> printCurrentProducts();
                case 11 -> printAverageProductPrice();
                case 12 -> printCheapestProduct();
                case 13 -> printProductsAboveAPrice();
                case 15 -> searchProductByName();
                case 16 -> sortByProductName();
                case 20 -> saveProducts();
                case 21 -> loadProducts();
                default -> System.out.println("Invalid option entered: " + option);
            }

            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress enter key to continue...");
            input.nextLine();
            input.nextLine(); //second read is required - bug in Scanner class; a String read is ignored straight after reading an int.

            //display the main menu again
            option = mainMenu();
        }

        //the user chose option 0, so exit the program
        System.out.println("Exiting...bye");
        System.exit(0);
    }

    //------------------------------------
    //PRODUCT DESCRIPTION MENU
    //------------------------------------
    private void addDescriptionToProduct() {
        Product product = askUserToSelectProduct();
        if (product != null){
            String language = readValidLanguage();
            String description = ScannerInput.readNextLine("\t Description: ");
            if (product.addDescription(language, description))
                System.out.println("Add Successful!");
            else
                System.out.println("Add NOT Successful");
        }
    }

    private void listProductDescriptions(){
        Product product = askUserToSelectProduct();
        if (product != null) {
            System.out.println(product.listDescriptions());
        }
    }

    private void updateDescriptionInProduct() {
        Product product = askUserToSelectProduct();
        if (product != null) {
            System.out.println(product.listDescriptions());
            if (product.numberOfDescriptions() > 0) {
                //ask user to enter a language.
                String language = readValidLanguage();
                if (product.isAlreadyAddedLanguage(language)) {
                    //if language has been added -> ask for new desc and update it.
                    String newDesc = ScannerInput.readNextLine("Enter a new description: ");
                    if (product.updateDescription(language, newDesc)) {
                        System.out.println("Description updated");
                    } else {
                        System.out.println("Description NOT updated");
                    }
                } else {
                    // if language hasn't been added -> update not successful
                    System.out.println("No descriptions for this language");
                }
            }
        }
    }

    private void deleteDescriptionFromProduct() {
        Product product = askUserToSelectProduct();
        if (product != null) {
            System.out.println(product.listDescriptions());
            if (product.numberOfDescriptions() > 0) {
                String language = readValidLanguage();
                if (product.deleteDescription(language) != null) {
                    System.out.println("Description deleted");
                } else {
                    System.out.println("Description not found");
                }
            }
        }
    }

    //print out a list of all current products i.e. that are in the current product line.
    private void printCurrentProducts(){
        System.out.println("List of CURRENT Products are:");
        System.out.println(store.listCurrentProducts());
    }

    //print out the average product price for all products stored in the array
    private void printAverageProductPrice(){
        double averagePrice = store.averageProductPrice();
        if (averagePrice != -1){
            System.out.println("The average product price is: " + averagePrice);
        }
        else{
            System.out.println("There are no products in the store.");
        }
    }

    //print out the product name that is the cheapest of those stored in the array
    private void printCheapestProduct(){
        Product cheapestProduct = store.cheapestProduct();
        if (cheapestProduct != null) {
            System.out.println("The cheapest product is:  " + cheapestProduct.getProductName());
        }
        else{
            System.out.println("There are no products in the store.");
        }
    }

    //ask the user to enter a price and print out all products costing that price or more.
    private void printProductsAboveAPrice(){
        System.out.print("View the products costing more than this price:  ");
        double price = input.nextDouble();
        System.out.println(store.listProductsAboveAPrice(price));
    }

    private void deleteProduct(){
        printProducts();
        if(store.numberOfProducts() > 0){
            //only ask the user to choose the product to delete if products exist
            int indexToDelete = ScannerInput.readNextInt("Enter the index of the product to delete ==> ");
            //pass the index of the product to controllers.Store for deleting and check for success.
            Product productToDelete = store.deleteProduct(indexToDelete);
            if (productToDelete != null){
                System.out.println("Delete Successful! Deleted product: " + productToDelete.getProductName());
            }
            else{
                System.out.println("Delete NOT Successful");
            }
        }
    }

    private void updateProduct(){
        printProducts();
        if (store.numberOfProducts() > 0){
            //only ask the user to choose the product to update if products exist
            int indexToUpdate = ScannerInput.readNextInt("Enter the index of the product to update ==> ");
            if (store.isValidIndex(indexToUpdate)){
                String productName = ScannerInput.readNextLine("Enter the Product Name:  ");
                int productCode = ScannerInput.readNextInt("Enter the Product Code:  ");
                double unitCost = ScannerInput.readNextDouble("Enter the Unit Cost:  ");

                //Ask the user to type in either a Y or an N.  This is then
                //converted to either a True or a False (i.e. a boolean value).
                char currentProduct = ScannerInput.readNextChar("Is this product in your current line (y/n): ");
                boolean inCurrentProductLine = Utilities.YNtoBoolean(currentProduct);

                //pass the index of the product and the new product details to controllers.Store for updating and check for success.
                if (store.updateProduct(indexToUpdate, productName, productCode, unitCost, inCurrentProductLine)){
                    System.out.println("Update Successful");
                }
                else{
                    System.out.println("Update NOT Successful");
                }
            }
            else{
                System.out.println("There are no products for this index number");
            }
        }
    }
    private void sortByProductName() {
        store.sortProductsByNameAscending();
        System.out.println(store.listProducts());
    }
    /**
     * save products to products.xml
     */
    private void saveProducts() {
        try {
            store.save();
            System.out.println("Products saved!");
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }

    /**
     * load products from products.xml
     */
    private void loadProducts() {
        try {
            store.load();
            System.out.println("Products successfully loaded!");
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
    }
    //------------------------------------
    // HELPER METHODS
    // ------------------------------------
    private Product askUserToSelectProduct(){
        printProducts();
        if (store.numberOfProducts() > 0) {
            Product product = store.findProduct(ScannerInput.readNextInt("Enter the index of the product: "));
            if (product != null) {
                return product;
            }
            else{
                System.out.println("Product index is not valid");
            }
        }
        return null;
    }

    private String readValidLanguage() {
        do {
            String language = ScannerInput.readNextLine("Enter the language " + LanguageUtility.getLanguages() + ": ");
            if (LanguageUtility.isValidLanguage(language)) {
                return language.toUpperCase();  //return as an uppercase so that it makes hashmap processing easier
            } else {
                System.err.println("\tLanguage not valid.");
            }
        } while (true);
    }

    private void searchProductByName() {
        String productName = ScannerInput.readNextLine("Please enter a product name to search by:");
        System.out.println(store.searchByProductName(productName));
    }

}