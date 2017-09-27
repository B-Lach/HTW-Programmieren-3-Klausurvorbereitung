# Enums

Enums sind Sammlungen von Konstanten.

Die Konstanten haben keinen primitiven Datentyp => Sind Objekte, die Eigenschaften und Methoden besitzen können.

Beispiel:

```java
public enum Fahrzeug {
	AUTO, MOTORRAD;
}
// Ähnlich zu:
// public class Fahrzeug extends Enum {
	// public static final Fahrzeug AUTO = new Fahrzeug();
	// public static final Fahrzeug MOTORRAD = new Fahrzeug();
	
	// private Fahrzeug() {}
// }
```

## Standard-Methoden

### Statisch

#### Enum.valueOf()
Instanziiert ein Objekt über die statische Methode ```Objekt.valueOf(String s)```
Wirft eine ```java.lang.IllegalArgumentException``` wenn zu instanziierende Konstante nicht definiert wurde

##### Beispiel:
```java
// Instanziieren über Enum.valueOf(String s)
Fahrzeug.valueOf("AUTO"); 
// Nicht definierte Konstanten werfen IllegalArgumentException:
Fahrzeug.valueOf("FAHRRAD");
```

#### Enum.values()
Gibt ein Array vom definiertem Enum zurück, der für jede Konstante ein Objekt hält.

##### Beispiel
```java
Fahrzeug[] vals = Fahrzeug.values();
vals.length();	// => 2
vals[0]; 		// => Fahrzeug.AUTO
vals[1].name();	// => "MOTORRAD"
```

### Instanz

#### instance.name()

Gibt eine String-Repräsentation der Konstante zurück. Nutzung von ```toString()``` wird präferiert um detailierte Informationen über das Objekt anzugeben.

##### Beispiel
```java
Fahrzeug f = Fahrzeug.AUTO;
f.name(); // => "AUTO"
```

#### instance.ordinal()

Gibt die Ordnungzahl der genutzten Konstante zurück. Genauer: Verweist auf die Stelle, an der die Konstante definiert wurde.

##### Beispiel
```java
Fahrzeug f = Fahrzeug.AUTO;
f.ordinal(); // => 0
```

# Maven

Kurz und knapp, was interssant sein könnte.

## GAV
Die drei wichtigesten Punkte um ein neues Maven-Projekt anzulegen

### Group Id

Repräsentiert Hersteller. Nutzt die umgedrehte URL dafür: ```com.github```

### Articaft Id

Repräsentiert den Namen des zu erstellenden Produktes: ```awesomeProject```

### Version

Repräsentiert die Versionsnummer des Projektes
Die Anfügung von ```-SNAPSHOT``` deutet auf eine, sich noch in Entwicklung befindende, Version hin.

## POM.xml
Konfigurationsdatei für das Projekt. Hier werden projektspezifische Einstellungen festgehalten.
Dazu können gehören:

* Zu verwendener Compiler 
* Benötigte Dependencies
* etc

### Compiler setzen

```xml
<properties>
	<maven.compiler.source>1.8</maven.compiler.source>
	<maven.compiler.target>1.8</maven.compiler.target>
</properties>
```

### Dependency setzen

Beispiel, wie in der POM.xml Dependencies hinzugefügt werden. Genauer wird hier JUnit v5.0.0 als Abhängigkeit hinzugefügt.
 
```xml
<project>
	<dependencies>
		<dependency>
			<groupId>org.junit</groupId>
			<artifactId>junit4-engine</artifactId>
			<version>5.0.0-ALPHA</version>
		</dependency>
	</dependencies>
</project>
```

