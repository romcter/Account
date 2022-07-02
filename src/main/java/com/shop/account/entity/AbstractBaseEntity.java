package com.shop.account.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

@MappedSuperclass
public class AbstractBaseEntity {

    @Id
    @Column
    @Access(AccessType.PROPERTY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public AbstractBaseEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isNew() {
        return this.getId() == null;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof AbstractBaseEntity)) {
            return false;
        } else {
            AbstractBaseEntity that = (AbstractBaseEntity) o;
            return this.isNew() ? super.equals(that) : (new EqualsBuilder()).append(this.getId(), that.getId()).isEquals();
        }
    }

    public int hashCode() {
        return this.isNew() ? super.hashCode() : (new HashCodeBuilder()).append(this.getId()).toHashCode();
    }

    public String toString() {
        return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("id", this.id).toString();
    }
}
