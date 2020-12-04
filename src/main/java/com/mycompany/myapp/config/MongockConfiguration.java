package com.mycompany.myapp.config;

import com.github.cloudyrock.mongock.driver.mongodb.sync.v4.driver.MongoSync4Driver;
import com.github.cloudyrock.standalone.MongockStandalone;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import io.quarkus.runtime.StartupEvent;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class MongockConfiguration {

  void onStart(@Observes StartupEvent ev) {
    MongoClient mongoClient = MongoClients.create(MongoClientSettings.builder().build());
    MongockStandalone
      .builder()
      .setDriver(MongoSync4Driver.withDefaultLock(mongoClient, "jhipster"))
      .addChangeLogsScanPackage("com.mycompany.myapp.config.dbmigrations")
      .buildRunner()
      .execute();
  }
}
