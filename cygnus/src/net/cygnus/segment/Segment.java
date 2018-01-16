package net.cygnus.segment;

import id.co.nds.beans.Actor;

public class Segment extends Actor {

	public static final String TABLENAME = "MS_SEGMENT";
	
	private String segmentId;
	private String segmentName;
	
	public Segment() {
		
	}
	
	public Segment(String segmentId) {
		this.segmentId = segmentId;
	}
	
	public String getSegmentId() {
		return segmentId;
	}
	public void setSegmentId(String segmentId) {
		this.segmentId = segmentId;
	}
	public String getSegmentName() {
		return segmentName;
	}
	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}
	
}
