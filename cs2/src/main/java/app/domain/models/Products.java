package app.domain.models;

public class Products {

    private long productId;
    private String nameProduct;
    private float price;
    private int cant = 0;

    public Products(long productId, String nameProduct, float price, int cant) {
        this.productId = productId;
        this.nameProduct = nameProduct;
        this.price = price;
        this.cant = cant;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }
}
