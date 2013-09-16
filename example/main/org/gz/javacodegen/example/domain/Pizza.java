package org.gz.javacodegen.example.domain;

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

@Entity(name = "Pizza")
public class Pizza implements Serializable{
    private static final long serialVersionUID = 1L;
	
@NotNull
@Size(min = 2 )
    private String name;
    

    private double price;
    
	
	
	@ManyToMany
	private Set<Topping> toppings = new HashSet<Topping>();
	
	
	@ManyToOne
	private Base base
	public String getName(){
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice(){
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	public Set<Topping> getToppings(){
		return this.toppings;
	}

	public void setToppings(Set<Topping> toppings) {
		this.toppings = toppings;
	}
	
	
	public Base getBase(){
		return this.base;
	}

	public void setBase(Base base) {
		this.base = base;
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
		if (!(obj instanceof Pizza)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		Pizza rhs = (Pizza) obj;
		return new EqualsBuilder().append(id, rhs.id).append(name, rhs.name).append(price, rhs.price).isEquals();
	}
	
	public int hashCode() {
		return new HashCodeBuilder().append(id).append(name).append(price).toHashCode();
	}
	
	
	//CUSTOM-CODE
	
	//END-CUSTOM-CODE
}