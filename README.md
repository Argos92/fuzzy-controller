# Алгоритмы нечеткого логического вывода

Библиотека реализующая алгоритмы нечеткого логического вывода.

Реализованы следующие алгоритмы:
+ Алгоритм Ларсена.
+ Алгоритм Мамдани.
+ Алгоритм Сугено.
+ Алгоритм Цукамото.
+ Упрощенный алгоритм нечеткого вывода.

#### Подключение

**Gradle**:
```
repositories {
    maven {
        url  "https://dl.bintray.com/a-k-pohresniy/onotoliy"
    }
}


dependencies {
    compile 'com.github.onotoliy:fuzzy-controller:1.0.0'
}
```

**Maven**:
```
<repositories>
    <repository>
        <snapshots>
            <enabled>
                false
            </enabled>
        </snapshots>
        <id>
            bintray-a-k-pohresniy-onotoliy
        </id>
        <name>
            bintray
        </name>
        <url>
            https://dl.bintray.com/a-k-pohresniy/onotoliy
        </url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.onotoliy</groupId>
    <artifactId>fuzzy-controller</artifactId>
    <version>1.0.0</version>
    <type>jar</type>
</dependency>
```

#### Примеры использования
+ [Алгоритм Ларсена](https://github.com/onotoliy/fuzzy-controller/blob/master/src/test/java/com/github/onotoliy/fuzzycontroller/algorithms/LarsenBuilderTest.java)
+ [Алгоритм Мамдани](https://github.com/onotoliy/fuzzy-controller/blob/master/src/test/java/com/github/onotoliy/fuzzycontroller/algorithms/MamdaniBuilderTest.java)
+ [Алгоритм Сугено](https://github.com/onotoliy/fuzzy-controller/blob/master/src/test/java/com/github/onotoliy/fuzzycontroller/algorithms/SugenoBuilderTest.java)
+ [Алгоритм Цукамото](https://github.com/onotoliy/fuzzy-controller/blob/master/src/test/java/com/github/onotoliy/fuzzycontroller/algorithms/TsukamotoBuilderTest.java)
+ [Упрощенный алгоритм нечеткого вывода](https://github.com/onotoliy/fuzzy-controller/blob/master/src/test/java/com/github/onotoliy/fuzzycontroller/algorithms/SimplifiedAlgorithmBuilderTest.java)
