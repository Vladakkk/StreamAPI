import java.util.Date;

public class Product {
    private String type;
    private double price;
    private boolean discount;
    private Date createDate;

    private Product(String type, double price, boolean discount, Date createDate) {
        this.type = type;
        this.price = price;
        this.discount = discount;
        this.createDate = createDate;
    }

    public static ProductBuilder builder(){
        return new ProductBuilder();
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public boolean hasDiscount() {
        return discount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    static class ProductBuilder {
        private String type;
        private double price;
        private boolean discount;
        private Date createDate;

        public ProductBuilder type(String type) {
            this.type = type;
            return this;
        }

        public ProductBuilder price(double price) {
            this.price = price;
            return this;
        }

        public ProductBuilder discount(boolean discount) {
            this.discount = discount;
            return this;
        }

        public ProductBuilder createDate(Date createDate) {
            this.createDate = createDate;
            return this;
        }

        public Product build(){
            return new Product(type, price, discount, createDate);
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "type='" + type + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", createDate=" + createDate +
                '}';
    }
}
