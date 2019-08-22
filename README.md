#Алгоритмы нечеткого логического вывода

##Добавление в проект

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
*settings.xml*
```
<settings xmlns='http://maven.apache.org/SETTINGS/1.0.0' xsi:schemaLocation='http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>
  <profiles>
    <profile>

      <pluginRepositories>
        <pluginRepository>
          <snapshots>
            <enabled>
              false
            </enabled>
          </snapshots>
          <id>
            bintray-a-k-pohresniy-onotoliy
          </id>
          <name>
            bintray-plugins
          </name>
          <url>
            https://dl.bintray.com/a-k-pohresniy/onotoliy
          </url>
        </pluginRepository>
      </pluginRepositories>
      <id>
        bintray
      </id>
    </profile>
  </profiles>
  <activeProfiles>
    <activeProfile>
      bintray
    </activeProfile>
  </activeProfiles>
</settings>
```