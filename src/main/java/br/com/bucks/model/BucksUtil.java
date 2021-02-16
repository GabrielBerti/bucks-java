/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bucks.model;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author La Laina
 */
public class BucksUtil {

    private Integer id;

    private String descr1;
    private String descr2;
    private String descr3;
    private String descr4;
    private String descr5;

    private Double vl1;
    private Double vl2;
    private Double vl3;
    private Double vl4;
    private Double vl5;

    private Integer cd1;
    private Integer cd2;
    private Integer cd3;
    private Integer cd4;
    private Integer cd5;

    private Date data1;
    private Date data2;
    private Date data3;
    private Date data4;
    private Date data5;

    private GrupoTitulo grupoTitulo;

    public BucksUtil() {

    }

    //////////////////////////////////////////////////////////////////////////// 
    /////////////////// Getters and Setters ////////////////////////////////////
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescr1() {
        return descr1;
    }

    public void setDescr1(String descr1) {
        this.descr1 = descr1;
    }

    public String getDescr2() {
        return descr2;
    }

    public void setDescr2(String descr2) {
        this.descr2 = descr2;
    }

    public String getDescr3() {
        return descr3;
    }

    public void setDescr3(String descr3) {
        this.descr3 = descr3;
    }

    public String getDescr4() {
        return descr4;
    }

    public void setDescr4(String descr4) {
        this.descr4 = descr4;
    }

    public String getDescr5() {
        return descr5;
    }

    public void setDescr5(String descr5) {
        this.descr5 = descr5;
    }

    public Double getVl1() {
        return vl1;
    }

    public void setVl1(Double vl1) {
        this.vl1 = vl1;
    }

    public Double getVl2() {
        return vl2;
    }

    public void setVl2(Double vl2) {
        this.vl2 = vl2;
    }

    public Double getVl3() {
        return vl3;
    }

    public void setVl3(Double vl3) {
        this.vl3 = vl3;
    }

    public Double getVl4() {
        return vl4;
    }

    public void setVl4(Double vl4) {
        this.vl4 = vl4;
    }

    public Double getVl5() {
        return vl5;
    }

    public void setVl5(Double vl5) {
        this.vl5 = vl5;
    }

    public Integer getCd1() {
        return cd1;
    }

    public void setCd1(Integer cd1) {
        this.cd1 = cd1;
    }

    public Integer getCd2() {
        return cd2;
    }

    public void setCd2(Integer cd2) {
        this.cd2 = cd2;
    }

    public Integer getCd3() {
        return cd3;
    }

    public void setCd3(Integer cd3) {
        this.cd3 = cd3;
    }

    public Integer getCd4() {
        return cd4;
    }

    public void setCd4(Integer cd4) {
        this.cd4 = cd4;
    }

    public Integer getCd5() {
        return cd5;
    }

    public void setCd5(Integer cd5) {
        this.cd5 = cd5;
    }

    public Date getData1() {
        return data1;
    }

    public void setData1(Date data1) {
        this.data1 = data1;
    }

    public Date getData2() {
        return data2;
    }

    public void setData2(Date data2) {
        this.data2 = data2;
    }

    public Date getData3() {
        return data3;
    }

    public void setData3(Date data3) {
        this.data3 = data3;
    }

    public Date getData4() {
        return data4;
    }

    public void setData4(Date data4) {
        this.data4 = data4;
    }

    public Date getData5() {
        return data5;
    }

    public void setData5(Date data5) {
        this.data5 = data5;
    }

    public GrupoTitulo getGrupoTitulo() {
        return grupoTitulo;
    }

    public void setGrupoTitulo(GrupoTitulo grupoTitulo) {
        this.grupoTitulo = grupoTitulo;
    }
    
    public Integer retornarAnoAtual(){
        Date data = new Date();
        Calendar calendario = Calendar.getInstance();

        calendario.setTime(data);
        return calendario.get(Calendar.YEAR);
    }
    
    public Integer retornarMesAtual(){
        Date data = new Date();
        Calendar calendario = Calendar.getInstance();

        calendario.setTime(data);
        return calendario.get(Calendar.MONTH) + 1;
    }    
    
    public Integer retornarAnoDeUmaData(Date pData){
        Calendar calendario = Calendar.getInstance();

        calendario.setTime(pData);
        return calendario.get(Calendar.YEAR);
    }        
    
    public Integer retornarMesDeUmaData(Date pData){
        Calendar calendario = Calendar.getInstance();

        calendario.setTime(pData);
        return calendario.get(Calendar.MONTH) + 1;
    }            
    
}
