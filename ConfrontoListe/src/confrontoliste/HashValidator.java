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

public class HashValidator {
        private Pattern pattern;
	private Matcher matcher;
 
	private static final String 
	  DOMAIN_NAME_PATTERN = "^[a-fA-F0-9]{32}$";
 
	public HashValidator() {
		pattern = Pattern.compile(DOMAIN_NAME_PATTERN);
	}
 
	public boolean validate(final String hex) {
 
		matcher = pattern.matcher(hex);
		return matcher.matches();
 
	}
}
