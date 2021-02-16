/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.DAO;

import br.com.bucks.model.BucksUtil;
import br.com.bucks.model.Conta;
import br.com.bucks.model.ContratoPessoa;
import br.com.bucks.model.GrupoTitulo;
import br.com.bucks.model.TipoPagamento;
import br.com.bucks.model.Titulo;
import br.com.bucks.model.TituloAgrupamento;
import br.com.bucks.model.TituloExtrato;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author La Laina
 */
public class TituloDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    private static TituloDAO instance;
    protected EntityManager entityManager;

    
    public List<Titulo> ultITituloInserido() {
        List lista3 = new ArrayList();
        
        String sql = "select t from Titulo t order by t.id DESC";

        entityManager.getTransaction().begin();
        lista3.add(entityManager.createQuery(sql).getResultList().get(0));

        return lista3;
    }
    
    public static TituloDAO getInstance() {
        if (instance == null) {
            instance = new TituloDAO();
        }

        return instance;
    }

    public TituloDAO() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory
                = Persistence.createEntityManagerFactory("persistence");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

    public void inserirTitulo(Titulo titulo) {

        try {
            System.out.println(" <<<TituloDAO inserirTitulo titulo:" + titulo.toString() + ">>>");
            entityManager.getTransaction().begin();
            entityManager.merge(titulo);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public Titulo porId(Integer id) {
        return entityManager.find(Titulo.class, id);
    }

    public void excluirTitulo(Integer id) {
        try {
            entityManager.getTransaction().begin();
            Titulo titulo;
            titulo = porId(id);
            entityManager.remove(titulo);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    private double calcularValorTransacao(double pSaldoConta,
            double pVlTitulo,
            String pPagarReceber,
            String pAcao) {
        System.out.println("calcularValorTransacao => pSaldoConta:" + pSaldoConta
                + " pVlTitulo: " + pVlTitulo
                + " pPagarReceber: " + pPagarReceber
                + " pAcao:" + pAcao);

        if (("R".equals(pPagarReceber) && (pAcao == "1-BAIXA"))
                || ("P".equals(pPagarReceber) && (pAcao == "2-ESTORNO"))) {
            System.out.println("calcularValorTransacao => [RECEBER]");
            return pSaldoConta + pVlTitulo;
        } else {
            System.out.println("calcularValorTransacao => [PAGAR]");
            return pSaldoConta - pVlTitulo;
        }
    }

    public void baixarTitulo(Integer p_num) {
        try {
            entityManager.getTransaction().begin();

            // 1) Recuperar Dados do Titulo
            // duvida: poe que tenho que instanciar um novo objeto, sendo que já estou com ele carregaqdo            
            Titulo titulo = porId(p_num);

            //
            System.out.println(" ");
            System.out.println(" --- TituloDAO.baixarTitulo - inicio");

            System.out.println("TituloDAO.baixarTitulo - titulo num: " + p_num);
            System.out.println("TituloDAO.baixarTitulo - titulo selectedTitulo: " + titulo);

            // 2) Recuperar Saldo da Conta
            double wSaldoAnt = titulo.getConta().getVlSaldo();
            System.out.println("TituloDAO.baixarTitulo - antes "
                    + " Conta:" + titulo.getConta().getDescr()
                    + " Saldo:" + titulo.getConta().getVlSaldo());

            // calcular saldo da conta
            double wSaldoAtu = calcularValorTransacao(
                    wSaldoAnt,
                    titulo.getVl(),
                    titulo.getGrupoTitulo().getCdTipo(),
                    "1-BAIXA");
            titulo.getConta().setVlSaldo(wSaldoAtu);

            System.out.println("TituloDAO.baixarTitulo - dps "
                    + " Conta:" + titulo.getConta().getDescr()
                    + " Saldo:" + titulo.getConta().getVlSaldo());

            titulo.getConta().setDt_alt(new Date());
            titulo.getConta().setUs_alt("BAIXAR TITULO");
//            contaDAO.inserirConta(conta);
            System.out.println("TituloDAO.baixarTitulo - Conta ALTERADA COM SUCESSO");

            // 3) Criar Extrato do Titulo        
            System.out.println("TituloDAO.baixarTitulo - criar extrato do titulo");
            TituloExtrato tituloExtrato = new TituloExtrato();
            // nao preencher o num, até pq a baixa é um insert e sera recuperado da sequence

            tituloExtrato.setFkTituloNum(titulo.getNum());
            tituloExtrato.setVl(titulo.getVl());
            if (titulo.getGrupoTitulo().getCdTipo().equals("P")) {
                tituloExtrato.setVl(titulo.getVl() * -1);
            }

            if (titulo.getGrupoTitulo().getCdTipo().equals("R")) {
                tituloExtrato.setVl(titulo.getVl());
            } else {
                tituloExtrato.setVl(titulo.getVl() * -1);
            }

            tituloExtrato.setVlSaldoAnt(wSaldoAnt);
            tituloExtrato.setVlSaldoAtu(wSaldoAtu);
            tituloExtrato.setDtCad(new Date());
            tituloExtrato.setUsCad("BAIXAR TITULO");

            TituloExtratoDAO tituloExtratoDAO = new TituloExtratoDAO();
            tituloExtratoDAO.inserir(tituloExtrato);

            // 4) Colocar a Data de Pagamento
            titulo.setDtPgto(new Date());
            titulo.setDtAlt(new Date());
            titulo.setUsAlt("BAIXAR TITULO");

            entityManager.merge(titulo);
            entityManager.getTransaction().commit();

            System.out.println(" --- TituloDAO.baixarTitulo - fim");
            System.out.println("  ");

        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void estornarTitulo(Integer p_num) {
        try {
            entityManager.getTransaction().begin();

            // 1) Recuperar Dados do Titulo
            // duvida: poe que tenho que instanciar um novo objeto, sendo que já estou com ele carregaqdo            
            Titulo titulo = porId(p_num);

            //
            System.out.println(" ");
            System.out.println(" --- TituloDAO.estornarTitulo - inicio");

            System.out.println("TituloDAO.estornarTitulo - titulo num: " + p_num);
            System.out.println("TituloDAO.estornarTitulo - titulo selectedTitulo: " + titulo);

            // 2) Recuperar Saldo da Conta
            ContaDAO contaDAO = new ContaDAO();

            Conta conta = contaDAO.porId(titulo.getConta().getNum());
            double wSaldoAnt = conta.getVlSaldo();
            System.out.println("TituloDAO.estornarTitulo - antes Conta:" + conta.getDescr() + " Saldo:" + conta.getVlSaldo());

            double wSaldoAtu = calcularValorTransacao(
                    wSaldoAnt,
                    titulo.getVl(),
                    titulo.getGrupoTitulo().getCdTipo(),
                    "2-ESTORNO");
            conta.setVlSaldo(wSaldoAtu);

//            conta.setVlSaldo(conta.getVlSaldo() - titulo.getVl());
//            double wSaldoAtu = conta.getVlSaldo();
            System.out.println("TituloDAO.estornarTitulo - depois Conta:" + conta.getDescr() + " Saldo:" + conta.getVlSaldo());

            conta.setDt_alt(new Date());
            conta.setUs_alt("ESTORNAR TITULO");
            contaDAO.inserirConta(conta);
            System.out.println("TituloDAO.estornarTitulo - Conta ALTERADA COM SUCESSO");

            // 3) Criar Extrato do Titulo        
            System.out.println("TituloDAO.estornarTitulo - criar extrato do titulo");
            TituloExtrato tituloExtrato = new TituloExtrato();
            tituloExtrato.setFkTituloNum(titulo.getNum());
            if (titulo.getGrupoTitulo().getCdTipo().equals("R")) {
                tituloExtrato.setVl(titulo.getVl() * -1);
            } else {
                tituloExtrato.setVl(titulo.getVl());
            }
            tituloExtrato.setVlSaldoAnt(wSaldoAnt);
            tituloExtrato.setVlSaldoAtu(wSaldoAtu);
            tituloExtrato.setDtCad(new Date());
            tituloExtrato.setUsCad("ESTORNAR TITULO");

            TituloExtratoDAO tituloExtratoDAO = new TituloExtratoDAO();
            tituloExtratoDAO.inserir(tituloExtrato);

            // 4) Colocar a Data de Pagamento
            titulo.setDtPgto(null);
            titulo.setDtAlt(new Date());
            titulo.setUsAlt("ESTORNAR TITULO");

            System.out.println("TituloDAO.estornarTitulo - inserir(alterar) titulo" + titulo);

            TituloDAO tituloDAO = new TituloDAO();
            tituloDAO.inserirTitulo(titulo);

            System.out.println("TituloDAO.estornarTitulo - Titulo[ALTERADO] COM SUCESSO!" + titulo);

            System.out.println(" --- TituloDAO.estornarTitulo - fim");
            System.out.println("  ");

            entityManager.merge(titulo);
            entityManager.getTransaction().commit();

            System.out.println(" --- TituloDAO.estornarTitulo - fim");
            System.out.println("  ");

        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public List<Titulo> todas(
            Integer pContaId,
            Integer pAno,
            Integer pMes,
            String pPago,
            String pCdContabiliza) {
        String cmd;
        cmd = "select t from Titulo t "
                + " where YEAR(t.dtVcto) = :filtroAno ";

        if (pMes != 0) {
            cmd = cmd + "   and MONTH(t.dtVcto) = " + pMes;
        }

        if (pContaId != null && pContaId > 0) {
            cmd = cmd + " and t.conta.id = " + pContaId;
        }

        if (!pPago.equals("ALL")) {
            if (pPago.equals("S")) {
                cmd = cmd + " and t.dtPgto is not null ";
            } else {
                cmd = cmd + " and t.dtPgto is null ";
            }
        }
        if (!pCdContabiliza.equals("ALL")) {
            cmd = cmd + " and t.grupoTitulo.cdContabiliza = :filtroCdContabiliza ";
        }

        TypedQuery<Titulo> query = entityManager.createQuery(
                cmd,
                Titulo.class
        );

        query.setParameter("filtroAno", pAno);
//        query.setParameter("filtroMes", pMes);

        if (!pCdContabiliza.equals("ALL")) {
            query.setParameter("filtroCdContabiliza", pCdContabiliza);
        }

        return query.getResultList();
    }

    public List<Titulo> consultarFiltrando(Integer pAno, Integer pMes) throws ParseException {

        String sql;
        sql = "select t from Titulo t where 1=1 ";

        if (pAno > 2005) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String dateInStringInicio = "01-" + String.format("%015d", pMes) + "-" + pAno;
            String dateInStringFinal = "30-" + String.format("%015d", pMes) + "-" + pAno;

            Date dateInicio = formatter.parse(dateInStringInicio);
            Date dateFinal = formatter.parse(dateInStringFinal);
            System.out.println(dateInicio);
            System.out.println(formatter.format(dateInicio));
            System.out.println(dateFinal);
            System.out.println(formatter.format(dateFinal));

            sql = sql + " and t.dtVecto >= " + dateInicio + " and t.dtVecto <= " + dateFinal;
        }

//        if (d.after(outraData) && d.before(maisOutraData)) {
//            null;
//        }
//    }
//        sql 
        TypedQuery<Titulo> query = entityManager.createQuery(
                sql,
                Titulo.class
        );

        return query.getResultList();

    }

    public List<Conta> todasContas() {
        TypedQuery<Conta> query = entityManager.createQuery(
                "select c from Conta c",
                Conta.class
        );

        return query.getResultList();
    }

    public List<Conta> filtrarContas(Integer pCdSit) {
        String cmd = "select c from Conta c";
        if (pCdSit != null && pCdSit != 0) {
            cmd = cmd + " where cdSit = " + pCdSit;
        }
        TypedQuery<Conta> query = entityManager.createQuery(
                cmd,
                Conta.class
        );

        return query.getResultList();
    }

    public List<TipoPagamento> todosTiposTitulos() {
        TypedQuery<TipoPagamento> query = entityManager.createQuery(
                "select tp from TipoPagamento tp",
                TipoPagamento.class
        );

        return query.getResultList();
    }

    public List<ContratoPessoa> lovContratoPessoa() {
        TypedQuery<ContratoPessoa> query = entityManager.createQuery(
                "select cp from ContratoPessoa cp",
                ContratoPessoa.class
        );

        return query.getResultList();
    }

    public List<String> listaDescricao() {
        TypedQuery<String> query = entityManager.createQuery(
                "select distinct t.descr from Titulo t",
                String.class
        );

        return query.getResultList();
    }

    public List<Integer> ultIdTitulo() {
        TypedQuery<Integer> query = entityManager.createQuery(
                "select max(t.id) from Titulo t",
                Integer.class
        );

        return query.getResultList();
    }

    public Titulo retornarUltTituloPorDescr(Integer pContaId, String pDescr) {
        System.out.println(" <<<TituloDAO retornarUltTituloPorDescr>>>");
        TypedQuery<Titulo> query = entityManager.createQuery(
                "select t from Titulo t where t.descr = :pDescr order by t.id desc ",
                Titulo.class
        );
        query.setParameter("pDescr", pDescr);

        // guardar o titulo mais recente (primeiro da lista)
        Titulo tituloRecente = new Titulo();
        if (query.getResultList().isEmpty()) {
            System.out.println(" <<<TituloDAO retornarUltTituloPorDescr nenhum registro encontrado para " + pDescr + ">>>");
            return null;
        }

        tituloRecente = query.getResultList().get(0);

        // se nao informou a conta então recuperar o mais recente
        if (pContaId == null) {
            return tituloRecente;
        }

        // procurar o proximo da lista que é dessa conta
        for (Titulo listaTitulo : query.getResultList()) {
            if (listaTitulo.getConta().getNum() == pContaId) {
                return listaTitulo;
            }
        }

        // caso nao encontre da mesma conta, entao retornou o mais recente que guardei
        return tituloRecente;

    }

    /*
    
//     * Método usando a API de Criteria para gerar a seguinte consulta: SELECT
//     * d.descricao as doce, count(*) as qtd, (count(*) * d.preco) as total FROM
//     * Venda v INNER JOIN (Venda_Doce vd JOIN Doce d ON (d.id = vd.doce_id)) ON
//     * (vd.venda_id = v.id) WHERE v.id = vd.venda_id AND d.id = vd.doce_id GROUP
//     * BY vd.doce_id ORDER BY d.descricao
//     *
//     * @return Uma lista de objetos RelatorioVendaDoce que sumariza a quantidade
//     * de doces vendidos.
     
    public List<RelatorioVendaDoce> relatorioDocesPorVenda() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
//        **
//         * Quando efetuar a consulta será guardado os objetos em uma classe que
//         * representa o relatório, esta classe possui os atributos descricao,
//         * quantidade e total.
//         *
        CriteriaQuery<RelatorioVendaDoce> relatorio = cb.createQuery(RelatorioVendaDoce.class);
        Root<Venda> venda = relatorio.from(Venda.class);
        Join<Venda, Doce> doce = venda.joinList("doces");

        
//         * A interface Selection<T> representa cada coluna que será retornada na
//         * consulta.
//         *
//         * Ela possui o método alias() onde você pode dar um apelido para a
//         * coluna que será consultada.
         
        Selection<String> nomeDoce = doce.get("descricao").as(String.class).alias("doce");

        
//         * Com o método count() da CriteriaBuilder está sendo usado para contar
//         * quantas vendas foram feitas por doce. Para isso também adicionei um
//         * groupBy por doce no CriteriaQuery.
         
        Selection<Long> qtd = cb.count(venda).alias("qtd");

        
//         * A interface CriteriaBuilder possui métodos para diversas funções,
//         * como por exemplo: - Funções de agregação: - avg(), count(),
//         * countDistinct(), max(), min(), sum() e outros. - Funções de dados: -
//         * abs(), concat(), length(), sqrt(), substring(), upper(), trim() e
//         * outros. - Expressões escalares: - all(), any(), sum(), prod(),
//         * diff(), quot(), selectCase() e outros. - Predicado: - and(), or(),
//         * not(), equal(), notEqual(), gt(), ge(), lt(), le(), between(),
//         * isNull(), isNotNull(), exists(), not(exists()), isEmpty(),
//         * isNotEmpty(), like(), notLike(), in(), not(in()) e outros.
//         *
//         * Usando o método prod() podemos multiplicar dois valores, então
//         * estamos multiplicando o preço do doce pela quantidade de vezes que
//         * ele foi vendido.
         
        Selection<Double> total = cb.prod(doce.get("preco").as(Double.class), cb.count(venda)).as(Double.class).alias("total");

        
//         * O método construct() do CriteriaBuilder vai guardar as colunas
//         * informadas na em objetos do tipo da classe informado.
//         *
//         * A classe informada precisa ter um construtor que recebe como
//         * parâmetro as colunas que serão retornadas, com seus respectivos tipos
//         * de dados e na ordem que eles serão consultados. Neste exemplo a
//         * classe RelatorioVendaDoce tem o construtor:
//         *
//         * public RelatorioVendaDoce(String descricao, Long quantidade, Double
//         * total) { this.descricao = descricao; this.quantidade = quantidade;
//         * this.total = total; }
         
        relatorio.select(cb.construct(RelatorioVendaDoce.class, nomeDoce, qtd, total));

        
//         * O método groupBy() da CriteriaQuery agrupa as informações, neste
//         * exemplo queremos agrupar as vendas por doce.
         
        relatorio.groupBy(doce);

        
//         * O método orderBy da CriteriaQuery pode ordenar de forma crescent
//         * (asc) ou decrescente (desc) os resultados, ele recebe como parametro
//         * um objeto do tipo Order que pode ser objetido através do método asc()
//         * ou desc() do CriteriaBuilder.
         
        relatorio.orderBy(cb.asc(doce.get("descricao")));

        TypedQuery<RelatorioVendaDoce> q = em.createQuery(relatorio);
        return q.getResultList();
    }

    */
    
    public List<BucksUtil> retornarSomaTituloPorAgrup() {
        List<BucksUtil> lista = new ArrayList();
        BucksUtil bucksUtil;

        TypedQuery<Object[]> query = entityManager.createQuery(
                "SELECT t.grupoTitulo"
                + ", t.vl"
                + ", t.dtVcto"        
                + " FROM Titulo AS t "
                + " WHERE t.id < 30 ",
                 Object[].class);
        List<Object[]> results = query.getResultList();
        for (Object[] reg : results) {
            System.out.println(
                    "idGrupo: " + ((GrupoTitulo) reg[0]).getNum() + ", Descr: " + ((GrupoTitulo) reg[0]).getDescr()+ " Vl:" + reg[1] + " Dt:" + reg[2]);
            
            bucksUtil = new BucksUtil();
            // GrupoTitulo
            bucksUtil.setGrupoTitulo( (GrupoTitulo) reg[0] );
            
            // v2 = Realizado
            bucksUtil.setVl2((Double) reg[1]);
            // cd1 = Ano
            bucksUtil.setCd1(bucksUtil.retornarAnoDeUmaData((Date) reg[2]));
            // cd2 = Mes
            bucksUtil.setCd2(bucksUtil.retornarMesDeUmaData((Date) reg[2]));
            
            lista.add(bucksUtil);
            
        }

        return lista;

    }

}
