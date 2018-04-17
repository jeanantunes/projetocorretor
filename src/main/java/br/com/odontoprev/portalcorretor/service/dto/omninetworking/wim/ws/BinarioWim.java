//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "binarioWim",
        propOrder = {"nome", "bytes", "encoding", "mimeType"}
)
public class BinarioWim {
    protected String nome;
    protected byte[] bytes;
    protected String encoding;
    protected String mimeType;

    public BinarioWim() {
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String var1) {
        this.nome = var1;
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public void setBytes(byte[] var1) {
        this.bytes = var1;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void setEncoding(String var1) {
        this.encoding = var1;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public void setMimeType(String var1) {
        this.mimeType = var1;
    }
}
