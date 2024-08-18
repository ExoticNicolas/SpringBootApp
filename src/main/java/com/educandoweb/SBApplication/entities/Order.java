package com.educandoweb.SBApplication.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.educandoweb.SBApplication.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant  moment;
	private Integer status;
	
	@JsonProperty("Client")
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "client_id")
	private User client;
	
	@JsonProperty("Items")
	@OneToMany(mappedBy = "id.order", fetch = FetchType.EAGER)
	private Set<OrderItem> items = new HashSet<>();
	
	@JsonProperty("Payment")
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;
	
	public Order() {
	
	}
	
	public Order(Long id, Instant moment, OrderStatus status, User client) {
		this.id = id;
		this.moment = moment;
		setStatus(status);
		this.client = client;
	}
	
	@JsonProperty("Total")
	public Double getTotal() {
		Double total = 0.0;
		for(OrderItem item : items) {
			total+=item.getSubTotal();
		}
		return total;
	}
	

	public OrderStatus getStatus() {
		return OrderStatus.valueOf(status);
	}

	public void setStatus(OrderStatus status) {
		if(status != null){
		this.status = status.getCode();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Set<OrderItem> getItems() {
		return items;
	}
	

	public User getUser() {
		return client;
	}

	public void setUser(User client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
