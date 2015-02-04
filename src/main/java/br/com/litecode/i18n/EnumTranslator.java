package br.com.litecode.i18n;

import javax.faces.context.FacesContext;
import java.util.ResourceBundle;

/**
 * <p>
 * Collection of utility methods for retrieving the localized string from a message resource bundle.
 * </p>
 * <p>
 * An optional initialization parameter (litefaces.ENUM_MESSAGE_BUNDLE) can be defined in web.xml to specify the name of the bundle file where the translated enum values can be found.
 * If the parameter is not specified the default message bundle file will be used.
 * </p>
 * <h3>Configuration (optional)</h3>
 * <p>Example: In order to define the enum keys in a separate file (e.g. br/com/litecode/enums.properties) the following configuration should be added to web.xml</p>
 *
 * <pre>
 * 	&lt;context-param&gt;
 *		&lt;param-name&gt;litefaces.ENUM_MESSAGE_BUNDLE&lt;/param-name&gt;
 *		&lt;param-value&gt;br.com.litecode.enums&lt;/param-value&gt;
 * 	&lt;/context-param&gt;
 * </pre>
 *
 * @author Thiago Wolff
 */
public final class EnumTranslator {
	private static final String ENUM_MESSAGE_BUNDLE = "litefaces.ENUM_MESSAGE_BUNDLE";
	private static final String KEY_PREFIX = "enum";
	private static final ResourceBundle messagesBundle;

	static {
		String enumMessageBundle = FacesContext.getCurrentInstance().getExternalContext().getInitParameter(ENUM_MESSAGE_BUNDLE);
		if (enumMessageBundle == null){
			messagesBundle = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle());
		} else {
			messagesBundle = ResourceBundle.getBundle(enumMessageBundle);
		}
	}

	public static String getEnum(Enum<?> e) {
		if (e == null) {
			return "";
		}

		String enumClass = getEnumClassName(e);
		String enumKey = KEY_PREFIX + "." + enumClass + "." + e.name().toLowerCase();

		if (!messagesBundle.containsKey(enumKey)) {
			return e.toString();
		}

		return messagesBundle.getString(enumKey);
	}

	public static String getEnumStyle(Enum<?> e) {
		if (e == null) {
			return "";
		}
		String enumClass = getEnumClassName(e);
		String enumStyle = KEY_PREFIX + "-" + enumClass + "-" + e.name().toLowerCase();
		return enumStyle;
	}

	private static String getEnumClassName(Enum<?> e) {
		String enumClass = e.getClass().getSimpleName();
		return String.valueOf(enumClass.charAt(0)).toLowerCase() + enumClass.substring(1);
	}
}