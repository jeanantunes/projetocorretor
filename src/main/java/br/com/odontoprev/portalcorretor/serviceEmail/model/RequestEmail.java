/**
 * SendEmail
 * API para envio de e-mail
 *
 * OpenAPI spec version: 1.0
 * Contact: arquiteteturati@odontoprev.com.br
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package br.com.odontoprev.portalcorretor.serviceEmail.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import io.swagger.annotations.ApiModelProperty;


/**
 * RequestEmail
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2018-02-26T13:41:47.810-03:00")
public class RequestEmail   {
  @SerializedName("sender")
  private String sender = null;

  @SerializedName("senderName")
  private String senderName = null;

  @SerializedName("recepients")
  private List<String> recepients = new ArrayList<String>();

  @SerializedName("recepientName")
  private String recepientName = null;

  @SerializedName("recepientsCC")
  private List<String> recepientsCC = new ArrayList<String>();

  @SerializedName("recepientsBCC")
  private List<String> recepientsBCC = new ArrayList<String>();

  @SerializedName("subject")
  private String subject = null;

  @SerializedName("type")
  private String type = null;

  @SerializedName("body")
  private String body = null;

  @SerializedName("attachment")
  private Attachment attachment = null;

  public RequestEmail sender(String sender) {
    this.sender = sender;
    return this;
  }

   /**
   * E-mail do remetente
   * @return sender
  **/
  @ApiModelProperty(example = "null", required = true, value = "E-mail do remetente")
  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public RequestEmail senderName(String senderName) {
    this.senderName = senderName;
    return this;
  }

   /**
   * E-mail com nome do remetente
   * @return senderName
  **/
  @ApiModelProperty(example = "null", required = true, value = "E-mail com nome do remetente")
  public String getSenderName() {
    return senderName;
  }

  public void setSenderName(String senderName) {
    this.senderName = senderName;
  }

  public RequestEmail recepients(List<String> recepients) {
    this.recepients = recepients;
    return this;
  }

  public RequestEmail addRecepientsItem(String recepientsItem) {
    this.recepients.add(recepientsItem);
    return this;
  }

   /**
   * E-mails dos destinat�rios
   * @return recepients
  **/
  @ApiModelProperty(example = "null", required = true, value = "E-mails dos destinat�rios")
  public List<String> getRecepients() {
    return recepients;
  }

  public void setRecepients(List<String> recepients) {
    this.recepients = recepients;
  }

  public RequestEmail recepientName(String recepientName) {
    this.recepientName = recepientName;
    return this;
  }

   /**
   * Nome do destinat�rio
   * @return recepientName
  **/
  @ApiModelProperty(example = "null", required = true, value = "Nome do destinat�rio")
  public String getRecepientName() {
    return recepientName;
  }

  public void setRecepientName(String recepientName) {
    this.recepientName = recepientName;
  }

  public RequestEmail recepientsCC(List<String> recepientsCC) {
    this.recepientsCC = recepientsCC;
    return this;
  }

  public RequestEmail addRecepientsCCItem(String recepientsCCItem) {
    this.recepientsCC.add(recepientsCCItem);
    return this;
  }

   /**
   * E-mails dos destinat�rios em c�pia
   * @return recepientsCC
  **/
  @ApiModelProperty(example = "null", value = "E-mails dos destinat�rios em c�pia")
  public List<String> getRecepientsCC() {
    return recepientsCC;
  }

  public void setRecepientsCC(List<String> recepientsCC) {
    this.recepientsCC = recepientsCC;
  }

  public RequestEmail recepientsBCC(List<String> recepientsBCC) {
    this.recepientsBCC = recepientsBCC;
    return this;
  }

  public RequestEmail addRecepientsBCCItem(String recepientsBCCItem) {
    this.recepientsBCC.add(recepientsBCCItem);
    return this;
  }

   /**
   * E-mails dos destinat�rios em c�pia oculta
   * @return recepientsBCC
  **/
  @ApiModelProperty(example = "null", value = "E-mails dos destinat�rios em c�pia oculta")
  public List<String> getRecepientsBCC() {
    return recepientsBCC;
  }

  public void setRecepientsBCC(List<String> recepientsBCC) {
    this.recepientsBCC = recepientsBCC;
  }

  public RequestEmail subject(String subject) {
    this.subject = subject;
    return this;
  }

   /**
   * Assunto
   * @return subject
  **/
  @ApiModelProperty(example = "null", required = true, value = "Assunto")
  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public RequestEmail type(String type) {
    this.type = type;
    return this;
  }

   /**
   * Tipo
   * @return type
  **/
  @ApiModelProperty(example = "text/html", required = true, value = "Tipo")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public RequestEmail body(String body) {
    this.body = body;
    return this;
  }

   /**
   * Corpo do e-mail
   * @return body
  **/
  @ApiModelProperty(example = "null", required = true, value = "Corpo do e-mail")
  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public RequestEmail attachment(Attachment attachment) {
    this.attachment = attachment;
    return this;
  }

   /**
   * Anexo
   * @return attachment
  **/
  @ApiModelProperty(example = "null", value = "Anexo")
  public Attachment getAttachment() {
    return attachment;
  }

  public void setAttachment(Attachment attachment) {
    this.attachment = attachment;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RequestEmail requestEmail = (RequestEmail) o;
    return Objects.equals(this.sender, requestEmail.sender) &&
        Objects.equals(this.senderName, requestEmail.senderName) &&
        Objects.equals(this.recepients, requestEmail.recepients) &&
        Objects.equals(this.recepientName, requestEmail.recepientName) &&
        Objects.equals(this.recepientsCC, requestEmail.recepientsCC) &&
        Objects.equals(this.recepientsBCC, requestEmail.recepientsBCC) &&
        Objects.equals(this.subject, requestEmail.subject) &&
        Objects.equals(this.type, requestEmail.type) &&
        Objects.equals(this.body, requestEmail.body) &&
        Objects.equals(this.attachment, requestEmail.attachment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sender, senderName, recepients, recepientName, recepientsCC, recepientsBCC, subject, type, body, attachment);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RequestEmail {\n");
    
    sb.append("    sender: ").append(toIndentedString(sender)).append("\n");
    sb.append("    senderName: ").append(toIndentedString(senderName)).append("\n");
    sb.append("    recepients: ").append(toIndentedString(recepients)).append("\n");
    sb.append("    recepientName: ").append(toIndentedString(recepientName)).append("\n");
    sb.append("    recepientsCC: ").append(toIndentedString(recepientsCC)).append("\n");
    sb.append("    recepientsBCC: ").append(toIndentedString(recepientsBCC)).append("\n");
    sb.append("    subject: ").append(toIndentedString(subject)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    body: ").append(toIndentedString(body)).append("\n");
    sb.append("    attachment: ").append(toIndentedString(attachment)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

