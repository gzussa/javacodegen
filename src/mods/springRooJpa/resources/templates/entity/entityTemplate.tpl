<#ftl strip_whitespace=true>
package ${packageName};

<#if serializable==true>
import java.io.Serializable;
</#if>
<#if sequenceName??>
import javax.persistence.SequenceGenerator;
</#if>
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
<#include classicImports>
<#if activeRecorde==true>
<#include activeRecordImports>
</#if>

//CUSTOM-IMPORT
<#if customImport??>${customImport}</#if>
//END-CUSTOM-IMPORT

@Entity(name = "${name}")
<#if table??>
@Table(catalog = "${catalogue}", schema="${schema}", name="${table}")
</#if>
public class ${name} <#if serializable==true>implements Serializable</#if>{
	<#if serializable==true>
    private static final long serialVersionUID = 1L;
	</#if>
	
	<#if fields??>
	<#list fields as field>
	<#compress>
	<#if field.persistenceType!="">@Temporal(${field.persistenceType})</#if>
	<#if field.notNull==true>@NotNull</#if>
	<#if field.isTransient==true>@Transient</#if>
	<#if field.unique==true || field.defaultValue!="">
	@Column(<#if field.unique==true>unique=true</#if> <#if field.defaultValue!="">columnDefinition="default ${field.defaultValue}"</#if>)
	</#if>
	<#if field.future==true>@Future</#if>
	<#if field.past==true>@Past</#if>
	<#if field.type=="Date">
	@Temporal(${field.temporalType})
	@DateTimeFormat(style = "${field.dateTimeFormatPattern}")
	</#if>
	<#if field.decimalMin!="">@DecimalMin("${field.decimalMin}")</#if>
	<#if field.decimalMax!="">@DecimalMax("${ field.decimalMax}")</#if>
	<#if field.digitInteger!="" || field.digitFraction!="">
	@Digits(<#if field.digitInteger!="">integer=${field.digitInteger}</#if> <#if field.digitFraction!="">fraction=${field.digitFraction}</#if>)
	</#if>
	<#if field.sizeMin!="" || field.sizeMax!=""> 
	@Size(<#if field.sizeMin!="">min = ${field.sizeMin}</#if> <#if field.sizeMax != "">max = ${field.sizeMax}</#if>)
	</#if>
	<#if field.regexp!="">@Pattern(regexp = "${field.regexp}")</#if>
	</#compress>

    private ${field.type} ${field.name};
    
	</#list>
	</#if>
	<#if relationships??>
	<#list relationships as relationship>
	<#if relationship.notNull==true>@NotNull</#if>
	<#if relationship.joinColumnName!="">@JoinColumn(name="${relationship.joinColumnName}")</#if>
	<#if relationship.cardinality=="ManyToMany">
	@ManyToMany<#if relationship.mappedBy!="">(mappedBy="${relationship.mappedBy}")</#if>
	private Set<${relationship.type}> ${relationship.name} = new HashSet<${relationship.type}>();
	<#elseif relationship.cardinality=="ManyToOne">
	@ManyToOne<#if relationship.mappedBy!="">(mappedBy="${relationship.mappedBy}")</#if>
	private ${relationship.type} ${relationship.name}
	<#elseif relationship.cardinality=="OneToMany">
	@OneToMany<#if relationship.mappedBy!="">(mappedBy="${relationship.mappedBy}")</#if>
	private Set<${relationship.type}> ${relationship.name} = new HashSet<${relationship.type}>();
	<#elseif relationship.cardinality=="OneToOne">
	@OneToOne<#if relationship.mappedBy!="">(mappedBy="${relationship.mappedBy}")</#if>
	private ${relationship.type} ${relationship.name}
	</#if>
	</#list>
	</#if>
    <#if fields??>
    <#list fields as field>
	public ${field.type} get${field.upperName}(){
		return this.${field.name};
	}

	public void set${field.upperName}(${field.type} ${field.name}) {
		this.${field.name} = ${field.name};
	}
	
	</#list>
	</#if>
	<#if relationships??>
	<#list relationships as relationship>
	<#if relationship.fetch!="">@Basic(fetch=${relationship.fetch})</#if>
	<#if relationship.cardinality=="OneToOne" || relationship.cardinality=="ManyToOne">
	public ${relationship.type} get${relationship.upperName}(){
		return this.${relationship.name};
	}

	public void set${relationship.upperName}(${relationship.type} ${relationship.name}) {
		this.${relationship.name} = ${relationship.name};
	}
	<#else>
	public Set<${relationship.type}> get${relationship.upperName}(){
		return this.${relationship.name};
	}

	public void set${relationship.upperName}(Set<${relationship.type}> ${relationship.name}) {
		this.${relationship.name} = ${relationship.name};
	}
	</#if>
	
	</#list>
	</#if>
	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@Id
	<#if sequenceName??>
    @SequenceGenerator(name = "${name}Gen", sequenceName = "${sequenceName}")
	</#if>
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
		if (!(obj instanceof ${name})) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		${name} rhs = (${name}) obj;
		return new EqualsBuilder().append(id, rhs.id)<#list fields as field>.append(${field.name}, rhs.${field.name})</#list>.isEquals();
	}
	
	public int hashCode() {
		return new HashCodeBuilder().append(id)<#list fields as field>.append(${field.name})</#list>.toHashCode();
	}
	
    <#if activeRecorde==true>
	<#include activeRecordTemplate>
	</#if>
	
	//CUSTOM-CODE
	<#if customCode??>${customCode}</#if>
	//END-CUSTOM-CODE
}