package br.com.bucks.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Login.class)
public abstract class Login_ {

	public static volatile SingularAttribute<Login, Integer> id;
	public static volatile SingularAttribute<Login, String> email;
	public static volatile SingularAttribute<Login, String> senha;

}

