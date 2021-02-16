package br.com.bucks.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TituloAgrupamento.class)
public abstract class TituloAgrupamento_ {

	public static volatile SingularAttribute<TituloAgrupamento, Date> dtCad;
	public static volatile SingularAttribute<TituloAgrupamento, Titulo> titulo;
	public static volatile SingularAttribute<TituloAgrupamento, Integer> id;
	public static volatile SingularAttribute<TituloAgrupamento, Integer> cdTipo;
	public static volatile SingularAttribute<TituloAgrupamento, String> usAlt;
	public static volatile SingularAttribute<TituloAgrupamento, String> usCad;
	public static volatile SingularAttribute<TituloAgrupamento, Date> dtAlt;

}

