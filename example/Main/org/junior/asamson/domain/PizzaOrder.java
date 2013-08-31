package org.junior.asamson.domain;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

//CUSTOM-IMPORT

//END-CUSTOM-IMPORT

@Entity(name = "PizzaOrder")
public class PizzaOrder implements Serializable{
    private static final long serialVersionUID = 1L;
	
@NotNull
@Size(min = 2 )
    private String name;
    
@Size(min = 30 )
    private String adresse;
    

    private Double total;
    
@Temporal(TemporalType.TIMESTAMP)
@DateTimeFormat(style = "M-")
    private Date deliveryDate;
    
	@NotNull
	
	@ManyToMany
	private Set<Pizza> pizzas = new HashSet<Pizza>();
	public String getName(){
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getAdresse(){
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public Double getTotal(){
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	public Date getDeliveryDate(){
		return this.deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	
	public Set<Pizza> getPizzas(){
		return this.pizzas;
	}

	public void setPizzas(Set<Pizza> pizzas) {
		this.pizzas = pizzas;
	}
	
	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

	@Version
    @Column(name = "version")
    private Integer version;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
    }

	public boolean equals(Object obj) {
		if (!(obj instanceof PizzaOrder)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		PizzaOrder rhs = (PizzaOrder) obj;
		return new EqualsBuilder().append(id, rhs.id).append(name, rhs.name).append(adresse, rhs.adresse).append(total, rhs.total).append(deliveryDate, rhs.deliveryDate).isEquals();
	}
	
	public int hashCode() {
		return new HashCodeBuilder().append(id).append(name).append(adresse).append(total).append(deliveryDate).toHashCode();
	}
	
	
	//CUSTOM-CODE
	
	//END-CUSTOM-CODE
}