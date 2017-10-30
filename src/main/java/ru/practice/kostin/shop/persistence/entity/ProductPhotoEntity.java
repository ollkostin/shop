package ru.practice.kostin.shop.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_photo")
public class ProductPhotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String path;

    @ManyToOne
    private ProductEntity product;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductPhotoEntity that = (ProductPhotoEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (path != null ? !path.equals(that.path) : that.path != null) return false;
        return product != null ? product.equals(that.product) : that.product == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductPhotoEntity{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", product=" + product +
                '}';
    }
}
