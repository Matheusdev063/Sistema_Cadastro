package edu.mtdev00.sistemapedido.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ItemOrderPK implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @ManyToOne
    @JoinColumn(name = "product_id") 
    private Product product;
    
    @ManyToOne
    @JoinColumn(name = "order_id") 
    private Order order;



    public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
    public int hashCode() {
        return Objects.hash(order, product);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ItemOrderPK other = (ItemOrderPK) obj;
        return Objects.equals(order, other.order) && Objects.equals(product, other.product);
    }
}