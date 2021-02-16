package br.com.bucks.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Conta.class)
public abstract class Conta_ {

	public static volatile SingularAttribute<Conta, Integer> cdSit;
	public static volatile SingularAttribute<Conta, String> obs;
	public static volatile SingularAttribute<Conta, Integer> num;
	public static volatile SingularAttribute<Conta, Date> dt_cad;
	public static volatile SingularAttribute<Conta, Date> dt_alt;
	public static volatile SingularAttribute<Conta, Double> vlSaldo;
	public static volatile SingularAttribute<Conta, String> us_cad;
	public static volatile SingularAttribute<Conta, String> us_alt;
	public static volatile SingularAttribute<Conta, String> descr;

}

