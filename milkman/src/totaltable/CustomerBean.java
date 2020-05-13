package totaltable;

public class CustomerBean {
String name;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public float getCquan() {
	return cquan;
}
public void setCquan(float cquan) {
	this.cquan = cquan;
}
public float getCprice() {
	return cprice;
}
public void setCprice(float cprice) {
	this.cprice = cprice;
}
public float getBquan() {
	return bquan;
}
public void setBquan(float bquan) {
	this.bquan = bquan;
}
public float getBprice() {
	return bprice;
}
public void setBprice(float bprice) {
	this.bprice = bprice;
}
public String getDos() {
	return dos;
}
public void setDos(String dos) {
	this.dos = dos;
}
/*public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}*/
String mobile;
String address;
float cquan;
float cprice;
float bquan;
float bprice;
String dos;
//String image;
public CustomerBean(){}
public CustomerBean(String name, String mobile, String address, float cquan, float cprice, float bquan, float bprice,
		String dos) {
	super();
	this.name = name;
	this.mobile = mobile;
	this.address = address;
	this.cquan = cquan;
	this.cprice = cprice;
	this.bquan = bquan;
	this.bprice = bprice;
	this.dos = dos;
	//this.image = image;
}

}
