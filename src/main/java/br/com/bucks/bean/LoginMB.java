package br.com.bucks.bean;

import br.com.bucks.DAO.LoginDAO;
import br.com.bucks.model.Login;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
//@ViewScoped
@SessionScoped
public class LoginMB implements Serializable {

    private Login login = new Login();
    Boolean usuarioLogado = false;

    public String efetuaLogin() {

        System.out.println("fazendo login do usuario " + this.login.getEmail());

        FacesContext context = FacesContext.getCurrentInstance();

        context.getExternalContext().getFlash().setKeepMessages(false);
        RequestContext.getCurrentInstance().update("formLogin");

        boolean existe = new LoginDAO().existe(this.login);
        if (existe) {
            System.out.println("Usuário existe !");

            this.setUsuarioLogado(true);

            return "menu?faces-redirect=true";
        } else {

            this.setUsuarioLogado(false);

            System.out.println("Usuário não existe !");
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null, new FacesMessage("Email ou senha incorretos"));
            RequestContext.getCurrentInstance().update("formLogin");
            //context.update("formOcorrenciaqt:statusServer");

            return null;
        }

    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Boolean getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Boolean usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

}
