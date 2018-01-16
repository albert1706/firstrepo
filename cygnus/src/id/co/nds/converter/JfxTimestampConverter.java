package id.co.nds.converter;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;

public class JfxTimestampConverter extends StrutsTypeConverter {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public Object convertFromString(Map context, String[] values, Class toClass) throws TypeConversionException {
    	System.out.println("DAVID");
        try{
               if (values == null || values.length == 0) {
                    return null;
                }
               return new java.sql.Timestamp(sdf.parse(values[0]).getTime());
        }catch(Exception e){        
            throw new TypeConversionException(e);
        }
    }

    @Override
    public String convertToString(Map context, Object object)  throws TypeConversionException  {
    	System.out.println("DCH");
        try{
            if(object instanceof Timestamp){
                return sdf.format(new Date(((Timestamp)object).getTime()));
            }
            return "";
        }catch(Exception e){
            throw new TypeConversionException(e);
        }
    }
	
}
