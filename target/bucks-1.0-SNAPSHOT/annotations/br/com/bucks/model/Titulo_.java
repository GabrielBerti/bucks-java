package br.com.bucks.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Titulo.class)
public abstract class Titulo_ {

	public static volatile SingularAttribute<Titulo, Double> vl;
	public static volatile SingularAttribute<Titulo, Integer> fk_conta_num;
	public static volatile SingularAttribute<Titulo, Integer> fk_tipo_titulo_num;
	public static volatile SingularAttribute<Titulo, Integer> fk_grupo_num;
	public static volatile SingularAttribute<Titulo, String> usCad;
	public static volatile SingularAttribute<Titulo, Date> dtVcto;
	public static volatile SingularAttribute<Titulo, Integer> parcelaTotal;
	public static volatile SingularAttribute<Titulo, String> obs;
	public static volatile SingularAttribute<Titulo, Integer> num;
	public static volatile SingularAttribute<Titulo, Date> dtAlt;
	public static volatile SingularAttribute<Titulo, Date> dtCad;
	public static volatile SingularAttribute<Titulo, String> usAlt;
	public static volatile SingularAttribute<Titulo, String> descr;
	public static volatile SingularAttribute<Titulo, Date> dtPgto;
	public static volatile SingularAttribute<Titulo, Integer> parcela;

}

