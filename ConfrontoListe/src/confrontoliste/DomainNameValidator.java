/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package confrontoliste;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author claudio
 */
public class DomainNameValidator {
    
    	private final Pattern pattern;
	private Matcher matcher;
 
	private static final String 
	  DOMAIN_NAME_PATTERN = "(^(https?):\\/\\/|^)(([^\\.]+)\\.([^\\.]+\\.[^\\/$]+)|([^\\.]+\\.[^\\/$]+))\\/?$";
 
	public DomainNameValidator() {
		pattern = Pattern.compile(DOMAIN_NAME_PATTERN);
	}
 
	public boolean validate(final String hex) {
 
		matcher = pattern.matcher(hex);
		return matcher.matches();
 
	}
}


// ^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]
// ^((http|https):\\/\\/)?(([a-zA-Z]{1})|([a-zA-Z]{1}[a-zA-Z]{1})|([a-zA-Z]{1}[0-9]{1})|([0-9]{1}[a-zA-Z]{1})|([a-zA-Z0-9][a-zA-Z0-9-_]{1,61}[a-zA-Z0-9]))\\.([a-zA-Z]{2,6}|[a-zA-Z0-9-_]{2,30}\\.[a-zA-Z]{2,3}(\\.[a-zA-Z]{2,3})?)$