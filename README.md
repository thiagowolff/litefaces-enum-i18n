# litefaces-enum-i18n
**litefaces-enum-i18n** is a simple utility library with enhanced support for JavaServer Faces (JSF 1.x and 2.0) enabling internationalization of enum fields. The library provides a set of EL functions to simplify the basic tasks of translating and styling enum values in a JSF page.

## How to use it ?

### 1. Add the required dependencies 
(The releases are available from Maven Central)

```
<dependency>
  <groupId>br.com.litecode</groupId>
  <artifactId>litefaces-enum-i18n</artifactId>
  <version>1.0.0</version>
</dependency>
```

### 2. Populate the message bundle file with the respective enum translations

Example:

```
public enum AlertType { 
    NONE, 
    AT_TIME_OF_EVENT, 
    FIFTEEN_MINUTES_BEFORE, 
    THIRTY_MINUTES_BEFORE, 
    ONE_HOUR_BEFORE
}
```

messages.properties

```
enum.alertType.none = Nenhum
enum.alertType.at_time_of_event = Na hora do evento
enum.alertType.fifteen_minutes_before = 15 minutos antes
enum.alertType.thirty_minutes_before = 30 minutos antes
enum.alertType.one_hour_before = 1 hora antes
enum.alertType.two_hours_before = 2 horas antes
```

### 3. Use the available EL functions to access the enum values in JSF pages

The LiteFaces EL functions are available under the following XML namespaces:
```
xmlns:lcf="http://litecode.com.br/functions"
```

Example:

```
<h:outputText value="#{lcf:enum(scheduleManager.event.alertType)} />" 
```

Additionaly, dash separated CSS class names can be defined following the enum naming convention:

style.css

```
enum-alertType-none { color: #000000; }
enum-alertType-at_time_of_event =  { color: #ff0000; }
enum-alertType-fifteen_minutes_before =  { color: #00ff00; }
enum-alertType-thirty_minutes_before =  { color: #666666; }
enum-alertType-one_hour_before =  { color: #aabbcc; }
enum-alertType-two_hours_before =  { color: #ff00ff; }
```

```
<h:outputText value="#{lcf:enum(scheduleManager.event.alertType)} styleClass="#{lcf:enumStyle(scheduleManager.event.alertType)} />" 
```

### 4. Setup message bundle name (optional)

An optional initialization parameter can be defined in web.xml to specify the name of the bundle file where the translated enum values should be retrieved. If the parameter is not specified the default message bundle file will be used.
 
Example: to define the enum message bundle file as br/com/litecode/enums.properties, the following configuration should be added to web.xml

```
<context-param>
    <param-name>litefaces.ENUM_MESSAGE_BUNDLE</param-name>
    <param-value>br.com.litecode.enums</param-value>
</context-param>
```

