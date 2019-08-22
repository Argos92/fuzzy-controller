# Алгоритмы нечеткого логического вывода


## Добавление в проект

**Gradle**:
```
repositories {
    maven {
        url  "https://dl.bintray.com/a-k-pohresniy/onotoliy"
    }
}


dependencies {
    compile 'com.github.onotoliy.fuzzycontroller:fuzzy-controller:1.0.2'
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
    <groupId>com.github.onotoliy.fuzzycontroller</groupId>
    <artifactId>fuzzy-controller</artifactId>
    <version>1.0.2</version>
    <type>jar</type>
</dependency>
```