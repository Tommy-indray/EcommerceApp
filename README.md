# EcommerceApp

Application web e-commerce basée sur le patron **MVC 2** et l'API **JPA** (Jakarta Persistence), réalisée dans le cadre de l'atelier « Application web basée sur MVC2 et JPA ».

- **Auteur** : Indray Christommy
- **Encadrant** : Pr. ELAACHAK Lotfi
- **Module** : Architecture Web Distribuée - JEE et Spring (Cycle Ingénieur LSI, FST Tanger)

## Fonctionnalités

| Module | Servlet | Actions |
|---|---|---|
| Produits | `ProduitServlet` | lister, ajouter, modifier, supprimer |
| Internautes | `InternauteServlet` | lister (avec total du panier), ajouter (panier créé automatiquement), modifier, supprimer |
| Paniers | `PanierServlet` | lister les lignes, ajouter une ligne, supprimer une ligne (total mis à jour) |
| Vitrines | `VitrineServlet` | lister, ajouter, modifier, supprimer |

Chaque Servlet gère plusieurs actions grâce au paramètre `action` (principe du MVC 2).

Au premier démarrage, un jeu de données marocain est inséré automatiquement (8 produits, 4 vitrines, 5 internautes avec paniers). L'initialisation est idempotente : aucun doublon aux démarrages suivants.

## Technologies

| Élément | Version |
|---|---|
| Java | 21 (LTS) |
| Build | Maven (packaging WAR) |
| Serveur | Apache Tomcat 10.1.x |
| Base de données | MariaDB / MySQL |
| API de persistance | Jakarta Persistence 3.2.0 |
| Implémentation JPA | EclipseLink 5.0.2 |
| Driver JDBC | MySQL Connector/J 9.1.0 |
| Vues | JSP + JSTL (Jakarta Tags 3.0) |
| Front-end | HTML5 + CSS3 |

## Modèle de données

```
Internaute 1 ---- 1 Panier          @OneToOne  (cascade ALL, panier_id)
Panier     1 ---- * LignePanier     @OneToMany (cascade ALL, orphanRemoval, mappedBy = panier)
LignePanier * --- 1 Produit         @ManyToOne (produit_id)
Internaute * ---- * Vitrine         @ManyToMany (table internaute_vitrine)
```

Les tables `internaute`, `panier`, `ligne_panier`, `produit`, `vitrine` et `internaute_vitrine` sont générées automatiquement par EclipseLink (`create-tables`).

## Structure du projet

```
EcommerceApp/
├── pom.xml
└── src/main/
    ├── java/com/ecommerce/
    │   ├── entities/     Internaute, Panier, LignePanier, Produit, Vitrine
    │   ├── servlets/     InternauteServlet, PanierServlet, ProduitServlet, VitrineServlet
    │   └── util/         JPAUtil, DataInitializer, AppStartupListener
    ├── resources/META-INF/persistence.xml
    └── webapp/
        ├── index.jsp
        ├── css/style.css
        └── WEB-INF/      web.xml, navbar.jspf, pages *-list.jsp et *-form.jsp
```

## Prérequis

- JDK 21
- Maven 3.9+
- Apache Tomcat 10.1 (Jakarta Servlet 6, pas Tomcat 9 ou moins)
- MariaDB ou MySQL en fonctionnement

## Installation et lancement

1. **Cloner le dépôt**
   ```bash
   git clone https://github.com/Tommy-indray/EcommerceApp.git
   cd EcommerceApp
   ```

2. **Configurer la base de données** : ouvrir `src/main/resources/META-INF/persistence.xml` et adapter l'URL JDBC, l'utilisateur et le mot de passe à votre installation. La base cible doit exister (ou être créable par l'utilisateur configuré).

3. **Compiler le WAR**
   ```bash
   mvn clean package
   ```

4. **Déployer** : copier le fichier `.war` généré dans `target/` vers le dossier `webapps/` de Tomcat, puis démarrer Tomcat.

5. **Ouvrir l'application** : `http://localhost:8080/<nom-du-war>/` (le chemin dépend du nom du fichier WAR déployé).

## Problèmes courants

| Problème | Cause | Solution |
|---|---|---|
| `Unsupported class file major version` | Java trop récent pour EclipseLink | Utiliser Java 21 |
| Erreur 404 au démarrage | WAR non déployé | Copier le WAR dans `webapps/` de Tomcat |
| `ClassNotFoundException: javax.servlet...` | Tomcat 8.5 ou 9 | Utiliser Tomcat 10.1 (jakarta.*) |
| `Communications link failure` | Connexions inactives fermées par la base | Pool avec `testOnBorrow` et `SELECT 1` (déjà configuré) |

## Licence

Projet pédagogique, usage académique.
