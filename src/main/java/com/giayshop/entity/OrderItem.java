package com.giayshop.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OrderItem")
public class OrderItem extends BaseEntity{

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id", foreignKey = @ForeignKey(name="Orders_ID_FK"))
	private Orders order;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Products product;
	
}
