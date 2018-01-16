package net.cygnus.report.journal;

import java.io.Serializable;
import java.sql.Timestamp;

import net.cygnus.coa.Coa;

public class Journal extends Coa implements Serializable {

	// list to be shown in Journal : 
	// nama transaksi
	// kode transaksi
	// tanggal transaksi
	// komisi/segment
	// kode coa
	// name coa
	// kode akun
	// debet
	// kredit
	// keterangan transaksi
	
	private static final long serialVersionUID = -8315103277883788998L;
	private Long trxId;
	private String trxName; 
	private Timestamp trxDate;
	private String trxType;
	private String description;
	private Double debet;
	private Double credit;
	private String segmentName;
	
	public Long getTrxId() {
		return trxId;
	}
	public void setTrxId(Long trxId) {
		this.trxId = trxId;
	}
	public String getTrxName() {
		return trxName;
	}
	public void setTrxName(String trxName) {
		this.trxName = trxName;
	}
	public Timestamp getTrxDate() {
		return trxDate;
	}
	public void setTrxDate(Timestamp trxDate) {
		this.trxDate = trxDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getDebet() {
		return debet;
	}
	public void setDebet(Double debet) {
		this.debet = debet;
	}
	public Double getCredit() {
		return credit;
	}
	public void setCredit(Double credit) {
		this.credit = credit;
	}
	public String getSegmentName() {
		return segmentName;
	}
	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}
	public String getTrxType() {
		return trxType;
	}
	public void setTrxType(String trxType) {
		this.trxType = trxType;
	}
	
}
