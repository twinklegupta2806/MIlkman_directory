package billingconsole;

public class BillingBean {
String name;
String dos;
String doe;
float amount;
float cqtotal;
float bqtotal;
String status;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDos() {
	return dos;
}
public void setDos(String dos) {
	this.dos = dos;
}
public String getDoe() {
	return doe;
}
public void setDoe(String doe) {
	this.doe = doe;
}
public float getAmount() {
	return amount;
}
public void setAmount(float amount) {
	this.amount = amount;
}
public float getCqtotal() {
	return cqtotal;
}
public void setCqtotal(float cqtotal) {
	this.cqtotal = cqtotal;
}
public float getBqtotal() {
	return bqtotal;
}
public void setBqtotal(float bqtotal) {
	this.bqtotal = bqtotal;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public BillingBean(String name, String dos, String doe, float amount, float cqtotal, float bqtotal, String status) {
	super();
	this.name = name;
	this.dos = dos;
	this.doe = doe;
	this.amount = amount;
	this.cqtotal = cqtotal;
	this.bqtotal = bqtotal;
	this.status = status;
}

}
