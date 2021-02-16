package br.com.bucks.bean;

import br.com.bucks.DAO.ContratoDAO;
import br.com.bucks.DAO.GrupoTituloDAO;
import br.com.bucks.DAO.LovDAO;
import br.com.bucks.DAO.PessoaDAO;
import br.com.bucks.DAO.TituloAgrupamentoDAO;
import br.com.bucks.DAO.TituloDAO;
import br.com.bucks.model.Conta;
import br.com.bucks.model.Contrato;
import br.com.bucks.model.GrupoTitulo;
import br.com.bucks.model.Pessoa;
import br.com.bucks.model.TipoPagamento;
import br.com.bucks.model.Titulo;
import br.com.bucks.model.TituloAgrupamento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@ManagedBean
@ViewScoped
//@RequestScoped
public class TituloMB implements Serializable {

    //Lista de Titulos
    private List<Titulo> listaTitulos = new ArrayList();

    //Listas
    private List<Conta> listaContas = new ArrayList();
    private List<GrupoTitulo> listaGruposTitulos = new ArrayList();
    private List<TipoPagamento> listaTiposTitulos = new ArrayList();
    private List<Contrato> lovContrato = new ArrayList();
    private List<Pessoa> lovPessoa = new ArrayList();

    //DAO
    private TituloDAO tituloDAO = new TituloDAO();
    private ContratoDAO contratoDAO = new ContratoDAO();
    private PessoaDAO pessoaDAO = new PessoaDAO();
    private GrupoTituloDAO grupoTituloDAO = new GrupoTituloDAO();
    private LovDAO lovDAO = new LovDAO();

    private Titulo selectedTitulo = new Titulo(); //pause...testando

    // Filtros
    private List<Conta> filtroListaContas = new ArrayList();
    private Conta filtroConta = new Conta();

    //
    private List<String> listaDescricao = new ArrayList<String>();
    private List<Integer> listaIdTitulo = new ArrayList<Integer>();

    private Integer num;
    private Conta conta;
    private GrupoTitulo grupoTitulo;
    private TipoPagamento tipoPagamento;
    private Contrato contrato;
    private Pessoa pessoa;
    private String descr;
    private Double vl;
    private String usCad;
    private Date dtCad;
    private Date dtVcto;
    private Date dtPgto;
    private Integer parcela;
    private Integer parcelaTotal;

    private String obs;

    // var
    private Double valorParcela;
    private String textBotao;
    private Double vl_parc;

    // Filtros
    private Integer filtroMes;
    private Integer filtroAno;
    private String filtroCdContabiliza;
    private String filtroPago;
    private List<LovDAO> filtroLovCdContabiliza = new ArrayList();
    private List<LovDAO> filtroLovPago = new ArrayList();

    private String teste;

    //Quadro Show
    private String showConta;
    private Double showContaPagar;
    private Double showContaReceber;

    @PostConstruct
    public void init() {
        setarFiltros();
        carregarListas();
    }

    private void carregarListas() {
        this.todosTitulos();
        this.todasContas();
        this.todosGrupostitulos();
        this.todosTiposTitulos();
        this.todosContratos();
        this.todasPessoas();

    }

    public void linhaSelecionada(SelectEvent event) {
        this.setNum(this.getSelectedTitulo().getNum());
        this.setConta(this.getSelectedTitulo().getConta());
        this.setGrupoTitulo(this.getSelectedTitulo().getGrupoTitulo());
        this.setTipoPagamento(this.getSelectedTitulo().getTipoPagamento());
        this.setContrato(this.getSelectedTitulo().getContrato());
        this.setPessoa(this.getSelectedTitulo().getPessoa());
        this.setDescr(this.getSelectedTitulo().getDescr());
        this.setVl(this.getSelectedTitulo().getVl());
        this.setDtCad(this.getSelectedTitulo().getDtCad());
        this.setDtVcto(this.getSelectedTitulo().getDtVcto());
        this.setDtPgto(this.getSelectedTitulo().getDtPgto());
        this.setParcela(this.getSelectedTitulo().getParcela());
        this.setParcelaTotal(this.getSelectedTitulo().getParcelaTotal());
        this.setObs(this.getSelectedTitulo().getObs());
    }

    public void todasContas() {
        this.setListaContas(tituloDAO.todasContas());
        this.setFiltroListaContas(tituloDAO.filtrarContas(1)); //Ativo
    }

    public void todosTitulos() {
        System.out.println(" <<<TituloMb todosTitulos >>>");
        Integer wFiltroConta = null;
        if (filtroConta != null) {
            wFiltroConta = filtroConta.getNum();
            System.out.println(" <<<TituloMb todosTitulos filtrar pela conta(" + filtroConta.getDescr() + ") >>>");
        }
        this.setListaTitulos(tituloDAO.todas(wFiltroConta,
                filtroAno,
                filtroMes,
                filtroPago,
                filtroCdContabiliza));
    }

    public void todosGrupostitulos() {
        this.setListaGruposTitulos(grupoTituloDAO.todosGruposTitulos());
    }

    public void todosTiposTitulos() {
        this.setListaTiposTitulos(tituloDAO.todosTiposTitulos());
    }

    public void todosContratos() {
        this.setLovContrato(contratoDAO.todas());
    }

    public void todasPessoas() {
        this.setLovPessoa(pessoaDAO.todas());
    }

    public List<Pessoa> getLovPessoa() {
        return lovPessoa;
    }

    public void setLovPessoa(List<Pessoa> lovPessoa) {
        this.lovPessoa = lovPessoa;
    }

    public List<Contrato> getLovContrato() {
        return lovContrato;
    }

    public void setLovContrato(List<Contrato> lovContrato) {
        this.lovContrato = lovContrato;
    }

//    public void prepararEdicao() {
//        ramoAtividadeConverter = new RamoAtividadeConverter(Arrays.asList(empresa.getRamoAtividade()));
//    }
    public boolean isTituloSeleciona() {
        if (selectedTitulo != null && selectedTitulo.getDescr() != null) {
            return true;
        } else {
            return false;
        }

    }

    public void limpaNum() {
        this.setNum(null);
    }

    public void verificaGeraParcelas() {

//        if ((this.parcela != 1 && this.parcelaTotal < 2) || this.getNum() != null) {
        if (this.parcela != 1 || this.parcelaTotal < 2 || this.getNum() != null) {
            this.inserirTitulo();
        } else {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('DialogGerarParcelas').show();");
        }

    }

    public void inserirTituloParcelado() {

        System.out.println("<<<TituloMB - inserirTituloParcelado>>>");

        Titulo titulo = new Titulo();
        TituloAgrupamento ta = new TituloAgrupamento();
        TituloAgrupamentoMB tamb = new TituloAgrupamentoMB();
        TituloAgrupamentoDAO tituloAgrupamentoDAO = new TituloAgrupamentoDAO();

        this.vl_parc = (this.getVl() / this.getParcelaTotal());

        tamb.carregarListUltIdTituloAgrupamento();
        Integer idTituloAgrup = tamb.listaIdTituloAgrupamento.get(0); //pause

        if (idTituloAgrup == null) {
            idTituloAgrup = 1;
        }

        for (int i = 1; i <= this.parcelaTotal; i++) {
            limpaNum();
            titulo.setDescr(this.getDescr());
            titulo.setConta(this.getConta());
            titulo.setTipoPagamento(this.getTipoPagamento());
            titulo.setGrupoTitulo(this.getGrupoTitulo());
            titulo.setContrato(this.getContrato());
            titulo.setPessoa(this.getPessoa());

            titulo.setVl(this.getVl());
            titulo.setDtCad(new Date());
            titulo.setUsCad("CADASTRO TITULO");
            titulo.setDtVcto(this.getDtVcto());
            titulo.setDtPgto(this.getDtPgto());
            titulo.setVl(this.vl_parc);
            titulo.setParcela(this.getParcela());
            titulo.setParcelaTotal(i);
            titulo.setObs(this.getObs());

            tituloDAO.inserirTitulo(titulo);

            ta.limpaNum();

            this.carregarListUltIdTitulo();
            Integer auxIdTitulo = this.listaIdTitulo.get(0);

            Titulo titTeste = new Titulo();
            titTeste = tituloDAO.porId(auxIdTitulo);

            ta.setId(idTituloAgrup);
            ta.setTitulo(titTeste);
            ta.setCdTipo(1);
            //tituloAgrupamento.setFkTituloNum(this.getFkTituloNum());
            ta.setDtCad(new Date());
            ta.setUsCad("TITULO AGRUPAMENTO");
            ta.setDtAlt(new Date());

            tituloAgrupamentoDAO.inserir(ta);

            ta.limpaCampos();

        }

        this.todosTitulos();

        this.limpaCampos();

    }

    public void inserirTitulo() {
        System.err.println(" <<<TituloMB inserirTitulo num:" + this.getNum() + " descr:" + this.getDescr() + " >>>");
        Titulo titulo = new Titulo();

        System.out.println(" ");
        if (this.getNum() != null) {
            System.out.println("<<<TituloMB UPDATE novo num:" + this.getNum() + ">>>");

            titulo.setNum(this.getNum());

            titulo.setDtAlt(new Date());
            titulo.setUsAlt("CADASTRO TITULO");
        } else {
            System.out.println("<<<TituloMB INSERT num:" + this.getNum() + ">>>");
        }

        titulo.setDescr(this.getDescr());
        titulo.setConta(this.getConta());
        titulo.setTipoPagamento(this.getTipoPagamento());
        titulo.setGrupoTitulo(this.getGrupoTitulo());
        titulo.setContrato(this.getContrato());
        titulo.setPessoa(this.getPessoa());

        titulo.setVl(this.getVl());
        titulo.setDtCad(new Date());
        titulo.setUsCad("CADASTRO TITULO");
        titulo.setDtVcto(this.getDtVcto());
        titulo.setDtPgto(this.getDtPgto());
        titulo.setVl(this.getVl());
        titulo.setParcela(this.getParcela());
        titulo.setParcelaTotal(this.getParcelaTotal());
        titulo.setObs(this.getObs());

        tituloDAO.inserirTitulo(titulo);

        this.todosTitulos();

        //this.limpaCampos();
        
        //RequestContext.getCurrentInstance().update("titulosDataTable");
    }

    public String textoBtn() {
        this.textBotao = "Não, gerar apenas 1 titulo de " + this.getVl() + "R$";

        return this.textBotao;
    }

    public String textoOutPut() {
        return "Deseja gerar " + this.parcelaTotal + " parcela de " + this.calculaValorParcela() + "R$ cada ?";
    }

    public void excluirTitulo() {
        tituloDAO.excluirTitulo(this.getNum());

        this.todosTitulos();

        this.limpaCampos();
    }

    public Double calculaValorParcela() {

        if (this.parcelaTotal != null && this.parcelaTotal != 0) {
            this.valorParcela = this.vl / this.parcelaTotal;

            return this.valorParcela;
        } else {
            return 0.0;
        }

    }

    public void limpaCampos() {
        this.setNum(null);
        this.setDescr(null);
        this.setDtCad(null);
        this.setDtPgto(null);
        this.setDtVcto(null);
        this.setConta(null);
        this.setGrupoTitulo(null);
        this.setTipoPagamento(null);
        this.setContrato(null);
        this.setPessoa(null);
        this.setObs(null);
        this.setParcela(null);
        this.setParcelaTotal(null);
        this.setVl(null);
    }

    public void actionNova() {
        System.out.println(" <<<TituloMB actionNova >>>");
        this.limpaCampos();
        this.carregarListDescricao();
        this.preencherCamposInserir();
    }

    public void baixarTitulo() {

        tituloDAO.baixarTitulo(this.getNum());

        this.todosTitulos();

        this.limpaCampos();

//        System.out.println(" ");
//        System.out.println(" --- baixarTitulo - inicio");
//        
//        System.out.println("baixarTitulo - titulo num: " + this.getNum());
//        System.out.println("baixarTitulo - titulo selectedTitulo: " + this.selectedTitulo);
//        
//        // 1) Recuperar Dados do Titulo
//        // duvida: poe que tenho que instanciar um novo objeto, sendo que já estou com ele carregaqdo
//        
//        Titulo titulo = tituloDAO.porId(this.getNum());
//        
//        // 2) Recuperar Saldo da Conta
//        Conta conta;
//        conta = contaDAO.porId(this.fk_conta_num);
//        System.out.println("baixarTitulo - antes Conta:" + conta.getDescr() + " Saldo:" + conta.getVlSaldo());
//        conta.setVlSaldo(conta.getVlSaldo() + titulo.getVl());
//        System.out.println("baixarTitulo - depois Conta:" + conta.getDescr() + " Saldo:" + conta.getVlSaldo());
//        contaDAO.inserirConta(conta);
//        
//        
//        // 3) Criar Extrato do Titulo        
//        System.out.println("criar extrato do titulo");
//        
//        // 4) Colocar a Data de Pagamento
//        titulo.setDtPgto(new Date());
//        
//        System.out.println("inserir(alterar) titulo" + titulo);
//        tituloDAO.inserirTitulo(titulo);
//        
//        System.out.println(" --- baixarTitulo - fim");        
//
//        this.todosTitulos();
//        this.limpaCampos();
//        
//        System.out.println(" --- baixarTitulo - fim");
//        System.out.println("  ");
//        
    }

    public void setarFiltros() {
        Date data = new Date();
        Calendar calendario = Calendar.getInstance();

        calendario.setTime(data);
        this.setFiltroMes(calendario.get(Calendar.MONTH) + 1);
        this.setFiltroAno(calendario.get(Calendar.YEAR));

        this.carregarFiltroLovContabiliza();
        this.filtroCdContabiliza = "ALL"; //TODOS

        this.carregarFiltroLovPago();
        this.filtroPago = "ALL"; //TODOS
    }

    public void estornarTitulo() {
        tituloDAO.estornarTitulo(this.getNum());

        this.todosTitulos();

    }

//    ////////////////////////////////////////////////////////////////////////////
//    //////////////////// getters and setters ///////////////////////////////////
    public List<GrupoTitulo> getListaGruposTitulos() {
        return listaGruposTitulos;
    }

    public void setListaGruposTitulos(List<GrupoTitulo> listaGruposTitulos) {
        this.listaGruposTitulos = listaGruposTitulos;
    }

    public List<TipoPagamento> getListaTiposTitulos() {
        return listaTiposTitulos;
    }

    public void setListaTiposTitulos(List<TipoPagamento> listaTiposTitulos) {
        this.listaTiposTitulos = listaTiposTitulos;
    }

    public List<Titulo> getListaTitulos() {
        return listaTitulos;
    }

    public void setListaTitulos(List<Titulo> listaTitulos) {
        this.listaTitulos = listaTitulos;
    }

    public List<Conta> getListaContas() {
        return listaContas;
    }

    public void setListaContas(List<Conta> listaContas) {
        this.listaContas = listaContas;
    }

    public Titulo getSelectedTitulo() {
        return selectedTitulo;
    }

    public List<Conta> getFiltroListaContas() {
        return filtroListaContas;
    }

    public void setFiltroListaContas(List<Conta> filtroListaContas) {
        this.filtroListaContas = filtroListaContas;
    }

    public Conta getFiltroConta() {
        return filtroConta;
    }

    public void setFiltroConta(Conta filtroConta) {
        this.filtroConta = filtroConta;
    }

    public void setSelectedTitulo(Titulo selectedTitulo) {
        System.out.println(" <<<TituloMb setSelectedTitulo>>>");

        this.selectedTitulo = selectedTitulo;

        if (selectedTitulo == null) {
            System.out.println("não carregar info, pois nao tem titulo selecionado");
            return;
        }

        System.out.println("carregar info");
        //preencher informacoes para mostrar no Show
        this.setShowConta(selectedTitulo.getConta().getDescr() + ": " + selectedTitulo.getConta().getVlSaldo());

        this.setShowContaPagar(0.0);
        this.setShowContaReceber(0.0);

        Integer wContaIdSelecionada = selectedTitulo.getConta().getNum();

        for (Titulo listaTitulo : listaTitulos) {
            if (listaTitulo.getConta().getNum() == wContaIdSelecionada) {
                if (listaTitulo.getGrupoTitulo().getCdTipo().equals("P")) {
                    this.setShowContaPagar(this.getShowContaPagar() + listaTitulo.getVl());
                } else {
                    this.setShowContaReceber(this.getShowContaReceber() + listaTitulo.getVl());
                }
            }
        }
    }

    public void copiarTitulo() {
        System.out.println(" <<<TituloMB copiarTitulo (" + selectedTitulo.getDescr() + ")>>>");

        limpaCampos();

        this.setNum(null);
        this.setConta(selectedTitulo.getConta());
        this.setDescr(selectedTitulo.getDescr() + " - testando");
        this.setGrupoTitulo(selectedTitulo.getGrupoTitulo());
        this.setTipoPagamento(selectedTitulo.getTipoPagamento());
        this.setContrato(selectedTitulo.getContrato());
        this.setPessoa(selectedTitulo.getPessoa());
        this.setVl(selectedTitulo.getVl());
        this.setUsCad(selectedTitulo.getUsCad());
        this.setDtCad(selectedTitulo.getDtCad());
        this.setDtVcto(selectedTitulo.getDtVcto());
        this.setDtPgto(selectedTitulo.getDtPgto());
        this.setParcela(selectedTitulo.getParcela());
        this.setParcelaTotal(selectedTitulo.getParcelaTotal());
        this.setObs(selectedTitulo.getObs());

        System.out.println("TituloMB copiarTitulo - copiado com sucesso");

    }

    public void carregarListDescricao() {
        this.setListaDescricao(tituloDAO.listaDescricao());
    }

    public void carregarListUltIdTitulo() {
        this.setListaIdTitulo(tituloDAO.ultIdTitulo());
    }

    public List<String> completeText(String texto) {
        System.err.println(" <<<TituloMB completeText texto:" + texto + ">>>");

        List<String> wListaDescricaoParcial = new ArrayList<>();

        for (String string : listaDescricao) {
            if (string.contains(texto)) {
                System.err.println(" <<<TituloMB completeText texto:" + texto + " contem na descricao (" + string + ")>>>");
                wListaDescricaoParcial.add(string);
            } else {
                System.err.println(" <<<TituloMB completeText texto:" + texto + " NAO contem na descricao (" + string + ")>>>");
            }
        }

        return wListaDescricaoParcial;
    }

//    public List<Integer> ultIdTitulo() {
//        System.err.println(" <<<TituloMB ultIdTitulo>>>");
//
//        this.carregarListUltIdTitulo();
//
//        List<Integer> wListaIdParcial = new ArrayList<>();
//
//        for (Integer integer : listaIdTitulo) {
//
//            wListaIdParcial.add(integer);
//
//        }
//        return wListaIdParcial;
//    }
    public void descrOnChange(AjaxBehaviorEvent event) {
        System.out.println(" <<<TituloMB descrOnChange>>>");
        System.out.println(" <<<TituloMB descrOnChange descr:" + this.getDescr() + ">>>");

        Integer wContaId = null;

        Titulo auxTitulo = new Titulo();

        // filtrar por conta ou nao
        if (this.conta != null) {
            wContaId = this.getConta().getNum();
        }

        auxTitulo = tituloDAO.retornarUltTituloPorDescr(wContaId, this.getDescr());

        if (auxTitulo == null) {
            System.out.println(" <<<TituloMB descrOnChange titulo não encontrado com essa descricao");
        } else {
            System.out.println(" <<<TituloMB descrOnChange titulo:" + auxTitulo.toString() + ">>>");
            // preencher campos
            // alterar a conta apenas se não informou
            if (this.getConta() == null) {
                this.setConta(auxTitulo.getConta());
            }

            // outros
            this.setGrupoTitulo(auxTitulo.getGrupoTitulo());
            this.setTipoPagamento(auxTitulo.getTipoPagamento());
        }

        //this.setObs("Teste refresh de tela");
    }

    private void preencherCamposInserir() {
        System.out.println(" <<<TituloMB preencherCamposInserir>>>");
        // recuperar a conta que foi selecionada, pois entende-se que é para cadastrar titulo daquela conta
        if (selectedTitulo != null) {
            this.setConta(selectedTitulo.getConta());
        }

        this.setDtCad(new Date());
        this.setDtVcto(new Date());
        this.setParcela(1);
        this.setParcelaTotal(1);
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public GrupoTitulo getGrupoTitulo() {
        return grupoTitulo;
    }

    public void setGrupoTitulo(GrupoTitulo grupoTitulo) {
        this.grupoTitulo = grupoTitulo;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Double getVl() {
        return vl;
    }

    public void setVl(Double vl) {
        this.vl = vl;
    }

    public Date getDtCad() {
        return dtCad;
    }

    public void setDtCad(Date dtCad) {
        this.dtCad = dtCad;
    }

    public String getUsCad() {
        return usCad;
    }

    public void setUsCad(String usCad) {
        this.usCad = usCad;
    }

    public Date getDtVcto() {
        return dtVcto;
    }

    public void setDtVcto(Date dtVcto) {
        this.dtVcto = dtVcto;
    }

    public Date getDtPgto() {
        return dtPgto;
    }

    public void setDtPgto(Date dtPgto) {
        this.dtPgto = dtPgto;
    }

    public Integer getParcela() {
        return parcela;
    }

    public void setParcela(Integer parcela) {
        this.parcela = parcela;
    }

    public Integer getParcelaTotal() {
        return parcelaTotal;
    }

    public void setParcelaTotal(Integer parcelaTotal) {
        this.parcelaTotal = parcelaTotal;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Integer getFiltroMes() {
        return filtroMes;
    }

    public void setFiltroMes(Integer filtroMes) {
        this.filtroMes = filtroMes;
    }

    public Integer getFiltroAno() {
        return filtroAno;
    }

    public void setFiltroAno(Integer filtroAno) {
        this.filtroAno = filtroAno;
    }

    private void carregarFiltroLovContabiliza() {
        this.setFiltroLovCdContabiliza(lovDAO.carregarLov("ALL SIM NAO", false));
    }

    private void carregarFiltroLovPago() {
        this.setFiltroLovPago(lovDAO.carregarLov("ALL SIM NAO", false));
    }

    public String getFiltroCdContabiliza() {
        return filtroCdContabiliza;
    }

    public void setFiltroCdContabiliza(String filtroCdContabiliza) {
        this.filtroCdContabiliza = filtroCdContabiliza;
    }

    public String getFiltroPago() {
        return filtroPago;
    }

    public void setFiltroPago(String filtroPago) {
        this.filtroPago = filtroPago;
    }

    public List<LovDAO> getFiltroLovCdContabiliza() {
        return filtroLovCdContabiliza;
    }

    public void setFiltroLovCdContabiliza(List<LovDAO> filtroLovCdContabiliza) {
        this.filtroLovCdContabiliza = filtroLovCdContabiliza;
    }

    public List<LovDAO> getFiltroLovPago() {
        return filtroLovPago;
    }

    public void setFiltroLovPago(List<LovDAO> filtroLovPago) {
        this.filtroLovPago = filtroLovPago;
    }

    public String getTeste() {
        return teste;
    }

    public void setTeste(String teste) {
        this.teste = teste;
    }

    public String getShowConta() {
        return showConta;
    }

    public void setShowConta(String showConta) {
        this.showConta = showConta;
    }

    public Double getShowContaPagar() {
        return showContaPagar;
    }

    public void setShowContaPagar(Double showContaPagar) {
        this.showContaPagar = showContaPagar;
    }

    public Double getShowContaReceber() {
        return showContaReceber;
    }

    public void setShowContaReceber(Double showContaReceber) {
        this.showContaReceber = showContaReceber;
    }

    public List<String> getListaDescricao() {
        return listaDescricao;
    }

    public void setListaDescricao(List<String> listaDescricao) {
        this.listaDescricao = listaDescricao;
    }

    public List<Integer> getListaIdTitulo() {
        return listaIdTitulo;
    }

    public void setListaIdTitulo(List<Integer> listaIdTitulo) {
        this.listaIdTitulo = listaIdTitulo;
    }

}
