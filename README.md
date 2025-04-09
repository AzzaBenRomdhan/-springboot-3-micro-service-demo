# 🧩 Microservices Architecture with Spring Boot

Ce projet met en œuvre une architecture **microservices** avec **Spring Boot**, incluant les composants suivants : Eureka Server, Config Server, API Gateway, services métier (School et Student), OpenFeign pour la communication inter-services, Zipkin pour la traçabilité, et PostgreSQL comme base de données.

---

## 🏗️ Architecture du projet

```
                        +-----------------------+
                        |     Config Server     |
                        |   (Spring Cloud)      |
                        +----------+------------+
                                   |
                        +----------v------------+
                        |     Eureka Server     |
                        | (Service Discovery)   |
                        +----------+------------+
                                   |
             +---------------------+---------------------+
             |                                           |
    +--------v--------+                        +--------v--------+
    |   API Gateway    |                        |     Zipkin      |
    | (Routing Layer)  |                        | (Distributed    |
    +--------+--------+                        |   Tracing)      |
             |                                 +-----------------+
   +---------+----------+
   |                    |
+--v--+            +----v----+
|Student Service |  | School Service |
+------+----------+  +-------------+
       |                        |
       +------ PostgreSQL ------+

```

---

## 📦 Modules

### 1. `config-server`
- Centralise la configuration de tous les services.
- Stockage local ou distant (ex: Git).
- Port : `8888`.

### 2. `discovery-server` (Eureka)
- Service de découverte (registre les microservices).
- Port : `8761`.
- Accès UI : [http://localhost:8761](http://localhost:8761)

### 3. `api-gateway`
- Point d'entrée unique.
- Routage intelligent via Eureka.
- Port : `8080`.

### 4. `student-service`
- Service métier pour gérer les étudiants.
- Port : `8090`.

### 5. `school-service`
- Service métier pour gérer les écoles.
- Port : `8091`.

---

## 🔁 Communication entre microservices

- Réalisée avec **OpenFeign**.
- Exemple : `SchoolService` appelle `StudentService` pour récupérer les étudiants d'une école.

---

## ⚙️ Lancement des services

1. **Démarrer `docker-compose`** pour PostgreSQL, PgAdmin et Zipkin :
   ```bash
   docker-compose up -d
   ```

2. **Démarrer les services Spring Boot dans cet ordre** :
   - `config-server`
   - `discovery-server`
   - `api-gateway`
   - `school-service`
   - `student-service`

---

## 🐳 Docker Compose

```yaml
services:
  postgresql:
    image: postgres
    ports:
      - "5432:5432"
  pgadmin:
    image: dpage/pgadmin4
    ports:
      - "5050:80"
  zipkin:
    image: openzipkin/zipkin
    ports:
      - "9411:9411"
```

Accès Zipkin : [http://localhost:9411/zipkin/](http://localhost:9411/zipkin/)

---

## 🔍 Tracing avec Zipkin

Chaque microservice intègre Zipkin grâce aux dépendances suivantes :

```xml
<dependency>
  <groupId>io.micrometer</groupId>
  <artifactId>micrometer-tracing-bridge-brave</artifactId>
</dependency>
<dependency>
  <groupId>io.zipkin.reporter2</groupId>
  <artifactId>zipkin-reporter-brave</artifactId>
</dependency>
```

Et configuration dans `application.yml` :

```yaml
management:
  tracing:
    sampling:
      probability: 1.0
```

---

## 🛠 Technologies utilisées

- **Spring Boot**
- **Spring Cloud (Config, Eureka, Gateway, OpenFeign)**
- **PostgreSQL**
- **Docker & Docker Compose**
- **Zipkin**
- **Lombok**
- **Micrometer**

---

## 📧 Auteur

- 👩‍💻 Développé par Azza
- 🚀 [LinkedIn](https://www.linkedin.com/)

---

## ✅ TODO (idées d'amélioration)

- [ ] Ajouter Spring Security + JWT.
- [ ] Déploiement dans le cloud (Heroku, AWS, etc.).
- [ ] Intégration de Prometheus + Grafana.

---
