package br.ufac.productmanager.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LifeCycleStatus implements Serializable{
    @Id
    @GeneratedValue
    @Column(name="status_id", nullable = false, updatable=false)
    private Long id;
    
    @Column(nullable=false)
    private String name;
    

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
    	return "[\""+getName()+"\","+getId()+"]";
    }
    
    public String toLog(String snapshot) {
    	
    	String log = """
			         {
	                     "idStatus":"%s",
	                     "name":"%s",
	                     "snapshot":"%s"
			         }
			            """;
    	
    	return  log.formatted(id,
							  name,
                              snapshot);
    	
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LifeCycleStatus other = (LifeCycleStatus) obj;
        return Objects.equals(id, other.id) && Objects.equals(name, other.name);
    }
    
    
}