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

@Entity(name = "Base")
public class Base implements Serializable{
    private static final long serialVersionUID = 1L;
	
@NotNull
@Size(min = 2 )
    private String name;
    
	public String getName(){
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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
		if (!(obj instanceof Base)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		Base rhs = (Base) obj;
		return new EqualsBuilder().append(id, rhs.id).append(name, rhs.name).isEquals();
	}
	
	public int hashCode() {
		return new HashCodeBuilder().append(id).append(name).toHashCode();
	}
	
	
	//CUSTOM-CODE
	public int customMethode(){
		return 3;
	}
	//END-CUSTOM-CODE
}