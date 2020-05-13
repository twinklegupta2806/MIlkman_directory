package variationtable;

public class VariationBean {
String cname;
float cowvar;
float buffvar;
String date;

public VariationBean()
{}
public VariationBean(String cname, float cowvar, float buffvar, String date) {
	super();
	this.cname = cname;
	this.cowvar = cowvar;
	this.buffvar = buffvar;
	this.date = date;
}
public String getCname() {
	return cname;
}
public void setCname(String cname) {
	this.cname = cname;
}
public float getCowvar() {
	return cowvar;
}
public void setCowvar(float cowvar) {
	this.cowvar = cowvar;
}
public float getBuffvar() {
	return buffvar;
}
public void setBuffvar(float buffvar) {
	this.buffvar = buffvar;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}

}
