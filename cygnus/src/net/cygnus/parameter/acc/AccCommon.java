package net.cygnus.parameter.acc;

import id.co.nds.beans.Actor;

import java.io.Serializable;

public class AccCommon extends Actor implements Serializable, Cloneable {

	private static final long serialVersionUID = -8840333371833158523L;

	private Long id;
	private Long parentId;
	private String accLv1Id;
	private String accLv1Name;
	private String accLv2Id;
	private String accLv2Name;
	private String accLv3Id;
	private String accLv3Name;
	private String accLv4Id;
	private String accLv4Name;
	private String post;
	private String heading;
	private String genericAcc;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getAccLv1Id() {
		return accLv1Id;
	}
	public void setAccLv1Id(String accLv1Id) {
		this.accLv1Id = accLv1Id;
	}
	public String getAccLv1Name() {
		return accLv1Name;
	}
	public void setAccLv1Name(String accLv1Name) {
		this.accLv1Name = accLv1Name;
	}
	public String getAccLv2Id() {
		return accLv2Id;
	}
	public void setAccLv2Id(String accLv2Id) {
		this.accLv2Id = accLv2Id;
	}
	public String getAccLv2Name() {
		return accLv2Name;
	}
	public void setAccLv2Name(String accLv2Name) {
		this.accLv2Name = accLv2Name;
	}
	public String getAccLv3Id() {
		return accLv3Id;
	}
	public void setAccLv3Id(String accLv3Id) {
		this.accLv3Id = accLv3Id;
	}
	public String getAccLv3Name() {
		return accLv3Name;
	}
	public void setAccLv3Name(String accLv3Name) {
		this.accLv3Name = accLv3Name;
	}
	public String getAccLv4Id() {
		return accLv4Id;
	}
	public void setAccLv4Id(String accLv4Id) {
		this.accLv4Id = accLv4Id;
	}
	public String getAccLv4Name() {
		return accLv4Name;
	}
	public void setAccLv4Name(String accLv4Name) {
		this.accLv4Name = accLv4Name;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getGenericAcc() {
		return genericAcc;
	}
	public void setGenericAcc(String genericAcc) {
		this.genericAcc = genericAcc;
	}
	public AccCommon clone() throws CloneNotSupportedException {
		return (AccCommon) super.clone();
	}	
}
