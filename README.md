lorem
=====

An extremely useful Lorem Ipsum generator for Java!

### Getting Started:

You can get the latest release via maven:
```xml
<dependency>
	<groupId>com.thedeanda</groupId>
	<artifactId>lorem</artifactId>
	<version>2.2</version>
</dependency>
```

Alternatively, you can add this to gradle:
```gradle
compile 'com.thedeanda:lorem:2.2'
```

Now prepare your implementation:
```java
Lorem lorem = LoremIpsum.getInstance();
```




### Sample Usage:

#### Words

```java
lorem.getWords(5, 10);
```
Randomly generates between 5 and 10 words.

```java
lorem.getParagraphs(2, 4);
```
Randomly generates between 2 and 4 paragraphs.

```java
lorem.getHtmlParagraphs(2, 4);
```
Randomly generates between 2 and 4 paragraphs wrapped in &lt;p&gt; tags.

```java
lorem.getTitle(2, 4);
```
Randomly generates a title of 2 to 4 words.


#### Names

```java
lorem.getName();
```
Generates a random name.

```java
lorem.getNameFemale();
```
Generates a random female name.

```java
lorem.getNameMale();
```
Generates a random male name.

```java
lorem.getFirstName();
```
Generates a random first name.

```java
lorem.getLastName();
```
Generates a random last name.



#### Phone Numbers

```java
lorem.getPhone();
```
Generates a phone number in the form: `(800) 555-1212`



#### Locations

```java
lorem.getCity();
```
Generates a city name.

```java
lorem.getStateFull();
```
Generates a state name.

```java
lorem.getStateAbbr();
```
Generates a 2 letter state abbreviation.

```java
lorem.getZipCode();
```
Generates a 5-digit zip code

```java
lorem.getCountry();
```
Generates a country name

#### Custom Category

```java
lorem.getCustomValue("nouns");
```
Generates a random value from the seeded file value passed to the Lorem constructor.
If the category is not loaded `CategoryNotExist` exception will be thrown