import java.util.ArrayList;
public class Store {
    private ArrayList<Product> products;


    public Store() { products = new ArrayList<Product>(); }

    public boolean add(Product product){
        return products.add (product);
    }

    public String listProducts(){
        if (products.isEmpty()) {
            return "No products in the store";
        } else {
            String listOfProducts = "";
            for (int i = 0; i < products.size(); i++) {
                listOfProducts += i + ": " + products.get(i) + "\n";
            }
            return listOfProducts;
        }
    }

    /**
     * This method returns the cheapest product in the array.
     * If no products are stored in the array, null is returned.
     *
     * @return The cheapest Product in the array or null, if no products are added yet.
     */
    public Product cheapestProduct() {
        if (!products.isEmpty()) {
            Product cheapestProduct = products.get(0);
            for (Product product: products) {
                if (product.getUnitCost() < cheapestProduct.getUnitCost())
                    cheapestProduct = product;
            }
            return cheapestProduct;
        } else {
            return null;
        }
    }

    /**
     * This method builds and returns a String containing all the products in the array
     * that are in the current product line.
     * <p>
     * For each product added to the String, the associated index number is included.
     *
     * @return A String containing all the products in the array, or "No products are in our current
     * product line", if none in the current line.  If no products are stored in the array, the
     * returned String indicates this.
     */
    public String listCurrentProducts() {
        if (products.isEmpty()) {
            return "No Products in the store";
        } else {
            String listOfProducts = "";
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).isInCurrentProductLine())
                    listOfProducts += i + ": " + products.get(i) + "\n";
            }
            if (listOfProducts.equals("")){
                return "No Products are in our current product line";
            }
            else{
                return listOfProducts;
            }
        }
    }

    /**
     * This method returns the average product price of all the products in the array.
     * If no products are stored in the array, the value returned is -1.
     *
     * @return The average product price, or -1 if no products exist.
     */
    public double averageProductPrice() {
        if (!products.isEmpty()) {
            double totalPrice = 0;
            for (Product product: products) {
                totalPrice += product.getUnitCost();
            }
            return totalPrice / products.size();
        } else {
            return -1;
        }

    }

    /**
     * This method builds and returns a String containing all the products in the array
     * that are more expensive that a specific amount (passed as a parameter).
     * <p>
     * For each product added to the String, the associated index number is included.
     * If no products are stored in the array, the returned String indicates this.
     *
     * @param price The value used to filter for products costing more than it.
     * @return A String containing all the products in the array more expensive than the parameter value
     * or "No Products are more expensive than: ", if none are more expensive.  If no products are
     * in the array, the returned String contains "No products in store".
     */
    public String listProductsAboveAPrice(double price) {
        if (products.isEmpty()) {
            return "No Products in the store";
        } else {
            String str = "";
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getUnitCost() > price)
                    str += i + ": " + products.get(i) + "\n";
            }
            if (str.equals("")) {
                return "No products are more expensive than: " + price;
            } else {
                return str;
            }
        }
    }

    /**
     *
     * @return products
     */
    public ArrayList<Product> getProducts(){
        return products;
    }

    /**
     * return number of products in arrayList
     * @return int
     */
    public int numberOfProducts() {
        return products.size();
    }

    /**
     * is valid value
     * @param index
     * @return boolean
     */
    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < products.size());
    }

    /**
     *
     * @param indexToDelete
     * @return product
     */
    public Product deleteProduct(int indexToDelete) {
        if (isValidIndex(indexToDelete)) {
            return products.remove(indexToDelete);
        }
        return null;
    }

    /**
     * find a product based on value
     * @param index
     * @return
     */
    public Product findProduct(int index) {
        if (isValidIndex(index)) {
            return products.get(index);
        }
        return null;
    }

    /**
     * Update a product based on passed index
     * @param indexToUpdate
     * @param updateDetails
     * @return
     */
    public boolean updateProduct(int indexToUpdate, Product updateDetails) {
        //find the product object by the index number
        Product foundProduct = findProduct(indexToUpdate);

        //if the product exists, use the details passed in the updateDetails parameter to
        //update the found product in the ArrayList.
        if (foundProduct != null) {
            foundProduct.setProductName(updateDetails.getProductName());
            foundProduct.setProductCode(updateDetails.getProductCode());
            foundProduct.setUnitCost(updateDetails.getUnitCost());
            foundProduct.setInCurrentProductLine(updateDetails.isInCurrentProductLine());
            return true;
        }

        //if the product was not found, return false, indicating that the update was not successful
        return false;
    }
}
