package br.com.litecode.i18n;

import javax.faces.context.FacesContext;
import java.util.ResourceBundle;

/**
 * <p>
 * Collection of utility methods for retrieving the localized string from a message resource bundle.
 * </p>
 * <p>
 * An optional initialization parameter (litefaces.ENUM_BUNDLE_PARAM) can be defined in web.xml to specify the name of the bundle file where the translated enum values can be found.
 * If the parameter is not specified the default message bundle file will be used.
 * </p>
 * <h3>Configuration (optional)</h3>
 * <h4>1. Resource message bundle name</h4>
 * <p>Example: In order to define the enum keys in a separate file (e.g. br/com/litecode/enums.properties) the following configuration should be added to web.xml</p>
 *
 * <pre>
 * 	&lt;context-param&gt;
 *		&lt;param-name&gt;litefaces.ENUM_BUNDLE_PARAM&lt;/param-name&gt;
 *		&lt;param-value&gt;br.com.litecode.enums&lt;/param-value&gt;
 * 	&lt;/context-param&gt;
 * </pre>
 *
 * <h4>2. Message key prefix</h4>
 * <p>Example: In order to prefix the enum keys with the word 'enum' the following configuration should be added to web.xml</p>
 *
 * <pre>
 * 	&lt;context-param&gt;
 *		&lt;param-name&gt;litefaces.ENUM_KEY_PREFIX&lt;/param-name&gt;
 *		&lt;param-value&gt;enum&lt;/param-value&gt;
 * 	&lt;/context-param&gt;
 * </pre>
 *
 * @author Thiago Wolff
 */
public final class EnumTranslator {
	private static final String ENUM_MESSAGE_BUNDLE_PARAM = "litefaces.ENUM_MESSAGE_BUNDLE";
	private static final String ENUM_KEY_PREFIX_PARAM = "litefaces.ENUM_KEY_PREFIX";

	private static ResourceBundle resourceBundle;
	private static String enumMessageKeyPrefix;

	static {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		enumMessageKeyPrefix = facesContext.getExternalContext().getInitParameter(ENUM_KEY_PREFIX_PARAM);

		String enumMessageBundle = facesContext.getExternalContext().getInitParameter(ENUM_MESSAGE_BUNDLE_PARAM);
		if (enumMessageBundle == null){
			resourceBundle = ResourceBundle.getBundle(facesContext.getApplication().getMessageBundle());
		} else {
			resourceBundle = ResourceBundle.getBundle(enumMessageBundle);
		}
	}

	public static String getEnum(Enum<?> e) {
		if (e == null) {
			return "";
		}

		String enumKeyPrefix = enumMessageKeyPrefix == null ? "" : enumMessageKeyPrefix + ".";
		String enumClass = getEnumClassName(e);
		String enumKey = enumKeyPrefix + enumClass + "." + e.name().toLowerCase();

		if (!resourceBundle.containsKey(enumKey)) {
			return e.toString();
		}

		return resourceBundle.getString(enumKey);
	}

	public static String getEnumStyle(Enum<?> e) {
		if (e == null) {
			return "";
		}

		String enumKeyPrefix = enumMessageKeyPrefix == null ? "" : enumMessageKeyPrefix + "-";
		String enumClass = getEnumClassName(e);
		String enumStyle = enumKeyPrefix + enumClass + "-" + e.name().toLowerCase();
		return enumStyle;
	}

	private static String getEnumClassName(Enum<?> e) {
		String enumClass = e.getClass().getSimpleName();
		return String.valueOf(enumClass.charAt(0)).toLowerCase() + enumClass.substring(1);
	}
}