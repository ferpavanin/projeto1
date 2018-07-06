/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabelas;

/**
 *
 * @author ferpa
 */
public class Clientes {

    private int idCliente = 0;
    private String razao = "";
    private long cnpjcpf = 0;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public long getCnpjcpf() {
        return cnpjcpf;
    }

    public void setCnpjcpf(long cnpjcpf) {
        this.cnpjcpf = cnpjcpf;
    }

}
