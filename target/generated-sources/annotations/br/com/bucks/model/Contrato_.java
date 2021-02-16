package br.com.bucks.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Contrato.class)
public abstract class Contrato_ {

	public static volatile SingularAttribute<Contrato, Date> dtCad;
	public static volatile SingularAttribute<Contrato, String> descr;
	public static volatile SingularAttribute<Contrato, String> obs;
	public static volatile SingularAttribute<Contrato, ContratoTipoEnum> contratoTipo;
	public static volatile SingularAttribute<Contrato, Date> dtBase;
	public static volatile SingularAttribute<Contrato, Double> vlBase;
	public static volatile SingularAttribute<Contrato, Integer> id;
	public static volatile SingularAttribute<Contrato, String> usAlt;
	public static volatile SingularAttribute<Contrato, String> usCad;
	public static volatile SingularAttribute<Contrato, Date> dtAlt;

}

