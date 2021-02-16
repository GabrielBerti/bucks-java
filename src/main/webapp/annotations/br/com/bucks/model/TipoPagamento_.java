package br.com.bucks.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TipoPagamento.class)
public abstract class TipoPagamento_ {

	public static volatile SingularAttribute<TipoPagamento, Integer> num;
	public static volatile SingularAttribute<TipoPagamento, Date> dt_cad;
	public static volatile SingularAttribute<TipoPagamento, Date> dt_alt;
	public static volatile SingularAttribute<TipoPagamento, String> us_cad;
	public static volatile SingularAttribute<TipoPagamento, String> us_alt;
	public static volatile SingularAttribute<TipoPagamento, String> descr;

}

