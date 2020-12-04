# jhipster

This application was generated using JHipster 6.10.1 and JHipster Quarkus 0.1.6, you can find documentation and help at [https://www.jhipster.tech/documentation-archive/v6.10.1](https://www.jhipster.tech/documentation-archive/v6.10.1).

# How to make it works

###Add dependencies in the pom.xml

```
<dependency>
   <groupId>io.quarkus</groupId>
   <artifactId>quarkus-mongodb-panache</artifactId>
</dependency>
<dependency>
   <groupId>com.github.cloudyrock.mongock</groupId>
   <artifactId>mongock-standalone</artifactId>
</dependency>
<dependency>
   <groupId>com.github.cloudyrock.mongock</groupId>
   <artifactId>mongodb-sync-v4-driver</artifactId>
</dependency>
<dependency>
   <groupId>org.mongodb</groupId>
   <artifactId>mongodb-driver-sync</artifactId>
   <version>4.1.1</version>
</dependency>
<dependency>
   <groupId>org.apache.commons</groupId>
   <artifactId>commons-lang3</artifactId>
</dependency>
```

###Add properties and remove sql properties by templating

```
# configure the MongoDB client for a replica set of two nodes
quarkus.mongodb.connection-string=mongodb://localhost:27017
# mandatory if you don't specify the name of the database using @MongoEntity
quarkus.mongodb.database=jhipster
```

##Add new files to init db through Mongock
`com.mycompany.myapp.config.dbmigrations.InitialSetupMigration;`
`com.mycompany.myapp.config.MongockConfiguration;`

###Update User and Authority
No need to take care of ids, extends PanacehMongoEntity. Update findOneWithAuthoritiesByLogin
With a simple find(“login”, login)

###In UserService.java
In the method updateUser() call explicitly the user.update() at the end.

###UserDto
Change Id from Long to ObjectId. Change mapper and Mappertest too.

Don’t generate TestResources.java

Be sure Mongo docker-compose files are created (should be done by JHipster).

###Don’t generate:

`JHipsterCompatiblePhysicalNamingStrategy.java`
`JHipsterCompatibleImplicitNamingStrategy.java`

###NOTE:
Because of the Mongo Panache bug (https://github.com/quarkusio/quarkus/issues/12514) you have to move User.java and Authority.java Entity into the package where classes use them.

Example:

On the first start be sure to move files into the `com.mycompany.myapp.config.dbmigrations` folder and then move them into the `com.mycompany.myapp.service;` folder.
