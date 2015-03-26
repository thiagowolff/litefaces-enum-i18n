# litefaces-enum-i18n
**litefaces-enum-i18n** is a simple utility library with enhanced support for JavaServer Faces (JSF 2.x) enabling internationalization of enum fields. The library provides a set of EL functions to simplify the basic tasks of translating and styling enum values in a JSF page.

## How to use it ?

### 1. Add the required dependencies 
(The releases are available from Maven Central)

```
<dependency>
  <groupId>br.com.litecode</groupId>
  <artifactId>litefaces-enum-i18n</artifactId>
  <version>1.0.2</version>
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
alertType.none = None
alertType.at_time_of_event = At time of event
alertType.fifteen_minutes_before = 15 minutes before
alertType.thirty_minutes_before = 30 minutes before
alertType.one_hour_before = 1 hour before
alertType.two_hours_before = 2 hours before
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
.alertType-none { color: #000000; }
.alertType-at_time_of_event =  { color: #ff0000; }
.alertType-fifteen_minutes_before =  { color: #00ff00; }
.alertType-thirty_minutes_before =  { color: #666666; }
.alertType-one_hour_before =  { color: #aabbcc; }
.alertType-two_hours_before =  { color: #ff00ff; }
```

```
<h:outputText value="#{lcf:enum(scheduleManager.event.alertType)} styleClass="#{lcf:enumStyle(scheduleManager.event.alertType)} />" 
```

## Configuration (optional)

### 1. Message bundle name

An optional initialization parameter can be defined in web.xml to specify the name of the bundle file where the translated enum values should be retrieved. If the parameter is not specified the default message bundle file will be used.
 
Example: to define the enum message bundle file as br/com/litecode/enums.properties, the following configuration should be added to web.xml

```
<context-param>
    <param-name>litefaces.ENUM_MESSAGE_BUNDLE</param-name>
    <param-value>br.com.litecode.enums</param-value>
</context-param>
```

### 2. Message key prefix

A prefix can be added to the message keys by setting the initialization parameter litefaces.ENUM_KEY_PREFIX in web.xml

Example:

```
<context-param>
    <param-name>litefaces.ENUM_KEY_PREFIX</param-name>
    <param-value>enum</param-value>
</context-param>
```

In this case the message keys should be defined as:

```
enum.alertType.none = None
enum.alertType.at_time_of_event = At time of event
enum.alertType.fifteen_minutes_before = 15 minutes before
enum.alertType.thirty_minutes_before = 30 minutes before
enum.alertType.one_hour_before = 1 hour before
enum.alertType.two_hours_before = 2 hours before
```
