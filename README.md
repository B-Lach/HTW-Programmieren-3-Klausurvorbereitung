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

Nicht erlaubt sind s
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

# Collections

Sind Klassem, die als "Behälter" für mehrere Objekte fungieren

In der Standardbibliothek sind unter anderem:

* List
* Set
* Map

## List

Dabei handelt es sich um eine lineare Datenstruktur. Objekte werden hintereinander in fester Reihenfolge gespeichert, ähnlich zu Array.

Ist die Reiehenfolge der Elemente von Relevanz oder können Elemente doppelt vorkommen? => List

### ArrayList

Wie ein Array, nur ohne Begrenzung - wächst dynamisch durch einfügen neuer Elemente. 

###### Vorteile
* schneller Zugriff auf einzelne Elemente

###### Nachteile
* Hinzufügen/Löschen langsam

### Codebeispiele

###### Deklaration und Instanziierung
```java
// Deklaration
List<String> liste;
// oder
LinkedList<String> list;

// Instanziierung
liste = new LinkedList<String>; // hier muss konkrete Klasse angegeben werden
```

###### Einfügen und Löschen

```java
// Am Ende anfügen
liste.add(element);

// An index einfügen
liste.add(index, element);

// Löscht Element an der Stelle, an der es zuerst gefunden wurde
liste.remove(element);
// Löscht Element an angegebenen index
liste.remove(index);
``` 

###### Direkter Zugriff

```java
// Gibt Anzahl der Elemente zurück
liste.size();

// Gibt Element an Position index zurück
// Wirft IndexOutOfBoundsException wenn  0 > index > liste.size() - 1
liste.get(index); 

// Setzt neues Element an der Stelle index
// Wirft IndexOutOfBoundsException wenn  0 > index > liste.size() - 1
liste.set(index, element);

```

### LinkedList

Verkettete Liste, in dem jedes Element auf das nächste verweist.

###### Vorteile
* Einfügen/Löschen in der Mitte relativ schnell

###### Nachteile
* Direkte Zugriff auf einzelne Elemente langsam

## Set

Dabei handelt es sich um eine Menge. Die Reihenfolge der Elemente ist beliebig, jedoch kann jedes Element nur einmal vorkommen

Ist die Reihenfolge der Elemente egal oder können Elemente nur einmal vorkommen? => Set

### HashSet
Generiert intern auf mit geerbten ```Object.hashCode()``` Methode Hashes, um die Elemente abzuspeichern. 

######  Vorteile
* Löschen / Einfügen sehr schnell

### TreeSet

Speichert Elemente in Baumstruktur, wodurch die Elemente automatisch sortiert werden. 
Bedingung ist, dass zu speichernde Elemente das Interface ```Comparable<E>``` implementieren

## Map

Dabei handelt es sich um eine assoziative Datenstruktur. Zu jedem Objekt wird ein identifizierender Schlüssel (Key) gespeichert. Der Zugriff auf ein bestimmtes Objekt überfolgt über den definierten Key.

Gibt es zu jedem Element einen eindeutigen Schlüssel? => Map

### HashMap

Für den Key wird intern ein Hash generiert, auf dessen Basis die Speicherreihenfolge festgelegt wird.

### TreeMap

Schlüssel werden sortiert in einer Baumstruktur gespeichert.

### Properties

Key-Value-Pairs vom Element String. Gedacht für die Speicherung von Systemeigenschaften. 

### Code-Beispiele

###### Deklaration

```java
HashMap<String, BigDecimal> map;
// oder
Map<String, BigDecimal> map;
```

###### Instanziierung

```java
map = new HashMap<String, BigDecimal>();
```


###### Einfügen und Löschen

```java
// Fügt für angegebenen Key definierte Value ein
// Überschreibt bereits vorhandenen Wert für angegebenen Schlüssel
map.put(key, value);

// Löschen des Elementes für angegebenen Key
map.remove(key);
``` 

###### Direkter Zugriff

```java
// Gibt Anzahl der Elemente zurück
map.size();

// Gibt Element für angegebenen Schlüssel zurück
// Wenn Key nicht vorhanden wird null zurückgegeben
map.get(key); 

// Testet ob key in der Map vorhanden ist
map.containsKey(key); // true || false
// Testet ob value in der Map vorhanden ist
map.containsValue(value); // true || false

```

## Erweiterte for-Schleife

Erweiterte Vorschleife funktioniert bei allen Klassen, die Iterable implementieren + Array

```java
for(String s: liste) {
	// mach was
}
```

## Iterator

Sind "Zeiger" auf in einer Collectino gespeichertes Element. Enthalten ebenso Info über nächstes Element-

Die erweiterte for-Schleife nutzt intern ebenso einen Iterator. 

```java
	// for(String s: liste) {} entspricht:

	Iterator<String> i = liste.iterator();
	// Check ob am Ende angekommen
	while(i.hasNext()) {
		String t = i.next();
		// Mach was mit t
		// i.next() gibt das Element an der aktuellen Position des Zeigers zurück
		// und bewegt den Zeiger auf das nächte Element
	}
```