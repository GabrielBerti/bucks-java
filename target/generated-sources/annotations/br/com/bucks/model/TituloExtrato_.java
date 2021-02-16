package br.com.bucks.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TituloExtrato.class)
public abstract class TituloExtrato_ {

	public static volatile SingularAttribute<TituloExtrato, Date> dtCad;
	public static volatile SingularAttribute<TituloExtrato, Double> vlSaldoAnt;
	public static volatile SingularAttribute<TituloExtrato, Double> vl;
	public static volatile SingularAttribute<TituloExtrato, Integer> fkTituloNum;
	public static volatile SingularAttribute<TituloExtrato, Double> vlSaldoAtu;
	public static volatile SingularAttribute<TituloExtrato, Integer> id;
	public static volatile SingularAttribute<TituloExtrato, String> usAlt;
	public static volatile SingularAttribute<TituloExtrato, String> usCad;
	public static volatile SingularAttribute<TituloExtrato, Date> dtAlt;

}

