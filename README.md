# Oghat
Prayer Times Calculation for Android

Setup
---

```groovy
repositories {
    maven {
        url 'https://dl.bintray.com/smasoumi/maven/'
    }
}
dependencies {
    //....
    compile 'io.saeid:oghat:1.0.0'
}
```

Usage
---
**Initializing** 
```java
PrayerTime pt = PrayerTime.getInstance()
.setDate(year, month, dayOfMonth) // in gregorian
.setLatLong(cityLatitude, cityLongitude)
.setTimeZone(4.5f)
.setCalculationType(...) // Optional - use CalculationType class (default is Institute of Geophysics, University of TEHRAN) 
.setJuristicType(...) // Optional - JuristicType class (default is Shafii)
.calculate();
```
**Get string format**
```java
pt.getSobhAzan()
pt.getToloo()
pt.getZohrAzan()
pt.getAsrAzan()
pt.getGhoroob()
pt.getMaghrebAzan()
pt.getEshaAzan()
pt.getMidnight()

```
**Or get `LocalTime` format**

```java
pt.getSobhAzanTime()
pt.getTolooTime()
pt.getZohrAzanTime()
pt.getAsrAzanTime()
pt.getMaghrebAzanTime()
pt.getEshaAzanTime()
pt.getMidnightDateTime()

```
