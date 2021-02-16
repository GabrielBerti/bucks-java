package br.com.bucks.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Orcamento.class)
public abstract class Orcamento_ {

	public static volatile SingularAttribute<Orcamento, Date> dtCad;
	public static volatile SingularAttribute<Orcamento, String> obs;
	public static volatile SingularAttribute<Orcamento, Integer> ano;
	public static volatile SingularAttribute<Orcamento, Double> vl;
	public static volatile SingularAttribute<Orcamento, GrupoTitulo> grupoTitulo;
	public static volatile SingularAttribute<Orcamento, Integer> mes;
	public static volatile SingularAttribute<Orcamento, Integer> id;
	public static volatile SingularAttribute<Orcamento, String> usAlt;
	public static volatile SingularAttribute<Orcamento, String> usCad;
	public static volatile SingularAttribute<Orcamento, Date> dtAlt;

}

