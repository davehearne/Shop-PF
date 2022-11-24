package models;

import utils.LanguageUtility;
import utils.Utilities;

import java.util.HashMap;
import java.util.Map;

/**
 * A scaled down version of a Product class
 *  
 * @author Dave Hearne
 * @version 6.0
 */
public class Product {
    private String productName = "";
    private int productCode = -1;
    private double unitCost = 0.00;
    private boolean inCurrentProductLine = false;
    private Map<String,String> productDescriptions = new HashMap<>();

    /**
     * Constructor for objects of class Product
     * @param productName Name of the product
     * @param productCode Code of the product
     * @param unitCost Unit cost of the product
     */
    public Product(String productName, int productCode, double unitCost, boolean inCurrentProductLine) {
        this.productName = Utilities.truncateString(productName, 20);
        this.productCode = productCode;
        this.unitCost = unitCost;
        this.inCurrentProductLine = inCurrentProductLine;

    }
         
    //-------
    //getters
    //-------
    /**
     * Returns the Product Name
     */
    public String getProductName(){
        return productName;
    }
    
    /**
     * Returns the Unit Cost 
     */
    public double getUnitCost(){
        return unitCost;
    }
    
    /**
     * Returns the Product Code
     */
    public int getProductCode() {
		return productCode;
	}

    /**
     * Returns a boolean indicating if the product is in the current product line 
     */
    public boolean isInCurrentProductLine() {
		return inCurrentProductLine;
	}

    public Map<String, String> getProductDescriptions() {
        return productDescriptions;
    }

    //-------
    //setters
    //-------

    public void setProductDescriptions(Map<String, String> productDescriptions) {
        this.productDescriptions = productDescriptions;
    }

    /**
     * Updates the Product Code to the value passed as a parameter
     * @param productCode The new Product Code
     */
	public void setProductCode(int productCode) {
        if (Utilities.validRange(productCode, 99, 9999)) {
            this.productCode = productCode;
        }
	}

	/**
     * Updates the Product Name to the value passed as a parameter
     * @param productName The new Product Name
     */
	public void setProductName(String productName) {
        if (Utilities.validateStringLength(productName, 20)) {
            this.productName = productName;
        }
	}

	/**
     * Updates the Unit Cost to the value passed as a parameter 
     * @param unitCost The new unit cost for the product
     */
	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}

	/**
     * Updates the boolean indicating whether the product is in the current product line or not.  
     * @param inCurrentProductLine Indicator that determines if the product is in the current product line or not.
     */
	public void setInCurrentProductLine(boolean inCurrentProductLine) {
		this.inCurrentProductLine = inCurrentProductLine;
	}

    /**
     * Builds a String representing a user-friendly representation of the object state
     * @return Details of the specific product
     */
    public String toString()
    {
        return "Product description: " + productName
                + ", product code: " + productCode
                + ", unit cost: " + unitCost
                + ", currently in product line: " + Utilities.booleanToYN(inCurrentProductLine);
    }

    public int numberOfDescriptions(){
        return productDescriptions.size();
    }
    public String findDescription(String language) {
        return productDescriptions.get(language);
    }
    public boolean isAlreadyAddedLanguage(String language) {
        return (findDescription(language) != null);
    }
    public String deleteDescription(String language) {
        return productDescriptions.remove(language);
    }
    public String listDescriptions() {
        if (numberOfDescriptions() > 0){
            String listToReturn = "";
            for(String language : productDescriptions.keySet()){
                listToReturn += language + ": " + productDescriptions.get(language) + ".\n";
            }
            return listToReturn;
        }
        return "No descriptions added yet";
    }
    public boolean updateDescription(String language, String updatedDescription) {
        //if the language has been added to the map, use the details passed in the
        //updateDescription parameter to update the description.
        if (isAlreadyAddedLanguage(language)) {
            productDescriptions.put(language, updatedDescription);
            return true;
        }

        //if the language was not found, return false, indicating that the update was not successful.
        return false;
    }

    public boolean addDescription(String language, String description){
        if (isAlreadyAddedLanguage(language)) {
            return false;
        }
        else{
            if (LanguageUtility.isValidLanguage(language)) {
                productDescriptions.put(language, description);
                return true;
            }
        }
        return false;
    }

}
