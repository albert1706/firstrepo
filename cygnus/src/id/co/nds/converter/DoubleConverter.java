package id.co.nds.converter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class DoubleConverter extends StrutsTypeConverter {

	@Override
	public Object convertFromString(Map context, String[] values, Class clazz) {
		// TODO Auto-generated method stub
		
		String temp = values[0];
		String cleanValue = temp;
		if (temp != null && temp.startsWith("Rp.")) {
			cleanValue = temp.replace("Rp.", "").trim();
		} 
		 
		NumberFormat nf = NumberFormat.getInstance();
		Double currencyVal;
		try {
			currencyVal = nf.parse(cleanValue).doubleValue();
			return currencyVal;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return values[0];
	}

	@Override
	public String convertToString(Map context, Object value) {
		// TODO Auto-generated method stub
		
		DecimalFormat format = new DecimalFormat("###,###,###,###.00");
		String out = format.format(value);
		
		return out;
	}

}
